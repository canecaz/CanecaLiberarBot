package me.caneca.liberarbot.plugin.listeners;

import me.caneca.api.api.API;
import me.caneca.liberarbot.Main;
import me.caneca.liberarbot.utils.cache.Cache;
import me.caneca.liberarbot.utils.objects.AllowPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import java.util.concurrent.TimeUnit;

public class PlayerPreJoinListener implements Listener {

    private final Cache cache;

    public PlayerPreJoinListener(Main main, Cache cache) {
        this.cache = cache;
        API.registerListener(main, this);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerPreJoin(AsyncPlayerPreLoginEvent event) {
        String name = event.getName();
        if (!cache.getRequireAuthentication().contains(name.toLowerCase())) return;

        String ip = event.getAddress().toString().substring(1);
        String cachedIp = cache.getIP(name);

        if (!ip.equals(cachedIp)) {
            AllowPlayer allowPlayer = cache.getAllowPlayer(name);

            if (allowPlayer == null || allowPlayer.isExpired() || !allowPlayer.isAllowed()) {
                event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER,
                        API.colored("&cVocê está em um IP diferente do registrado da última vez." +
                                "\nDigite seu nick no canal liberar do discord." +
                                "\nVocê tem 15 segundos para isso."));

                long time = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(15);
                allowPlayer = new AllowPlayer(ip, time);

                cache.getAllow().put(name, allowPlayer);
                return;
            }

            cache.getAllow().remove(name);
            cache.getLastIPs().put(name, ip);
            cache.saveIP(name, ip);
        }
    }
}

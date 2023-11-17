package me.caneca.liberarbot.utils.cache;

import lombok.Getter;
import me.caneca.liberarbot.Main;
import me.caneca.liberarbot.utils.objects.AllowPlayer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
public class Cache {

    private final Set<String> requireAuthentication = new HashSet<>();
    private final Map<String, String> lastIPs = new HashMap<>();
    private final Map<String, AllowPlayer> allow = new HashMap<>();

    public String getIP(String name) {
        return getLastIPs().getOrDefault(name, null);
    }

    public AllowPlayer getAllowPlayer(String name) {
        return getAllow().getOrDefault(name, null);
    }

    public void saveIP(String name, String ip) {
        Main.getInstance().getConfig().set("ips." + name, ip);
        Main.getInstance().saveConfig();
    }
}

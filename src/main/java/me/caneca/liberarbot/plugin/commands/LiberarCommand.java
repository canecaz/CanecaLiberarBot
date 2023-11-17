package me.caneca.liberarbot.plugin.commands;

import me.caneca.api.api.API;
import me.caneca.liberarbot.Main;
import me.caneca.liberarbot.utils.cache.Cache;
import me.caneca.liberarbot.utils.cache.loader.CacheLoader;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class LiberarCommand implements CommandExecutor {

    private final Main main;
    private final Cache cache;

    public LiberarCommand(Main main, Cache cache) {
        this.main = main;
        this.cache = cache;
        API.registerCommand(main, "liberar", this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!API.hasPermission(sender, "superior")) return true;

        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            main.reloadConfig();
            new CacheLoader(main.getConfig(), cache);

            sender.sendMessage(API.colored("&ePlugin recarregado."));
            API.LEVEL_UP(sender);
            return true;
        }

        sender.sendMessage(API.colored("&cUtilize: /liberar reload."));
        API.VILLAGER_NO(sender);
        return false;
    }
}

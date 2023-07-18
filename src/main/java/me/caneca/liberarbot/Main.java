package me.caneca.liberarbot;

import me.caneca.liberarbot.utils.cache.Cache;
import me.caneca.liberarbot.utils.cache.loader.CacheLoader;
import me.caneca.liberarbot.utils.loader.Loader;
import me.caneca.liberarbot.utils.manager.BotManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Main getInstance() {
        return getPlugin(Main.class);
    }

    private BotManager botManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        reloadConfig();
        Cache cache = new Cache();
        botManager = new BotManager();
        botManager.start(cache);
        new CacheLoader(getConfig(), cache);
        new Loader(this, cache);
    }

    @Override
    public void onDisable() {
        botManager.shutdown();
    }
}

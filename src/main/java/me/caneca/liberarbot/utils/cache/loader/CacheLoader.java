package me.caneca.liberarbot.utils.cache.loader;

import me.caneca.liberarbot.utils.cache.Cache;
import org.bukkit.configuration.file.FileConfiguration;

public class CacheLoader {

    public CacheLoader(FileConfiguration config, Cache cache) {
        if (!config.contains("ips")) return;
        cache.getLastIPs().clear();
        config.getConfigurationSection("ips").getKeys(false).forEach(key ->
                cache.getLastIPs().put(key, config.getString("ips." + key)));
    }
}

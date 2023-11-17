package me.caneca.liberarbot.utils.cache.loader;

import me.caneca.liberarbot.utils.cache.Cache;
import org.bukkit.configuration.file.FileConfiguration;

public class CacheLoader {

    public CacheLoader(FileConfiguration config, Cache cache) {
        cache.getLastIPs().clear();
        cache.getRequireAuthentication().clear();

        if (config.contains("require-authentication"))
            cache.getRequireAuthentication().addAll(config.getStringList("require-authentication"));

        if (config.contains("ips"))
            config.getConfigurationSection("ips").getKeys(false).forEach(key ->
                cache.getLastIPs().put(key, config.getString("ips." + key)));
    }
}

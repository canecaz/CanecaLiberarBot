package me.caneca.liberarbot.utils.loader;

import me.caneca.liberarbot.Main;
import me.caneca.liberarbot.plugin.PlayerPreJoinListener;
import me.caneca.liberarbot.utils.cache.Cache;

public class Loader {

    private final Main main;
    private final Cache cache;

    public Loader(Main main, Cache cache) {
        this.main = main;
        this.cache = cache;
        loadListeners();
    }

    public void loadListeners() {
        new PlayerPreJoinListener(main, cache);
    }

}

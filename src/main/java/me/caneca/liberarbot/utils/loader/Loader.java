package me.caneca.liberarbot.utils.loader;

import me.caneca.liberarbot.Main;
import me.caneca.liberarbot.plugin.commands.LiberarCommand;
import me.caneca.liberarbot.plugin.listeners.PlayerPreJoinListener;
import me.caneca.liberarbot.utils.cache.Cache;

public class Loader {

    private final Main main;
    private final Cache cache;

    public Loader(Main main, Cache cache) {
        this.main = main;
        this.cache = cache;

        loadListeners();
        loadCommands();
    }

    private void loadListeners() {
        new PlayerPreJoinListener(main, cache);
    }

    private void loadCommands() {
        new LiberarCommand(main, cache);
    }

}

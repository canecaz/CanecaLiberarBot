package me.caneca.liberarbot.plugin;

import me.caneca.liberarbot.utils.cache.Cache;
import me.caneca.liberarbot.utils.objects.AllowPlayer;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class DiscordListeners extends ListenerAdapter {

    private final Cache cache;

    public DiscordListeners(Cache cache) {
        this.cache = cache;
    }

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        TextChannel channel = event.getChannel();
        if (event.getAuthor().isBot() || !channel.getId().equals("1125263742968541257")) return;
        String message = event.getMessage().getContentRaw();
        event.getMessage().delete().queue();
        AllowPlayer allowPlayer = cache.getAllowPlayer(message);
        if (allowPlayer == null || allowPlayer.isExpired()) {
            channel.sendMessage("'" + message + "' não precisa ser liberado.")
                    .queue(message1 -> message1.delete().queueAfter(5, TimeUnit.SECONDS));
            return;
        }
        if (allowPlayer.isAllowed()) {
            channel.sendMessage("'" + message + "' já foi liberado!")
                    .queue(message1 -> message1.delete().queueAfter(5, TimeUnit.SECONDS));
            return;
        }
        allowPlayer.setAllowed(true);
        allowPlayer.setEndTime(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(15));
        channel.sendMessage("'" + message + "' foi liberado e tem 15 segundos para entrar no servidor!")
                .queue(message1 -> message1.delete().queueAfter(5, TimeUnit.SECONDS));
    }
}

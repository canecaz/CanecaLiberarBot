package me.caneca.liberarbot.utils.manager;

import lombok.Getter;
import me.caneca.liberarbot.plugin.DiscordListeners;
import me.caneca.liberarbot.utils.cache.Cache;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

@Getter
public class BotManager {

    public JDA jda;

    public void start(Cache cache) {
        try {
            jda = JDABuilder.createDefault("MTEyNTI1NzA4MDU4MDg4MjQ5NA.GJIp2W.7xP5yhja7i-0DU_GWslcr5PVJP_Qg7RlvICHIk")
                    .enableIntents(GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MEMBERS).build();
            jda.addEventListener(new DiscordListeners(cache));
        } catch (LoginException e) {
            throw new RuntimeException(e);
        }
    }

    public void shutdown() {
        if (jda == null) return;
        jda.shutdownNow();
    }
}

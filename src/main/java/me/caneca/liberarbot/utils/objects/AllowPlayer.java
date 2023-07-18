package me.caneca.liberarbot.utils.objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AllowPlayer {

    private final String ip;
    private long endTime;
    private boolean allowed;

    public AllowPlayer(String ip, long endTime) {
        this.ip = ip;
        this.endTime = endTime;
        this.allowed = false;
    }

    public boolean isExpired() {
        return this.endTime <= System.currentTimeMillis();
    }
}

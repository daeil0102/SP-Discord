package net.teujaem.spDiscord.model;

public class DiscordMessageModel {

    private String message;
    private String channel;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}

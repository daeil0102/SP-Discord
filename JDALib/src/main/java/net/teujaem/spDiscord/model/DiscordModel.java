package net.teujaem.spDiscord.model;

import java.util.logging.Logger;

public class DiscordModel {

    private String token;
    private String serverId;
    private String consoleChannelId;
    private String chatChannelId;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getConsoleChannelId() {
        return consoleChannelId;
    }

    public void setChatChannelId(String chatChannelId) {
        this.chatChannelId = chatChannelId;
    }

    public String getChatChannelId() {
        return chatChannelId;
    }

    public void setConsoleChannelId(String consoleChannelId) {
        this.consoleChannelId = consoleChannelId;
    }
}
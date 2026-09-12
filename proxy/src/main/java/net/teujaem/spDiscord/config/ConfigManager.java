package net.teujaem.spDiscord.config;

public class ConfigManager {

    private String token = "token";
    private String serverId = "serverId";
    private String consoleChannelId = "consoleChannelId";
    private String chatChannelId = "chatChannelId";

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

    public void setConsoleChannelId(String consoleChannelId) {
        this.consoleChannelId = consoleChannelId;
    }

    public String getChatChannelId() {
        return chatChannelId;
    }

    public void setChatChannelId(String chatChannelId) {
        this.chatChannelId = chatChannelId;
    }
}
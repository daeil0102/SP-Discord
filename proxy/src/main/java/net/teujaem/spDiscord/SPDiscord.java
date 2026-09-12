package net.teujaem.spDiscord;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import net.teujaem.spDiscord.config.ConfigManager;
import net.teujaem.spDiscord.config.LoadConfig;
import net.teujaem.spDiscord.listener.DiscordChatListener;
import net.teujaem.spDiscord.model.DiscordModel;
import net.teujaem.spFramework.SPFramework;
import org.slf4j.Logger;

import java.nio.file.Path;

@Plugin(
        id = "spdiscord",
        name = "SP-Discord",
        version = BuildConstants.VERSION
)

public class SPDiscord {

    @Inject
    private final Logger logger;

    private static SPDiscord instance;

    private final ProxyServer server;

    private ConfigManager configManager;
    private Discord discord;
    private final Path dataDirectory;

    @Inject
    public SPDiscord(
            ProxyServer server,
            Logger logger,
            @DataDirectory Path dataDirectory
    ) {

        instance = this;

        this.server = server;
        this.logger = logger;
        this.dataDirectory = dataDirectory;
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        configManager =
                LoadConfig.load(
                        dataDirectory,
                        logger
                );

        DiscordModel discordModel = new DiscordModel();
        discordModel.setToken(configManager.getToken());
        discordModel.setServerId(configManager.getServerId());
        discordModel.setChatChannelId(configManager.getChatChannelId());
        discordModel.setConsoleChannelId(configManager.getConsoleChannelId());

        discord = new Discord(discordModel);
        discord.addMessageEvent(new DiscordChatListener());
        discord.start();

        logger.info("SP-Discord가 시작되었습니다.");
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public Discord getDiscord() {
        return discord;
    }

    public ProxyServer getServer() {
        return server;
    }

    public static SPDiscord getInstance() {
        return instance;
    }
}

package net.teujaem.spDiscord;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Dependency;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import net.teujaem.spDiscord.config.ConfigManager;
import net.teujaem.spDiscord.config.LoadConfig;
import net.teujaem.spDiscord.listener.chat.DiscordChatListener;
import net.teujaem.spDiscord.listener.chat.MCChatListener;
import net.teujaem.spDiscord.listener.console.DiscordConsoleListener;
import net.teujaem.spDiscord.listener.console.MCConsoleListener;
import net.teujaem.spDiscord.listener.player.JoinListener;
import net.teujaem.spDiscord.listener.player.QuitListener;
import net.teujaem.spDiscord.model.DiscordModel;
import org.slf4j.Logger;

import java.nio.file.Path;

@Plugin(
        id = "spdiscord",
        name = "SP-Discord",
        version = BuildConstants.VERSION,
        dependencies = {
                @Dependency(id = "spframework")
        }
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
        discord.addMessageEvent(new DiscordConsoleListener());
        discord.start();

        logger.info("SP-Discord가 시작되었습니다.");

        server.getEventManager().register(this, new MCChatListener(this));
        server.getEventManager().register(this, new MCConsoleListener(this));
        server.getEventManager().register(this, new JoinListener(this));
        server.getEventManager().register(this, new QuitListener(this));

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

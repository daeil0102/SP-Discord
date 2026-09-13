package net.teujaem.spDiscord;

import net.teujaem.spDiscord.config.ConfigManager;
import net.teujaem.spDiscord.config.LoadConfig;
import net.teujaem.spDiscord.listener.ChatListener;
import net.teujaem.spDiscord.listener.ConsoleListener;
import net.teujaem.spDiscord.listener.discord.SendConsoleListener;
import org.bukkit.plugin.java.JavaPlugin;

public class SPDiscord extends JavaPlugin  {

    private static SPDiscord instance;

    private static ConfigManager configManager;

    @Override
    public void onEnable() {

        instance = this;

        reload();

    }

    @Override
    public void onDisable() {

    }

    private void reload() {
        getServer().getPluginManager().registerEvents(new ChatListener(this), this);
        getServer().getPluginManager().registerEvents(new ConsoleListener(this), this);
        getServer().getPluginManager().registerEvents(new SendConsoleListener(this), this);

        configManager = LoadConfig.load(this);
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public static SPDiscord getInstance() {
        return instance;
    }

}

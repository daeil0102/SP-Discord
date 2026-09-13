package net.teujaem.spDiscord.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class LoadConfig {

    public static ConfigManager load(Plugin plugin) {

        File file = new File(plugin.getDataFolder(), "config.yaml");

        if (!file.exists()) {
            plugin.saveResource("config.yaml", false);
        }

        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        ConfigManager configManager = new ConfigManager();

        configManager.setName(
                config.getString("name", getTopFileName())
        );

        return configManager;
    }

    private static String getTopFileName() {
        try {
            return new File(".").getCanonicalFile().getName();
        } catch (IOException e) {
            return "";
        }
    }
}
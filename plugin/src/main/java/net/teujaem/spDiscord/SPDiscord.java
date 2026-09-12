package net.teujaem.spDiscord;

import org.bukkit.plugin.java.JavaPlugin;

public class SPDiscord extends JavaPlugin  {

    private static SPDiscord instance;

    @Override
    public void onEnable() {

        instance = this;

    }

    @Override
    public void onDisable() {

    }

    public static SPDiscord getInstance() {
        return instance;
    }
}

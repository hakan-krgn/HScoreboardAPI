package com.hakan.scoreboard;

import com.hakan.scoreboard.listeners.ConnectionListeners;
import com.hakan.scoreboard.scoreboard.nms.SetupNMS;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Plugin instance;

    public static Plugin getInstance() {
        return instance;
    }

    public static void setup(Plugin plugin) {
        if (instance != null) {
            Bukkit.getLogger().warning("HScoreboardAPI already registered.");
            return;
        }
        instance = plugin;
        new SetupNMS().setup();
        Bukkit.getPluginManager().registerEvents(new ConnectionListeners(), plugin);
    }

    @Override
    public void onEnable() {
        setup(this);
    }
}
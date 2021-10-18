package me.sillysock;

import me.sillysock.Commands.NickCommand;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SillyNick extends JavaPlugin {

    private static Logger logger;
    private static FileConfiguration config;

    @Override
    public void onEnable() {
        logger = getLogger();
        config = getConfig();

        getCommand("nick").setExecutor(new NickCommand());

        saveDefaultConfig();

        success("Registered /nick");
        success("The plugin has loaded successfully!");
    }

    @Override
    public void onDisable() {
        success("The plugin has disabled successfully!");
    }

    public static void success(final String err) {
        logger.log(Level.INFO, ChatColor.GREEN + err);
    }

    public static void err(final String err) {
        logger.log(Level.SEVERE, ChatColor.RED + "ERROR: " + err);
    }

    public static FileConfiguration getConfiguration() {
        return config;
    }
}

package me.sillysock;

import me.sillysock.Commands.NickCommand;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SillyNick extends JavaPlugin {

    private static Logger logger; // Literally just to log shit
    private static FileConfiguration config; // Modify the config.yml file
    private static HashMap<Player, String> nicknamedPlayers = new HashMap<>();

    @Override
    public void onEnable() { // When the plugin loads on startup, the below code is executed.
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

    public static HashMap<Player, String> getNicknamedPlayers() {
        return nicknamedPlayers;
    }

    public static boolean isNicked(final Player user) {
        return nicknamedPlayers.containsKey(user);
    }
}

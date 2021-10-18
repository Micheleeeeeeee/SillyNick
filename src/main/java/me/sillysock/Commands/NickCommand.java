package me.sillysock.Commands;

import me.sillysock.SillyNick;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NickCommand implements CommandExecutor {

    Player p;

    @Override
    public boolean onCommand(final CommandSender sender, final Command cmd, final String s, final String[] args) {

        if (!(sender instanceof Player)) {
            SillyNick.err("Only players may execute this command.");
            return true;
        }

        p = (Player) sender;

        if (args.length < 1) {
            p.sendMessage(ChatColor.GREEN + "Usage: /nick <nickname>");
            return true;
        }

        if (hasBadWordsOrNameIsTaken(args[0])) {
            p.sendMessage(ChatColor.RED + "Your nickname contains illegal characters or it is taken.");
            return true;
        }

        p.setDisplayName(args[0]);
        p.setPlayerListName(args[0]);

        p.sendMessage(ChatColor.GREEN + "Your nickname has been set to " + ChatColor.YELLOW + args[0]);

        return false;
    }

    boolean hasBadWordsOrNameIsTaken(final String nick) {

        for (final Object word : SillyNick.getConfiguration().getList("badwords")) {
            if (nick.contains(word.toString())) return true;
        }

        for (final Player p : Bukkit.getOnlinePlayers()) {
            if (p.getName().equals(nick) && !p.getName().equals(user)) return true;
        }

        return false;
    }
}

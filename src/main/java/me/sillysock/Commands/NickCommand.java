package me.sillysock.Commands;

import me.sillysock.SillyNick;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class NickCommand implements CommandExecutor {

    Player p;
    String nick;
    String name;

    @Override
    public boolean onCommand(final CommandSender sender, final Command cmd, final String s, final String[] args) {

        if (!(sender instanceof Player)) {
            SillyNick.err("Only players may execute this command.");
            return true;
        }

        p = (Player) sender;
        name = p.getName();

        if (args.length < 1) {
            p.sendMessage(ChatColor.GREEN + "Usage: /nick <nickname>");
            return true;
        }

        if (args[0].equalsIgnoreCase("reset"))
            if (SillyNick.isNicked(p)) {
                p.setDisplayName(name);
                p.setPlayerListName(name);
                p.sendMessage(ChatColor.GREEN + "Your nickname has been reset.");
                SillyNick.getNicknamedPlayers().remove(p);
            }


        nick = args[0];

        if (hasBadWordsOrNameIsTaken(nick, p)) {
            p.sendMessage(ChatColor.RED + "Your nickname contains illegal characters or it is taken.");
            p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_FALL, 10L, 10L);
            return true;
        }

        p.setDisplayName(nick);
        p.setPlayerListName(nick);

        p.sendMessage(ChatColor.GREEN + "Your nickname has been set to " + ChatColor.YELLOW + nick);

        return false;
    }

    private boolean hasBadWordsOrNameIsTaken(final String nick, final Player user) {

        for (final Object word : SillyNick.getConfiguration().getList("badwords"))
            if (nick.contains(word.toString())) return true;


        for (final Player p : Bukkit.getOnlinePlayers())
            if (p.getName().equals(nick) && !p.getName().equals(user.getName()) || p.getDisplayName().equals(nick)) return true;

        return false;
    }
}

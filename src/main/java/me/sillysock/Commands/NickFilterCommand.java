package me.sillysock.Commands;

import me.sillysock.SillyNick;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NickFilterCommand implements CommandExecutor {

    @Override
    public boolean onCommand(final CommandSender sender, final Command cmd, final String s, final String[] args) {

        if (!(sender instanceof Player)) {
            SillyNick.err("Only players may execute this command.");
            return true;
        }



        return false;
    }

}
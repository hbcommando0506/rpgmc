package me.hbcommando.rpgmc.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Fly implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String [] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("This command could only be run by players");
            return true;
        }

        Player player = (Player) sender;

        if (player.getAllowFlight()) {
            player.setAllowFlight(false);
            player.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "Flight | " + ChatColor.RED + "Disabled");
        } else {
            player.setAllowFlight(true);
            player.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "Flight | " + ChatColor.GREEN + "Enabled");
        }

        return true;
    }
}

package com.frostcraft.admintools;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpeedCommand implements CommandExecutor {
    AdminTools plugin;

    public SpeedCommand(AdminTools plugin) { this.plugin = plugin; }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player;
        java.util.UUID uuid;
        try {
            player = (Player) sender;
            uuid = player.getUniqueId();
        } catch (Exception e) {
            sender.sendMessage("This command must be issued by a player.");
            return true;
        }

        if (!plugin.verifiedList.contains(uuid)) {
            sender.sendMessage("You are not verified to do this.");
            return true;
        }

        float value = 1;

        try {
            value = Float.parseFloat(args[1]);
            if (args[0].startsWith("walk")) { value *= 2; }
        } catch (Exception e) {
            sender.sendMessage("Invalid float value.");
            return true;
        }

        value /= 10;

        if (value > 1) {
            value = 1;
        } else if (value < -1) {
            value = -1;
        }

        if (args[0].startsWith("fl")) {
            player.setFlySpeed(value);
            sender.sendMessage("Set flight speed to " + value);
        } else if (args[0].startsWith("walk")) {
            player.setWalkSpeed(value);
            sender.sendMessage("Set the walk speed to " + value);
        } else {
            sender.sendMessage("Error?: " + value);
            return true;
        }

        return true;
    }
}

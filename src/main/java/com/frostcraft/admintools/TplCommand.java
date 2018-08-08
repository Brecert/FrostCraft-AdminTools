package com.frostcraft.admintools;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TplCommand implements CommandExecutor {
    AdminTools plugin;

    public TplCommand(AdminTools plugin) { this.plugin = plugin; }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player;
        java.util.UUID uuid;
        try {
            player = (Player) sender;
            uuid = player.getUniqueId();
        }
        catch (Exception e) {
            sender.sendMessage("This command must be issued by a player.");
            return true;
        }

        if (!plugin.verifiedList.contains(uuid)) {
            sender.sendMessage("You are not verified to do this.");
            return true;
        }

        Player tplPlayer;
        try {
            tplPlayer = Bukkit.getServer().getPlayer(args[0]);
        }
        catch (Exception e) {
            player.sendMessage("Player " + args[0] + " not found or not specified.");
            return true;
        }

        player.teleport(tplPlayer);
        player.sendMessage("You have been teleported to " + tplPlayer.getName());

        return true;
    }
}

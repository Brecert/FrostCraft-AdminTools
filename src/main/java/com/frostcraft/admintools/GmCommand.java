package com.frostcraft.admintools;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GmCommand implements CommandExecutor {
    AdminTools plugin;

    public GmCommand(AdminTools plugin) { this.plugin = plugin; }

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

        if (player.getGameMode().equals(GameMode.CREATIVE)) {
            player.setGameMode(GameMode.SURVIVAL);
            player.sendMessage("Gamemode set to survival.");
        } else {
            player.setGameMode(GameMode.CREATIVE);
            player.sendMessage("Gamemode set to creative.");
        }

        return true;
    }
}

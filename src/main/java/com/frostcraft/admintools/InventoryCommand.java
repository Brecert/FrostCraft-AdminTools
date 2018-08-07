package com.frostcraft.admintools;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InventoryCommand implements CommandExecutor {
    AdminTools plugin;

    public InventoryCommand(AdminTools plugin) { this.plugin = plugin; }

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


        Player inventoryPlayer;
        try {
            inventoryPlayer = Bukkit.getServer().getPlayer(args[0]);
        } catch (Exception e) {
            sender.sendMessage("Player " + args[0] + "not found, or no player specified.");
            return true;
        }

        if (args[1].startsWith("ender")) {
            player.openInventory(inventoryPlayer.getEnderChest());
        } else {
            player.openInventory(inventoryPlayer.getInventory());
        }

        return true;
    }
}

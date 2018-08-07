package com.frostcraft.admintools;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VanishCommand implements CommandExecutor {
    AdminTools plugin;

    public VanishCommand(AdminTools plugin) { this.plugin = plugin; }

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
            return false;
        }

        if (!plugin.verifiedList.contains(uuid)) {
            sender.sendMessage("You are not verified to do this.");
            return false;
        }

        // Make sure no args are met.
        // TODO: Find a better way to do this.
        if (args.length > 0) {
            sender.sendMessage("Usage:");
            sender.sendMessage("vanish");

            return false;
        }


        boolean isVanished = false;

        if (plugin.vanishList.contains(uuid)) { isVanished = true; }

        // TODO: Do something with Player.canSee(player)
        if (isVanished) {
            for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                p.showPlayer(plugin, player);
            }
            plugin.vanishList.remove(uuid);
            sender.sendMessage("You are no longer vanished.");
        } else {
            for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                p.hidePlayer(plugin, player);
            }
            plugin.vanishList.add(uuid);
            sender.sendMessage("You have vanished!");
        }

        return true;
    }
}

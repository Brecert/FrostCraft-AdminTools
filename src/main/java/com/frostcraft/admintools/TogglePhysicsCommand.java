package com.frostcraft.admintools;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TogglePhysicsCommand implements CommandExecutor {
    AdminTools plugin;

    public TogglePhysicsCommand(AdminTools plugin) { this.plugin = plugin; }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            if (!plugin.verifiedList.contains(((Player) sender).getUniqueId())) {
                sender.sendMessage("You are not verified to do this.");
                return false;
            }
        }

        plugin.enablePhysics = !plugin.enablePhysics;
        sender.sendMessage(plugin.enablePhysics ? "Physics enabled." : "Physics disabled.");
        return true;
    }
}

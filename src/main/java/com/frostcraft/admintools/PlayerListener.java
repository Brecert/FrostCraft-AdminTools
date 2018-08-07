package com.frostcraft.admintools;


import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    private AdminTools plugin;

    public PlayerListener(AdminTools plugin) { this.plugin = plugin; }

    /********************************************************************************
     * PLAYER JOIN EVENT
     ********************************************************************************/
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player;
        UUID uuid;

        player = event.getPlayer();
        uuid = player.getUniqueId();

        // Hide vanished players
        for (UUID vanishedPlayer : AdminTools.vanishList) {
            player.hidePlayer(plugin, Bukkit.getServer().getPlayer(vanishedPlayer));
        }
    }

    /********************************************************************************
     * PLAYER QUIT EVENT
     ********************************************************************************/
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player;
        UUID uuid;

        player = event.getPlayer();
        uuid = player.getUniqueId();

        AdminTools.verifiedList.remove(uuid);
        AdminTools.vanishList.remove(uuid);
    }
}

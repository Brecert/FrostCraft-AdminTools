package com.frostcraft.admintools;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;

public class BlockListener implements Listener {
    /********************************************************************************
     * 			BLOCK PHYSICS EVENT
     ********************************************************************************/
    @EventHandler
    public void onBlockPhysics(BlockPhysicsEvent event)
    {
        if (!AdminTools.enablePhysics) {
            event.setCancelled(true);
        }
    }
}

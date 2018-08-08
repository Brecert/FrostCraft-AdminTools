package com.frostcraft.admintools;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class AdminTools extends JavaPlugin {

    public static boolean enablePhysics = true;
    public static List<UUID> verifiedList = new ArrayList<UUID>();
    public static List<UUID> vanishList = new ArrayList<UUID>();

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new PlayerListener(this),this);
        getServer().getPluginManager().registerEvents(new BlockListener(),this);

        getCommand("verify").setExecutor(new VerifyCommand(this));
        getCommand("vanish").setExecutor(new VanishCommand(this));
        getCommand("togglephysics").setExecutor(new TogglePhysicsCommand(this));
        getCommand("speed").setExecutor(new SpeedCommand(this));
        getCommand("inventory").setExecutor(new InventoryCommand(this));
        getCommand("tpl").setExecutor(new TplCommand(this));
        getCommand("gm").setExecutor(new GmCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

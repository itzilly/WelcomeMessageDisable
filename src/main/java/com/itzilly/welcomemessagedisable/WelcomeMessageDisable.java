package com.itzilly.welcomemessagedisable;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class WelcomeMessageDisable extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("Starting WelcomeMessageDisable v1.9 by itzilly");
        Bukkit.getLogger().info("Need help? Visit https://itzilly.com/plugins/spigot/WelcomeMessageDisable");

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        getCommand("wmd").setExecutor(new configCommand(this));

        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        if (getConfig().getBoolean("disable-join-message")) {
            event.setJoinMessage("");
        }
    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event) {
        if (getConfig().getBoolean("disable-quit-message")) {
            event.setQuitMessage("");
        }
    }

}

package com.core.thewolfbadger.cratedrop.main;

import com.core.thewolfbadger.cratedrop.cmds.DropCommand;
import com.core.thewolfbadger.cratedrop.drop.Drop;
import com.core.thewolfbadger.cratedrop.drop.OpenDrop;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: TheWolfBadger
 * Date: 8/15/15
 * Time: 2:58 PM
 */
public class Main extends JavaPlugin {
    Random rand;
    Drop drop;
    DropCommand dc;
    OpenDrop oD;
    boolean worldGuardInstalled = false;
    boolean commands = false;
    public Random getRandom() {
        return this.rand;
    }
    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.rand = new Random();
        this.oD = new OpenDrop(this);
        this.drop = new Drop(this, this.oD);
        this.dc = new DropCommand(this, this.drop);
        new BukkitRunnable() {
            int timer = 0;
            int timeToDrop = getConfig().getInt("Options.DropEach");
            @Override
        public void run() {
                this.timer++;
                if(this.timer >= this.timeToDrop) {
                    Location crateDropped = drop.drop();
                    String x = String.valueOf(crateDropped.getX());
                    String z = String.valueOf(crateDropped.getZ());
                    String y = String.valueOf(crateDropped.getY());
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("Messages.alert_header")));
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("Messages.alert").replace("{X}", x).replace("{Z}", z).replace("{Y}", y)));
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("Messages.alert_footer")));
                    this.timer = 0;
                }
            }
        }.runTaskTimer(this, 0L, 20L);
        this.getServer().getPluginManager().registerEvents(this.drop, this);
        this.getCommand("cratedrop").setExecutor(this.dc);
    }
    @Override
    public void onDisable() {}
}

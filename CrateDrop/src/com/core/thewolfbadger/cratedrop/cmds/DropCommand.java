package com.core.thewolfbadger.cratedrop.cmds;

import com.core.thewolfbadger.cratedrop.drop.Drop;
import com.core.thewolfbadger.cratedrop.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created with IntelliJ IDEA.
 * User: TheWolfBadger
 * Date: 8/15/15
 * Time: 4:13 PM
 */
public class DropCommand implements CommandExecutor {
    private Main m;
    private Drop drop;
    public DropCommand(Main m, Drop d) {
        this.m = m;
        this.drop = d;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if(cmd.getName().equalsIgnoreCase("cratedrop")) {
            if(sender instanceof Player) {
                Player p = (Player) sender;
                if(p.hasPermission("CrateDrop.usage")) {
                    Location crateDropped = drop.drop();
                    String x = String.valueOf(crateDropped.getX());
                    String z = String.valueOf(crateDropped.getZ());
                    String y = String.valueOf(crateDropped.getY());
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', m.getConfig().getString("Messages.alert_header")));
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', m.getConfig().getString("Messages.alert").replace("{X}", x).replace("{Z}", z).replace("{Y}", y)));
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', m.getConfig().getString("Messages.alert_footer")));
                }
            }
        }
        return true;
    }
}

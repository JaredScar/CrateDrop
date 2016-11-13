package com.core.thewolfbadger.cratedrop.drop;

import com.core.thewolfbadger.cratedrop.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

/**
 * Created with IntelliJ IDEA.
 * User: TheWolfBadger
 * Date: 8/15/15
 * Time: 3:38 PM
 */
public class Drop implements Listener {
    private Main m;
    private OpenDrop oD;
    public Drop(Main m, OpenDrop oD) {
        this.m = m;
        this.oD = oD;
    }
    public Location drop() {
        int min = m.getConfig().getInt("Options.RadiusMin");
        int max = m.getConfig().getInt("Options.RadiusMax");
        int result = m.getRandom().nextInt((max - min) + min);
        int resultSecond = m.getRandom().nextInt((max-min)+min);
        String world = m.getConfig().getString("Options.WorldEnabled");
        int y = m.getConfig().getInt("Options.DropFrom");
        Location drop = new Location(Bukkit.getWorld(world), result, y, resultSecond);
        //TODO Commands section
        drop.getChunk().load();
        String material = m.getConfig().getString("Options.CrateMaterialDrop");
        FallingBlock fb = Bukkit.getWorld(world).spawnFallingBlock(drop, Material.valueOf(material), (byte) 0);
        MetadataValue mdv = new FixedMetadataValue(this.m, "drop");
        fb.setMetadata("cratedrop", mdv);
        while (Bukkit.getWorld(world).getBlockAt(result, y, resultSecond).getType() == Material.AIR) {
            y--;
        }
        Location locationToReturn = new Location(Bukkit.getWorld(world), result, y, resultSecond);
        return locationToReturn;
    }
    //TODO
    public void deconfuse(String commandsSection) {}
    @EventHandler
    public void onBlockChange(EntityChangeBlockEvent evt) {
        if(evt.getEntity() instanceof FallingBlock) {
            FallingBlock fb = (FallingBlock) evt.getEntity();
            if(fb.getMaterial() == Material.valueOf(m.getConfig().getString("Options.CrateMaterialDrop"))) {
                String material = m.getConfig().getString("Options.CrateMaterial");
                fb.remove();
                evt.setCancelled(true);
                evt.getBlock().setType(Material.valueOf(material));
                Block b = evt.getBlock();
                MetadataValue mdv = new FixedMetadataValue(this.m, "drop");
                b.setMetadata("cratedropBlock", mdv);
            }
        }
    }
    @EventHandler
    public void onBlockInteract(PlayerInteractEvent evt) {
        if(evt.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block b = evt.getClickedBlock();
            if(b !=null) {
                String material = m.getConfig().getString("Options.CrateMaterial");
                if(b.getType() == Material.valueOf(material)) {
                    if(b.hasMetadata("cratedropBlock")) {
                        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', m.getConfig().getString("Messages.crate_collected").replace("{PLAYER}", evt.getPlayer().getName()).replace("{X}", String.valueOf(b.getX())).replace("{Y}", String.valueOf(b.getY())).replace("{Z}", String.valueOf(b.getZ()))));
                        b.setType(Material.AIR);
                        oD.loadInventory(evt.getPlayer());
                    }
                }
            }
        }
    }
}

package com.core.thewolfbadger.cratedrop.drop;

import com.core.thewolfbadger.cratedrop.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: TheWolfBadger
 * Date: 8/15/15
 * Time: 5:26 PM
 */
public class OpenDrop {
    private Main m;
    public OpenDrop(Main m) {
        this.m = m;
    }
    public void loadInventory(Player p) {
        int random = m.getRandom().nextInt(100)+1;
        List<String> crates = m.getConfig().getStringList("CrateList");
        String crate = "";
        for(String crateS : crates) {
            if(m.getConfig().getIntegerList("CrateChance").get(crates.indexOf(crateS)) >= random) {
                crate = crateS;
            }
        }
            List<String> rewards = m.getConfig().getStringList("Crates."+crate+".rewards");
            List<Integer> amounts = m.getConfig().getIntegerList("Crates."+crate+".amount");
            List<String> enchants = m.getConfig().getStringList("Crates."+crate+".enchant");
            List<String> displayNames = m.getConfig().getStringList("Crates."+crate+".displayName");
            List<String> lores = m.getConfig().getStringList("Crates."+crate+".lore");
            List<Integer> chance = m.getConfig().getIntegerList("Crates."+crate+".chance");
            Inventory inv = Bukkit.createInventory(p, m.getConfig().getInt("Crates."+crate+".rows")*9, ChatColor.translateAlternateColorCodes('&', m.getConfig().getString("Crates."+crate+".title")));
            for(int i=0; i<rewards.size(); i++) {
                if(rewards.get(i) !=null) {
                    if(isMaterial(rewards.get(i))) {
                        int chanceNum = chance.get(i);
                        if(chanceNum >= m.getRandom().nextInt(100-1)+1) {
                            ItemStack item = toItemStack(rewards.get(i));
                            int amount = amounts.get(i);
                            item.setAmount(amount);
                            String enchantString = "";
                            String[] enchantMultiple = enchants.get(i).split(", ");
                            if(enchantMultiple.length >= 1) {
                                enchantMultiple = enchants.get(i).split(", ");
                            } else {
                                enchantString = enchants.get(i);
                                enchantMultiple = new String[] {""};
                            }
                            String displayName = ChatColor.translateAlternateColorCodes('&', displayNames.get(i));
                            String loreString = "";
                            String[] loreMultiple = lores.get(i).split(" // ");
                            if(loreMultiple.length >= 1) {
                                loreMultiple = lores.get(i).split(" // ");
                            } else {
                                loreString = lores.get(i);
                                loreMultiple = new String[] {""};
                            }
                            if(!enchantMultiple[0].equals("")) {
                                for(String enchant : enchantMultiple) {
                                    String[] data = enchant.split(":");
                                    this.addEnchantment(item, data[0], Integer.parseInt(data[1]));
                                }
                            } else
                                if(!enchantString.equals("")) {
                                    String[] data = enchantString.split(":");
                                    this.addEnchantment(item, data[0], Integer.parseInt(data[1]));
                                }
                            ItemMeta itemMeta = item.getItemMeta();
                            itemMeta.setDisplayName(displayName);
                            if(!loreMultiple[0].equals("")) {
                                ArrayList<String> list = new ArrayList<String>();
                                for(String lore : loreMultiple) {
                                    list.add(ChatColor.translateAlternateColorCodes('&', lore));
                                }
                                itemMeta.setLore(list);
                            } else
                            if(!loreString.equals("")) {
                                ArrayList<String> list = new ArrayList<String>();
                                list.add(ChatColor.translateAlternateColorCodes('&', loreString));
                                itemMeta.setLore(list);
                            }
                            item.setItemMeta(itemMeta);
                            inv.addItem(item);
                        }
                    }
                }
            }
        p.openInventory(inv);
        }
    public ItemStack toItemStack(String s) {
        if(s.contains(":")) {
            String[] data = s.split(":");
            return new ItemStack(Material.valueOf(data[0]), 1, Short.valueOf(data[1]));
        }
        return new ItemStack(Material.valueOf(s), 1);
    }
    public void addEnchantment(ItemStack item, String ench, Integer level) {
        for(Enchantment enchants : Enchantment.values()) {
            if(enchants.getName().toUpperCase().equals(ench.toUpperCase())) {
                item.addEnchantment(enchants, level);
                item.addUnsafeEnchantment(enchants, level);
            }
        } System.out.print("Enchantments: "+item.getEnchantments());
    }
    public boolean isMaterial(String s) {
        if(s.contains(":")) {
            String[] data = s.split(":");
            try {
                new ItemStack(Material.valueOf(data[0]), 1, Short.valueOf(data[1]));
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            try {
                Material.valueOf(s);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }
}

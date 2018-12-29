package com.ymail.cannibalturtle87;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.event.inventory.InventoryClickEvent;

public abstract class OperatingSystem {
    private final String name;
    
    public OperatingSystem(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public static OperatingSystem getOSByName(String name) {
        switch (name) {
            case "Com/OS":
                return new ComOS();
            case "CraftOS":
                return new CraftOS();
            default:
                return null;
        }
    }
    
    public static ItemStack getOSDisc(String name) {
        ItemStack osDisc = new ItemStack(Material.GREEN_RECORD, 1);
        ItemMeta osMeta = osDisc.getItemMeta();
        osMeta.setDisplayName("\u00A7bOperating System");
        ArrayList<String> osLore = new ArrayList<>();
        osMeta.setLore(null);
        switch (name) {
            case "Com/OS":
                osLore.add("\u00A75Com/OS");
                osMeta.setLore(osLore);
                osDisc.setItemMeta(osMeta);
                return osDisc;
            case "CraftOS":
                osLore.add("\u00A75CraftOS");
                osMeta.setLore(osLore);
                osDisc.setItemMeta(osMeta);
                return osDisc;
            default:
                return null;
        }
    }
    
    public static ItemStack getLoginDisc() {
        ItemStack osDisc = new ItemStack(Material.RECORD_3, 1);
        ItemMeta osMeta = osDisc.getItemMeta();
        osMeta.setLore(null);
        osMeta.setDisplayName("\u00A7bLog-in Disc");
        osDisc.setItemMeta(osMeta);
        return osDisc;
    }
    
    public Inventory homeScreen(Player playa) {
        Inventory inv = Bukkit.createInventory(playa, 54, "Computer");
        ItemStack menu = getMenuIcon();
        ItemStack users = getUsersIcon();
        ItemStack compose = getComposeIcon();
        for(int i = 0; i < 45; i++) {
            inv.setItem(i, getBackground());
        }
        for(int i = 45; i < 54; i++) {
            inv.setItem(i, getTaskbar());
        }
        inv.setItem(45, menu);
        inv.setItem(46, users);
        inv.setItem(47, compose);
        ItemStack logOut = getLogOutIcon();
        inv.setItem(53, logOut);
        return inv;
    }

    public Inventory contactsScreen(Player playa) {
        Inventory inv = Bukkit.createInventory(playa, 54, "Computer - Contacts");
        ItemStack users = getUsersIcon();
        for(int i = 0; i < 45; i++) {
            inv.setItem(i, getBackground());
        }
        for(int i = 45; i < 54; i++) {
            inv.setItem(i, getTaskbar());
        }
        for(int i = 0; i < Computer.getLoggedInPlayers().keySet().size(); i++) {
            ItemMeta usersMeta = users.getItemMeta();
            Player[] loggedInPlayers = new Player[]{};
            loggedInPlayers = Computer.getLoggedInPlayers().keySet().toArray(loggedInPlayers);
            usersMeta.setDisplayName(loggedInPlayers[i].getName());
            String ip = "ID: " + ChatColor.GREEN + loggedInPlayers[i].getUniqueId().toString();
            ArrayList<String> lore = new ArrayList<>();
            lore.add(ip);
            usersMeta.setLore(lore);
            users.setItemMeta(usersMeta);
            inv.setItem(i, users);
        }
        ItemStack menu = getMenuIcon();
        inv.setItem(45, menu);
        ItemStack logOut = getLogOutIcon();
        inv.setItem(53, logOut);
        return inv;
    }
    
    public Inventory composeScreen(Player playa) {
        Inventory inv = Bukkit.createInventory(playa, 54, "Computer - File Upload");
        ItemStack yellowGlass = new ItemStack(Material.STAINED_GLASS_PANE, 1);
        for(int i = 0; i < 45; i++) {
            inv.setItem(i, getBackground());
        }
        for(int i = 45; i < 54; i++) {
            inv.setItem(i, getTaskbar());
        }
        inv.setItem(12, yellowGlass);
        inv.setItem(13, yellowGlass);
        inv.setItem(14, yellowGlass);
        inv.setItem(21, yellowGlass);
        inv.setItem(22, new ItemStack(Material.AIR));
        inv.setItem(23, yellowGlass);
        inv.setItem(30, yellowGlass);
        inv.setItem(31, yellowGlass);
        inv.setItem(32, yellowGlass);
        ItemStack menu = getMenuIcon();
        inv.setItem(45, menu);
        ItemStack logOut = getLogOutIcon();
        inv.setItem(53, logOut);
        return inv;
    }

    public Inventory inboxScreen(Player playa, ItemStack is) {
        Inventory inv = Bukkit.createInventory(playa, 54, "Computer - File Receive");
        ItemStack whiteGlass = new ItemStack(Material.STAINED_GLASS_PANE, 1);
        for(int i = 0; i < 45; i++) {
            inv.setItem(i, getBackground());
        }
        for(int i = 45; i < 54; i++) {
            inv.setItem(i, getTaskbar());
        }
        inv.setItem(12, whiteGlass);
        inv.setItem(13, whiteGlass);
        inv.setItem(14, whiteGlass);
        inv.setItem(21, whiteGlass);
        inv.setItem(22, is);
        inv.setItem(23, whiteGlass);
        inv.setItem(30, whiteGlass);
        inv.setItem(31, whiteGlass);
        inv.setItem(32, whiteGlass);
        ItemStack menu = getMenuIcon();
        inv.setItem(45, menu);
        ItemStack logOut = getLogOutIcon();
        inv.setItem(53, logOut);
        return inv;
    }

    public Inventory usersScreen(Player playa) {
        Inventory inv = Bukkit.createInventory(playa, 54, "Computer - Users");
        ItemStack users = getUsersIcon();
        for(int i = 0; i < 45; i++) {
            inv.setItem(i, getBackground());
        }
        for(int i = 45; i < 54; i++) {
            inv.setItem(i, getTaskbar());
        }
        int i = 0;
        for(Player p : Bukkit.getServer().getOnlinePlayers()) {
            ItemMeta usersMeta = users.getItemMeta();
            usersMeta.setDisplayName(p.getName());
            String ip = "ID: " + ChatColor.GREEN + p.getUniqueId().toString();
            ArrayList<String> lore = new ArrayList<>();
            lore.add(ip);
            usersMeta.setLore(lore);
            users.setItemMeta(usersMeta);
            inv.setItem(i, users);
            i ++;
        }
        ItemStack menu = getMenuIcon();
        inv.setItem(45, menu);
        ItemStack logOut = getLogOutIcon();
        inv.setItem(53, logOut);
        return inv;
    }
    
    public boolean onInventoryClick(InventoryClickEvent e) {
    	return false;
    };
    
    public abstract ItemStack getMenuIcon();
    
    public abstract ItemStack getUsersIcon();
    
    public abstract ItemStack getComposeIcon();
    
    public abstract ItemStack getLogOutIcon();
    
    public abstract ItemStack getBackground();
    
    public abstract ItemStack getTaskbar();
}

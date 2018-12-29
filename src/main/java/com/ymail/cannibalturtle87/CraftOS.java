package com.ymail.cannibalturtle87;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;

public class CraftOS extends OperatingSystem {
    public CraftOS() {
        super("CraftOS");
    }
    
    @Override
    public Inventory homeScreen(Player playa) {
        Inventory inv = Bukkit.createInventory(playa, 54, "Computer");
        for(int i = 0; i < 45; i++) {
            inv.setItem(i, getBackground());
        }
        for(int i = 45; i < 54; i++) {
            inv.setItem(i, getTaskbar());
        }
        inv.setItem(36, getLogOutIcon());
        inv.setItem(45, getMenuIcon());
        inv.setItem(46, getUsersIcon());
        inv.setItem(53, Utils.setName(new ItemStack(Material.WATCH, 1), "\u00A7r\u00A76Clock"));
        return inv;
    }
    
    public Inventory menuScreen(Player playa) {
        Inventory inv = Bukkit.createInventory(playa, 54, "Computer - Menu");
        for(int i = 0; i < 45; i++) {
            inv.setItem(i, getBackground());
        }
        for(int i = 45; i < 54; i++) {
            inv.setItem(i, getTaskbar());
        }
        inv.setItem(10, getComposeIcon());
        if (Main.blingjeweledpresent) {
        	inv.setItem(19, getBlingJeweledIcon());
        }
        inv.setItem(45, getMenuIcon());
        inv.setItem(46, getUsersIcon());
        inv.setItem(53, Utils.setName(new ItemStack(Material.WATCH, 1), "\u00A7r\u00A76Clock"));
        return inv;
    }
    
    private ItemStack getBlingJeweledIcon() {
    	return Utils.setName(new ItemStack(Material.EMERALD, 1), "\u00A7r\u00A7aBlingJeweled");
    }
    
    @Override
    public boolean onInventoryClick(InventoryClickEvent e) {
    	Player player = (Player) e.getWhoClicked();
        if (e.getCurrentItem().getType() == getMenuIcon().getType()) {
            if(e.getInventory().getTitle().equalsIgnoreCase("Computer - Menu")) {
                player.openInventory(homeScreen(player));
                return true;
            } else {
                player.openInventory(menuScreen(player));
                return true;
            }
        }
        if (e.getCurrentItem().getType() == getCloseIcon().getType()) {
            player.openInventory(homeScreen(player));
            return true;
        }
        if(e.getInventory().getTitle().equalsIgnoreCase("Computer - Menu")) {
            if(e.getCurrentItem().getType() == getBlingJeweledIcon().getType() && Main.blingjeweledpresent) {
                player.closeInventory();
                com.sethbling.blingjeweled.BlingJeweled.instance.newGame(player);
                return true;
            }
        }
        if(e.getCurrentItem().getType() == getComposeIcon().getType()) {
            player.openInventory(contactsScreen(player));
            return true;
        }
        return false;
    }
    
    @Override
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
            usersMeta.setDisplayName("\u00A7r" + loggedInPlayers[i].getName());
            String ip = "\u00A7rID: " + ChatColor.GREEN + loggedInPlayers[i].getUniqueId().toString();
            ArrayList<String> lore = new ArrayList<>();
            lore.add(ip);
            usersMeta.setLore(lore);
            users.setItemMeta(usersMeta);
            inv.setItem(i, users);
        }
        ItemStack menu = getMenuIcon();
        inv.setItem(45, menu);
        //ItemStack logOut = getLogOutIcon();
        //inv.setItem(52, logOut);
        inv.setItem(53, Utils.setName(new ItemStack(Material.WATCH, 1), "\u00A7r\u00A76Clock"));
        inv.setItem(8, getCloseIcon());
        return inv;
    }
    
    @Override
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
        //ItemStack logOut = getLogOutIcon();
        //inv.setItem(52, logOut);
        inv.setItem(53, Utils.setName(new ItemStack(Material.WATCH, 1), "\u00A7r\u00A76Clock"));
        inv.setItem(8, getCloseIcon());
        return inv;
    }
    
    @Override
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
        //ItemStack logOut = getLogOutIcon();
        //inv.setItem(52, logOut);
        inv.setItem(53, Utils.setName(new ItemStack(Material.WATCH, 1), "\u00A7r\u00A76Clock"));
        inv.setItem(8, getCloseIcon());
        return inv;
    }
    
    @Override
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
            usersMeta.setDisplayName("\u00A7r" + p.getName());
            String ip = "\u00A7rID: " + ChatColor.GREEN + p.getUniqueId().toString();
            ArrayList<String> lore = new ArrayList<>();
            lore.add(ip);
            usersMeta.setLore(lore);
            users.setItemMeta(usersMeta);
            inv.setItem(i, users);
            i ++;
        }
        ItemStack menu = getMenuIcon();
        inv.setItem(45, menu);
        //ItemStack logOut = getLogOutIcon();
        //inv.setItem(52, logOut);
        inv.setItem(53, Utils.setName(new ItemStack(Material.WATCH, 1), "\u00A7r\u00A76Clock"));
        inv.setItem(8, getCloseIcon());
        return inv;
    }

    public ItemStack getCloseIcon() {
        return Utils.setName(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14), "\u00A7r\u00A7cClose");
    }
    
    @Override
    public ItemStack getMenuIcon() {
        return Utils.setName(new ItemStack(Material.ENDER_PEARL, 1), "\u00A7r\u00A75Menu");
    }

    @Override
    public ItemStack getUsersIcon() {
        return Utils.setName(new ItemStack(Material.SKULL_ITEM, 1, (short) 3), "\u00A7rUsers");
    }

    @Override
    public ItemStack getLogOutIcon() {
        return Utils.setName(new ItemStack(Material.WOOD_DOOR, 1), "\u00A7rLog Out");
    }

    @Override
    public ItemStack getComposeIcon() {
        return Utils.setName(new ItemStack(Material.BOOK_AND_QUILL, 1), "\u00A7rFile Transfer");
    }

    @Override
    public ItemStack getBackground() {
        return Utils.setName(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15), "\u00A7rDesktop");
    }

    @Override
    public ItemStack getTaskbar() {
        return Utils.setName(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 3), "\u00A7rTaskbar");
    }
}

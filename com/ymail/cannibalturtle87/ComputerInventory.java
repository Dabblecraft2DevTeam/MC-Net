/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ymail.cannibalturtle87;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 *
 * @author Hayden
 */
public class ComputerInventory {
    
    public static Inventory createComputerInventory(Player playa) {
        Inventory inv = Bukkit.createInventory(playa, 54, "Computer");
        ItemStack blackGlass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)15);
        ItemStack blueGlass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 11);
        ItemStack menu = getMenuItemStack();
        ItemStack users = getUsersItemStack();
        ItemStack compose = getComposeItemStack();
        for(int i = 0; i < 45; i++) {
            inv.setItem(i, blackGlass);
        }
        for(int i = 45; i < 54; i++) {
            inv.setItem(i, blueGlass);
        }
        inv.setItem(45, menu);
        inv.setItem(46, users);
        inv.setItem(47, compose);
        return inv;
    }
    
    public static Inventory createContactsInventory(Player playa) {
        Inventory inv = Bukkit.createInventory(playa, 54, "Computer - Contacts");
        ItemStack blackGlass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)15);
        ItemStack blueGlass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 11);
        ItemStack users = getUsersItemStack();
        for(int i = 0; i < 45; i++) {
            inv.setItem(i, blackGlass);
        }
        for(int i = 45; i < 54; i++) {
            inv.setItem(i, blueGlass);
        }
        for(int i = 0; i < Computer.getLoggedInPlayers().keySet().size(); i++) {
            ItemMeta usersMeta = users.getItemMeta();
            Player[] loggedInPlayers = new Player[]{};
            loggedInPlayers = Computer.getLoggedInPlayers().keySet().toArray(loggedInPlayers);
            usersMeta.setDisplayName(loggedInPlayers[i].getName());
            String ip = "IP: " + ChatColor.GREEN + loggedInPlayers[i].getUniqueId().toString();
            ArrayList<String> lore = new ArrayList<>();
            lore.add(ip);
            usersMeta.setLore(lore);
            users.setItemMeta(usersMeta);
            inv.setItem(i, users);
        }
        ItemStack menu = getMenuItemStack();
        inv.setItem(45, menu);
        return inv;
    }
    
    public static Inventory createComposeInventory(Player playa) {
        Inventory inv = Bukkit.createInventory(playa, 54, "Computer - File Upload");
        ItemStack blackGlass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)15);
        ItemStack blueGlass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 11);
        ItemStack whiteGlass = new ItemStack(Material.STAINED_GLASS_PANE, 1);
        ItemStack users = getUsersItemStack();
        for(int i = 0; i < 45; i++) {
            inv.setItem(i, blackGlass);
        }
        for(int i = 45; i < 54; i++) {
            inv.setItem(i, blueGlass);
        }
        inv.setItem(12, whiteGlass);
        inv.setItem(13, whiteGlass);
        inv.setItem(14, whiteGlass);
        inv.setItem(21, whiteGlass);
        inv.setItem(22, new ItemStack(Material.AIR));
        inv.setItem(23, whiteGlass);
        inv.setItem(30, whiteGlass);
        inv.setItem(31, whiteGlass);
        inv.setItem(32, whiteGlass);
        ItemStack menu = getMenuItemStack();
        inv.setItem(45, menu);
        return inv;
    }
    
    public static Inventory createNewFileInventory(Player playa, ItemStack is) {
        Inventory inv = Bukkit.createInventory(playa, 54, "Computer - File Receive");
        ItemStack blackGlass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)15);
        ItemStack blueGlass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 11);
        ItemStack whiteGlass = new ItemStack(Material.STAINED_GLASS_PANE, 1);
        ItemStack users = getUsersItemStack();
        for(int i = 0; i < 45; i++) {
            inv.setItem(i, blackGlass);
        }
        for(int i = 45; i < 54; i++) {
            inv.setItem(i, blueGlass);
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
        ItemStack menu = getMenuItemStack();
        inv.setItem(45, menu);
        return inv;
    }
    
    public static Inventory createUsersInventory(Player playa) {
        Inventory inv = Bukkit.createInventory(playa, 54, "Computer - Users");
        ItemStack blackGlass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)15);
        ItemStack blueGlass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 11);
        ItemStack users = getUsersItemStack();
        for(int i = 0; i < 45; i++) {
            inv.setItem(i, blackGlass);
        }
        for(int i = 45; i < 54; i++) {
            inv.setItem(i, blueGlass);
        }
        for(int i = 0; i < Bukkit.getOnlinePlayers().length; i++) {
            ItemMeta usersMeta = users.getItemMeta();
            usersMeta.setDisplayName(Bukkit.getOnlinePlayers()[i].getName());
            String ip = "IP: " + ChatColor.GREEN + Bukkit.getOnlinePlayers()[i].getUniqueId().toString();
            ArrayList<String> lore = new ArrayList<>();
            lore.add(ip);
            usersMeta.setLore(lore);
            users.setItemMeta(usersMeta);
            inv.setItem(i, users);
        }
        ItemStack menu = getMenuItemStack();
        inv.setItem(45, menu);
        return inv;
    }
    
    public static ItemStack getMenuItemStack() {
        ItemStack menu = new ItemStack(Material.ENDER_PEARL, 1);
        ItemMeta menuMeta = menu.getItemMeta();
        menuMeta.setDisplayName("Menu");
        menu.setItemMeta(menuMeta);
        return menu;
    }
    
    public static ItemStack getUsersItemStack() {
        ItemStack users = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        ItemMeta usersMeta = users.getItemMeta();
        usersMeta.setDisplayName("Users");
        users.setItemMeta(usersMeta);
        return users;
    }
    
    public static ItemStack getComposeItemStack() {
        ItemStack compose = new ItemStack(Material.BOOK_AND_QUILL, 1, (short) 3);
        ItemMeta composeMeta = compose.getItemMeta();
        composeMeta.setDisplayName("Compose");
        compose.setItemMeta(composeMeta);
        return compose;
    }
}

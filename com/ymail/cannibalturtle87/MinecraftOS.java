package com.ymail.cannibalturtle87;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MinecraftOS extends OperatingSystem {
    
    public MinecraftOS() {
        super("MinecraftOS");
    }

    @Override
    public ItemStack getMenuIcon() {
        ItemStack menu = new ItemStack(Material.NETHER_STAR, 1);
        ItemMeta menuMeta = menu.getItemMeta();
        menuMeta.setDisplayName("Menu");
        menu.setItemMeta(menuMeta);
        return menu;
    }

    @Override
    public ItemStack getUsersIcon() {
        ItemStack users = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        ItemMeta usersMeta = users.getItemMeta();
        usersMeta.setDisplayName("Users");
        users.setItemMeta(usersMeta);
        return users;
    }

    @Override
    public ItemStack getLogOutIcon() {
        ItemStack users = new ItemStack(Material.IRON_SWORD, 1);
        ItemMeta usersMeta = users.getItemMeta();
        usersMeta.setDisplayName("Log Out");
        users.setItemMeta(usersMeta);
        return users;
    }

    @Override
    public ItemStack getComposeIcon() {
        ItemStack compose = new ItemStack(Material.BOOK_AND_QUILL, 1, (short) 3);
        ItemMeta composeMeta = compose.getItemMeta();
        composeMeta.setDisplayName("Compose");
        compose.setItemMeta(composeMeta);
        return compose;
    }

    @Override
    public ItemStack getBackground() {
        ItemStack bg = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 9);
        return bg;
    }

    @Override
    public ItemStack getTaskbar() {
        ItemStack taskbar = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
        return taskbar;
    }
    
}

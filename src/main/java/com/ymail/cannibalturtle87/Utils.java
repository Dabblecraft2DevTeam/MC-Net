package com.ymail.cannibalturtle87;

import java.util.ArrayList;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Utils {
	public Utils() {}
	
	public static ItemStack setName(ItemStack is, String name){
        ItemMeta m = is.getItemMeta();
        m.setDisplayName(name);
        is.setItemMeta(m);
        return is;
    }
    
    public static ItemStack setLore(ItemStack is, String lore){
        ItemMeta m = is.getItemMeta();
        ArrayList<String> _lore = new ArrayList<String>();
        _lore.add(lore);
        m.setLore(_lore);
        is.setItemMeta(m);
        return is;
    }
}
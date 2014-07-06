package com.ymail.cannibalturtle87;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    
    public static final Logger logger = Logger.getLogger("minecraft");
    private final PluginDescriptionFile pd = this.getDescription();
    
    @Override
    public void onEnable() {
        logger.log(Level.INFO, "{0} version {1} has been enabled!", new Object[]{pd.getName(), pd.getVersion()});
        PluginManager pm = this.getServer().getPluginManager();
        final NetListeners nl = new NetListeners();
        pm.registerEvents(nl, this);
        Bukkit.addRecipe(new ShapedRecipe(OperatingSystem.getOSDisc("CraftOS")).shape("gdg","rer","ccc").setIngredient('g', Material.GOLD_NUGGET).setIngredient('d', Material.DIAMOND)
        .setIngredient('r', Material.REDSTONE).setIngredient('e', Material.EYE_OF_ENDER).setIngredient('c', Material.COAL));
        Bukkit.addRecipe(new ShapedRecipe(OperatingSystem.getOSDisc("MinecraftOS")).shape("geg","ror","ccc").setIngredient('g', Material.GOLD_NUGGET).setIngredient('e', Material.EMERALD)
        .setIngredient('r', Material.REDSTONE).setIngredient('o', Material.EYE_OF_ENDER).setIngredient('c', Material.COAL));
        Bukkit.addRecipe(new ShapedRecipe(OperatingSystem.getLoginDisc()).shape("gdg","rpr","ccc").setIngredient('g', Material.GOLD_NUGGET).setIngredient('d', Material.DIAMOND)
        .setIngredient('r', Material.REDSTONE).setIngredient('p', Material.PAPER).setIngredient('c', Material.COAL));
    }
    
    @Override
    public void onDisable() {
        logger.log(Level.INFO, "{0} version {1} has been disabled!", new Object[]{pd.getName(), pd.getVersion()});
    }
}

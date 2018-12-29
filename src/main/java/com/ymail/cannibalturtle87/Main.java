package com.ymail.cannibalturtle87;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public static final Logger logger = Logger.getLogger("minecraft");
    private final PluginDescriptionFile pd = this.getDescription();
    public static boolean blingjeweledpresent = false;
    
    @Override
    public void onEnable() {
    	PluginManager pm = this.getServer().getPluginManager();
        final NetListeners nl = new NetListeners();
        pm.registerEvents(nl, this);
        Bukkit.addRecipe(new ShapedRecipe(OperatingSystem.getOSDisc("Com/OS")).shape("gdg","rer","ccc").setIngredient('g', Material.GOLD_NUGGET).setIngredient('d', Material.DIAMOND)
        .setIngredient('r', Material.REDSTONE).setIngredient('e', Material.EYE_OF_ENDER).setIngredient('c', Material.COAL));
        Bukkit.addRecipe(new ShapedRecipe(OperatingSystem.getOSDisc("CraftOS")).shape("geg","ror","ccc").setIngredient('g', Material.GOLD_NUGGET).setIngredient('e', Material.EMERALD)
        .setIngredient('r', Material.REDSTONE).setIngredient('o', Material.EYE_OF_ENDER).setIngredient('c', Material.COAL));
        Bukkit.addRecipe(new ShapedRecipe(OperatingSystem.getLoginDisc()).shape("gdg","rpr","ccc").setIngredient('g', Material.GOLD_NUGGET).setIngredient('d', Material.DIAMOND)
        .setIngredient('r', Material.REDSTONE).setIngredient('p', Material.PAPER).setIngredient('c', Material.COAL));
    	try {
    	    Class<?> clazz = Class.forName("com.sethbling.blingjeweled.Glow"); // does my modified version exist?
    	    blingjeweledpresent = true;
    	} catch (ClassNotFoundException e) {
    		logger.log(Level.INFO, "BlingJeweled not available, some features will not be enabled!");
    	}
        logger.log(Level.INFO, "{0} version {1} has been enabled!", new Object[]{pd.getName(), pd.getVersion()});
    }
    
    @Override
    public void onDisable() {
        logger.log(Level.INFO, "{0} version {1} has been disabled!", new Object[]{pd.getName(), pd.getVersion()});
    }
    
    @EventHandler
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    	if (cmd.getName().equalsIgnoreCase("getosdisc")) {
    		if (!(sender instanceof Player)) {
    			sender.sendMessage(ChatColor.RED + "This command can only be run by a player.");
    		} else {
    			Player player = (Player) sender;
    			if (player.hasPermission("mcnet.command.getosdisc")) {
    				if (OperatingSystem.getOSByName(args[0]) != null) {
    					player.getInventory().addItem(OperatingSystem.getOSDisc(args[0]));
    					return true;
    				} else {
    					sender.sendMessage(ChatColor.RED + "That OS does not exist");
    				}
    			} else {
    				sender.sendMessage(ChatColor.RED + "You don't have the permission mcnet.command.getosdisc");
    			}
    		}
    	} else if (cmd.getName().equalsIgnoreCase("getlogindisc")) {
    		if (!(sender instanceof Player)) {
    			sender.sendMessage(ChatColor.RED + "This command can only be run by a player.");
    		} else {
    			Player player = (Player) sender;
    			if (player.hasPermission("mcnet.command.getlogindisc")) {
    				player.getInventory().addItem(OperatingSystem.getLoginDisc());
    				return true;
    			} else {
    				sender.sendMessage(ChatColor.RED + "You don't have the permission mcnet.command.getlogindisc");
    			}
    		}
    	}
    	return false;
    }
}

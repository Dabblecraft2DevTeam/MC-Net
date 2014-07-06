package com.ymail.cannibalturtle87;

import java.util.logging.Level;
import java.util.logging.Logger;
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
    }
    
    @Override
    public void onDisable() {
        logger.log(Level.INFO, "{0} version {1} has been disabled!", new Object[]{pd.getName(), pd.getVersion()});
    }
}

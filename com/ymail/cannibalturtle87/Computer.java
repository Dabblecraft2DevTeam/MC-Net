package com.ymail.cannibalturtle87;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.Callable;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.metadata.LazyMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

public class Computer {
    
    private Sign s;
    private OfflinePlayer user, recipient;
    public static int COMPUTER = 0x000000, SERVER = 0x000001;
    private static HashMap<Player, Computer> loggedInPlayers = new HashMap<>();
    private static HashMap<Player, Computer> allComputers = new HashMap<>();
    
    public static HashMap<Player, Computer> getLoggedInPlayers() {
        return loggedInPlayers;
    }
    
    public static void logIn(Player p, Computer c) {
        if(!loggedInPlayers.containsKey(p)) {
            loggedInPlayers.put(p, c);
        }
    }
    
    public static void logOut(Player p) {
        if(loggedInPlayers.containsKey(p)) {
            loggedInPlayers.remove(p);
        }
    }
    
    public OfflinePlayer getRecipient() {
        return recipient;
    }
    
    public void setRecipient(OfflinePlayer p) {
        this.recipient = p;
    }
    
    public void setSign(Sign s) {
        this.s = s;
    }
    
    public void setUser(UUID user) {
        if(getSign().hasMetadata("user")) {
            getSign().removeMetadata("user", Bukkit.getPluginManager().getPlugin("MC-Net"));
        }
        this.user = Bukkit.getOfflinePlayer(user);
        LazyMetadataValue mv = new LazyMetadataValue(Bukkit.getPluginManager().getPlugin("MC-Net"), new Callable() {

            @Override
            public Object call() throws Exception {
                return user.toString();
            }
            
        });
        getSign().setMetadata("user", mv);
    }
    
    public OfflinePlayer getUser() {
        return user;
    }
    
    public Sign getSign() {
        return s;
    }
    
    public static HashMap<Player, Computer> getAllRegisteredComputers() {
        return allComputers;
    }
    
    public static void addComputer(Player s, Computer c) {
        if(!allComputers.containsKey(s)) {
            allComputers.put(s, c);
        }
    }
    
    public static void removeComputer(Player s) {
        if(allComputers.containsKey(s)) {
            allComputers.remove(s);
        }
    }
    
    public static Computer getComputer(Player s) {
        if(allComputers.containsKey(s)) {
            return allComputers.get(s);
        }
        return null;
    }
    
    public static int getType(Sign s) {
        if(ChatColor.stripColor(s.getLine(0)).equalsIgnoreCase("[Computer]")) {
            return COMPUTER;
        }
        
        if(ChatColor.stripColor(s.getLine(0)).equalsIgnoreCase("[Server]")) {
            return SERVER;
        }
        return -1;
    }
    
    public static OfflinePlayer getUser(Sign s) {
        if(s.hasMetadata("user")) {
            for(MetadataValue mv : s.getMetadata("user")) {
                if(mv.getOwningPlugin() == Bukkit.getPluginManager().getPlugin("MC-Net")) {
                    return Bukkit.getOfflinePlayer(UUID.fromString(mv.value().toString()));
                }
            }
        }
        return null;
    }
}

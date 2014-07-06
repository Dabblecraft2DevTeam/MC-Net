package com.ymail.cannibalturtle87;

import java.util.HashMap;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

public class Computer {
    
    private Sign s;
    private OfflinePlayer recipient;
    private OperatingSystem os = null;
    public static int COMPUTER = 0x000000, SERVER = 0x000001;
    private static final HashMap<Player, Computer> loggedInPlayers = new HashMap<>();
    
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
    
    public static boolean isLoggedIn(Player p) {
        return loggedInPlayers.containsKey(p);
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
    
    public static OperatingSystem getOS(Sign s) {
        return OperatingSystem.getOSByName(ChatColor.stripColor(s.getLine(2)));
    }
    
    public OperatingSystem getOS() {
        return OperatingSystem.getOSByName(ChatColor.stripColor(s.getLine(2)));
    }
    
    public void setOperatingSystem(OperatingSystem os) {
        getSign().setLine(2, ChatColor.GREEN + os.getName());
        getSign().update();
    }
    
    public Sign getSign() {
        return s;
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
}

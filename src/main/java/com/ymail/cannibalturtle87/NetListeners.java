package com.ymail.cannibalturtle87;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.command.*;

public class NetListeners implements Listener {
    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if(e.getClickedBlock().getType() == Material.SIGN || e.getClickedBlock().getType() == Material.SIGN_POST || e.getClickedBlock().getType() == Material.WALL_SIGN) {
                Sign sign = (Sign) e.getClickedBlock().getState();
                if (Computer.getType(sign) != -1) {
                    if (e.hasItem()) {
                        if (e.getItem().getType() == OperatingSystem.getLoginDisc().getType() || e.getItem().getType() == Material.GREEN_RECORD) {
                            ItemStack is = e.getItem();
                            if (is.hasItemMeta()) {
                                ItemMeta im = is.getItemMeta();
                                if (im.getDisplayName().equals("\u00A7bLog-in Disc")) {
                                    if (e.getClickedBlock().getType() == Material.SIGN || e.getClickedBlock().getType() == Material.SIGN_POST || e.getClickedBlock().getType() == Material.WALL_SIGN) {
                                        if (Computer.getOS(sign) != null && sign.getLine(2).contains(ChatColor.GREEN.toString())) {
                                        	if (Computer.isLoggedIn(player))
	                                            Computer.logOut(player);
                                        	Computer c = new Computer();
                                            c.setSign(sign);
                                            c.setOperatingSystem(OperatingSystem.getOSByName(ChatColor.stripColor(sign.getLine(2))));
                                            player.openInventory(c.getOS().homeScreen(player));
                                            Computer.logIn(player, c);
                                        } else {
                                            player.sendMessage(ChatColor.RED + "Unable to log-in. Reason: no operating system.");
                                        }
                                    }
                                }
                                if(im.getDisplayName().equals("\u00A7bOperating System")) {
                                    String name = im.getLore().get(0);
                                    if(e.getClickedBlock().getType() == Material.SIGN || e.getClickedBlock().getType() == Material.SIGN_POST || e.getClickedBlock().getType() == Material.WALL_SIGN) {
                                        Computer c = new Computer();
                                        c.setSign(sign);
                                        c.setOperatingSystem(OperatingSystem.getOSByName(ChatColor.stripColor(name)));
                                        player.sendMessage(ChatColor.GREEN + ChatColor.stripColor(name) + " successfully installed!");
                                        player.getInventory().removeItem(is);
                                        player.updateInventory();
                                    }
                                }
                            }
                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "You need a log-in disc to do that!");
                    }
                }
            }
        }
    }
    
    @EventHandler
    public void OnPlayerInteract(PlayerInteractEvent e){
        Player player = e.getPlayer();
        if (e.getClickedBlock() != null && (e.getClickedBlock().getType() == Material.SIGN || e.getClickedBlock().getType() == Material.SIGN_POST || e.getClickedBlock().getType() == Material.WALL_SIGN) && e.getAction() == Action.LEFT_CLICK_BLOCK) {
            System.out.println("Yee");
            Sign sign = (Sign) e.getClickedBlock().getState();
            if (Computer.getType(sign) != -1) {
                if (Computer.getOS(sign) != null && sign.getLine(2).contains(ChatColor.GREEN.toString())) {
                    player.getInventory().addItem(OperatingSystem.getOSDisc(ChatColor.stripColor(sign.getLine(2))));
                    player.sendMessage(ChatColor.GREEN + ChatColor.stripColor(sign.getLine(2)) + " successfully uninstalled!");
                    sign.setLine(2, "");
                    sign.update();
                }
            }
        }
    }
    
    @EventHandler
    public void onInventoryInteract(InventoryClickEvent e) {
        try {
            if(e.getView().getTopInventory().getTitle().contains("Computer")) {
                Player player = (Player) e.getWhoClicked();
                Computer c = Computer.getLoggedInPlayers().get(player);
                if(e.getRawSlot() <= 53) {
                    if (!c.getOS().onInventoryClick(e)) {
                        if(e.getCurrentItem().getType() == c.getOS().getMenuIcon().getType()) {
                            player.openInventory(c.getOS().homeScreen(player));
                        } else if(e.getCurrentItem().getType() == c.getOS().getLogOutIcon().getType()) {
                            Computer.logOut(player);
                            player.closeInventory();
                            player.sendMessage(ChatColor.GREEN + "Logged out successfully!");
                        } else if(e.getInventory().getTitle().equalsIgnoreCase("Computer")) {
                            if(e.getCurrentItem().getType() == c.getOS().getUsersIcon().getType()) {
                                player.openInventory(c.getOS().usersScreen(player));
                            }
                            if(e.getCurrentItem().getType() == c.getOS().getComposeIcon().getType()) {
                                player.openInventory(c.getOS().contactsScreen(player));
                            }
                        } else if(e.getInventory().getTitle().equalsIgnoreCase("Computer - Contacts")) {
                            if(e.getCurrentItem().getType() == c.getOS().getUsersIcon().getType()) {
                                Player p = Bukkit.getServer().getPlayer(ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()));
                                c.setRecipient(Bukkit.getServer().getOfflinePlayer(p.getUniqueId()));
                                player.openInventory(c.getOS().composeScreen(player));
                            }
                        } else if(e.getInventory().getTitle().equalsIgnoreCase("Computer - File Upload")) {
                            if(e.getCursor().getType() == Material.WRITTEN_BOOK || e.getCursor().getType() == Material.BOOK_AND_QUILL) {
                                if(c.getRecipient().isOnline()) {
                                    c.getRecipient().getPlayer().openInventory(c.getOS().inboxScreen(c.getRecipient().getPlayer(), e.getCursor()));
                                    player.openInventory(c.getOS().homeScreen(player));
                                }else {
                                    player.sendMessage(ChatColor.RED + c.getRecipient().getName() + " is no longer online!");
                                }
                            }
                        } else if(e.getInventory().getTitle().equalsIgnoreCase("Computer - File Receive")) {
                            if(e.getCurrentItem().getType() == Material.WRITTEN_BOOK || e.getCurrentItem().getType() == Material.BOOK_AND_QUILL) {
                                e.setCancelled(false);
                                return;
                            }
                        }
                    }
                    e.setCancelled(true);
                }
            }
        }catch(NullPointerException npe){}
    }
    
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        if(Computer.getLoggedInPlayers().containsKey(e.getPlayer())) {
            Player p = e.getPlayer();
            Computer.logOut(p);
        }
    }
    
    @EventHandler
    public void onSignCreate(SignChangeEvent e) {
        Player player = e.getPlayer();
        if(e.getLine(0).equalsIgnoreCase("[Computer]")) {
            e.setLine(0, "[Computer]");
            e.setLine(1, "");
            e.setLine(2, "");
            e.setLine(3, "");
            player.sendMessage(ChatColor.GREEN + "Successfully built a computer!");
        }
        if(e.getLine(0).equalsIgnoreCase("[Server]")) {
            e.setLine(0, "[Server]");
            e.setLine(1, "");
            e.setLine(2, "");
            e.setLine(3, "");
            player.sendMessage("This is a server!");
        }
    }
}

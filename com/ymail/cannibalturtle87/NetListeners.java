package com.ymail.cannibalturtle87;

import java.util.ArrayList;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class NetListeners implements Listener {
    
    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        Player playa = e.getPlayer();
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if(e.getClickedBlock().getType() == Material.SIGN || e.getClickedBlock().getType() == Material.SIGN_POST || e.getClickedBlock().getType() == Material.WALL_SIGN) {
                Sign sign = (Sign) e.getClickedBlock().getState();
                if(Computer.getType(sign) != -1) {
                    if(e.hasItem()) {
                        if(e.getItem().getType() == Material.GREEN_RECORD) {
                            ItemStack is = e.getItem();
                            if(is.hasItemMeta()) {
                                ItemMeta im = is.getItemMeta();
                                if(im.getDisplayName().equals("Log-in Disc")) {
                                    String userName = im.getLore().get(0);
                                    String password = im.getLore().get(1);
                                    if(userName.equals(playa.getName()) && password.equals(playa.getUniqueId().toString())) {
                                        if(e.getClickedBlock().getType() == Material.SIGN || e.getClickedBlock().getType() == Material.SIGN_POST || e.getClickedBlock().getType() == Material.WALL_SIGN) {
                                                Computer c = new Computer();
                                                c.setSign(sign);
                                                c.setUser(playa.getUniqueId());
                                                Computer.removeComputer(playa);
                                                Computer.addComputer(playa, c);
                                                playa.openInventory(ComputerInventory.createComputerInventory(playa));
                                            }
                                        }
                                    }
                                }
                            }
                        }else {
                            playa.sendMessage(ChatColor.RED + "You need a log-in disc to do that!");
                        }
                }
            }
        }
    }
    
    @EventHandler
    public void onInventoryInteract(InventoryClickEvent e) {
        try {
            if(e.getView().getTopInventory().getTitle().contains("Computer")) {
                Player playa = (Player) e.getWhoClicked();
                Computer c = Computer.getLoggedInPlayers().get(playa);
                if(e.getRawSlot() <= 53) {
                    if(e.getCurrentItem().getType() == ComputerInventory.getMenuItemStack().getType()) {
                        playa.openInventory(ComputerInventory.createComputerInventory(playa));
                    }
                    if(e.getInventory().getTitle().equalsIgnoreCase("Computer")) {
                        if(e.getCurrentItem().getType() == ComputerInventory.getUsersItemStack().getType()) {
                            playa.openInventory(ComputerInventory.createUsersInventory(playa));
                        }
                        if(e.getCurrentItem().getType() == ComputerInventory.getComposeItemStack().getType()) {
                            playa.openInventory(ComputerInventory.createContactsInventory(playa));
                        }
                    }
                    if(e.getInventory().getTitle().equalsIgnoreCase("Computer - Contacts")) {
                        if(e.getCurrentItem().getType() == ComputerInventory.getUsersItemStack().getType()) {
                            Player p = Bukkit.getPlayer(e.getCurrentItem().getItemMeta().getDisplayName());
                            c.setRecipient(p);
                            playa.openInventory(ComputerInventory.createComposeInventory(playa));
                        }
                    }
                    if(e.getInventory().getTitle().equalsIgnoreCase("Computer - File Upload")) {
                        if(e.getCursor().getType() == Material.WRITTEN_BOOK || e.getCursor().getType() == Material.BOOK_AND_QUILL) {
                            if(c.getRecipient().isOnline()) {
                                c.getRecipient().getPlayer().openInventory(ComputerInventory.createNewFileInventory(c.getRecipient().getPlayer(), e.getCursor()));
                                //playa.openInventory(ComputerInventory.createComputerInventory(playa));
                                e.setCancelled(false);
                            }
                        }
                    }
                    if(e.getInventory().getTitle().equalsIgnoreCase("Computer - File Receive")) {
                        if(e.getCurrentItem().getType() == Material.WRITTEN_BOOK || e.getCurrentItem().getType() == Material.BOOK_AND_QUILL) {
                            e.setCancelled(false);
                            return;
                        }
                    }
                    e.setCancelled(true);
                }
            }
        }catch(NullPointerException npe){}
    }
    
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        try {
            if(e.getView().getTopInventory().getTitle().contains("Computer")) {
                Player playa = (Player) e.getPlayer();
                Computer.logOut(playa);
            }
        }catch(NullPointerException npe){}
    }
    
    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent e) {
        try {
            if(e.getView().getTopInventory().getTitle().contains("Computer")) {
                Player playa = (Player) e.getPlayer();
                Computer.logIn(playa, Computer.getComputer(playa));
            }
        }catch(NullPointerException npe){}
    }
    
    @EventHandler
    public void onSignCreate(SignChangeEvent e) {
        Player playa = e.getPlayer();
        if(e.getLine(0).equalsIgnoreCase("[Computer]")) {
            playa.sendMessage("This is a computer!");
            Computer c = new Computer();
            c.setSign((Sign) e.getBlock().getState());
            c.setUser(playa.getUniqueId());
            ItemStack loginDisc = new ItemStack(Material.GREEN_RECORD, 1);
            ItemMeta loginMeta = loginDisc.getItemMeta();
            loginMeta.setDisplayName("Log-in Disc");
            ArrayList<String> lore = new ArrayList<>();
            lore.add(playa.getName());
            lore.add(playa.getUniqueId().toString());
            loginMeta.setLore(lore);
            loginDisc.setItemMeta(loginMeta);
            playa.getWorld().dropItem(playa.getLocation(), loginDisc);
        }
        if(e.getLine(0).equalsIgnoreCase("[Server]")) {
            playa.sendMessage("This is a server!");
        }
    }
}

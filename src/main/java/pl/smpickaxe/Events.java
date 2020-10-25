package pl.smpickaxe;

import net.coreprotect.CoreProtectAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Objects;

import static sun.security.krb5.Confounder.intValue;

public class Events implements Listener {
    public static HashMap<String, Integer> blockFace = new HashMap<>();

    private void getNearbyBlock(Location loc,Player p,int uno,int dos, int tres) {
        loc.add(uno, dos, tres);
        Material mat = loc.getBlock().getType();
        //p.sendMessage("Sprawdzam blok obok Ciebie:"+mat.toString()+"Lokalizacja:"+loc.toString());
        if (loc.getBlock().getType().getHardness() < 50 && loc.getBlock().getType().getHardness() >0) {
            //p.sendMessage("Hardness"+String.valueOf(loc.getBlock().getType().getHardness()));
            //p.sendMessage("Nie jest twardy");
            if(mat.equals(Material.CHEST) || mat.equals(Material.ENDER_CHEST) || mat.equals(Material.BEACON)){
                p.sendMessage("Wokół Ciebie są nielegalne bloki!");
            }
            else {
                //p.sendMessage("Nie jest z czarnej lsity");
                loc.getBlock().breakNaturally();
            }
        }
    }

    @EventHandler
    public void BreakDiamondEvent(BlockBreakEvent e) {
        if (e.getPlayer().getInventory().getItemInMainHand().getType() == Material.DIAMOND_PICKAXE ||
                e.getPlayer().getInventory().getItemInMainHand().getType() == Material.IRON_PICKAXE ||
                e.getPlayer().getInventory().getItemInMainHand().getType() == Material.NETHERITE_PICKAXE) {
            if (!e.getPlayer().getInventory().getItemInMainHand().getEnchantments().containsKey(Enchantment.SILK_TOUCH)) {
                if (e.getBlock().getType() == Material.DIAMOND_ORE) {
                    int rand = (int) (Math.random() * (230 - 1 + 1) + 1);
                    if (rand == 151) {
                        Bukkit.broadcastMessage(ChatColor.BLUE +" "+ ChatColor.BOLD+"Gracz "+e.getPlayer().getName()+" wykopał rzadki rubin! Gratulacje!");
                        e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), CustomDiamond.createCustomDiamond());
                    }
                }
            }


        }
    }
    @EventHandler
     public void getBlockFace(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemInHand = player.getItemInHand();
        BlockFace bFace = event.getBlockFace();
        if (itemInHand.getType().equals(Material.DIAMOND_PICKAXE)) {
        if (bFace == BlockFace.UP || bFace == BlockFace.DOWN)
            blockFace.put(player.getName(), 1);
        if (bFace == BlockFace.NORTH || bFace == BlockFace.SOUTH)
            blockFace.put(player.getName(), 2);
        if (bFace == BlockFace.WEST || bFace == BlockFace.EAST)
            blockFace.put(player.getName(), 3);
        }
    }

    @EventHandler
    public void BreakUsing21Pickaxe(BlockBreakEvent e){
        Player p = e.getPlayer();
        if(e.isCancelled()) return;
        if(Objects.requireNonNull(p.getInventory().getItemInMainHand().getItemMeta()).getDisplayName().equals(ChatColor.BLUE+"Kilof Poziomu 1")) {
            Location loc = e.getBlock().getLocation();
            if((Integer) blockFace.get(p.getName()) == 1){
                getNearbyBlock(loc,p,-1,0,0);
            }
            else{
                getNearbyBlock(loc,p,0,-1,0);
            }

        }
        else if(Objects.requireNonNull(p.getInventory().getItemInMainHand().getItemMeta()).getDisplayName().equals(ChatColor.BLUE + "Kilof Poziomu 2")) {
            e.getPlayer().sendMessage("Poziom 2");
            Location loc = e.getBlock().getLocation();
            if((Integer) blockFace.get(p.getName()) == 1) {
                getNearbyBlock(loc,p,1,0,0);
                getNearbyBlock(loc,p,0,0,-1);
                getNearbyBlock(loc,p,-1,0,0);
            }
            else if(p.getFacing() == BlockFace.EAST || p.getFacing() == BlockFace.WEST){
                getNearbyBlock(loc,p,0,0,1);
                getNearbyBlock(loc,p,0,-1,0);
                getNearbyBlock(loc,p,0,0,-1);
            }
            else{
                getNearbyBlock(loc,p,0,-1,0);
                getNearbyBlock(loc,p,1,0,0);
                getNearbyBlock(loc,p,0,1,0);
            }



        }
        else if(Objects.requireNonNull(p.getInventory().getItemInMainHand().getItemMeta()).getDisplayName().equals(ChatColor.BLUE + "Kilof Poziomu 3")) {
            e.getPlayer().sendMessage("Poziom 3");
            Location loc = e.getBlock().getLocation();
            if((Integer) blockFace.get(p.getName()) == 1){
                getNearbyBlock(loc,p,0,0,1);
                getNearbyBlock(loc,p,1,0,0);
                getNearbyBlock(loc,p,0,0,-1);
                getNearbyBlock(loc,p,0,0,-1);
                getNearbyBlock(loc,p,-1,0,0);
            }
            else if(e.getPlayer().getFacing() == BlockFace.EAST || e.getPlayer().getFacing() == BlockFace.WEST){
                getNearbyBlock(loc,p,0,-1,0);
                getNearbyBlock(loc,p,0,0,1);
                getNearbyBlock(loc,p,0,0,-2);
                getNearbyBlock(loc,p,0,1,0);
                getNearbyBlock(loc,p,0,0,2);
            }
            else{
                getNearbyBlock(loc,p,0,-1,0);
                getNearbyBlock(loc,p,1,0,0);
                getNearbyBlock(loc,p,-2,0,0);
                getNearbyBlock(loc,p,0,1,0);
                getNearbyBlock(loc,p,2,0,0);
            }


        }
        else if(Objects.requireNonNull(p.getInventory().getItemInMainHand().getItemMeta()).getDisplayName().equals(ChatColor.BLUE + "Kilof Poziomu 4")) {
            e.getPlayer().sendMessage("Poziom 4");
            Location loc = e.getBlock().getLocation();
            if((Integer)blockFace.get(e.getPlayer().getName()).intValue() == 1){
                getNearbyBlock(loc,p,0,0,1);
                getNearbyBlock(loc,p,1,0,0);
                getNearbyBlock(loc,p,0,0,-1);
                getNearbyBlock(loc,p,0,0,-1);
                getNearbyBlock(loc,p,-1,0,0);
                getNearbyBlock(loc,p,-1,0,0);
                getNearbyBlock(loc,p,0,0,1);
                getNearbyBlock(loc,p,0,0,1);
            }
            else if(e.getPlayer().getFacing() == BlockFace.EAST || e.getPlayer().getFacing() == BlockFace.WEST){
                getNearbyBlock(loc,p,0,1,0);
                getNearbyBlock(loc,p,0,-2,0);
                getNearbyBlock(loc,p,0,0,1);
                getNearbyBlock(loc,p,0,0,-2);
                getNearbyBlock(loc,p,0,1,0);
                getNearbyBlock(loc,p,0,0,2);
                getNearbyBlock(loc,p,0,1,0);
                getNearbyBlock(loc,p,0,0,-2);
            }
            else{
                getNearbyBlock(loc,p,0,1,0);
                getNearbyBlock(loc,p,0,-2,0);
                getNearbyBlock(loc,p,1,0,0);
                getNearbyBlock(loc,p,-2,0,0);
                getNearbyBlock(loc,p,0,1,0);
                getNearbyBlock(loc,p,2,0,0);
                getNearbyBlock(loc,p,0,1,0);
                getNearbyBlock(loc,p,-2,0,0);
            }
        }
    }




}

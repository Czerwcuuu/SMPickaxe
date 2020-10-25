package pl.smpickaxe;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class Events implements Listener {

    @EventHandler
    public void BreakDiamondEvent(BlockBreakEvent e) {
        if (e.getPlayer().getInventory().getItemInMainHand().getType() == Material.DIAMOND_PICKAXE ||
                e.getPlayer().getInventory().getItemInMainHand().getType() == Material.IRON_PICKAXE ||
                e.getPlayer().getInventory().getItemInMainHand().getType() == Material.NETHERITE_PICKAXE) {
            if (!e.getPlayer().getInventory().getItemInMainHand().getEnchantments().containsKey(Enchantment.SILK_TOUCH)) {
                if (e.getBlock().getType() == Material.DIAMOND_ORE) {
                    int rand = (int) (Math.random() * (300 - 1 + 1) + 1);
                    if (rand == 151) {
                        e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), CustomDiamond.createCustomDiamond());
                    }
                }
            }


        }
    }

    @EventHandler
    public void BreakUsing21Pickaxe(BlockBreakEvent e){
        if(!e.isCancelled() && e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.BLUE+"Kilof Poziomu 1")) {
            Location loc = e.getBlock().getLocation();
            loc.add(0, -1, 0);
            loc.getBlock().breakNaturally();

        }
    }
}

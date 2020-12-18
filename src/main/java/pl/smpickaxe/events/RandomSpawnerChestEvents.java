package pl.smpickaxe.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.smpickaxe.ores.Netherium;
import pl.smpickaxe.utils.RandomSpawnerChest;
import pl.smpickaxe.utils.SpawnersUtil;

import java.util.List;
import java.util.Objects;

public class RandomSpawnerChestEvents implements Listener {
    @EventHandler
    public void RandomSpawnerChestPlace(BlockPlaceEvent e){
        int amount = e.getPlayer().getInventory().getItemInMainHand().getAmount();
        if(e.getItemInHand().getType().equals(Material.CHEST)){
            ItemStack item = e.getItemInHand();
            if(item.getItemMeta().hasLore()){
                e.getPlayer().getInventory().getItemInMainHand().setAmount(e.getPlayer().getInventory().getItemInMainHand().getAmount()-1);
                SpawnersUtil.GetRandomSpawner(e.getPlayer());
                e.getBlock().setType(Material.AIR);
            }
        }

    }
    @EventHandler
    public void SpawnerDestroy(BlockBreakEvent e){
        if(!e.getBlock().getType().equals(Material.SPAWNER))return;
        if(!e.getPlayer().getItemInHand().getItemMeta().hasLore()) return;
        if(!e.getPlayer().getItemInHand().getType().equals(Material.DIAMOND_PICKAXE)) return;
        ItemMeta meta = e.getPlayer().getItemInHand().getItemMeta();
        List<String> lore = meta.getLore();

        if(lore.get(0).equals("Kilof potrafiący wykopać Spawner")){
            e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), RandomSpawnerChest.createRandomSpawnerChest());
        }



    }

    @EventHandler
    public void OnCrouchRightclick(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        Action action = e.getAction();

        if (!player.isSneaking()) return;
        if (!action.equals(Action.RIGHT_CLICK_AIR)) return;
        if (player.getInventory().getItemInMainHand().getType() != Material.CHEST) return;
        if (!player.getInventory().getItemInMainHand().getItemMeta().hasLore()) return;

        try {
            e.getPlayer().getInventory().getItemInMainHand().setAmount(e.getPlayer().getInventory().getItemInMainHand().getAmount()-1);
            SpawnersUtil.GetRandomSpawner(e.getPlayer());
        } catch (Exception ex) {
            return;
        } //you didn't drop anything

    }
}

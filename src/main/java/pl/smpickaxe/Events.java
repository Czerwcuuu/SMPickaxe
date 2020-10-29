package pl.smpickaxe;

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
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Objects;

public class Events implements Listener {
    public static HashMap<String, Integer> blockFace = new HashMap<>();
    ItemStack pickaxe1 = CreatePickaxe.newPickaxe("1x2",new ItemStack(Material.STICK), CustomDiamond.createCustomDiamond());
    ItemStack pickaxe2 = CreatePickaxe.newPickaxe("2x2",pickaxe1, pickaxe1);

    private void getNearbyBlock(Player p, int uno, int dos, int tres, BlockBreakEvent e) {
        Location a;
        if (EASTorWEST(p) || blockFace.get(p.getName()) == 1){
            a = e.getBlock().getLocation().add(uno, dos, tres);
        } else {
            a = e.getBlock().getLocation().add(tres, dos, uno);
        }
        Material mat = e.getBlock().getLocation().getBlock().getType();
        //p.sendMessage("Sprawdzam blok obok Ciebie:"+mat.toString()+"Lokalizacja:"+loc.toString());
        if (mat.getHardness() < 50 && mat.getHardness() > 0) {
            //p.sendMessage("Hardness"+String.valueOf(loc.getBlock().getType().getHardness()));
            //p.sendMessage("Nie jest twardy");
            if (mat.equals(Material.CHEST) || mat.equals(Material.ENDER_CHEST) || mat.equals(Material.BEACON)) {
                p.sendMessage("Wokół Ciebie są nielegalne bloki!");
            } else {
                //p.sendMessage("Nie jest z czarnej lsity");
                if (e instanceof MojaZajebistaKlasa) {
                    return;
                }
                MojaZajebistaKlasa fakeEvent = new MojaZajebistaKlasa(a.getBlock(), p);
                Bukkit.getPluginManager().callEvent(fakeEvent);

                if (!fakeEvent.isCancelled()) {
                    //Bukkit.broadcastMessage("nie anulowany");
                    a.getBlock().breakNaturally();
                }
                //Bukkit.broadcastMessage("anulowany");


            }
        }
    }

    @EventHandler
    public void BreakDiamondEvent(BlockBreakEvent e) {
        ItemStack hand = e.getPlayer().getInventory().getItemInMainHand();
        Block block = e.getBlock();
        if (!block.getDrops(hand).isEmpty()) {
            if (!hand.getEnchantments().containsKey(Enchantment.SILK_TOUCH)) {
                if (block.getType() == Material.DIAMOND_ORE) {
                    int rand = (int) (Math.random() * (230 - 1 + 1) + 1);
                    if (rand == 151) {
                        Bukkit.broadcastMessage(ChatColor.BLUE + " " + ChatColor.BOLD + "Gracz " + e.getPlayer().getName() + " wykopał rzadki rubin! Gratulacje!");
                        block.getWorld().dropItemNaturally(block.getLocation(), CustomDiamond.createCustomDiamond());
                    }
                }
            }


        }
    }

    @EventHandler
    public void getBlockFace(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        Material itemInHand = p.getInventory().getItemInMainHand().getType();
        BlockFace bFace = event.getBlockFace();
        if (itemInHand.equals(Material.DIAMOND_PICKAXE)) {
            if (bFace == BlockFace.UP || bFace == BlockFace.DOWN){
                blockFace.put(p.getName(), 1);
            } else {blockFace.put(p.getName(), 0);}

        }
    }

    @EventHandler
    public void BreakUsingPickaxe(BlockBreakEvent e) {
        if (e instanceof MojaZajebistaKlasa) {
            return;
        }
        Player p = e.getPlayer();

        if (e.isCancelled()) return;
        try {
            String itemname = Objects.requireNonNull(p.getInventory().getItemInMainHand().getItemMeta()).getDisplayName();
            int s = getpickaxe(itemname, p);
            // Kopanie w dol i gore


            if (blockFace.get(p.getName()) == 1) {
                if (s >= 1) {
                    getNearbyBlock(p, -1, 0, 0, e);
                    if (s >= 2) {
                        getNearbyBlock(p, 0, 0, -1, e);
                        getNearbyBlock(p, -1, 0, -1, e);
                        if (s >= 3) {
                            getNearbyBlock(p, 0, 0, 1, e);
                            getNearbyBlock(p, -1, 0, 1, e);
                            if (s == 4) {
                                getNearbyBlock(p, 1, 0, -1, e);
                                getNearbyBlock(p, 1, 0, 0, e);
                                getNearbyBlock(p, 1, 0, 1, e);

                            }
                        }
                    }
                }
            } else {
                //kopanie na boki
                if (s >= 1) {
                    getNearbyBlock(p, 0, -1, 0, e);
                    if (s >= 2) {
                        getNearbyBlock(p, 0, 0, 1, e);
                        getNearbyBlock(p, 0, -1, 1, e);
                        if (s >= 3) {
                            getNearbyBlock(p, 0, 1, 0, e);
                            getNearbyBlock(p, 0, 1, 1, e);
                            if (s == 4) {
                                getNearbyBlock(p, 0, 1, -1, e);
                                getNearbyBlock(p, 0, 0, -1, e);
                                getNearbyBlock(p, 0, -1, -1, e);

                            }
                        }
                    }
                }
            }

        } catch (NullPointerException ex) {
            return;
        }

/*
        try {
            Location loc = e.getBlock().getLocation();
            if (itemname.equals(ChatColor.BLUE + "Kilof Poziomu 1")) {

                if (blockFace.get(p.getName()) == 1) {
                    getNearbyBlock(loc, p, -1, 0, 0, e);
                } else {
                    getNearbyBlock(loc, p, 0, -1, 0, e);
                }

            } else if (itemname.equals(ChatColor.BLUE + "Kilof Poziomu 2")) {
                //e.getPlayer().sendMessage("Poziom 2");
                if (blockFace.get(p.getName()) == 1) {
                    getNearbyBlock(loc, p, 1, 0, 0, e);
                    getNearbyBlock(loc, p, 0, 0, -1, e);
                    getNearbyBlock(loc, p, -1, 0, 0, e);
                } else if (EASTorWEST(p)) {
                    getNearbyBlock(loc, p, 0, 0, 1, e);
                    getNearbyBlock(loc, p, 0, -1, 0, e);
                    getNearbyBlock(loc, p, 0, 0, -1, e);
                } else {
                    getNearbyBlock(loc, p, 0, -1, 0, e);
                    getNearbyBlock(loc, p, 1, 0, 0, e);
                    getNearbyBlock(loc, p, 0, 1, 0, e);
                }


            } else if (itemname.equals(ChatColor.BLUE + "Kilof Poziomu 3")) {
                //e.getPlayer().sendMessage("Poziom 3");
                if (blockFace.get(p.getName()) == 1) {
                    getNearbyBlock(loc, p, 0, 0, 1, e);
                    getNearbyBlock(loc, p, 1, 0, 0, e);
                    getNearbyBlock(loc, p, 0, 0, -1, e);
                    getNearbyBlock(loc, p, 0, 0, -1, e);
                    getNearbyBlock(loc, p, -1, 0, 0, e);
                } else if (EASTorWEST(p)) {
                    getNearbyBlock(loc, p, 0, -1, 0, e);
                    getNearbyBlock(loc, p, 0, 0, 1, e);
                    getNearbyBlock(loc, p, 0, 0, -2, e);
                    getNearbyBlock(loc, p, 0, 1, 0, e);
                    getNearbyBlock(loc, p, 0, 0, 2, e);
                } else {
                    getNearbyBlock(loc, p, 0, -1, 0, e);
                    getNearbyBlock(loc, p, 1, 0, 0, e);
                    getNearbyBlock(loc, p, -2, 0, 0, e);
                    getNearbyBlock(loc, p, 0, 1, 0, e);
                    getNearbyBlock(loc, p, 2, 0, 0, e);
                }


            } else if (itemname.equals(ChatColor.BLUE + "Kilof Poziomu 4")) {
                //e.getPlayer().sendMessage("Poziom 4");
                if (blockFace.get(p.getName()) == 1) {
                    getNearbyBlock(loc, p, 0, 0, 1, e);
                    getNearbyBlock(loc, p, 1, 0, 0, e);
                    getNearbyBlock(loc, p, 0, 0, -1, e);
                    getNearbyBlock(loc, p, 0, 0, -1, e);
                    getNearbyBlock(loc, p, -1, 0, 0, e);
                    getNearbyBlock(loc, p, -1, 0, 0, e);
                    getNearbyBlock(loc, p, 0, 0, 1, e);
                    getNearbyBlock(loc, p, 0, 0, 1, e);
                } else if (EASTorWEST(p)) {
                    getNearbyBlock(loc, p, 0, 1, 0, e);
                    getNearbyBlock(loc, p, 0, -2, 0, e);
                    getNearbyBlock(loc, p, 0, 0, 1, e);
                    getNearbyBlock(loc, p, 0, 0, -2, e);
                    getNearbyBlock(loc, p, 0, 1, 0, e);
                    getNearbyBlock(loc, p, 0, 0, 2, e);
                    getNearbyBlock(loc, p, 0, 1, 0, e);
                    getNearbyBlock(loc, p, 0, 0, -2, e);
                } else {
                    getNearbyBlock(loc, p, 0, 1, 0, e);
                    getNearbyBlock(loc, p, 0, -2, 0, e);
                    getNearbyBlock(loc, p, 1, 0, 0, e);
                    getNearbyBlock(loc, p, -2, 0, 0, e);
                    getNearbyBlock(loc, p, 0, 1, 0, e);
                    getNearbyBlock(loc, p, 2, 0, 0, e);
                    getNearbyBlock(loc, p, 0, 1, 0, e);
                    getNearbyBlock(loc, p, -2, 0, 0, e);
                }
            }
        } catch (NullPointerException ex) {
            return;
        }*/
    }

    public int getpickaxe(String name, Player p) {
        String itemname = p.getInventory().getItemInMainHand().getItemMeta().getDisplayName();
        if (itemname.equals(pickaxe1.getItemMeta().getDisplayName())) {
            return 1;
        } else if (itemname.equals(pickaxe2.getItemMeta().getDisplayName())) {
            return 2;
        } else if (name.equals(ChatColor.BLUE + "Kilof Poziomu 3")) {
            return 3;
        } else if (name.equals(ChatColor.BLUE + "Kilof Poziomu 4")) {
            return 4;
        }
        return 0;
    }

    public boolean EASTorWEST(Player p) {
        return p.getFacing() == BlockFace.EAST || p.getFacing() == BlockFace.WEST;
    }


}

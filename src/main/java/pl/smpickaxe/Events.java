package pl.smpickaxe;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.smpickaxe.ores.Bedrock;
import pl.smpickaxe.ores.CustomDiamond;
import pl.smpickaxe.ores.Meteorite;
import pl.smpickaxe.ores.Platinium;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Events implements Listener {
    public static HashMap<String, Integer> blockFace = new HashMap<>();
    ItemStack pickaxe1 = CreatePickaxe.newPickaxe("Rubinowy","1x2", CustomDiamond.createCustomDiamond(), CustomDiamond.createCustomDiamond(), new ItemStack(Material.AIR), new ItemStack(Material.STICK), new ItemStack(Material.DIAMOND_PICKAXE));
    ItemStack pickaxe2 = CreatePickaxe.newPickaxe("Meteorytowy","2x2", Meteorite.createMeteorite(), Meteorite.createMeteorite(), new ItemStack(Material.AIR), new ItemStack(Material.STICK), new ItemStack(Material.DIAMOND_PICKAXE));
    ItemStack pickaxe3 = CreatePickaxe.newPickaxe("Platynowy","2x3", Platinium.createPlatinium(), Platinium.createPlatinium(), new ItemStack(Material.AIR), new ItemStack(Material.STICK), new ItemStack(Material.DIAMOND_PICKAXE));
    ItemStack pickaxe4 = CreatePickaxe.newPickaxe("Bedrockowy","3x3", Bedrock.createBedrock(), Bedrock.createBedrock(), new ItemStack(Material.AIR), new ItemStack(Material.STICK), new ItemStack(Material.DIAMOND_PICKAXE));
    ItemStack axe = CreatePickaxe.newPickaxe("Timber","1", CustomDiamond.createCustomDiamond(), new ItemStack(Material.AIR), CustomDiamond.createCustomDiamond(), new ItemStack(Material.STICK), new ItemStack(Material.DIAMOND_AXE));

    private void getNearbyBlock(Player p, int uno, int dos, int tres, BlockBreakEvent e) {
        Location a;
        if (EASTorWEST(p) || blockFace.get(p.getName()) == 1) {
            a = e.getBlock().getLocation().add(uno, dos, tres);
        } else {
            a = e.getBlock().getLocation().add(tres, dos, uno);
        }
        Material mat = a.getBlock().getType();
        if (mat.getHardness() < 50 && mat.getHardness() > 0) {
            if (mat.equals(Material.CHEST) || mat.equals(Material.ENDER_CHEST) || mat.equals(Material.BEACON)) {
                p.sendMessage("Wokół Ciebie są nielegalne bloki!");
            } else {
                if (e instanceof MojaZajebistaKlasa) {
                    return;
                }
                MojaZajebistaKlasa fakeEvent = new MojaZajebistaKlasa(a.getBlock(), p);
                Bukkit.getPluginManager().callEvent(fakeEvent);

                if (!fakeEvent.isCancelled()) {
                    a.getBlock().breakNaturally(p.getInventory().getItemInMainHand());
                }


            }
        }
    }

    @EventHandler
    public void BreakDiamondEvent(BlockBreakEvent e) {
        if (e.isCancelled()){
            return;
        }
            ItemStack hand = e.getPlayer().getInventory().getItemInMainHand();
            Block block = e.getBlock();
            if (!block.getDrops(hand).isEmpty()) {
                if (!hand.getEnchantments().containsKey(Enchantment.SILK_TOUCH)) {
                    if (block.getType() == Material.DIAMOND_ORE || block.getType() == Material.EMERALD) {
                        int rand_rubin = (int) (Math.random() * (190 - 1 + 1) + 1);
                        int rand_meteor = (int) (Math.random() * (500 - 1 + 1) + 1);
                        int rand_platinum = (int) (Math.random() * (1000 - 1 + 1) + 1);
                        int rand_bedrock = (int) (Math.random() * (2000 - 1 + 1) + 1);
                        if (rand_rubin == 130) {
                            Bukkit.broadcastMessage("§b§lGracz " + e.getPlayer().getName() + " wykopał rzadki rubin! Gratulacje!");
                            if (e.isCancelled()){
                                return;
                            }
                            else{
                                block.getWorld().dropItemNaturally(block.getLocation(), CustomDiamond.createCustomDiamond());
                            }

                        }
                        if (rand_meteor == 450) {
                            Bukkit.broadcastMessage("§6§lGracz " + e.getPlayer().getName() + " wykopał starożytny meteoryt! Gratulacje!");
                            if (e.isCancelled()){
                                return;
                            }
                            else{
                                block.getWorld().dropItemNaturally(block.getLocation(), Meteorite.createMeteorite());
                            }

                        }
                        if (rand_platinum == 760) {
                            Bukkit.broadcastMessage("§4§lGracz " + e.getPlayer().getName() + " wykopał bezcenną platynę! Gratulacje!");
                            if (e.isCancelled()){
                                return;
                            }
                            else{
                                block.getWorld().dropItemNaturally(block.getLocation(), Platinium.createPlatinium());
                            }
                        }
                        if (rand_bedrock == 1001) {
                            Bukkit.broadcastMessage("§0§lGracz " + e.getPlayer().getName() + " wykopał bedrock O_o! Gratulacje!");
                            if (e.isCancelled()){
                                return;
                            }
                            else{
                                block.getWorld().dropItemNaturally(block.getLocation(), Bedrock.createBedrock());
                            }

                        }
                    }
                }


            }
        }

    @EventHandler
    public void getBlockFace(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Material itemInHand = p.getInventory().getItemInMainHand().getType();
        BlockFace bFace = e.getBlockFace();
        if (itemInHand.equals(Material.DIAMOND_PICKAXE) || itemInHand.equals(Material.NETHERITE_PICKAXE)) {
            if (bFace == BlockFace.UP || bFace == BlockFace.DOWN) {
                blockFace.put(p.getName(), 1);
            } else {
                blockFace.put(p.getName(), 0);
            }

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
            int s = getpickaxe(p);
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

    }


    public int getpickaxe(Player p) {
        List<String> lore = p.getInventory().getItemInMainHand().getItemMeta().getLore();
        if (lore.get(0).equals(pickaxe1.getItemMeta().getLore().get(0))) {
            return 1;
        } else if (lore.get(0).equals(pickaxe2.getItemMeta().getLore().get(0))) {
            return 2;
        } else if (lore.get(0).equals(pickaxe3.getItemMeta().getLore().get(0))) {
            return 3;
        } else if (lore.get(0).equals(pickaxe4.getItemMeta().getLore().get(0))) {
            return 4;
        }
        return 0;
    }

    public boolean EASTorWEST(Player p) {
        return p.getFacing() == BlockFace.EAST || p.getFacing() == BlockFace.WEST;
    }

    /*
        @EventHandler
        public void BrakingUseAxe(BlockBreakEvent e) {
            try {

                if (!e.isCancelled() && e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "Timber")) {
                    Material type = e.getBlock().getType();
                    if (type == Material.ACACIA_LOG || type == Material.BIRCH_LOG || type == Material.DARK_OAK_LOG || type == Material.JUNGLE_LOG || type == Material.OAK_LOG || type == Material.SPRUCE_LOG) {
                        droptree(e.getBlock().getLocation(), e.getPlayer());
                        Location saploc = e.getBlock().getLocation().add(0, -1, 0);
                        Material sapling = saploc.getBlock().getType();
                        if (sapling == Material.DIRT) {
                            //sadzenie saplingu
                            Bukkit.getWorld(e.getBlock().getWorld().getName()).getBlockAt(e.getBlock().getLocation()).setType(Material.OAK_SAPLING);
                        }
                    }
                } else {
                    return;
                }
            } catch (NullPointerException ex) {
                return;
            }

        }
    */
    public void droptree(Location l, Player p) {
        List<Block> blocks = new LinkedList<>();
        for (int i = l.getBlockY(); i < l.getWorld().getHighestBlockYAt(l.getBlockX(), l.getBlockZ()); i++) {
            Block loc = Bukkit.getWorld(l.getWorld().getName()).getBlockAt(l.getBlockX(), i, l.getBlockZ());
            Material type = loc.getType();
            if (type == Material.ACACIA_LOG || type == Material.BIRCH_LOG || type == Material.DARK_OAK_LOG || type == Material.JUNGLE_LOG || type == Material.OAK_LOG || type == Material.SPRUCE_LOG) {
                blocks.add(loc);
            } else {
                break;
            }

        }
        for (Block block : blocks) {
            block.breakNaturally(p.getInventory().getItemInMainHand());
        }
        blocks.clear();
    }


    @EventHandler
    public void anvilname(PrepareAnvilEvent e) {
        //cancle rename white dye
        if (e.getResult() != null) {
            if (e.getResult().getType() == Material.WHITE_DYE) {
                e.setResult(null);
            }
        }

        //anvil lore connection
        try {
            ItemStack[] list = e.getInventory().getContents();
            if(e.getInventory().contains(Material.DIAMOND_PICKAXE) && e.getInventory().contains(Material.DIAMOND_PICKAXE)) {
                for (ItemStack i : list) {

                    if (Objects.requireNonNull(i.getItemMeta()).getLore() != null && Objects.requireNonNull(i.getItemMeta().getLore()).size() == 3 && i.getType() == Material.DIAMOND_PICKAXE) {
                        List<String> lore = i.getItemMeta().getLore();
                        if (lore.get(3).equals(pickaxe1.getItemMeta().getLore().get(3))) {
                            ItemMeta meta = e.getResult().getItemMeta();
                            meta.setLore(lore);
                            e.getResult().setItemMeta(meta);


                        }
                    }
                }
            }
        } catch (NullPointerException ex) {
            return;
        }
    }

}

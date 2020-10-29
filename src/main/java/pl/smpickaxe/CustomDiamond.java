package pl.smpickaxe;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class CustomDiamond {
    private CustomDiamond() {
    }

    public static ItemStack createCustomDiamond() {
        ItemStack customdiamond = new ItemStack(Material.WHITE_DYE);
        ItemMeta meta = customdiamond.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        lore.add("Bardzo Rzadki Rubin, pilnuj go!");
        meta.setLore(lore);
        meta.setDisplayName(ChatColor.DARK_RED + "Rubin");
        customdiamond.setItemMeta(meta);

        return customdiamond;
    }
}

package pl.smpickaxe;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class CreatePickaxe {
    public static ItemStack newPickaxe(String ile, ItemStack I, ItemStack G) {
        NamespacedKey klucz = new NamespacedKey(Smpickaxe.getInstance(), "k"+ile);

        ItemStack k = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta meta = k.getItemMeta();

        ArrayList<String> lorek1 = new ArrayList<>();
        lorek1.add("Kilof kopiący tunel "+ile);
        lorek1.add("Możesz go ulepszyć!");
        lorek1.add("Możesz go użyć tylko na mapie surowcowej!");
        meta.setDisplayName(ChatColor.BLUE+"Kilof "+ile);
        meta.setLore(lorek1);
        if(Bukkit.getRecipe(NamespacedKey.minecraft("k"+ile)) == null) {
            RecipeChoice k1 = new RecipeChoice.ExactChoice(G);
            RecipeChoice k2 = new RecipeChoice.ExactChoice(I);
            k.setItemMeta(meta);
            ShapedRecipe gp = new ShapedRecipe(klucz, k);
            gp.shape("GGG", " I ", " I ");
            gp.setIngredient('G', k1);
            gp.setIngredient('I', k2);
            Bukkit.addRecipe(gp);
        } else {
            Bukkit.broadcastMessage("Recepta jest juz zaladowana");
        }

        return k;
    }

}

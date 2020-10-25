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

public class Pickaxe_POW1 {
    public static final NamespacedKey Pickaxe_POW1_KEY = new NamespacedKey(Smpickaxe.getInstance(), "pickaxe_pow1");

    private Pickaxe_POW1() {
    }

    public static ItemStack createPickaxe_POW1() {
        ItemStack k1 = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta meta = k1.getItemMeta();

        ArrayList<String> lorek1 = new ArrayList<>();
        lorek1.add("Kilof kopiący tunel 2x1");
        lorek1.add("Możesz go ulepszyć!");
        lorek1.add("Możesz go użyć tylko na mapie surowcowej!");
        meta.setDisplayName(ChatColor.BLUE + "Kilof Poziomu 1");
        meta.setLore(lorek1);

        k1.setItemMeta(meta);
        return k1;
    }

    public static boolean k1_recipe() {
        ItemStack k1_pick = Pickaxe_POW1.createPickaxe_POW1();
        ItemStack rubin = CustomDiamond.createCustomDiamond();
        RecipeChoice k1 = new RecipeChoice.ExactChoice(rubin);


        ShapedRecipe gp = new ShapedRecipe(Pickaxe_POW1_KEY, k1_pick);

        gp.shape("GGG", " I ", " I ");

        gp.setIngredient('G', k1);
        gp.setIngredient('I', Material.STICK);

        boolean success = Bukkit.addRecipe(gp);
        return success;
    }

    public static boolean k1_recipe_unregister() {
        boolean success = Bukkit.removeRecipe(Pickaxe_POW1_KEY);

        return success;
    }
}

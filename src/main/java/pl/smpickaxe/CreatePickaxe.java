package pl.smpickaxe;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class CreatePickaxe {
    public static ItemStack newPickaxe(String ile, ItemStack D, ItemStack I, ItemStack G, ItemStack S, ItemStack out) {
        NamespacedKey klucz = new NamespacedKey(Smpickaxe.getInstance(), "k" + ile);

        ItemStack k = out;
        ItemMeta meta = k.getItemMeta();

        ArrayList<String> lorek1 = new ArrayList<>();
        if (ile != null) {
            lorek1.add("Kilof kopiący tunel " + ile);
            meta.setDisplayName(ChatColor.BLUE + "Kilof " + ile);
        } else {
            lorek1.add("Siekiera zcinająca drzewa");
            meta.setDisplayName(ChatColor.BLUE + "Timber");
        }
        lorek1.add("Możesz go ulepszyć!");
        lorek1.add("Możesz go użyć tylko na mapie surowcowej!");


        meta.setLore(lorek1);
        if (Bukkit.getRecipe(klucz(ile)) == null) {
            RecipeChoice k1 = new RecipeChoice.ExactChoice(D);
            RecipeChoice k2 = new RecipeChoice.ExactChoice(I);
            RecipeChoice k3 = new RecipeChoice.ExactChoice(G);
            RecipeChoice k4 = new RecipeChoice.ExactChoice(S);
            k.setItemMeta(meta);
            ShapedRecipe gp = new ShapedRecipe(klucz, k);
            gp.shape("DDG", "SI ", " I ");
            gp.setIngredient('D', k1);
            gp.setIngredient('I', k2);
            gp.setIngredient('G', k3);
            gp.setIngredient('S', k4);
            Bukkit.addRecipe(gp);
        } else {
            Bukkit.broadcastMessage("Recepta jest juz zaladowana");
        }

        return k;
    }

    public static NamespacedKey klucz(String ile) {
        return new NamespacedKey(Smpickaxe.getInstance(), "k" + ile);
    }

    public static boolean recipe_unregister(String ile) {
        return Bukkit.removeRecipe(klucz(ile));
    }

}

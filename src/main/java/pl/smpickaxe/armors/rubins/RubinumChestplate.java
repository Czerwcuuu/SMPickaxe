package pl.smpickaxe.armors.rubins;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import pl.smpickaxe.Smpickaxe;
import pl.smpickaxe.ores.CustomDiamond;

import java.util.ArrayList;
import java.util.UUID;

public class RubinumChestplate {
    public static final NamespacedKey RUBIN_CHESTPLATE = new NamespacedKey(Smpickaxe.getInstance(), "rubinchestplate");
    static ArrayList<String> lore = new ArrayList<>();

    private RubinumChestplate() {
    }

    public static ItemStack create() {
        ItemStack helmet = new ItemStack(Material.DIAMOND_CHESTPLATE);


        ItemMeta meta = helmet.getItemMeta();
        meta.setDisplayName(ChatColor.BLUE + "Rubinowa Zbroja");
        lore.add("Zbroja z rubinu");
        lore.add("Przedmiot dodaje 4HP");
        meta.setLore(lore);

        //Modifier max health
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "generic.maxHealth", 8, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);
        meta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, modifier);

        helmet.setItemMeta(meta);

        return helmet;
    }

    public static void register() {
        ItemStack ore = CustomDiamond.createCustomDiamond();
        RecipeChoice k1 = new RecipeChoice.ExactChoice(ore);
        ItemStack item = create();

        ShapedRecipe rec = new ShapedRecipe(RUBIN_CHESTPLATE, item);

        rec.shape(" A ", "ABA", " A ");
        rec.setIngredient('A', k1);
        rec.setIngredient('B', Material.NETHERITE_CHESTPLATE);

        boolean success = Bukkit.addRecipe(rec);
        if (success)
            Smpickaxe.getInstance().getLogger().fine("Dodany przepis na " + RUBIN_CHESTPLATE.getNamespace() + ":" + RUBIN_CHESTPLATE.getKey());
        else
            Smpickaxe.getInstance().getLogger().fine("NIEPOMYSLNE dodanie przepisu na: " + RUBIN_CHESTPLATE.getNamespace() + ":" + RUBIN_CHESTPLATE.getKey());

    }

    public static boolean unregister() {
        boolean success = Bukkit.removeRecipe(RUBIN_CHESTPLATE);

        if (success)
            Smpickaxe.getInstance().getLogger().fine("Usunieto przepis na:" + RUBIN_CHESTPLATE.getNamespace() + ":" + RUBIN_CHESTPLATE.getKey());

        else
            Smpickaxe.getInstance().getLogger().fine("Nie udalo sie usunac przepisu na: " + RUBIN_CHESTPLATE.getNamespace() + ":" + RUBIN_CHESTPLATE.getKey());

        return success;
    }
}

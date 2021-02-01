
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

public class RubinumLeggins {
    public static final NamespacedKey RUBIN_LEGGINS = new NamespacedKey(Smpickaxe.getInstance(), "rubinleggins");
    static ArrayList<String> lore = new ArrayList<>();

    private RubinumLeggins() {
    }

    public static ItemStack create() {
        ItemStack helmet = new ItemStack(Material.DIAMOND_LEGGINGS);


        ItemMeta meta = helmet.getItemMeta();
        meta.setDisplayName(ChatColor.BLUE + "Rubinowe Spodnie");
        lore.add("Spodnie z rubinu");
        lore.add("Przedmiot dodaje 3HP");
        meta.setLore(lore);

        //Modifier max health
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "generic.maxHealth", 6, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS);
        meta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, modifier);

        helmet.setItemMeta(meta);

        return helmet;
    }

    public static void register() {
        ItemStack ore = CustomDiamond.createCustomDiamond();
        RecipeChoice k1 = new RecipeChoice.ExactChoice(ore);
        ItemStack item = create();

        ShapedRecipe rec = new ShapedRecipe(RUBIN_LEGGINS, item);

        rec.shape(" A ", "ABA", " A ");
        rec.setIngredient('A', k1);
        rec.setIngredient('B', Material.NETHERITE_LEGGINGS);

        boolean success = Bukkit.addRecipe(rec);
        if (success)
            Smpickaxe.getInstance().getLogger().fine("Dodany przepis na " + RUBIN_LEGGINS.getNamespace() + ":" + RUBIN_LEGGINS.getKey());
        else
            Smpickaxe.getInstance().getLogger().fine("NIEPOMYSLNE dodanie przepisu na: " + RUBIN_LEGGINS.getNamespace() + ":" + RUBIN_LEGGINS.getKey());

    }

    public static boolean unregister() {
        boolean success = Bukkit.removeRecipe(RUBIN_LEGGINS);

        if (success)
            Smpickaxe.getInstance().getLogger().fine("Usunieto przepis na:" + RUBIN_LEGGINS.getNamespace() + ":" + RUBIN_LEGGINS.getKey());

        else
            Smpickaxe.getInstance().getLogger().fine("Nie udalo sie usunac przepisu na: " + RUBIN_LEGGINS.getNamespace() + ":" + RUBIN_LEGGINS.getKey());

        return success;
    }
}



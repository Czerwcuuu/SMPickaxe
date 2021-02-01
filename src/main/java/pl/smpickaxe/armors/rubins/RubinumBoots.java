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

public class RubinumBoots {
    public static final NamespacedKey RUBIN_BOOTS = new NamespacedKey(Smpickaxe.getInstance(), "rubinboots");
    static ArrayList<String> lore = new ArrayList<>();

    private RubinumBoots() {
    }

    public static ItemStack create() {
        ItemStack helmet = new ItemStack(Material.DIAMOND_BOOTS);


        ItemMeta meta = helmet.getItemMeta();
        meta.setDisplayName(ChatColor.BLUE + "Rubinowe Buty");
        lore.add("Buty z rubinu");
        lore.add("Przedmiot dodaje 1HP");
        meta.setLore(lore);

        //Modifier max health
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "generic.maxHealth", 20000, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET);
        meta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, modifier);
        modifier = new AttributeModifier(UUID.randomUUID(), "generic.armor", 2000, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET);
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR, modifier);

        helmet.setItemMeta(meta);

        return helmet;
    }

    public static void register() {
        ItemStack ore = CustomDiamond.createCustomDiamond();
        RecipeChoice k1 = new RecipeChoice.ExactChoice(ore);
        ItemStack item = create();

        ShapedRecipe rec = new ShapedRecipe(RUBIN_BOOTS, item);

        rec.shape(" A ", "ABA", " A ");
        rec.setIngredient('A', k1);
        rec.setIngredient('B', Material.NETHERITE_BOOTS);

        boolean success = Bukkit.addRecipe(rec);
        if (success)
            Smpickaxe.getInstance().getLogger().fine("Dodany przepis na " + RUBIN_BOOTS.getNamespace() + ":" + RUBIN_BOOTS.getKey());
        else
            Smpickaxe.getInstance().getLogger().fine("NIEPOMYSLNE dodanie przepisu na: " + RUBIN_BOOTS.getNamespace() + ":" + RUBIN_BOOTS.getKey());

    }

    public static boolean unregister() {
        boolean success = Bukkit.removeRecipe(RUBIN_BOOTS);

        if (success)
            Smpickaxe.getInstance().getLogger().fine("Usunieto przepis na:" + RUBIN_BOOTS.getNamespace() + ":" + RUBIN_BOOTS.getKey());

        else
            Smpickaxe.getInstance().getLogger().fine("Nie udalo sie usunac przepisu na: " + RUBIN_BOOTS.getNamespace() + ":" + RUBIN_BOOTS.getKey());

        return success;
    }
}






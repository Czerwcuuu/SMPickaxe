package pl.smpickaxe.armors.rubins;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import pl.smpickaxe.Smpickaxe;
import pl.smpickaxe.ores.CustomDiamond;

import java.util.UUID;

public class RubinumHelmet {
    public static final NamespacedKey RUBIN_HELMET = new NamespacedKey(Smpickaxe.getInstance(), "rubinhelmet");

    private RubinumHelmet() {
    }

    public static ItemStack create() {
        ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);

        ItemMeta meta = helmet.getItemMeta();
        meta.setDisplayName(ChatColor.BLUE + "Rubinowy Hełm");

        //Modifier max health
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(),"generic.maxHealth",10,AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD);
        meta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH,modifier);

        helmet.setItemMeta(meta);

        return helmet;
    }

    public static void register() {
        ItemStack ore = CustomDiamond.createCustomDiamond();
        RecipeChoice k1 = new RecipeChoice.ExactChoice(ore);
        ItemStack item = RubinumHelmet.create();

        ShapedRecipe rec = new ShapedRecipe(RUBIN_HELMET, item);

        rec.shape("AAA", "ABA", "AAA");
        rec.setIngredient('A', k1);
        rec.setIngredient('B', Material.NETHERITE_HELMET);


        boolean success = Bukkit.addRecipe(rec);
        if (success)
            Smpickaxe.getInstance().getLogger().fine("Dodany przepis na " + RUBIN_HELMET.getNamespace() + ":" + RUBIN_HELMET.getKey());
        else
            Smpickaxe.getInstance().getLogger().fine("NIEPOMYSLNE dodanie przepisu na: " + RUBIN_HELMET.getNamespace() + ":" + RUBIN_HELMET.getKey());

    }

    public static boolean unregister() {
        boolean success = Bukkit.removeRecipe(RUBIN_HELMET);

        if (success)
            Smpickaxe.getInstance().getLogger().fine("Usunieto przepis na:" + RUBIN_HELMET.getNamespace() + ":" + RUBIN_HELMET.getKey());

        else
            Smpickaxe.getInstance().getLogger().fine("Nie udalo sie usunac przepisu na: " + RUBIN_HELMET.getNamespace() + ":" + RUBIN_HELMET.getKey());

        return success;
    }




}

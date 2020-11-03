package pl.smpickaxe;

import org.bukkit.plugin.java.JavaPlugin;

public final class Smpickaxe extends JavaPlugin {

    private static Smpickaxe plugin;

    public static Smpickaxe getInstance() {
        return plugin;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;

        //Pickaxe_POW1.k1_recipe();
        //Pickaxe_POW2.k2_recipe();
        //Pickaxe_POW3.k3_recipe();
        //Pickaxe_POW4.k4_recipe();
        getServer().getPluginManager().registerEvents(new Events(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        CreatePickaxe.recipe_unregister("1x2");
        CreatePickaxe.recipe_unregister("2x2");
        CreatePickaxe.recipe_unregister("2x3");
        CreatePickaxe.recipe_unregister("3x3");
        CreatePickaxe.recipe_unregister("1");
    }

}

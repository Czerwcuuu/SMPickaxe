package pl.smpickaxe;

import org.bukkit.plugin.java.JavaPlugin;

public final class Smpickaxe extends JavaPlugin {

    private static Smpickaxe plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        Pickaxe_POW1.k1_recipe();
        getServer().getPluginManager().registerEvents(new Events(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Pickaxe_POW1.k1_recipe_unregister();
    }

    public static Smpickaxe getInstance(){
        return plugin;
    }
}

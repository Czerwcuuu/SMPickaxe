package pl.smpickaxe;

import org.bukkit.block.CreatureSpawner;
import org.bukkit.plugin.java.JavaPlugin;
import pl.smpickaxe.commands.GivePickaxe;

public final class Smpickaxe extends JavaPlugin {

    private static Smpickaxe plugin;

    public static Smpickaxe getInstance() {
        return plugin;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        this.getCommand("smpickaxe").setExecutor(new GivePickaxe());
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

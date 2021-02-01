package pl.smpickaxe;

import org.bukkit.plugin.java.JavaPlugin;
import pl.smpickaxe.armors.rubins.RubinumBoots;
import pl.smpickaxe.armors.rubins.RubinumChestplate;
import pl.smpickaxe.armors.rubins.RubinumHelmet;
import pl.smpickaxe.armors.rubins.RubinumLeggins;
import pl.smpickaxe.commands.GivePickaxe;
import pl.smpickaxe.config.ArmorsConfig;
import pl.smpickaxe.events.Events;
import pl.smpickaxe.events.RandomSpawnerChestEvents;
import pl.smpickaxe.utils.CreatePickaxe;

public final class Smpickaxe extends JavaPlugin {

    private static Smpickaxe plugin;

    public static Smpickaxe getInstance() {
        return plugin;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;

        //Config
        getConfig().options().copyDefaults(true);
        saveConfig();

        //Register rubinium armors
        /*RubinumHelmet.register();
        RubinumChestplate.register();
        RubinumLeggins.register();
        RubinumBoots.register();*/
        ArmorsConfig.setup();
        this.getCommand("smpickaxe").setExecutor(new GivePickaxe());
        getServer().getPluginManager().registerEvents(new Events(), this);
        getServer().getPluginManager().registerEvents(new RandomSpawnerChestEvents(), this);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        CreatePickaxe.recipe_unregister("1x2");
        CreatePickaxe.recipe_unregister("2x2");
        CreatePickaxe.recipe_unregister("2x3");
        CreatePickaxe.recipe_unregister("3x3_");
        CreatePickaxe.recipe_unregister("2x2spawnery");
        CreatePickaxe.recipe_unregister("1");

        //Armors Rubinium
        /*RubinumHelmet.unregister();
        RubinumChestplate.unregister();
        RubinumLeggins.unregister();
        RubinumBoots.unregister();*/
    }

}

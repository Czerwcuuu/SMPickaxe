package pl.smpickaxe.events;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;

public class PickaxeCraftEvent implements Listener {

    @EventHandler
    public void PrepareItemCraftEvent (PrepareItemCraftEvent e){
        try{
            Bukkit.getServer().broadcastMessage(e.getView().getPlayer().toString());
            Bukkit.getServer().broadcastMessage(e.getRecipe().getResult().toString());
        }
        catch(NullPointerException ex){
            Bukkit.getServer().broadcastMessage("Wyjebało błąd, ale to dobrze!");
        }

    }


}

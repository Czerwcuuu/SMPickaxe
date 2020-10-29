package pl.smpickaxe;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;

public class MojaZajebistaKlasa extends BlockBreakEvent {
    public MojaZajebistaKlasa(Block block, Player p) {
        super(block, p);
    }
}

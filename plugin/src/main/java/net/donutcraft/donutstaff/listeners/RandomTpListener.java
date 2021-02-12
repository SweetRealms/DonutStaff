package net.donutcraft.donutstaff.listeners;

import net.donutcraft.donutstaff.api.event.RandomTpEvent;
import net.donutcraft.donutstaff.api.staffmode.StaffModeHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import javax.inject.Inject;

public class RandomTpListener implements Listener {

    @Inject private StaffModeHandler staffModeHandler;

    @EventHandler
    public void randomTpListener(RandomTpEvent event) {
        Player player = event.getPlayer();

        staffModeHandler.randomTp(player);
    }
}

package net.donutcraft.donutstaff.listeners.vanilla;

import net.donutcraft.donutstaff.api.staffmode.StaffModeHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import javax.inject.Inject;

public class PlayerDeathListener implements Listener {

    @Inject StaffModeHandler staffModeHandler;

    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent event) {

        Player player = event.getEntity().getPlayer();
        staffModeHandler.saveDeathPlayerInventory(player);
    }
}

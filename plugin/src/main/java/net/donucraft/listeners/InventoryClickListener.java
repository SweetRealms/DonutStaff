package net.donucraft.listeners;

import net.donutcraft.donutstaff.api.staffmode.StaffModeManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import javax.inject.Inject;

public class InventoryClickListener implements Listener {

    @Inject private StaffModeManager staffModeManager;

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (staffModeManager.isOnStaffMode(player)) {
            event.setCancelled(true);
        }
    }
}

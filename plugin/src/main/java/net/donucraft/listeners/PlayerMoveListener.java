package net.donucraft.listeners;

import net.donutcraft.donutstaff.api.staffmode.StaffModeHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import javax.inject.Inject;

public class PlayerMoveListener implements Listener {

    @Inject private StaffModeHandler staffModeHandler;

    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (staffModeHandler.isPlayerFrozen(player)) {
            player.teleport(event.getFrom());
            event.setCancelled(true);
        }
    }
}

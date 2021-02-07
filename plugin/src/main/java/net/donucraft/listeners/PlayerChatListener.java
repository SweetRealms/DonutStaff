package net.donucraft.listeners;

import net.donucraft.files.FileCreator;
import net.donutcraft.donutstaff.api.staffmode.StaffModeHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import javax.inject.Inject;
import javax.inject.Named;

public class PlayerChatListener implements Listener {

    @Inject private StaffModeHandler staffModeHandler;
    @Inject @Named("messages") private FileCreator messages;

    @EventHandler
    public void onPlayerChatEvent(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        if (staffModeHandler.isPlayerInStaffChat(player)) {
            for (Player staff : Bukkit.getOnlinePlayers()) {
                if (!staff.hasPermission("donutcraft.staffchat.receive")) {
                    return;
                }
                staff.sendMessage(messages.getString("staff-mode.staff-chat.prefix") + message);
            }
            event.setMessage(null);
            event.setCancelled(true);
        }

    }
}

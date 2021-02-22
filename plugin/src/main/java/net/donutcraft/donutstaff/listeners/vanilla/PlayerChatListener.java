package net.donutcraft.donutstaff.listeners.vanilla;

import net.donutcraft.donutstaff.files.FileCreator;
import net.donutcraft.donutstaff.api.cache.SetCache;
import net.donutcraft.donutstaff.api.staffmode.StaffModeHandler;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.UUID;

public class PlayerChatListener implements Listener {

    @Inject @Named("messages") private FileCreator messages;
    @Inject @Named("config") private FileCreator config;
    @Inject @Named("staff-chat-cache") private SetCache<UUID> staffChatCache;

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerChatEvent(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        if ((staffChatCache.get().contains(player.getUniqueId())
                || message.startsWith(config.getString("staff-chat.short-cut"))
                        && player.hasPermission("donutstaff.staffchat"))) {
            message = message.replace(config.getString("staff-chat.short-cut"), "");

            for (Player staff : Bukkit.getServer().getOnlinePlayers()) {
                if (staff.hasPermission("donutcraft.staffchat.receive")) {
                    staff.sendMessage(messages.getString("staff-mode.staff-chat.prefix")
                            .replace("%player_name%", player.getName()) + message);
                }
            }

            event.setMessage(null);
            event.setCancelled(true);
        }

    }
}

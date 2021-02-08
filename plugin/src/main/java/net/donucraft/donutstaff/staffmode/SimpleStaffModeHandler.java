package net.donucraft.donutstaff.staffmode;

import net.donucraft.donutstaff.files.FileCreator;
import net.donucraft.donutstaff.util.nms.NMSManager;
import net.donutcraft.donutstaff.api.cache.Cache;
import net.donutcraft.donutstaff.api.staffmode.StaffModeHandler;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.*;

public class SimpleStaffModeHandler implements StaffModeHandler {

    @Inject @Named("messages") private FileCreator messages;
    @Inject @Named("freeze-cache") private Cache<UUID> freezeCache;
    @Inject @Named("staff-chat-cache") private Cache<UUID> staffChatCache;
    @Inject private NMSManager nmsManager;

    @Override
    public void freezePlayer(Player target) {
        for (String line : messages.getStringList("player.frozen-enabled.target")) {
            target.sendMessage(line);
        }
        nmsManager.getNMSHandler().sendTitle(target, "player.frozen-enabled.title",
                "player.frozen-enabled.subtitle",1, 3, 1);
        freezeCache.add(target.getUniqueId());
    }

    @Override
    public void unFreezePlayer(Player target) {
        for (String line : messages.getStringList("player.frozen-disabled.target")) {
            target.sendMessage(line);
        }
        nmsManager.getNMSHandler().sendTitle(target, "player.frozen-disabled.title",
                "player.frozen-disabled.subtitle", 1, 3, 1);
        freezeCache.remove(target.getUniqueId());
    }

    @Override
    public boolean isPlayerFrozen(Player target) {
        return freezeCache.exists(target.getUniqueId());
    }

    @Override
    public void toggleStaffChat(Player player) {
        if (!isPlayerInStaffChat(player)) {
            player.sendMessage(messages.getString("staff-mode.staff-chat.enabled"));
            staffChatCache.add(player.getUniqueId());
            return;
        }
        staffChatCache.remove(player.getUniqueId());
        player.sendMessage(messages.getString("staff-mode.staff-chat.disabled"));
    }

    @Override
    public boolean isPlayerInStaffChat(Player player) {
        return staffChatCache.exists(player.getUniqueId());
    }

    @Override
    public void fakeLeave(Player player) {
        Bukkit.broadcastMessage(messages.getString("staff-mode.commands.fake-leave")
                .replace("%player_name%", player.getName()));
    }

    @Override
    public void randomTp(Player player) {

    }
}

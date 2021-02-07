package net.donucraft.staffmode;

import net.donucraft.files.FileCreator;
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

    private final Set<UUID> frozenPlayers;
    private final Set<UUID> playersInStaffChat;

    public SimpleStaffModeHandler() {
        frozenPlayers = new HashSet<>();
        playersInStaffChat = new HashSet<>();
    }

    @Override
    public void freezePlayer(Player target) {
        target.sendMessage(messages.getString("player.frozen-enabled"));
        freezeCache.add(target.getUniqueId());
    }

    @Override
    public void unFreezePlayer(Player target) {
        target.sendMessage(messages.getString("player.frozen-disabled"));
        freezeCache.remove(target.getUniqueId());
    }

    @Override
    public boolean isPlayerFrozen(Player target) {
        return frozenPlayers.contains(target.getUniqueId());
    }

    @Override
    public void toggleStaffChat(Player player) {
        if (!isPlayerInStaffChat(player)) {
            player.sendMessage(messages.getString("staff-mode.staff-chat.enabled"));
            playersInStaffChat.add(player.getUniqueId());
            return;
        }
        playersInStaffChat.remove(player.getUniqueId());
        player.sendMessage(messages.getString("staff-mode.staff-chat.disabled"));
    }

    @Override
    public boolean isPlayerInStaffChat(Player player) {
        return playersInStaffChat.contains(player.getUniqueId());
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

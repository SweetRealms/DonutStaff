package net.donucraft.staffmode;

import net.donucraft.files.FileCreator;
import net.donutcraft.donutstaff.api.staffmode.StaffModeHandler;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SimpleStaffModeHandler implements StaffModeHandler {

    @Inject private FileCreator messages;

    private final List<UUID> frozenPlayers;
    private final List<UUID> playersInStaffChat;

    public SimpleStaffModeHandler() {
        frozenPlayers = new ArrayList<>();
        playersInStaffChat = new ArrayList<>();
    }

    @Override
    public void freezePlayer(Player target) {
        if (!isPlayerFrozen(target)) {
            target.sendMessage(messages.getString("player.frozen-enabled"));
            frozenPlayers.add(target.getUniqueId());
        }
        target.sendMessage(messages.getString("player.frozen-disabled"));
        frozenPlayers.remove(target.getUniqueId());
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

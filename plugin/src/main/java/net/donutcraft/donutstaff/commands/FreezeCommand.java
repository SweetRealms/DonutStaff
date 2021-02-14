package net.donutcraft.donutstaff.commands;

import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;

import net.donutcraft.donutstaff.files.FileCreator;
import net.donutcraft.donutstaff.api.staffmode.StaffModeHandler;

import org.bukkit.entity.Player;

import javax.inject.Inject;
import javax.inject.Named;

@Command(names = "freeze", desc = "Freeze a player.", permission = "donutstaff.freeze")
public class FreezeCommand implements CommandClass {

    @Inject private StaffModeHandler staffModeHandler;
    @Inject @Named("messages") private FileCreator messages;

    @Command(names = "")
    public boolean onFreezeCommand(@Sender Player player, Player target) {

        if (target.hasPermission("donutstaff.bypass")) {
            player.sendMessage(messages.getString("staff-mode.bypass")
                    .replace("%prefix%", messages.getString("commons.global-prefix")));
            return true;
        }

        if (staffModeHandler.isPlayerFrozen(target)) {
            staffModeHandler.unFreezePlayer(target);
            player.sendMessage(messages.getString("player.frozen-disabled.freezer")
                    .replace("%player_name%", target.getName())
                    .replace("%prefix%", messages.getString("commons.global-prefix")));
            return true;
        }
        player.sendMessage(messages.getString("player.frozen-enabled.freezer")
                .replace("%player_name%", target.getName())
                .replace("%prefix%", messages.getString("commons.global-prefix")));
        staffModeHandler.freezePlayer(target);

        return true;
    }
}

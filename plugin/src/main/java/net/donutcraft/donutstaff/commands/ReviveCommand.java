package net.donutcraft.donutstaff.commands;

import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import net.donutcraft.donutstaff.api.staffmode.StaffModeHandler;
import org.bukkit.entity.Player;

import javax.inject.Inject;

@Command(names = {"revive", "revivir"}, desc = "Revive a player.", permission = "donutstaff.revive")
public class ReviveCommand implements CommandClass {

    @Inject private StaffModeHandler staffModeHandler;

    @Command(names = "")
    public boolean onReviveCommand(@Sender Player player, Player target) {

        staffModeHandler.returnPlayerInventory(player, target);

        return true;
    }
}

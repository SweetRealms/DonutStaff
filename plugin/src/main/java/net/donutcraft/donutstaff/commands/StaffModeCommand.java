package net.donutcraft.donutstaff.commands;

import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import net.donutcraft.donutstaff.api.staffmode.StaffModeManager;
import org.bukkit.entity.Player;

import javax.inject.Inject;

@Command(names = {"staff", "sm", "mod"}, desc = "Enable staff mode.", permission = "donutcraft.staffmode")
public class StaffModeCommand implements CommandClass {

    @Inject private StaffModeManager staffModeManager;

    @Command(names = "")
    public boolean onStaffModeCommand(@Sender Player player) {
        if (staffModeManager.isOnStaffMode(player)) {
            staffModeManager.disableStaffMode(player);
            return true;
        }
        staffModeManager.enableStaffMode(player);

        return true;
    }

}
package net.donucraft.commands;

import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import net.donutcraft.donutstaff.api.staffmode.StaffModeHandler;
import org.bukkit.entity.Player;

import javax.inject.Inject;

@Command(names = "fakeleave", desc = "Fake a leave of a player", permission = "donutcraft.fakeleave")
public class FakeLeaveCommand implements CommandClass {

    @Inject private StaffModeHandler staffModeHandler;

    @Command(names = "")
    public boolean onFakeLeaveCommand(@Sender Player player) {

        staffModeHandler.fakeLeave(player);

        return true;
    }
}

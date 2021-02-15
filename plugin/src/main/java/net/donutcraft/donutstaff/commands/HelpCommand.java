package net.donutcraft.donutstaff.commands;

import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import net.donutcraft.donutstaff.files.FileCreator;
import org.bukkit.entity.Player;

import javax.inject.Inject;
import javax.inject.Named;

@Command(names = {"donuthelp", "staffhelp"}, desc = "See plugin commands", permission = "donutstaff.help")
public class HelpCommand implements CommandClass {

    @Inject @Named("messages") private FileCreator messages;

    @Command(names = "")
    public boolean onHelpCommand(@Sender Player player) {

        for (String line : messages.getStringList("staff-mode.commands.help")) {
            player.sendMessage(line);
        }

        return true;
    }
}

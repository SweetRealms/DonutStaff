package net.donutcraft.donutstaff.commands;

import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import net.donutcraft.donutstaff.files.FileCreator;

import org.bukkit.Bukkit;

import javax.inject.Inject;
import javax.inject.Named;

@Command(names = {"clearchat", "chatclear", "cc"}, desc = "Clear the chat", permission = "donutstaff.clearchat")
public class ClearChatCommand implements CommandClass {

    @Inject @Named("messages") private FileCreator messages;

    @Command(names = "")
    public boolean onClearChatCommand() {

        for (int i = 0; i < 100; ++i) {
            Bukkit.broadcastMessage("");
        }

        Bukkit.broadcastMessage(messages.getString("staff-mode.commands.clear-chat")
                .replace("%prefix%", messages.getString("commons.global-prefix")));
        return true;
    }
}

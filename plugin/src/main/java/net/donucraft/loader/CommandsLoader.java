package net.donucraft.loader;

import me.fixeddev.commandflow.CommandManager;
import me.fixeddev.commandflow.annotated.AnnotatedCommandTreeBuilder;
import me.fixeddev.commandflow.annotated.AnnotatedCommandTreeBuilderImpl;
import me.fixeddev.commandflow.annotated.part.PartInjector;
import me.fixeddev.commandflow.annotated.part.SimplePartInjector;
import me.fixeddev.commandflow.annotated.part.defaults.DefaultsModule;
import me.fixeddev.commandflow.bukkit.BukkitCommandManager;
import me.fixeddev.commandflow.bukkit.factory.BukkitModule;
import me.fixeddev.commandflow.command.Command;
import net.donucraft.DonutStaff;
import net.donucraft.commands.FakeLeaveCommand;
import net.donucraft.commands.FreezeCommand;
import net.donucraft.commands.StaffChatCommand;
import net.donucraft.commands.StaffModeCommand;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class CommandsLoader implements Loader {

    @Inject private DonutStaff donutStaff;
    @Inject private StaffModeCommand staffModeCommand;
    @Inject private StaffChatCommand staffChatCommand;
    @Inject private FreezeCommand freezeCommand;
    @Inject private FakeLeaveCommand fakeLeaveCommand;

    @Override
    public void load() {
        PartInjector partInjector = new SimplePartInjector();
        partInjector.install(new DefaultsModule());
        partInjector.install(new BukkitModule());

        AnnotatedCommandTreeBuilder annotatedCommandTreeBuilder = new AnnotatedCommandTreeBuilderImpl(partInjector);
        CommandManager commandManager = new BukkitCommandManager(donutStaff.getName());

        List<Command> commandList = new ArrayList<>();
        commandList.addAll(annotatedCommandTreeBuilder.fromClass(staffModeCommand));
        commandList.addAll(annotatedCommandTreeBuilder.fromClass(staffChatCommand));
        commandList.addAll(annotatedCommandTreeBuilder.fromClass(freezeCommand));
        commandList.addAll(annotatedCommandTreeBuilder.fromClass(fakeLeaveCommand));

        commandManager.registerCommands(commandList);
    }
}

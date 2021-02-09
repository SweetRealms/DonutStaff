package net.donutcraft.donutstaff.loader;

import me.fixeddev.commandflow.CommandManager;
import me.fixeddev.commandflow.annotated.AnnotatedCommandTreeBuilder;
import me.fixeddev.commandflow.annotated.AnnotatedCommandTreeBuilderImpl;
import me.fixeddev.commandflow.annotated.part.PartInjector;
import me.fixeddev.commandflow.annotated.part.SimplePartInjector;
import me.fixeddev.commandflow.annotated.part.defaults.DefaultsModule;
import me.fixeddev.commandflow.bukkit.BukkitCommandManager;
import me.fixeddev.commandflow.bukkit.factory.BukkitModule;
import me.fixeddev.commandflow.command.Command;
import me.fixeddev.commandflow.translator.DefaultTranslator;
import net.donutcraft.donutstaff.DonutStaff;
import net.donutcraft.donutstaff.commands.*;
import net.donutcraft.donutstaff.flow.CustomTranslationProvider;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class CommandsLoader implements Loader {

    @Inject private DonutStaff donutStaff;
    @Inject private StaffModeCommand staffModeCommand;
    @Inject private StaffChatCommand staffChatCommand;
    @Inject private FreezeCommand freezeCommand;
    @Inject private FakeLeaveCommand fakeLeaveCommand;
    @Inject private ClearChatCommand clearChatCommand;

    @Override
    public void load() {
        PartInjector partInjector = new SimplePartInjector();
        partInjector.install(new DefaultsModule());
        partInjector.install(new BukkitModule());

        AnnotatedCommandTreeBuilder annotatedCommandTreeBuilder = new AnnotatedCommandTreeBuilderImpl(partInjector);
        CommandManager commandManager = new BukkitCommandManager(donutStaff.getName());
        commandManager.setTranslator(new DefaultTranslator(new CustomTranslationProvider()));

        List<Command> commandList = new ArrayList<>();
        commandList.addAll(annotatedCommandTreeBuilder.fromClass(staffModeCommand));
        commandList.addAll(annotatedCommandTreeBuilder.fromClass(staffChatCommand));
        commandList.addAll(annotatedCommandTreeBuilder.fromClass(freezeCommand));
        commandList.addAll(annotatedCommandTreeBuilder.fromClass(fakeLeaveCommand));
        commandList.addAll(annotatedCommandTreeBuilder.fromClass(clearChatCommand));

        commandManager.registerCommands(commandList);
    }
}

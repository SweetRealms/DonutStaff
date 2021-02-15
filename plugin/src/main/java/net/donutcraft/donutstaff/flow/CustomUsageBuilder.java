package net.donutcraft.donutstaff.flow;

import me.fixeddev.commandflow.CommandContext;
import me.fixeddev.commandflow.command.Command;
import me.fixeddev.commandflow.usage.UsageBuilder;

import net.donutcraft.donutstaff.files.FileCreator;

import net.kyori.text.Component;
import net.kyori.text.TextComponent;

import javax.inject.Inject;
import javax.inject.Named;

public class CustomUsageBuilder implements UsageBuilder {

    @Inject
    @Named("messages")
    private FileCreator messages;

    @Override
    public Component getUsage(CommandContext commandContext) {

        Command toExecute = commandContext.getCommand();

        String label = String.join(" ", commandContext.getLabels());

        Component prefixComponent = TextComponent.of(messages.getString("commons.args.prefix"));
        Component labelComponent = TextComponent.of(label);
        Component partComponents = toExecute.getPart().getLineRepresentation();

        if(partComponents != null) {
            labelComponent = prefixComponent.append(labelComponent.append(TextComponent.of(" ")).append(partComponents));
        }

        return labelComponent;
    }
}

package net.donutcraft.donutstaff.flow;

import me.fixeddev.commandflow.CommandContext;
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
        String label = String.join(" ", commandContext.getLabels());
        Component infoComponent = TextComponent.of(messages.getString("commons.args.prefix"));

        switch (label) {
            case "freeze":
                Component labelComponentFreeze = TextComponent.of(messages.getString("commons.args.freeze.usage"));
                return infoComponent.append(labelComponentFreeze);
            case "revive":
                Component labelComponent = TextComponent.of(messages.getString("commons.args.revive.usage"));
                return infoComponent.append(labelComponent);

            default:
                return TextComponent.of(messages.getString("commons.args.no-more-args"));
        }
    }
}

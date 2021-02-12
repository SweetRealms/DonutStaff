package net.donutcraft.donutstaff.flow;

import me.fixeddev.commandflow.Namespace;
import me.fixeddev.commandflow.translator.TranslationProvider;
import net.donutcraft.donutstaff.files.FileCreator;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashMap;
import java.util.Map;

public class CustomTranslationProvider implements TranslationProvider {

    @Inject @Named("messages") private FileCreator messages;

    protected final Map<String, String> translations;

    public CustomTranslationProvider() {
        this.translations = new HashMap<>();
        translations.put("argument.no-more", "No more arguments were found, size: %s position: %s");
    }

    @Override
    public String getTranslation(Namespace namespace, String key) {

        switch (key) {
            case "player.offline":
                return messages.getString("commons.offline-player")
                        .replace("%prefix%", messages.getString("commons.global-prefix"));
            case "sender.only-player":
                return messages.getString("commons.only-player-sender")
                        .replace("%prefix%", messages.getString("commons.global-prefix"));
            case "sender.unknown":
                return messages.getString("commons.unknown-sender")
                        .replace("%prefix%", messages.getString("commons.global-prefix"));
            case "command.no-permission":
                return messages.getString("commons.not-enough-permissions")
                        .replace("%prefix%", messages.getString("commons.global-prefix"));
            case "command.subcommand.invalid":
                return messages.getString("commons.invalid-argument")
                        .replace("%prefix%", messages.getString("commons.global-prefix"));
        }
        return translations.get(key);
    }
}

package net.donucraft.module;

import me.yushust.inject.Binder;
import me.yushust.inject.Module;
import me.yushust.inject.key.TypeReference;
import net.donucraft.DonutStaff;
import net.donucraft.cache.FreezeCache;
import net.donucraft.cache.StaffChatCache;
import net.donucraft.cache.StaffModeCache;
import net.donucraft.files.FileCreator;
import net.donucraft.files.FileMatcher;
import net.donucraft.staffmode.SimpleStaffModeHandler;
import net.donucraft.staffmode.SimpleStaffModeManager;
import net.donutcraft.donutstaff.api.cache.Cache;
import net.donutcraft.donutstaff.api.staffmode.StaffModeHandler;
import net.donutcraft.donutstaff.api.staffmode.StaffModeManager;
import org.bukkit.plugin.Plugin;

import java.util.UUID;

public class MainModule implements Module {

    private final DonutStaff donutStaff;

    public MainModule(DonutStaff donutStaff) {
        this.donutStaff = donutStaff;
    }

    @Override
    public void configure(Binder binder) {

        FileMatcher fileMatcher = new FileMatcher()
                .bind("config", new FileCreator(donutStaff, "config"))
                .bind("messages", new FileCreator(donutStaff, "messages"));


        binder.install(fileMatcher.build());
        binder.install(new ServiceModule());

        binder.bind(DonutStaff.class).toInstance(donutStaff);
        binder.bind(Plugin.class).to(DonutStaff.class);

        binder.bind(StaffModeManager.class).to(SimpleStaffModeManager.class);
        binder.bind(StaffModeHandler.class).to(SimpleStaffModeHandler.class);

        binder.bind(new TypeReference<Cache<UUID>>(){}).named("freeze-cache").to(FreezeCache.class).singleton();
        binder.bind(new TypeReference<Cache<UUID>>(){}).named("staff-chat-cache").to(StaffChatCache.class).singleton();
        binder.bind(new TypeReference<Cache<UUID>>(){}).named("staff-mode-cache").to(StaffModeCache.class).singleton();


    }
}

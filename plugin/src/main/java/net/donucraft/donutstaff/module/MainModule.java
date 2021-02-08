package net.donucraft.donutstaff.module;

import me.yushust.inject.Binder;
import me.yushust.inject.Module;
import me.yushust.inject.key.TypeReference;
import net.donucraft.donutstaff.DonutStaff;
import net.donucraft.donutstaff.cache.FreezeCache;
import net.donucraft.donutstaff.cache.StaffChatCache;
import net.donucraft.donutstaff.cache.StaffModeCache;
import net.donucraft.donutstaff.files.FileCreator;
import net.donucraft.donutstaff.files.FileMatcher;
import net.donucraft.donutstaff.staffmode.SimpleStaffModeHandler;
import net.donucraft.donutstaff.staffmode.SimpleStaffModeManager;
import net.donucraft.donutstaff.util.nms.NMSManager;
import net.donucraft.donutstaff.util.nms.SimpleNMSManager;
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

        binder.bind(NMSManager.class).to(SimpleNMSManager.class);
    }
}

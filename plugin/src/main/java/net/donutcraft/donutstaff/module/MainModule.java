package net.donutcraft.donutstaff.module;

import me.yushust.inject.Binder;
import me.yushust.inject.Module;
import me.yushust.inject.key.TypeReference;

import net.donutcraft.donutstaff.DonutStaff;
import net.donutcraft.donutstaff.api.cache.MapCache;
import net.donutcraft.donutstaff.cache.*;
import net.donutcraft.donutstaff.files.FileCreator;
import net.donutcraft.donutstaff.files.FileMatcher;
import net.donutcraft.donutstaff.staffmode.SimpleStaffModeHandler;
import net.donutcraft.donutstaff.staffmode.SimpleStaffModeManager;
import net.donutcraft.donutstaff.util.nms.NMSManager;
import net.donutcraft.donutstaff.util.nms.SimpleNMSManager;
import net.donutcraft.donutstaff.api.cache.SetCache;
import net.donutcraft.donutstaff.api.staffmode.StaffModeHandler;
import net.donutcraft.donutstaff.api.staffmode.StaffModeManager;

import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.UUID;

public class MainModule implements Module {

    private final DonutStaff donutStaff;

    public MainModule(DonutStaff donutStaff) {
        this.donutStaff = donutStaff;
    }

    @Override
    public void configure(Binder binder) {

        FileMatcher fileMatcher = new FileMatcher()
                //.bind("config", new FileCreator(donutStaff, "config"))
                .bind("items-file", new FileCreator(donutStaff, "items"))
                .bind("messages", new FileCreator(donutStaff, "messages"));



        binder.install(fileMatcher.build());

        binder.install(new ServiceModule());

        binder.bind(DonutStaff.class).toInstance(donutStaff);
        binder.bind(Plugin.class).to(DonutStaff.class);

        binder.bind(NMSManager.class).to(SimpleNMSManager.class).singleton();

        binder.bind(StaffModeManager.class).to(SimpleStaffModeManager.class);
        binder.bind(StaffModeHandler.class).to(SimpleStaffModeHandler.class);

        binder.bind(new TypeReference<SetCache<UUID>>() {}).named("freeze-cache").to(FreezeCache.class).singleton();
        binder.bind(new TypeReference<SetCache<UUID>>() {}).named("staff-chat-cache").to(StaffChatCache.class).singleton();
        binder.bind(new TypeReference<SetCache<UUID>>() {}).named("staff-mode-cache").to(StaffModeCache.class).singleton();
        binder.bind(new TypeReference<SetCache<UUID>>() {}).named("vanish-cache").to(VanishCache.class).singleton();
        binder.bind(new TypeReference<MapCache<UUID, List<ItemStack>>>() {})
                .named("death-inventories-cache").to(DeathInventoriesCache.class).singleton();
        binder.bind(new TypeReference<MapCache<UUID, List<ItemStack>>>() {})
                .named("death-armor-cache").to(DeathArmorCache.class).singleton();

    }
}

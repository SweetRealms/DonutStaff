package net.donutcraft.donutstaff.module;

import me.yushust.inject.AbstractModule;
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

import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;

public class MainModule extends AbstractModule {

    private final DonutStaff donutStaff;

    public MainModule(DonutStaff donutStaff) {
        this.donutStaff = donutStaff;
    }

    @Override
    public void configure() {

        FileMatcher fileMatcher = new FileMatcher()
                .bind("items-file", new FileCreator(donutStaff, "items"))
                .bind("messages", new FileCreator(donutStaff, "messages"));

        install(fileMatcher.build());

        install(new ServiceModule());

        bind(DonutStaff.class).toInstance(donutStaff);
        bind(Plugin.class).to(DonutStaff.class);

        bind(NMSManager.class).to(SimpleNMSManager.class).singleton();

        bind(StaffModeManager.class).to(SimpleStaffModeManager.class);
        bind(StaffModeHandler.class).to(SimpleStaffModeHandler.class);

        bind(new TypeReference<MapCache<UUID, List<ItemStack>>>() {})
                .named("death-inventories-cache").toInstance(new MapCacheImpl<>());
        bind(new TypeReference<MapCache<UUID, List<ItemStack>>>() {})
                .named("death-armor-cache")
                .toInstance(new MapCacheImpl<>());
        bindSetCache(UUID.class, "freeze-cache");
        bindSetCache(UUID.class, "staff-chat-cache");
        bindSetCache(UUID.class, "staff-mode-cache");
        bindSetCache(UUID.class, "vanish-cache");
    }

    private void bindSetCache(Type type, String name) {
        if (name == null) {
            bind(TypeReference.of(SetCache.class, type)).to(TypeReference.of(SetCacheImpl.class, type)).singleton();
        } else {
            bind(TypeReference.of(SetCache.class, type)).named(name).to(TypeReference.of(SetCacheImpl.class, type)).singleton();
        }
    }
}

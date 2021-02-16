package net.donutcraft.donutstaff;

import me.yushust.inject.Injector;

import net.donutcraft.donutstaff.module.MainModule;
import net.donutcraft.donutstaff.service.DonutStaffService;

import org.bukkit.plugin.java.JavaPlugin;

import javax.inject.Inject;
import javax.inject.Named;

public class DonutStaff extends JavaPlugin {

    @Inject
    @Named("main-service")
    private DonutStaffService donutStaffService;

    public void onLoad() {
        Injector injector = Injector.create(new MainModule(this));
        injector.getInstance(getClass());
    }

    public void onEnable() {

        donutStaffService.start();
    }

    public void onDisable() {

        donutStaffService.stop();
    }
}

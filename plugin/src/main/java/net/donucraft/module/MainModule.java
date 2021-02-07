package net.donucraft.module;

import me.yushust.inject.Binder;
import me.yushust.inject.Module;
import net.donucraft.DonutStaff;
import net.donucraft.files.FileCreator;
import net.donucraft.files.FileMatcher;
import net.donucraft.staffmode.SimpleStaffModeHandler;
import net.donucraft.staffmode.SimpleStaffModeManager;
import net.donutcraft.donutstaff.api.staffmode.StaffModeHandler;
import net.donutcraft.donutstaff.api.staffmode.StaffModeManager;
import org.bukkit.plugin.Plugin;

public class MainModule implements Module {

    private DonutStaff donutStaff;

    public MainModule(DonutStaff futuristicClans) {
        this.donutStaff = futuristicClans;
    }

    @Override
    public void configure(Binder binder) {

        FileMatcher fileMatcher = new FileMatcher()
                .bind("config", new FileCreator(donutStaff, "config"))
                .bind("messages", new FileCreator(donutStaff, "messages"));


        binder.install(fileMatcher.build());

        binder.bind(DonutStaff.class).toInstance(donutStaff);
        binder.bind(Plugin.class).to(DonutStaff.class);

        binder.bind(StaffModeManager.class).to(SimpleStaffModeManager.class);
        binder.bind(StaffModeHandler.class).to(SimpleStaffModeHandler.class);

        binder.install(new ServiceModule());

    }
}

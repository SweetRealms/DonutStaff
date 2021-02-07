package net.donucraft.module;

import me.yushust.inject.Binder;
import me.yushust.inject.Module;
import net.donucraft.service.DonutStaffService;
import net.donucraft.service.Service;

public class ServiceModule implements Module {

    @Override
    public void configure(Binder binder) {
        binder.bind(Service.class).named("main-service").to(DonutStaffService.class).singleton();
    }
}

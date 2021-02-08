package net.donucraft.service;

import net.donucraft.loader.CommandsLoader;
import net.donucraft.loader.ListenersLoader;
import net.donucraft.util.nms.NMSSetup;

import javax.inject.Inject;

public class DonutStaffService implements Service {

    @Inject private CommandsLoader commandsLoader;
    @Inject private ListenersLoader listenersLoader;
    @Inject private NMSSetup nmsSetup;

    @Override
    public void start() {
        commandsLoader.load();
        listenersLoader.load();
        nmsSetup.enableNMS();
    }

    @Override
    public void stop() {

    }
}

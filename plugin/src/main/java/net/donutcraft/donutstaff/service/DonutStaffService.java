package net.donutcraft.donutstaff.service;

import net.donutcraft.donutstaff.loader.CommandsLoader;
import net.donutcraft.donutstaff.loader.ListenersLoader;
import net.donutcraft.donutstaff.util.nms.NMSManager;

import javax.inject.Inject;

public class DonutStaffService implements Service {

    @Inject private CommandsLoader commandsLoader;
    @Inject private ListenersLoader listenersLoader;
    @Inject private NMSManager nmsManager;

    @Override
    public void start() {
        nmsManager.enableNMS();
        commandsLoader.load();
        listenersLoader.load();
    }

    @Override
    public void stop() {

    }
}

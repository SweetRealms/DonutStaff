package net.donucraft.service;

import net.donucraft.loader.CommandsLoader;
import net.donucraft.loader.ListenersLoader;

import javax.inject.Inject;

public class DonutStaffService implements Service {

    @Inject private CommandsLoader commandsLoader;
    @Inject private ListenersLoader listenersLoader;

    @Override
    public void start() {
        commandsLoader.load();
        listenersLoader.load();
    }

    @Override
    public void stop() {

    }
}

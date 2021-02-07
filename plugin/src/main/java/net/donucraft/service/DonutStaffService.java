package net.donucraft.service;

import net.donucraft.loader.CommandsLoader;

import javax.inject.Inject;

public class DonutStaffService implements Service {

    @Inject private CommandsLoader commandsLoader;

    @Override
    public void start() {
        commandsLoader.load();
    }

    @Override
    public void stop() {

    }
}

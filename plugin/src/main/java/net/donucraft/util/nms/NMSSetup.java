package net.donucraft.util.nms;

import net.donutcraft.donutstaff.api.nms.NMSHandler;

public interface NMSSetup {
    boolean enableNMS();

    NMSHandler getNMSHandler();

    String getServerVersion();
}

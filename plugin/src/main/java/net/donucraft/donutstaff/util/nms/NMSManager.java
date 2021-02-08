package net.donucraft.donutstaff.util.nms;

import net.donutcraft.donutstaff.api.nms.NMSHandler;

public interface NMSManager {

    void enableNMS();

    NMSHandler getNMSHandler();

    String getServerVersion();
}

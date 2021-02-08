package net.donucraft.util.nms;

import net.donutcraft.donutstaff.api.nms.*;
import org.bukkit.Bukkit;

import java.util.regex.Pattern;

public class NMSSetupImpl implements NMSSetup {
    private final String serverVersion;

    private NMSHandler nmsHandler;

    public NMSSetupImpl() {
        serverVersion = Bukkit.getServer().getClass().getPackage().getName().split(Pattern.quote("."))[3];
    }


    @Override
    public boolean enableNMS() {
        switch (serverVersion) {

            case "v1_8_R3":
                nmsHandler = new NMSHandler_1_8_R3();
                break;
            case "v1_9_R2":
                nmsHandler = new NMSHandler_1_9_R2();
                break;
            case "v1_10_R1":
                nmsHandler = new NMSHandler_1_10_R1();
                break;
            case "v1_11_R1":
                nmsHandler = new NMSHandler_1_11_R1();
                break;
            case "v1_12_R1":
                nmsHandler = new NMSHandler_1_12_R1();
            case "v1_13_R2":
                nmsHandler = new NMSHandler_1_13_R2();
                break;
            case "v1_14_R1":
                nmsHandler = new NMSHandler_1_14_R1();
                break;
            case "v1_15_R1":
                nmsHandler = new NMSHandler_1_15_R1();
                break;
            case "v1_16_R1":
                nmsHandler = new NMSHandler_1_16_R1();
                break;
            case "v1_16_R3":
                nmsHandler = new NMSHandler_1_16_R3();
                break;
            default:
                Bukkit.getLogger().severe("Your server version is not supported!");
                return false;
        }
        return true;
    }

    @Override
    public NMSHandler getNMSHandler() {
        return nmsHandler;
    }

    @Override
    public String getServerVersion() {
        return serverVersion;
    }
}

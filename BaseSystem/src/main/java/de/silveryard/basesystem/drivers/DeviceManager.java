package de.silveryard.basesystem.drivers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebif on 26.04.2017.
 */
public class DeviceManager {
    private static DeviceManager instance;

    public static void initialize(){
        instance = new DeviceManager();
    }
    public static DeviceManager getInstance(){
        return instance;
    }

    private List<Driver> loadedDrivers;

    private DeviceManager(){
        loadedDrivers = new ArrayList<>();
    }
}

package de.silveryard.basesystem.driver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by silveryard on 29.04.17.
 */
public final class DriverManager {
    private static DriverManager instance;
    public static void initialize(){
        instance = new DriverManager();
    }
    public static DriverManager getInstance(){
        return instance;
    }

    private List<Driver> drivers;

    private DriverManager(){
        drivers = new ArrayList<>();
    }

    public void update(){
        for(int i = 0; i < drivers.size(); i++){
            drivers.get(i).update();
        }
    }

    public <T extends Driver> T loadDriver(Class<T> driverClass){
        T driver = getDriver(driverClass);

        if(driver != null){
            return driver;
        }

        try {
            driver = driverClass.newInstance();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        drivers.add(driver);
        driver.onLoad();
        return driver;
    }
    public <T extends Driver> void unloadDriver(Class<T> driverClass){
        T driver = getDriver(driverClass);

        if(driver == null){
            return;
        }

        drivers.remove(driver);
        driver.onUnload();
    }
    public <T extends Driver> boolean isDriverLoaded(Class<T> driverClass){
        return getDriver(driverClass) != null;
    }
    public <T extends Driver> T getDriver(Class<T> driverClass){
        for(int i = 0; i < drivers.size(); i++){
            Driver driver = drivers.get(i);
            if(driverClass.isInstance(driver)){
                return (T)driver;
            }
        }

        return null;
    }
}

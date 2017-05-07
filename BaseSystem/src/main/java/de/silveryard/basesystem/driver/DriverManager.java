package de.silveryard.basesystem.driver;

import de.silveryard.basesystem.util.IDisposable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by silveryard on 29.04.17.
 */
public final class DriverManager implements IDisposable {
    private static DriverManager instance;
    /**
     * Initializes this manager
     */
    public static void initialize(){
        instance = new DriverManager();
    }
    /**
     * Returns the instance of this manager
     * @return Instance
     */
    public static DriverManager getInstance(){
        return instance;
    }

    private List<Driver> drivers;

    private DriverManager(){
        drivers = new ArrayList<>();
    }

    /**
     * Updates all drivers
     */
    public void update(){
        for(int i = 0; i < drivers.size(); i++){
            drivers.get(i).update();
        }
    }

    /**
     * Loads a driver. Only one driver of a given class can be loaded
     * @param driverClass Class of the driver to load
     * @return Loaded driver instance
     */
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
    /**
     * Unloads a driver.
     * @param driverClass Class of the driver to unload
     */
    public <T extends Driver> void unloadDriver(Class<T> driverClass){
        T driver = getDriver(driverClass);

        if(driver == null){
            return;
        }

        drivers.remove(driver);
        driver.onUnload();
    }
    /**
     * Checks if a driver is currently loaded
     * @param driverClass Class of the driver to check
     * @return
     */
    public <T extends Driver> boolean isDriverLoaded(Class<T> driverClass){
        return getDriver(driverClass) != null;
    }
    /**
     * Gets a driver
     * @param driverClass Class of the driver to get
     * @return Driver instance if driver is loaded. Null otherwise
     */
    public <T extends Driver> T getDriver(Class<T> driverClass){
        for(int i = 0; i < drivers.size(); i++){
            Driver driver = drivers.get(i);
            if(driverClass.isInstance(driver)){
                return (T)driver;
            }
        }

        return null;
    }

    @Override
    public void dispose() {
        for(int i = 0; i < drivers.size(); i++){
            drivers.get(i).onUnload();
        }
        drivers.clear();
    }
}

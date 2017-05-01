package de.silveryard.basesystem.driver;

import java.util.List;

/**
 * Created by silveryard on 29.04.17.
 */
public abstract class Driver<TDev extends Device>  {
    private boolean loaded;

    public Driver(){
        loaded = false;
    }

    public void onLoad(){
        loaded = true;
    }
    public void onUnload(){
        loaded = false;
    }

    public void update(){}

    public final boolean isLoaded(){
        return loaded;
    }
    public abstract List<TDev> getDevices();
}

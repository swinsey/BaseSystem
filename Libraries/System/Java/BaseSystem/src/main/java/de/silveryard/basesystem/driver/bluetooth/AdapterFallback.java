package de.silveryard.basesystem.driver.bluetooth;

/**
 * Created by silveryard on 01.05.17.
 */
final class AdapterFallback extends Adapter {
    @Override
    public String getAddress() {
        return "fallback";
    }
    @Override
    public String getAlias() {
        return "fallback";
    }
    @Override
    public String getModalias() {
        return "fallback";
    }
    @Override
    public String getName() {
        return "fallback";
    }

    @Override
    public boolean isDiscoverable() {
        return false;
    }
    @Override
    public boolean isDiscovering() {
        return false;
    }
    @Override
    public boolean isPairable() {
        return false;
    }
    @Override
    public boolean isPowered() {
        return false;
    }

    @Override
    public void startDiscovery() {

    }
    @Override
    public void stopDiscovery() {

    }
}

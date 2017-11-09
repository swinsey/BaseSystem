package de.silveryard.basesystem.driver.bluetoothphone;

/**
 * Created by silveryard on 28.05.17.
 */
public abstract class Phonebook {
    public abstract int getNumEntries();

    public abstract String getEntryName(int entry);
    public abstract int getEntryNumNumbers(int entry);
    public abstract String getEntryNumberType(int entry, int number);
    public abstract String getEntryNumber(int entry, int number);
}

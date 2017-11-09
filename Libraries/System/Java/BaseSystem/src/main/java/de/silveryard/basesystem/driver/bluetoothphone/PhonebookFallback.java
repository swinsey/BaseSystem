package de.silveryard.basesystem.driver.bluetoothphone;

/**
 * Created by silveryard on 29.05.17.
 */
final class PhonebookFallback extends Phonebook {
    @Override
    public int getNumEntries() {
        return 0;
    }

    @Override
    public String getEntryName(int entry) {
        return null;
    }

    @Override
    public int getEntryNumNumbers(int entry) {
        return 0;
    }

    @Override
    public String getEntryNumberType(int entry, int number) {
        return null;
    }

    @Override
    public String getEntryNumber(int entry, int number) {
        return null;
    }
}

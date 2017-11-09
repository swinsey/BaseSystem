package de.silveryard.basesystem.driver.bluetoothphone;

import ezvcard.Ezvcard;
import ezvcard.VCard;
import ezvcard.parameter.TelephoneType;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by silveryard on 28.05.17.
 */
final class ObexPhonebook extends Phonebook {
    private final List<VCard> cards;

    public ObexPhonebook(String path){
        try {
            cards = Ezvcard.parse(new File(path)).all();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        //TODO remove duplicate numbers

        System.out.println("Parsed Phonebook: " + path + ". " + cards.size() + " Entries");
    }

    @Override
    public int getNumEntries() {
        return cards.size();
    }

    @Override
    public String getEntryName(int entry) {
        return cards.get(entry).getFormattedName().getValue();
    }

    @Override
    public int getEntryNumNumbers(int entry) {
        return cards.get(entry).getTelephoneNumbers().size();
    }

    @Override
    public String getEntryNumberType(int entry, int number) {
        List<TelephoneType> types = cards.get(entry).getTelephoneNumbers().get(number).getTypes();
        if(types.size() == 0){
            return "";
        }else{
            return types.get(0).getValue();
        }
    }

    @Override
    public String getEntryNumber(int entry, int number) {
        return cards.get(entry).getTelephoneNumbers().get(number).getText();
    }
}

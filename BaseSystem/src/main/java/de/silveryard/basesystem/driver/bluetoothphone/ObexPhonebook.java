package de.silveryard.basesystem.driver.bluetoothphone;

import ezvcard.Ezvcard;
import ezvcard.VCard;
import ezvcard.io.chain.ChainingTextParser;
import ezvcard.parameter.TelephoneType;
import ezvcard.property.Telephone;

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
}

package de.silveryard.basesystem.settings;

import de.silveryard.basesystem.util.IDisposable;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by silveryard on 04.06.17.
 */
public final class SettingsManager implements IDisposable {
    private static SettingsManager instance;

    public static void initialize(Path directory){
        instance = new SettingsManager(directory);
    }
    public static SettingsManager getInstance(){
        return instance;
    }

    private SettingsDB db;

    private SettingsManager(Path directory){
        if(!Files.exists(directory)){
            throw new RuntimeException("SettingsManager directory does not exist");
        }
        if(!Files.isDirectory(directory)){
            throw new RuntimeException("SettingsManager directory cannot be a file");
        }

        Path dbFile = Paths.get(directory.toString(), "settings.db");
        db = new SettingsDB(dbFile);
    }

    public synchronized void putEntry(String container, String key, byte[] data){
        if(db.entryExists(container, key)){
            db.updateEntry(container, key, data);
        }else{
            db.addEntry(container, key, data);
        }
    }
    public synchronized void putEntry(String container, String key, String value){
        putEntry(container, key, value.getBytes());
    }

    public synchronized void deleteKey(String container, String key){
        if(db.entryExists(container, key)){
            db.deleteEntry(container, key);
        }
    }

    public synchronized byte[] getEntryByteArray(String container, String key){
        if(db.entryExists(container, key)){
            return db.getEntry(container, key);
        }else{
            return null;
        }
    }
    public synchronized String getEntryString(String container, String key){
        byte[] data = getEntryByteArray(container, key);

        if(data == null){
            return null;
        } else {
            return new String(data);
        }
    }

    @Override
    public void dispose() {
        db.dispose();
    }
}

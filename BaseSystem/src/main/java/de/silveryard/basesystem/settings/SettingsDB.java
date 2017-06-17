package de.silveryard.basesystem.settings;

import de.silveryard.basesystem.util.IDisposable;

import javax.sql.rowset.serial.SerialBlob;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;

/**
 * Layout:
 *  varchar(100) container
 *  varchar(100) key
 *  BLOB value
 */

/**
 * Created by silveryard on 04.06.17.
 */
final class SettingsDB implements IDisposable {
    private final Connection dbConnection;

    public SettingsDB(Path dbFile){
        boolean recreate = false;
        if(!Files.exists(dbFile)){
            recreate = true;
        }
        try{
            Class.forName("org.sqlite.JDBC");
            dbConnection = DriverManager.getConnection("jdbc:sqlite:" + dbFile.toString());
        }catch(Exception e){
            throw new RuntimeException(e);
        }

        if(recreate){
            String statement = "" +
                    "CREATE TABLE settings (" +
                    "    container CHAR(100) NOT NULL," +
                    "    key CHAR(100) NOT NULL," +
                    "    value BLOB NOT NULL," +
                    "    PRIMARY KEY(container, key)" +
                    ")";
            executeSql(statement);
        }
    }

    public boolean entryExists(String container, String key){
        try {
            PreparedStatement stmtExists = dbConnection.prepareStatement("" +
                    "SELECT " +
                    "  container " +
                    "FROM " +
                    "  settings " +
                    "WHERE " +
                    "    container = ? " +
                    "  AND " +
                    "    key = ?");
            stmtExists.setString(1, container);
            stmtExists.setString(2, key);
            ResultSet resultSet = stmtExists.executeQuery();
            return resultSet.next();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    public void addEntry(String container, String key, byte[] value){
        try {
            PreparedStatement stmtInsert = dbConnection.prepareStatement("" +
                    "INSERT INTO" +
                    "  settings" +
                    "  (container, key, value) " +
                    "VALUES " +
                    "  (?, ?, ?)");
            stmtInsert.setString(1, container);
            stmtInsert.setString(2, key);
            stmtInsert.setBytes(3, value);
            stmtInsert.execute();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    public void updateEntry(String container, String key, byte[] value){
        try{
            PreparedStatement stmtUpdate = dbConnection.prepareStatement("" +
                    "UPDATE " +
                    "  settings " +
                    "SET" +
                    "  value = ? " +
                    "WHERE " +
                    "    container = ? " +
                    "  AND " +
                    "    key = ?");
            stmtUpdate.setString(2, container);
            stmtUpdate.setString(3, key);
            stmtUpdate.setBytes(1, value);
            stmtUpdate.executeUpdate();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public void deleteEntry(String container, String key){
        try{
            PreparedStatement stmtDelete = dbConnection.prepareStatement("" +
                    "DELETE FROM" +
                    "  settings " +
                    "WHERE" +
                    "    container = ? " +
                    "  AND" +
                    "    key = ?");
            stmtDelete.setString(1, container);
            stmtDelete.setString(2, key);
            stmtDelete.execute();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    public byte[] getEntry(String container, String key){
        try {
            PreparedStatement stmtSelect = dbConnection.prepareStatement("" +
                    "SELECT " +
                    "  value " +
                    "FROM " +
                    "  settings " +
                    "WHERE " +
                    "    container = ? " +
                    "  AND " +
                    "    key = ?");
            stmtSelect.setString(1, container);
            stmtSelect.setString(2, key);
            ResultSet resultSet = stmtSelect.executeQuery();
            resultSet.next();
            return resultSet.getBytes("value");
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    private synchronized void executeSql(String sql){
        try{
            Statement stmt = dbConnection.createStatement();
            stmt.execute(sql);
            stmt.close();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void dispose() {
        try {
            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}

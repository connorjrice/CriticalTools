package CriticalTools.Util;

import CriticalTools.Objects.Database;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility for reading/writing the Database to a file.
 *
 * @author Connor Rice
 */
public class DatabaseIO {

    public DatabaseIO() {
    }

    public void writeDB(Database db) {
        try {
            try (FileOutputStream fOS = new FileOutputStream("./database.db");
                    ObjectOutputStream out = new ObjectOutputStream(fOS)) {
                out.writeObject(db);
            }
        } catch (IOException ex) {
            Logger.getLogger(DatabaseIO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Database readDB() {
        Database db = null;
        try {
            try (FileInputStream fIS = new FileInputStream("./database.db");
                    ObjectInputStream in = new ObjectInputStream(fIS)) {
                db = (Database) in.readObject();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DatabaseIO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(DatabaseIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return db;
    }
}

package CriticalTools.Util;

import CriticalTools.Objects.Database;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 * Utility for reading/writing the Database to a file.
 *
 * @author Connor Rice
 */
public class DatabaseIO {

    public DatabaseIO() {
    }

    /**
     * Writes an input Database object to a file.
     *
     * @param db
     */
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

    /**
     * Writes an input Database object to a file.
     *
     * @param db
     */
    public void writeDB(Database db, File inputFile) {
        try {
            try (FileOutputStream fOS = new FileOutputStream(inputFile.getAbsolutePath()+"/database.db");
                    ObjectOutputStream out = new ObjectOutputStream(fOS)) {
                out.writeObject(db);
            }
        } catch (IOException ex) {
            Logger.getLogger(DatabaseIO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Reads an existing database file into memory.
     *
     * @return
     */
    public Database readDB() throws IOException, ClassNotFoundException {
        FileInputStream fIS = new FileInputStream("./database.db");
        ObjectInputStream in = new ObjectInputStream(fIS);
        return (Database) in.readObject();
    }

    /**
     * Exports an input Database object using a file selector dialog.
     *
     * @param db
     */
    public void exportDB(Database db) {
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new java.io.File("."));
        fc.setDialogTitle("Choose a directory to write database...");
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fc.setAcceptAllFileFilterUsed(false);
        fc.setVisible(true);
        if (fc.showOpenDialog(fc) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fc.getSelectedFile();
            writeDB(db, selectedFile);
        }

    }
}

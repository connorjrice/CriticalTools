package pagefinder.IO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Parse .ind file containing either 0 for non-measure images, or a string
 * formatted as *,*. This format indicates the beginning and ending measure
 * of a given image.
 * @author Connor Rice
 */
public class INDParse {
    ArrayList<String> db;
    
    public INDParse() {
        db = new ArrayList<>();
        buildDB();
    }    
    
    /**
     * Parses the Arrangement .arr file into an ArrayList<String>.
     */
    private void buildDB() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("Arrangements.arr")))) {
            String line = br.readLine();
            while (line != null) {
                db.add(line);
                line = br.readLine();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ARRParse.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ARRParse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Parses .arr file into an ArrayList<String>
     * @return parsed .arr file
     */
    public ArrayList<String> getDB() {
        return db;
    }
    
    /**
     * Returns the size of the index database.
     * @return size of index database.
     */
    public int getDBSize() {
        return db.size();
    }
    
}

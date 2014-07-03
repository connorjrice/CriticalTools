package pagefinder.IO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Connor Rice
 */
public class INDParse {
    ArrayList<String> db;
    
    public INDParse() {
        db = new ArrayList<>();
        buildDB();
    }    
    
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
    
    public int[] getDB() {
        int[] db = new int[getDBSize()];
        return db;
    }
    
    public int getDBSize() {
        return 0;
    }
    
}

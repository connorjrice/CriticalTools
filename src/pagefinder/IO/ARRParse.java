package pagefinder.IO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pagefinder.Objects.Arrangement;

/**
 *
 * @author Connor Rice
 */
public class ARRParse {
    
    private ArrayList<String> arrangements;
    
    public ARRParse() {
        arrangements = new ArrayList<>();
        parseARR();
    }
    
    private void parseARR() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("Arrangements.arr")))) {
            String line = br.readLine();

            while (line != null) {
                arrangements.add(line);
                line = br.readLine();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ARRParse.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ARRParse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Arrangement[] getARR() {
        Arrangement[] arr = new Arrangement[arrangements.size()];
        for (int i = 0; i < arrangements.size(); i++) {
            String[] curarr = arrangements.get(i).split(",");
            arr[i] = new Arrangement(curarr[0], curarr[1], curarr[2]);
        }
        return arr;
    }
    
}

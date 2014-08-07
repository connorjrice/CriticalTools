package CriticalTools.Objects;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Database object that contains all of the information/image data that the user
 * has in-processed.
 *
 * @author Connor Rice
 */
public class Database implements Serializable {

    private ArrayList<ArrayList<ImageData>> imageDataList;
    private String[] imageStrings;
    private ArrayList<String> arrangementNames;

    public Database(ArrayList<ArrayList<ImageData>> id, String[] is, ArrayList<String> an) {
        this.imageDataList = id;
        this.imageStrings = is;
        this.arrangementNames = an;
    }

    public ArrayList<ArrayList<ImageData>> getAllImageData() {
        return imageDataList;
    }
    
    public ArrayList<ImageData> getImageData(int index) {
        return imageDataList.get(index);
    }

    public String[] getImageStrings() {
        return imageStrings;
    }

    public ArrayList<String> getArrangementNames() {
        return arrangementNames;
    }
    
    public String[] getArrangementNamesStr() {
        String[] arrStr = new String[arrangementNames.size()];
        for (int i = 0; i < arrangementNames.size(); i++) {
            arrStr[i] = arrangementNames.get(i);
        }
        return arrStr;
    }
    
    
    
}

package CriticalTools.Objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Database object that contains all of the information/image data that the user
 * has in-processed.
 *
 * @author Connor Rice
 */
public class Database implements Serializable {

    private ArrayList<ArrayList<ImageData>> imageDataList;
    private HashMap arrangementIndices;
    private String[] imageStrings;
    private String[] arrangementNames;

    public Database(ArrayList<ArrayList<ImageData>> id, String[] is, String[] an, HashMap ai) {
        this.imageDataList = id;
        this.imageStrings = is;
        this.arrangementNames = an;
        this.arrangementIndices = ai;
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

    public String[] getArrangementNames() {
        return arrangementNames;
    }
    
    public HashMap getArrangementMap() {
        return arrangementIndices;
    }
    
    public int getArrangementIndex(String s) {
        return Integer.parseInt((String)arrangementIndices.get(s));
    }
    
}

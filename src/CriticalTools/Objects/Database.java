package CriticalTools.Objects;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Connor Rice
 */
public class Database implements Serializable {
    private ArrayList<ImageData> imageDataList;
    private String[] imageStrings;
    
    public Database(ArrayList<ImageData> id, String[] is) {
        this.imageDataList = id;
        this.imageStrings = is;
    }
    
    
    public ArrayList<ImageData> getImageData() {
        return imageDataList;
    }
    
    public String[] getImageStrings() {
        return imageStrings;
    }
    
    
}

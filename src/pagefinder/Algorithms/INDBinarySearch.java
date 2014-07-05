package pagefinder.Algorithms;

import java.util.ArrayList;

/**
 * Binary search for parsed .ind files.
 * @author Connor Rice
 */
public class INDBinarySearch {
    private ArrayList<String> indDB;
    
    public INDBinarySearch(ArrayList<String> indDB) {
        this.indDB = indDB;
    }
    

    /**
     * Recusive method that takes in a measure and returns the corresponding
     * page that the measure is located on.
     * @param index
     * @param found
     * @return 
     */
    public int binarySearch(int index) {
        int[] parsedIND = parseINDString(index);
        if (parsedIND[0] <= index && index <= parsedIND[1]) {
            return index;
        } else {
            if (index > parsedIND[1]) {
                return binarySearch(index+(index/2));
            } else {
                return binarySearch(index-(index/2));
            }
        }
    }
    
    /**
     * Returns a primitive array of integers that represent the starting and
     * ending measures of an image.
     * @param index
     * @return 
     */
    private int[] parseINDString(int index) {
        int[] parsedIND = new int[2];
        if (!indDB.get(index).equals("0")) {
            String[] sarr = indDB.get(index).split(",");
            for (int i = 0; i < sarr.length; i++) {
                parsedIND[i] = Integer.parseInt(sarr[i]);
            }
            return parsedIND;
        } else {
            return parsedIND;
        }
    }
    
}

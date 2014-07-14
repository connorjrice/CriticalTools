package CriticalTools.Algorithms;

import java.util.ArrayList;
import CriticalTools.Objects.SearchResult;

/**
 * Binary search for parsed .ind files.
 * @author Connor Rice
 */
public class INDBinarySearch {
    private ArrayList<String> indDB;
    
    public INDBinarySearch(ArrayList<String> indDB) {
        this.indDB = indDB;
    }
    
    public SearchResult binarySearch(int measure) {
        return binarySearchHelper(indDB.size()/2, measure);
    }
    
    /**
     * Recusive method that takes in a measure and returns the corresponding
     * page that the measure is located on.
     * @param index
     * @param found
     * @return 
     */
    private SearchResult binarySearchHelper(int index, int measure) {
        int[] parsedIND = parseINDString(index);
        if (parsedIND[0] <= measure && measure <= parsedIND[1]) {
            return new SearchResult(index, parsedIND);
        } else {
            if (measure > parsedIND[1]) {
                return binarySearchHelper(index+(index/2), measure);
            } else {
                return binarySearchHelper(index-(index/2), measure);
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

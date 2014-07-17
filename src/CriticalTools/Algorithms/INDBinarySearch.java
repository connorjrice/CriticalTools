package CriticalTools.Algorithms;

import CriticalTools.MeasureLocator.PageOps;
import java.util.ArrayList;
import CriticalTools.Objects.SearchResult;

/**
 * Binary search for parsed .ind files.
 * @author Connor Rice
 */
public class INDBinarySearch {
    private ArrayList<String> indDB;
    private PageOps pOps;
    private SearchResult lastResult;
    
    /**
     * Constructor used by PageOps.
     * @param pOps
     * @param indDB 
     */
    public INDBinarySearch(PageOps pOps, ArrayList<String> indDB) {
        this.indDB = indDB;
        this.pOps = pOps;
    }
    
    /**
     * Returns a SearchResult object from a measure to be found in the indDB.
     * @param measure : User's desired measure.
     * @return 
     */
    public SearchResult binarySearch(int measure) {
        lastResult = binarySearchHelper(indDB.size()/2, measure);
        return lastResult;
    }
    
    /**
     * Returns the last result created by the search.
     * @return most recent SearchResult.
     */
    public SearchResult getLastResult() {
        return lastResult;
    }
    
    /**
     * Returns a SearchResult of the next page in the work.
     * @return 
     */
    public SearchResult getNextPage() {
        int nextMeasure = lastResult.getMeasureRange()[1] + 1;
        lastResult = binarySearchHelper(indDB.size()/2, nextMeasure);
        return lastResult;
    }
    
    /**
     * Returns a SearchResult of the previous page in the work.
     * @return 
     */
    public SearchResult getPrevPage() {
        int nextMeasure = lastResult.getMeasureRange()[0] - 1;
        lastResult = binarySearchHelper(indDB.size()/2, nextMeasure);
        return lastResult;
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

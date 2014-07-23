package CriticalTools.Util;

import CriticalTools.Objects.ImageData;
import java.util.ArrayList;
import CriticalTools.Objects.SearchResult;

/**
 * Binary search for parsed .ind files.
 * @author Connor Rice
 */
public class BinarySearch {
    private SearchResult lastResult;
    private ArrayList<ImageData> imageData;
    
    /*public INDBinarySearch(ArrayList<String> indDB) {
        this.indDB = indDB;
    }*/
    
    public BinarySearch(ArrayList<ImageData> imgData) {
        this.imageData = imgData;
    }
    
    /**
     * Returns a SearchResult object from a measure to be found in the indDB.
     * @param measure : User's desired measure.
     * @return 
     */
    public SearchResult binarySearch(int measure) {
        lastResult = binarySearchHelper(imageData.size()/2, measure);
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
        lastResult = binarySearchHelper(imageData.size()/2, nextMeasure);
        return lastResult;
    }
    
    /**
     * Returns a SearchResult of the previous page in the work.
     * @return 
     */
    public SearchResult getPrevPage() {
        int nextMeasure = lastResult.getMeasureRange()[0] - 1;
        lastResult = binarySearchHelper(imageData.size()/2, nextMeasure);
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
        int[] measureRange = getMeasureRange(index);
        if (measureRange[0] <= measure && measure <= measureRange[1]) {
            return new SearchResult(index, measureRange);
        } else {
            if (measure > measureRange[1]) {
                return binarySearchHelper(index+(index/2), measure);
            } else {
                return binarySearchHelper(index-(index/2), measure);
            }
        }
    }
    
    public int[] getMeasureRange(int index) {
        ImageData id = imageData.get(index);
        return new int[] {id.getStartMeasure(), id.getEndMeasure()};
    }
    

    
    
}

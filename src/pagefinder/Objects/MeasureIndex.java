package pagefinder.Objects;

import java.util.ArrayList;
import pagefinder.Algorithms.INDBinarySearch;

/**
 *
 * @author Connor Rice
 */
public class MeasureIndex {
    private INDBinarySearch search;
    
    public MeasureIndex(ArrayList<String> indDB) {
        this.search = new INDBinarySearch(indDB);
    }
    
    /**
     * Take in a measure number as an integer, returns the relevant page
     * number from the index database.
     * @param measureNumber
     * @return 
     */
    public int getPageNumberFromMeasure(int measureNumber) {
        return 0;
    }

    
    
}

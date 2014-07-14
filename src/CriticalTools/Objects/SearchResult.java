package CriticalTools.Objects;

/**
 * Object that contains the relevant page numbers, measure starting and ending
 * numbers of the relevant page numbers.
 * @author Connor Rice
 */
public class SearchResult {
    private int pagenum;
    private int[] measurerange;
    
    public SearchResult(int pagenum, int[] measurerange) {
        this.pagenum = pagenum;
        this.measurerange = measurerange;
    }
    
    public int getPageNum() {
        return pagenum;
    }
    
    public int[] getMeasureRange() {
        return measurerange;
    }
    
}

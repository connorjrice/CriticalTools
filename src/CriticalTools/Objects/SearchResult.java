package CriticalTools.Objects;

/**
 * Object that contains the relevant page numbers, measure starting and ending
 * numbers of the relevant page numbers.
 * @author Connor Rice
 */
public class SearchResult {
    private int pagenum;
    private int[] measurerange;
    private String imgLoc;
    
    public SearchResult(int pagenum, int[] measurerange) {
        this.pagenum = pagenum;
        this.measurerange = measurerange;
    }
    
    public void setImgLoc(String imgLoc) {
        this.imgLoc = imgLoc;
    }
    
    public int getPageNum() {
        return pagenum;
    }
    
    public int[] getMeasureRange() {
        return measurerange;
    }
    
    public String getImgLoc() {
        return imgLoc;
    }
    
}

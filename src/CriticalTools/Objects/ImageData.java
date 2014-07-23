package CriticalTools.Objects;

import java.io.Serializable;

/**
 * Object that holds the data for each page in an arrangement.
 * @author Connor Rice
 */
public class ImageData implements Serializable {
    private int startMeasure;
    private int endMeasure;
    private int pageNumber;
    private String imgType;


    public ImageData(int startMeasure, int endMeasure, int pageNumber, String imgType) {
        this.startMeasure = startMeasure;
        this.endMeasure = endMeasure;
        this.pageNumber = pageNumber;
        this.imgType = imgType;
    }
    
    public int getStartMeasure() {
        return startMeasure;
    }
    
    public int getEndMeasure() {
        return endMeasure;
    }
    
    public int getPageNumber() {
        return pageNumber;
    }
    
    public String getImgType() {
        return imgType;
    }
}

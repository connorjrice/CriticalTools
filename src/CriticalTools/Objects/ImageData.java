package CriticalTools.Objects;

/**
 * Object that holds the data for each page in an arrangement.
 * @author Connor Rice
 */
public class ImageData {
    private final int startMeasure;
    private final int endMeasure;
    private final String imgType;

    public ImageData(int startMeasure, int endMeasure, String imgType) {
        this.startMeasure = startMeasure;
        this.endMeasure = endMeasure;
        this.imgType = imgType;
    }
    
    public int getStartMeasure() {
        return startMeasure;
    }
    
    public int getEndMeasure() {
        return endMeasure;
    }
    
    public String getImgType() {
        return imgType;
    }
}

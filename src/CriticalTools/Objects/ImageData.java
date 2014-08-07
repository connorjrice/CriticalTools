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
    private String arrangementDir;
    private String[] imgNames;


    public ImageData(int startMeasure, int endMeasure, int pageNumber, 
            String[] imgNames, String arrangementDir) {
        this.startMeasure = startMeasure;
        this.endMeasure = endMeasure;
        this.pageNumber = pageNumber;
        this.imgNames = imgNames;
        this.imgType = getImgType(imgNames);
        this.arrangementDir = arrangementDir;
    }
    
            /**
     * Returns the page number from an array of String filenames. Returns -1 if
     * the page numbers do not match.
     *
     * @param fileNames
     * @return
     */
    public int getPageNumber(String[] fileNames) {
        int[] pageArray = new int[fileNames.length];
        for (int i = 0; i < pageArray.length; i++) {
            pageArray[i] = Integer.parseInt(fileNames[i].split("_")[0]);
        }
        boolean isSame = checkPageEqual(pageArray);
        if (isSame) {
            return pageArray[0];
        } else {
            return -1;
        }

    }
    
        /**
     * Takes in an array of fileNames as Strings, and returns a string with the
     * abbreviated type of image. Pages with "Bottom" and "Top" image files are
     * abbreviated as "bt"
     *
     * @param fileNames
     * @return
     */
    public String getImgType(String[] fileNames) {
        String imgType = "";
        for (String s : fileNames) {
            String[] sa = s.split("_");
            imgType += sa[1].charAt(0);
        }
        return imgType;
    }
    
        /**
     * Returns true if the page numbers are equal, false if they are not.
     *
     * @param pageArray
     * @return
     */
    public boolean checkPageEqual(int[] pageArray) {
        boolean result = true;
        for (int i = 1; i < pageArray.length; i++) {
            if (pageArray[i - 1] != pageArray[i]) {
                result = false;
            }
        }
        return result;
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
    
    public String getArrangementDir() {
        return arrangementDir;
    }
    
    public int getNumImages() {
        return imgType.length();
    }
    
    public String[] getImgNames() {
        return imgNames;
    }
    
}

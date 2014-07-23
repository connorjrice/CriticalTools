package CriticalTools.Util;

import CriticalTools.Objects.ImageData;
import java.util.ArrayList;

/**
 * Binary search for ImageData objects from the Database.
 *
 * @author Connor Rice
 */
public class BinarySearch {

    private ArrayList<ImageData> imageData;
    private ImageData lastImage;

    /*public INDBinarySearch(ArrayList<String> indDB) {
     this.indDB = indDB;
     }*/
    public BinarySearch(ArrayList<ImageData> imgData) {
        this.imageData = imgData;
    }

    /**
     * Returns a ImageData object from a measure to be found in the indDB.
     *
     * @param measure : User's desired measure.
     * @return
     */
    public ImageData binarySearch(int measure) {
        System.out.println(imageData.size());
        lastImage = binarySearchHelper(imageData.size() / 2, measure);
        return lastImage;
    }

    /**
     * Returns the last result created by the search.
     *
     * @return most recent ImageData.
     */
    public ImageData getLastResult() {
        return lastImage;
    }

    /**
     * Returns a ImageData of the next page in the work.
     *
     * @return
     */
    public ImageData getNextPage() {
        int nextMeasure = lastImage.getEndMeasure() + 1;
        lastImage = binarySearchHelper(imageData.size() / 2, nextMeasure);
        return lastImage;
    }

    /**
     * Returns a ImageData of the previous page in the work.
     *
     * @return
     */
    public ImageData getPrevPage() {
        int nextMeasure = lastImage.getStartMeasure() - 1;
        lastImage = binarySearchHelper(imageData.size() / 2, nextMeasure);
        return lastImage;
    }

    /**
     * Recusive method that takes in a measure and returns the corresponding
     * page that the measure is located on.
     * TODO: Handling for Stack Overflow/not found
     * @param index
     * @param found
     * @return
     */
    private ImageData binarySearchHelper(int index, int measure) {
        int[] measureRange = getMeasureRange(index);
        if (measureRange[0] <= measure && measure <= measureRange[1]) {
            return imageData.get(index);
        } else {
            if (measure > measureRange[1]) {
                if (index > 0) {
                    return binarySearchHelper(index + (index / 2), measure);
                } else {
                    return binarySearchHelper(1, measure);
                }
            } else {
                if (index < 1) {
                    return binarySearchHelper(index - (index / 2), measure);
                } else {
                    return binarySearchHelper(0, measure);
                }
            }
        }
    }

    public int[] getMeasureRange(int index) {
        ImageData id = imageData.get(index);
        return new int[]{id.getStartMeasure(), id.getEndMeasure()};
    }
}

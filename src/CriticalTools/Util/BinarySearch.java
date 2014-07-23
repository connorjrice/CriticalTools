package CriticalTools.Util;

import CriticalTools.Objects.ImageData;

/**
 * Binary search for ImageData objects from the Database.
 *
 * @author Connor Rice
 */
public class BinarySearch {

    private ImageData[][] imageData;
    private ImageData lastImage;
    private int arrIndex;

    /*public INDBinarySearch(ArrayList<String> indDB) {
     this.indDB = indDB;
     }*/
    public BinarySearch(ImageData[][] imgData, int arrIndex) {
        this.imageData = imgData;
        this.arrIndex = arrIndex;
    }

    /**
     * Returns a ImageData object from a measure to be found in the indDB.
     *
     * @param measure : User's desired measure.
     * @return
     */
    public ImageData binarySearch(int measure) {
        lastImage = binarySearchHelper(imageData.length / 2, measure);
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
        lastImage = binarySearchHelper(imageData.length / 2, nextMeasure);
        return lastImage;
    }

    /**
     * Returns a ImageData of the previous page in the work.
     *
     * @return
     */
    public ImageData getPrevPage() {
        int nextMeasure = lastImage.getStartMeasure() - 1;
        lastImage = binarySearchHelper(imageData.length / 2, nextMeasure);
        return lastImage;
    }

    /**
     * Recusive method that takes in a measure and returns the corresponding
     * page that the measure is located on.
     *
     * @param index
     * @param found
     * @return
     */
    private ImageData binarySearchHelper(int index, int measure) {
        int[] measureRange = getMeasureRange(index);
        if (measureRange[0] <= measure && measure <= measureRange[1]) {
            return imageData[arrIndex][index];
        } else {
            if (measure > measureRange[1]) {
                return binarySearchHelper(index + (index / 2), measure);
            } else {
                return binarySearchHelper(index - (index / 2), measure);
            }
        }
    }

    public int[] getMeasureRange(int index) {
        ImageData id = imageData[arrIndex][index];
        return new int[]{id.getStartMeasure(), id.getEndMeasure()};
    }
}

package CriticalTools.Objects;

import java.io.Serializable;

/**
 * Database object that contains all of the information/image data that the user
 * has in-processed.
 *
 * @author Connor Rice
 */
public class Database implements Serializable {

    private ImageData[][] imageDataList;
    private String[] imageStrings;
    private String[] arrangementNames;

    public Database(ImageData[][] id, String[] is, String[] an) {
        this.imageDataList = id;
        this.imageStrings = is;
        this.arrangementNames = an;
    }

    public ImageData[][] getImageData() {
        return imageDataList;
    }

    public String[] getImageStrings() {
        return imageStrings;
    }

    public String[] getArrangementNames() {
        return arrangementNames;
    }
}

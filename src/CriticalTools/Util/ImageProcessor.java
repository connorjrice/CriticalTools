package CriticalTools.Util;

import CriticalTools.ImageProcessing.ImageProcessingDialog;
import CriticalTools.ImageProcessing.ImageProcessingMainForm;
import CriticalTools.Objects.Database;
import CriticalTools.Objects.ImageData;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author Connor Rice
 */
public class ImageProcessor {
    
    private JFileChooser fc;
    private ArrayList<ImageData> imgDataList;
    private DatabaseIO dataIO;
    private String[] imageStrings;
    private String[] arrangementNames;
    private ImageProcessingMainForm parentFrame;
    
    public ImageProcessor(ImageProcessingMainForm c) {
        this.dataIO = new DatabaseIO();
        this.imgDataList = new ArrayList<>();
        this.parentFrame = c;
    }
    
    /**
     * Displays a JFileChooser that lets the user choose the directory with the
     * images to be in-processed.
     */
    public void openDirectory() {
        fc = new JFileChooser();
        fc.setCurrentDirectory(new java.io.File("."));
        fc.setDialogTitle("Choose a directory with image files...");
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fc.setAcceptAllFileFilterUsed(false);
        fc.setVisible(true);
        if (fc.showOpenDialog(parentFrame) == JFileChooser.APPROVE_OPTION) {
            this.imageStrings = parseImgNames(fc.getSelectedFile());
        }
    }

    /**
     * Processes the image files that the user chooses from openDirectory()
     *
     * @param inFile
     * @return
     */
    private String[] parseImgNames(File inFile) {
        String[] imgStingTemp = new String[0];
        if (inFile.isDirectory()) {
            File[] curDirectory = inFile.listFiles();
            imgStingTemp = new String[curDirectory.length];
            for (int i = 0; i < curDirectory.length; i++) {
                imgStingTemp[i] = curDirectory[i].getName();
            }
        }
        return imgStingTemp;
    }

    public void saveDB() {
        Database db = new Database(imgDataList, imageStrings, arrangementNames);
        dataIO.writeDB(db);
    }

    public void loadDB() {
        Database db = dataIO.readDB();
        this.imgDataList = db.getImageData();
        this.imageStrings = db.getImageStrings();
        this.arrangementNames = db.getArrangementNames();
    }
    
    public String[] getListData() {
        return imageStrings;
    }

    /**
     * Creates a new ImageData object which is added to the ArrayList
     *
     * @param pageInts
     * @param imgType
     */
    public void addPage(int[] pageInts, String imgType, String arrangementDir) {
        ImageData id = createImageData(pageInts[0], pageInts[1], pageInts[2], imgType, arrangementDir);
        imgDataList.add(id);
    }

    private int getArrangementIndex() {
        return 0;
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

    /**
     * Creates a popup dialog for processing images.
     */
    public void createProcessingDialog() {
        List selectedValues = parentFrame.getImageList().getSelectedValuesList();
        String[] selectedString = new String[selectedValues.size()];
        for (int i = 0; i < selectedValues.size(); i++) {
            selectedString[i] = (String) selectedValues.get(i);
        }
        JFrame IPD = new ImageProcessingDialog(parentFrame, getImgType(selectedString), getPageNumber(selectedString));
        IPD.setVisible(true);
    }
    
    public ArrayList<ImageData> getImageDataList() {
        return imgDataList;
    }

    /**
     * Returns a new ImageData object.
     *
     * @param startMeasure
     * @param endMeasure
     * @param pageNumber
     * @param imgType
     * @return
     */
    public ImageData createImageData(int startMeasure, int endMeasure,
            int pageNumber, String imgType, String arrangementDir) {
        return new ImageData(startMeasure, endMeasure, pageNumber, imgType, arrangementDir);
    }
}
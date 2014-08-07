package CriticalTools.Util;

import CriticalTools.CommonForms.ErrorForm;
import CriticalTools.ImageProcessing.ImageProcessingDialog;
import CriticalTools.ImageProcessing.ImageProcessingMainForm;
import CriticalTools.Objects.Database;
import CriticalTools.Objects.ImageData;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author Connor Rice
 */
public class ImageProcessor {

    private JFileChooser fc;
    private ArrayList<ArrayList<ImageData>> imgDataList;
    private DatabaseIO dataIO;
    private String[] imageStrings;
    private ArrayList<String> arrangementNames;
    private ImageProcessingMainForm parentFrame;
    private Database db;
    private int arrangementIndex;

    public ImageProcessor(ImageProcessingMainForm c) {
        this.dataIO = new DatabaseIO();
        this.imgDataList = new ArrayList<>();
        this.parentFrame = c;
    }
    
    /**
     * TODO: Add handling for same arrangement name.
     * @param name 
     */
    public void newArrangement(String name) {
        if (arrangementNames != null) {
            System.out.println("here");
            arrangementNames.add(name);
            arrangementIndex = arrangementNames.size()-1;
            imgDataList.add(new ArrayList<ImageData>());
        } else {
            try {
                loadDB();
            } catch (IOException | ClassNotFoundException ex) {
                newVars();
                newArrangement(name);
            }
        }
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
    
    public String[] getImageStrings() {
        return imageStrings;
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
    
    private void newVars() {
        this.imgDataList = new ArrayList<>();
        this.imageStrings = new String[10];
        this.arrangementNames = new ArrayList<>();
    }
    
    private void createDB() {
        db = new Database(imgDataList, arrangementNames);
    }

    public void saveDB() {
        createDB();
        dataIO.writeDB(db);
    }

    public void loadDB() throws IOException, ClassNotFoundException {
        this.db = dataIO.readDB();
        this.imgDataList = db.getAllImageData();
        this.arrangementNames = db.getArrangementNames();
    }
    
    public String[] getArrangementNames(){
        return db.getArrangementNamesStr();
    }
    
    
    /**
     * Creates a new ImageData object which is added to the ArrayList
     *
     * @param pageInts
     * @param imgType
     */
    public void addPage(int[] pageInts, String[] selectedString) {
        ImageData id = createImageData(pageInts[0], pageInts[1], pageInts[2], selectedString, null);
        if (getPageExists(pageInts[2]-1)) {
            imgDataList.get(getArrangementIndex()).set(pageInts[2]-1, id);
        } else {
            imgDataList.get(getArrangementIndex()).add(id);
        }
    }
   
    
    private boolean getPageExists(int index) {
        if (index < imgDataList.get(arrangementIndex).size()) {
            return imgDataList.get(arrangementIndex).get(index) != null;
        } else {
            return false;
        }
    }

    public int getArrangementIndex() {
        return arrangementIndex;
    }
    
    public ArrayList<ImageData> getCurrentArrangement() {
        return imgDataList.get(arrangementIndex);
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
        if (getPageNumber(selectedString) != -1) {
            JFrame IPD = new ImageProcessingDialog(parentFrame, selectedString);
            IPD.setVisible(true);
        } else {
            new ErrorForm("Page numbers not equal.", parentFrame).setVisible(true);
        }

    }

    public ArrayList<ArrayList<ImageData>> getImageDataList() {
        return imgDataList;
    }

    /**
     * Returns a new ImageData object.
     * @param startMeasure
     * @param endMeasure
     * @param pageNumber
     * @param imgType
     * @return
     */
    public ImageData createImageData(int startMeasure, int endMeasure,
            int pageNumber, String[] imgNames, String arrangementDir) {
        return new ImageData(startMeasure, endMeasure, pageNumber, imgNames, arrangementDir);
    }
}

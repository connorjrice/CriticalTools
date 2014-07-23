package CriticalTools.Util;

import CriticalTools.CommonForms.ImageForm;
import CriticalTools.CommonForms.ErrorForm;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import CriticalTools.MeasureFinder.ImageUtilsForm;
import CriticalTools.MeasureFinder.MeasureMainForm;
import CriticalTools.Objects.Database;
import CriticalTools.Objects.ImageData;
import java.awt.Component;

/**
 * Methods for operations to be used by jFrames.
 * @author Connor Rice
 */
public class PageOps {

    private BinarySearch binarySearch;
    private ImageUtilsForm utilFrame;
    private ImageData curData;
    private ImageForm imgForm;
    private Component c;
    private DatabaseIO dataIO;
    private Database database;

    public PageOps(Component c) {
        this.c = c;
        new MeasureMainForm(this, c).setVisible(true);
        this.dataIO = new DatabaseIO();
        this.database = dataIO.readDB();
        binarySearch = new BinarySearch(database.getImageData(), 0);
    }

    /**
     * 
     * @return the parsed Model for arrangements.
     */
    public DefaultComboBoxModel parseArr() {
        return getComboBoxModel();
    }
    
    private void createUtilsForm(ImageData id) {
        utilFrame = new ImageUtilsForm(this, id);
        utilFrame.setVisible(true);
        int uw = imgForm.getX() - utilFrame.getWidth();
        int uy = imgForm.getY() / 2 + utilFrame.getHeight();
        utilFrame.setLocation(uw, uy);
    }
    
    public ImageData nextImage() {
        destroyImageForm();
        try {
            curData = binarySearch.getNextPage();
            newImageForm();
        } catch (IOException ex) {
            new ErrorForm("Image file not found.", c).setVisible(true);
            Logger.getLogger(ImageForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return curData;
    }
    
    public ImageData previousImage() {
        destroyImageForm();
        try {
            curData = binarySearch.getPrevPage();
            newImageForm();
        } catch (IOException ex) {
            new ErrorForm("Image file not found.", c).setVisible(true);
            Logger.getLogger(ImageForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return curData;
    }
    
    private void newImageForm() throws IOException {
        imgForm = new ImageForm(curData, c);
        imgForm.setVisible(true);
    }

    public void initialImageOpen(String measureString) {
        try {
            curData = binarySearch.binarySearch(Integer.parseInt(measureString));
            try {
                imgForm = new ImageForm(curData, c);
                imgForm.setVisible(true);
                createUtilsForm(curData);
            } catch (IOException ex) {
                new ErrorForm("Image file not found.", c).setVisible(true);
                Logger.getLogger(ImageForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NumberFormatException ex) {
            new ErrorForm("Please enter a valid measure number.", c).setVisible(true);
            Logger.getLogger(ImageForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void destroyImageForm() {
        imgForm.closeExternal();
    }
    
    
    /*public String getImgLoc() {
        return System.getProperty("user.dir") + System.getProperty("file.separator") + arrangements[0].getDir() + System.getProperty("file.separator") + curData.getPageNum() + ".jpg";
    }*/
    
    public String getImgLoc() {
        return "./RiB_Grofe_Whiteman/" + curData;
    }


    private DefaultComboBoxModel getComboBoxModel() {
        //return new DefaultComboBoxModel(database.getArrangementNames());
        return new DefaultComboBoxModel();
    }
}

package CriticalTools.Util;

import CriticalTools.Exception.MeasureNotFoundException;
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
public class ImageHandler {

    private BinarySearch binarySearch;
    private ImageUtilsForm utilFrame;
    private ImageData curData;
    private ImageForm[] imgForms;
    private Component c;
    private DatabaseIO dataIO;
    private Database database;

    public ImageHandler(Component c) {
        this.c = c;
        new MeasureMainForm(this, c).setVisible(true);
        this.dataIO = new DatabaseIO();
        try {
            this.database = dataIO.readDB();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ImageHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.imgForms = new ImageForm[3];
        binarySearch = new BinarySearch(database.getAllImageData().get(0));
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
        int uw = imgForms[0].getX() - utilFrame.getWidth();
        int uy = imgForms[0].getY() / 2 + utilFrame.getHeight();
        utilFrame.setLocation(uw, uy);
    }
    
    public ImageData nextImage() {
        destroyImageForm();
        try {
            curData = binarySearch.getNextPage();
            newImageForm();
        } catch (IOException | IndexOutOfBoundsException ex) {
            new ErrorForm("Image file not found.", c).setVisible(true);
            Logger.getLogger(ImageForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MeasureNotFoundException ex) {
            new ErrorForm("Measure not found.", c).setVisible(true);
            Logger.getLogger(ImageForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return curData;
    }
    
    public ImageData previousImage() {
        destroyImageForm();
        try {
            curData = binarySearch.getPrevPage();
            newImageForm();
        } catch (IOException | IndexOutOfBoundsException ex) {
            new ErrorForm("Image file not found.", c).setVisible(true);
            Logger.getLogger(ImageForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MeasureNotFoundException ex) {
            new ErrorForm("Measure not found.", c).setVisible(true);
            Logger.getLogger(ImageForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return curData;
    }
    
    private void newImageForm() throws IOException, MeasureNotFoundException {
        if (curData == null) {
            throw new MeasureNotFoundException();
        }
        for (int i = 0; i < curData.getNumImages(); i++) {
            imgForms[i] = new ImageForm(curData, i, c);
            imgForms[i].setVisible(true);
        }

    }

    public void initialImageOpen(String measureString) {
        try {
            curData = binarySearch.binarySearch(Integer.parseInt(measureString));
            try {
                newImageForm();
                createUtilsForm(curData);
            } catch (IOException ex) {
                new ErrorForm("Image file not found.", c).setVisible(true);
                Logger.getLogger(ImageForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NumberFormatException ex) {
            new ErrorForm("Please enter a valid measure number.", c).setVisible(true);
            Logger.getLogger(ImageForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MeasureNotFoundException ex) {
            new ErrorForm("Measure not found.", c).setVisible(true);
            Logger.getLogger(ImageForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void destroyImageForm() {
        for (int i = 0; i < curData.getNumImages(); i++) {
            imgForms[i].closeExternal();            
        }

    }
    
    
    /*public String getImgLoc() {
        return System.getProperty("user.dir") + System.getProperty("file.separator") + arrangements[0].getDir() + System.getProperty("file.separator") + curData.getPageNum() + ".jpg";
    }*/
    
    /**
     * TODO: Actual handling of getImgLoc()
     * @return 
     */
    public String getImgLoc() {
        return "./RiB_Grofe_Whiteman/" + curData;
    }


    private DefaultComboBoxModel getComboBoxModel() {
        //return new DefaultComboBoxModel(database.getArrangementNames());
        return new DefaultComboBoxModel();
    }
}

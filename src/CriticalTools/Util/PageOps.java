package CriticalTools.Util;

import CriticalTools.CommonForms.ImageForm;
import CriticalTools.CommonForms.ErrorForm;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import CriticalTools.MeasureFinder.ImageUtilsForm;
import CriticalTools.MeasureFinder.MeasureMainForm;
import CriticalTools.Objects.Arrangement;
import CriticalTools.Objects.SearchResult;
import java.awt.Component;

/**
 * Methods for operations to be used by jFrames.
 * @author Connor Rice
 */
public class PageOps {

    private Arrangement[] arrangements;
    private BinarySearch binarySearch;
    private SearchResult result;
    private ImageUtilsForm utilFrame;
    private ImageForm imgForm;
    private Component c;
    private DatabaseIO dataIO;

    public PageOps(Component c) {
        this.c = c;
        new MeasureMainForm(this, c).setVisible(true);
        this.dataIO = new DatabaseIO();
        binarySearch = new BinarySearch(dataIO.readDB().getImageData());
    }

    /**
     * 
     * @return the parsed Model for arrangements.
     */
    public DefaultComboBoxModel parseArr() {
        return getComboBoxModel();
    }
    
    private void createUtilsForm(SearchResult searchResult) {
        utilFrame = new ImageUtilsForm(this, searchResult);
        utilFrame.setVisible(true);
        int uw = imgForm.getX() - utilFrame.getWidth();
        int uy = imgForm.getY() / 2 + utilFrame.getHeight();
        utilFrame.setLocation(uw, uy);
    }
    
    public SearchResult nextImage() {
        destroyImageForm();
        try {
            result = binarySearch.getNextPage();
            result.setImgLoc(getImgLoc());
            newImageForm();
        } catch (IOException ex) {
            new ErrorForm("Image file not found.", c).setVisible(true);
            Logger.getLogger(ImageForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public SearchResult previousImage() {
        destroyImageForm();
        try {
            result = binarySearch.getPrevPage();
            result.setImgLoc(getImgLoc());
            newImageForm();
        } catch (IOException ex) {
            new ErrorForm("Image file not found.", c).setVisible(true);
            Logger.getLogger(ImageForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    private void newImageForm() throws IOException {
        imgForm = new ImageForm(result, c);
        imgForm.setVisible(true);
    }

    private void parseInd() {
    }

    public void initialImageOpen(String measureString) {
        try {
            result = binarySearch.binarySearch(Integer.parseInt(measureString));
            result.setImgLoc(getImgLoc());
            try {
                imgForm = new ImageForm(result, c);
                imgForm.setVisible(true);
                createUtilsForm(result);
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
        return System.getProperty("user.dir") + System.getProperty("file.separator") + arrangements[0].getDir() + System.getProperty("file.separator") + result.getPageNum() + ".jpg";
    }*/
    
    public String getImgLoc() {
        return "./RiB_Grofe_Whiteman/" + result;
    }


    private DefaultComboBoxModel getComboBoxModel() {
        String[] model = new String[arrangements.length];
        for (int i = 0; i < arrangements.length; i++) {
            model[i] = arrangements[i].getName();
        }
        return new DefaultComboBoxModel(model);
    }
}

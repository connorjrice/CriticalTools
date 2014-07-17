package CriticalTools.MeasureLocator;

import CriticalTools.CommonForms.ErrorForm;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import CriticalTools.Algorithms.INDBinarySearch;
import CriticalTools.IO.ARRParse;
import CriticalTools.IO.INDParse;
import CriticalTools.Objects.Arrangement;
import CriticalTools.Objects.SearchResult;
import java.awt.Component;

/**
 * Methods for operations to be used by jFrames.
 * @author Connor Rice
 */
public class PageOps {

    private Arrangement[] arrangements;
    private int[] measureDB;
    private PageOps pOps;
    private int pageNumber;
    private INDBinarySearch ibs;
    private SearchResult result;
    private ImageUtilsForm utilFrame;
    private ImageForm imgForm;
    private Component c;

    public PageOps(Component c) {
        this.c = c;
        arrangements = new ARRParse().getARR();
        new MeasureMainForm(this, c).setVisible(true);
        ibs = new INDBinarySearch(this, new INDParse().getDB());
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

    private void parseInd() {
    }

    public void openImages(String measureString) {
        try {
            result = ibs.binarySearch(Integer.parseInt(measureString));
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

    
    public String getImgLoc() {
        return System.getProperty("user.dir") + System.getProperty("file.separator") + arrangements[0].getDir() + System.getProperty("file.separator") + result.getPageNum() + ".jpg";
    }

    private String parseMeasureNumber() {
        String converted = "";
        return converted;
    }

    private DefaultComboBoxModel getComboBoxModel() {
        String[] model = new String[arrangements.length];
        for (int i = 0; i < arrangements.length; i++) {
            model[i] = arrangements[i].getName();
        }
        return new DefaultComboBoxModel(model);
    }
}

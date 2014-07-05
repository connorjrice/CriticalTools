package pagefinder;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import pagefinder.Algorithms.INDBinarySearch;
import pagefinder.GUI.ErrorForm;
import pagefinder.GUI.ImageForm;
import pagefinder.GUI.MainForm;
import pagefinder.IO.ARRParse;
import pagefinder.IO.INDParse;
import pagefinder.Objects.Arrangement;
import pagefinder.Objects.SearchResult;

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

    public PageOps() {
        arrangements = new ARRParse().getARR();
        new MainForm(this).setVisible(true);
        ibs = new INDBinarySearch(new INDParse().getDB());

    }

    /**
     * 
     * @return the parsed Model for arrangements.
     */
    public DefaultComboBoxModel parseArr() {
        return getComboBoxModel();
    }

    private void parseInd() {
    }

    public void openImages(String measureString) {
        try {
            result = ibs.binarySearch(Integer.parseInt(measureString));
            try {
                new ImageForm(this, result).setVisible(true);
            } catch (IOException ex) {
                new ErrorForm("Image file not found.").setVisible(true);
                Logger.getLogger(ImageForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NumberFormatException ex) {
            new ErrorForm("Please enter a valid measure number.").setVisible(true);
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

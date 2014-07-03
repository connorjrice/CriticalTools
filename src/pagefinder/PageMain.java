package pagefinder;

import pagefinder.GUI.MainForm;
import pagefinder.IO.ARRParse;

/**
 *
 * @author Connor Rice
 */
public class PageMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new MainForm(new ARRParse().getARR()).setVisible(true);
    }
}

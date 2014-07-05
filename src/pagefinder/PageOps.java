package pagefinder;

import pagefinder.GUI.MainForm;
import pagefinder.IO.ARRParse;

/**
 *
 * @author Connor Rice
 */
public class PageOps {
    
    public PageOps() {
        new MainForm(new ARRParse().getARR()).setVisible(true);
    }
    
}

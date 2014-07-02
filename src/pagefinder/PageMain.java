/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pagefinder;

import pagefinder.GUI.MainForm;
import pagefinder.IO.ARRParse;

/**
 *
 * @author Development
 */
public class PageMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new MainForm(new ARRParse().getARR()).setVisible(true);
    }
}

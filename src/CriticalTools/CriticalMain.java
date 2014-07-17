package CriticalTools;

import CriticalTools.MainGUI.MainGUI;

/**
 * Critical Tools: A suite of applications that are designed to aid in the 
 * process of authoring a Musical Critical Edition.
 * 
 * TODO: Database Viewer
 * TODO: Measure comparison
 * TODO: Image processing
 * TODO: Incorporate edited images
 * TODO: Create new .ind file for Whiteman
 * TODO: Create .ind file for Fair Copy
 * TODO: Create .ind file for Ink Score
 * TODO: Implement image searching based upon instrument
 * TODO: Serializable databases
 * @author Connor Rice
 */
public class CriticalMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new MainGUI().setVisible(true);
    }
}

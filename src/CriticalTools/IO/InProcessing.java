package CriticalTools.IO;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

/**
 *
 * @author Development
 */
public class InProcessing extends JPanel {
    private JFileChooser fc;
    
    public InProcessing() {}
    
    public void openDirectory() {
        fc = new JFileChooser();
        fc.setCurrentDirectory(new java.io.File("."));
        fc.setDialogTitle("Choose a directory with image files...");
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fc.setAcceptAllFileFilterUsed(false);
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            System.out.println(fc.getCurrentDirectory());
        }
    }
    
}

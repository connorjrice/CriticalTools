package CriticalTools.ImageProcessing;

import CriticalTools.CommonForms.QuitForm;
import CriticalTools.Util.DatabaseIO;
import CriticalTools.Objects.Database;
import CriticalTools.Objects.ImageData;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * Builds a list of files from a given directory. TODO: Move operations into
 * Util package Main form for Image Processing.
 *
 * @author Connor Rice
 */
public class ImageProcessingMainForm extends javax.swing.JFrame {

    private JFileChooser fc;
    private ArrayList<ImageData> imgDataList;
    private DatabaseIO dataIO;
    private String[] imageStrings;
    private String[] arrangementNames;

    /**
     * Creates new form ImageProcessingMainForm
     */
    public ImageProcessingMainForm(Component c) {
        initComponents();
        setLocationRelativeTo(c);
        this.dataIO = new DatabaseIO();
        this.imgDataList = new ArrayList<>();
        //loadDB();
    }

    /**
     * Displays a JFileChooser that lets the user choose the directory with the
     * images to be in-processed.
     */
    private void openDirectory() {
        fc = new JFileChooser();
        fc.setCurrentDirectory(new java.io.File("."));
        fc.setDialogTitle("Choose a directory with image files...");
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fc.setAcceptAllFileFilterUsed(false);
        fc.setVisible(true);
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            this.imageStrings = parseImgNames(fc.getSelectedFile());
            imageList.setListData(imageStrings);
        }
    }

    /**
     * Processes the image files that the user chooses from openDirectory()
     *
     * @param inFile
     * @return
     */
    private String[] parseImgNames(File inFile) {
        String[] imgStingTemp = new String[0];
        if (inFile.isDirectory()) {
            File[] curDirectory = inFile.listFiles();
            imgStingTemp = new String[curDirectory.length];
            for (int i = 0; i < curDirectory.length; i++) {
                imgStingTemp[i] = curDirectory[i].getName();
            }
        }
        return imgStingTemp;
    }

    protected void saveDB() {
        Database db = new Database(imgDataList, imageStrings, arrangementNames);
        dataIO.writeDB(db);
    }

    protected final void loadDB() {
        Database db = dataIO.readDB();
        this.imgDataList = db.getImageData();
        this.imageStrings = db.getImageStrings();
        this.arrangementNames = db.getArrangementNames();
        imageList.setListData(imageStrings);
    }

    /**
     * Creates a new ImageData object which is added to the ArrayList
     *
     * @param pageInts
     * @param imgType
     */
    public void addPage(int[] pageInts, String imgType, String arrangementDir) {
        ImageData id = createImageData(pageInts[0], pageInts[1], pageInts[2], imgType, arrangementDir);
        imgDataList.add(id);
    }

    private int getArrangementIndex() {
        return 0;
    }

    /**
     * Takes in an array of fileNames as Strings, and returns a string with the
     * abbreviated type of image. Pages with "Bottom" and "Top" image files are
     * abbreviated as "bt"
     *
     * @param fileNames
     * @return
     */
    public String getImgType(String[] fileNames) {
        String imgType = "";
        for (String s : fileNames) {
            String[] sa = s.split("_");
            imgType += sa[1].charAt(0);
        }
        return imgType;
    }

    /**
     * Returns the page number from an array of String filenames. Returns -1 if
     * the page numbers do not match.
     *
     * @param fileNames
     * @return
     */
    public int getPageNumber(String[] fileNames) {
        int[] pageArray = new int[fileNames.length];
        for (int i = 0; i < pageArray.length; i++) {
            pageArray[i] = Integer.parseInt(fileNames[i].split("_")[0]);
        }
        boolean isSame = checkPageEqual(pageArray);
        if (isSame) {
            return pageArray[0];
        } else {
            return -1;
        }

    }

    /**
     * Returns true if the page numbers are equal, false if they are not.
     *
     * @param pageArray
     * @return
     */
    public boolean checkPageEqual(int[] pageArray) {
        boolean result = true;
        for (int i = 1; i < pageArray.length; i++) {
            if (pageArray[i - 1] != pageArray[i]) {
                result = false;
            }
        }
        return result;
    }

    /**
     * Creates a popup dialog for processing images.
     */
    public void createProcessingDialog() {
        List selectedValues = imageList.getSelectedValuesList();
        String[] selectedString = new String[selectedValues.size()];
        for (int i = 0; i < selectedValues.size(); i++) {
            selectedString[i] = (String) selectedValues.get(i);
        }
        JFrame IPD = new ImageProcessingDialog(this, getImgType(selectedString), getPageNumber(selectedString));
        IPD.setVisible(true);
    }

    /**
     * Returns a new ImageData object.
     *
     * @param startMeasure
     * @param endMeasure
     * @param pageNumber
     * @param imgType
     * @return
     */
    public ImageData createImageData(int startMeasure, int endMeasure,
            int pageNumber, String imgType, String arrangementDir) {
        return new ImageData(startMeasure, endMeasure, pageNumber, imgType, arrangementDir);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        imageList = new javax.swing.JList();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openDirectoryItem = new javax.swing.JMenuItem();
        openExistingDBItem = new javax.swing.JMenuItem();
        saveDataItem = new javax.swing.JMenuItem();
        quitButton = new javax.swing.JMenuItem();
        parseMenu = new javax.swing.JMenu();
        autoProcess = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Critical Tools - Image Processing");

        imageList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imageListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(imageList);

        fileMenu.setText("File");
        fileMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileMenuActionPerformed(evt);
            }
        });

        openDirectoryItem.setText("Open directory...");
        openDirectoryItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openDirectoryItemActionPerformed(evt);
            }
        });
        fileMenu.add(openDirectoryItem);

        openExistingDBItem.setText("Open existing database");
        openExistingDBItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openExistingDBItemActionPerformed(evt);
            }
        });
        fileMenu.add(openExistingDBItem);

        saveDataItem.setText("Save...");
        saveDataItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveDataItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveDataItem);

        quitButton.setText("Quit");
        quitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitButtonActionPerformed(evt);
            }
        });
        fileMenu.add(quitButton);

        jMenuBar1.add(fileMenu);

        parseMenu.setText("Parse");

        autoProcess.setText("Automatic Processing");
        parseMenu.add(autoProcess);

        jMenuBar1.add(parseMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 770, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Action for Quit Button
     *
     * @param evt
     */
    private void quitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitButtonActionPerformed
        new QuitForm(this).setVisible(true);
    }//GEN-LAST:event_quitButtonActionPerformed

    private void fileMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileMenuActionPerformed
    }//GEN-LAST:event_fileMenuActionPerformed

    private void openDirectoryItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openDirectoryItemActionPerformed
        openDirectory();
    }//GEN-LAST:event_openDirectoryItemActionPerformed

    private void imageListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imageListMouseClicked
        if (evt.getButton() == (MouseEvent.BUTTON3)) {
            createProcessingDialog();
        }
    }//GEN-LAST:event_imageListMouseClicked

    private void openExistingDBItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openExistingDBItemActionPerformed
        loadDB();
    }//GEN-LAST:event_openExistingDBItemActionPerformed

    private void saveDataItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveDataItemActionPerformed
        saveDB();
    }//GEN-LAST:event_saveDataItemActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem autoProcess;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JList imageList;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem openDirectoryItem;
    private javax.swing.JMenuItem openExistingDBItem;
    private javax.swing.JMenu parseMenu;
    private javax.swing.JMenuItem quitButton;
    private javax.swing.JMenuItem saveDataItem;
    // End of variables declaration//GEN-END:variables
}

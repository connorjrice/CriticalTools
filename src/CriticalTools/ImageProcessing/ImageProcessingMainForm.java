package CriticalTools.ImageProcessing;

import CriticalTools.CommonForms.QuitForm;
import CriticalTools.Util.ImageProcessor;
import java.awt.Component;
import java.awt.event.MouseEvent;
import javax.swing.JList;

/**
 *
 * Builds a list of files from a given directory. Main form for Image
 * Processing.
 *
 * @author Connor Rice
 */
public class ImageProcessingMainForm extends javax.swing.JFrame {

    private ImageProcessor imageProcessor;

    /**
     * Creates new form ImageProcessingMainForm
     */
    public ImageProcessingMainForm(Component c) {
        initComponents();
        setLocationRelativeTo(c);
        this.imageProcessor = new ImageProcessor(this);
    }

    public ImageProcessor getImageProcessor() {
        return imageProcessor;
    }

    public JList getImageList() {
        return imageList;
    }
    
    public void newArrangementDir(String arrangementName) {
        imageProcessor.newArrangement(arrangementName);
        imageProcessor.openDirectory();
        imageList.setListData(imageProcessor.getListData());
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
        openExistingDBItem = new javax.swing.JMenuItem();
        newArrangement = new javax.swing.JMenuItem();
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

        openExistingDBItem.setText("Open existing database");
        openExistingDBItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openExistingDBItemActionPerformed(evt);
            }
        });
        fileMenu.add(openExistingDBItem);

        newArrangement.setText("New arrangement...");
        newArrangement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newArrangementActionPerformed(evt);
            }
        });
        fileMenu.add(newArrangement);

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

    private void newArrangementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newArrangementActionPerformed
        new NewArrangementDialog(this).setVisible(true);
    }//GEN-LAST:event_newArrangementActionPerformed

    private void imageListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imageListMouseClicked
        if (evt.getButton() == (MouseEvent.BUTTON3)) {
            imageProcessor.createProcessingDialog();
        }
    }//GEN-LAST:event_imageListMouseClicked

    private void openExistingDBItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openExistingDBItemActionPerformed
        imageProcessor.loadDB();
        imageList.setListData(imageProcessor.getListData());
    }//GEN-LAST:event_openExistingDBItemActionPerformed

    private void saveDataItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveDataItemActionPerformed
        imageProcessor.saveDB();
    }//GEN-LAST:event_saveDataItemActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem autoProcess;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JList imageList;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem newArrangement;
    private javax.swing.JMenuItem openExistingDBItem;
    private javax.swing.JMenu parseMenu;
    private javax.swing.JMenuItem quitButton;
    private javax.swing.JMenuItem saveDataItem;
    // End of variables declaration//GEN-END:variables
}

package CriticalTools.DatabaseManagement;

import CriticalTools.CommonForms.QuitForm;
import CriticalTools.Util.DatabaseIO;
import CriticalTools.Objects.Database;
import CriticalTools.Objects.ImageData;
import java.awt.Component;
import java.util.ArrayList;

/**
 * Database Viewer: Allows the user to browse through the various databases
 * utilized by Critical Tools. TODO: Difference between Image Processing and
 * Database Viewer: IP has the names of the files, DM has pages.
 *
 * @author Connor Rice
 */
public class DatabaseManagementMainForm extends javax.swing.JFrame {

    private DatabaseIO dataIO;
    private ArrayList<ImageData> imgDataList;
    private String[] imageStrings;
    private boolean isArrangementView = true;

    /**
     * Creates new form DatabaseViewerMainForm
     */
    public DatabaseManagementMainForm(Component c) {
        initComponents();
        setLocationRelativeTo(c);
        this.dataIO = new DatabaseIO();
        loadDB();
    }

    private void loadDB() {
        Database db = dataIO.readDB();
        this.imgDataList = db.getImageData();
        this.imageStrings = db.getImageStrings();
        databaseList.setListData(imageStrings);
    }
    
    private void exportDB() {
        dataIO.exportDB(dataIO.readDB());
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
        databaseList = new javax.swing.JList();
        upButton = new javax.swing.JButton();
        infoButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openItem = new javax.swing.JMenuItem();
        saveItem = new javax.swing.JMenuItem();
        exportItem = new javax.swing.JMenuItem();
        quitButton = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Critical Tools - Database Management");

        jScrollPane1.setViewportView(databaseList);

        upButton.setText("Up");

        infoButton.setText("Info");

        fileMenu.setText("File");

        openItem.setText("Open...");
        fileMenu.add(openItem);

        saveItem.setText("Save");
        fileMenu.add(saveItem);

        exportItem.setText("Export...");
        exportItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportItemActionPerformed(evt);
            }
        });
        fileMenu.add(exportItem);

        quitButton.setText("Quit");
        quitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitButtonActionPerformed(evt);
            }
        });
        fileMenu.add(quitButton);

        jMenuBar1.add(fileMenu);

        editMenu.setText("Edit");
        jMenuBar1.add(editMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 781, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(infoButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(upButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(upButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(infoButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void quitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitButtonActionPerformed
        new QuitForm(this).setVisible(true);
    }//GEN-LAST:event_quitButtonActionPerformed

    private void exportItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportItemActionPerformed
        // TODO add your handling code here:
        exportDB();
    }//GEN-LAST:event_exportItemActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList databaseList;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exportItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JButton infoButton;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem openItem;
    private javax.swing.JMenuItem quitButton;
    private javax.swing.JMenuItem saveItem;
    private javax.swing.JButton upButton;
    // End of variables declaration//GEN-END:variables
}

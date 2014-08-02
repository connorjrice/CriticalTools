package CriticalTools.ImageProcessing;

import CriticalTools.Objects.ImageData;
import CriticalTools.Util.ImageProcessor;
import java.util.ArrayList;

/**
 * Dialog box for in-processing images into the database.
 * @author Connor Rice
 */
public class ImageProcessingDialog extends javax.swing.JFrame {

    private ImageProcessingMainForm parentFrame;

    /**
     * Creates new form ImageProcessingDialog
     */
    public ImageProcessingDialog(ImageProcessingMainForm parentFrame, String imgType, int pageNum) {
        initComponents();
        setLocationRelativeTo(parentFrame);
        setResizable(false);
        this.parentFrame = parentFrame;
        setTextFields(imgType, pageNum);
    }
    
    private void setTextFields(String imgType, int pageNum) {
        ImageProcessor ip = parentFrame.getImageProcessor();
        imgTypeField.setText(imgType);
        pageNumField.setText(Integer.toString(pageNum));
        ArrayList<ImageData> imageDataList = ip.getCurrentArrangement();
        if (pageNum-1 < imageDataList.size()) {
            startingMeasureField.setText(Integer.toString(
                    imageDataList.get(pageNum-1).getStartMeasure()));
            endingMeasureField.setText(Integer.toString(
                    imageDataList.get(pageNum-1).getEndMeasure()));
        }
        //arrangementNameLabel.setText(parentFrame.getImageProcessor().);
        arrangementIndexLabel.setText(Integer.toString(ip.getArrangementIndex()));
    } 

    /**
     * Returns a primitive array of integers containing:
     * 1. Starting Measure
     * 2. Ending Measure
     * 3. Page Number
     * @return 
     */
    public int[] parseImageVars() {
        return new int[]{Integer.parseInt(startingMeasureField.getText()), Integer.parseInt(endingMeasureField.getText()), Integer.parseInt(pageNumField.getText())};
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pageNumLabel = new javax.swing.JLabel();
        imgTypeLabel = new javax.swing.JLabel();
        startMeasureLabel = new javax.swing.JLabel();
        endingMeasureLabel = new javax.swing.JLabel();
        pageNumField = new javax.swing.JTextField();
        imgTypeField = new javax.swing.JTextField();
        startingMeasureField = new javax.swing.JTextField();
        endingMeasureField = new javax.swing.JTextField();
        addDataButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        arrangementLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        arrangementIndexLabel = new javax.swing.JLabel();
        arrangementNameLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add Page...");

        pageNumLabel.setText("Page Number:");

        imgTypeLabel.setText("Image Type:");

        startMeasureLabel.setText("Starting Measure:");

        endingMeasureLabel.setText("Ending Measure:");

        imgTypeField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imgTypeFieldActionPerformed(evt);
            }
        });

        startingMeasureField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startingMeasureFieldActionPerformed(evt);
            }
        });

        addDataButton.setText("Add Page");
        addDataButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addDataButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        arrangementLabel.setText("Arrangement:");

        jLabel1.setText("Name");

        jLabel2.setText("Index");

        arrangementIndexLabel.setText("-1");

        arrangementNameLabel.setText("Whiteman");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addDataButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cancelButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pageNumLabel)
                                    .addComponent(arrangementLabel))
                                .addGap(37, 37, 37)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(0, 66, Short.MAX_VALUE))
                                    .addComponent(pageNumField)
                                    .addComponent(arrangementNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(imgTypeLabel)
                                    .addComponent(startMeasureLabel)
                                    .addComponent(endingMeasureLabel))
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(startingMeasureField)
                                    .addComponent(imgTypeField)
                                    .addComponent(endingMeasureField))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(arrangementIndexLabel)))
                        .addGap(67, 67, 67)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(arrangementLabel)
                    .addComponent(arrangementIndexLabel)
                    .addComponent(arrangementNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pageNumLabel)
                    .addComponent(pageNumField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(imgTypeLabel)
                    .addComponent(imgTypeField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startMeasureLabel)
                    .addComponent(startingMeasureField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(endingMeasureLabel)
                    .addComponent(endingMeasureField))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addDataButton)
                    .addComponent(cancelButton)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void addDataButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addDataButtonActionPerformed
        int[] intA = parseImageVars();
        parentFrame.getImageProcessor().addPage(intA, imgTypeField.getText(), null);
        dispose();
    }//GEN-LAST:event_addDataButtonActionPerformed

    private void imgTypeFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imgTypeFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_imgTypeFieldActionPerformed

    private void startingMeasureFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startingMeasureFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_startingMeasureFieldActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addDataButton;
    private javax.swing.JLabel arrangementIndexLabel;
    private javax.swing.JLabel arrangementLabel;
    private javax.swing.JLabel arrangementNameLabel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField endingMeasureField;
    private javax.swing.JLabel endingMeasureLabel;
    private javax.swing.JTextField imgTypeField;
    private javax.swing.JLabel imgTypeLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField pageNumField;
    private javax.swing.JLabel pageNumLabel;
    private javax.swing.JLabel startMeasureLabel;
    private javax.swing.JTextField startingMeasureField;
    // End of variables declaration//GEN-END:variables
}

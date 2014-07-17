package CriticalTools.MeasureLocator;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import CriticalTools.Objects.SearchResult;
import java.awt.Component;

/**
 * TODO: Move image operations to ImageOps java
 * @author Connor Rice
 */
public class ImageForm extends javax.swing.JFrame {



    private BufferedImage originalImage;
    private BufferedImage currentImage;
    private SearchResult searchResult;

    public ImageForm(SearchResult searchResult, Component c) throws IOException {
        this.searchResult = searchResult;
        initComponents();
        initialScale();
        setLocationRelativeTo(c);
    }

    private void initialScale() throws IOException {
        originalImage = ImageIO.read(new File(searchResult.getImgLoc()));
        jLabel1.setIcon(scaleImage(originalImage));
        this.setBounds(0, 0, currentImage.getWidth(), currentImage.getHeight());
    }

    private ImageIcon scaleImage(BufferedImage in) {
        float[] imgVars = getScaledDimensions(in);
        BufferedImage out = new BufferedImage(round(imgVars[1]), round(imgVars[2]), BufferedImage.TYPE_INT_ARGB);
        Graphics g = out.createGraphics();
        g.drawImage(in, 0, 0, round(imgVars[1]), round(imgVars[2]), null);
        g.dispose();
        currentImage = out;
        return new ImageIcon(out);
    }

    private int round(float n) {
        return Math.round(n);
    }

    private boolean getDimensionThreshold(int width, int height) {
        if (width > height) {
            if (width <= 1440 && height <= 900) {
                return true;
            } else {
                return false;
            }
        } else {
            if (width <= 900 && height <= 1440) {
                return true;
            } else {
                return false;
            }
        }
    }

    private float[] getScaledDimensions(BufferedImage in) {
        float scalingFactor = 1;
        int height = in.getHeight();
        int width = in.getWidth();
        while (!getDimensionThreshold(height, width)) {
            scalingFactor *= .95;
            height *= scalingFactor;
            width *= scalingFactor;
        }
        return new float[]{scalingFactor, width, height};
    }
    
    @Override
    public void dispose() {
        super.dispose();
    }
    
    public void closeExternal() {
        super.dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 400, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 300, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}

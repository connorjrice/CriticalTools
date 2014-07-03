package pagefinder.IO;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import pagefinder.GUI.ImageForm;

/**
 *
 * @author Connor Rice
 */
public class ScaleUtils {
    private String imgloc;
    
    public ScaleUtils(String imgloc) {
        try {
            this.imgloc = imgloc;
            scaleImage();
        } catch (IOException ex) {
            Logger.getLogger(ImageForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void scaleImage() throws IOException {
        //setIcon(getImageIcon(ImageIO.read(getClass().getResource(imgloc))));
    }    
    
    private ImageIcon getImageIcon(BufferedImage in) {
        float[] imgVars = getScaledDimensions(in);
        AffineTransform at = new AffineTransform();
        at.scale(imgVars[0], imgVars[0]);
        AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        BufferedImage out = new BufferedImage(Math.round(imgVars[1]), 
                Math.round(imgVars[2]), BufferedImage.TYPE_INT_ARGB);
        return new ImageIcon(scaleOp.filter(in, out));
    }
    
    private boolean getDimensionThreshold(int width, int height) {
        if (width < 800 && height < 600) {
            return true;
        } else {
            return false;
        }
    }
    
    private float[] getScaledDimensions(BufferedImage in) {
        float scalingFactor = 1;
        int height = in.getHeight();
        int width = in.getWidth();
        while (!getDimensionThreshold(height, width)) {
            scalingFactor -= .1;
            height *= scalingFactor;
            width *= scalingFactor;
        }
        return new float[] {scalingFactor, width, height};
    }

    private ImageIcon getNewIcon(Icon icon, int scalingFactor) {
        BufferedImage bi = new BufferedImage(scalingFactor*icon.getIconWidth(), 
                scalingFactor*icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bi.createGraphics();
        g.scale(scalingFactor, scalingFactor);
        return new ImageIcon(bi);
    }
    
}

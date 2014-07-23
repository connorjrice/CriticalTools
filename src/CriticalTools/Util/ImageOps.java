package CriticalTools.Util;

import CriticalTools.Objects.SearchResult;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Development
 */
public class ImageOps {
    private SearchResult searchResult;
    private BufferedImage originalImage;
    private BufferedImage currentImage;
    
    public ImageOps(SearchResult sr) {
        this.searchResult = sr;
    }
    
    /**
     * Retruns the page number from the relevant SearchResult object.
     * @return 
     */
    public String getFormattedTitle() {
        return "Page: " + searchResult.getPageNum();
    }

    public void readImage() throws IOException {
        originalImage = ImageIO.read(new File(searchResult.getImgLoc()));
    }
    
    public int[] getImageBounds() {
        return new int[] {0, 0, currentImage.getWidth(), currentImage.getHeight()};
    }

    public ImageIcon scaleImage() {
        float[] imgVars = getScaledDimensions(originalImage);
        BufferedImage out = new BufferedImage(round(imgVars[1]), round(imgVars[2]), BufferedImage.TYPE_INT_ARGB);
        Graphics g = out.createGraphics();
        g.drawImage(originalImage, 0, 0, round(imgVars[1]), round(imgVars[2]), null);
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
    
}

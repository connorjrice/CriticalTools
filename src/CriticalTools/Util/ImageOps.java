package CriticalTools.Util;

import CriticalTools.Objects.ImageData;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Utility for performing image manipulations on more than one image. TODO:
 * Implement multiple images.
 *
 * @author Connor Rice
 */
public class ImageOps {

    private ImageData imageData;
    private BufferedImage originalImage;
    private BufferedImage currentImage;
    private int photoNumber;

    public ImageOps(ImageData id, int photoNumber) {
        this.imageData = id;
        this.photoNumber = photoNumber;
    }

    /**
     * Retruns the page number from the relevant ImageData object.
     *
     * @return
     */
    public String getFormattedTitle() {
        return "Page: " + imageData.getPageNumber();
    }

    public void readImage() throws IOException {
        String imgType = imageData.getImgType();
        String imageLocation = getImageLocation(imageData.getPageNumber(), 
                Character.toString(imgType.charAt(photoNumber)));
        System.out.println(imageLocation);
        originalImage = ImageIO.read(new File(imageLocation));
    }

    public String getImageLocation(int i, String s) {
        switch (s) {
            case "b":
                return getArrangementDir() + getConvertedIndex(i) + "_bottom.jpg";
            case "t":
                return getArrangementDir() + getConvertedIndex(i) + "_top.jpg";
            case "m":
                return getArrangementDir() + getConvertedIndex(i) + "_mid.jpg";
            default:
                return "";
        }
    }
    
    public String getArrangementDir() {
        //return "./" + imageData.getArrangementDir() + "/";
        return "./" + "RiB_Grofe_Whiteman" + "/";
    }

    public String getConvertedIndex(int index) {
        if (index < 10) {
            return "0" + index;
        } else {
            return Integer.toString(index);
        }
    }

    public int[] getImageBounds() {
        return new int[]{0, 0, currentImage.getWidth(), currentImage.getHeight()};
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

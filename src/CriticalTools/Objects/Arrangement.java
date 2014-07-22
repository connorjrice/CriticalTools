package CriticalTools.Objects;

/**
 * Object that contains the name of the arrangements available, the directory
 * in which they exist, and the relevant .ind file.
 * @author Connor Rice
 */
public class Arrangement {
    
    private String name;
    private String dir;
    private ImageData[] imgData;
    private String indFile;
   
    public Arrangement(String name, String dir, ImageData[] imgData) {
        this.name = name;
        this.dir = dir;
        this.imgData = imgData;
    }
    
    /**
     * Deprecated constructor for .ind files.
     * @param name
     * @param dir
     * @param indfile 
     */
    public Arrangement(String name, String dir, String indfile) {
        this.name = name;
        this.dir = dir;
        this.indFile = indfile;
    }
    
    public String getName() {
        return name;
    }
    
    public String getDir() {
        return dir;
    }
    
    public ImageData[] getImgData() {
        return imgData;
    }
    
    public String getIndFile() {
        return indFile;
    }
}

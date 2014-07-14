package CriticalTools.Objects;

/**
 * Object that contains the name of the arrangements available, the directory
 * in which they exist, and the relevant .ind file.
 * @author Connor Rice
 */
public class Arrangement {
    
    private String name;
    private String dir;
    private String indFile;
   
    public Arrangement(String name, String dir, String indFile) {
        this.name = name;
        this.dir = dir;
        this.indFile = indFile;
    }
    
    public String getName() {
        return name;
    }
    
    public String getDir() {
        return dir;
    }
    
    public String getIndFile() {
        return indFile;
    }
}

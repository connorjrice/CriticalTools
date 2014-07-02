/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.Objects;

/**
 *
 * @author Development
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

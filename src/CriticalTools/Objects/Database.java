package CriticalTools.Objects;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * TODO: Fully implement Database objects
 * @author Connor Rice
 */
public class Database implements Serializable {
    private Arrangement[] arrangements;
    private ArrayList<String> indDB;
    
    public Database(Arrangement[] arrangements, ArrayList<String> indDB) {
        this.arrangements = arrangements;
        this.indDB = indDB;
    }
    
    public Arrangement[] getArrangements() {
        return arrangements;
    }
    
    public ArrayList<String> getIndDB() {
        return indDB;
    }
    
    
}

package dataAnalysis;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Singleton pattern for storing all the data in one place
 * @author akanitkar
 *
 */
final class DBStore
{
    private static DBStore instance = null;
    
    private DBStore(){
        
    }
    
    /** returns instance of singleton DBStore */
    public static DBStore getInstance(){
        if (instance == null)
            instance = new DBStore();
        
        return instance;
    }
    
    //data fields in Singleton class
    
    Set<Record> store = new HashSet<Record>();
    HashMap<String, Index> indexStore = new HashMap<String, Index>();
}

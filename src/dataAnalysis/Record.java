package dataAnalysis;

import java.util.*;

/**
 * Represents an instance of every record in each file. This class is created to encapsulate various 
 * types of data. Easiest to add are flat structure data, but also has more complex structure such
 * as tag clod. 
 * 
 * @author akanitkar
 *
 */
public class Record
{    
    String [] data = new String[Env.FLAT_COL_COUNT];
    Map <String, Integer> tagCloud = new HashMap<String, Integer>(); 
        
    /** Add flat column data type into a record */
    public void addData(Column colName, String value){
        data[colName.getValue()] = value;
    }
    
    /** returns data for given column name */
    public String getData(Column colName){
        return data[colName.getValue()];
    }
    
    /** Adds tag data type */
    public void addTagData(String key, Integer value){
        tagCloud.put(key, value);
    }
    
    /** Utility method to print a record */
    public String toString(){
        StringBuffer buffer = new StringBuffer();
        for(Column col : Column.values()){
            buffer.append(data[col.getValue()]);
            buffer.append(" ");
        }
        
        Iterator it = tagCloud.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry pairs = (Map.Entry)it.next();
            buffer.append(pairs.getKey() + ":" + pairs.getValue() + " ");
        }
        
        return buffer.toString();
    }
    
}





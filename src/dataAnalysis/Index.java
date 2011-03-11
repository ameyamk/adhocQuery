package dataAnalysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Holds index for a given column on entire data store. 
 * Use IndexBuilder to gereate indexes
 * @author akanitkar
 */
public class Index
{            
    Map <String, ArrayList<Record>> records = new HashMap<String, ArrayList<Record>>();
}

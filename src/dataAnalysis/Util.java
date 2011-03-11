package dataAnalysis;

import java.util.*;


/** provides static utility methods */
public class Util
{
    /** Sorts values of map. Default is on key, this is utility method to solve this problem */
    static List sortByValue(Map map)
    {
        if (map != null)
        {
            List<Map.Entry<String, Integer>> list = new LinkedList(map.entrySet());
            Collections.sort(list, new Comparator()
            {
                public int compare(Object o1, Object o2)
                {
                    return ((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue());
                }
            });
            return list;
        } else
        {
            return new LinkedList();
        }
    }
}

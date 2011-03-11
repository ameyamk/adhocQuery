package dataAnalysis;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * RecommendationSystem class provides various API's which can be used in a
 * manner such as: "If You like item x, you may also like Y"
 * 
 * Although, more advanced algorithms can be used, for the purpose of this demo
 * I am using simple weight calculations. In order to speed up the process, some
 * of the data is precomputed and kept in memory.
 * 
 * @author akanitkar
 */
public class RecommendationSystem
{
    DBStore db;

    // <Map<"football" - <gigaom, 3>>
    // <Map<"technology" - <gigaom, 12>>
    HashMap<String, HashMap<String, Integer>> tag2SiteWeightMap = new HashMap<String, HashMap<String, Integer>>();

    /** 
     * Constructor to create recommendation system. When this instance is created, 
     * data is preprocessed and stored in memory. If data changes at later time, 
     * you need to run recompute method available in this class
     * @param aDb
     */
    public RecommendationSystem(DBStore aDb)
    {
        db = aDb;
        preprocess();
    }
    
    /** Recompute recommendation data */ 
    public void recompute(){
        preprocess();
    }

    // process interests and find sites for each interest, and also calculate
    // weight based on ratings data
    private void preprocess()
    {

        Set<Record> records = db.store;

        for (Record aRec : records)
        {
            Map<String, Integer> map = aRec.tagCloud;
            Iterator it = map.entrySet().iterator();

            while (it.hasNext())
            {
                Map.Entry<String, Integer> pairs = (Map.Entry) it.next();

                String tagKey = pairs.getKey(); // tagName
                Integer tagValue = pairs.getValue() * Integer.parseInt(aRec.getData(Column.RATING)); // weight

                String website = aRec.getData(Column.SITE);

              //populate weights corresponding to interest
                if (tag2SiteWeightMap.containsKey(tagKey))
                {
                    HashMap<String, Integer> site2count = tag2SiteWeightMap.get(tagKey);

                    if (site2count.containsKey(website))
                    {
                        int count = site2count.get(website);
                        count = count + tagValue;
                        site2count.put(website, count);
                    } else
                    {
                        site2count.put(website, tagValue);
                    }
                } else
                {
                    HashMap<String, Integer> site2count = new HashMap<String, Integer>();
                    site2count.put(website, tagValue);
                    tag2SiteWeightMap.put(tagKey, site2count);
                }                                                
            }
        }

    }

    /**
     * If you are the person with these kind of interests, here are the sites
     * you may like Method: Uses preprocessed data allowing faster
     * recommendations. Recommendation are based on tag cloud weight and user
     * rating.
     * 
     * @param tags
     */
    public void recommendSitesByInterest(String interest)
    {
        HashMap<String, Integer> site2count = tag2SiteWeightMap.get(interest);
        List<Map.Entry<String, Integer>> sorted = Util.sortByValue(site2count);
        System.out.println("\nRecommended sites  for your interest  " + interest + " are :");

        int maxSize = sorted.size();

        for (int i = 1; i <= maxSize; i++)
        {
            System.out.println("Rank " + i + " : " + sorted.get(sorted.size() - i));
        }
    }
    
}

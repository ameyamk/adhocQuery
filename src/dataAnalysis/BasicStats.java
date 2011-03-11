package dataAnalysis;

import java.util.*;

/**
 * Provides 2 basic types of API's, ones which go over all data and APi's which
 * use indexs to avoid amount of processing is required
 * 
 * @author akanitkar
 * 
 */
public class BasicStats
{
    DBStore db;

    BasicStats(DBStore aDb)
    {
        db = aDb;
    }

    /** Print utility, to print all data */
    public void printAll()
    {
        for (Record aRec : db.store)
        {
            System.out.println(aRec);
        }
    }

    /**
     * API to find out what are most common interests for a given type. This is
     * helpful in finding what kind of people visit any website, or whats common
     * interest of people from given state.
     * 
     * @param colName
     * @param byColValue
     * @param Idx
     * @param topN
     *            examples: findTopNInterestByIndex(Columns.COUNTRY, "USA",
     *            countryIdx, 5) = This should print what are 5 most common
     *            interests for people from United States
     *            findTopNInterestByIndex(Columns.SITE, "gigaom", countryIdx, 5)
     *            = This should print what are 5 most common interest type of
     *            people visiting gigaom.com
     */
    public void topNInterestsByIndex(Column colName, String byColValue, Index byIdx, int topN)
    {
        ArrayList<Record> records = byIdx.records.get(byColValue);

        if (records.size() > 0)
        {

            LinkedHashMap<String, Integer> trackCountMap = new LinkedHashMap<String, Integer>();

            int counter = 0;

            for (Record aRec : records)
            {
                Map map = aRec.tagCloud;
                Iterator it = map.entrySet().iterator();
                // iterate over all tagCloud for this user and add tagCloud
                // count into larger countMap
                while (it.hasNext())
                {
                    Map.Entry<String, Integer> pairs = (Map.Entry) it.next();

                    String tagKey = pairs.getKey();
                    Integer tagValue = pairs.getValue();

                    if (trackCountMap.containsKey(tagKey))
                    {
                        counter = trackCountMap.get(tagKey);
                        counter = counter + tagValue;
                        trackCountMap.put(tagKey, counter);
                    } else
                    {
                        trackCountMap.put(tagKey, tagValue);
                    }
                }

            }

            List<Map.Entry<String, Integer>> sorted = Util.sortByValue(trackCountMap);
            System.out.println("\nTop " + topN + " most popular interests for " + colName + "  " + byColValue + " are as follows :");

            int maxSize = sorted.size();
            if (maxSize < topN)
                topN = maxSize;

            for (int i = 1; i <= topN; i++)
            {
                System.out.println("Rank " + i + " : " + sorted.get(sorted.size() - i));
            }
        } else
        {
            System.out.println("No records found");
        }
    }

    /**
     * API to return top N most popular column value for a given constraint eg.
     * List top 5 states which sends most traffic to GigaOM website. You need to
     * create index on Column Website first, lets call it webIdx, and then call
     * this API as follows topNPopularByIndex(Columns.STATE, "GIGAOM", webIdx,
     * 5) API demands passing Index, there by avoiding going over all the data.
     */
    public void topNPopularByIndex(Column colName, String byColValue, Index byIdx, int topN)
    {
        ArrayList<Record> records = byIdx.records.get(byColValue);

        if (records.size() > 0)
        {

            LinkedHashMap<String, Integer> trackCountMap = new LinkedHashMap<String, Integer>();

            int counter = 0;

            for (Record aRec : records)
            {
                counter = 0;
                String key = aRec.getData(colName);
                if (trackCountMap.containsKey(key))
                {
                    counter = trackCountMap.get(key);
                    counter++;
                    trackCountMap.put(key, counter);
                } else
                {
                    trackCountMap.put(key, 1);
                    counter = 1;
                }
            }

            List<Map.Entry<String, Integer>> sorted = Util.sortByValue(trackCountMap);
            System.out.println("\nTop " + topN + " most popular " + colName + " for " + byColValue + " are as follows :");

            int maxSize = sorted.size();
            if (maxSize < topN)
                topN = maxSize;

            for (int i = 1; i <= topN; i++)
            {
                System.out.println("Rank " + i + " : " + sorted.get(sorted.size() - i));
            }
        } else
        {
            System.out.println("No records found");
        }
    }

    /**
     * API: Returns most popular value for given column Simplest API in this
     * suite
     * 
     * @param colName
     */
    public void topMostPopular(Column colName)
    {
        if (!db.store.isEmpty())
        {

            Map<String, Integer> trackCountMap = new HashMap<String, Integer>();

            Record mostPopular = null;
            int maxCount = 0;
            int counter = 0;
            for (Record aRec : db.store)
            {
                counter = 0;
                String key = aRec.getData(colName);
                if (trackCountMap.containsKey(key))
                {
                    counter = trackCountMap.get(key);
                    counter++;
                    trackCountMap.put(key, counter);
                } else
                {
                    trackCountMap.put(key, 1);
                    counter = 1;
                }

                if (counter >= maxCount)
                {
                    maxCount = counter;
                    mostPopular = aRec;
                }
            }

            System.out.println("\n Most popular value for column " + colName.toString() + " is " + mostPopular.getData(colName) + " with count " + maxCount);
        } else
        {
            System.out.println("No records found");
        }

    }

}

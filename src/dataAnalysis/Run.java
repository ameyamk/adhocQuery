package dataAnalysis;

import java.io.FileNotFoundException;


/**
 * Holds main method. If you want to start reading code, start from this class.
 * 
 * For the purpose of this project, this provides 3 various types of API's
 * 
 * Type 1: Simple API to query the data: These algorithms run over all the data
 * Type 2: Data Query API with conditions : These API's run over only subset of data using indexes. however indexes needs to be supplied.
 * Type 3: Recommendation System API: These data API's attempt at guessing what user may like given set of information. Since some of these
 *         algorithms can get very slow, all of these API's make use of precomputed data  

 * @author akanitkar
 * 
 * Extensibility:
 * Adding new columns of data is easy, since flat data is encapsulated in Record class. Also most of Type 1 and Type 2 query API's would work even on new columns without any code change.
 * Adding more complex data type such as tagclod would however need code change.
 * 
 * Data is encapsulated in singleton DBStore class. This can be easily extended for caching/ persitent storage, since all the data access is channelized through DBStore API's.
 * Once Index is built its stored globally in singleton instance. Hence no need to recompute indexes.
 * 
 *  If you have any more questions please feel free to email me at: 412-951-4913
 */
public class Run
{
    /**
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException
    {

        FileReader reader = new FileReader();
        DBStore db = DBStore.getInstance();
               
        init(reader, db);
        
        BasicStats stat = new BasicStats(DBStore.getInstance());
        
        
        System.out.println("\n************ Data queries using simple API's ***********\n");
        
        stat.topMostPopular(Column.GENDER);
        stat.topMostPopular(Column.SITE);
        stat.topMostPopular(Column.AGE);
        stat.topMostPopular(Column.RATING);
        stat.topMostPopular(Column.CITY);
        stat.topMostPopular(Column.STATE);
        stat.topMostPopular(Column.COUNTRY);
        
        Index webidx = IndexBuilder.buildIndex(Column.SITE, db);
        Index stateidx = IndexBuilder.buildIndex(Column.STATE, db);
        
        System.out.println("\n************ Data queries using API's based on Indexes ***********\n");
        
        
        stat.topNPopularByIndex(Column.STATE, "gigaom", webidx, 5);
        
        stat.topNPopularByIndex(Column.STATE, "hubspot", webidx, 5);
        
        stat.topNPopularByIndex(Column.SITE, "California", stateidx, 5);
        
        stat.topNPopularByIndex(Column.SITE, "Texas", stateidx, 5);
        
        stat.topNInterestsByIndex(Column.SITE, "simplyexplained", webidx, 10);
        
        stat.topNInterestsByIndex(Column.STATE, "California", stateidx, 10);
        
        RecommendationSystem rec = new RecommendationSystem(db);
        
        System.out.println("\n************ Recommendation System: If you like x you may also like y kinds ***********\n");
        
        rec.recommendSitesByInterest("photography");
        
        rec.recommendSitesByInterest("humor");
                   
        //stat.printAll();
        
    }

    /**utility that reads and loads all files in memory 
     * hard coded  list of file names, change this if you have more files */
    private static void init(FileReader reader, DBStore db) throws FileNotFoundException
    {
        //load all files
        String fileName = "/Users/akanitkar/Desktop/urls/free411.com.csv";                  
        reader.loadFile(fileName, "free411", db);
        
        fileName = "/Users/akanitkar/Desktop/urls/gigaom.com.csv";
        reader.loadFile(fileName, "gigaom", db);
        
        fileName = "/Users/akanitkar/Desktop/urls/hubspot.com.csv";
        reader.loadFile(fileName, "hubspot", db);
        
        fileName = "/Users/akanitkar/Desktop/urls/leadertoleader.org.csv";
        reader.loadFile(fileName, "leader2leader", db);
        
        fileName = "/Users/akanitkar/Desktop/urls/simplyexplained.com.csv";
        reader.loadFile(fileName, "simplyexplained", db);
    }

}

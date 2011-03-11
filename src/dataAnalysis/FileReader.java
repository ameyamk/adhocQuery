package dataAnalysis;

import java.io.*;
import java.util.*;



public class FileReader
{
    /** Read and load given file for a given site into given datastore */
    public static void loadFile(String filePath, String websiteName, DBStore db) throws FileNotFoundException
    {
        
        FileInputStream fstream = new FileInputStream(filePath);
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String strLine;
        String wname = websiteName;
        try
        {
            while((strLine = br.readLine())!=null)
//            for (int i = 0; i < 5; i++)
            {
//                strLine = br.readLine();
                Record record = new Record();
                record.addData(Column.SITE, wname);

                StringTokenizer tokens = new StringTokenizer(strLine, ",");
                for (int col = 0; col < Env.FLAT_COL_COUNT-1; col++)
                {
                    String colValue = tokens.nextToken();
                    if (col == 0)
                        record.addData(Column.RATING, colValue);

                    else if (col == 1)
                        record.addData(Column.TIMESTAMP, colValue);

                    else if (col == 2)
                        record.addData(Column.AGE, colValue);

                    else if (col == 3)
                    {
                        record.addData(Column.GENDER, colValue);
                    }

                    else if (col == 4)
                    {
                        record.addData(Column.CITY, colValue);
                    }

                    else if (col == 5)
                    {
                        record.addData(Column.STATE, colValue);
                    }

                    else if (col == 6)
                    {
                        record.addData(Column.COUNTRY, colValue);
                    }
                                                          
                }
                
                String tag;
                                                
                while(tokens.hasMoreTokens()){
                    tag = tokens.nextToken();                                       
                    StringTokenizer tagTokden = new StringTokenizer(tag, ":");
                    String tagName = tagTokden.nextToken();
                    Integer count = new Integer(tagTokden.nextToken());
                    record.addTagData(tagName, count);                                        
                }
                                
                db.store.add(record);
            }
            in.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

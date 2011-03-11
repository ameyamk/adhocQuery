package dataAnalysis;

import java.util.ArrayList;

/**
 * Index Builder builds as name suggests - builds index on a given column Also,
 * checks if index is already built, if yes then returns already built index.
 * 
 * @author akanitkar
 */
public class IndexBuilder
{
    static Index buildIndex(Column colName, DBStore db)
    {

        if (db.indexStore.containsKey(colName.toString()))
            return db.indexStore.get(colName.toString());

        Index idx = new Index();
        for (Record aRec : db.store)
        {
            String colKey = aRec.getData(colName);
            ArrayList<Record> recordList;
            if (idx.records.containsKey(colKey))
            {
                recordList = idx.records.get(colKey);
                recordList.add(aRec);
            } else
            {
                recordList = new ArrayList<Record>();
                recordList.add(aRec);
            }

            idx.records.put(colKey, recordList);
        }

        return idx;
    }
}

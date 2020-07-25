package data;

import com.irfansyed.umeedenau.validation.Global;


public class TableMetadata {
    public static String TABLE_NAME = "TableMetadata";

    public static String getCreateQuery() {
        String query;
        query = "CREATE TABLE '" + TABLE_NAME + "' ('id' INTEGER PRIMARY KEY AUTOINCREMENT,  " +

                Global.user + "  Text, " +
                Global.appversion + "  Text, " +
                Global.gpslat + "  Text, " +
                Global.gpslng + "  Text, " +
                "LHWSectionId  Text, " +
                "datetimeInterview  Text " +

                ')';

        return query;
    }
}
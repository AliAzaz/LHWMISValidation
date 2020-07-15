package data;

import com.irfansyed.umeedenau.validation.Global;


public class TableF3SectionB {
    public static String TABLE_NAME = "TableF3SectionB";

    public static String getCreateQuery() {
        String query;
        query = "CREATE TABLE '" + TABLE_NAME + "' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'FK_id' INTEGER,   " +

                " Status  Text, " +
                Global.lhwf3b1 + "  Text, " +
                Global.lhwf3b2 + "  Text, " +
                Global.lhwf3b3 + "  Text, " +
                Global.lhwf3b4a + "  Text, " +
                Global.lhwf3b4b + "  Text, " +
                Global.lhwf3b4c + "  Text, " +
                Global.lhwf3b5 + "  Text, " +
                Global.lhwf3b5b + "  Text, " +
                Global.lhwf3b5b96x + "  Text, " +
                Global.lhwf3b6 + "  Text, " +
                Global.lhwf3bphoto + "  Text " +

                ')';


        return query;
    }
}
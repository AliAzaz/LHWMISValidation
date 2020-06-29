package data;

import com.irfansyed.umeedenau.validation.Global;


public class TableF5SectionB {
    public static String TABLE_NAME = "TableF5SectionB";

    public static String getCreateQuery() {
        String query;
        query = "CREATE TABLE '" + TABLE_NAME + "' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'FK_id' INTEGER,   " +


                " Status  Text, " +
                Global.lhwf5b1 + "  Text, " +
                Global.lhwf5b2 + "  Text, " +
                Global.lhwf5b3 + "  Text, " +
                Global.lhwf5b4 + "  Text, " +
                Global.lhwf5b5a + "  Text, " +
                Global.lhwf5b5b + "  Text, " +
                Global.lhwf5b5c + "  Text, " +
                Global.lhwf5b5a1 + "  Text, " +
                Global.lhwf5b5b1 + "  Text, " +
                Global.lhwf5b5b196x + "  Text, " +
                Global.lhwf5b6 + "  Text, " +
                Global.lhwf5b7_1 + "  Text, " +
                Global.lhwf5b7_2 + "  Text, " +
                Global.lhwf5b7_3 + "  Text, " +
                Global.lhwf5b7_4 + "  Text, " +
                Global.lhwf5b7_5 + "  Text, " +
                Global.lhwf5b7_6 + "  Text, " +
                Global.lhwf5b7_7 + "  Text, " +
                Global.lhwf5b7_8 + "  Text, " +
                Global.lhwf5b796 + "  Text, " +
                Global.lhwf5b796x + "  Text " +


                ')';


        return query;
    }
}

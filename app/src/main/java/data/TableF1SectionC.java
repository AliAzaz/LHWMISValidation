package data;

import com.irfansyed.umeedenau.validation.Global;


public class TableF1SectionC {
    public static String TABLE_NAME = "TableF1SectionC";

    public static String getCreateQuery() {
        String query;
        query = "CREATE TABLE '" + TABLE_NAME + "' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'FK_id' INTEGER,   " +


                Global.LhwSectionPKId + "  Text, " +
                "Status  Text, " +
                Global.lhwf1c1 + "  Text, " +
                Global.lhwf1c2 + "  Text, " +
                Global.lhwf1c3 + "  Text, " +
                Global.lhwf1c3Aa + "  Text, " +
                Global.lhwf1c3Ab + "  Text, " +
                Global.lhwf1c4 + "  Text, " +
                Global.lhwf1c4A + "  Text, " +
                Global.lhwf1c5 + "  Text, " +
                Global.lhwf1c6 + "  Text, " +
                Global.lhwf1c7 + "  Text, " +
                Global.lhwf1c7A + "  Text, " +
                Global.lhwf1c8 + "  Text, " +
                Global.lhwc11a + "  Text, " +
                Global.lhwc11b + "  Text, " +
                Global.lhwf1cphoto + "  Text, " +
                "lhwf1c9" + "  Text, " +
                "lhwf1c10" + "  Text " +

                ')';


        return query;
    }
}

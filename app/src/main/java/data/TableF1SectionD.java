package data;

import com.irfansyed.umeedenau.validation.Global;


public class TableF1SectionD {
    public static String TABLE_NAME = "TableF1SectionD";

    public static String getCreateQuery() {
        String query;
        query = "CREATE TABLE '" + TABLE_NAME + "' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'FK_id' INTEGER,   " +


                Global.LhwSectionPKId + "  Text, " +
                "Status  Text, " +
                Global.lhwf1d1 + "  Text, " +
                Global.lhwf1d2 + "  Text, " +
                Global.lhwf1d3 + "  Text, " +
                Global.lhwf1d4 + "  Text, " +
                Global.lhwf1d8a + "  Text, " +
                Global.lhwf1d8b + "  Text, " +
                Global.lhwf1d4Aa + "  Text, " +
                Global.lhwf1d4Ab + "  Text, " +
                Global.lhwf1d5 + "  Text, " +
                Global.lhwf1d6 + "  Text, " +
                Global.lhwf1d7 + "  Text, " +
                Global.lhwf1dphoto + "  Text " +

                ')';


        return query;
    }
}

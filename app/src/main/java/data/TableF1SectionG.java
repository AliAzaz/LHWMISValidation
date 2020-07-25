package data;

import com.irfansyed.umeedenau.validation.Global;


public class TableF1SectionG {
    public static String TABLE_NAME = "TableF1SectionG";

    public static String getCreateQuery() {
        String query;
        query = "CREATE TABLE '" + TABLE_NAME + "' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'FK_id' INTEGER,   " +


                Global.LhwSectionPKId + "  Text, " +
                "Status  Text, " +
                Global.lhwf1g1 + "  Text, " +
                Global.lhwf1g2 + "  Text, " +
                Global.lhwf1g3 + "  Text, " +
                Global.lhwf1g4 + "  Text, " +
                Global.lhwf1g4Aa + "  Text, " +
                Global.lhwf1g4Ab + "  Text, " +
                Global.lhwf1g5 + "  Text, " +
                Global.lhwf1g10a + "  Text, " +
                Global.lhwf1g10b + "  Text, " +
                Global.lhwf1g6 + "  Text, " +
                Global.lhwf1g7 + "  Text, " +
                Global.lhwf1g8 + "  Text, " +
                Global.lhwf1gphoto + "  Text, " +
                Global.lhwf1g9 + "  Text" +

                ')';


        return query;
    }
}

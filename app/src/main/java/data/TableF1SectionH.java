package data;

import com.irfansyed.umeedenau.validation.Global;


public class TableF1SectionH {
    public static String TABLE_NAME = "TableF1SectionH";

    public static String getCreateQuery() {
        String query;
        query = "CREATE TABLE '" + TABLE_NAME + "' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'FK_id' INTEGER,   " +


                Global.LhwSectionPKId + "  Text, " +
                "Status  Text, " +
                Global.lhwf1h1 + "  Text, " +
                Global.lhwf1h2 + "  Text, " +
                Global.lhwf1h3 + "  Text, " +
                Global.lhwf1h4 + "  Text, " +
                Global.lhwf1h5 + "  Text, " +
                Global.lhwf1h10a + "  Text, " +
                Global.lhwf1h10b + "  Text, " +
                Global.lhwf1h6 + "  Text, " +
                Global.lhwf1h7 + "  Text, " +
                Global.lhwf1h8 + "  Text, " +
                Global.lhwf1hphoto + "  Text, " +
                Global.lhwf1h9 + "  Text " +

                ')';


        return query;
    }
}

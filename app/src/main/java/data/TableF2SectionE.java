package data;

import com.irfansyed.umeedenau.validation.Global;


public class TableF2SectionE {
    public static String TABLE_NAME = "TableF2SectionE";

    public static String getCreateQuery() {
        String query;
        query = "CREATE TABLE '" + TABLE_NAME + "' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'FK_id' INTEGER,   " +

                Global.LhwSectionPKId + "  Text, " +
                Global.lhwf2e1 + "  Text, " +
                Global.lhwf2e1a + "  Text, " +
                Global.lhwf2e2 + "  Text, " +
                Global.lhwf2e3 + "  Text, " +
                Global.lhwf2e4 + "  Text, " +
                Global.lhwf2e5 + "  Text, " +
                Global.lhwf2e5a + "  Text, " +
                Global.lhwf2e6 + "  Text, " +
                Global.lhwf2e6a + "  Text, " +
                Global.lhwf2e7 + "  Text, " +
                Global.lhwf2e7a + "  Text, " +
                Global.lhwf2e8 + "  Text, " +
                Global.lhwf2e9 + "  Text, " +
                Global.lhwf2e10 + "  Text, " +
                Global.lhwf2e11 + "  Text, " +
                Global.lhwf2e12 + "  Text, " +
                Global.lhwf2e12a + "  Text, " +
                Global.lhwf2e13 + "  Text ," +
                Global.GPSLat + "  Text," +
                Global.GPSLong + "  Text," +
                Global.InterviewTime + "  Text" +

                ')';

        return query;
    }
}
package data;

import com.irfansyed.umeedenau.validation.Global;


public class TableF2SectionF {
    public static String TABLE_NAME = "TableF2SectionF";

    public static String getCreateQuery() {
        String query;
        query = "CREATE TABLE '" + TABLE_NAME + "' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'FK_id' INTEGER,   " +

                Global.LhwSectionPKId + "  Text, " +
                Global.lhwf2f1 + "  Text, " +
                Global.lhwf2f3 + "  Text, " +
                Global.lhwf2f3a + "  Text, " +
                Global.lhwf2f4 + "  Text, " +
                Global.lhwf2f5 + "  Text, " +
                Global.lhwf2f5a + "  Text, " +
                Global.lhwf2f6 + "  Text, " +
                Global.lhwf2f7 + "  Text, " +
                Global.lhwf2f8 + "  Text, " +
                Global.lhwf2f8a + "  Text, " +
                Global.lhwf2f9 + "  Text, " +
                Global.lhwf2f10 + "  Text, " +
                Global.lhwf2f11 + "  Text, " +
                Global.lhwf2f12 + "  Text, " +
                Global.lhwf2f13 + "  Text ," +
                Global.GPSLat + "  Text," +
                Global.GPSLong + "  Text," +
                Global.InterviewTime + "  Text" +

                ')';

        return query;
    }
}
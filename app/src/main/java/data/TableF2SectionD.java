package data;

import com.irfansyed.umeedenau.validation.Global;


public class TableF2SectionD {
    public static String TABLE_NAME = "TableF2SectionD";

    public static String getCreateQuery() {
        String query;
        query = "CREATE TABLE '" + TABLE_NAME + "' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'FK_id' INTEGER,   " +

                Global.LhwSectionPKId + "  Text, " +
                Global.lhwf2d1 + "  Text, " +
                Global.lhwf2d2 + "  Text, " +
                Global.lhwf2d2a + "  Text, " +
                Global.lhwf2d2b + "  Text, " +
                Global.lhwf2d3 + "  Text, " +
                Global.lhwf2d4 + "  Text, " +
                Global.lhwf2d5 + "  Text, " +
                Global.lhwf2d6 + "  Text, " +
                Global.lhwf2d6a + "  Text, " +
                Global.lhwf2d7 + "  Text, " +
                Global.lhwf2d8 + "  Text, " +
                Global.lhwf2d9 + "  Text, " +
                Global.lhwf2d10 + "  Text, " +
                Global.lhwf2d11 + "  Text, " +
                Global.lhwf2d12 + "  Text ," +
                Global.GPSLat + "  Text," +
                Global.GPSLong + "  Text," +
                Global.InterviewTime + "  Text" +

                ')';

        return query;
    }
}

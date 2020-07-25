package data;

import com.irfansyed.umeedenau.validation.Global;


public class TableF4SectionB {
    public static String TABLE_NAME = "TableF4SectionB";

    public static String getCreateQuery() {
        String query;
        query = "CREATE TABLE '" + TABLE_NAME + "' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'FK_id' INTEGER,   " +

                Global.lhwf4b0 + "  Text, " +
                Global.lhwf4b1 + "  Text, " +
                Global.lhwf4b2 + "  Text, " +
                Global.lhwf4b3 + "  Text, " +
                Global.lhwf4b5 + "  Text, " +
                Global.lhwf4b6 + "  Text ," +
                Global.GPSLat + "  Text," +
                Global.GPSLong + "  Text," +
                Global.InterviewTime + "  Text" +

                ')';

        return query;
    }
}
package data;

import com.irfansyed.umeedenau.validation.Global;


public class TableF2SectionC {
    public static String TABLE_NAME = "TableF2SectionC";

    public static String getCreateQuery() {
        String query;
        query = "CREATE TABLE '" + TABLE_NAME + "' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'FK_id' INTEGER,   " +

                Global.LhwSectionPKId + "  Text, " +

                Global.lhwf2c1 + "  Text, " +
                Global.lhwf2c2 + "  Text, " +
                Global.lhwf2c2a + "  Text, " +
                Global.lhwf2c2b + "  Text, " +
                Global.lhwf2c3 + "  Text, " +
                Global.lhwf2c4 + "  Text, " +
                Global.lhwf2c4a + "  Text, " +
                Global.lhwf2c4b + "  Text, " +
                Global.lhwf2c5 + "  Text, " +
                Global.lhwf2c6 + "  Text, " +
                Global.lhwf2c6a + "  Text, " +
                Global.lhwf2c7 + "  Text, " +
                Global.lhwf2c8 + "  Text, " +
                Global.lhwf2c8a + "  Text, " +
                Global.lhwf2c9 + "  Text, " +
                Global.lhwf2c10 + "  Text, " +
                Global.lhwf2c11 + "  Text," +
                Global.GPSLat + "  Text," +
                Global.GPSLong + "  Text," +
                Global.InterviewTime + "  Text" +


                ')';


        return query;
    }
}

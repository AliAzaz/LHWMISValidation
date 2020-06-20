package data;

import com.irfansyed.umeedenau.validation.Global;


public class TableF6SectionB {
    public static String TABLE_NAME = "TableF6SectionB";

    public static String getCreateQuery() {
        String query;
        query = "CREATE TABLE '" + TABLE_NAME + "' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'FK_id' INTEGER,   " +




                Global.lhwf6b0+"  Text, "+
                Global.lhwf6b1+"  Text, "+
                Global.lhwf6b2+"  Text, "+
                Global.lhwf6b3+"  Text, "+
                Global.lhwf6b4+"  Text, "+
                Global.lhwf6b5+"  Text, "+
                Global.lhwf6b6_1+"  Text, "+
                Global.lhwf6b6_2+"  Text, "+
                Global.lhwf6b6_3+"  Text, "+
                Global.lhwf6b6_4+"  Text, "+
                Global.lhwf6b6_5+"  Text, "+
                Global.lhwf6b6_6+"  Text, "+
                Global.lhwf6b6_7+"  Text, "+
                Global.lhwf6b6_8+"  Text ,"+
                Global.lhwf6b696 + "  Text ," +
                Global.lhwf6b696x + "  Text ," +
                Global.GPSLat+"  Text,"+
                Global.GPSLong+"  Text,"+
                Global.lhwf6bphoto + "  Text," +
                Global.InterviewTime+"  Text"+







                ')';


        return query;
    }
}

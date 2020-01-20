package data;

import com.irfansyed.umeedenau.validation.Global;


public class TableF2SectionH {
    public static String TABLE_NAME = "TableF2SectionH";

    public static String getCreateQuery() {
        String query;
        query = "CREATE TABLE '" + TABLE_NAME + "' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'FK_id' INTEGER,   " +


                Global.LhwSectionPKId+"  Text, "+
                Global.lhwf2h1+"  Text, "+
                Global.lhwf2h2+"  Text, "+
                Global.lhwf2h2a + "  Text, " +
                Global.lhwf2h3+"  Text, "+
                Global.lhwf2h4+"  Text, "+
                Global.lhwf2h5+"  Text, "+
                Global.lhwf2h6+"  Text, "+
                Global.lhwf2h7+"  Text, "+
                Global.lhwf2h8+"  Text, "+
                Global.lhwf2h9+"  Text, "+
                Global.lhwf2h10+"  Text, "+
                Global.lhwf2h11+"  Text ,"+
                Global.GPSLat+"  Text,"+
                Global.GPSLong+"  Text,"+
                Global.InterviewTime+"  Text"+









                ')';


        return query;
    }
}

package data;

import com.irfansyed.umeedenau.validation.Global;


public class TableF1SectionH {
    public static String TABLE_NAME = "TableF1SectionH";

    public static String getCreateQuery() {
        String query;
        query = "CREATE TABLE '" + TABLE_NAME + "' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'FK_id' INTEGER,   " +



                Global.lhwf1h1+"  Text, "+
                Global.lhwf1h2+"  Text, "+
                Global.lhwf1h3+"  Text, "+
                Global.lhwf1h4+"  Text, "+
                Global.lhwf1h5+"  Text "+









                ')';


        return query;
    }
}

package data;

import com.irfansyed.umeedenau.validation.Global;


public class TableF2SectionG {
    public static String TABLE_NAME = "TableF2SectionG";

    public static String getCreateQuery() {
        String query;
        query = "CREATE TABLE '" + TABLE_NAME + "' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'FK_id' INTEGER,   " +


                Global.LhwSectionPKId+"  Text, "+
                Global.lhwf2g3+"  Text, "+
                Global.lhwf2g4+"  Text, "+
                Global.lhwf2g5+"  Text, "+
                Global.lhwf2g6+"  Text, "+
                Global.lhwf2g7+"  Text, "+
                Global.lhwf2g8+"  Text, "+
                Global.lhwf2g9+"  Text, "+
                Global.lhwf2g10+"  Text "+








                ')';


        return query;
    }
}

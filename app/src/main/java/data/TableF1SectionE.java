package data;

import com.irfansyed.umeedenau.validation.Global;


public class TableF1SectionE {
    public static String TABLE_NAME = "TableF1SectionE";

    public static String getCreateQuery() {
        String query;
        query = "CREATE TABLE '" + TABLE_NAME + "' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'FK_id' INTEGER,   " +


                Global.LhwSectionPKId+"  Text, "+
                "Status  Text, "+
                Global.lhwf1e1+"  Text, "+
                Global.lhwf1e2+"  Text, "+
                Global.lhwf1e3+"  Text, "+
                Global.lhwf1e4+"  Text, "+
                Global.lhwf1e4Aa+"  Text, "+
                Global.lhwf1e4Ab+"  Text, "+
                Global.lhwf1e5+"  Text, "+
                Global.lhwf1e5b+"  Text, "+
                Global.lhwf1e6+"  Text, "+
                Global.lhwf1e6b+"  Text, "+
                Global.lhwf1e7+"  Text, "+
                Global.lhwf1e7b+"  Text, "+
                Global.lhwf1e8 + "  Text, " +
                Global.lhwf1ephoto + "  Text " +







                ')';


        return query;
    }
}

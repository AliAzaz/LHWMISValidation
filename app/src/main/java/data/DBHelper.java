package data;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Umeed-e-Nau on 12/21/2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "LHWMIS.db";
    private static final int VERSION = 5;

    Context mContext;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.beginTransaction();

        //db.execSQL(activity1.getCreateQuery());
        db.execSQL(TableF1SectionC.getCreateQuery());
        db.execSQL(TableF1SectionD.getCreateQuery());
        db.execSQL(TableF1SectionE.getCreateQuery());
        db.execSQL(TableF1SectionF.getCreateQuery());
        db.execSQL(TableF1SectionG.getCreateQuery());
        db.execSQL(TableF1SectionH.getCreateQuery());


        db.execSQL(TableF2SectionC.getCreateQuery());
        db.execSQL(TableF2SectionD.getCreateQuery());
        db.execSQL(TableF2SectionE.getCreateQuery());
        db.execSQL(TableF2SectionF.getCreateQuery());
        db.execSQL(TableF2SectionG.getCreateQuery());
        db.execSQL(TableF2SectionH.getCreateQuery());

        db.execSQL(TableF3SectionB.getCreateQuery());
        db.execSQL(TableF4SectionB.getCreateQuery());

        db.execSQL(TableF5SectionB.getCreateQuery());
        db.execSQL(TableF6SectionB.getCreateQuery());


        db.execSQL(TableLoginData.getCreateQuery());

        db.execSQL(TableLHWSection.getCreateQuery());
        db.execSQL(TableHHSection.getCreateQuery());
        db.execSQL(TableMetadata.getCreateQuery());


        db.setTransactionSuccessful();
        db.endTransaction();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        //  String query="";
        //  db.beginTransaction();
        //  query = "DROP TABLE IF EXISTS " + TableF1SectionC.TABLE_NAME;
        //  db.execSQL(query);
//
        //  db.execSQL(TableF1SectionC.getCreateQuery());
//
//
        //  db.setTransactionSuccessful();
        //  db.endTransaction();
    }

    public Cursor execRAW(String query) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(query, null);
        } catch (SQLiteException e) {
            e.printStackTrace();
            Log.d(DBHelper.class.getName(), " Exception while executing Query");
        }
        return cursor;
    }


    // ANDROID DATABASE MANAGER
    public ArrayList<Cursor> getData(String Query) {
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[]{"message"};
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2 = new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);

        try {
            String maxQuery = Query;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);

            //add value to cursor2
            Cursor2.addRow(new Object[]{"Success"});

            alc.set(1, Cursor2);
            if (null != c && c.getCount() > 0) {

                alc.set(0, c);
                c.moveToFirst();

                return alc;
            }
            return alc;
        } catch (SQLException sqlEx) {
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[]{"" + sqlEx.getMessage()});
            alc.set(1, Cursor2);
            return alc;
        } catch (Exception ex) {

            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[]{"" + ex.getMessage()});
            alc.set(1, Cursor2);
            return alc;
        }
    }

}
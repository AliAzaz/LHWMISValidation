package data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import utils.MyPreferences;


/**
 * Created by Umeed-e-Nau on 12/23/2016.
 */
public class LocalDataManager {
    Context mContext;
    public static SQLiteDatabase database;


    public LocalDataManager(Context context) {
        // try {
        mContext = context;
        database = new DBHelper(mContext).getWritableDatabase();


      /*  } catch (Exception e) {
             Toast.makeText(context,"Error in Localdata Mangager",Toast.LENGTH_LONG).show();
        }

        */
    }


    public List<String> getLogList(String status) {

        ArrayList<String> list = new ArrayList<>();

        try {

            String query = "select ac.id,t.Q1,t.Q2,pk_pk,ac.a2 from activity1 ac join tabl1 t on ac.pk_pk=t.id where ac.Interview_status = '%s' order by ac.id ASC";
            query = String.format(query, status);

            database.beginTransaction();
            Cursor c = database.rawQuery(query, null);
            if (c != null) {
                if (c.moveToFirst()) {
                    do {


                        list.add(c.getString(0) + "/" + c.getString(1) + "/" + c.getString(2) + "/" + c.getString(3) + "/" + c.getString(4));


                    } while (c.moveToNext());
                }
            }
        } finally {
            database.setTransactionSuccessful();
            database.endTransaction();
            database.close();
        }
        return list;
    }


    public List<String> getLogList_mr(String status) {

        ArrayList<String> list = new ArrayList<>();

        try {

            String query = "select ac.id,t.Q1,t.Q2,pk_pk from activity3 ac join tabl1 t on ac.pk_pk=t.id where t.Interview_status = '%s' order by ac.id ASC";
            query = String.format(query, status);

            database.beginTransaction();
            Cursor c = database.rawQuery(query, null);
            if (c != null) {
                if (c.moveToFirst()) {
                    do {


                        list.add(c.getString(0) + "/" + c.getString(1) + "/" + c.getString(2) + "/" + c.getString(3));


                    } while (c.moveToNext());
                }
            }
        } finally {
            database.setTransactionSuccessful();
            database.endTransaction();
            database.close();
        }
        return list;
    }


    public List<String> getLogList_acivity1(String status) {

        ArrayList<String> list = new ArrayList<>();

        try {

            String query = "select id,Q1,Q2 from tabl1 where type_interview='Validation' and  Interview_status = '%s' order by id ASC";
            query = String.format(query, status);

            database.beginTransaction();
            Cursor c = database.rawQuery(query, null);
            if (c != null) {
                if (c.moveToFirst()) {
                    do {

                        list.add(c.getString(0) + "/" + c.getString(1) + "/" + c.getString(2) + "/" + "validation");


                    } while (c.moveToNext());
                }
            }
        } finally {
            database.setTransactionSuccessful();
            database.endTransaction();
            database.close();
        }
        return list;
    }


    public List<String> getLogList_acivity3(String status) {

        ArrayList<String> list = new ArrayList<>();

        try {

            String query = "select id,Q1,Q2 from tabl1 where type_interview='MR' and  Interview_status = '%s' order by id ASC";
            query = String.format(query, status);

            database.beginTransaction();
            Cursor c = database.rawQuery(query, null);
            if (c != null) {
                if (c.moveToFirst()) {
                    do {

                        list.add(c.getString(0) + "/" + c.getString(1) + "/" + c.getString(2) + "/" + "MR");


                    } while (c.moveToNext());
                }
            }
        } finally {
            database.setTransactionSuccessful();
            database.endTransaction();
            database.close();
        }
        return list;
    }


}






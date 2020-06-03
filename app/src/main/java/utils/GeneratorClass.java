package utils;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.cardview.widget.CardView;

import com.irfansyed.umeedenau.validation.Global;

import java.util.HashMap;

import data.LocalDataManager;

import static data.LocalDataManager.database;


public abstract class GeneratorClass {

    public static HashMap<String, String> Has_Map = new HashMap<>();

    public static HashMap<String, String> Insert_table(LinearLayout lv, boolean flag, String... convention) {


        for (int i = 0; i < lv.getChildCount(); i++) {
            View view = lv.getChildAt(i);

            String assig_id = convention.length > 0 ? convention[0] : "";

            if (view instanceof CardView) {
                for (int j = 0; j < ((CardView) view).getChildCount(); j++) {
                    View view1 = ((CardView) view).getChildAt(j);
                    if (view1 instanceof LinearLayout) {
                        Insert_table((LinearLayout) view1, false, assig_id);
                    }
                }
            } else if (view instanceof RadioGroup) {

                RadioGroup rdp = (RadioGroup) view;
                assig_id += ValidatorClass_1.getIDComponent(rdp);
                int rdbID = rdp.getCheckedRadioButtonId();

                if (rdbID != -1) {

                    for (byte j = 0; j < ((RadioGroup) view).getChildCount(); j++) {

                        if (rdbID == ((RadioGroup) view).getChildAt(j).getId()) {

                            RadioButton rdb = rdp.findViewById(((RadioGroup) view).getChildAt(j).getId());

                            Has_Map.put(assig_id, getValues(ValidatorClass_1.getIDComponent(rdb)));

                            break;
                        }

                    }
                } else {
                    Has_Map.put(assig_id, "0");
                }

            } else if (view instanceof EditText) {
                assig_id += ValidatorClass_1.getIDComponent(view);
                Has_Map.put(assig_id, ((EditText) view).getText().toString());
            } else if (view instanceof CheckBox) {
                assig_id += ValidatorClass_1.getIDComponent(view);
                if (((CheckBox) view).isChecked()) {
                    Has_Map.put(assig_id, getValues(assig_id));
                } else {
                    Has_Map.put(assig_id, "0");
                }
            } else if (view instanceof Spinner) {
                assig_id += ValidatorClass_1.getIDComponent(view);
                if (((Spinner) view).getSelectedItemPosition() != 0) {
                    Has_Map.put(assig_id, ((Spinner) view).getSelectedItem().toString());
                } else {
                    Has_Map.put(assig_id, "");
                }
            }

        }


        return Has_Map;
    }

    private static String getValues(String nameconv) {

        if (nameconv.length() > 0) {

            //String str = nameconv.substring(nameconv.length() - (nameconv.length() >= 2 ? 2 : 1)
            // );

            String[] strr = nameconv.split("_");
            String str = strr[1];
            char[] str_str = str.toLowerCase().toCharArray();


            if (str.charAt(str.length() - 1) <= '9') {
                return String.valueOf(Integer.parseInt(str));
            } else {

                String ascii_letters = "abcdefghijklmnopqrstuvwxyz";

                for (byte i = 0; i < ascii_letters.length(); i++) {
                    if (str_str[str.length() - 1] == ascii_letters.charAt(i)) {
                        return String.valueOf(i + 1);
                    }
                }

                return "0";

            }
        } else {
            return "";
        }
    }


    public static void inert_db(String table_Name, Context mContext, HashMap<String, String> has_columns) {


        Has_Map.putAll(has_columns);


        Object[] keys = Has_Map.keySet().toArray();
        String vv = "";
        String kkg = "";

        String query = "insert into " + table_Name + "(";

        for (int i = 0; i < Has_Map.size(); i++) {
            String value = Has_Map.get(keys[i]);
            String kk = keys[i].toString();
            if (i == 0) {


                vv = kk;
                kkg = value;
            } else {

                vv = vv + "," + kk;
                kkg = kkg + "','" + value;
            }

        }
        query = query + vv + ") values('" + kkg + "')";


        query = String.format(query);

        LocalDataManager validationactivity = new LocalDataManager(mContext);

        database.execSQL(query);

        int i = 0;
    }


    public static int hh_section_count(String tbl_name, Context mContext) {
        int count = 0;

        String query2 = "select lhw.id from  TableLHWSection lhw join " + tbl_name + " tb2 " +
                " on lhw.id=tb2.LhwSectionPKId where lhw.status='0' and tb2.LhwSectionPKId='" + Global.LhwSection_id + "'";

        LocalDataManager Lm = new LocalDataManager(mContext);
        Cursor c = database.rawQuery(query2, null);


        if (c != null) {
            if (c.moveToFirst()) {
                do {


                    count++;


                } while (c.moveToNext());
            }
        }

        return count;


    }


    public static void HH_update_Form1(String tbl_name, String pk_id, Context mContext) {

        String query = "update " + tbl_name + " set Status='1' where id=" + pk_id;

        LocalDataManager validationactivity = new LocalDataManager(mContext);

        database.execSQL(query);
    }

    public static void LHWSectionUpdateCOunt(String COlumn_name, int pk_id, Context mContext) {

        int count = 0;
        String query2 = "select " + COlumn_name + " from TableLHWSection where id=" + pk_id;
        Cursor c = database.rawQuery(query2, null);

        if (c != null) {
            if (c.moveToFirst()) {
                do {

                    count = c.getInt(0);

                } while (c.moveToNext());
            }
        }
        count = count + 1;

        String query = "update TableLHWSection set " + COlumn_name + "='" + count + "' where id=" + pk_id;

        LocalDataManager validationactivity = new LocalDataManager(mContext);

        database.execSQL(query);
    }

    public static boolean LHWsectionStatus(String table_name) {


        String query2 = "select * from " + table_name + " where LhwSectionPKId=" + Global.LhwSection_id;

        Cursor c = database.rawQuery(query2, null);


        if (c != null) {
            if (c.moveToFirst()) {
                do {


                    return false;


                } while (c.moveToNext());
            }
        }

        return true;


    }


    public static boolean checktextbox(EditText ed1, EditText ed2) {
        int i_ed1, i_ed2;
        i_ed1 = Integer.parseInt(ed1.getText().toString());
        i_ed2 = Integer.parseInt(ed2.getText().toString());


        if (i_ed2 > i_ed1 && i_ed1 != 00) {
            ed1.setError("Should be greater then HH ");
            ed2.setError("Should be Less then Sourcer Regsiter ");
            ed1.requestFocus();
            return false;

        }

        return true;
    }
}

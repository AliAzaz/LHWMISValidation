package com.irfansyed.umeedenau.validation;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.irfansyed.umeedenau.validation.databinding.Form4sectionbBinding;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import data.LocalDataManager;
import utils.ClearAllcontrol;
import utils.GeneratorClass;
import utils.GetGpsHideForm;
import utils.ValidatorClass;

import static data.LocalDataManager.database;


public class Form4SectionB extends AppCompatActivity implements View.OnClickListener, RadioButton.OnCheckedChangeListener {


    //region Initialization
    Form4sectionbBinding bin;
    String Lat, Long;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bin = DataBindingUtil.setContentView(this, R.layout.form4sectionb);


        select_Member();

        bin.lhwf4b11.setOnCheckedChangeListener(this);
        bin.lhwf4b12.setOnCheckedChangeListener(this);

        bin.lhwf4b22.setOnCheckedChangeListener(this);
        bin.lhwf4b22.setOnCheckedChangeListener(this);

        bin.btnNext.setOnClickListener(this);

        String gps_ = GetGpsHideForm.get_gps(this);
        String[] gps = gps_.split("/");
        Lat = gps[0];
        Long = gps[1];


    }


    String Fk_id = "";

    void select_Member() {


        bin.lhwf4b0.setAdapter(null);

        ArrayList<String> lst_member = new ArrayList<>();
        String query = "select lhwf3b4a,lhwf3b4b,lhwf3b4c,id from TableF3SectionB where Status='0' and FK_id=" + Global.LhwSection_id;


        query = String.format(query);

        LocalDataManager Lm = new LocalDataManager(this);
        Cursor c = database.rawQuery(query, null);


        lst_member.add("Select member");
        if (c != null) {
            if (c.moveToFirst()) {
                do {

                    String Member1 = c.getString(0);
                    String Member2 = c.getString(1);
                    String Member3 = c.getString(2);

                    Fk_id = c.getString(3);

                    if (check_member(Member1) == false) {
                        lst_member.add(c.getString(0));
                    }

                    if (check_member(Member2) == false) {
                        lst_member.add(c.getString(1));
                    }

                    if (check_member(Member3) == false) {
                        lst_member.add(c.getString(2));
                    }


                } while (c.moveToNext());
            }
        }


        ArrayAdapter<String> dataAdapterD = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lst_member);

        dataAdapterD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bin.lhwf4b0.setAdapter(dataAdapterD);


        bin.lhwf4b0.setSelection(0);


    }

    boolean check_member(String meberName) {
        boolean bol = false;
        String query = "select lhwf4b0 from TableF4SectionB where lhwf4b0='" + meberName + "' and  FK_id=" + Fk_id;


        query = String.format(query);

        LocalDataManager Lm = new LocalDataManager(this);
        Cursor c = database.rawQuery(query, null);


        if (c != null) {
            if (c.moveToFirst()) {
                do {

                    bol = true;

                } while (c.moveToNext());
            }
        }

        return bol;

    }


    @Override
    public void onClick(View view) {

        if (!formValidation()) {
            return;
        }

        insert_data();
        Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();

        finish();
    }


    private boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, bin.SectionB);
    }


    void insert_data() {
        HashMap<String, String> Has_Map = new HashMap<>();
        GeneratorClass.Has_Map.clear();


        Has_Map.put("FK_id", Fk_id);

        String start_time = DateFormat.getDateTimeInstance().format(new Date());

        Has_Map.put(Global.GPSLat, Lat);
        Has_Map.put(Global.GPSLong, Long);
        Has_Map.put(Global.InterviewTime, start_time);


        //GeneratorClass.Insert_table(bin.SectionB,true);

        GeneratorClass.getContainerJSON(bin.SectionB, true);
        GeneratorClass.inert_db("TableF4SectionB", this, Has_Map);
        GeneratorClass.LHWSectionUpdateCOunt("LHWCommunityVHCCount", Global.LhwSection_id, this);


    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (buttonView.getId() == R.id.lhwf4b1_1 || buttonView.getId() == R.id.lhwf4b1_2) {


            if (bin.lhwf4b12.isChecked()) {

                ClearAllcontrol.ClearAll(bin.LvLhwf4b2);
                ClearAllcontrol.ClearAll(bin.LvLhwf4b3);


                ClearAllcontrol.ClearAll(bin.LvLhwf4b5);
                ClearAllcontrol.ClearAll(bin.LvLhwf4b5);
            }

        }

        if (buttonView.getId() == R.id.lhwf4b2_1 || buttonView.getId() == R.id.lhwf4b2_2) {


            if (bin.lhwf4b22.isChecked()) {


                ClearAllcontrol.ClearAll(bin.LvLhwf4b3);


                ClearAllcontrol.ClearAll(bin.LvLhwf4b5);
                ClearAllcontrol.ClearAll(bin.LvLhwf4b5);
            }

        }
    }
}

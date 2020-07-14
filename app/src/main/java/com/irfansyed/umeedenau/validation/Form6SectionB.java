package com.irfansyed.umeedenau.validation;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.irfansyed.umeedenau.validation.databinding.Form6sectionbBinding;
import com.shashank.sony.fancytoastlib.FancyToast;
import com.validatorcrawler.aliazaz.Clear;

import java.lang.reflect.Field;
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
import static utils.ValidatorClass.EmptySpinner;


public class Form6SectionB extends AppCompatActivity implements View.OnClickListener {


    //region Initialization
    Form6sectionbBinding bin;
    String Lat, Long;
    private int PhotoSerial;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bin = DataBindingUtil.setContentView(this, R.layout.form6sectionb);

        select_Member();

        setupSkips();

        PhotoSerial = 0;

        /*bin.lhwf6b11.setOnCheckedChangeListener(this);
        bin.lhwf6b12.setOnCheckedChangeListener(this);
        bin.lhwf6b21.setOnCheckedChangeListener(this);
        bin.lhwf6b22.setOnCheckedChangeListener(this);*/

        bin.btnNext.setOnClickListener(this);

        String gps_ = GetGpsHideForm.get_gps(this);
        String[] gps = gps_.split("/");
        Lat = gps[0];
        Long = gps[1];
    }

    private void setupSkips() {

        bin.lhwf6b1.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (bin.lhwf6b12.isChecked()) {

                bin.cvlhwf6b2.setVisibility(View.GONE);
                bin.cvlhwf6b3.setVisibility(View.GONE);
                bin.cvlhwf6b4.setVisibility(View.GONE);
                bin.cvlhwf6b5.setVisibility(View.GONE);
                bin.cvlhwf6b6.setVisibility(View.GONE);

                Clear.clearAllFields(bin.cvlhwf6b2);
                Clear.clearAllFields(bin.cvlhwf6b3);
                Clear.clearAllFields(bin.cvlhwf6b4);
                Clear.clearAllFields(bin.cvlhwf6b5);
                Clear.clearAllFields(bin.cvlhwf6b6);

            } else if (bin.lhwf6b11.isChecked()) {

                bin.cvlhwf6b2.setVisibility(View.VISIBLE);
                bin.cvlhwf6b3.setVisibility(View.VISIBLE);
                bin.cvlhwf6b4.setVisibility(View.VISIBLE);
                bin.cvlhwf6b5.setVisibility(View.VISIBLE);
                bin.cvlhwf6b6.setVisibility(View.VISIBLE);
            }
        }));


        bin.lhwf6b2.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (bin.lhwf6b22.isChecked()) {

                bin.cvlhwf6b3.setVisibility(View.GONE);
                bin.cvlhwf6b4.setVisibility(View.GONE);
                bin.cvlhwf6b5.setVisibility(View.GONE);
                bin.cvlhwf6b6.setVisibility(View.GONE);

                Clear.clearAllFields(bin.cvlhwf6b3);
                Clear.clearAllFields(bin.cvlhwf6b4);
                Clear.clearAllFields(bin.cvlhwf6b5);
                Clear.clearAllFields(bin.cvlhwf6b6);

            } else if (bin.lhwf6b21.isChecked()) {

                bin.cvlhwf6b3.setVisibility(View.VISIBLE);
                bin.cvlhwf6b4.setVisibility(View.VISIBLE);
                bin.cvlhwf6b5.setVisibility(View.VISIBLE);
                bin.cvlhwf6b6.setVisibility(View.VISIBLE);
            }
        }));


        bin.lhwf6b4.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!bin.lhwf6b4.getText().toString().equals("") && bin.lhwf6b4.getText().toString() != null) {

                    if (Integer.valueOf(bin.lhwf6b4.getText().toString()) >= 0 && Integer.valueOf(bin.lhwf6b4.getText().toString()) <= 4) {
                        bin.cvlhwf6b6.setVisibility(View.GONE);
                        Clear.clearAllFields(bin.cvlhwf6b6);
                    } else {
                        bin.cvlhwf6b6.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

    }


    String Fk_id = "";

    void select_Member() {

        bin.lhwf6b0.setAdapter(null);

        ArrayList<String> lst_member = new ArrayList<>();
        String query = "select lhwf5b5a,lhwf5b5b,lhwf5b5c,id from TableF5SectionB where Status='0' and FK_id=" + Global.LhwSection_id;


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
        bin.lhwf6b0.setAdapter(dataAdapterD);


        bin.lhwf6b0.setSelection(0);
    }

    boolean check_member(String meberName) {
        boolean bol = false;
        String query = "select lhwf6b0 from TableF6SectionB where lhwf6b0='" + meberName + "' and  FK_id=" + Fk_id;


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

        if (bin.lhwf6b0.getSelectedItem().equals("Select member")) {
            Toast.makeText(this, "Please select member", Toast.LENGTH_SHORT).show();
            return false;
        }

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


        GeneratorClass.getContainerJSON(bin.SectionB, true);
        GeneratorClass.inert_db("TableF6SectionB", this, Has_Map);
        GeneratorClass.LHWSectionUpdateCOunt("LHWCommunityWSGCount", Global.LhwSection_id, this);

    }
}
package com.irfansyed.umeedenau.validation;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.irfansyed.umeedenau.validation.databinding.Form2sectiongBinding;
import com.validatorcrawler.aliazaz.Clear;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;

import data.LocalDataManager;
import utils.ClearAllcontrol;
import utils.GeneratorClass;
import utils.GetGpsHideForm;
import utils.ValidatorClass;

import static data.LocalDataManager.database;


public class Form2SectionG extends AppCompatActivity implements View.OnClickListener {


    //region Initialization
    Form2sectiongBinding bin;
    String FK_id;
    String Lat, Long;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bin = DataBindingUtil.setContentView(this, R.layout.form2sectiong);

        setupSkips();


        Intent iin = getIntent();
        Bundle b = iin.getExtras();
        if (b != null) {
            FK_id = (String) b.get("pk_id");
            this.ininfo();
        }


        bin.btnNext.setOnClickListener(this);

        String gps_ = GetGpsHideForm.get_gps(this);
        String[] gps = gps_.split("/");
        Lat = gps[0];
        Long = gps[1];


        /*bin.rlhwf2g11.setOnCheckedChangeListener(this);
        bin.rlhwf2g12.setOnCheckedChangeListener(this);
        bin.lhwf2g5a2.setOnCheckedChangeListener(this);
        bin.lhwf2g5a1.setOnCheckedChangeListener(this);*/

    }


    private void setupSkips() {

        bin.lhwf2g1.setOnCheckedChangeListener((radioGroup, i) -> {

            Clear.clearAllFields(bin.cvlhwf2g3);
            Clear.clearAllFields(bin.cvlhwf2g4);
            Clear.clearAllFields(bin.cvlhwf2g5);
            Clear.clearAllFields(bin.cvlhwf2g5a);
            Clear.clearAllFields(bin.cvlhwf2g6);
            Clear.clearAllFields(bin.cvlhwf2g7);
            Clear.clearAllFields(bin.cvlhwf2g7a);
            Clear.clearAllFields(bin.cvlhwf2g10);
            Clear.clearAllFields(bin.cvlhwf2g11a);


            bin.cvlhwf2g3.setVisibility(View.GONE);
            bin.cvlhwf2g4.setVisibility(View.GONE);
            bin.cvlhwf2g5.setVisibility(View.GONE);
            bin.cvlhwf2g5a.setVisibility(View.GONE);
            bin.cvlhwf2g6.setVisibility(View.GONE);
            bin.cvlhwf2g7.setVisibility(View.GONE);
            bin.cvlhwf2g7a.setVisibility(View.GONE);
            bin.cvlhwf2g8.setVisibility(View.VISIBLE);
            bin.cvlhwf2g10.setVisibility(View.GONE);
            bin.cvlhwf2g11a.setVisibility(View.GONE);
            bin.cvheadingqi.setVisibility(View.GONE);


            if (i == bin.rlhwf2g11.getId()) {
                bin.cvlhwf2g3.setVisibility(View.VISIBLE);
                bin.cvlhwf2g4.setVisibility(View.VISIBLE);
                bin.cvlhwf2g5.setVisibility(View.VISIBLE);
                bin.cvlhwf2g5a.setVisibility(View.VISIBLE);
                bin.cvlhwf2g6.setVisibility(View.VISIBLE);
                bin.cvlhwf2g7.setVisibility(View.VISIBLE);
                bin.cvlhwf2g7a.setVisibility(View.VISIBLE);
                bin.cvlhwf2g8.setVisibility(View.VISIBLE);
                bin.cvlhwf2g10.setVisibility(View.VISIBLE);
                bin.cvlhwf2g11a.setVisibility(View.VISIBLE);
                bin.cvheadingqi.setVisibility(View.VISIBLE);

                bin.cvlhwf2g3a.setVisibility(View.GONE);
                Clear.clearAllFields(bin.cvlhwf2g3a);

            } else {
                bin.cvlhwf2g3a.setVisibility(View.VISIBLE);
            }
        });


        bin.lhwf2g3.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!bin.lhwf2g3.getText().toString().equals("")) {

                    if (Integer.valueOf(bin.lhwf2g3.getText().toString()) > 0) {
                        bin.cvlhwf2g3a.setVisibility(View.GONE);
                        Clear.clearAllFields(bin.cvlhwf2g3a);
                    } else {
                        bin.cvlhwf2g3a.setVisibility(View.VISIBLE);
                    }

                }
            }
        });


        bin.lhwf2g5a.setOnCheckedChangeListener((radioGroup, i) -> {
            Clear.clearAllFields(bin.cvlhwf2g6);
            Clear.clearAllFields(bin.cvlhwf2g7);
            Clear.clearAllFields(bin.cvlhwf2g7a);
            //Clear.clearAllFields(bin.cvlhwf2g8);
            //Clear.clearAllFields(bin.cvlhwf2g9);

            bin.cvlhwf2g6.setVisibility(View.GONE);
            bin.cvlhwf2g7.setVisibility(View.GONE);
            bin.cvlhwf2g7a.setVisibility(View.GONE);
            /*bin.cvlhwf2g8.setVisibility(View.GONE);
            bin.cvlhwf2g9.setVisibility(View.GONE);*/

            if (i == bin.lhwf2g5a1.getId()) {
                bin.cvlhwf2g6.setVisibility(View.VISIBLE);
                bin.cvlhwf2g7.setVisibility(View.VISIBLE);
                bin.cvlhwf2g7a.setVisibility(View.VISIBLE);
                /*bin.cvlhwf2g8.setVisibility(View.VISIBLE);
                bin.cvlhwf2g9.setVisibility(View.VISIBLE);*/
            } else {

            }
        });


        bin.lhwf2g7a.setOnCheckedChangeListener((radioGroup, i) -> {
            if (i == bin.lhwf2g7a3.getId()) {
                bin.cvlhwf2g8.setVisibility(View.VISIBLE);
            } else if (i != bin.lhwf2g7a3.getId()){
                bin.cvlhwf2g8.setVisibility(View.GONE);
                Clear.clearAllFields(bin.cvlhwf2g8);
            }
        });


    }


    @Override
    public void onClick(View view) {
        if (!formValidation() && !bin.lhwf2g3.getText().toString().equals("999")) {
            return;
        }


        if (bin.lhwf2g3.getText().toString().length() > 0) {
            int a = Integer.parseInt(bin.lhwf2g3.getText().toString());
            if (a > 20) {
                bin.lhwf2g3.setError("should be less then 20");
                bin.lhwf2g3.requestFocus();
                return;
            }
        }

        if (bin.lhwf2g5.getText().toString().length() > 0) {
            // int a=Integer.parseInt(bin.lhwf2g5.getText().toString());
            // if(a>59)
            // {
            //     bin.lhwf2g5.setError("should be less then 60");
            //     bin.lhwf2g5.requestFocus();
            //     return;
            // }
        }
        insert_data();


        Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();
        Intent intt = new Intent(getBaseContext(), PendingInterviewsHH.class);
        startActivity(intt);

        finish();

    }

    private boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, bin.SectionG);
    }


    void insert_data() {
        HashMap<String, String> Has_Map = new HashMap<>();
        GeneratorClass.Has_Map.clear();


        Has_Map.put("FK_id", FK_id + "");
        Has_Map.put("LhwSectionPKId", Global.LhwSection_id + "");

        String start_time = DateFormat.getDateTimeInstance().format(new Date());

        Has_Map.put(Global.GPSLat, Lat);
        Has_Map.put(Global.GPSLong, Long);
        Has_Map.put(Global.InterviewTime, start_time);


        //GeneratorClass.Insert_table(bin.SectionG, true);


        GeneratorClass.getContainerJSON(bin.SectionG, true);
        GeneratorClass.inert_db("TableF2SectionG", this, Has_Map);
        GeneratorClass.HH_update_Form1("TableF1SectionG", FK_id, this);
        GeneratorClass.LHWSectionUpdateCOunt("LHWCommunityHHCount", Global.LhwSection_id, this);


    }


    public void ininfo() {

        String query2 = "select lhwf1g4  from TableF1SectionG  where id=" + FK_id;

        LocalDataManager Lm = new LocalDataManager(this);
        Cursor c = database.rawQuery(query2, null);


        if (c != null) {
            if (c.moveToFirst()) {
                do {


                    bin.lhwf2g4.setText(c.getString(0));


                } while (c.moveToNext());
            }

        }


    }


    /*@Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (buttonView.getId() == R.id.rlhwf2g1_1 || buttonView.getId() == R.id.rlhwf2g1_2) {

            if (bin.rlhwf2g12.isChecked() == true) {


                //  bin.LvLhwf2g3.setVisibility(View.GONE);
                //  bin.LvLhwf2g4.setVisibility(View.GONE);
                //  bin.LvLhwf2g5.setVisibility(View.GONE);
                //  bin.LvLhwf2g5a.setVisibility(View.GONE);
                //  bin.LvLhwf2g6.setVisibility(View.GONE);
                //  bin.LvLhwf2g7.setVisibility(View.GONE);
                //  bin.LvLhwf2g8.setVisibility(View.GONE);
                //  bin.LvLhwf2g9.setVisibility(View.GONE);
                //  bin.LvLhwf2g10.setVisibility(View.GONE);
                //  bin.LvLhwf2g11.setVisibility(View.GONE);


                ClearAllcontrol.ClearAll(bin.LvLhwf2g3);
                ClearAllcontrol.ClearAll(bin.LvLhwf2g4);
                ClearAllcontrol.ClearAll(bin.LvLhwf2g5);
                ClearAllcontrol.ClearAll(bin.LvLhwf2g5a);
                ClearAllcontrol.ClearAll(bin.LvLhwf2g6);
                ClearAllcontrol.ClearAll(bin.LvLhwf2g7);
                ClearAllcontrol.ClearAll(bin.LvLhwf2g8);
                ClearAllcontrol.ClearAll(bin.LvLhwf2g9);
                ClearAllcontrol.ClearAll(bin.LvLhwf2g10);
                ClearAllcontrol.ClearAll(bin.LvLhwf2g11);

            } else {
                // bin.LvLhwf2g3.setVisibility(View.VISIBLE);
                // bin.LvLhwf2g4.setVisibility(View.VISIBLE);
                // bin.LvLhwf2g5.setVisibility(View.VISIBLE);
                // bin.LvLhwf2g5a.setVisibility(View.VISIBLE);
                // bin.LvLhwf2g6.setVisibility(View.VISIBLE);
                // bin.LvLhwf2g7.setVisibility(View.VISIBLE);
                // bin.LvLhwf2g8.setVisibility(View.VISIBLE);
                // bin.LvLhwf2g9.setVisibility(View.VISIBLE);
                // bin.LvLhwf2g10.setVisibility(View.VISIBLE);
                // bin.LvLhwf2g11.setVisibility(View.VISIBLE);
            }

        }


        if (buttonView.getId() == R.id.lhwf2g5a_1 || buttonView.getId() == R.id.lhwf2g5a_2) {

            if (bin.lhwf2g5a2.isChecked() == true) {


                // bin.LvLhwf2g6.setVisibility(View.GONE);
                // bin.LvLhwf2g7.setVisibility(View.GONE);
                // bin.LvLhwf2g8.setVisibility(View.GONE);
                // bin.LvLhwf2g9.setVisibility(View.GONE);
                // bin.LvLhwf2g10.setVisibility(View.GONE);
                // bin.LvLhwf2g11.setVisibility(View.GONE);


                ClearAllcontrol.ClearAll(bin.LvLhwf2g6);
                ClearAllcontrol.ClearAll(bin.LvLhwf2g7);
                ClearAllcontrol.ClearAll(bin.LvLhwf2g8);
                ClearAllcontrol.ClearAll(bin.LvLhwf2g9);
                ClearAllcontrol.ClearAll(bin.LvLhwf2g10);
                ClearAllcontrol.ClearAll(bin.LvLhwf2g11);

            } else {

                //bin.LvLhwf2g6.setVisibility(View.VISIBLE);
                //bin.LvLhwf2g7.setVisibility(View.VISIBLE);
                //bin.LvLhwf2g8.setVisibility(View.VISIBLE);
                //bin.LvLhwf2g9.setVisibility(View.VISIBLE);
                //bin.LvLhwf2g10.setVisibility(View.VISIBLE);
                //bin.LvLhwf2g11.setVisibility(View.VISIBLE);
            }

        }
    }*/

}
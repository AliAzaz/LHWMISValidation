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

import com.irfansyed.umeedenau.validation.databinding.Form2sectiondBinding;
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


public class Form2SectionD extends AppCompatActivity implements View.OnClickListener {


    //region Initialization
    Form2sectiondBinding bin;
    String FK_id;
    String Lat, Long;

    RadioButton lhwf2d1_1, lhwf2d1_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bin = DataBindingUtil.setContentView(this, R.layout.form2sectiond);

        setupSkips();


        Intent iin = getIntent();
        Bundle b = iin.getExtras();
        if (b != null) {
            FK_id = (String) b.get("pk_id");
            this.ininfo();
        }


        lhwf2d1_1 = findViewById(R.id.rlhwf2d1_1);
        lhwf2d1_2 = findViewById(R.id.rlhwf2d1_2);

        /*lhwf2d1_1.setOnCheckedChangeListener(this);
        lhwf2d1_2.setOnCheckedChangeListener(this);
        bin.lhwf2d71.setOnCheckedChangeListener(this);
        bin.lhwf2d72.setOnCheckedChangeListener(this);
        bin.lhwf2d73.setOnCheckedChangeListener(this);

        bin.lhwf2d91.setOnCheckedChangeListener(this);
        bin.lhwf2d92.setOnCheckedChangeListener(this);
        bin.lhwf2d93.setOnCheckedChangeListener(this);*/

        bin.btnNext.setOnClickListener(this);


        String gps_ = GetGpsHideForm.get_gps(this);
        String[] gps = gps_.split("/");
        Lat = gps[0];
        Long = gps[1];

    }


    private void setupSkips() {

        bin.lhwf2d1.setOnCheckedChangeListener((radioGroup, i) -> {

            if (i == bin.rlhwf2d11.getId()) {
                bin.cvlhwf2d2.setVisibility(View.VISIBLE);
                bin.cvlhwf2d2a.setVisibility(View.VISIBLE);
                bin.cvlhwf2d3.setVisibility(View.VISIBLE);
                bin.cvlhwf2d4.setVisibility(View.VISIBLE);
                bin.cvlhwf2d5.setVisibility(View.VISIBLE);
                bin.cvlhwf2d6.setVisibility(View.VISIBLE);
                bin.cvlhwf2d6a.setVisibility(View.VISIBLE);
                bin.cvlhwf2d7.setVisibility(View.VISIBLE);
                bin.cvlhwf2d8.setVisibility(View.VISIBLE);
                bin.cvlhwf2d9.setVisibility(View.VISIBLE);
                bin.cvlhwf2d10.setVisibility(View.VISIBLE);
                bin.cvlhwf2d11.setVisibility(View.VISIBLE);
                bin.cvlhwf2d12.setVisibility(View.VISIBLE);
                bin.cvheadingqi.setVisibility(View.VISIBLE);

                bin.cvlhwf2d2b.setVisibility(View.GONE);
                Clear.clearAllFields(bin.cvlhwf2d2b);


            } else {
                bin.cvlhwf2d2.setVisibility(View.GONE);
                bin.cvlhwf2d2a.setVisibility(View.GONE);
                bin.cvlhwf2d3.setVisibility(View.GONE);
                bin.cvlhwf2d4.setVisibility(View.GONE);
                bin.cvlhwf2d5.setVisibility(View.GONE);
                bin.cvlhwf2d6.setVisibility(View.GONE);
                bin.cvlhwf2d6a.setVisibility(View.GONE);
                bin.cvlhwf2d7.setVisibility(View.GONE);
                bin.cvlhwf2d8.setVisibility(View.GONE);
                bin.cvlhwf2d9.setVisibility(View.GONE);
                bin.cvlhwf2d10.setVisibility(View.GONE);
                bin.cvlhwf2d11.setVisibility(View.GONE);
                bin.cvlhwf2d12.setVisibility(View.GONE);
                bin.cvheadingqi.setVisibility(View.GONE);

                bin.cvlhwf2d2b.setVisibility(View.VISIBLE);

                Clear.clearAllFields(bin.cvlhwf2d2);
                Clear.clearAllFields(bin.cvlhwf2d2a);
                Clear.clearAllFields(bin.cvlhwf2d5);
                Clear.clearAllFields(bin.cvlhwf2d6);
                Clear.clearAllFields(bin.cvlhwf2d6a);
                Clear.clearAllFields(bin.cvlhwf2d7);
                Clear.clearAllFields(bin.cvlhwf2d8);
                Clear.clearAllFields(bin.cvlhwf2d9);
                Clear.clearAllFields(bin.cvlhwf2d10);
                Clear.clearAllFields(bin.cvlhwf2d11);
                Clear.clearAllFields(bin.cvlhwf2d12);

            }
        });


        bin.lhwf2d2a.setOnCheckedChangeListener((radioGroup, i) -> {

            if (i == bin.lhwf2d2a1.getId()) {

                bin.cvlhwf2d3.setVisibility(View.VISIBLE);
                bin.cvlhwf2d4.setVisibility(View.VISIBLE);
                bin.cvlhwf2d5.setVisibility(View.VISIBLE);
                bin.cvlhwf2d6.setVisibility(View.VISIBLE);
                bin.cvlhwf2d6a.setVisibility(View.VISIBLE);
                bin.cvlhwf2d7.setVisibility(View.VISIBLE);
                bin.cvlhwf2d8.setVisibility(View.VISIBLE);
                bin.cvlhwf2d9.setVisibility(View.VISIBLE);
                bin.cvlhwf2d10.setVisibility(View.VISIBLE);
                bin.cvlhwf2d11.setVisibility(View.VISIBLE);
                bin.cvlhwf2d12.setVisibility(View.VISIBLE);
                bin.cvheadingqi.setVisibility(View.VISIBLE);

                bin.cvlhwf2d2b.setVisibility(View.GONE);
                Clear.clearAllFields(bin.cvlhwf2d2b);


            } else {

                bin.cvlhwf2d3.setVisibility(View.GONE);
                bin.cvlhwf2d4.setVisibility(View.GONE);
                bin.cvlhwf2d5.setVisibility(View.GONE);
                bin.cvlhwf2d6.setVisibility(View.GONE);

                bin.cvlhwf2d7.setVisibility(View.GONE);
                bin.cvlhwf2d8.setVisibility(View.GONE);
                bin.cvlhwf2d9.setVisibility(View.GONE);
                bin.cvlhwf2d10.setVisibility(View.GONE);
                bin.cvlhwf2d11.setVisibility(View.GONE);
                bin.cvlhwf2d12.setVisibility(View.GONE);
                bin.cvheadingqi.setVisibility(View.GONE);

                bin.cvlhwf2d2b.setVisibility(View.VISIBLE);

                if (i == bin.rlhwf2d12.getId()) {
                    bin.cvlhwf2d6a.setVisibility(View.VISIBLE);
                }

                Clear.clearAllFields(bin.cvlhwf2d5);
                Clear.clearAllFields(bin.cvlhwf2d6);
                Clear.clearAllFields(bin.cvlhwf2d7);
                Clear.clearAllFields(bin.cvlhwf2d8);
                Clear.clearAllFields(bin.cvlhwf2d9);
                Clear.clearAllFields(bin.cvlhwf2d10);
                Clear.clearAllFields(bin.cvlhwf2d11);
                Clear.clearAllFields(bin.cvlhwf2d12);

            }
        });


        bin.lhwf2d7.setOnCheckedChangeListener((radioGroup, i) -> {

            if (i == bin.lhwf2d71.getId()) {
                bin.cvlhwf2d8.setVisibility(View.VISIBLE);
            } else {
                bin.cvlhwf2d8.setVisibility(View.GONE);
                Clear.clearAllFields(bin.cvlhwf2d8);
            }
        });


        bin.lhwf2d9.setOnCheckedChangeListener((radioGroup, i) -> {
            if (i == bin.lhwf2d91.getId()) {
                bin.cvlhwf2d10.setVisibility(View.VISIBLE);
                bin.cvlhwf2d11.setVisibility(View.VISIBLE);
                bin.cvlhwf2d12.setVisibility(View.VISIBLE);
            } else {
                bin.cvlhwf2d10.setVisibility(View.GONE);
                bin.cvlhwf2d11.setVisibility(View.GONE);
                bin.cvlhwf2d12.setVisibility(View.GONE);

                Clear.clearAllFields(bin.cvlhwf2d10);
                Clear.clearAllFields(bin.cvlhwf2d11);
                Clear.clearAllFields(bin.cvlhwf2d12);
            }
        });
    }


    @Override
    public void onClick(View view) {
        if (!formValidation() && !bin.lhwf2d2.getText().toString().equals("999")) {
            return;
        }


        if (bin.lhwf2d2.getText().toString().length() > 0) {
            int a = Integer.parseInt(bin.lhwf2d2.getText().toString());
            if (a > 5) {
                bin.lhwf2d2.setError("should be less then 5");
                bin.lhwf2d2.requestFocus();
                return;
            }
        }
        insert_data();


        Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();
        Intent intt = new Intent(getBaseContext(), PendingInterviewsHH.class);
        startActivity(intt);

        finish();

    }


    /*public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (buttonView.getId() == R.id.lhwf2d7_1 || buttonView.getId() == R.id.lhwf2d7_2 || buttonView.getId() == R.id.lhwf2d7_3) {

            ClearAllcontrol.ClearAll(bin.LvLhwf2d8);

        }

        if (buttonView.getId() == R.id.lhwf2d9_1 || buttonView.getId() == R.id.lhwf2d9_2 || buttonView.getId() == R.id.lhwf2d9_3) {

            ClearAllcontrol.ClearAll(bin.LvLhwf2d10);
            ClearAllcontrol.ClearAll(bin.LvLhwf2d11);
            ClearAllcontrol.ClearAll(bin.LvLhwf2d12);

        }

        if (buttonView.getId() == R.id.rlhwf2d1_1 || buttonView.getId() == R.id.rlhwf2d1_2) {

            if (lhwf2d1_2.isChecked() == true) {

                // bin.LvLhwf2d2.setVisibility(View.GONE);
                // bin.LvLhwf2d3.setVisibility(View.GONE);
                // bin.LvLhwf2d4.setVisibility(View.GONE);
                // bin.LvLhwf2d5.setVisibility(View.GONE);
                // bin.LvLhwf2d6.setVisibility(View.GONE);
                // bin.LvLhwf2d6a.setVisibility(View.GONE);
                // bin.LvLhwf2d7.setVisibility(View.GONE);
                // bin.LvLhwf2d8.setVisibility(View.GONE);
                // bin.LvLhwf2d9.setVisibility(View.GONE);
                // bin.LvLhwf2d10.setVisibility(View.GONE);
                // bin.LvLhwf2d11.setVisibility(View.GONE);
                // bin.LvLhwf2d12.setVisibility(View.GONE);


                ClearAllcontrol.ClearAll(bin.LvLhwf2d2);
                ClearAllcontrol.ClearAll(bin.LvLhwf2d3);
                ClearAllcontrol.ClearAll(bin.LvLhwf2d4);
                ClearAllcontrol.ClearAll(bin.LvLhwf2d5);
                ClearAllcontrol.ClearAll(bin.LvLhwf2d6);
                ClearAllcontrol.ClearAll(bin.LvLhwf2d6a);
                ClearAllcontrol.ClearAll(bin.LvLhwf2d7);
                ClearAllcontrol.ClearAll(bin.LvLhwf2d8);
                ClearAllcontrol.ClearAll(bin.LvLhwf2d9);
                ClearAllcontrol.ClearAll(bin.LvLhwf2d10);
                ClearAllcontrol.ClearAll(bin.LvLhwf2d11);
                ClearAllcontrol.ClearAll(bin.LvLhwf2d12);


            } else {


                // bin.LvLhwf2d2.setVisibility(View.VISIBLE);
                // bin.LvLhwf2d3.setVisibility(View.VISIBLE);
                // bin.LvLhwf2d4.setVisibility(View.VISIBLE);
                // bin.LvLhwf2d5.setVisibility(View.VISIBLE);
                // bin.LvLhwf2d6.setVisibility(View.VISIBLE);
                // bin.LvLhwf2d6a.setVisibility(View.VISIBLE);
                // bin.LvLhwf2d7.setVisibility(View.VISIBLE);
                // bin.LvLhwf2d8.setVisibility(View.VISIBLE);
                // bin.LvLhwf2d9.setVisibility(View.VISIBLE);
                // bin.LvLhwf2d10.setVisibility(View.VISIBLE);
                // bin.LvLhwf2d11.setVisibility(View.VISIBLE);
                // bin.LvLhwf2d12.setVisibility(View.VISIBLE);
            }
        }
    }*/

    private boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, bin.SectionD);
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


        //GeneratorClass.Insert_table(bin.SectionD, true);

        GeneratorClass.getContainerJSON(bin.SectionD, true);
        GeneratorClass.inert_db("TableF2SectionD", this, Has_Map);
        GeneratorClass.HH_update_Form1("TableF1SectionD", FK_id, this);
        GeneratorClass.LHWSectionUpdateCOunt("LHWCommunityHHCount", Global.LhwSection_id, this);


    }


    public void ininfo() {

        String query2 = "select lhwf1d3,lhwf1d4  from TableF1SectionD  where id=" + FK_id;

        LocalDataManager Lm = new LocalDataManager(this);
        Cursor c = database.rawQuery(query2, null);


        if (c != null) {
            if (c.moveToFirst()) {
                do {

                    bin.lhwf2d3.setText(c.getString(0));
                    bin.lhwf2d4.setText(c.getString(1));


                } while (c.moveToNext());
            }

        }


    }
}

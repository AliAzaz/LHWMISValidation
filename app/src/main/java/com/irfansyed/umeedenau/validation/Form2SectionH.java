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

import com.irfansyed.umeedenau.validation.databinding.Form2sectionhBinding;
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


public class Form2SectionH extends AppCompatActivity implements View.OnClickListener {


    //region Initialization
    Form2sectionhBinding bin;

    String FK_id;
    String Lat, Long;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bin = DataBindingUtil.setContentView(this, R.layout.form2sectionh);

        setupSkips();


        Intent iin = getIntent();
        Bundle b = iin.getExtras();
        if (b != null) {
            FK_id = (String) b.get("pk_id");
            this.ininfo();
        }

        /*bin.lhwf2h61.setOnCheckedChangeListener(this);
        bin.lhwf2h62.setOnCheckedChangeListener(this);
        bin.rlhwf2h11.setOnCheckedChangeListener(this);
        bin.rlhwf2h12.setOnCheckedChangeListener(this);*/

        bin.btnNext.setOnClickListener(this);


        String gps_ = GetGpsHideForm.get_gps(this);
        String[] gps = gps_.split("/");
        Lat = gps[0];
        Long = gps[1];
    }


    private void setupSkips() {

        bin.lhwf2h1.setOnCheckedChangeListener((radioGroup, i) -> {
            if (bin.rlhwf2h12.isChecked() == true) {
                bin.cvlhwf2h2a.setVisibility(View.VISIBLE);

                Clear.clearAllFields(bin.cvlhwf2h2);
                Clear.clearAllFields(bin.cvlhwf2h3);
                Clear.clearAllFields(bin.cvlhwf2h4);
                Clear.clearAllFields(bin.cvlhwf2h5);
                Clear.clearAllFields(bin.cvlhwf2h6);
                Clear.clearAllFields(bin.cvlhwf2h7);
                Clear.clearAllFields(bin.cvlhwf2h8);
                Clear.clearAllFields(bin.cvlhwf2h9);
                Clear.clearAllFields(bin.cvlhwf2h10);
                Clear.clearAllFields(bin.cvlhwf2h11);

                bin.cvlhwf2h2.setVisibility(View.GONE);
                bin.cvlhwf2h3.setVisibility(View.GONE);
                bin.cvlhwf2h4.setVisibility(View.GONE);
                bin.cvlhwf2h5.setVisibility(View.GONE);
                bin.cvlhwf2h6.setVisibility(View.GONE);
                bin.cvlhwf2h7.setVisibility(View.GONE);
                bin.cvlhwf2h8.setVisibility(View.GONE);
                bin.cvlhwf2h9.setVisibility(View.GONE);
                bin.cvlhwf2h10.setVisibility(View.GONE);
                bin.cvlhwf2h11.setVisibility(View.GONE);

            } else {
                bin.cvlhwf2h2a.setVisibility(View.GONE);
                Clear.clearAllFields(bin.cvlhwf2h2a);

                bin.cvlhwf2h2.setVisibility(View.VISIBLE);
                bin.cvlhwf2h3.setVisibility(View.VISIBLE);
                bin.cvlhwf2h4.setVisibility(View.VISIBLE);
                bin.cvlhwf2h5.setVisibility(View.VISIBLE);
                bin.cvlhwf2h6.setVisibility(View.VISIBLE);
                bin.cvlhwf2h7.setVisibility(View.VISIBLE);
                bin.cvlhwf2h8.setVisibility(View.VISIBLE);
                bin.cvlhwf2h9.setVisibility(View.VISIBLE);
                bin.cvlhwf2h10.setVisibility(View.VISIBLE);
                bin.cvlhwf2h11.setVisibility(View.VISIBLE);

            }
        });


        bin.lhwf2h7.setOnCheckedChangeListener((radioGroup, i) -> {
            if (bin.lhwf2h74.isChecked() && bin.rlhwf2h11.isChecked() == true) {
                bin.lhwf2h796x.setVisibility(View.VISIBLE);
            } else {
                bin.lhwf2h796x.setVisibility(View.GONE);
                Clear.clearAllFields(bin.lhwf2h796x);
            }
        });


        bin.lhwf2h6.setOnCheckedChangeListener((radioGroup, i) -> {
            if (bin.lhwf2h61.isChecked() == true && bin.rlhwf2h11.isChecked() == true) {
                bin.cvlhwf2h7.setVisibility(View.VISIBLE);
            } else {
                bin.cvlhwf2h7.setVisibility(View.GONE);
                Clear.clearAllFields(bin.cvlhwf2h7);
            }
        });

    }


    @Override
    public void onClick(View view) {
        if (!formValidation() && !bin.lhwf2h2.getText().toString().equals("999")) {
            return;
        }


        if (bin.lhwf2h5.getText().length() > 0) {
            int age = Integer.parseInt(bin.lhwf2h5.getText().toString());

            if (age < 15 || age > 49) {
                Toast.makeText(this, "MARWA Age Must be Between 15 to 49", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        insert_data();


        Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();
        Intent intt = new Intent(getBaseContext(), PendingInterviewsHH.class);
        startActivity(intt);

        finish();

    }


    private boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, bin.SectionH);
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


        //GeneratorClass.Insert_table(bin.SectionH, true);

        GeneratorClass.getContainerJSON(bin.SectionH, true);
        GeneratorClass.inert_db("TableF2SectionH", this, Has_Map);
        GeneratorClass.HH_update_Form1("TableF1SectionH", FK_id, this);
        GeneratorClass.LHWSectionUpdateCOunt("LHWCommunityHHCount", Global.LhwSection_id, this);


    }


    public void ininfo() {

        String query2 = "select lhwf1h3,lhwf1h4  from TableF1SectionH  where id=" + FK_id;

        LocalDataManager Lm = new LocalDataManager(this);
        Cursor c = database.rawQuery(query2, null);


        if (c != null) {
            if (c.moveToFirst()) {
                do {

                    bin.lhwf2h3.setText(c.getString(0));
                    bin.lhwf2h4.setText(c.getString(1));


                } while (c.moveToNext());
            }

        }

    }
}
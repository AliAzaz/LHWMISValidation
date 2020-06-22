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

import com.irfansyed.umeedenau.validation.databinding.Form2sectioncBinding;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;

import data.LocalDataManager;
import utils.ClearAllcontrol;
import utils.GeneratorClass;
import utils.GetGpsHideForm;
import utils.ValidatorClass;

import static data.LocalDataManager.database;


public class Form2SectionC extends AppCompatActivity implements View.OnClickListener, RadioButton.OnCheckedChangeListener {


    //region Initialization
    Form2sectioncBinding bin;
    String FK_id;

    String Lat, Long;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bin = DataBindingUtil.setContentView(this, R.layout.form2sectionc);


        Intent iin = getIntent();
        Bundle b = iin.getExtras();
        if (b != null) {
            FK_id = (String) b.get("pk_id");


            this.ininfo();

        }


        bin.lhwf2c61.setOnCheckedChangeListener(this);
        bin.lhwf2c62.setOnCheckedChangeListener(this);
        bin.lhwf2c63.setOnCheckedChangeListener(this);

        bin.rlhwf2c11.setOnCheckedChangeListener(this);
        bin.rlhwf2c12.setOnCheckedChangeListener(this);


        bin.btnNext.setOnClickListener(this);

        String gps_ = GetGpsHideForm.get_gps(this);
        String[] gps = gps_.split("/");
        Lat = gps[0];
        Long = gps[1];


        bin.lhwf2c2.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                if (bin.lhwf2c2.getText().toString().equals("999")) {

                    //   bin.LvLhwf2c3.setVisibility(View.GONE);
                    //   bin.LvLhwf2c4.setVisibility(View.GONE);
                    //   bin.LvLhwf2c5.setVisibility(View.GONE);
                    //   bin.LvLhwf2c6.setVisibility(View.GONE);
                    //   bin.LvLhwf2c7.setVisibility(View.GONE);
                    //   bin.LvLhwf2c8.setVisibility(View.GONE);
                    //   bin.LvLhwf2c9.setVisibility(View.GONE);
                    //   bin.LvLhwf2c10.setVisibility(View.GONE);
                    //   bin.LvLhwf2c11.setVisibility(View.GONE);


                } else {
                    //  bin.LvLhwf2c3.setVisibility(View.VISIBLE);
                    //  bin.LvLhwf2c4.setVisibility(View.VISIBLE);
                    //  bin.LvLhwf2c5.setVisibility(View.VISIBLE);
                    //  bin.LvLhwf2c6.setVisibility(View.VISIBLE);
                    //  bin.LvLhwf2c7.setVisibility(View.VISIBLE);
                    //  bin.LvLhwf2c8.setVisibility(View.VISIBLE);
                    //  bin.LvLhwf2c9.setVisibility(View.VISIBLE);
                    //  bin.LvLhwf2c10.setVisibility(View.VISIBLE);
                    //  bin.LvLhwf2c11.setVisibility(View.VISIBLE);
                }
            }
        });


    }

    @Override
    public void onClick(View view) {
        if (!formValidation() && !bin.lhwf2c2.getText().toString().equals("999")) {
            return;
        }

        insert_data();


        if (bin.lhwf2c2.getText().length() > 0) {
            int a = Integer.parseInt(bin.lhwf2c2.getText().toString());
            if (a > 5) {
                bin.lhwf2c2.setError("Should be less then 5");
                bin.lhwf2c2.requestFocus();
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
        return ValidatorClass.EmptyCheckingContainer(this, bin.SectionC);
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


        //GeneratorClass.Insert_table(bin.SectionC, true);

        GeneratorClass.getContainerJSON(bin.SectionC, true);
        GeneratorClass.inert_db("TableF2SectionC", this, Has_Map);
        GeneratorClass.HH_update_Form1("TableF1SectionC", FK_id, this);
        GeneratorClass.LHWSectionUpdateCOunt("LHWCommunityHHCount", Global.LhwSection_id, this);


    }


    public void ininfo() {

        String query2 = "select lhwf1c3,lhwf1c4  from TableF1SectionC  where id=" + FK_id;

        LocalDataManager Lm = new LocalDataManager(this);
        Cursor c = database.rawQuery(query2, null);


        if (c != null) {
            if (c.moveToFirst()) {
                do {

                    bin.lhwf2c3.setText(c.getString(0));
                    bin.lhwf2c4.setText(c.getString(1));


                } while (c.moveToNext());
            }

        }


    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (buttonView.getId() == R.id.lhwf2c6_1 || buttonView.getId() == R.id.lhwf2c6_2 || buttonView.getId() == R.id.lhwf2c6_3) {

            ClearAllcontrol.ClearAll(bin.LvLhwf2c7);

        }

        if (buttonView.getId() == R.id.rlhwf2c1_1 || buttonView.getId() == R.id.rlhwf2c1_2) {

            if (bin.rlhwf2c12.isChecked() == true) {

                // bin.LvLhwf2c2.setVisibility(View.GONE);
                // bin.LvLhwf2c3.setVisibility(View.GONE);
                // bin.LvLhwf2c4.setVisibility(View.GONE);
                // bin.LvLhwf2c4a.setVisibility(View.GONE);
                // bin.LvLhwf2c5.setVisibility(View.GONE);
                // bin.LvLhwf2c6.setVisibility(View.GONE);
                // bin.LvLhwf2c7.setVisibility(View.GONE);
                // bin.LvLhwf2c8.setVisibility(View.GONE);
                // bin.LvLhwf2c8a.setVisibility(View.GONE);
                // bin.LvLhwf2c9.setVisibility(View.GONE);
                // bin.LvLhwf2c10.setVisibility(View.GONE);
                // bin.LvLhwf2c11.setVisibility(View.GONE);


                ClearAllcontrol.ClearAll(bin.LvLhwf2c2);
                ClearAllcontrol.ClearAll(bin.LvLhwf2c3);
                ClearAllcontrol.ClearAll(bin.LvLhwf2c4);
                ClearAllcontrol.ClearAll(bin.LvLhwf2c4a);
                ClearAllcontrol.ClearAll(bin.LvLhwf2c5);
                ClearAllcontrol.ClearAll(bin.LvLhwf2c6);
                ClearAllcontrol.ClearAll(bin.LvLhwf2c7);
                ClearAllcontrol.ClearAll(bin.LvLhwf2c8);
                ClearAllcontrol.ClearAll(bin.LvLhwf2c8a);
                ClearAllcontrol.ClearAll(bin.LvLhwf2c9);
                ClearAllcontrol.ClearAll(bin.LvLhwf2c10);
                ClearAllcontrol.ClearAll(bin.LvLhwf2c11);


            } else {
                // bin.LvLhwf2c2.setVisibility(View.VISIBLE);
                // bin.LvLhwf2c3.setVisibility(View.VISIBLE);
                // bin.LvLhwf2c4.setVisibility(View.VISIBLE);
                // bin.LvLhwf2c4a.setVisibility(View.VISIBLE);
                // bin.LvLhwf2c5.setVisibility(View.VISIBLE);
                // bin.LvLhwf2c6.setVisibility(View.VISIBLE);
                // bin.LvLhwf2c7.setVisibility(View.VISIBLE);
                // bin.LvLhwf2c8.setVisibility(View.VISIBLE);
                // bin.LvLhwf2c8a.setVisibility(View.VISIBLE);
                // bin.LvLhwf2c9.setVisibility(View.VISIBLE);
                // bin.LvLhwf2c10.setVisibility(View.VISIBLE);
                // bin.LvLhwf2c11.setVisibility(View.VISIBLE);
            }
        }

        if (buttonView.getId() == R.id.lhwf2c4a_1 || buttonView.getId() == R.id.lhwf2c4a_2) {

            if (bin.lhwf2c4a2.isChecked() == true) {


                //   bin.LvLhwf2c5.setVisibility(View.GONE);
                //   bin.LvLhwf2c6.setVisibility(View.GONE);
                //   bin.LvLhwf2c7.setVisibility(View.GONE);
                //   bin.LvLhwf2c8.setVisibility(View.GONE);
                //   bin.LvLhwf2c8a.setVisibility(View.GONE);
                //   bin.LvLhwf2c9.setVisibility(View.GONE);
                //   bin.LvLhwf2c10.setVisibility(View.GONE);
                //   bin.LvLhwf2c11.setVisibility(View.GONE);


                ClearAllcontrol.ClearAll(bin.LvLhwf2c5);
                ClearAllcontrol.ClearAll(bin.LvLhwf2c6);
                ClearAllcontrol.ClearAll(bin.LvLhwf2c7);
                ClearAllcontrol.ClearAll(bin.LvLhwf2c8);
                ClearAllcontrol.ClearAll(bin.LvLhwf2c8a);
                ClearAllcontrol.ClearAll(bin.LvLhwf2c9);
                ClearAllcontrol.ClearAll(bin.LvLhwf2c10);
                ClearAllcontrol.ClearAll(bin.LvLhwf2c11);


            } else {

                //  bin.LvLhwf2c5.setVisibility(View.VISIBLE);
                //  bin.LvLhwf2c6.setVisibility(View.VISIBLE);
                //  bin.LvLhwf2c7.setVisibility(View.VISIBLE);
                //  bin.LvLhwf2c8.setVisibility(View.VISIBLE);
                //  bin.LvLhwf2c8a.setVisibility(View.VISIBLE);
                //  bin.LvLhwf2c9.setVisibility(View.VISIBLE);
                //  bin.LvLhwf2c10.setVisibility(View.VISIBLE);
                //  bin.LvLhwf2c11.setVisibility(View.VISIBLE);
            }
        }
    }
}

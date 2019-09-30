package com.irfansyed.umeedenau.validation;

import android.content.Intent;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.irfansyed.umeedenau.validation.databinding.Form1sectionfBinding;
import com.irfansyed.umeedenau.validation.databinding.Form2sectionfBinding;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;

import data.LocalDataManager;
import utils.ClearAllcontrol;
import utils.GeneratorClass;
import utils.GetGpsHideForm;
import utils.ValidatorClass;

import static data.LocalDataManager.database;


public  class Form2SectionF extends AppCompatActivity implements View.OnClickListener, RadioButton.OnCheckedChangeListener {


    //region Initialization
    Form2sectionfBinding bin;

    String FK_id;
    String Lat,Long;
    RadioButton lhwf2f1_1,lhwf2f1_2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bin= DataBindingUtil.setContentView(this, R.layout.form2sectionf);

        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        if(b!=null)
        {
            FK_id =(String) b.get("pk_id");


            this.ininfo();

        }






        bin.btnNext.setOnClickListener(this);


        String gps_=GetGpsHideForm.get_gps(this);
        String[] gps=gps_.split("/");
         Lat=gps[0];
         Long=gps[1];

        lhwf2f1_1=findViewById(R.id.lhwf2f1_1);
        lhwf2f1_2=findViewById(R.id.lhwf2f1_2);


        lhwf2f1_1.setOnCheckedChangeListener(this);
        lhwf2f1_2.setOnCheckedChangeListener(this);
        bin.lhwf2f5a1.setOnCheckedChangeListener(this);
        bin.lhwf2f5a2.setOnCheckedChangeListener(this);

        bin.lhwf2f3.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                if(bin.lhwf2f3.getText().toString().equals("999"))
                {


                    bin.LvLhwf2f4.setVisibility(View.GONE);
                    bin.LvLhwf2f5.setVisibility(View.GONE);
                    bin.LvLhwf2f6.setVisibility(View.GONE);
                    bin.LvLhwf2f7.setVisibility(View.GONE);
                    bin.LvLhwf2f8.setVisibility(View.GONE);
                    bin.LvLhwf2f9.setVisibility(View.GONE);
                    bin.LvLhwf2f10.setVisibility(View.GONE);
                    bin.LvLhwf2f11.setVisibility(View.GONE);
                    bin.LvLhwf2f12.setVisibility(View.GONE);
                    bin.LvLhwf2f13.setVisibility(View.GONE);




                }
                else
                {

                    bin.LvLhwf2f4.setVisibility(View.VISIBLE);
                    bin.LvLhwf2f5.setVisibility(View.VISIBLE);
                    bin.LvLhwf2f6.setVisibility(View.VISIBLE);
                    bin.LvLhwf2f7.setVisibility(View.VISIBLE);
                    bin.LvLhwf2f8.setVisibility(View.VISIBLE);
                    bin.LvLhwf2f9.setVisibility(View.VISIBLE);
                    bin.LvLhwf2f10.setVisibility(View.VISIBLE);
                    bin.LvLhwf2f11.setVisibility(View.VISIBLE);
                    bin.LvLhwf2f12.setVisibility(View.VISIBLE);
                    bin.LvLhwf2f13.setVisibility(View.VISIBLE);

                }
            }
        });


    }






    @Override
    public void onClick(View view)
    {

        if (!formValidation() && !bin.lhwf2f3.getText().toString().equals("999")) {
            return;
        }

        insert_data();


        Toast.makeText(this,"Data Inserted",Toast.LENGTH_SHORT).show();
        Intent intt = new Intent(getBaseContext(), PendingInterviewsHH.class);
        startActivity(intt);

        finish();

    }

    private boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, bin.SectionF);
    }



    void insert_data()
    {
        HashMap<String, String> Has_Map = new HashMap<>();
        GeneratorClass.Has_Map.clear();


        Has_Map.put("FK_id",FK_id+"");
        Has_Map.put("LhwSectionPKId",Global.LhwSection_id+"");


        String start_time = DateFormat.getDateTimeInstance().format(new Date());

        Has_Map.put(Global.GPSLat,Lat);
        Has_Map.put(Global.GPSLong,Long);
        Has_Map.put(Global.InterviewTime,start_time);


        GeneratorClass.Insert_table(bin.SectionF,true);
        GeneratorClass.inert_db("TableF2SectionF",this,Has_Map);
        GeneratorClass.HH_update_Form1("TableF1SectionF",FK_id,this);
        GeneratorClass.LHWSectionUpdateCOunt("LHWCommunityHHCount",Global.LhwSection_id,this);



    }


    public    void ininfo() {

        String query2 = "select lhwf1f4  from TableF1SectionF  where id=" + FK_id;

        LocalDataManager Lm = new LocalDataManager(this);
        Cursor c = database.rawQuery(query2, null);


        if (c != null) {
            if (c.moveToFirst()) {
                do {


                    bin.lhwf2f4.setText(c.getString(0));


                } while (c.moveToNext());
            }

        }


    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView.getId() == R.id.lhwf2f1_1 || buttonView.getId() == R.id.lhwf2f1_2) {

            if(lhwf2f1_2.isChecked()==true) {


                bin.LvLhwf2f1.setVisibility(View.GONE);
                bin.LvLhwf2f3.setVisibility(View.GONE);
                bin.LvLhwf2f4.setVisibility(View.GONE);
                bin.LvLhwf2f5.setVisibility(View.GONE);
                bin.LvLhwf2f5a.setVisibility(View.GONE);
                bin.LvLhwf2f6.setVisibility(View.GONE);
                bin.LvLhwf2f7.setVisibility(View.GONE);
                bin.LvLhwf2f8.setVisibility(View.GONE);
                bin.LvLhwf2f9.setVisibility(View.GONE);
                bin.LvLhwf2f10.setVisibility(View.GONE);
                bin.LvLhwf2f11.setVisibility(View.GONE);
                bin.LvLhwf2f12.setVisibility(View.GONE);
                bin.LvLhwf2f13.setVisibility(View.GONE);


                ClearAllcontrol.ClearAll(bin.LvLhwf2f1);
                ClearAllcontrol.ClearAll(bin.LvLhwf2f3);
                ClearAllcontrol.ClearAll(bin.LvLhwf2f4);
                ClearAllcontrol.ClearAll(bin.LvLhwf2f5);
                ClearAllcontrol.ClearAll(bin.LvLhwf2f5a);
                ClearAllcontrol.ClearAll(bin.LvLhwf2f6);
                ClearAllcontrol.ClearAll(bin.LvLhwf2f7);
                ClearAllcontrol.ClearAll(bin.LvLhwf2f8);
                ClearAllcontrol.ClearAll(bin.LvLhwf2f9);
                ClearAllcontrol.ClearAll(bin.LvLhwf2f10);
                ClearAllcontrol.ClearAll(bin.LvLhwf2f11);
                ClearAllcontrol.ClearAll(bin.LvLhwf2f12);
                ClearAllcontrol.ClearAll(bin.LvLhwf2f13);






            }
            else
            {


                bin.LvLhwf2f1.setVisibility(View.VISIBLE);
                bin.LvLhwf2f3.setVisibility(View.VISIBLE);
                bin.LvLhwf2f4.setVisibility(View.VISIBLE);
                bin.LvLhwf2f5.setVisibility(View.VISIBLE);
                bin.LvLhwf2f5a.setVisibility(View.VISIBLE);
                bin.LvLhwf2f6.setVisibility(View.VISIBLE);
                bin.LvLhwf2f7.setVisibility(View.VISIBLE);
                bin.LvLhwf2f8.setVisibility(View.VISIBLE);
                bin.LvLhwf2f9.setVisibility(View.VISIBLE);
                bin.LvLhwf2f10.setVisibility(View.VISIBLE);
                bin.LvLhwf2f11.setVisibility(View.VISIBLE);
                bin.LvLhwf2f12.setVisibility(View.VISIBLE);
                bin.LvLhwf2f13.setVisibility(View.VISIBLE);



            }

        }



        if (buttonView.getId() == R.id.lhwf2f5a_1 || buttonView.getId() == R.id.lhwf2f5a_2) {

            if(bin.lhwf2f5a2.isChecked()==true) {



                bin.LvLhwf2f6.setVisibility(View.GONE);
                bin.LvLhwf2f7.setVisibility(View.GONE);
                bin.LvLhwf2f8.setVisibility(View.GONE);
                bin.LvLhwf2f9.setVisibility(View.GONE);
                bin.LvLhwf2f10.setVisibility(View.GONE);
                bin.LvLhwf2f11.setVisibility(View.GONE);
                bin.LvLhwf2f12.setVisibility(View.GONE);
                bin.LvLhwf2f13.setVisibility(View.GONE);


                ClearAllcontrol.ClearAll(bin.LvLhwf2f6);
                ClearAllcontrol.ClearAll(bin.LvLhwf2f7);
                ClearAllcontrol.ClearAll(bin.LvLhwf2f8);
                ClearAllcontrol.ClearAll(bin.LvLhwf2f9);
                ClearAllcontrol.ClearAll(bin.LvLhwf2f10);
                ClearAllcontrol.ClearAll(bin.LvLhwf2f11);
                ClearAllcontrol.ClearAll(bin.LvLhwf2f12);
                ClearAllcontrol.ClearAll(bin.LvLhwf2f13);






            }
            else
            {



                bin.LvLhwf2f6.setVisibility(View.VISIBLE);
                bin.LvLhwf2f7.setVisibility(View.VISIBLE);
                bin.LvLhwf2f8.setVisibility(View.VISIBLE);
                bin.LvLhwf2f9.setVisibility(View.VISIBLE);
                bin.LvLhwf2f10.setVisibility(View.VISIBLE);
                bin.LvLhwf2f11.setVisibility(View.VISIBLE);
                bin.LvLhwf2f12.setVisibility(View.VISIBLE);
                bin.LvLhwf2f13.setVisibility(View.VISIBLE);



            }

        }
    }
}

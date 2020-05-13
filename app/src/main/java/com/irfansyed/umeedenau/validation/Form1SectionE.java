package com.irfansyed.umeedenau.validation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.irfansyed.umeedenau.validation.databinding.Form1sectioneBinding;

import java.util.HashMap;

import utils.ClearAllcontrol;
import utils.GeneratorClass;
import utils.ValidatorClass;


public  class Form1SectionE extends AppCompatActivity implements View.OnClickListener, RadioButton.OnCheckedChangeListener  {


    //region Initialization
    Form1sectioneBinding bin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bin= DataBindingUtil.setContentView(this, R.layout.form1sectione);


        bin.btnNext.setOnClickListener(this);



        if(GeneratorClass.LHWsectionStatus("TableF1SectionE")==false)
        {

            bin.lhwf1e1.setText("000");
            bin.lhwf1e1.setVisibility(View.GONE);
        }


        bin.lhwf1e51.setOnCheckedChangeListener(this);
        bin.lhwf1e52.setOnCheckedChangeListener(this);
        bin.lhwf1e61.setOnCheckedChangeListener(this);
        bin.lhwf1e62.setOnCheckedChangeListener(this);




    }






    @Override
    public void onClick(View view)
    {
        if (!formValidation()) {
            return;
        }


        if(!GeneratorClass.checktextbox(bin.lhwf1e1,bin.lhwf1e2))
        {
            return;
        }


        if(bin.lhwf1e1.getText().length()>0) {
            int a = Integer.parseInt(bin.lhwf1e1.getText().toString());
            if (a > 60 ) {
                bin.lhwf1e1.requestFocus();
                bin.lhwf1e1.setError("Should be less then 60 and greater then 0 ");

                return;
            }

        }
        int b=Integer.parseInt(bin.lhwf1e2.getText().toString());
        if(b>10)
        {
            bin.lhwf1e2.requestFocus();
            bin.lhwf1e2.setError("Should be less then 10");

           // return;
        }


        insert_data();
        int count= GeneratorClass.hh_section_count("TableF1SectionE",this);

        Toast.makeText(this,"Data Inserted",Toast.LENGTH_SHORT).show();


        Intent returnIntent = new Intent();
        returnIntent.putExtra("count",count+"");
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }
    private boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, bin.SectionE);
    }

    void insert_data()
    {
        HashMap<String, String> Has_Map = new HashMap<>();
        GeneratorClass.Has_Map.clear();


        Has_Map.put("FK_id",Global.LhwHH_id+"");
        Has_Map.put("LhwSectionPKId",Global.LhwSection_id+"");
        Has_Map.put("Status","0");

        GeneratorClass.Insert_table(bin.SectionE,true);
        GeneratorClass.inert_db("TableF1SectionE",this,Has_Map);
        GeneratorClass.LHWSectionUpdateCOunt("LHWOfficeHHCount",Global.LhwSection_id,this);




    }
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


        if (buttonView.getId() == R.id.lhwf1e5_1 || buttonView.getId() == R.id.lhwf1e5_2) {

            ClearAllcontrol.ClearAll(bin.LvLhwf1e5b);

        }


        if (buttonView.getId() == R.id.lhwf1e6_1 || buttonView.getId() == R.id.lhwf1e6_2) {

            ClearAllcontrol.ClearAll(bin.LvLhwf1e6b);

        }
    }





}

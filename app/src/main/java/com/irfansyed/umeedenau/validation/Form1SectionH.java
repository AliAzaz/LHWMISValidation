package com.irfansyed.umeedenau.validation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.irfansyed.umeedenau.validation.databinding.Form1sectionhBinding;

import java.util.HashMap;

import utils.GeneratorClass;
import utils.ValidatorClass;


public  class Form1SectionH extends AppCompatActivity implements View.OnClickListener {


    //region Initialization
    Form1sectionhBinding bin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bin= DataBindingUtil.setContentView(this, R.layout.form1sectionh);

         bin.btnNext.setOnClickListener(this);



        if(GeneratorClass.LHWsectionStatus("TableF1SectionH")==false)
        {

            bin.lhwf1h1.setText("000");
            bin.lhwf1h1.setVisibility(View.GONE);
        }

    }






    @Override
    public void onClick(View view)
    {
        if (!formValidation()) {
            return;
        }



        /*if(bin.lhwf1h1.getText().length()>0) {
            int a = Integer.parseInt(bin.lhwf1h1.getText().toString());
            if (a > 20) {
                bin.lhwf1h1.requestFocus();
                bin.lhwf1h1.setError("Should be less then 20");

                return;
            }

        }*/


        if(bin.lhwf1h1.getText().length()>0) {
            int a = Integer.parseInt(bin.lhwf1h2.getText().toString());
            if (a > 10) {
                bin.lhwf1h2.requestFocus();
                bin.lhwf1h2.setError("Should be less then 10");

                return;
            }

        }


        if(!GeneratorClass.checktextbox(bin.lhwf1h1,bin.lhwf1h2))
        {
            return;
        }

        if(bin.lhwf1h5.getText().length()>0)
        {
            int age=Integer.parseInt(bin.lhwf1h5.getText().toString());

            if(age<15 || age>49)
            {
                Toast.makeText(this,"MARWA Age Must be Between 15 to 49",Toast.LENGTH_SHORT).show();
                return;
            }
        }

        insert_data();
        int count= GeneratorClass.hh_section_count("TableF1SectionH",this);

        Toast.makeText(this,"Data Inserted",Toast.LENGTH_SHORT).show();


        Intent returnIntent = new Intent();
        returnIntent.putExtra("count",count+"");
        setResult(Activity.RESULT_OK,returnIntent);
        finish();

    }


    private boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, bin.SectionH);
    }



    void insert_data()
    {
        HashMap<String, String> Has_Map = new HashMap<>();
        GeneratorClass.Has_Map.clear();


        Has_Map.put("FK_id",Global.LhwHH_id+"");
        Has_Map.put("LhwSectionPKId",Global.LhwSection_id+"");
        Has_Map.put("Status","0");

        GeneratorClass.Insert_table(bin.SectionH,true);
        GeneratorClass.inert_db("TableF1SectionH",this,Has_Map);
        GeneratorClass.LHWSectionUpdateCOunt("LHWOfficeHHCount",Global.LhwSection_id,this);




    }



}

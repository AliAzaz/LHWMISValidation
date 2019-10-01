package com.irfansyed.umeedenau.validation;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.irfansyed.umeedenau.validation.databinding.Form1sectiondBinding;

import java.util.HashMap;

import utils.GeneratorClass;
import utils.ValidatorClass;


public  class Form1SectionD extends AppCompatActivity implements View.OnClickListener {


    //region Initialization
    Form1sectiondBinding bin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bin= DataBindingUtil.setContentView(this, R.layout.form1sectiond);

        bin.btnNext.setOnClickListener(this);


        if(GeneratorClass.LHWsectionStatus("TableF1SectionD")==false)
        {

            bin.lhwf1d1.setText("000");
            bin.lhwf1d1.setVisibility(View.GONE);
        }



    }






    @Override
    public void onClick(View view)
    {

        if (!formValidation()) {
            return;
        }


        if(!GeneratorClass.checktextbox(bin.lhwf1d1,bin.lhwf1d2))
        {
            return;
        }


        if(bin.lhwf1d1.getText().length()>0) {

            int a = Integer.parseInt(bin.lhwf1d1.getText().toString());
            if (a > 30 ) {
                bin.lhwf1d1.requestFocus();
                bin.lhwf1d1.setError("Should be less then 30 or greater then 0 ");

                return;
            }
        }
        int b=Integer.parseInt(bin.lhwf1d2.getText().toString());
        if(b>5)
        {
            bin.lhwf1d2.requestFocus();
            bin.lhwf1d2.setError("Should be less then 5");

            return;
        }



        insert_data();
        int count= GeneratorClass.hh_section_count("TableF1SectionD",this);

        Toast.makeText(this,"Data Inserted",Toast.LENGTH_SHORT).show();


        Intent returnIntent = new Intent();
        returnIntent.putExtra("count",count+"");
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }


    private boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, bin.Dsection);
    }

    void insert_data()
    {
        HashMap<String, String> Has_Map = new HashMap<>();


        Has_Map.put("FK_id",Global.LhwHH_id+"");
        Has_Map.put("LhwSectionPKId",Global.LhwSection_id+"");
        Has_Map.put("Status","0");

        GeneratorClass.Has_Map.clear();
        GeneratorClass.Insert_table(bin.Dsection,true);
        GeneratorClass.inert_db("TableF1SectionD",this,Has_Map);
        GeneratorClass.LHWSectionUpdateCOunt("LHWOfficeHHCount",Global.LhwSection_id,this);




    }


}

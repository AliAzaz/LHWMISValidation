package com.irfansyed.umeedenau.validation;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;


import com.irfansyed.umeedenau.validation.databinding.Form5sectionbBinding;

import java.util.HashMap;

import utils.ClearAllcontrol;
import utils.GeneratorClass;
import utils.ValidatorClass;


public  class Form5SectionB extends AppCompatActivity implements View.OnClickListener,RadioButton.OnCheckedChangeListener {


    //region Initialization
    Form5sectionbBinding bin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bin= DataBindingUtil.setContentView(this, R.layout.form5sectionb);


        bin.btnNext.setOnClickListener(this);
        bin.lhwf5b11.setOnCheckedChangeListener(this);
        bin.lhwf5b12.setOnCheckedChangeListener(this);

    }






    @Override
    public void onClick(View view)
    {
        if (!formValidation()) {
            return;
        }

        insert_data();
        Toast.makeText(this,"Data Inserted",Toast.LENGTH_SHORT).show();

        finish();
    }
    private boolean formValidation()
    {
        return ValidatorClass.EmptyCheckingContainer(this, bin.SectionB);
    }



    void insert_data()
    {
        HashMap<String, String> Has_Map = new HashMap<>();
        GeneratorClass.Has_Map.clear();


        Has_Map.put("FK_id",Global.LhwSection_id+"");


        GeneratorClass.Insert_table(bin.SectionB,true);
        GeneratorClass.inert_db("TableF5SectionB",this,Has_Map);




    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


        if(buttonView.getId()==R.id.lhwf5b1_1 || buttonView.getId()==R.id.lhwf5b1_2 )
        {


            if(bin.lhwf5b12.isChecked()) {

                ClearAllcontrol.ClearAll(bin.LvLhwf5b2);
                ClearAllcontrol.ClearAll(bin.LvLhwf5b3);
                ClearAllcontrol.ClearAll(bin.LvLhwf5b4);
                ClearAllcontrol.ClearAll(bin.LvLhwf5b5a);
                ClearAllcontrol.ClearAll(bin.LvLhwf5b5b);
                ClearAllcontrol.ClearAll(bin.LvLhwf5b5c);
                ClearAllcontrol.ClearAll(bin.LvLhwf5b6);
                ClearAllcontrol.ClearAll(bin.LvLhwf5b7);


            }

        }
    }
}

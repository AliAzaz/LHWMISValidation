package com.irfansyed.umeedenau.validation;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.irfansyed.umeedenau.validation.databinding.Form1sectioncBinding;

import java.util.HashMap;

import utils.ClearAllcontrol;
import utils.GeneratorClass;
import utils.ValidatorClass;


public  class Form1SectionC extends AppCompatActivity implements View.OnClickListener,RadioButton.OnCheckedChangeListener {


    //region Initialization
    Form1sectioncBinding bin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bin= DataBindingUtil.setContentView(this, R.layout.form1sectionc);

        bin.btnNext.setOnClickListener(this);


      bin.lhwf1c61.setOnCheckedChangeListener(this);
      bin.lhwf1c62.setOnCheckedChangeListener(this);
      bin.lhwf1c63.setOnCheckedChangeListener(this);
      bin.lhwf1c81.setOnCheckedChangeListener(this);
        bin.lhwf1c82.setOnCheckedChangeListener(this);
        bin.lhwf1c83.setOnCheckedChangeListener(this);

      if(GeneratorClass.LHWsectionStatus("TableF1SectionC")==false)
      {

          bin.lhwf1c1.setText("000");
          bin.lhwf1c1.setVisibility(View.GONE);
      }


    }






    @Override
    public void onClick(View view)
    {
        if (!formValidation()) {
             return;
        }



        if(!GeneratorClass.checktextbox(bin.lhwf1c1,bin.lhwf1c2))
        {
            return;
        }

        /*if(bin.lhwf1c1.getText().length()>0) {
            int a = Integer.parseInt(bin.lhwf1c1.getText().toString());
            if (a > 30 ) {
                bin.lhwf1c1.requestFocus();
                bin.lhwf1c1.setError("Should be less then 30 or Greater then 0 ");

                return;
            }
        }*/

        /*int b=Integer.parseInt(bin.lhwf1c2.getText().toString());
        if(b>5)
        {
            bin.lhwf1c2.requestFocus();
            bin.lhwf1c2.setError("Should be less then 5");

            return;
        }*/



        insert_data();
        int count= GeneratorClass.hh_section_count("TableF1SectionC",this);

        Toast.makeText(this,"Data Inserted",Toast.LENGTH_SHORT).show();


        Intent returnIntent = new Intent();
        returnIntent.putExtra("count",count+"");
        setResult(Activity.RESULT_OK,returnIntent);
        finish();


    }

    private boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, bin.pp);
    }



    void insert_data()
    {
        HashMap<String, String> Has_Map = new HashMap<>();
        GeneratorClass.Has_Map.clear();


        Has_Map.put("FK_id",Global.LhwHH_id+"");
        Has_Map.put("LhwSectionPKId",Global.LhwSection_id+"");
        Has_Map.put("Status","0");

        GeneratorClass.Insert_table(bin.pp,true);
        GeneratorClass.inert_db("TableF1SectionC",this,Has_Map);
        GeneratorClass.LHWSectionUpdateCOunt("LHWOfficeHHCount",Global.LhwSection_id,this);




    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


      if(buttonView.getId()==R.id.lhwf1c6_1 || buttonView.getId()==R.id.lhwf1c6_2 || buttonView.getId()==R.id.lhwf1c6_3)
      {

                  ClearAllcontrol.ClearAll(bin.LvLhwf1c7);
                  ClearAllcontrol.ClearAll(bin.LvLhwf1c7A);

      }

        if(buttonView.getId()==R.id.lhwf1c8_1 || buttonView.getId()==R.id.lhwf1c8_2 || buttonView.getId()==R.id.lhwf1c8_3)
        {



                ClearAllcontrol.ClearAll(bin.LvLhwf1c9);


        }

    }
}

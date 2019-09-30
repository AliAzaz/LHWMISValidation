package com.irfansyed.umeedenau.validation;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.irfansyed.umeedenau.validation.databinding.Form1sectiongBinding;

import java.util.HashMap;

import utils.GeneratorClass;
import utils.ValidatorClass;


public  class Form1SectionG extends AppCompatActivity implements View.OnClickListener {


    //region Initialization
    Form1sectiongBinding bin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bin= DataBindingUtil.setContentView(this, R.layout.form1sectiong);


        bin.btnNext.setOnClickListener(this);





        if(GeneratorClass.LHWsectionStatus("TableF1SectionG")==false)
        {

            bin.lhwf1g1.setText("000");
            bin.lhwf1g1.setVisibility(View.GONE);

            bin.lhwf1g2.setText("000");
            bin.lhwf1g2.setVisibility(View.GONE);
        }



        bin.lhwf1g1.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() != 0)
                {
                    bin.lhwf1g2.setVisibility(View.GONE);
                    //   bin.lhwf1g2.setText("00");
                }
                else
                {
                    bin.lhwf1g2.setVisibility(View.VISIBLE);
                    //  bin.lhwf1g2.setText("");
                }
            }
        });

        bin.lhwf1g2.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() != 0)
                {
                    bin.lhwf1g1.setVisibility(View.GONE);
                    //  bin.lhwf1g1.setText("00");
                }
                else
                {
                    bin.lhwf1g1.setVisibility(View.VISIBLE);
                    // bin.lhwf1g1.setText("");
                }
            }
        });





}






    @Override
    public void onClick(View view)
    {
        if (!formValidation()) {
            return;
        }


        if(bin.lhwf1g1.getText().length()>0) {
            int a = Integer.parseInt(bin.lhwf1g1.getText().toString());
            if (a > 100) {
                bin.lhwf1g1.requestFocus();
                bin.lhwf1g1.setError("Should be less then 100");

                return;
            }
        }

        if(bin.lhwf1g2.getText().length()>0) {
            int b = Integer.parseInt(bin.lhwf1g2.getText().toString());
            if (b > 100) {
                bin.lhwf1g2.requestFocus();
                bin.lhwf1g2.setError("Should be less then 100");

                return;
            }
        }

        if(bin.lhwf1g3.getText().length()>0) {
            int b = Integer.parseInt(bin.lhwf1g3.getText().toString());
            if (b > 30) {
                bin.lhwf1g3.requestFocus();
                bin.lhwf1g3.setError("Should be less then 30");

                return;
            }
        }

        if(bin.lhwf1g3.getText().length()>0) {
            int b = Integer.parseInt(bin.lhwf1g3.getText().toString());
            if (b > 30) {
                bin.lhwf1g3.requestFocus();
                bin.lhwf1g3.setError("Should be less then 30");

                return;
            }
        }

        if(bin.lhwf1g5.getText().length()>0) {
            int b = Integer.parseInt(bin.lhwf1g5.getText().toString());
            if (b > 59) {
                bin.lhwf1g5.requestFocus();
                bin.lhwf1g5.setError("age of child should be less then  60");

                return;
            }
        }





        insert_data();
        int count= GeneratorClass.hh_section_count("TableF1SectionG",this);

        Toast.makeText(this,"Data Inserted",Toast.LENGTH_SHORT).show();


        Intent returnIntent = new Intent();
        returnIntent.putExtra("count",count+"");
        setResult(Activity.RESULT_OK,returnIntent);
        finish();

    }

    private boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, bin.SectionG);
    }



    void insert_data()
    {
        HashMap<String, String> Has_Map = new HashMap<>();
        GeneratorClass.Has_Map.clear();


        Has_Map.put("FK_id",Global.LhwHH_id+"");
        Has_Map.put("LhwSectionPKId",Global.LhwSection_id+"");
        Has_Map.put("Status","0");

        GeneratorClass.Insert_table(bin.SectionG,true);
        GeneratorClass.inert_db("TableF1SectionG",this,Has_Map);
        GeneratorClass.LHWSectionUpdateCOunt("LHWOfficeHHCount",Global.LhwSection_id,this);




    }



}

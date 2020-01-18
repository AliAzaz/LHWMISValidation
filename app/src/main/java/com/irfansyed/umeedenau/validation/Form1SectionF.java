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

import com.irfansyed.umeedenau.validation.databinding.Form1sectionfBinding;

import java.util.HashMap;

import utils.GeneratorClass;
import utils.ValidatorClass;


public  class Form1SectionF extends AppCompatActivity implements View.OnClickListener {


    //region Initialization
    Form1sectionfBinding bin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bin= DataBindingUtil.setContentView(this, R.layout.form1sectionf);
        bin.btnNext.setOnClickListener(this);


        if (GeneratorClass.LHWsectionStatus("TableF1SectionF") == false) {

            bin.lhwf1f1.setText("000");
            bin.lhwf1f1.setVisibility(View.GONE);

            bin.lhwf1f2.setText("000");
            bin.lhwf1f2.setVisibility(View.GONE);

        }



        bin.lhwf1f1.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (bin.lhwf1f1.getText().toString().isEmpty()) return;
                bin.lhwf1f3.setMaxvalue(Integer.valueOf(bin.lhwf1f1.getText().toString()) > 20 ? 20 : Integer.valueOf(bin.lhwf1f1.getText().toString()));

                if (s.length() != 0) {
                    bin.lhwf1f2.setVisibility(View.GONE);
                 //   bin.lhwf1f2.setText("00");
                } else {
                    bin.lhwf1f2.setVisibility(View.VISIBLE);
                  //  bin.lhwf1f2.setText("");
                }
            }
        });


        bin.lhwf1f2.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (bin.lhwf1f2.getText().toString().isEmpty()) return;
                bin.lhwf1f3.setMaxvalue(Integer.valueOf(bin.lhwf1f2.getText().toString()) > 20 ? 20 : Integer.valueOf(bin.lhwf1f2.getText().toString()));

                if (s.length() != 0) {
                    bin.lhwf1f1.setVisibility(View.GONE);
                  //  bin.lhwf1f1.setText("00");
                } else {
                    bin.lhwf1f1.setVisibility(View.VISIBLE);
                   // bin.lhwf1f1.setText("");
                }
            }
        });

    }






// update

    @Override
    public void onClick(View view) {
        if (!formValidation()) {
            return;
        }


        insert_data();
        int count= GeneratorClass.hh_section_count("TableF1SectionF",this);

        Toast.makeText(this,"Data Inserted",Toast.LENGTH_SHORT).show();


        Intent returnIntent = new Intent();
        returnIntent.putExtra("count",count+"");
        setResult(Activity.RESULT_OK,returnIntent);
        finish();

    }


    private boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, bin.SectionF);
    }


    void insert_data() {
        HashMap<String, String> Has_Map = new HashMap<>();
        GeneratorClass.Has_Map.clear();


        Has_Map.put("FK_id",Global.LhwHH_id+"");
        Has_Map.put("LhwSectionPKId",Global.LhwSection_id+"");
        Has_Map.put("Status","0");

        GeneratorClass.Insert_table(bin.SectionF,true);
        GeneratorClass.inert_db("TableF1SectionF",this,Has_Map);
        GeneratorClass.LHWSectionUpdateCOunt("LHWOfficeHHCount",Global.LhwSection_id,this);

    }


}

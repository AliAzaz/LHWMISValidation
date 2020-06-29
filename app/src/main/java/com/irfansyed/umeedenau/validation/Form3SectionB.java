package com.irfansyed.umeedenau.validation;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.irfansyed.umeedenau.validation.databinding.Form3sectionbBinding;
import com.validatorcrawler.aliazaz.Clear;

import java.util.HashMap;

import utils.ClearAllcontrol;
import utils.GeneratorClass;
import utils.ValidatorClass;


public class Form3SectionB extends AppCompatActivity implements View.OnClickListener {


    //region Initialization
    Form3sectionbBinding bin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bin = DataBindingUtil.setContentView(this, R.layout.form3sectionb);

        setupSkips();

        bin.btnNext.setOnClickListener(this);

    }


    private void setupSkips() {

        bin.lhwf3b1.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bin.lhwf3b12.getId()) {

                bin.cvlhwf3b2.setVisibility(View.GONE);
                bin.cvlhwf3b3.setVisibility(View.GONE);
                bin.cvlhwf3b4a.setVisibility(View.GONE);
                bin.cvlhwf3b4b.setVisibility(View.GONE);
                bin.cvlhwf3b4c.setVisibility(View.GONE);

                Clear.clearAllFields(bin.cvlhwf3b2);
                Clear.clearAllFields(bin.cvlhwf3b3);
                Clear.clearAllFields(bin.cvlhwf3b4a);
                Clear.clearAllFields(bin.cvlhwf3b4b);
                Clear.clearAllFields(bin.cvlhwf3b4c);

            } else {

                bin.cvlhwf3b2.setVisibility(View.VISIBLE);
                bin.cvlhwf3b3.setVisibility(View.VISIBLE);
                bin.cvlhwf3b4a.setVisibility(View.VISIBLE);
                bin.cvlhwf3b4b.setVisibility(View.VISIBLE);
                bin.cvlhwf3b4c.setVisibility(View.VISIBLE);

            }
        }));


        bin.lhwf3b2.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bin.lhwf3b22.getId()) {

                bin.cvlhwf3b3.setVisibility(View.GONE);
                bin.cvlhwf3b4a.setVisibility(View.GONE);
                bin.cvlhwf3b4b.setVisibility(View.GONE);
                bin.cvlhwf3b4c.setVisibility(View.GONE);
                bin.cvlhwf3b5.setVisibility(View.GONE);
                bin.cvlhwf3b5b.setVisibility(View.GONE);

                Clear.clearAllFields(bin.cvlhwf3b3);
                Clear.clearAllFields(bin.cvlhwf3b4a);
                Clear.clearAllFields(bin.cvlhwf3b4b);
                Clear.clearAllFields(bin.cvlhwf3b4c);
                Clear.clearAllFields(bin.cvlhwf3b5);
                Clear.clearAllFields(bin.cvlhwf3b5b);

            } else {


                bin.cvlhwf3b3.setVisibility(View.VISIBLE);
                bin.cvlhwf3b4a.setVisibility(View.VISIBLE);
                bin.cvlhwf3b4b.setVisibility(View.VISIBLE);
                bin.cvlhwf3b4c.setVisibility(View.VISIBLE);
                bin.cvlhwf3b5.setVisibility(View.VISIBLE);
                bin.cvlhwf3b5b.setVisibility(View.VISIBLE);


            }
        }));


        bin.lhwf3b5.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bin.lhwf3b52.getId()) {
                bin.cvlhwf3b5b.setVisibility(View.VISIBLE);
            } else {
                bin.cvlhwf3b5b.setVisibility(View.GONE);
                Clear.clearAllFields(bin.cvlhwf3b5b);
            }
        }));


        bin.lhwf3b5b.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bin.lhwf3b5b96.getId()) {
                bin.lhwf3b5b96x.setVisibility(View.VISIBLE);
            } else {
                bin.lhwf3b5b96x.setVisibility(View.GONE);
                Clear.clearAllFields(bin.lhwf3b5b96x);
            }
        }));


    }


    @Override
    public void onClick(View view) {

        if (!formValidation()) {
            return;
        }

        insert_data();
        Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();

        finish();
    }


    private boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, bin.SectionB);
    }


    void insert_data() {
        HashMap<String, String> Has_Map = new HashMap<>();
        GeneratorClass.Has_Map.clear();


        Has_Map.put("FK_id", Global.LhwSection_id + "");
        Has_Map.put("Status", "0");


        //GeneratorClass.Insert_table(bin.SectionB, true);

        GeneratorClass.getContainerJSON(bin.SectionB, true);
        GeneratorClass.inert_db("TableF3SectionB", this, Has_Map);
        GeneratorClass.LHWSectionUpdateCOunt("LHWOfficeVHCCount", Global.LhwSection_id, this);


    }


    /*@Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


        if (buttonView.getId() == R.id.lhwf3b1_1 || buttonView.getId() == R.id.lhwf3b1_2) {


            if (bin.lhwf3b12.isChecked()) {

                ClearAllcontrol.ClearAll(bin.LvLhwf3b2);
                ClearAllcontrol.ClearAll(bin.LvLhwf3b3);
                ClearAllcontrol.ClearAll(bin.LvLhwf3b4a);
                ClearAllcontrol.ClearAll(bin.LvLhwf3b4b);
                ClearAllcontrol.ClearAll(bin.LvLhwf3b4c);
                ClearAllcontrol.ClearAll(bin.LvLhwf3b5);
                ClearAllcontrol.ClearAll(bin.LvLhwf3b6);

            }

        }


        if (buttonView.getId() == R.id.lhwf3b2_1 || buttonView.getId() == R.id.lhwf3b2_2) {


            if (bin.lhwf3b22.isChecked()) {


                ClearAllcontrol.ClearAll(bin.LvLhwf3b3);
                ClearAllcontrol.ClearAll(bin.LvLhwf3b4a);
                ClearAllcontrol.ClearAll(bin.LvLhwf3b4b);
                ClearAllcontrol.ClearAll(bin.LvLhwf3b4c);
                ClearAllcontrol.ClearAll(bin.LvLhwf3b5);
                ClearAllcontrol.ClearAll(bin.LvLhwf3b6);

            }

        }

        if (buttonView.getId() == R.id.lhwf3b5_1 || buttonView.getId() == R.id.lhwf3b5_2) {


            if (bin.lhwf3b22.isChecked()) {

                ClearAllcontrol.ClearAll(bin.LvLhwf3b6);

            }

        }


    }*/


}
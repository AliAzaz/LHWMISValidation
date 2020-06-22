package com.irfansyed.umeedenau.validation;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.irfansyed.umeedenau.validation.databinding.Form3sectionbBinding;

import java.util.HashMap;

import utils.ClearAllcontrol;
import utils.GeneratorClass;
import utils.ValidatorClass;


public class Form3SectionB extends AppCompatActivity implements View.OnClickListener, RadioButton.OnCheckedChangeListener {


    //region Initialization
    Form3sectionbBinding bin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bin = DataBindingUtil.setContentView(this, R.layout.form3sectionb);


        bin.btnNext.setOnClickListener(this);

        bin.lhwf3b11.setOnCheckedChangeListener(this);
        bin.lhwf3b12.setOnCheckedChangeListener(this);

        bin.lhwf3b21.setOnCheckedChangeListener(this);
        bin.lhwf3b22.setOnCheckedChangeListener(this);


        bin.lhwf3b51.setOnCheckedChangeListener(this);
        bin.lhwf3b52.setOnCheckedChangeListener(this);

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


    @Override
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


    }
}

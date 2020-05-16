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


public class Form1SectionE extends AppCompatActivity implements View.OnClickListener, RadioButton.OnCheckedChangeListener {


    //region Initialization
    Form1sectioneBinding bin;
    private int PhotoSerial;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bin = DataBindingUtil.setContentView(this, R.layout.form1sectione);


        bin.btnNext.setOnClickListener(this);
        PhotoSerial = 0;


        if (GeneratorClass.LHWsectionStatus("TableF1SectionE") == false) {

            bin.lhwf1e1.setText("000");
            bin.lhwf1e1.setVisibility(View.GONE);
        }


        bin.lhwf1e51.setOnCheckedChangeListener(this);
        bin.lhwf1e52.setOnCheckedChangeListener(this);
        bin.lhwf1e61.setOnCheckedChangeListener(this);
        bin.lhwf1e62.setOnCheckedChangeListener(this);


    }


    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btn_snap) {
            Intent intent = new Intent(this, TakePhoto.class);

            // intent.putExtra("picID", "901001" + "_" + "A-0001-001" + "_" + PhotoSerial + "_");
            //  intent.putExtra("childName", "Hassan");

// TODO: add identification information
            intent.putExtra("picID", Global.LhwSection_id + "_" + PhotoSerial);
            intent.putExtra("childName", "Newborn Care");


            intent.putExtra("picView", "Sect_D".toUpperCase());
            startActivityForResult(intent, 1); // Activity is started with requestCode 1 = Front

        }

        if (!formValidation()) {
            return;
        }


        if (!GeneratorClass.checktextbox(bin.lhwf1e1, bin.lhwf1e2)) {
            return;
        }


        if (bin.lhwf1e1.getText().length() > 0) {
            int a = Integer.parseInt(bin.lhwf1e1.getText().toString());
            if (a > 60) {
                bin.lhwf1e1.requestFocus();
                bin.lhwf1e1.setError("Should be less then 60 and greater then 0 ");

                return;
            }

        }
        int b = Integer.parseInt(bin.lhwf1e2.getText().toString());
        if (b > 10) {
            bin.lhwf1e2.requestFocus();
            bin.lhwf1e2.setError("Should be less then 10");

            // return;
        }


        insert_data();
        int count = GeneratorClass.hh_section_count("TableF1SectionE", this);

        Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();


        Intent returnIntent = new Intent();
        returnIntent.putExtra("count", count + "");
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    private boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, bin.SectionE);
    }

    void insert_data() {
        HashMap<String, String> Has_Map = new HashMap<>();
        GeneratorClass.Has_Map.clear();


        Has_Map.put("FK_id", Global.LhwHH_id + "");
        Has_Map.put("LhwSectionPKId", Global.LhwSection_id + "");
        Has_Map.put("Status", "0");

        GeneratorClass.Insert_table(bin.SectionE, true);
        GeneratorClass.inert_db("TableF1SectionE", this, Has_Map);
        GeneratorClass.LHWSectionUpdateCOunt("LHWOfficeHHCount", Global.LhwSection_id, this);


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


  /* onActivityResult(resultCode) 0= Photo Cancel, 1=Photo Taken
        if resultCode = 1 than also returns -> Intent Extra (FileName)*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        // Toast.makeText(this, requestCode + "_" + resultCode, Toast.LENGTH_SHORT).show();
        if (resultCode == 1) {
            Toast.makeText(this, "Photo Taken", Toast.LENGTH_SHORT).show();
            PhotoSerial++;

            String fileName = data.getStringExtra("FileName");
            bin.lhwf1ephoto.setText(bin.lhwf1ephoto.getText() + String.valueOf(PhotoSerial) + " - " + fileName + ";\r\n");
        } else {
            Toast.makeText(this, "Photo Cancelled", Toast.LENGTH_SHORT).show();
        }
    }


}

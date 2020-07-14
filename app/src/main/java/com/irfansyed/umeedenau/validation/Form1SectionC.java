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

import com.irfansyed.umeedenau.validation.databinding.Form1sectioncBinding;
import com.validatorcrawler.aliazaz.Clear;

import java.util.HashMap;

import utils.ClearAllcontrol;
import utils.GeneratorClass;
import utils.ValidatorClass;


public class Form1SectionC extends AppCompatActivity implements View.OnClickListener {


    //region Initialization
    Form1sectioncBinding bin;
    private int PhotoSerial;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bin = DataBindingUtil.setContentView(this, R.layout.form1sectionc);
        setupSkips();

        bin.btnNext.setOnClickListener(this);
        bin.btnSnap.setOnClickListener(this);

        PhotoSerial = 0;
        /*bin.lhwf1c61.setOnCheckedChangeListener(this);
        bin.lhwf1c62.setOnCheckedChangeListener(this);
        bin.lhwf1c63.setOnCheckedChangeListener(this);
        bin.lhwf1c81.setOnCheckedChangeListener(this);
        bin.lhwf1c82.setOnCheckedChangeListener(this);
        bin.lhwf1c83.setOnCheckedChangeListener(this);
        bin.lhwc11a1.setOnCheckedChangeListener(this);*/


        if (!GeneratorClass.LHWsectionStatus("TableF1SectionC")) {
            bin.lhwf1c1.setText("000");
            bin.lhwf1c1.setVisibility(View.GONE);
        }

    }


    private void setupSkips() {

        bin.lhwf1c6.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (bin.lhwf1c61.isChecked()) {
                bin.cvlhwf1c7A.setVisibility(View.VISIBLE);

                bin.cvlhwf1c7.setVisibility(View.GONE);
                Clear.clearAllFields(bin.cvlhwf1c7);
            } else {
                bin.cvlhwf1c7A.setVisibility(View.GONE);
                Clear.clearAllFields(bin.cvlhwf1c7A);

                bin.cvlhwf1c7.setVisibility(View.VISIBLE);
            }
        }));

        bin.lhwf1c8.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (bin.lhwf1c81.isChecked()) {
                Clear.clearAllFields(bin.LvLhwf1c9);
                bin.LvLhwf1c9.setVisibility(View.GONE);
            } else {
                bin.LvLhwf1c9.setVisibility(View.VISIBLE);
            }
        }));

        bin.lhwc11a.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (bin.lhwc11a2.isChecked()) {
                Clear.clearAllFields(bin.cvlhwf1c3A);
                Clear.clearAllFields(bin.cvlhwf1c4a);
                Clear.clearAllFields(bin.cvlhwf1c5);
                Clear.clearAllFields(bin.cvlhwf1c6);
                Clear.clearAllFields(bin.cvlhwf1c7);
                Clear.clearAllFields(bin.cvlhwf1c7A);
                Clear.clearAllFields(bin.cvlhwf1c8);
                Clear.clearAllFields(bin.cvlhwf1c9);

                bin.cvlhwc11b.setVisibility(View.VISIBLE);
                bin.cvlhwf1c3A.setVisibility(View.GONE);
                bin.cvlhwf1c4a.setVisibility(View.GONE);
                bin.cvlhwf1c5.setVisibility(View.GONE);
                bin.cvlhwf1c6.setVisibility(View.GONE);
                bin.cvlhwf1c7.setVisibility(View.GONE);
                bin.cvlhwf1c7A.setVisibility(View.GONE);
                bin.cvlhwf1c8.setVisibility(View.GONE);
                bin.cvlhwf1c9.setVisibility(View.GONE);

            } else {
                bin.cvlhwc11b.setVisibility(View.GONE);
                Clear.clearAllFields(bin.cvlhwc11b);


                bin.cvlhwf1c3A.setVisibility(View.VISIBLE);
                bin.cvlhwf1c4a.setVisibility(View.VISIBLE);
                bin.cvlhwf1c5.setVisibility(View.VISIBLE);
                bin.cvlhwf1c6.setVisibility(View.VISIBLE);
                bin.cvlhwf1c7.setVisibility(View.VISIBLE);
                bin.cvlhwf1c7A.setVisibility(View.VISIBLE);
                bin.cvlhwf1c8.setVisibility(View.VISIBLE);
                bin.cvlhwf1c9.setVisibility(View.VISIBLE);
            }
        }));

    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_snap) {

            Intent intent = new Intent(this, TakePhoto.class);

            //   intent.putExtra("picID", "901001" + "_" + "A-0001-001" + "_" + PhotoSerial + "_");
            //   intent.putExtra("childName", "Hassan");
// TODO: add identification information
            intent.putExtra("picID", Global.LhwSection_id + "_" + PhotoSerial);
            intent.putExtra("childName", "Maternal Care During Pregnancy");
            intent.putExtra("picView", "Sect_C".toUpperCase());
            startActivityForResult(intent, 1); // Activity is started with requestCode 1 = Front

        }

        if (view.getId() == R.id.btn_next) {

            if (!formValidation()) {
                return;
            }


            if (!GeneratorClass.checktextbox(bin.lhwf1c1, bin.lhwf1c2)) {
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
            int count = GeneratorClass.hh_section_count("TableF1SectionC", this);

            Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();


            Intent returnIntent = new Intent();
            returnIntent.putExtra("count", count + "");
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }


    }

    private boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, bin.pp);
    }

    void insert_data() {
        HashMap<String, String> Has_Map = new HashMap<>();
        GeneratorClass.Has_Map.clear();
        Has_Map.put("FK_id", Global.LhwHH_id + "");
        Has_Map.put("LhwSectionPKId", Global.LhwSection_id + "");
        Has_Map.put("Status", "0");

        //GeneratorClass.Insert_table(bin.pp, true);

        GeneratorClass.getContainerJSON(bin.pp, true);
        GeneratorClass.inert_db("TableF1SectionC", this, Has_Map);
        GeneratorClass.LHWSectionUpdateCOunt("LHWOfficeHHCount", Global.LhwSection_id, this);

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

            String fileName = data.getStringExtra("FileName");
            PhotoSerial++;

            bin.lhwf1cphoto.setText(bin.lhwf1cphoto.getText() + String.valueOf(PhotoSerial) + " - " + fileName + ";\r\n");
        } else {
            Toast.makeText(this, "Photo Cancelled", Toast.LENGTH_SHORT).show();
        }
    }


    /*@Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (buttonView.getId() == R.id.lhwc11a_2) {
            //ClearAllcontrol.ClearAll(bin.LvLhwc11b);
            Clear.clearAllFields(buttonView);
        }


        if (buttonView.getId() == R.id.lhwf1c6_1
                || buttonView.getId() == R.id.lhwf1c6_2
                || buttonView.getId() == R.id.lhwf1c6_3) {
            ClearAllcontrol.ClearAll(bin.LvLhwf1c7);
            ClearAllcontrol.ClearAll(bin.LvLhwf1c7A);
        }
        if (buttonView.getId() == R.id.lhwf1c8_1
                || buttonView.getId() == R.id.lhwf1c8_2
                || buttonView.getId() == R.id.lhwf1c8_3) {
            ClearAllcontrol.ClearAll(bin.LvLhwf1c9);
        }

    }*/

}
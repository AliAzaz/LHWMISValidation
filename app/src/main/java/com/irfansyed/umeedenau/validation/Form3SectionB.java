package com.irfansyed.umeedenau.validation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.irfansyed.umeedenau.validation.databinding.Form3sectionbBinding;
import com.validatorcrawler.aliazaz.Clear;

import java.util.HashMap;

import utils.GeneratorClass;
import utils.ValidatorClass;


public class Form3SectionB extends AppCompatActivity implements View.OnClickListener {


    //region Initialization
    Form3sectionbBinding bin;
    private int PhotoSerial;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bin = DataBindingUtil.setContentView(this, R.layout.form3sectionb);

        setupSkips();

        PhotoSerial = 0;

        bin.btnNext.setOnClickListener(this);
    }


    private void setupSkips() {

        bin.lhwf3b1.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (bin.lhwf3b12.isChecked()) {

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

                bin.cvlhwf3b5b.setVisibility(View.VISIBLE);

            } else if (bin.lhwf3b11.isChecked()) {

                Clear.clearAllFields(bin.cvlhwf3b5b);
                bin.cvlhwf3b5b.setVisibility(View.GONE);

                bin.cvlhwf3b2.setVisibility(View.VISIBLE);
                bin.cvlhwf3b3.setVisibility(View.VISIBLE);
                bin.cvlhwf3b4a.setVisibility(View.VISIBLE);
                bin.cvlhwf3b4b.setVisibility(View.VISIBLE);
                bin.cvlhwf3b4c.setVisibility(View.VISIBLE);

            }
        }));


        bin.lhwf3b2.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (bin.lhwf3b21.isChecked() && bin.lhwf3b11.isChecked()) {

                bin.cvlhwf3b3.setVisibility(View.VISIBLE);
                bin.cvlhwf3b4a.setVisibility(View.VISIBLE);
                bin.cvlhwf3b4b.setVisibility(View.VISIBLE);
                bin.cvlhwf3b4c.setVisibility(View.VISIBLE);
                bin.cvlhwf3b5.setVisibility(View.VISIBLE);
                bin.cvlhwf3b5b.setVisibility(View.VISIBLE);

            } else if (bin.lhwf3b22.isChecked()) {

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
            }
        }));


        bin.lhwf3b5.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (bin.lhwf3b52.isChecked()) {
                bin.cvlhwf3b5b.setVisibility(View.VISIBLE);
            } else if (bin.lhwf3b51.isChecked()) {
                bin.cvlhwf3b5b.setVisibility(View.GONE);
                Clear.clearAllFields(bin.cvlhwf3b5b);
            }
        }));


        bin.lhwf3b5b.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (bin.lhwf3b5b96.isChecked()) {
                bin.lhwf3b5b96x.setVisibility(View.VISIBLE);
            } else {
                bin.lhwf3b5b96x.setVisibility(View.GONE);
                Clear.clearAllFields(bin.lhwf3b5b96x);
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
            intent.putExtra("childName", "VHC SR");
            intent.putExtra("picView", "Sect_VHC_SR".toUpperCase());
            startActivityForResult(intent, 1); // Activity is started with requestCode 1 = Front

        }

        if (view.getId() == R.id.btn_next) {

            if (!formValidation()) {
                return;
            }

            insert_data();
            Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();

            finish();
        }
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        // Toast.makeText(this, requestCode + "_" + resultCode, Toast.LENGTH_SHORT).show();
        if (resultCode == 1) {
            Toast.makeText(this, "Photo Taken", Toast.LENGTH_SHORT).show();

            String fileName = data.getStringExtra("FileName");
            PhotoSerial++;

            bin.lhwf3photo.setText(bin.lhwf3photo.getText() + String.valueOf(PhotoSerial) + " - " + fileName + ";\r\n");
        } else {
            Toast.makeText(this, "Photo Cancelled", Toast.LENGTH_SHORT).show();
        }
    }


}
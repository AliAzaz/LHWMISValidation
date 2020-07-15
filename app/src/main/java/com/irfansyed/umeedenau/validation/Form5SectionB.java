package com.irfansyed.umeedenau.validation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.irfansyed.umeedenau.validation.databinding.Form5sectionbBinding;

import java.util.HashMap;

import utils.ClearAllcontrol;
import utils.GeneratorClass;
import utils.ValidatorClass;


public class Form5SectionB extends AppCompatActivity implements View.OnClickListener, RadioButton.OnCheckedChangeListener {

    //region Initialization
    Form5sectionbBinding bin;
    private int PhotoSerial;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bin = DataBindingUtil.setContentView(this, R.layout.form5sectionb);
        PhotoSerial = 0;


        bin.btnNext.setOnClickListener(this);
        bin.lhwf5b11.setOnCheckedChangeListener(this);
        bin.lhwf5b12.setOnCheckedChangeListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_snap) {
            Intent intent = new Intent(this, TakePhoto.class);

            //   intent.putExtra("picID", "901001" + "_" + "A-0001-001" + "_" + PhotoSerial + "_");
            //   intent.putExtra("childName", "Hassan");

// TODO: add identification information
            intent.putExtra("picID", Global.LhwHH_id + "_" + Global.LhwSection_id + "_" + PhotoSerial);
            intent.putExtra("childName", "WOMEN SUPPORT GROUP (WSG)");


            intent.putExtra("picView", "Sect_6B".toUpperCase());
            startActivityForResult(intent, 1); // Activity is started with requestCode 1 = Front

        }
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

        GeneratorClass.getContainerJSON(bin.SectionB, true);
        GeneratorClass.inert_db("TableF5SectionB", this, Has_Map);
        GeneratorClass.LHWSectionUpdateCOunt("LHWOfficeWSGCount", Global.LhwSection_id, this);

        /*JSONObject json = GeneratorClass.getContainerJSON(bin.SectionB, true);
        Log.d("insert_data: ", json.toString());*/

    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView.getId() == R.id.lhwf5b1_1 || buttonView.getId() == R.id.lhwf5b1_2) {

            if (bin.lhwf5b12.isChecked()) {

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        // Toast.makeText(this, requestCode + "_" + resultCode, Toast.LENGTH_SHORT).show();
        if (resultCode == 1) {
            Toast.makeText(this, "Photo Taken", Toast.LENGTH_SHORT).show();
            PhotoSerial++;

            String fileName = data.getStringExtra("FileName");
            bin.lhwf5bphoto.setText(bin.lhwf5bphoto.getText() + String.valueOf(PhotoSerial) + " - " + fileName + ";\r\n");
        } else {
            Toast.makeText(this, "Photo Cancelled", Toast.LENGTH_SHORT).show();
        }
    }
}
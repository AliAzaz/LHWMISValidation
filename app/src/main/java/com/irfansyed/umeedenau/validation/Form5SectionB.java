package com.irfansyed.umeedenau.validation;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.irfansyed.umeedenau.validation.databinding.Form5sectionbBinding;
import com.validatorcrawler.aliazaz.Clear;

import org.json.JSONObject;

import java.util.HashMap;

import utils.ClearAllcontrol;
import utils.GeneratorClass;
import utils.ValidatorClass;


public class Form5SectionB extends AppCompatActivity implements View.OnClickListener {

    //region Initialization
    Form5sectionbBinding bin;
    private int PhotoSerial;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bin = DataBindingUtil.setContentView(this, R.layout.form5sectionb);

        setupSkips();

        PhotoSerial = 0;

        bin.btnNext.setOnClickListener(this);
        bin.btnSnap.setOnClickListener(this);
    }


    private void setupSkips() {

        bin.lhwf5b1.setOnCheckedChangeListener(((radioGroup, i) -> {

            if (bin.lhwf5b12.isChecked()) {

                bin.cvlhwf5b2.setVisibility(View.GONE);
                bin.cvlhwf5b3.setVisibility(View.GONE);
                bin.cvlhwf5b4.setVisibility(View.GONE);
                bin.cvlhwf5b5a.setVisibility(View.GONE);
                bin.cvlhwf5b5b.setVisibility(View.GONE);
                bin.cvlhwf5b5c.setVisibility(View.GONE);
                bin.cvlhwf5b5a1.setVisibility(View.GONE);
                bin.cvlhwf5b5b1.setVisibility(View.GONE);
                bin.cvlhwf5b6.setVisibility(View.GONE);
                bin.cvlhwf5b7.setVisibility(View.GONE);

                Clear.clearAllFields(bin.cvlhwf5b2);
                Clear.clearAllFields(bin.cvlhwf5b3);
                Clear.clearAllFields(bin.cvlhwf5b4);
                Clear.clearAllFields(bin.cvlhwf5b5a);
                Clear.clearAllFields(bin.cvlhwf5b5b);
                Clear.clearAllFields(bin.cvlhwf5b5c);
                Clear.clearAllFields(bin.cvlhwf5b5a1);
                Clear.clearAllFields(bin.cvlhwf5b5b1);
                Clear.clearAllFields(bin.cvlhwf5b6);
                Clear.clearAllFields(bin.cvlhwf5b7);

            } else if (bin.lhwf5b11.isChecked()) {

                bin.cvlhwf5b2.setVisibility(View.VISIBLE);
                bin.cvlhwf5b3.setVisibility(View.VISIBLE);
                bin.cvlhwf5b4.setVisibility(View.VISIBLE);
                bin.cvlhwf5b5a.setVisibility(View.VISIBLE);
                bin.cvlhwf5b5b.setVisibility(View.VISIBLE);
                bin.cvlhwf5b5c.setVisibility(View.VISIBLE);
                bin.cvlhwf5b5a1.setVisibility(View.VISIBLE);
                bin.cvlhwf5b5b1.setVisibility(View.VISIBLE);
                bin.cvlhwf5b6.setVisibility(View.VISIBLE);
                bin.cvlhwf5b7.setVisibility(View.VISIBLE);

            }

        }));


        bin.lhwf5b5a1.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (bin.lhwf5b5a1.getText().toString().equals("0")) {
                    bin.cvlhwf5b5b1.setVisibility(View.VISIBLE);

                    Clear.clearAllFields(bin.cvlhwf5b6);
                    Clear.clearAllFields(bin.cvlhwf5b7);

                    bin.cvlhwf5b6.setVisibility(View.GONE);
                    bin.cvlhwf5b7.setVisibility(View.GONE);

                } else {
                    bin.cvlhwf5b5b1.setVisibility(View.GONE);
                    Clear.clearAllFields(bin.cvlhwf5b5b1);


                    if (bin.lhwf5b11.isChecked()) {
                        bin.cvlhwf5b6.setVisibility(View.VISIBLE);
                        bin.cvlhwf5b7.setVisibility(View.VISIBLE);
                    } else {
                        bin.cvlhwf5b6.setVisibility(View.GONE);
                        bin.cvlhwf5b7.setVisibility(View.GONE);
                    }

                }
            }
        });


        bin.lhwf5b5b1.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (bin.lhwf5b5b196.isChecked()) {
                bin.lhwf5b5b196x.setVisibility(View.VISIBLE);
            } else {
                bin.lhwf5b5b196x.setVisibility(View.GONE);
                Clear.clearAllFields(bin.lhwf5b5b196x);
            }

        }));


        bin.lhwf5b796.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                    bin.lhwf5b796x.setVisibility(View.VISIBLE);
                } else {
                    bin.lhwf5b796x.setVisibility(View.GONE);
                    Clear.clearAllFields(bin.lhwf5b796x);
                }
            }
        });

    }


    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btn_snap) {

            Intent intent = new Intent(this, TakePhoto.class);

            //   intent.putExtra("picID", "901001" + "_" + "A-0001-001" + "_" + PhotoSerial + "_");
            //   intent.putExtra("childName", "Hassan");
// TODO: add identification information
            intent.putExtra("picID", Global.LhwSection_id + "_" + PhotoSerial);
            intent.putExtra("childName", "WSG SR");
            intent.putExtra("picView", "Sect_WSG_SR".toUpperCase());
            startActivityForResult(intent, 1); // Activity is started with requestCode 1 = Front

        }


        if (view.getId() == R.id.btnNext) {

            if (!formValidation()) {
                return;
            }

            insert_data();
            Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();

            finish();
        }
    }

    private boolean formValidation() {

        if (!ValidatorClass.EmptyCheckingContainer(this, bin.SectionB)) {
            return false;
        }


        if (!bin.lhwf5bphoto.getText().toString().equals("")) {

        } else {
            Toast.makeText(this, "Please take photo", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
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


    /*@Override
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

            bin.lhwf5bphoto.setText(bin.lhwf5bphoto.getText() + String.valueOf(PhotoSerial) + " - " + fileName + ";\r\n");
        } else {
            Toast.makeText(this, "Photo Cancelled", Toast.LENGTH_SHORT).show();
        }
    }

}
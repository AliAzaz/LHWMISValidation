package com.irfansyed.umeedenau.validation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.irfansyed.umeedenau.validation.databinding.Form1sectionfBinding;

import java.util.HashMap;

import utils.GeneratorClass;
import utils.ValidatorClass;


public class Form1SectionF extends AppCompatActivity implements View.OnClickListener {


    //region Initialization
    Form1sectionfBinding bin;
    private int PhotoSerial;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bin = DataBindingUtil.setContentView(this, R.layout.form1sectionf);
        bin.btnNext.setOnClickListener(this);
        bin.btnSnap.setOnClickListener(this);

        PhotoSerial = 0;


        if (GeneratorClass.LHWsectionStatus("TableF1SectionF") == false) {

            bin.lhwf1f1.setText("000");
            bin.lhwf1f1.setVisibility(View.GONE);

            bin.lhwf1f2.setText("000");
            bin.lhwf1f2.setVisibility(View.GONE);

        }


        bin.lhwf1f1.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                if (bin.lhwf1f1.getText().toString().isEmpty()) return;
                bin.lhwf1f3.setMaxvalue(Integer.valueOf(bin.lhwf1f1.getText().toString()) > 20 ? 20 : Integer.valueOf(bin.lhwf1f1.getText().toString()));
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
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
            public void afterTextChanged(Editable s) {
                if (bin.lhwf1f2.getText().toString().isEmpty()) return;
                bin.lhwf1f3.setMaxvalue(Integer.valueOf(bin.lhwf1f2.getText().toString()) > 20 ? 20 : Integer.valueOf(bin.lhwf1f2.getText().toString()));
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

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

        if (view.getId() == R.id.btn_snap) {
            Intent intent = new Intent(this, TakePhoto.class);

            //    intent.putExtra("picID", "901001" + "_" + "A-0001-001" + "_" + PhotoSerial + "_");
            //  intent.putExtra("childName", "Hassan");

// TODO: add identification information
            intent.putExtra("picID", Global.LhwSection_id + "_" + PhotoSerial);
            intent.putExtra("childName", "Diarrhea");


            intent.putExtra("picView", "Sect_F".toUpperCase());
            startActivityForResult(intent, 1); // Activity is started with requestCode 1 = Front

        }
        if (view.getId() == R.id.btn_next) {

            if (!formValidation()) {
                return;
            }


            insert_data();
            int count = GeneratorClass.hh_section_count("TableF1SectionF", this);

            Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();


            Intent returnIntent = new Intent();
            returnIntent.putExtra("count", count + "");
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }
    }


    private boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, bin.SectionF);
    }


    void insert_data() {
        HashMap<String, String> Has_Map = new HashMap<>();
        GeneratorClass.Has_Map.clear();


        Has_Map.put("FK_id", Global.LhwHH_id + "");
        Has_Map.put("LhwSectionPKId", Global.LhwSection_id + "");
        Has_Map.put("Status", "0");

        //GeneratorClass.Insert_table(bin.SectionF, true);

        GeneratorClass.getContainerJSON(bin.SectionF, true);
        GeneratorClass.inert_db("TableF1SectionF", this, Has_Map);
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
            PhotoSerial++;

            String fileName = data.getStringExtra("FileName");
            bin.lhwf1fphoto.setText(bin.lhwf1fphoto.getText() + String.valueOf(PhotoSerial) + " - " + fileName + ";\r\n");
        } else {
            Toast.makeText(this, "Photo Cancelled", Toast.LENGTH_SHORT).show();
        }
    }
}

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

import com.irfansyed.umeedenau.validation.databinding.Form1sectiongBinding;
import com.validatorcrawler.aliazaz.Clear;

import java.util.HashMap;

import utils.GeneratorClass;
import utils.ValidatorClass;


public class Form1SectionG extends AppCompatActivity implements View.OnClickListener {


    //region Initialization
    Form1sectiongBinding bin;
    private int PhotoSerial;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bin = DataBindingUtil.setContentView(this, R.layout.form1sectiong);

        setupSkips();

        bin.btnNext.setOnClickListener(this);
        bin.btnSnap.setOnClickListener(this);

        PhotoSerial = 0;


        if (GeneratorClass.LHWsectionStatus("TableF1SectionG") == false) {

            bin.lhwf1g1.setText("000");
            bin.lhwf1g1.setVisibility(View.GONE);

            bin.lhwf1g2.setText("000");
            bin.lhwf1g2.setVisibility(View.GONE);
        }


    }


    private void setupSkips() {
        bin.lhwf1g1.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                if (bin.lhwf1g1.getText().toString().isEmpty()) return;
                bin.lhwf1g3.setMaxvalue(Integer.valueOf(bin.lhwf1g1.getText().toString()) > 20 ? 20 : Integer.valueOf(bin.lhwf1g1.getText().toString()));
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                /*if (s.length() != 0) {
                    bin.lhwf1g2.setVisibility(View.GONE);
                    //   bin.lhwf1g2.setText("00");
                } else {
                    bin.lhwf1g2.setVisibility(View.VISIBLE);
                    //  bin.lhwf1g2.setText("");
                }*/
            }
        });


        bin.lhwf1g2.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                if (bin.lhwf1g2.getText().toString().isEmpty()) return;
                bin.lhwf1g3.setMaxvalue(Integer.valueOf(bin.lhwf1g2.getText().toString()) > 20 ? 20 : Integer.valueOf(bin.lhwf1g2.getText().toString()));
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                /*if (s.length() != 0) {
                    bin.lhwf1g1.setVisibility(View.GONE);
                    //  bin.lhwf1g1.setText("00");
                } else {
                    bin.lhwf1g1.setVisibility(View.VISIBLE);
                    // bin.lhwf1g1.setText("");
                }*/
            }
        });


        bin.lhwf1g10a.setOnCheckedChangeListener(((radioGroup, i) -> {

            if (bin.lhwf1g10a2.isChecked()) {
                bin.cvlhwf1g10b.setVisibility(View.VISIBLE);

                bin.cvlhwf1g4A.setVisibility(View.GONE);
                bin.cvlhwf1g6.setVisibility(View.GONE);
                bin.cvlhwf1g7.setVisibility(View.GONE);
                bin.cvlhwf1g8.setVisibility(View.GONE);

                Clear.clearAllFields(bin.cvlhwf1g4A);
                Clear.clearAllFields(bin.cvlhwf1g6);
                Clear.clearAllFields(bin.cvlhwf1g7);
                Clear.clearAllFields(bin.cvlhwf1g8);

            } else {
                Clear.clearAllFields(bin.cvlhwf1g10b);
                bin.cvlhwf1g10b.setVisibility(View.GONE);

                bin.cvlhwf1g4A.setVisibility(View.VISIBLE);
                bin.cvlhwf1g6.setVisibility(View.VISIBLE);
                bin.cvlhwf1g7.setVisibility(View.VISIBLE);
                bin.cvlhwf1g8.setVisibility(View.VISIBLE);

            }

        }));
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_snap) {
            Intent intent = new Intent(this, TakePhoto.class);

            // intent.putExtra("picID", "901001" + "_" + "A-0001-001" + "_" + PhotoSerial + "_");
            //  intent.putExtra("childName", "Hassan");

// TODO: add identification information
            intent.putExtra("picID", Global.LhwSection_id + "_" + PhotoSerial);
            intent.putExtra("childName", "Cough or Difficult Breathing");


            intent.putExtra("picView", "Sect_G".toUpperCase());
            startActivityForResult(intent, 1); // Activity is started with requestCode 1 = Front

        }

        if (view.getId() == R.id.btn_next) {

            if (!formValidation()) {
                return;
            }


        /*if(bin.lhwf1g1.getText().length()>0) {
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
        }*/


            insert_data();
            int count = GeneratorClass.hh_section_count("TableF1SectionG", this);

            Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();


            Intent returnIntent = new Intent();
            returnIntent.putExtra("count", count + "");
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }
    }

    private boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, bin.SectionG);
    }


    void insert_data() {
        HashMap<String, String> Has_Map = new HashMap<>();
        GeneratorClass.Has_Map.clear();


        Has_Map.put("FK_id", Global.LhwHH_id + "");
        Has_Map.put("LhwSectionPKId", Global.LhwSection_id + "");
        Has_Map.put("Status", "0");

        //GeneratorClass.Insert_table(bin.SectionG, true);

        GeneratorClass.getContainerJSON(bin.SectionG, true);
        GeneratorClass.inert_db("TableF1SectionG", this, Has_Map);
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
            bin.lhwf1gphoto.setText(bin.lhwf1gphoto.getText() + String.valueOf(PhotoSerial) + " - " + fileName + ";\r\n");
        } else {
            Toast.makeText(this, "Photo Cancelled", Toast.LENGTH_SHORT).show();
        }
    }
}

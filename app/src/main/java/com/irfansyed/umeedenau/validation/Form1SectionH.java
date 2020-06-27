package com.irfansyed.umeedenau.validation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.irfansyed.umeedenau.validation.databinding.Form1sectionhBinding;
import com.validatorcrawler.aliazaz.Clear;

import java.util.HashMap;

import utils.GeneratorClass;
import utils.ValidatorClass;


public class Form1SectionH extends AppCompatActivity implements View.OnClickListener {


    //region Initialization
    Form1sectionhBinding bin;
    private int PhotoSerial;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bin = DataBindingUtil.setContentView(this, R.layout.form1sectionh);

        bin.btnNext.setOnClickListener(this);
        bin.btnSnap.setOnClickListener(this);

        PhotoSerial = 0;


        if (GeneratorClass.LHWsectionStatus("TableF1SectionH") == false) {

            bin.lhwf1h1.setText("000");
            bin.lhwf1h1.setVisibility(View.GONE);
        }


        bin.lhwf1h10a.setOnCheckedChangeListener(((radioGroup, i) -> {

            if (i == bin.lhwf1h10a2.getId()) {
                Clear.clearAllFields(bin.cvlhwf1h5);
                Clear.clearAllFields(bin.cvlhwf1h6);
                Clear.clearAllFields(bin.cvlhwf1h7);
                Clear.clearAllFields(bin.cvlhwf1h8);
                Clear.clearAllFields(bin.cvlhwf1h9);

                bin.cvlhwf1h10b.setVisibility(View.VISIBLE);
                bin.cvlhwf1h5.setVisibility(View.GONE);
                bin.cvlhwf1h6.setVisibility(View.GONE);
                bin.cvlhwf1h7.setVisibility(View.GONE);
                bin.cvlhwf1h8.setVisibility(View.GONE);
                bin.cvlhwf1h9.setVisibility(View.GONE);

            } else {
                Clear.clearAllFields(bin.cvlhwf1h10b);


                bin.cvlhwf1h10b.setVisibility(View.GONE);

                bin.cvlhwf1h5.setVisibility(View.VISIBLE);
                bin.cvlhwf1h6.setVisibility(View.VISIBLE);
                bin.cvlhwf1h7.setVisibility(View.VISIBLE);
                bin.cvlhwf1h8.setVisibility(View.VISIBLE);
                bin.cvlhwf1h9.setVisibility(View.VISIBLE);
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
            intent.putExtra("picID", Global.LhwHH_id + "_" + Global.LhwSection_id + "_" + PhotoSerial);
            intent.putExtra("childName", "Married Women of Reproductive Age");


            intent.putExtra("picView", "Sect_H".toUpperCase());
            startActivityForResult(intent, 1); // Activity is started with requestCode 1 = Front

        }
        if (view.getId() == R.id.btn_Next) {

            if (!formValidation()) {
                return;
            }



        /*if(bin.lhwf1h1.getText().length()>0) {
            int a = Integer.parseInt(bin.lhwf1h1.getText().toString());
            if (a > 20) {
                bin.lhwf1h1.requestFocus();
                bin.lhwf1h1.setError("Should be less then 20");

                return;
            }

        }*/


            if (bin.lhwf1h1.getText().length() > 0) {
                int a = Integer.parseInt(bin.lhwf1h2.getText().toString());
                if (a > 10) {
                    bin.lhwf1h2.requestFocus();
                    bin.lhwf1h2.setError("Should be less then 10");

                    return;
                }

            }


            if (!GeneratorClass.checktextbox(bin.lhwf1h1, bin.lhwf1h2)) {
                return;
            }

            if (bin.lhwf1h5.getText().length() > 0) {
                int age = Integer.parseInt(bin.lhwf1h5.getText().toString());

                if (age < 15 || age > 49) {
                    Toast.makeText(this, "MARWA Age Must be Between 15 to 49", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            insert_data();
            int count = GeneratorClass.hh_section_count("TableF1SectionH", this);

            Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();


            Intent returnIntent = new Intent();
            returnIntent.putExtra("count", count + "");
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }
    }


    private boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, bin.SectionH);
    }


    void insert_data() {
        HashMap<String, String> Has_Map = new HashMap<>();
        GeneratorClass.Has_Map.clear();


        Has_Map.put("FK_id", Global.LhwHH_id + "");
        Has_Map.put("LhwSectionPKId", Global.LhwSection_id + "");
        Has_Map.put("Status", "0");

        //GeneratorClass.Insert_table(bin.SectionH, true);

        GeneratorClass.getContainerJSON(bin.SectionH, true);
        GeneratorClass.inert_db("TableF1SectionH", this, Has_Map);
        GeneratorClass.LHWSectionUpdateCOunt("LHWOfficeHHCount", Global.LhwSection_id, this);


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
            bin.lhwf1hphoto.setText(bin.lhwf1hphoto.getText() + String.valueOf(PhotoSerial) + " - " + fileName + ";\r\n");
        } else {
            Toast.makeText(this, "Photo Cancelled", Toast.LENGTH_SHORT).show();
        }
    }

}

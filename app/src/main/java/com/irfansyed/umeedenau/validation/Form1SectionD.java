package com.irfansyed.umeedenau.validation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.irfansyed.umeedenau.validation.databinding.Form1sectiondBinding;

import java.util.HashMap;

import utils.GeneratorClass;
import utils.ValidatorClass;


public class Form1SectionD extends AppCompatActivity implements View.OnClickListener {


    //region Initialization
    Form1sectiondBinding bin;
    private int PhotoSerial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bin = DataBindingUtil.setContentView(this, R.layout.form1sectiond);

        bin.btnNext.setOnClickListener(this);
        PhotoSerial = 0;


        if (GeneratorClass.LHWsectionStatus("TableF1SectionD") == false) {

            bin.lhwf1d1.setText("000");
            bin.lhwf1d1.setVisibility(View.GONE);
        }


    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_snap) {
            Intent intent = new Intent(this, TakePhoto.class);

            //   intent.putExtra("picID", "901001" + "_" + "A-0001-001" + "_" + PhotoSerial + "_");
            //   intent.putExtra("childName", "Hassan");

// TODO: add identification information
            intent.putExtra("picID", Global.LhwSection_id + "_" + PhotoSerial);
            intent.putExtra("childName", "Maternal Care After Deliver");


            intent.putExtra("picView", "Sect_D".toUpperCase());
            startActivityForResult(intent, 1); // Activity is started with requestCode 1 = Front

        }

        if (!formValidation()) {
            return;
        }


        if (!GeneratorClass.checktextbox(bin.lhwf1d1, bin.lhwf1d2)) {
            return;
        }


        if (bin.lhwf1d1.getText().length() > 0) {

            int a = Integer.parseInt(bin.lhwf1d1.getText().toString());
            if (a > 50) {
                bin.lhwf1d1.requestFocus();
                bin.lhwf1d1.setError("Should be less then 50 or greater then 0 ");

                return;
            }
        }
        int b = Integer.parseInt(bin.lhwf1d2.getText().toString());
        if (b > 5) {
            bin.lhwf1d2.requestFocus();
            bin.lhwf1d2.setError("Should be less then 5");

            return;
        }


        insert_data();
        int count = GeneratorClass.hh_section_count("TableF1SectionD", this);

        Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();


        Intent returnIntent = new Intent();
        returnIntent.putExtra("count", count + "");
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }


    private boolean formValidation() {
        return ValidatorClass.EmptyCheckingContainer(this, bin.Dsection);
    }

    void insert_data() {
        HashMap<String, String> Has_Map = new HashMap<>();


        Has_Map.put("FK_id", Global.LhwHH_id + "");
        Has_Map.put("LhwSectionPKId", Global.LhwSection_id + "");
        Has_Map.put("Status", "0");

        GeneratorClass.Has_Map.clear();
        GeneratorClass.Insert_table(bin.Dsection, true);
        GeneratorClass.inert_db("TableF1SectionD", this, Has_Map);
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
            bin.lhwf1dphoto.setText(bin.lhwf1dphoto.getText() + String.valueOf(PhotoSerial) + " - " + fileName + ";\r\n");
        } else {
            Toast.makeText(this, "Photo Cancelled", Toast.LENGTH_SHORT).show();
        }
    }
}

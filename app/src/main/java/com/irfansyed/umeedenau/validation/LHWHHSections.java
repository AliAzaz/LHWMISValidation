package com.irfansyed.umeedenau.validation;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.irfansyed.umeedenau.validation.databinding.LhwhhsectionBinding;

import data.LocalDataManager;
import utils.GeneratorClass;

import static data.LocalDataManager.database;


public class LHWHHSections extends AppCompatActivity implements View.OnClickListener {


    //region Initialization
    LhwhhsectionBinding bin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bin = DataBindingUtil.setContentView(this, R.layout.lhwhhsection);

        bin.btnSectionC.setOnClickListener(this);
        bin.btnSectionD.setOnClickListener(this);
        bin.btnSectionE.setOnClickListener(this);
        bin.btnSectionF.setOnClickListener(this);
        bin.btnSectionG.setOnClickListener(this);
        bin.btnSectionH.setOnClickListener(this);

        update_button_header();

    }


    @Override
    public void onClick(View view) {
        show_alert(view);

    }


    private void show_alert(final View click_view) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogLayout = inflater.inflate(R.layout.form1sectionb, null);
        builder.setView(dialogLayout);
        builder.setCancelable(false);


        final EditText lhwf1b1, lhwf1b2, lhwf1b3;
        final LinearLayout lv_lhwf1b1 = dialogLayout.findViewById(R.id.lv_lhwf1b1);
        final LinearLayout lv_lhwf1b2 = dialogLayout.findViewById(R.id.lv_lhwf1b2);

        lhwf1b1 = dialogLayout.findViewById(R.id.lhwf1b1);
        lhwf1b2 = dialogLayout.findViewById(R.id.lhwf1b2);
        lhwf1b3 = dialogLayout.findViewById(R.id.lhwf1b3);

        final Button btn_cancel, btn_ok;
        btn_cancel = dialogLayout.findViewById(R.id.btn_cancel);
        btn_ok = dialogLayout.findViewById(R.id.btn_ok);
        final AlertDialog dialog = builder.create();


        dialog.show();


        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int requestCode = 0;
                String Section = "";


                if (Gothrough.IamHiden(lv_lhwf1b1) == false) {

                    return;
                }

                if (Gothrough.IamHiden(lv_lhwf1b2) == false) {

                    return;
                }

                if (lhwf1b1.getText().toString().equals("0")) {
                    lhwf1b1.setError("not be equals to 0 ");
                    return;
                }

                Intent intt;

                if (click_view.getId() == R.id.btnSectionC) {
                    intt = new Intent(getBaseContext(), Form1SectionC.class);
                    requestCode = 1;
                    Section = "TableF1SectionC";


                } else if (click_view.getId() == R.id.btnSectionD) {
                    intt = new Intent(getBaseContext(), Form1SectionD.class);
                    requestCode = 2;
                    Section = "TableF1SectionD";
                } else if (click_view.getId() == R.id.btnSectionE) {
                    intt = new Intent(getBaseContext(), Form1SectionE.class);
                    requestCode = 3;
                    Section = "TableF1SectionE";
                } else if (click_view.getId() == R.id.btnSectionF) {
                    intt = new Intent(getBaseContext(), Form1SectionF.class);
                    requestCode = 4;
                    Section = "TableF1SectionF";
                } else if (click_view.getId() == R.id.btnSectionG) {
                    intt = new Intent(getBaseContext(), Form1SectionG.class);
                    requestCode = 5;
                    Section = "TableF1SectionG";
                } else {
                    intt = new Intent(getBaseContext(), Form1SectionH.class);
                    requestCode = 6;
                    Section = "TableF1SectionH";
                }


                if (lhwf1b2.getText().toString().length() == 0) {
                    lhwf1b2.setText("00");
                }

                if (lhwf1b3.getText().toString().length() == 0) {
                    lhwf1b3.setText("00");
                }


                insert_db(lhwf1b1.getText().toString(), lhwf1b2.getText().toString(), lhwf1b3.getText().toString(), Section);


                dialog.dismiss();


                startActivityForResult(intt, requestCode);

            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == Activity.RESULT_OK) {
            String count = data.getStringExtra("count");
            update_button_texts(requestCode, count);
        }
        if (resultCode == Activity.RESULT_CANCELED) {
            //Write your code if there's no result
        }
    }//


    void update_button_header() {
        int count = 0;
        count = GeneratorClass.hh_section_count("TableF1SectionC", this);
        update_button_texts(1, count + "");
        count = GeneratorClass.hh_section_count("TableF1SectionD", this);
        update_button_texts(2, count + "");
        count = GeneratorClass.hh_section_count("TableF1SectionE", this);
        update_button_texts(3, count + "");
        count = GeneratorClass.hh_section_count("TableF1SectionF", this);
        update_button_texts(4, count + "");
        count = GeneratorClass.hh_section_count("TableF1SectionG", this);
        update_button_texts(5, count + "");
        count = GeneratorClass.hh_section_count("TableF1SectionH", this);
        update_button_texts(6, count + "");

    }


    void update_button_texts(int requestCode, String count) {
        if (requestCode == 1) {
            bin.btnSectionC.setText(getResources().getString(R.string.lhwf1header_c) + "(" + count + ")");
        } else if (requestCode == 2) {
            bin.btnSectionD.setText(getResources().getString(R.string.lhwf1header_d) + "(" + count + ")");
        } else if (requestCode == 3) {
            bin.btnSectionE.setText(getResources().getString(R.string.lhwf1header_e) + "(" + count + ")");
        } else if (requestCode == 4) {
            bin.btnSectionF.setText(getResources().getString(R.string.lhwf1header_f) + "(" + count + ")");
        } else if (requestCode == 5) {
            bin.btnSectionG.setText(getResources().getString(R.string.lhwf1header_g) + "(" + count + ")");
        } else {
            bin.btnSectionH.setText(getResources().getString(R.string.lhwf1header_h) + "(" + count + ")");
        }
    }

    public void insert_db(String lhwf1b1, String lhwf1b2, String lhwf1b3, String Section) {
        String query = "insert into  TableHHSection (lhwf1b1,lhwf1b2,lhwf1b3,FK_id,Section) values('" +

                lhwf1b1 + "','" + lhwf1b2 + "','" + lhwf1b3 + "','" + Global.LhwSection_id + "','" + Section + "')";

        query = String.format(query);

        LocalDataManager validationactivity = new LocalDataManager(this);

        database.execSQL(query);


        String query2 = "select id from  TableHHSection where FK_id='" + Global.LhwSection_id + "'";


        query = String.format(query2);

        LocalDataManager Lm = new LocalDataManager(this);
        Cursor c = database.rawQuery(query2, null);


        if (c != null) {
            if (c.moveToFirst()) {
                do {

                    Global.LhwHH_id = c.getInt(0);


                } while (c.moveToNext());
            }
        }

    }


}

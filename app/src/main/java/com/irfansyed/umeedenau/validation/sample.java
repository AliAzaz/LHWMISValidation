package com.irfansyed.umeedenau.validation;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.irfansyed.umeedenau.validation.databinding.LhwdashbordBinding;


public class sample extends AppCompatActivity implements View.OnClickListener {


    //region Initialization
    LhwdashbordBinding bin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bin = DataBindingUtil.setContentView(this, R.layout.lhwdashbord);

    }


    @Override
    public void onClick(View view) {

    }


}
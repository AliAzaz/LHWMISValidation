package com.irfansyed.umeedenau.validation;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import data.LocalDataManager;
import utils.BackgroundTask2;
import utils.MyPreferences;
import utils.PostRequestData;

public class LoginActivity extends AppCompatActivity implements View.OnTouchListener {

    double AppVersion;
    ProgressDialog bar;
    String version;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        CheckBox showpassowrd = findViewById(R.id.showpassowrd);


        MyPreferences pref = new MyPreferences(this);
        AppVersion = Double.parseDouble(pref.getAppVersion());

        final EditText textUsername = findViewById(R.id.login_username);
        final EditText textPassword = findViewById(R.id.login_password);
        final TextView textlogout = findViewById(R.id.txt_logut);
        Button btnLogin = findViewById(R.id.login_btn);
        final MyPreferences preferences = new MyPreferences(this);

        Button UpdateButton = findViewById(R.id.Updateapp);


        showpassowrd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    textPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    textPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });


        textlogout.setOnTouchListener(this);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (textUsername.getText().toString().trim().isEmpty()
                        || textPassword.getText().toString().trim().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                    return;
                } else if (preferences.getUserId() == -1) {


                    new LoginAsync(LoginActivity.this, textUsername.getText().toString().trim(),
                            textPassword.getText().toString().trim()).execute();
                } else {
                    if (textUsername.getText().toString().trim().equals(preferences.getUsername())) {

                        if (textPassword.getText().toString().trim().equals(preferences.getPassword())) {


                            // call andother activity


                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            LoginActivity.this.startActivity(intent);
                            LoginActivity.this.finish();

                        } else {
                            textPassword.setError("Incorrect use Password");
                        }

                    } else {
                        textUsername.setError("Incorrect use name");
                    }
                }

            }
        });


        try {

            String packageName = getApplicationContext().getPackageName();

            long installedOn = this
                    .getPackageManager()
                    .getPackageInfo(packageName, 0)
                    .lastUpdateTime;
            long versionCode = this
                    .getPackageManager()
                    .getPackageInfo(packageName, 0)
                    .versionCode;
            String versionName = this
                    .getPackageManager()
                    .getPackageInfo(packageName, 0)
                    .versionName;
            version = "Ver. " + versionName + "." + versionCode + " \r\n( Last Updated: " + new SimpleDateFormat("dd MMM. yyyy").format(new Date(installedOn)) + " )";
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


        UpdateButton.setText(version);

        UpdateButton.setEnabled(false);

        String method2 = "check_app_update";
        BackgroundTask2 backgroundTask2 = new BackgroundTask2(this, UpdateButton, AppVersion);
        backgroundTask2.execute(method2);

        UpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DownloadNewVersion().execute();

            }
        });


    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        MyPreferences MyPreferences;


        new MyPreferences(LoginActivity.this).removeUserId();
        Toast.makeText(this, "Logout Succefully", Toast.LENGTH_LONG).show();
        return false;
    }


    // Update Appp


    class DownloadNewVersion extends AsyncTask<String, Integer, Boolean> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            bar = new ProgressDialog(LoginActivity.this);
            bar.setCancelable(false);

            bar.setMessage("Downloading...");

            bar.setIndeterminate(true);
            bar.setCanceledOnTouchOutside(false);
            bar.show();

        }

        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);

            bar.setIndeterminate(false);
            bar.setMax(100);
            bar.setProgress(progress[0]);
            String msg = "";
            if (progress[0] > 99) {

                msg = "Finishing... ";

            } else {

                msg = "Downloading... " + progress[0] + "%";
            }
            bar.setMessage(msg);

        }

        @Override
        protected void onPostExecute(Boolean result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);

            bar.dismiss();

            if (result) {

                Toast.makeText(getApplicationContext(), "Update Done",
                        Toast.LENGTH_SHORT).show();

            } else {

                Toast.makeText(getApplicationContext(), "Error: Try Again (Check Internet Connection)",
                        Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected Boolean doInBackground(String... arg0) {

            Boolean flag = false;

            try {

                URL url = new URL("http://43.245.131.159:8080/uendashboard/lhw/app-debug.apk");

                HttpURLConnection c = (HttpURLConnection) url.openConnection();
                c.setRequestMethod("GET");
                c.setDoOutput(true);
                c.connect();

                String PATH = Environment.getExternalStorageDirectory() + "/Download/";
                File file = new File(PATH);
                file.mkdirs();

                File outputFile = new File(file, "app-debug.apk");

                if (outputFile.exists()) {
                    outputFile.delete();
                }

                InputStream is = c.getInputStream();

                int total_size = 14316922;//size of apk

                byte[] buffer = new byte[1024];
                int len1 = 0;
                int per = 0;
                int downloaded = 0;

                FileOutputStream fos = new FileOutputStream(outputFile);

                while ((len1 = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, len1);
                    downloaded += len1;
                    per = downloaded * 100 / total_size;
                    publishProgress(per);
                }
                fos.close();
                is.close();

                // newverions(PATH);

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(new File(PATH + "app-debug.apk")), "application/vnd.android.package-archive");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);


                flag = true;
            } catch (Exception e) {
                Log.e("Taq", "Update Error: " + e.getMessage());
                flag = false;
            }
            return flag;


        }


    }


}


class LoginAsync extends AsyncTask {
    Context mContext;
    ProgressDialog mDialog;
    String mUserMsg, mUsername, mPassword;
    LocalDataManager LmManger;

    public LoginAsync(Context context, String username, String password) {
        this.mContext = context;
        this.mUsername = username;
        this.mPassword = password;
        mDialog = new ProgressDialog(context);
    }

    @Override
    protected void onPreExecute() {
        mDialog.setMessage("Logging in..");
        mDialog.setCancelable(false);
        mDialog.show();

        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(Object[] params) {
        URL url;
        HttpURLConnection connection;
        String urlString = new MyPreferences(mContext).getReqLogin();

        //"http://umeed.bsoftproducts.com//Testing/check_user_id";

        try {
            url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");

            OutputStream os = connection.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));

            // set pa
            // rameter values for post-request
            HashMap<String, String> param = new HashMap<String, String>();
            param.put("username", mUsername);
            param.put("password", mPassword);

            bw.write(PostRequestData.getData(param));
            bw.flush();

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String data = "", line;
                while ((line = br.readLine()) != null) {
                    data += line;
                }

                return data;
            } else {
                mUserMsg = "Server Couldn't process the request";
            }
        } catch (IOException e) {
            mUserMsg = "Please make sure that Internet connection is available," +
                    " and server IP is inserted in settings";
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        try {

            //connection isn't available or something is wrong with server address
            if (mUserMsg != null)
                throw new IOException();

            String resp = (String) o;

            if (resp == null || resp.equals(""))
                throw new NullPointerException("Server response is empty");
            else if (resp.equals("-1")) {
                mUserMsg = "Incorrect username or password";
            } else {
                mUserMsg = null;
                MyPreferences prefs = new MyPreferences(mContext);

                // for login id and data collectore Name irfan

                //   Toast.makeText(mContext, resp, Toast.LENGTH_SHORT).show();
                String[] resp_arry = resp.split("!");

                String Distric = resp_arry[0];
                String tehsil = resp_arry[1];
                String Reporting_LHS = resp_arry[2];
                String Reporting_HF = resp_arry[3];
                String LHW_name = resp_arry[4];
                String lhw_ids = resp_arry[5];


                String[] lst_Distric = Distric.split("%");
                String[] lst_tehsil = tehsil.split("%");
                String[] lst_Reporting_LHS = Reporting_LHS.split("%");
                String[] lst_DReporting_HF = Reporting_HF.split("%");
                String[] lst_LHW_name = LHW_name.split("%");
                String[] lst_lhw_ids = lhw_ids.split("%");

                LmManger = new LocalDataManager(mContext);

                for (int i = 0; i < lst_Distric.length; i++) {
                    if (i == 0) {
                        Delelte_date();
                    }

                    insert_data(lst_Distric[i], lst_tehsil[i], lst_Reporting_LHS[i],

                            lst_DReporting_HF[i], lst_LHW_name[i], lst_lhw_ids[i]
                    );

                }


                prefs.setUsername(mUsername);
                prefs.setPassword(mPassword);
                prefs.setName(mUsername);
                prefs.setUserId(1);


                //   prefs.setLHWIDS(resp);

                // redirect to another activity from here..
                Intent intent = new Intent(mContext, HomeActivity.class);
                mContext.startActivity(intent);
                ((Activity) mContext).finish();
            }

        } catch (IOException e) {
            //if connection was available via connecting but
            //we can't get data from server..
            if (mUserMsg == null)
                mUserMsg = "Please check connection";
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
            mUserMsg = e.getMessage();
        } finally {
            if (mUserMsg != null)
                Toast.makeText(mContext, mUserMsg, Toast.LENGTH_SHORT).show();
        }
        // hide the progressDialog
        mDialog.hide();

        super.onPostExecute(o);
    }


    public void insert_data(String district, String Tehsil, String Reporting_LHS, String Reporting_HF, String LHW_Name, String LHW_Ids) {


        String query = "insert into  TableLoginData (District,Tehsil,Reporting_LHS,Reporting_HF,LHW_Name,LHW_Ids) values('" +

                district + "','" + Tehsil + "','" + Reporting_LHS + "','" + Reporting_HF + "','" + LHW_Name + "','" + LHW_Ids + "')";

        query = String.format(query);


        LocalDataManager.database.execSQL(query);

    }

    public void Delelte_date() {


        String query = " Delete from   TableLoginData ";

        query = String.format(query);

        LocalDataManager validationactivity = new LocalDataManager(mContext);

        LocalDataManager.database.execSQL(query);

    }


}
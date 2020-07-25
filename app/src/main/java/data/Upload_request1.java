package data;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.widget.Toast;

import com.irfansyed.umeedenau.validation.Global;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;


import utils.MyPreferences;
import utils.PostRequestData;
import utils.QuestionModel;


import static data.LocalDataManager.database;

/**
 * Created by Umeed-e-Nau on 12/28/2016.
 */
public class Upload_request1 extends AsyncTask {

    Context mContext;
    public static ProgressDialog dialog;
    HashMap<String, String> param;
    String[] interviewLogData;
    String mUserMsg;

    public Upload_request1(Context context) {

        //stop interview
        // if(InterviewUploadingStatus.status<2)
        //   this.cancel(false);

        mContext = context;
        dialog = new ProgressDialog(context);
        param = new HashMap<>();

    }

    @Override
    protected void onPreExecute() {

        dialog.setMessage("Uploading interview Please wait ....");
        dialog.setCancelable(false);
        dialog.show();


        HashMap<String, List<String>> map = QuestionModel.quest.get("a");


        //region Query

        String query = "select Q11, Q12,Q1_1, Q1, Q2, Q3, Q4, datee, timee, userid, type_interview, Interview_status " +
                " from tabl1 where id = '%s' order by id  desc LIMIT 1";

        query = String.format(query, Global.global_id);

        LocalDataManager Lm = new LocalDataManager(mContext);
        Cursor c = Lm.database.rawQuery(query, null);


        if (c != null) {
            if (c.moveToFirst()) {

                param.put("Q11", c.getString(c.getColumnIndex("Q11")));
                param.put("Q12", c.getString(c.getColumnIndex("Q12")));
                param.put("Q1_1", c.getString(c.getColumnIndex("Q1_1")));
                param.put("Q1", c.getString(c.getColumnIndex("Q1")));
                param.put("Q2", c.getString(c.getColumnIndex("Q2")));
                param.put("Q3", c.getString(c.getColumnIndex("Q3")));
                param.put("Q4", c.getString(c.getColumnIndex("Q4")));
                param.put("datee", c.getString(c.getColumnIndex("datee")));
                param.put("timee", c.getString(c.getColumnIndex("timee")));
                param.put("userid", c.getString(c.getColumnIndex("userid")));
                param.put("type_interview", c.getString(c.getColumnIndex("type_interview")));
                param.put("Interview_status", c.getString(c.getColumnIndex("Interview_status")));

                Global.type_interview = c.getString(c.getColumnIndex("type_interview"));

            }

        }
        //endregion


        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(Object[] objects) {

        String urlString = new MyPreferences(mContext).getReq1();

        URL url;
        HttpURLConnection connection;

        try {
            url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            // connection.setRequestMethod("GET");
            connection.setConnectTimeout(1000);

            OutputStream os = connection.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

            bw.write(PostRequestData.getData(param));
            bw.flush();


            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String st = "", line;
                while ((line = br.readLine()) != null) {
                    st = st + line;
                }
                return st;
            } else {
                mUserMsg = "Server Couldn't process the request";
            }
        } catch (MalformedURLException e) {
            Toast.makeText(mContext, e.toString(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
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
            dialog.dismiss();

            if (mUserMsg != null)
                throw new IOException();


            //int houseId = Integer.parseInt(((String) o).replace("\"",""));

            String result = (((String) o).replace("\"", ""));


            ///  Toast.makeText(mContext, "Interivew Has ben Uploaded", Toast.LENGTH_SHORT).show();
            // new LocalDataManager(mContext).uploadInterview();

            Global.server_id = result;

            if (Global.type_interview.equals("Validation")) {

                new Upload_request2(mContext).execute();
            } else {
                new Upload_request3(mContext).execute();
            }

            // thread.start();


        } catch (IOException e) {
            //if connection was available via connecting but
            //we can't get data from server..
            if (mUserMsg == null)
                mUserMsg = "Please check connection";
            dialog.dismiss();
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
            mUserMsg = e.getMessage();
            dialog.dismiss();
        } catch (Exception e) {
            Toast.makeText(mContext, "Uploading failed at request 1", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
            return;
        } finally {
            if (mUserMsg != null)
                Toast.makeText(mContext, mUserMsg, Toast.LENGTH_SHORT).show();
        }


        super.onPostExecute(o);
    }


    // wait for Toast then kill app
    Thread thread = new Thread() {
        @Override
        public void run() {
            try {
                Thread.sleep(800); // As I am using LENGTH_LONG in Toast
                // Your_Activity.this.finish();


                int pid = android.os.Process.myPid();
                android.os.Process.killProcess(pid);
                // getActivity().finish();


                System.exit(0);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
}



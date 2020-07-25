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
import java.util.Map;

import utils.MyPreferences;
import utils.PostRequestData;

/**
 * Created by Umeed-e-Nau on 12/28/2016.
 */
public class UploadF4SectionB extends AsyncTask {

    Context mContext;
    public static ProgressDialog dialog;
    HashMap<String, String> param;
    String[] interviewLogData;
    String mUserMsg;

    public UploadF4SectionB(Context context) {

        mContext = context;
        dialog = new ProgressDialog(context);
        param = new HashMap<>();
    }


    @Override
    protected void onPreExecute() {

        dialog.setMessage("Uploading interview Please wait ....");
        dialog.setCancelable(false);
        dialog.show();

        int ccc = 0;

        String Fk_id = "";

        //region Query

        String query = "select * from TableF4SectionB f4 join TableF3SectionB F3 on f4.FK_id=f3.id where F3.Fk_id='" + Global.global_id + "'";

        LocalDataManager Lm = new LocalDataManager(mContext);
        Cursor c = Lm.database.rawQuery(query, null);


        if (c != null && c.getCount() != 0) {
            if (c.moveToFirst()) {

                Global.loop_count = c.getCount();
                do {

                    if (ccc == Global.loop_Increment) {

                        param.put("lhwf4b0", c.getString(c.getColumnIndex("lhwf4b0")));
                        param.put("lhwf4b1", c.getString(c.getColumnIndex("lhwf4b1")));
                        param.put("lhwf4b2", c.getString(c.getColumnIndex("lhwf4b2")));
                        param.put("lhwf4b3", c.getString(c.getColumnIndex("lhwf4b3")));
                        param.put("lhwf4b5", c.getString(c.getColumnIndex("lhwf4b5")));
                        param.put("lhwf4b4", "NA");
                        param.put("lhwf4b6", c.getString(c.getColumnIndex("lhwf4b6")));

                        param.put("GPSLat", c.getString(c.getColumnIndex("GPSLat")));
                        param.put("GPSLong", c.getString(c.getColumnIndex("GPSLong")));
                        param.put("InterviewTime", c.getString(c.getColumnIndex("InterviewTime")));

                        param.put("LhwSectionPKId", Global.server_id);

                    }

                    ccc++;

                } while (c.moveToNext());
            }

        } else {
            param.put("lhwf4b0", "00");
            param.put("lhwf4b1", "00");
            param.put("lhwf4b2", "00");
            param.put("lhwf4b3", "00");
            param.put("lhwf4b5", "00");
            param.put("lhwf4b4", "NA");
            param.put("lhwf4b6", "00");


            param.put("GPSLat", "00");
            param.put("GPSLong", "00");
            param.put("InterviewTime", "00");

            param.put("LhwSectionPKId", Global.server_id);


        }


        for (Map.Entry<String, String> entry : param.entrySet()) {
            if (entry.getValue() == null || entry.getValue().length() == 0) {
                param.put(entry.getKey(), "00");
            }
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());

        }


        super.onPreExecute();
    }


    @Override
    protected Object doInBackground(Object[] objects) {

        String urlString = new MyPreferences(mContext).getReq3();

        urlString = urlString + "InsertF4SectionB";

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
            //   dialog.dismiss();

            if (mUserMsg != null)
                throw new IOException();


            //int houseId = Integer.parseInt(((String) o).replace("\"",""));

            //    String result = (((String) o).replace("\"", ""));


            ///  Toast.makeText(mContext, "Interivew Has ben Uploaded", Toast.LENGTH_SHORT).show();
            // new LocalDataManager(mContext).uploadInterview();

            //      Global.server_id=result;


            Global.loop_Increment++;
            if (Global.loop_count != Global.loop_Increment && Global.loop_count != 0) {


                new UploadF4SectionB(mContext).execute();

            } else {
                Global.loop_Increment = 0;
                Global.loop_count = 0;


                new UploadF5SectionB(mContext).execute();

            }


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
        }


      /*  catch (Exception e) {
            Toast.makeText(mContext, "Uploading failed at request 2", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
            return;
        }

        */ finally {
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



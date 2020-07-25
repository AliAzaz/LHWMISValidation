package data;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.irfansyed.umeedenau.validation.Global;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import utils.MyPreferences;
import utils.PostRequestData;

/**
 * Created by Umeed-e-Nau on 12/28/2016.
 */
public class UploadF1F2SectionC extends AsyncTask {

    Context mContext;
    public static ProgressDialog dialog;
    HashMap<String, String> param;
    String[] interviewLogData;
    String mUserMsg;

    public UploadF1F2SectionC(Context context) {


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

        String query = "select * from TableF1SectionC as F1 join TableHHSection as H on F1.Fk_id=H.id  where F1.LhwSectionPKId='" + Global.global_id + "'";


        LocalDataManager Lm = new LocalDataManager(mContext);
        Cursor c = LocalDataManager.database.rawQuery(query, null);


        int a = c.getCount();
        if (c != null && c.getCount() != 0) {
            if (c.moveToFirst()) {


                Global.loop_count = c.getCount();
                do {


                    if (ccc == Global.loop_Increment) {

                        param.put("lhwf1c1", c.getString(c.getColumnIndex("lhwf1c1")));
                        param.put("lhwf1c2", c.getString(c.getColumnIndex("lhwf1c2")));
                        param.put("lhwf1c3", c.getString(c.getColumnIndex("lhwf1c3")) + "-" + c.getString(c.getColumnIndex("lhwf1c3Aa")));
                        param.put("lhwf1c4", c.getString(c.getColumnIndex("lhwf1c4")) + "-" + c.getString(c.getColumnIndex("lhwf1c4A")));
                        param.put("lhwf1c5", c.getString(c.getColumnIndex("lhwf1c5")));
                        param.put("lhwf1c6", c.getString(c.getColumnIndex("lhwf1c6")));
                        param.put("lhwf1c7", c.getString(c.getColumnIndex("lhwf1c7")) + "-" + c.getString(c.getColumnIndex("lhwf1c7A")));
                        param.put("lhwf1c8", c.getString(c.getColumnIndex("lhwf1c8")) + "-" + c.getString(c.getColumnIndex("lhwf1c9")) + "-" + c.getString(c.getColumnIndex("lhwf1c10")));

                        param.put("lhwf1c3Ab", c.getString(c.getColumnIndex("lhwf1c3Ab")));
                        param.put("lhwc11a", c.getString(c.getColumnIndex("lhwc11a")));
                        param.put("lhwc11b", c.getString(c.getColumnIndex("lhwc11b")));

                        param.put("lhwf1cphoto", c.getString(c.getColumnIndex("lhwf1cphoto")));
                        param.put("lhwf1b1", c.getString(c.getColumnIndex("lhwf1b1")));
                        param.put("lhwf1b2", c.getString(c.getColumnIndex("lhwf1b2")));
                        param.put("lhwf1b3", c.getString(c.getColumnIndex("lhwf1b3")));

                        Fk_id = c.getString(0);
                    }
                    ccc++;


                } while (c.moveToNext());
            }

        } else {
            param.put("lhwf1c1", "00");
            param.put("lhwf1c2", "00");
            param.put("lhwf1c3", "00");
            param.put("lhwf1c4", "00");
            param.put("lhwf1c5", "00");
            param.put("lhwf1c6", "00");
            param.put("lhwf1c7", "00");
            param.put("lhwf1c8", "00");
            param.put("lhwf1b1", "00");
            param.put("lhwf1b2", "00");
            param.put("lhwf1b3", "00");


        }


        String query2 = "select * from TableF2SectionC where LhwSectionPKId='" + Global.global_id + "' and Fk_id='" + Fk_id + "'";


        LocalDataManager Lm2 = new LocalDataManager(mContext);
        Cursor c2 = LocalDataManager.database.rawQuery(query2, null);


        if (c2 != null && c2.getCount() != 0) {
            if (c2.moveToFirst()) {

                param.put("lhwf2c2", c2.getString(c2.getColumnIndex("lhwf2c2")) + "-" + c2.getString(c2.getColumnIndex("lhwf2c2a"))
                        + "-" + c2.getString(c2.getColumnIndex("lhwf2c2b"))
                );

                param.put("lhwf2c3", c2.getString(c2.getColumnIndex("lhwf2c3")));
                param.put("lhwf2c4", c2.getString(c2.getColumnIndex("lhwf2c4")) + "-" + c2.getString(c2.getColumnIndex("lhwf2c4b")));

                param.put("lhwf2c5", c2.getString(c2.getColumnIndex("lhwf2c5")));
                param.put("lhwf2c6", c2.getString(c2.getColumnIndex("lhwf2c6")) + "-" + c2.getString(c2.getColumnIndex("lhwf2c6a")));
                param.put("lhwf2c7", c2.getString(c2.getColumnIndex("lhwf2c7")));
                param.put("lhwf2c8", c2.getString(c2.getColumnIndex("lhwf2c8")));

                param.put("lhwf2c9", c2.getString(c2.getColumnIndex("lhwf2c9")));
                param.put("lhwf2c10", c2.getString(c2.getColumnIndex("lhwf2c10")));


                param.put("lhwf2c11", c2.getString(c2.getColumnIndex("lhwf2c11")) + "-" +
                        c2.getString(c2.getColumnIndex("lhwf2c1")) + "-" +
                        c2.getString(c2.getColumnIndex("lhwf2c4a")) + "-" +
                        c2.getString(c2.getColumnIndex("lhwf2c8a"))
                );

                param.put("GPSLat", c2.getString(c2.getColumnIndex("GPSLat")));
                param.put("GPSLong", c2.getString(c2.getColumnIndex("GPSLong")));
                param.put("InterviewTime", c2.getString(c2.getColumnIndex("InterviewTime")));

                param.put("LhwSectionPKId", Global.server_id);
            }

        } else {

            param.put("lhwf2c2", "00");
            param.put("lhwf2c3", "00");
            param.put("lhwf2c4", "00");
            param.put("lhwf2c5", "00");
            param.put("lhwf2c6", "00");
            param.put("lhwf2c7", "00");
            param.put("lhwf2c8", "00");
            param.put("lhwf2c9", "00");
            param.put("lhwf2c10", "00");
            param.put("lhwf2c11", "00");

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

        String urlString = new MyPreferences(mContext).getReq2();

        URL url;
        HttpURLConnection connection;

        try {
            url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            // connection.setRequestMethod("GET");

            connection.setConnectTimeout(1000);

            OutputStream os = connection.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));

            bw.write(PostRequestData.getData(param));
            Log.d("F1F2SecC", "doInBackground: " + PostRequestData.getData(param));
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
                mUserMsg = "CODE: " + responseCode + "\r\nServer Couldn't process the request";
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

            if (mUserMsg != null) {
                dialog.dismiss();
                throw new IOException();

            }
            //int houseId = Integer.parseInt(((String) o).replace("\"",""));

            //    String result = (((String) o).replace("\"", ""));


            ///  Toast.makeText(mContext, "Interivew Has ben Uploaded", Toast.LENGTH_SHORT).show();
            // new LocalDataManager(mContext).uploadInterview();

            //      Global.server_id=result;


            Global.loop_Increment++;

            if (Global.loop_count != Global.loop_Increment && Global.loop_count != 0) {


                new UploadF1F2SectionC(mContext).execute();
            } else {
                Global.loop_Increment = 0;
                Global.loop_count = 0;


                new UploadF1F2SectionD(mContext).execute();

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
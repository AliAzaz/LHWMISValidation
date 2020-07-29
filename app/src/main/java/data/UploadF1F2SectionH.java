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
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import utils.MyPreferences;
import utils.PostRequestData;

/**
 * Created by Umeed-e-Nau on 12/28/2016.
 */
public class UploadF1F2SectionH extends AsyncTask {

    Context mContext;
    public static ProgressDialog dialog;
    HashMap<String, String> param;
    String[] interviewLogData;
    String mUserMsg;

    public UploadF1F2SectionH(Context context) {


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
        String query = "select * from TableF1sectionH as F1 join TableHHSection as H on F1.Fk_id=H.id  where F1.LhwSectionPKId='" + Global.global_id + "'";

        LocalDataManager Lm = new LocalDataManager(mContext);
        Cursor c = LocalDataManager.database.rawQuery(query, null);


        if (c != null && c.getCount() != 0) {
            if (c.moveToFirst()) {

                Global.loop_count = c.getCount();
                do {
                    if (ccc == Global.loop_Increment) {

                        param.put("lhwf1h1", c.getString(c.getColumnIndex("lhwf1h1")));
                        param.put("lhwf1h2", c.getString(c.getColumnIndex("lhwf1h2")));
                        param.put("lhwf1h3", c.getString(c.getColumnIndex("lhwf1h3")));
                        param.put("lhwf1h4", c.getString(c.getColumnIndex("lhwf1h4")));
                        param.put("lhwf1h5", c.getString(c.getColumnIndex("lhwf1h5")) + "-" +
                                c.getString(c.getColumnIndex("lhwf1h6")) + "-" +
                                c.getString(c.getColumnIndex("lhwf1h7")) + "-" +
                                c.getString(c.getColumnIndex("lhwf1h8")) + "-" +
                                c.getString(c.getColumnIndex("lhwf1h9")));

                        param.put("lhwf1h10a", c.getString(c.getColumnIndex("lhwf1h10a")));
                        param.put("lhwf1h10b", c.getString(c.getColumnIndex("lhwf1h10b")));

                        param.put("lhwf1hphoto", c.getString(c.getColumnIndex("lhwf1hphoto")));

                        param.put("lhwf1b1", c.getString(c.getColumnIndex("lhwf1b1")));
                        param.put("lhwf1b2", c.getString(c.getColumnIndex("lhwf1b2")));
                        param.put("lhwf1b3", c.getString(c.getColumnIndex("lhwf1b3")));

                        Fk_id = c.getString(0);
                    }
                    ccc++;

                } while (c.moveToNext());
            }

        } else {
            param.put("lhwf1h1", "00");
            param.put("lhwf1h2", "00");
            param.put("lhwf1h3", "00");
            param.put("lhwf1h4", "00");
            param.put("lhwf1h5", "00");
            param.put("lhwf1h10a", "00");
            param.put("lhwf1h10b", "00");
            param.put("lhwf1hphoto", "00");

            param.put("lhwf1b1", "00");
            param.put("lhwf1b2", "00");
            param.put("lhwf1b3", "00");

        }


        String query2 = "select * from TableF2sectionH where LhwSectionPKId='" + Global.global_id + "' and Fk_id='" + Fk_id + "'";

        LocalDataManager Lm2 = new LocalDataManager(mContext);
        Cursor c2 = LocalDataManager.database.rawQuery(query2, null);

        int a = c2.getCount();
        if (c2 != null && c2.getCount() != 0) {
            if (c2.moveToFirst()) {

                param.put("lhwf2h2", c2.getString(c2.getColumnIndex("lhwf2h2")));
                param.put("lhwf2h2a", c2.getString(c2.getColumnIndex("lhwf2h2a")));

                param.put("lhwf2h3", c2.getString(c2.getColumnIndex("lhwf2h3")));
                param.put("lhwf2h4", c2.getString(c2.getColumnIndex("lhwf2h4")));
                param.put("lhwf2h5", c2.getString(c2.getColumnIndex("lhwf2h5")));
                param.put("lhwf2h6", c2.getString(c2.getColumnIndex("lhwf2h6")));
                param.put("lhwf2h7", c2.getString(c2.getColumnIndex("lhwf2h7")));
                param.put("lhwf2h796x", c2.getString(c2.getColumnIndex("lhwf2h796x")));

                param.put("lhwf2h8", c2.getString(c2.getColumnIndex("lhwf2h8")));
                param.put("lhwf2h9", c2.getString(c2.getColumnIndex("lhwf2h9")));
                param.put("lhwf2h10", c2.getString(c2.getColumnIndex("lhwf2h10")));
                param.put("lhwf2h11", c2.getString(c2.getColumnIndex("lhwf2h11")) + "-" + c2.getString(c2.getColumnIndex("lhwf2h1")));


                param.put("GPSLat", c2.getString(c2.getColumnIndex("GPSLat")));
                param.put("GPSLong", c2.getString(c2.getColumnIndex("GPSLong")));
                param.put("InterviewTime", c2.getString(c2.getColumnIndex("InterviewTime")));

                param.put("LhwSectionPKId", Global.server_id);

            }

        } else {

            param.put("lhwf2h2", "00");
            param.put("lhwf2h2a", "00");
            param.put("lhwf2h3", "00");
            param.put("lhwf2h4", "00");
            param.put("lhwf2h5", "00");
            param.put("lhwf2h6", "00");
            param.put("lhwf2h7", "00");
            param.put("lhwf2h796x", "00");
            param.put("lhwf2h8", "00");
            param.put("lhwf2h9", "00");
            param.put("lhwf2h10", "00");
            param.put("lhwf2h11", "00");

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

        urlString = urlString + "InsertF1F2sectionH";

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
            // dialog.dismiss();

            if (mUserMsg != null)
                throw new IOException();


            //int houseId = Integer.parseInt(((String) o).replace("\"",""));

            //    String result = (((String) o).replace("\"", ""));


            ///  Toast.makeText(mContext, "Interivew Has ben Uploaded", Toast.LENGTH_SHORT).show();
            // new LocalDataManager(mContext).uploadInterview();

            //      Global.server_id=result;


            Global.loop_Increment++;
            if (Global.loop_count != Global.loop_Increment && Global.loop_count != 0) {


                new UploadF1F2SectionH(mContext).execute();

            } else {
                Global.loop_Increment = 0;
                Global.loop_count = 0;

                new UploadF3SectionB(mContext).execute();


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



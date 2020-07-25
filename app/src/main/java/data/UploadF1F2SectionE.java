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
public class UploadF1F2SectionE extends AsyncTask {

    Context mContext;
    public static ProgressDialog dialog;
    HashMap<String, String> param;
    String[] interviewLogData;
    String mUserMsg;

    public UploadF1F2SectionE(Context context) {


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

        String query = "select * from TableF1SectionE as F1 join TableHHSection as H on F1.Fk_id=H.id  where F1.LhwSectionPKId='" + Global.global_id + "'";


        LocalDataManager Lm = new LocalDataManager(mContext);
        Cursor c = LocalDataManager.database.rawQuery(query, null);


        if (c != null && c.getCount() != 0) {
            if (c.moveToFirst()) {


                Global.loop_count = c.getCount();
                do {

                    if (ccc == Global.loop_Increment) {

                        param.put("lhwf1e1", c.getString(c.getColumnIndex("lhwf1e1")));

                        param.put("lhwf1e2", c.getString(c.getColumnIndex("lhwf1e2")));
                        param.put("lhwf1e3", c.getString(c.getColumnIndex("lhwf1e3")));
                        param.put("lhwf1e4", c.getString(c.getColumnIndex("lhwf1e4"))
                                + "-" + c.getString(c.getColumnIndex("lhwf1e4Aa"))
                                + "-" + c.getString(c.getColumnIndex("lhwf1e4Ab"))
                        );


                        param.put("lhwf1e5", c.getString(c.getColumnIndex("lhwf1e5")));
                        param.put("lhwf1e5b", c.getString(c.getColumnIndex("lhwf1e5b")));

                        param.put("lhwf1e6", c.getString(c.getColumnIndex("lhwf1e6")));
                        param.put("lhwf1e6b", c.getString(c.getColumnIndex("lhwf1e6b")));


                        param.put("lhwf1e7", c.getString(c.getColumnIndex("lhwf1e7")));
                        param.put("lhwf1e7b", c.getString(c.getColumnIndex("lhwf1e7b")));


                        param.put("lhwf1e8", c.getString(c.getColumnIndex("lhwf1e8")) + "-" +
                                c.getString(c.getColumnIndex("lhwf1e5b")) + "-" +
                                c.getString(c.getColumnIndex("lhwf1e6b")) + "-" +
                                c.getString(c.getColumnIndex("lhwf1e7b"))

                        );

                        param.put("lhwf1e9a", c.getString(c.getColumnIndex("lhwf1e9a")));
                        param.put("lhwf1e9b", c.getString(c.getColumnIndex("lhwf1e9b")));


                        param.put("lhwf1ephoto", c.getString(c.getColumnIndex("lhwf1ephoto")));

                        param.put("lhwf1b1", c.getString(c.getColumnIndex("lhwf1b1")));
                        param.put("lhwf1b2", c.getString(c.getColumnIndex("lhwf1b2")));
                        param.put("lhwf1b3", c.getString(c.getColumnIndex("lhwf1b3")));


                        Fk_id = c.getString(0);
                    }
                    ccc++;

                } while (c.moveToNext());
            }

        } else {
            param.put("lhwf1e1", "00");
            param.put("lhwf1e2", "00");
            param.put("lhwf1e3", "00");
            param.put("lhwf1e4", "00");
            param.put("lhwf1e5", "00");
            param.put("lhwf1e6", "00");
            param.put("lhwf1e7", "00");
            param.put("lhwf1e8", "00");
            param.put("lhwf1b1", "00");
            param.put("lhwf1b2", "00");
            param.put("lhwf1b3", "00");


        }


        String query2 = "select * from TableF2SectionE where LhwSectionPKId='" + Global.global_id + "' and Fk_id='" + Fk_id + "'";


        LocalDataManager Lm2 = new LocalDataManager(mContext);
        Cursor c2 = LocalDataManager.database.rawQuery(query2, null);


        int a = c2.getCount();
        if (c2 != null && c2.getCount() != 0) {
            if (c2.moveToFirst()) {

                param.put("lhwf2e2",
                        c2.getString(c2.getColumnIndex("lhwf2e2"))

                                + "-" + c2.getString(c2.getColumnIndex("lhwf2e1a"))

                );
                param.put("lhwf2e3", c2.getString(c2.getColumnIndex("lhwf2e3")));
                param.put("lhwf2e4", c2.getString(c2.getColumnIndex("lhwf2e4")));
                param.put("lhwf2e5", c2.getString(c2.getColumnIndex("lhwf2e5")));
                param.put("lhwf2e5a", c2.getString(c2.getColumnIndex("lhwf2e5a")));
                param.put("lhwf2e6", c2.getString(c2.getColumnIndex("lhwf2e6")));
                param.put("lhwf2e7", c2.getString(c2.getColumnIndex("lhwf2e7")));
                param.put("lhwf2e8", c2.getString(c2.getColumnIndex("lhwf2e8")));
                param.put("lhwf2e9", c2.getString(c2.getColumnIndex("lhwf2e9")));
                param.put("lhwf2e10", c2.getString(c2.getColumnIndex("lhwf2e10")));
                param.put("lhwf2e11", c2.getString(c2.getColumnIndex("lhwf2e11")));
                param.put("lhwf2e12",
                        c2.getString(c2.getColumnIndex("lhwf2e12")) + "-" +

                                c2.getString(c2.getColumnIndex("lhwf2e12a"))


                );

                param.put("lhwf2e13",
                        c2.getString(c2.getColumnIndex("lhwf2e13")) + "-" +
                                c2.getString(c2.getColumnIndex("lhwf2e1")) + "-" +
                                c2.getString(c2.getColumnIndex("lhwf2e6a")) + "-" +
                                c2.getString(c2.getColumnIndex("lhwf2e7a"))


                );


                param.put("GPSLat", c2.getString(c2.getColumnIndex("GPSLat")));
                param.put("GPSLong", c2.getString(c2.getColumnIndex("GPSLong")));
                param.put("InterviewTime", c2.getString(c2.getColumnIndex("InterviewTime")));

                param.put("LhwSectionPKId", Global.server_id);


            }

        } else {

            param.put("lhwf2e2", "00");
            param.put("lhwf2e3", "00");
            param.put("lhwf2e4", "00");
            param.put("lhwf2e5", "00");
            param.put("lhwf2e5a", "00");
            param.put("lhwf2e6", "00");
            param.put("lhwf2e7", "00");
            param.put("lhwf2e8", "00");
            param.put("lhwf2e9", "00");
            param.put("lhwf2e10", "00");
            param.put("lhwf2e11", "00");
            param.put("lhwf2e12", "00");
            param.put("lhwf2e13", "00");


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

        urlString = urlString + "InsertF1F2SectionE";

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


                new UploadF1F2SectionE(mContext).execute();

            } else {
                Global.loop_Increment = 0;
                Global.loop_count = 0;
                new UploadF1F2SectionF(mContext).execute();

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



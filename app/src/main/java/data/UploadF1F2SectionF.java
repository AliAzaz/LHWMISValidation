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
public class UploadF1F2SectionF extends AsyncTask {

    Context mContext;
    public static ProgressDialog dialog;
    HashMap<String, String> param;
    String[] interviewLogData;
    String mUserMsg;

    public UploadF1F2SectionF(Context context) {


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

        String query = "select * from TableF1sectionF as F1 join TableHHSection as H on F1.Fk_id=H.id  where F1.LhwSectionPKId='" + Global.global_id + "'";


        LocalDataManager Lm = new LocalDataManager(mContext);
        Cursor c = LocalDataManager.database.rawQuery(query, null);


        if (c != null && c.getCount() != 0) {
            if (c.moveToFirst()) {


                Global.loop_count = c.getCount();
                do {


                    if (ccc == Global.loop_Increment) {

                        param.put("lhwf1f1", c.getString(c.getColumnIndex("lhwf1f1")));
                        param.put("lhwf1f2", c.getString(c.getColumnIndex("lhwf1f2")));
                        param.put("lhwf1f3", c.getString(c.getColumnIndex("lhwf1f3")));
                        param.put("lhwf1f4", c.getString(c.getColumnIndex("lhwf1f4"))
                                + "-" + c.getString(c.getColumnIndex("lhwf1f4Aa"))
                                + "-" + c.getString(c.getColumnIndex("lhwf1f4Ab"))
                        );

                        param.put("lhwf1f5", c.getString(c.getColumnIndex("lhwf1f5")));
                        param.put("lhwf1f6", c.getString(c.getColumnIndex("lhwf1f6")));
                        param.put("lhwf1f7", c.getString(c.getColumnIndex("lhwf1f7")));
                        param.put("lhwf1f8", c.getString(c.getColumnIndex("lhwf1f8")));
                        param.put("lhwf1f9", c.getString(c.getColumnIndex("lhwf1f9")));
                        param.put("lhwf1f10", c.getString(c.getColumnIndex("lhwf1f10")));
                        param.put("lhwf1f11a", c.getString(c.getColumnIndex("lhwf1f11a")));
                        param.put("lhwf1f11b", c.getString(c.getColumnIndex("lhwf1f11b")));

                        param.put("lhwf1fphoto", c.getString(c.getColumnIndex("lhwf1fphoto")));


                        param.put("lhwf1b1", c.getString(c.getColumnIndex("lhwf1b1")));
                        param.put("lhwf1b2", c.getString(c.getColumnIndex("lhwf1b2")));
                        param.put("lhwf1b3", c.getString(c.getColumnIndex("lhwf1b3")));


                        Fk_id = c.getString(0);
                    }
                    ccc++;


                } while (c.moveToNext());
            }

        } else {
            param.put("lhwf1f1", "00");
            param.put("lhwf1f2", "00");
            param.put("lhwf1f3", "00");
            param.put("lhwf1f4", "00");
            param.put("lhwf1f5", "00");
            param.put("lhwf1f6", "00");
            param.put("lhwf1f7", "00");
            param.put("lhwf1f8", "00");
            param.put("lhwf1f9", "00");
            param.put("lhwf1f10", "00");

            param.put("lhwf1b1", "00");
            param.put("lhwf1b2", "00");
            param.put("lhwf1b3", "00");


        }


        String query2 = "select * from TableF2sectionF where LhwSectionPKId='" + Global.global_id + "' and Fk_id='" + Fk_id + "'";


        LocalDataManager Lm2 = new LocalDataManager(mContext);
        Cursor c2 = LocalDataManager.database.rawQuery(query2, null);


        int a = c2.getCount();
        if (c2 != null && c2.getCount() != 0) {
            if (c2.moveToFirst()) {


                param.put("lhwf2f3",
                        c2.getString(c2.getColumnIndex("lhwf2f3"))
                                + "-" + c2.getString(c2.getColumnIndex("lhwf2f3a"))


                );
                param.put("lhwf2f4", c2.getString(c2.getColumnIndex("lhwf2f4")));
                param.put("lhwf2f5", c2.getString(c2.getColumnIndex("lhwf2f5")));
                param.put("lhwf2f6", c2.getString(c2.getColumnIndex("lhwf2f6")));
                param.put("lhwf2f7", c2.getString(c2.getColumnIndex("lhwf2f7")));
                param.put("lhwf2f8",
                        c2.getString(c2.getColumnIndex("lhwf2f8"))
                                + "-" +
                                c2.getString(c2.getColumnIndex("lhwf2f8a"))

                );
                param.put("lhwf2f9", c2.getString(c2.getColumnIndex("lhwf2f9")));
                param.put("lhwf2f10", c2.getString(c2.getColumnIndex("lhwf2f10")));
                param.put("lhwf2f11", c2.getString(c2.getColumnIndex("lhwf2f11")));
                param.put("lhwf2f12", c2.getString(c2.getColumnIndex("lhwf2f12")));
                param.put("lhwf2f13",

                        c2.getString(c2.getColumnIndex("lhwf2f13")) + "-" +
                                c2.getString(c2.getColumnIndex("lhwf2f1")) + "-" +
                                c2.getString(c2.getColumnIndex("lhwf2f5a"))


                );


                param.put("GPSLat", c2.getString(c2.getColumnIndex("GPSLat")));
                param.put("GPSLong", c2.getString(c2.getColumnIndex("GPSLong")));
                param.put("InterviewTime", c2.getString(c2.getColumnIndex("InterviewTime")));

                param.put("LhwSectionPKId", Global.server_id);


            }

        } else {


            param.put("lhwf2f3", "00");
            param.put("lhwf2f4", "00");
            param.put("lhwf2f5", "00");
            param.put("lhwf2f6", "00");
            param.put("lhwf2f7", "00");
            param.put("lhwf2f8", "00");
            param.put("lhwf2f9", "00");
            param.put("lhwf2f10", "00");
            param.put("lhwf2f11", "00");
            param.put("lhwf2f12", "00");
            param.put("lhwf2f13", "00");


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

        urlString = urlString + "InsertF1F2sectionF";

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


                new UploadF1F2SectionF(mContext).execute();

            } else {
                Global.loop_Increment = 0;
                Global.loop_count = 0;
                new UploadF1F2SectionG(mContext).execute();

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



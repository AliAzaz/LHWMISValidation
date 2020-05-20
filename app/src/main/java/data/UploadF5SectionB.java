package data;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;
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
public class UploadF5SectionB extends AsyncTask {

    Context mContext;
    public static ProgressDialog dialog;
    HashMap<String, String> param;
    String[] interviewLogData;
    String mUserMsg;

    public UploadF5SectionB(Context context) {


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

        String query = "select * from TableF5SectionB where Fk_id='" + Global.global_id + "'";


        LocalDataManager Lm = new LocalDataManager(mContext);
        Cursor c = LocalDataManager.database.rawQuery(query, null);
        Log.d("CURSOR", "onPreExecute: " + c.getCount());

        if (c != null && c.getCount()!=0) {
            if (c.moveToFirst()) {


                Global.loop_count = c.getCount();
                do {


                    if (ccc == Global.loop_Increment) {

                        param.put("lhwf5b1", c.getString(c.getColumnIndex("lhwf5b1")));
                        param.put("lhwf5b2", c.getString(c.getColumnIndex("lhwf5b2")));
                        param.put("lhwf5b3", c.getString(c.getColumnIndex("lhwf5b3")));
                        param.put("lhwf5b4", c.getString(c.getColumnIndex("lhwf5b4")));
                        param.put("lhwf5b5a", c.getString(c.getColumnIndex("lhwf5b5a")));
                        param.put("lhwf5b5b", c.getString(c.getColumnIndex("lhwf5b5b")));
                        param.put("lhwf5b5c", c.getString(c.getColumnIndex("lhwf5b5c")));
                        param.put("lhwf5b6", c.getString(c.getColumnIndex("lhwf5b6")));
                        param.put("lhwf5b7_1", c.getString(c.getColumnIndex("lhwf5b7_1")));
                        param.put("lhwf5b7_2", c.getString(c.getColumnIndex("lhwf5b7_2")));
                        param.put("lhwf5b7_3", c.getString(c.getColumnIndex("lhwf5b7_3")));
                        param.put("lhwf5b7_4", c.getString(c.getColumnIndex("lhwf5b7_4")));
                        param.put("lhwf5b7_5", c.getString(c.getColumnIndex("lhwf5b7_5")));
                        param.put("lhwf5b7_6", c.getString(c.getColumnIndex("lhwf5b7_6")));
                        param.put("lhwf5b7_7", c.getString(c.getColumnIndex("lhwf5b7_7")));
                        param.put("lhwf5b7_8", c.getString(c.getColumnIndex("lhwf5b7_8")));
                        param.put("lhwf5b796", c.getString(c.getColumnIndex("lhwf5b796")));
                        param.put("lhwf5b796x", c.getString(c.getColumnIndex("lhwf5b796x")));



                        param.put("LhwSectionPKId", Global.server_id);


                    }
                    ccc++;


                } while (c.moveToNext());
            }

        }
        else {
            param.put("lhwf5b1", "00");
            param.put("lhwf5b2", "00");
            param.put("lhwf5b3", "00");
            param.put("lhwf5b4", "00");
            param.put("lhwf5b5a","00");
            param.put("lhwf5b5b","00");
            param.put("lhwf5b5c","00");
            param.put("lhwf5b6", "00");


            param.put("lhwf5b7_1","00");
            param.put("lhwf5b7_2","00");
            param.put("lhwf5b7_3","00");
            param.put("lhwf5b7_4","00");
            param.put("lhwf5b7_5","00");
            param.put("lhwf5b7_6","00");
            param.put("lhwf5b7_7","00");
            param.put("lhwf5b7_8","00");
            param.put("lhwf5b796", "00");
            param.put("lhwf5b796x", "00");

            param.put("LhwSectionPKId", Global.server_id);
        }



            for (Map.Entry<String, String> entry : param.entrySet()) {
                if (entry.getValue() == null || entry.getValue().length()==0) {
                    param.put(entry.getKey(), "00");
                }
                System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());

        }


        super.onPreExecute();
    }


    @Override
    protected Object doInBackground(Object[] objects) {

        String urlString = new MyPreferences(mContext).getReq3();

        urlString = urlString + "InsertF5SectionB";

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




            Global.loop_Increment=0;
            Global.loop_count=0;

                new UploadF6SectionB(mContext).execute();












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



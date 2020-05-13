package com.irfansyed.umeedenau.validation;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import data.LocalDataManager;
import data.UploadLHWSection;

import static data.LocalDataManager.database;


public class PendingUploads extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;


    public static   void show_alert(String LHWName, String LHWid, String HF, String LHWHHCount, String LHWVHCCount, String LHWWSGCount, String LHWFieldHHCount, String LHWFieldVHCCount, String LHWFieldWSGCount, final Context context, final String Pkid)
    {
        final androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(context);
        LayoutInflater inflater =(LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        final View dialogLayout = inflater.inflate(R.layout.dialogupload, null);
        builder.setView(dialogLayout);
        builder.setCancelable(false);




        final EditText RHHcount,RHCcount,RWSGcount,CHHcount,CHCcount,CWSGcount,
                LHWname,LHWID,HFName;

        LHWname=dialogLayout.findViewById(R.id.LHWname);
        LHWID=dialogLayout.findViewById(R.id.LHWID);
        HFName=dialogLayout.findViewById(R.id.HFName);

        RHHcount=dialogLayout.findViewById(R.id.RHHcount);
        RHCcount=dialogLayout.findViewById(R.id.RHCcount);
        RWSGcount=dialogLayout.findViewById(R.id.RWSGcount);

        CHHcount=dialogLayout.findViewById(R.id.CHHcount);
        CHCcount=dialogLayout.findViewById(R.id.CHCcount);
        CWSGcount=dialogLayout.findViewById(R.id.CWSGcount);


        LHWname.setText(LHWName);
        LHWID.setText(LHWid);
        HFName.setText(HF);

        RHHcount.setText(LHWHHCount);
        RHCcount.setText(LHWVHCCount);
        RWSGcount.setText(LHWFieldHHCount);

        CHHcount.setText(LHWFieldHHCount);
        CHCcount.setText(LHWFieldVHCCount);
        CWSGcount.setText(LHWFieldWSGCount);


        final Button btn_cancel,btn_ok;
        btn_cancel=dialogLayout.findViewById(R.id.btn_cancel);
        btn_ok=dialogLayout.findViewById(R.id.btn_ok);
        final androidx.appcompat.app.AlertDialog dialog = builder.create();




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


                Global.global_id=Pkid;

                new UploadLHWSection(context).execute();


                dialog.dismiss();







            }
        });



    }


    public static HashMap<String, String> map_add = new HashMap<>();
    public static HashMap<String, String> map_contac = new HashMap<>();

    public List<String> get_list() {


        List<String> lst = new ArrayList<>();
        String query2 = "select id,lhwf1a2,lhwf1a3,lhwf1a4 from  TableLHWSection  where status='0'";

        LocalDataManager Lm = new LocalDataManager(this);
        Cursor c = database.rawQuery(query2, null);


        if (c != null) {
            if (c.moveToFirst()) {
                do {


                    lst.add(c.getString(0) + "-" + c.getString(1) + "-" + c.getString(2) + "-" + c.getString(3));


                } while (c.moveToNext());
            }
        }

        return lst;


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_completed);

        List<String> list = get_list();


        if (list == null)
            return;

        Collections.sort(list);

        mRecyclerView = findViewById(R.id.list_survey_completed);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        mAdapter = new PendingUploadsCustomAdapter(this, list);
        mRecyclerView.setAdapter(mAdapter);


    }

    public static   void ininfo(String id,Context context)
    {

        String LHWHHCount="0";
        String LHWVHCCount="0";
        String LHWWSGCount="0";

        String LHWFieldHHCount="0";
        String LHWFieldVHCCount="0";
        String LHWFieldWSGCount="0";
        String LHWName="";
        String LHWID="";
        String HF="";


        String query2 = "select LHWOfficeHHCount,LHWCommunityHHCount,LHWOfficeVHCCount,LHWCommunityVHCCount,LHWOfficeWSGCount,LHWCommunityWSGCount,lhwf1a3,lhwf1a4,lhwf1a2 from TableLHWSection   where Status='0' and id="+id;

   //     LocalDataManager Lm = new LocalDataManager(this);
        Cursor c = database.rawQuery(query2, null);


        if (c != null) {
            if (c.moveToFirst()) {
                do {

                    LHWHHCount=c.getString(c.getColumnIndex("LHWOfficeHHCount"));
                    LHWFieldHHCount=c.getString(c.getColumnIndex("LHWCommunityHHCount"));
                    LHWVHCCount=c.getString(c.getColumnIndex("LHWOfficeVHCCount"));
                    LHWFieldVHCCount=c.getString(c.getColumnIndex("LHWCommunityVHCCount"));
                    LHWWSGCount=c.getString(c.getColumnIndex("LHWOfficeWSGCount"));
                    LHWFieldWSGCount=c.getString(c.getColumnIndex("LHWCommunityWSGCount"));
                    LHWID=c.getString(6);
                    LHWName=c.getString(7);

                    HF=c.getString(8);


                } while (c.moveToNext());
            }
           show_alert(LHWName,LHWID,HF,LHWHHCount,LHWVHCCount,LHWWSGCount,LHWFieldHHCount,LHWFieldVHCCount,LHWFieldWSGCount,context,id);






    }




}

class  PendingUploadsCustomAdapter extends RecyclerView.Adapter {

    Context mContext;
    List<String> mList;

    public PendingUploadsCustomAdapter(Context context, List<String> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_survey_pending, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final ViewHolder vh = (ViewHolder) holder;

        //rejected items..
        if (mList.get(position).contains("--00")) {
            vh.itemView.setBackgroundColor(Color.parseColor("#FFB7BC"));
        }

        vh.textName.setText(mList.get(position).split("--")[0]);
        vh.textId.setText(position + 1 + "");

        String memberId = vh.textName.getText().toString();





        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String memberId = vh.textName.getText().toString();


                String[] arrr = memberId.split("-");
                String id = arrr[0];



                PendingUploads.ininfo(id, mContext);


            }
        });
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textName, textId;

        public ViewHolder(View v) {
            super(v);
            textName = v.findViewById(R.id.text_item_survey_pending_name);
            textId = v.findViewById(R.id.text_item_survey_pending_id);
        }
    }


}


}
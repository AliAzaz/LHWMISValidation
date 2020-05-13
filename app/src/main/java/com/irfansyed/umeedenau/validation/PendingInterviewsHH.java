package com.irfansyed.umeedenau.validation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

import static data.LocalDataManager.database;


public class PendingInterviewsHH extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;


    public static void show_alert(String Addres, String Contact, String Respondent, String Mother, final Context context, final String id, final String Section) {
        final androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View dialogLayout = inflater.inflate(R.layout.dialogpendinghh, null);
        builder.setView(dialogLayout);
        builder.setCancelable(false);


        final EditText addres, contact, respondent, mohter;

        addres = dialogLayout.findViewById(R.id.addres);
        contact = dialogLayout.findViewById(R.id.contact);
        respondent = dialogLayout.findViewById(R.id.respondent);
        mohter = dialogLayout.findViewById(R.id.mohter);
        addres.setText(Addres);
        contact.setText(Contact);
        respondent.setText(Respondent);
        mohter.setText(Mother);


        final Button btn_cancel, btn_ok;
        btn_cancel = dialogLayout.findViewById(R.id.btn_cancel);
        btn_ok = dialogLayout.findViewById(R.id.btn_ok);
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


                Intent intt;

                if (Section.equals("SectionC")) {
                    intt = new Intent(context, Form2SectionC.class);
                } else if (Section.equals("SectionD")) {
                    intt = new Intent(context, Form2SectionD.class);
                } else if (Section.equals("SectionE")) {
                    intt = new Intent(context, Form2SectionE.class);
                } else if (Section.equals("SectionF")) {
                    intt = new Intent(context, Form2SectionF.class);
                } else if (Section.equals("SectionG")) {
                    intt = new Intent(context, Form2SectionG.class);
                } else {
                    intt = new Intent(context, Form2SectionH.class);
                }


                intt.putExtra("pk_id", id);
                context.startActivity(intt);
                dialog.dismiss();
                ((Activity) context).finish();


            }
        });



    }


   public static HashMap<String,String> map_add=new HashMap<>();
   public static HashMap<String,String> map_contac=new HashMap<>();

    public static HashMap<String,String> section_dic=new HashMap<>();
    public static HashMap<String,String> section_di2=new HashMap<>();

    public   List<String> get_list()
    {

        section_dic.put("SectionC","Maternal Care During Pregnancy");
        section_dic.put("SectionD","Maternal Care After Delivery");
        section_dic.put("SectionE","New Born Care");
        section_dic.put("SectionF","Diarreha");
        section_dic.put("SectionG","Cough or Diffcult Breathing");
        section_dic.put("SectionH","MWRA");


        section_di2.put("Maternal Care During Pregnancy","SectionC");
        section_di2.put("Maternal Care After Delivery","SectionD");
        section_di2.put("New Born Care","SectionE");
        section_di2.put("Diarreha","SectionF");
        section_di2.put("Cough or Diffcult Breathing","SectionG");
        section_di2.put("MWRA","SectionH");



        List<String> lst=new ArrayList<>();
        String query2 = "select id,lhwf1b1,Section,lhwf1b2,lhwf1b3 from  TableHHSection lhw where Fk_id="+Global.LhwSection_id;

        LocalDataManager Lm = new LocalDataManager(this);
        Cursor c = database.rawQuery(query2, null);


        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    String section=c.getString(2);
                    section=section.substring(7);



                    if(Get_status(section,c.getString(0))==true)
                        continue;

                    section=section_dic.get(section);

                    lst.add(c.getString(1)+"/"+section+"/"+c.getString(0)+"/"+c.getString(3));

                    map_add.put(c.getString(0),c.getString(3));
                    map_contac.put(c.getString(0),c.getString(4));

                } while (c.moveToNext());
            }
        }

        return lst;


    }


    public boolean   Get_status(String tbl_Name,String id)
    {


        Boolean bol=true;
        String tblName="TableF1"+tbl_Name;

        String query2 = "select Status  from "+tblName+"  where Status='0' and Fk_id="+id;


        Cursor c = database.rawQuery(query2, null);


        if (c != null) {
            if (c.moveToFirst()) {
                do {


                  bol=false;


                } while (c.moveToNext());
            }

        }


return bol;


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pendinginterviewshh);

        List<String> list = get_list();


        if (list == null)
            return;

        Collections.sort(list);

        mRecyclerView = findViewById(R.id.list_survey_completed);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        mAdapter = new PendingInterviewsHHCustomAdapter(this, list);
        mRecyclerView.setAdapter(mAdapter);


    }

    public static   void ininfo(String tbl_Name,String id,Context context)
    {


        tbl_Name=section_di2.get(tbl_Name);

        String RespondentName="";
        String RespndehusbandName="";
        String pkid="";

       String tblName="TableF1"+tbl_Name;

        String query2 = "select *  from "+tblName+"  where Status='0' and Fk_id="+id;

   //     LocalDataManager Lm = new LocalDataManager(this);
        Cursor c = database.rawQuery(query2, null);


        if (c != null) {
            if (c.moveToFirst()) {
                do {


                    pkid=c.getString(0);
                    RespondentName=c.getString(6);
                    RespndehusbandName=c.getString(7);


                } while (c.moveToNext());
            }
            show_alert(map_add.get(id),map_contac.get(id),RespondentName,RespndehusbandName,context,pkid,tbl_Name);
        }





    }




}




class  PendingInterviewsHHCustomAdapter extends RecyclerView.Adapter{

    Context mContext;
    List<String> mList;
    public PendingInterviewsHHCustomAdapter(Context context, List<String> list){
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
        if(mList.get(position).contains("--00")) {
          vh.itemView.setBackgroundColor(Color.parseColor("#FFB7BC"));
        }

        vh.textName.setText(mList.get(position).split("--")[0]);
        vh.textId.setText(position+1 +"");

        String memberId = vh.textName.getText().toString();


        String[] arrr=memberId.split("/");




        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                        String memberId = vh.textName.getText().toString();





                        String[] arrr=memberId.split("/");
                         String id=arrr[2];
                         String tbl=arrr[1];






               PendingInterviewsHH.ininfo(tbl,id,mContext);




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
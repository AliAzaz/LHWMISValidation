package com.irfansyed.umeedenau.validation;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.irfansyed.umeedenau.validation.Global.PROJECT_NAME;


public class PhotoUploads extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    public List<String> get_list() {

        List<String> lst = new ArrayList<>();

        String fileName = "";
        String appFolder = PROJECT_NAME;

        File sdDir = Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        Log.d("Files", "Path: " + sdDir);
        File directory = new File(String.valueOf(sdDir), appFolder);
        Log.d("Directory", "uploadPhotos: " + directory);
        if (directory.exists()) {
            File[] files = directory.listFiles(new FileFilter() {
                @Override
                public boolean accept(File file) {
                    return (file.getPath().endsWith(".jpg") || file.getPath().endsWith(".jpeg"));
                }
            });


            Log.d("Files", "Count: " + files.length);

            if (files.length > 0) {
                for (int i = 0; i < files.length; i++) {
                    Log.d("Files", "FileName:" + files[i].getName());

                    lst.add(files[i].getName());


                }
            }
        }
        Log.d("PhotoTAG", "count: " + lst.size());

        return lst;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_completed);

        populatePhotoList();



    }

    private void populatePhotoList() {

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

    class PendingUploadsCustomAdapter extends RecyclerView.Adapter {

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

            vh.textName.setText(mList.get(position));
            vh.textId.setText(position + 1 + "");

            vh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String memberId = vh.textName.getText().toString();

                    SyncAllPhotos syncAllPhotos = new SyncAllPhotos(memberId, mContext);
                    syncAllPhotos.execute();

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
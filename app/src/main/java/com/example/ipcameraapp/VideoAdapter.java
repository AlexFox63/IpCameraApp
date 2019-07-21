package com.example.ipcameraapp;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VideoAdapter extends BaseAdapter {
    Context context;
    List<VideoListItems>listItems;
    VideoListItems videoListItems;

    public VideoAdapter(Context context, List<VideoListItems> listItems) {
        this.context = context;
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int i) {
        return listItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View grid;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(view == null) {
            grid = new View(context);
            grid = inflater.inflate(R.layout.video_item, null);
            VideoView videoView = grid.findViewById(R.id.videoView);
            if((listItems.get(i).Login != null) && (listItems.get(i).Pwd) != null){
                Map<String,String> headers = new HashMap<>();
                headers.put("Login", listItems.get(i).Login);
                headers.put("Password", listItems.get(i).Pwd);
                videoView.setVideoURI(Uri.parse("rtsp://" + listItems.get(i).videoPath + "/axis-media/media.amp"), headers);
            }else {
                videoView.setVideoURI(Uri.parse("rtsp://" + listItems.get(i).videoPath + "/axis-media/media.amp"));
            }
            videoView.start();
        }else {
            grid = (View) view;
        }
        return grid;
    }




}

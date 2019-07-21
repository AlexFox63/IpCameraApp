package com.example.ipcameraapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FullVideoActivity extends Activity {

    List<VideoListItems> listItems;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_video);
        Context context = getApplicationContext();
        Toast.makeText(context, "SecondActivity", Toast.LENGTH_SHORT).show();
        Intent i = getIntent();
        int position = i.getExtras().getInt("id");
        Toast.makeText(getApplicationContext(), position, Toast.LENGTH_SHORT).show();

        VideoAdapter videoAdapter = new VideoAdapter(this, listItems);
        VideoView videoView = findViewById(R.id.full_videoView);
        if((videoAdapter.listItems.get(position).Login != null) && (videoAdapter.listItems.get(position).Pwd) != null){
            Map<String,String> headers = new HashMap<>();
            headers.put("Login", videoAdapter.listItems.get(position).Login);
            headers.put("Password", videoAdapter.listItems.get(position).Pwd);
            videoView.setVideoURI(Uri.parse("rtsp://" + videoAdapter.listItems.get(position).videoPath + "/axis-media/media.amp"), headers);
        }else {
            videoView.setVideoURI(Uri.parse("rtsp://" + videoAdapter.listItems.get(position).videoPath + "/axis-media/media.amp"));
        }
        videoView.start();
    }
}

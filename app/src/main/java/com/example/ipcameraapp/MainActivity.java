package com.example.ipcameraapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AddVideoDialogFragment.AddVideoDialogListener {

    private ArrayList<VideoListItems> itemsArrayList = new ArrayList<>();
    private VideoListItems videoListItems;
    private GridView gridView;
    private String IP;
    private String LOGIN;
    private String PASSWORD;


    final static String url = "rtsp://46.0.199.87/axis-media/media.amp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(onAddingListener());

        gridView = findViewById(R.id.gridView);

        Toast.makeText(getApplicationContext(), "Press in Create", Toast.LENGTH_SHORT).show();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "Press in Grid" + i, Toast.LENGTH_SHORT).show();
                // passing array index
                Intent intent1 = new Intent(getApplicationContext(), FullVideoActivity.class);
                intent1.putExtra("id", i);
                startActivity(intent1);
            }
        });

    }

    private View.OnClickListener onAddingListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNoticeDialog();
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showNoticeDialog() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new AddVideoDialogFragment();
        dialog.show(getSupportFragmentManager(), "AddVideoDialogFragment");
    }


    @Override
    public void onDialogPositiveClick(Bundle bundle) {
        IP = bundle.getString("IP", "");
        LOGIN = bundle.getString("LOGIN", "");
        PASSWORD = bundle.getString("PWD", "");
        Intent intent = new Intent("SendData");
        intent.putExtra("IP", IP);
        intent.putExtra("LOGIN", LOGIN);
        intent.putExtra("PWD", PASSWORD);
        //LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
        videoListItems = new VideoListItems(IP, LOGIN, PASSWORD);
        itemsArrayList.add(videoListItems);
        VideoAdapter videoAdapter = new VideoAdapter(getApplicationContext(), itemsArrayList);
        gridView.setAdapter(videoAdapter);

        //Toast.makeText(getApplicationContext(), IP, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }



}

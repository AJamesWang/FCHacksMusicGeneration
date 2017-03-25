package com.example.alphafishprop.sonicsightreader;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {
    private static final String LOG_TAG="MainMenu";
    private static final int REQUEST_RECORD_AUDIO_PERMISSION=200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        final Button button_start = (Button) findViewById(R.id.button_start);
        //button_start.OnClickListener
        button_start.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                button_start_click(button_start);
            }
        });
    }

    //permission to RECORD_AUDIO
    private boolean permissionToRecordAccepted=false;
    private String[] permissions = {Manifest.permission.RECORD_AUDIO};

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode){
            case REQUEST_RECORD_AUDIO_PERMISSION:
                permissionToRecordAccepted=grantResults[0]== PackageManager.PERMISSION_GRANTED;
                break;
        }
        if (!permissionToRecordAccepted)
            finish();
    }



    public void button_start_click(Button b) {
        setContentView(R.layout.activity_sightreading_view);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        setContentView(R.layout.activity_sightreading_view);
        return;
    }

}

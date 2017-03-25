package com.example.alphafishprop.sonicsightreader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class SightreadingView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sightreading_view);
    }

    public void button_start_click(Button v) {
        //start activity in here
        v.setText("It worked!");
    }

    public void button_options_click(Button b) {
        //ToDo:  Nothing I guess, this will not be implemented
    }
}

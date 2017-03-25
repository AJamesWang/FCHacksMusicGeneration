package com.example.alphafishprop.sonicsightreader;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;

public class MainMenu extends AppCompatActivity {
    private static final String LOG_TAG="MainMenu";
    private static final int REQUEST_RECORD_AUDIO_PERMISSION=200;
    //public static AudioHandler h;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        //this.generateMelody();
//        final Button button_stop = (Button) findViewById(R.id.button_stop);
        //button_start.OnClickListener
//        button_stop.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                button_stop.setText("blah");
//            }
//        });
        final Button button_start = (Button) findViewById(R.id.button_start);
        //button_start.OnClickListener
        button_start.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                button_start_click(button_start);
            }
        });
        //this.h = new AudioHandler();
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
        Intent i = new Intent(getBaseContext(), SightreadingView.class);
        MainMenu.this.startActivity(i);
    }

    MelodyGenerator melodyGenerator=new MelodyGenerator();

    private void generateMelody() {
        Button b = (Button)findViewById(R.id.button_stop);
        List<String> melody = melodyGenerator.getMelody();
      b.setText(melody.get(0));
        for(int i=1;i<=melody.size(); i++){
            String note=melody.get(i-1).toLowerCase();//not editing 16 switch statements
            ImageView iv;
            switch(i){
                case(1):
                    iv=(ImageView)findViewById(R.id.note1);
                    break;
                case(2):
                    iv=(ImageView)findViewById(R.id.note2);
                    break;
                case(3):
                    iv=(ImageView)findViewById(R.id.note3);
                    break;
                case(4):
                    iv=(ImageView)findViewById(R.id.note4);
                    break;
                case(5):
                    iv=(ImageView)findViewById(R.id.note5);
                    break;
                case(6):
                    iv=(ImageView)findViewById(R.id.note6);
                    break;
                case(7):
                    iv=(ImageView)findViewById(R.id.note7);
                    break;
                case(8):
                    iv=(ImageView)findViewById(R.id.note8);
                    break;
                case(9):
                    iv=(ImageView)findViewById(R.id.note9);
                    break;
                case(10):
                    iv=(ImageView)findViewById(R.id.note10);
                    break;
                case(11):
                    iv=(ImageView)findViewById(R.id.note11);
                    break;
                case(12):
                    iv=(ImageView)findViewById(R.id.note12);
                    break;
                case(13):
                    iv=(ImageView)findViewById(R.id.note13);
                    break;
                case(14):
                    iv=(ImageView)findViewById(R.id.note14);
                    break;
                case(15):
                    iv=(ImageView)findViewById(R.id.note15);
                    break;
                case(16):
                    iv=(ImageView)findViewById(R.id.note16);
                    break;
                default://FUCK YOU ANDROID
                    iv=(ImageView)findViewById(R.id.note16);
                    break;
            }

            iv.setImageResource(R.drawable.treble_clef);
//            switch(note){
//                case("a4"):
//                    iv.setImageResource(R.drawable.a4);
//                    break;
//                case("b4"):
//                    iv.setImageResource(R.drawable.b4);
//                    break;
//                case("c4"):
//                    iv.setImageResource(R.drawable.c4);
//                    break;
//                case("c5"):
//                    iv.setImageResource(R.drawable.c5);
//                    break;
//                case("d4"):
//                    iv.setImageResource(R.drawable.d4);
//                    break;
//                case("e4"):
//                    iv.setImageResource(R.drawable.e4);
//                    break;
//                case("f4"):
//                    iv.setImageResource(R.drawable.c4);
//                    break;
//                case("g4"):
//                    iv.setImageResource(R.drawable.g4);
//                    break;
//            }
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        setContentView(R.layout.activity_sightreading_view);
        return;
    }


    /**
     * Created by zhendeveloper on 3/25/17.
     */

    public class MelodyGenerator {
        //C Major, 4/4, four bars of quarter notes
        //length is always 16
        public List<String> getMelody() {
            List<String> chords = getChords();
            List<String> melody = new LinkedList<>();
            for (String chord : chords) {
                melody.add(getNoteFromChord(chord));
            }
            assert melody.size() == 16;
            return melody;
        }

        //four bars of quarter notes
        //generates chords backwards
        List<String> getChords() {
            List<String> chords = new LinkedList<>();
            chords.add("I");
            for (int i = 0; i < 14; i++) {
                chords.add(0, getPrevChord(chords.get(0)));
            }
            chords.add(0, getFirstChord(chords.get(0)));
            return chords;
        }

        String getNoteFromChord(String chord) {
            double r = Math.random();
            switch (chord) {
                case "I":
                    if (r < 1.0 / 4)
                        return "C4";
                    if (r < 2.0 / 4)
                        return "E4";
                    if (r < 3.0 / 4)
                        return "G4";
                    //else
                    return "C5";
                case "ii":
                    if (r < 1.0 / 3)
                        return "D4";
                    if (r < 2.0 / 3)
                        return "F4";
                    //else
                    return "A4";
                case "IV":
                    if (r < 1.0 / 4)
                        return "C4";
                    if (r < 2.0 / 4)
                        return "F4";
                    if (r < 3.0 / 4)
                        return "A4";
                    //else
                    return "C5";
                case "V":
                    if (r < 1.0 / 4)
                        return "D4";
                    if (r < 2.0 / 4)
                        return "F4";
                    if (r < 3.0 / 4)
                        return "G4";
                    //else
                    return "B4";
                case "V(V)":
                    if (r < 1.0 / 2)
                        return "D4";
                    //else
                    return "A4";
                case "vi":
                    if (r < 1.0 / 4)
                        return "C4";
                    if (r < 2.0 / 4)
                        return "E4";
                    if (r < 3.0 / 4)
                        return "A4";
                    //else
                    return "C5";
                case "vii":
                    if (r < 1.0 / 3)
                        return "D4";
                    if (r < 2.0 / 3)
                        return "F4";
                    //else
                    return "B4";
                default:
                    throw new InputMismatchException("Bad chord input");
            }
        }

        //works only in Major
        //
        String getPrevChord(String chord) {
            double r = Math.random(); //r is in [0, 1)
            switch (chord) {
                case "I":
                    if (r < .25)
                        return "vii";
                    //else
                    return "V";
                case "ii":
                    if (r < .25)
                        return "IV";
                    if (r > .5)
                        return "I";
                    //else
                    return "vi";
                case "IV":
                    if (r < (1.0 / 3))
                        return "vi";
                    //else
                    return "I";
                case "V":
                    double c1 = .25;
                    if (r < c1)
                        return "I";
                    c1 += 3.0 / 32;
                    if (r < c1)
                        return "ii";
                    c1 += 9.0 / 32;
                    if (r < c1)
                        return "IV";
                    c1 += 3.0 / 16;
                    if (r < c1)
                        return "V(V)";
                    c1 += 1.0 / 8;
                    if (r < c1)
                        return "vi";
                    //else
                    return "vii";
                case "V(V)":
                    double c2 = 1.0 / 3;
                    if (r < c2)
                        return "I";
                    c2 += 1.0 / 8;
                    if (r < c2)
                        return "ii";
                    c2 += 3.0 / 8;
                    if (r < c2)
                        return "IV";
                    //else
                    return "vi";
                case "vi":
                    if (r < 1.0 / 3)
                        return "vii";
                    //else
                    return "V";
                case "vii":
                    double c3 = .25;
                    if (r < c3)
                        return "I";
                    c3 += 3.0 / 32;
                    if (r < c3)
                        return "ii";
                    c3 += 9.0 / 32;
                    if (r < c3)
                        return "IV";
                    c3 += 1.0 / 4;
                    if (r < c3)
                        return "V(V)";
                    //else
                    return "vi";
                default:
                    throw new InputMismatchException("Bad chord input");
            }
        }

        //works only in Major
        //
        String getFirstChord(String chord) {
            final double r = Math.random(); //r is in [0, 1)
            switch (chord) {
                case "I":
                    return "V";
                case "ii":
                    return "I";
                case "IV":
                    return "I";
                case "V":
                    return "I";
                case "V(V)":
                    return "I";
                case "vi":
                    return "V";
                case "vii":
                    return "I";
                default:
                    throw new InputMismatchException("Bad chord input");
            }
        }
    }
}

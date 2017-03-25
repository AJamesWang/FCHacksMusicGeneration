package com.example.alphafishprop.sonicsightreader;

import android.Manifest;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.util.Log;

import java.io.IOException;

/**
 * Created by alphafishprop on 3/25/2017.
 */

public class AudioHandler {

    private static final String LOG_TAG="AudioHandler";

    private static String mFileName=null;//@TODO: give file a name
    private MediaRecorder mRecorder=null;
    private MediaPlayer mPlayer=null;

    //Will probably handled higher up
    //permission to RECORD_AUDIO
//    private boolean permissionToRecordAccepted=false;
//    private String[] permissions={Manifest.permission.RECORD_AUDIO};

    public AudioHandler(){
    }

    public void startRecording(){
        mRecorder=new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try{
            mRecorder.prepare();
        } catch (IOException e){
            Log.e(LOG_TAG, "prepare() failed");
        }
    }

    public void stopRecording(){
        mRecorder.stop();
        mRecorder.release();
        mRecorder=null;
    }

    public void startPlayback(){
        mPlayer=new MediaPlayer();
        try {
            mPlayer.setDataSource(mFileName);
            mPlayer.prepare();
            mPlayer.start();
        } catch(IOException e){
            Log.e(LOG_TAG, "prepare() failed");
        }
    }

    public void stopPlayback(){
        mPlayer.release();
        mPlayer=null;
    }
}

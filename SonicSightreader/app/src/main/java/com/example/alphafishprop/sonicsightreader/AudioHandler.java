package com.example.alphafishprop.sonicsightreader;

import android.Manifest;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.util.Log;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by alphafishprop on 3/25/2017.
 */

public class AudioHandler {

    private static final String LOG_TAG="AudioHandler";

    private FileDescriptor mRecordedAudioFileDesc = null;
    private File mCorrectMelodyFile = null;
    private MediaRecorder mRecorder=null;
    private MediaPlayer mPlayer=null;

    //Will probably handled higher up
    //permission to RECORD_AUDIO
//    private boolean permissionToRecordAccepted=false;
//    private String[] permissions={Manifest.permission.RECORD_AUDIO};

    public AudioHandler()
    {
        mRecordedAudioFileDesc = FileOutputStream(File.createTempFile("recordedAudio", "3gpp")).getFD()
    }

    public void startRecording(){
        mRecorder=new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mRecordedAudioFileDesc);
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

    public void startPlaybackOfRecordedVoice(){
        mPlayer=new MediaPlayer();
        try {
            mPlayer.setDataSource(mRecordedAudioFileDesc);
            mPlayer.prepare();
            mPlayer.start();
        } catch(IOException e){
            Log.e(LOG_TAG, "prepare() failed");
        }
    }

    public void stopPlaybackOfRecordedVoice(){
        mPlayer.release();
        mPlayer=null;
    }

    public void startPlaybackOfCorrectMelody(List<String> melody)
    {
        mCorrectMelodyFile = File.createTempFile("correctMelody", "mid");
        AudioFileWriter.writeMelodyToFile(melody, FileOutputStream(mCorrectMelodyFile));
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource("correctMelody.mid");
            mPlayer.prepare();
            mPlayer.start();
        } catch(IOException e){
            Log.e(LOG_TAG, "prepare() failed");
        }
    }

    public void stopPlaybackOfCorrectMelody()
    {
        mPlayer.release();
        mPlayer=null;
    }
}

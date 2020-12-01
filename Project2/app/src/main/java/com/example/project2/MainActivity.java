package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Queue;
import java.util.SimpleTimeZone;

public class MainActivity extends AppCompatActivity {
    private TextToSpeech mTTS;
    private EditText mEditText;
    private SeekBar mSeekBarPitch,mSeekBarSpeed;
    private Button btnSpeak;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       btnSpeak=findViewById(R.id.btnSpeak);
        mTTS=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status==TextToSpeech.SUCCESS){
                   int result= mTTS.setLanguage(Locale.ENGLISH);
                   if(result==TextToSpeech.LANG_MISSING_DATA||result==TextToSpeech.LANG_NOT_SUPPORTED){
                       Log.e("TTS","Language Not Supported");
                   }
                   else{
                       btnSpeak.setEnabled(true);
                   }
                }
                else{
                    Log.e("TTS","Initialization failed");
                }
            }
        });
        mEditText=findViewById(R.id.edit_text);
        mSeekBarPitch=findViewById(R.id.seek_bar_pitch);
        mSeekBarSpeed=findViewById(R.id.seek_bar_speed);
        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak();
            }
        });
    }

    private void speak() {
        SimpleDateFormat sdf=new SimpleDateFormat("hh:mm a");
        String currentTime=sdf.format(Calendar.getInstance().getTime());//System.out.println("current"+currentTime);
        String text=currentTime;//mEditText.getText().toString();
        float pitch=mSeekBarPitch.getProgress()/50;
        if(pitch<0.1)pitch=0.1f;
        float speed=mSeekBarSpeed.getProgress()/50;
        if(speed<0.1)speed=0.1f;
        mTTS.setPitch(pitch);
        mTTS.setSpeechRate(speed);
        mTTS.speak(text,TextToSpeech.QUEUE_FLUSH,null);
    }

    @Override
    protected void onDestroy() {
        if(mTTS!=null){
            mTTS.stop();
            mTTS.shutdown();
        }
        super.onDestroy();
    }
}
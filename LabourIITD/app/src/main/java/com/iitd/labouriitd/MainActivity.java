package com.iitd.labouriitd;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> al=new ArrayList<String>(){
        { add("What is your name?");add("From where do you belong?");
        add("Location where you like to work at?");add("Your Educational History");
        add("What kind of work you need?");}
        };
    private ArrayList<String> ansL=new ArrayList<>();
    private int ind=0;
    private TextView outputText;
    private TextToSpeech mTTs;
    private EditText mEditText;
    private SeekBar mSeekBarPitch;
    private SeekBar mSeekBarSpeed;
    private Button mBuutonSpeak;
    private Button btnRefresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        outputText=(TextView)findViewById(R.id.txtOutput);
        mBuutonSpeak=findViewById(R.id.btn_speak);
        btnRefresh=findViewById(R.id.btnRefresh);
        System.out.println(al);
        mTTs=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status==TextToSpeech.SUCCESS){

                    int result=mTTs.setLanguage(Locale.ENGLISH);
                    if(result==TextToSpeech.LANG_MISSING_DATA || result==TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.e("TTS","Language not supported");

                    }
                    else{
                        mBuutonSpeak.setEnabled(true);
                    }
                }
                else{
                    Log.e("TTS","Initialization Failed");
                }
            }
        });
        mEditText=findViewById(R.id.editText);
        mSeekBarPitch=findViewById(R.id.seek_bar_pitch);
        mSeekBarSpeed=findViewById(R.id.seek_bar_speed);
        mBuutonSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak();
            }
        });
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ind!=0)
                ansL.remove(ind);
                --ind;
            }
        });
    }
    private void speak(){
        //String text=mEditText.getText().toString();

        String text="";
        if(ind<5){
            mEditText.setText(al.get(ind));
           text=al.get(ind);
        }
        else{
            System.out.println("Answers"+ansL);
            startActivity(new Intent(this,ReportActivity.class).putExtra("answers",ansL));
        }
        float pitch=(float)mSeekBarPitch.getProgress()/50;
        if(pitch<0.1)pitch=0.1f;
        float speed=(float)mSeekBarSpeed.getProgress()/50;
        if(speed<0.1)speed=0.1f;

        mTTs.setPitch(pitch);
        mTTs.setSpeechRate(speed);
        mTTs.speak(text,TextToSpeech.QUEUE_FLUSH,null);

    }

    @Override
    protected void onDestroy() {
        if(mTTs!=null){
            mTTs.stop();
            mTTs.shutdown();
        }
        super.onDestroy();
    }
    public void btnSpeech(View view) {
        Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Speak Something");
        try {
            startActivityForResult(intent,1);
        }catch(ActivityNotFoundException e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                if(resultCode==RESULT_OK && null!=data)
                {
                    ArrayList<String> result=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    outputText.setText(result.get(0));
                    ansL.add(result.get(0));
                }
                break;

        }
    }

    public void changeQ(View view) {
        ++ind;

    }
}
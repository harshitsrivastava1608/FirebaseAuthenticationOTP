package com.iitd.labouriitd;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class Result extends AppCompatActivity {
private String outputText;
private TextView textView2;
public static final String SHARED_PREFS="sharedPrefs";
public static final String text="text";
static int count=0;private ArrayList <MyModel> modelArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        textView2=findViewById(R.id.textView2);
        Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Speak Something");
       // System.out.println("wel");
        try {
            //System.out.println("come");
            startActivityForResult(intent,1);
           // System.out.println("home"+outputText);
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
                    outputText=(result.get(0));
                    textView2.setText(outputText);
                    Intent intent=new Intent(this,Take3.class);

                    intent.putExtra("sir",outputText);
                    intent.putExtra("position",getIntent().getIntExtra("position",999));

                    startActivity(intent);
                    //MyModel myModel=new MyModel("What is your name?",outputText);
                   // modelArrayList.add(myModel);
                  //  modelArrayList.set(0,new MyModel("What is your name?",outputText));
                  
                    System.out.println("back"+outputText);
                    Intent i=new Intent();
                    i.putExtra("outputText",outputText);


                }


        }
    }

    private void saveData() {
        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(text,"0");

    }
}
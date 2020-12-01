package com.iitd.labouriitd;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Locale;

public class Microphone extends AppCompatActivity {
private String outputText;
private Intent intent;
private TextView txtText;
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_microphone);
        txtText=findViewById(R.id.txtText);
        intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Speak Something");
        System.out.println("wel");
        try {
            System.out.println("come");
            startActivityForResult(intent,1);
            System.out.println("home"+outputText);
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
                    txtText.setText(outputText);
                    System.out.println("pose"+getIntent().getIntExtra("position",999));
                  //  intent.putStringArrayListExtra("back",new ArrayList<String>().add(result));

                    intent.putExtra("back",outputText);
                    System.out.println("back"+outputText);


                   // onDestroy();
                    //ansL.add(result.get(0));
                }
                break;

        }
    }

void checkPermision(){

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, 101);
        }
    }

}

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    String fileName="mytextfile.txt";
    public void createFile(View view)  {
       String data=outputText;FileOutputStream outputStream=null;
        try {
             outputStream=openFileOutput(fileName,MODE_PRIVATE);
             outputStream.write(data.getBytes());
             outputStream.flush();
             txtText.setText(outputText+"File Written");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    finally {
            if (outputStream!=null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("ans",outputText);
    }
    public void readFile(View v){
        StringBuilder stringBuilder=new StringBuilder();
        InputStream inputStream=null;
        try {
            inputStream=openFileInput(fileName);
            int read;
            while ((read=inputStream.read())!=-1){
                stringBuilder.append((char)read);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //txtText.setText(stringBuilder.toString());
        String path=getFilesDir().getAbsolutePath();
        txtText.setText(stringBuilder.toString()+"**"+path);
    }

    public void resultcheck(View view) {
        startActivity(new Intent(this,Result.class));
    }
}
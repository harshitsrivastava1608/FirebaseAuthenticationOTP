package com.iitd.labouriitd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.StringTokenizer;

public class Submission extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission);
        sharedPreferences = getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE);

       textView= findViewById(R.id.textView);
        StringTokenizer stringTokenizer=new StringTokenizer(sharedPreferences.getString("response", "-999"),",");
        int count = 0;
        String str="";
        while (stringTokenizer.hasMoreTokens()) {
            str=str+count+stringTokenizer.nextToken()+"\n";

            ++count;
        }
       textView.setText(str);
    }
}
package com.iitd.labouriitd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class ReportActivity extends AppCompatActivity {
    private TextView report;
    private ArrayList<String> al=new ArrayList<String>(){
        { add("What is your name?");add("From where do you belong?");
            add("Location where you like to work at?");add("Your Educational History");
            add("What kind of work you need?");}
    };
    private ArrayList<String> ansL=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        report=(TextView)findViewById(R.id.report);
        ansL=getIntent().getStringArrayListExtra("answers");String fs="",fq="";
        int id=0;
        for(String questions:al)
            fq=fq+questions;
        for (String answers : ansL){
            if(id<5)
            fs=fs+(id+1)+":"+al.get(id)+answers+"\t";++id;
            System.out.println("--"+al.get(id));
        }
        report.setText(fq+fs);
    }
}
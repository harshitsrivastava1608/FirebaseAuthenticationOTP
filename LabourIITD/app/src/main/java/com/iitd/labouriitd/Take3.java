package com.iitd.labouriitd;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Take3 extends AppCompatActivity {
    private ActionBar actionBar;
    private ViewPager viewPager;
    private ArrayList<MyModel> modelArrayList;
    private int pose;
    private MyAdapter myAdapter;
    private String str;
    SharedPreferences sharedPreferences;
    public static final String text = "text";
    private ArrayList<String> al = new ArrayList<String>() {
        {
            add("What is your name?");
            add("From where do you belong?");
            add("Location where you like to work at?");
            add("Your Educational History");
            add("What kind of work you need?");
        }
    };
    public ArrayList<String> ansL = new ArrayList<String>() {
        {
            add("Your Answer");
            add("Your Answer");
            add("Your Answer");
            add("Your Answer");
            add("Your Answer");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_take3);

        actionBar = getSupportActionBar();
        viewPager = findViewById(R.id.viewPager);
        loadCards(al, ansL);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                String title = modelArrayList.get(position).getQuestion();
                //  actionBar.setTitle(title);
                actionBar.hide();
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        // int n=Integer.parseInt(sharedPreferences.getString("text","-1"));
        String ott = getIntent().getStringExtra("sir");
        pose = getIntent().getIntExtra("position", 0);
        ansL.set(pose, ott);
        str = ott;
        sharedPreferences = getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE);

        String res = sharedPreferences.getString("response", "-1");
        saveData();
        String res1 = sharedPreferences.getString("response", "-1");
        System.out.println("before" + pose+res + "after" + res1 + sharedPreferences.getString("response", "Your Answer"));
        //   System.out.println("Q"+pose+""+al+"A"+ansL);
       /*
        if(pose>0){
            for(int i=0;i<pose;i++){
                al.remove(i);ansL.remove(i);}}
        System.out.println("Q"+pose+""+al+"A"+ansL);*/
        if(pose>=4) {

            startActivity(new Intent(this,Submission.class));
        }
        loadCards(al, ansL);
    }

    @Override
    protected void onResume() {

        super.onResume();
    }

    private void loadCards(ArrayList<String> qset, ArrayList<String> aset) {
        sharedPreferences = getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE);
        String temp = sharedPreferences.getString("response", "Your Answer");

        StringTokenizer stringTokenizer = new StringTokenizer(temp, ",");
        System.out.println("Tokens:" + temp + stringTokenizer);
        int count = 0;

        while (stringTokenizer.hasMoreTokens()) {
                if(count<=4)
            aset.set(count, stringTokenizer.nextToken());
                else
                    break;
            ++count;
        }

        modelArrayList = new ArrayList<>();
        int i = 0;
        for (String ques : qset) {
            modelArrayList.add(new MyModel(ques, aset.get(i)));
            ++i;
        }


        myAdapter = new MyAdapter(this, modelArrayList);
        viewPager.setAdapter(myAdapter);
        viewPager.setPadding(100, 0, 100, 0);
    }

    private void saveData() {
        sharedPreferences = getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE);
        String temp = sharedPreferences.getString("response", "Your Answer");
        if (temp.equals("Your Answer"))

            sharedPreferences.edit().putString("response", str).commit();//apply();
        else
            sharedPreferences.edit().putString("response", temp + "," + str).commit();
        System.out.println("str" + str + "**" + sharedPreferences.getString("response", "Your Answer"));
    }


}
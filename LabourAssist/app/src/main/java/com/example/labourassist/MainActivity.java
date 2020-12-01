package com.example.labourassist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.Adapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ViewPagerAdapter adapter;ViewPager2 viewPager2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager2=findViewById(R.id.viewpager);
        List<Integer> images=new ArrayList<Integer>();
        images.add(R.drawable.imga); images.add(R.drawable.imgb); images.add(R.drawable.imgc);
      adapter = new ViewPagerAdapter(images);
        viewPager2.setAdapter(adapter);

    }
}
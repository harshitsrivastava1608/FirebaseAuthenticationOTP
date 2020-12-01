package com.iitd.labouriitd;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.Locale;

public class MyAdapter extends PagerAdapter implements MyViewAdapter {
    private Context context;
String outputText;
   private TextView descriptionTv;
    private ArrayList<MyModel> modelArrayList;
    private TextToSpeech mTTs;

    public MyAdapter(Context context, ArrayList<MyModel> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @Override
    public int getCount() {
        return modelArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_view_pager,container,false);

        mTTs=new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status==TextToSpeech.SUCCESS){

                    int result=mTTs.setLanguage(Locale.ENGLISH);
                    if(result==TextToSpeech.LANG_MISSING_DATA || result==TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.e("TTS","Language not supported");

                    }
                    else{
                        view.findViewById(R.id.ic_speaker).setEnabled(true);
                    }
                }
                else{
                    Log.e("TTS","Initialization Failed");
                }
            }
        });
        //ImageView bannerIv=view.findViewById(R.id.bannerIv);
        TextView titleTv=view.findViewById(R.id.titleTv);
        descriptionTv=view.findViewById(R.id.descriptionTv);
       // TextView dateTv=view.findViewById(R.id.dateTv);
        MyModel model=modelArrayList.get(position);
        String title=model.getQuestion();
        String desc=model.getAnswer();
       view.findViewById(R.id.ic_speaker).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               speak(titleTv.getText().toString());
           }
       });
        //bannerIv.setImageResource(image);
        titleTv.setText(title);
        descriptionTv.setText(desc);
        //dateTv.setText(date);
        view.findViewById(R.id.ic_mic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,Result.class);
                intent.putExtra("position",position);
                notifyDataSetChanged();
                ((Activity)context).startActivityForResult(intent,1);
               String ot=intent.getStringExtra("outputText");
               System.out.println("Brought here:"+ot);

            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,title+"\n"+desc+"\n",Toast.LENGTH_SHORT).show();
            //TextView tv;

            //tv=view.findViewById(R.id.descriptionTv);
            //tv.setText("hola");

            }
        });

        container.addView(view,position);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
    private void speak(String text){

        mTTs.speak(text, TextToSpeech.QUEUE_FLUSH,null);

    }



}

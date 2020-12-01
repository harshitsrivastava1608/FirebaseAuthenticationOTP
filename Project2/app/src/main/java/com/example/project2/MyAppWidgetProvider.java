package com.example.project2;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.ImageButton;
import android.widget.RemoteViews;

public class MyAppWidgetProvider extends AppWidgetProvider {
    ImageButton btnTrig;
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        for(int appWidgetId:appWidgetIds){

            Intent intent=new Intent(context,MainActivity.class);
            PendingIntent pendingIntent=PendingIntent.getActivity(context,0,intent,0);
            RemoteViews views=new RemoteViews(context.getPackageName(),R.layout.wid_layout);

            views.setOnClickPendingIntent(R.id.btnTrig,pendingIntent);
            appWidgetManager.updateAppWidget(appWidgetId,views);

        }
    }
}

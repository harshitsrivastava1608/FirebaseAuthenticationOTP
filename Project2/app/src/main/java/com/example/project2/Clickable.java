package com.example.project2;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import java.util.Calendar;
import java.util.Random;

/**
 * Implementation of App Widget functionality.
 */
public class Clickable extends AppWidgetProvider {
    MyPreference preference;
    private static final String WIDGET_SYNC = "WIDGET_SNC";
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        Intent intent=new Intent(context,Clickable.class);
        intent.setAction(WIDGET_SYNC);
        //intent.putExtra("appWidgetId",appWidgetId);
        PendingIntent pendingIntent= PendingIntent.getBroadcast(context,0,intent,0);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.clickable);

        views.setTextViewText(R.id.appwidget_text,""+(Math.random()) );
        views.setOnClickPendingIntent(R.id.iv_sync,pendingIntent);
        
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (WIDGET_SYNC==intent.getAction()){
            int appWidgetId=intent.getIntExtra("appWidgetId",0);
            updateAppWidget(context, AppWidgetManager.getInstance(context), appWidgetId);
        }
        super.onReceive(context, intent);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them

        if (preference==null) {
            preference = new MyPreference(context);
        }
        int ids=preference.getWidgetIds();

        for (int appWidgetId : appWidgetIds) {
            ids.add(appWidgetId).toString()
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        Intent intent=new Intent(context,Clickable.class);
        intent.setAction(WIDGET_SYNC);
        PendingIntent pendingIntent= PendingIntent.getBroadcast(context,0,intent,0);
        Calendar now=Calendar.getInstance();
        now.set(Calendar.SECOND,0);
        now.add(Calendar.MINUTE,1);
        AlarmManager alarmManager= (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.RTC,now.getTimeInMillis(),60000,pendingIntent);

        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}
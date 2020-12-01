package com.example.project2

import android.content.Context

val PREFERENCE_NAME="preference"
val PREFERENCE_WIDGET_ID="preference"
class MyPreference (context: Context){
    val preference=context.getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE)
    fun updateWidgetIds(ids:MutableSet<String>){
        val editor=preference.edit()
        editor.putStringSet(PREFERENCE_WIDGET_ID,ids)
        editor.apply()

    }
    //fun  getWidgetIds():MutableSet<String>{

    //}
}
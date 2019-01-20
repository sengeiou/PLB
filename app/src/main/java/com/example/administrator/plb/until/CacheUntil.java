package com.example.administrator.plb.until;

import android.content.Context;
import android.content.SharedPreferences;

public class CacheUntil {
    private static Context context;
    private static SharedPreferences preferences;
    public static void putBoolean(Context c,String key,Boolean isAuto){
         if(context==null){
             context=c;
             preferences=context.getSharedPreferences("cache",Context.MODE_PRIVATE);
         }
         preferences.edit().putBoolean(key,isAuto).commit();
    }
    public static Boolean getBoolean(Context c,String key,Boolean def){
         if(context==null){
             context=c;
             preferences=context.getSharedPreferences("cache",Context.MODE_PRIVATE);
         }
         return preferences.getBoolean(key,def);
    }
    public static void putString(Context c,String key,String value){
         if(context==null){
             context=c;
             preferences=context.getSharedPreferences("cache",Context.MODE_PRIVATE);
         }
         preferences.edit().putString(key,value).commit();
    }
    public static String getString(Context c,String key,String def){
         if(context==null){
             context=c;
             preferences=context.getSharedPreferences("cache",Context.MODE_PRIVATE);
         }
       return preferences.getString(key,def);
    }
    public static void removeString(Context c,String key,String def){
         if(context==null){
             context=c;
             preferences=context.getSharedPreferences("cache",Context.MODE_PRIVATE);
         }
        preferences.edit().remove(key).commit();

    }
}

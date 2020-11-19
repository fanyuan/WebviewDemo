package com.example.webviewdemo;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class Tools {
    /**
     * 日志打印
     * @param msg
     */
    public static void log(String msg) {
        Log.d("ddebug",msg);
    }

    public static void toast(Context c, String msg) {
        Toast.makeText(c,msg,Toast.LENGTH_LONG).show();
        Activity a = null;
    }
    public static void attFrag(Activity a){
        //a.getFragmentManager().beginTransaction().attach(new ToolFragment()).commit();
        a.getFragmentManager().beginTransaction().add(new ToolFragment(),"test").commit();
    }
}

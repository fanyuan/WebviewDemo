package com.example.webviewdemo;

import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class JavascriptCallback {
    private Context context;

    public JavascriptCallback(Context context) {
        this.context = context;
    }
    @JavascriptInterface
    public void test(){
        Toast.makeText(context,"本地方法test被调用",Toast.LENGTH_SHORT).show();
        Log.d("ddebug","本地方法test被调用");
    }

    @JavascriptInterface
    public String  test1(String str){
        Toast.makeText(context,"带参数本地方法被调用" + str,Toast.LENGTH_SHORT).show();
        Log.d("ddebug","带参数本地方法被调用" + str);
        return "带参数本地方法被调用";
    }

    @JavascriptInterface
    public void test2(String parm){

        Log.d("ddebug", parm);
    }
    @JavascriptInterface
    public void test3(String parm){

        Log.d("ddebug", parm);;
    }
    @JavascriptInterface
    public void test4(String parm){

        Log.d("ddebug", parm);
    }

}

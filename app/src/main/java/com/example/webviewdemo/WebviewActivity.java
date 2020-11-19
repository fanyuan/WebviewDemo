package com.example.webviewdemo;

import android.content.Intent;
import android.os.Debug;
import android.os.Environment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class WebviewActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        initWebview();
        loadLocal();
    }
    private void startMethodTracing(){
        File f = new File(Environment.getExternalStorageDirectory()+File.separator+"tmp","demo.trace");
        if(f.exists()){
            f.delete();
        }
        Debug.startMethodTracing(f.getAbsolutePath());
        Log.d("ddebug","f = " + f.getAbsolutePath());

        Debug.stopMethodTracing();
    }

    private void initWebview() {
        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        webView.addJavascriptInterface(new JavascriptCallback(this),"javascriptCallback");
        webView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;//super.shouldOverrideUrlLoading(view, url);
            }
        });
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onJsAlert(WebView view, String url, final String message, final JsResult result) {
                toast( message);
                Log.d("ddebug","onJsAlert " + message);
                result.cancel();
                return true;
            }
            //设置响应js 的Confirm()函数
            @Override
            public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) {

                return true;
            }
            //设置响应js 的Prompt()函数
            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, final JsPromptResult result) {

                return true;
            }
        });
    }

    private void loadLocal() {
        String str = "file:///android_asset/test.html";
//        String path = Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"tmp"+ File.separator +
//                "html" + File.separator +"GitHub.html";
//        File file = new File(path);
//        Tools.log(file.getAbsolutePath()+ " --- " + file.exists());
//        path = "file://" + path;
//        Tools.log( "path --- " + path);
        webView.loadUrl(str);
    }

    public void callJs(String msg){
        webView.loadUrl("javascript:alertMsg('" + msg+ "')");
    }
    public void toast(String click) {
        Toast.makeText(this,click,Toast.LENGTH_SHORT).show();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("ddebug","---onActivityResult---");
        /**
         * 处理银联云闪付手机支付控件返回的支付结果
         */
        if (data == null) {
            return;
        }

        String msg = "";
        /*
         * 支付控件返回字符串:success、fail、cancel 分别代表支付成功，支付失败，支付取消
         */
        String str = data.getExtras().getString("pay_result");
        if (str.equalsIgnoreCase("success")) {
            //如果想对结果数据校验确认，直接去商户后台查询交易结果，
            //校验支付结果需要用到的参数有sign、data、mode(测试或生产)，sign和data可以在result_data获取到
            /**
             * result_data参数说明：
             * sign —— 签名后做Base64的数据
             * data —— 用于签名的原始数据
             *      data中原始数据结构：
             *      pay_result —— 支付结果success，fail，cancel
             *      tn —— 订单号
             */
            msg = "云闪付支付成功";
        } else if (str.equalsIgnoreCase("fail")) {
            msg = "云闪付支付失败！";
        } else if (str.equalsIgnoreCase("cancel")) {
            msg = "用户取消了云闪付支付";
        }
        callJs(msg);
    }
}

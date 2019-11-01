package com.baozi.demo.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.webkit.JsResult;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.baozi.demo.R;
import com.baozi.treerecyclerview.adpater.TreeRecyclerAdapter;

public class TBAtivity extends AppCompatActivity {
    private TreeRecyclerAdapter adapter = new TreeRecyclerAdapter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tb_home);
//        WebView content = findViewById(R.id.content);
//        WebSettings settings = content.getSettings();
//        settings.setJavaScriptEnabled(true);
//        settings.setPluginState(WebSettings.PluginState.ON);
//        settings.setMediaPlaybackRequiresUserGesture(false);
//        content.setWebViewClient(new WebViewClient() {
//
//        });
//        content.setWebChromeClient(new WebChromeClient() {
//            @Override
//            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
//                Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
//                return true;
//            }
//            // This method is called when the web content is requesting permission to access some
//            // resources.
//            //当网页内容被请求允许访问某些资源，调用此方法。
//            @Override
//            public void onPermissionRequest(PermissionRequest request) {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    ActivityCompat.requestPermissions(TBAtivity.this,
//                            new String[]{Manifest.permission.CAMERA},
//                            100);
//                    request.grant(request.getResources());
//                }
//            }
//        });
//        content.loadUrl("file:///android_asset/test.html");
    }
}

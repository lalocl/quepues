package com.wordpress.appsandroidsite.quepues.activity;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import com.wordpress.appsandroidsite.quepues.R;

/**
 * Created by laura on 15/04/2016.
 */
public class WebViewActivity  extends Activity {

    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.webview);
        url=getIntent().getStringExtra("url");

        WebView myWebView = (WebView)this.findViewById(R.id.webView);
        myWebView.loadUrl(url);

    }

}

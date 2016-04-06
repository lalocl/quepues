package com.wordpress.appsandroidsite.quepues.activity;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.wordpress.appsandroidsite.quepues.R;

/**
 * Created by laura on 05/04/2016.
 */
public class WebViewActivity extends Activity {
    private WebView browser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado);

        browser = (WebView) findViewById(R.id.webkit);

        //habilitamos javascript y el zoom
        browser.getSettings().setJavaScriptEnabled(true);
        browser.getSettings().setBuiltInZoomControls(true);

        //habilitamos los plugins (flash)
     //   browser.getSettings().setPluginsEnabled(true);

        browser.loadUrl("http://http://aula10formacion.com/");

        browser.setWebViewClient(new WebViewClient() {
            // evita que los enlaces se abran fuera nuestra app en el navegador de android
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

        });
    }
}
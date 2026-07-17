package com.garudaxyber.youtubego.osp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WebView webView = new WebView(this);
        WebSettings webSettings = webView.getSettings();

        // Optimasi Performa
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        
        // Identitas OSP (Anti-Bloat)
        webSettings.setUserAgentString("GarudaXyber-OSP/1.0 (Low-End Optimized)");

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                // Injeksi JS untuk membuang elemen berat dan iklan
                String hideScript = "javascript:(function() { " +
                        "var elements = document.querySelectorAll('.header, .footer, .ad-container, .yt-header');" +
                        "for (var i = 0; i < elements.length; i++) { elements[i].style.display='none'; }" +
                        "})()";
                view.loadUrl(hideScript);
            }
        });

        // Load entry point
        webView.loadUrl("https://m.youtube.com");
        setContentView(webView);
    }

    @Override
    public void onBackPressed() {
        // WebView handling agar tidak langsung keluar app
        WebView wv = (WebView) findViewById(android.R.id.content).getChildAt(0);
        if (wv.canGoBack()) {
            wv.goBack();
        } else {
            super.onBackPressed();
        }
    }
}

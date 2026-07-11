package com.axedolf.lexiquecmdpowershell;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {
    private static final String APP_URL = "https://axedolf.github.io/lexique-cmd-powershell/?app=android-v4-2-clickfix";
    private WebView webView;

    @Override
    @SuppressLint("SetJavaScriptEnabled")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setStatusBarColor(Color.rgb(2, 6, 23));
        getWindow().setNavigationBarColor(Color.rgb(2, 6, 23));

        webView = new WebView(this);
        webView.setBackgroundColor(Color.rgb(2, 6, 23));
        webView.clearCache(true);
        setContentView(webView);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);

        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                webView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                if (request.isForMainFrame()) {
                    showOfflinePage();
                }
            }
        });

        if (hasNetwork()) {
            webView.loadUrl(APP_URL);
        } else {
            showOfflinePage();
        }
    }

    @Override
    public void onBackPressed() {
        if (webView != null && webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    private boolean hasNetwork() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        if (manager == null) return false;
        NetworkInfo info = manager.getActiveNetworkInfo();
        return info != null && info.isConnected();
    }

    private void showOfflinePage() {
        String html = "<!doctype html><html lang=\"fr\"><head><meta charset=\"utf-8\"><meta name=\"viewport\" content=\"width=device-width,initial-scale=1\">" +
            "<style>body{margin:0;background:#020617;color:#e2e8f0;font-family:system-ui,Segoe UI,sans-serif;display:grid;min-height:100vh;place-items:center;padding:24px}main{max-width:420px}h1{font-size:24px;margin:0 0 12px}p{line-height:1.6;color:#94a3b8}.btn{display:inline-block;margin-top:16px;background:#0284c7;color:white;padding:12px 16px;border-radius:8px;text-decoration:none;font-weight:700}</style></head>" +
            "<body><main><h1>Lexique indisponible hors connexion</h1><p>Connecte ton telephone a Internet puis relance l'application. La version web GitHub Pages reste la source principale.</p>" +
            "<a class=\"btn\" href=\"" + APP_URL + "\">Reessayer</a></main></body></html>";
        webView.loadDataWithBaseURL(APP_URL, html, "text/html", "UTF-8", null);
    }
}

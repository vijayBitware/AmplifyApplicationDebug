package com.amplify.view.Activity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.amplify.R;
import com.amplify.view.MyApplication;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class ContactUsActivity extends AppCompatActivity {
    ImageView imgBack;
    WebView webView;
    ProgressDialog dialog;
    TextView txtHeading;
    private Tracker mTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        init();
    }

    private void init() {
        MyApplication application = (MyApplication) getApplication();
        mTracker = application.getDefaultTracker();
        txtHeading = findViewById(R.id.txtHeading);
        imgBack = findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        webView = findViewById(R.id.webView);
        txtHeading.setText("Contact Us");
        String url = "https://www.amplifymeapp.com/contact-us/";
        dialog =new ProgressDialog(ContactUsActivity.this);
        dialog.setMessage("Loading..");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.show();


        webView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            }
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon)
            {}

            @Override
            public void onPageFinished(WebView view, String url) {
                dialog.dismiss();
                super.onPageFinished(view,url);

            }

        });

        webView.loadUrl(url);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTracker.setScreenName("ContactUsActivity");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }
}

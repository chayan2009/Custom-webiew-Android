package com.withjetpack.custom_webview_android;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new WebAppInterface(this), "Android");

        // Improved HTML with vertically stacked buttons
        String htmlData = "<html>" +
                "<head>" +
                "<style>" +
                "body { font-family: Arial, sans-serif; text-align: center; margin-top: 50px; }" +
                ".button-container { display: flex; flex-direction: column; align-items: center; gap: 20px; }" +
                "button { padding: 15px 30px; font-size: 16px; background-color: #4CAF50; color: white; border: none; border-radius: 5px; cursor: pointer; width: 200px; }" +
                "button:hover { background-color: #45a049; }" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class='button-container'>" +
                "<button onclick=\"openActivity1()\">Open Activity 1</button>" +
                "<button onclick=\"openActivity2()\">Open Activity 2</button>" +
                "</div>" +
                "<script type=\"text/javascript\">" +
                "function openActivity1() {" +
                "    Android.openActivity1();" +
                "}" +
                "function openActivity2() {" +
                "    Android.openActivity2();" +
                "}" +
                "</script>" +
                "</body>" +
                "</html>";

        webView.setWebViewClient(new WebViewClient());
        webView.loadDataWithBaseURL(null, htmlData, "text/html", "UTF-8", null);
    }

    public class WebAppInterface {
        Context mContext;

        WebAppInterface(Context c) {
            mContext = c;
        }

        @JavascriptInterface
        public void openActivity1() {
            Intent intent = new Intent(mContext, MainActivity2.class);
            startActivity(intent);
        }

        @JavascriptInterface
        public void openActivity2() {
            Intent intent = new Intent(mContext, MainActivity2.class);
            startActivity(intent);
        }
    }
}



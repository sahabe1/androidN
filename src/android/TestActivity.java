/*
 Copyright 2014 Giovanni Di Gregorio.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */

package com.purchasingpower.inappbrowser;
import android.app.Activity;
import android.util.Log;
import android.webkit.WebView;
import android.os.Bundle;
import android.content.Intent;



import android.view.View;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextView;


public class TestActivity extends Activity {
    ImageView back;
    ImageView forward;
    ProgressDialog dialog;
    TextView done;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            //setContentView(R.layout.activity_main);
        setContentView(getResources().getIdentifier("nativebrowserplugin", "layout", getPackageName()));
        Intent intent = getIntent();
        String url = intent.getExtras().getString("url");
        final WebView myWebView = (WebView) findViewById(getResources().getIdentifier("webview", "id", getPackageName()));
        myWebView.setWebViewClient(new MyWebViewClient());
        myWebView.loadUrl(url);

        dialog = ProgressDialog.show(TestActivity.this, "", "Loading...", true);

        back = (ImageView) findViewById(getResources().getIdentifier("back", "id", getPackageName()));
        back.setImageResource(getResources().getIdentifier("back_gray", "drawable", getPackageName()));
        forward = (ImageView) findViewById(getResources().getIdentifier("forward", "id", getPackageName()));
        forward.setImageResource(getResources().getIdentifier("forward_gray", "drawable", getPackageName()));
        done = (TextView) findViewById(getResources().getIdentifier("done", "id", getPackageName()));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myWebView.canGoBack()){
                    myWebView.goBack();
                }
            }
        });
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myWebView.canGoForward()){
                    myWebView.goForward();
                }
            }
        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("Done","Clicked");
                finish();
            }
        });
    }
    private class MyWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            dialog.show();
            back.setEnabled(view.canGoBack());
            forward.setEnabled(view.canGoForward());
            if (view.canGoBack()){
                back.setImageResource(getResources().getIdentifier("back_blue", "drawable", getPackageName()));
            }
            else
              {
                back.setImageResource(getResources().getIdentifier("back_gray", "drawable", getPackageName()));
              }

            if (view.canGoForward()){
                forward.setImageResource(getResources().getIdentifier("forward_blue", "drawable", getPackageName()));
            }
            else
              {
                forward.setImageResource(getResources().getIdentifier("forward_gray", "drawable", getPackageName()));
              }

                //You can add some custom functionality here
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            dialog.dismiss();
            back.setEnabled(view.canGoBack());
            forward.setEnabled(view.canGoForward());
            if (view.canGoBack()){
                back.setImageResource(getResources().getIdentifier("back_blue", "drawable", getPackageName()));
            }
            else
              {
                back.setImageResource(getResources().getIdentifier("back_gray", "drawable", getPackageName()));
              }

            if (view.canGoForward()){
                forward.setImageResource(getResources().getIdentifier("forward_blue", "drawable", getPackageName()));
            }
            else
              {
                forward.setImageResource(getResources().getIdentifier("forward_gray", "drawable", getPackageName()));
              }
                //You can add some custom functionality here
        }
        
        
    }
}





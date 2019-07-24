package com.example.httpdemo.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;

import com.example.httpdemo.R;
import com.example.httpdemo.http.util.HttpURLConnectionUtil;


public class HttpUrlConnectionTest extends AppCompatActivity {
    TextView textView;
    MyHandler myHandler=new MyHandler();
    private String result;
    final String url = HttpURLConnectionUtil.BASE_URL + "DaiBanShiJian";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_url_connection_test);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                try{
                    result = HttpURLConnectionUtil.getContextByHttp(url);
                }
                catch (Exception e)
                {
                    result="error";
                }
                Message msg = myHandler.obtainMessage();
                msg.what = 21;
                msg.obj = result;
                myHandler.sendMessage(msg);
            }
        }).start();

        textView=(TextView)findViewById(R.id.txt);

    }
    public class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case 21:
                    String result=String.valueOf(msg.obj );
                    textView.setText(result);
                    break;

                default:
                    break;
            }
        }
    }
}

package com.example.httpdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.httpdemo.test.HttpUrlConnectionTest;
import com.example.httpdemo.test.OkHttpTest;
//https://blog.csdn.net/itachi85/article/details/51190687
public class MainActivity extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //HttpUrlConnection网络连接测试
//        intent = new Intent(MainActivity.this, HttpUrlConnectionTest.class);
        //OkHttp网络连接测试
        intent = new Intent(MainActivity.this, OkHttpTest.class);

        startActivity(intent);
    }
}

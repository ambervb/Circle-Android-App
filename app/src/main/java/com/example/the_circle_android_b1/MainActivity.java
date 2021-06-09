package com.example.the_circle_android_b1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.se.omapi.Session;
import android.view.View;

import com.example.the_circle_android_b1.activities.CameraPreviewActivity;
import com.example.the_circle_android_b1.activities.ChatActivity;

public class MainActivity extends AppCompatActivity {
    private Session mSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void cameraPreviewActivity(View view){
        Intent intent = new Intent(MainActivity.this, CameraPreviewActivity.class);
        startActivity(intent);
    }
    public void chatActivity(View view){
        Intent intent = new Intent(MainActivity.this, ChatActivity.class);
        startActivity(intent);
    }

}
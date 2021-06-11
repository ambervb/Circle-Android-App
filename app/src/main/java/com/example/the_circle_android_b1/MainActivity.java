package com.example.the_circle_android_b1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;



import com.example.the_circle_android_b1.activities.CameraPreviewActivity;
import com.example.the_circle_android_b1.activities.ChatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_preview);


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
package com.example.the_circle_android_b1;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.pedro.rtplibrary.rtmp.RtmpCamera2;
import com.pedro.rtplibrary.view.OpenGlView;

import net.ossrs.rtmp.ConnectCheckerRtmp;


public class CameraActivity extends AppCompatActivity implements ConnectCheckerRtmp, SurfaceHolder.Callback, View.OnClickListener,  View.OnTouchListener{

    private RtmpCamera2 rtmpCamera2;
    private Button recordButton;
    private Button switchButton;
    private OpenGlView surfaceGL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_camera_preview);

            recordButton = findViewById(R.id.buttonRecord);
            switchButton = findViewById(R.id.buttonSwitch);


            surfaceGL = findViewById(R.id.surfaceView);


            rtmpCamera2 = new RtmpCamera2(surfaceGL, this);

            recordButton.setOnClickListener(this);
            switchButton.setOnClickListener(this);

            surfaceGL.getHolder().addCallback(this);
            surfaceGL.setOnTouchListener(this);




        }


    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        //later
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
        rtmpCamera2.startPreview();
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        //later
    }

    @Override
    public void onConnectionSuccessRtmp() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(CameraActivity.this, "Connection success", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onConnectionFailedRtmp(String reason) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(CameraActivity.this, "Connection failed. " + reason, Toast.LENGTH_SHORT)
                        .show();
                rtmpCamera2.stopStream();

            }
        });
    }

    @Override
    public void onNewBitrateRtmp(long bitrate) {
        //later
    }

    @Override
    public void onDisconnectRtmp() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(CameraActivity.this, "Disconnected", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onAuthErrorRtmp() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(CameraActivity.this, "Auth error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onAuthSuccessRtmp() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(CameraActivity.this, "Auth success", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
         Toast.makeText(CameraActivity.this, "clicked", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        //later
        return true;
    }


}
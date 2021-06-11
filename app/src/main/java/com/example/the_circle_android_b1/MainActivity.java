package com.example.the_circle_android_b1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.the_circle_android_b1.activities.CameraPreviewActivity;
import com.example.the_circle_android_b1.activities.ChatActivity;
import com.pedro.encoder.input.video.CameraHelper;
import com.pedro.rtplibrary.rtmp.RtmpCamera1;

import net.ossrs.rtmp.ConnectCheckerRtmp;

public class MainActivity extends AppCompatActivity implements ConnectCheckerRtmp, SurfaceHolder.Callback {
    private RtmpCamera1 rtmpCamera1;
    private Button button;
    private SurfaceView surfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_preview);

        surfaceView = findViewById(R.id.surfaceView);
        rtmpCamera1 = new RtmpCamera1(surfaceView, this);
        surfaceView.getHolder().addCallback(this);
    }

    public void onConnectionStartedRtmp(String rtmpUrl) {
    }

    @Override
    public void onConnectionSuccessRtmp() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "Connection success", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onConnectionFailedRtmp(final String reason) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (rtmpCamera1.reTry(5000, "reason")) {
                    Toast.makeText(MainActivity.this, "Retry", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    Toast.makeText(MainActivity.this, "Connection failed. " + reason, Toast.LENGTH_SHORT)
                            .show();
                    rtmpCamera1.stopStream();

                }
            }
        });
    }


    @Override
    public void onNewBitrateRtmp(long bitrate) {

    }

    @Override
    public void onDisconnectRtmp() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "Disconnected", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onAuthErrorRtmp() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "Auth error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onAuthSuccessRtmp() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "Auth success", Toast.LENGTH_SHORT).show();
            }
        });
    }



    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        rtmpCamera1.startPreview(CameraHelper.Facing.BACK);
    }
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

        rtmpCamera1.stopPreview();
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
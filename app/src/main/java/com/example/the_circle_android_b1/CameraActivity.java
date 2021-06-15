package com.example.the_circle_android_b1;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.pedro.encoder.input.video.CameraHelper;
import com.pedro.encoder.input.video.CameraOpenException;
import com.pedro.rtplibrary.rtmp.RtmpCamera2;
import com.pedro.rtplibrary.view.OpenGlView;
import net.ossrs.rtmp.ConnectCheckerRtmp;


public class CameraActivity extends AppCompatActivity implements ConnectCheckerRtmp, SurfaceHolder.Callback, View.OnClickListener{

    private RtmpCamera2 rtmpCamera2;
    private Button recordButton;
    private Button switchButton;
    private OpenGlView surfaceGL;
    private String streamUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_camera_preview);

            recordButton = findViewById(R.id.buttonRecord);
            switchButton = findViewById(R.id.buttonSwitch);
            surfaceGL = findViewById(R.id.surfaceView);

            //TODO Change to real url when available
//          Give url for server to connect to. 1935 is port for rtmp
//          remote:
//          streamUrl = "rtmp://159.65.202.252:1935/live/test";
//          local:
            streamUrl = "rtmp://192.168.178.13:1935/live/test";

            rtmpCamera2 = new RtmpCamera2(surfaceGL, this);

            recordButton.setOnClickListener(this);
            switchButton.setOnClickListener(this);

            surfaceGL.getHolder().addCallback(this);
        }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonRecord:
                if (!rtmpCamera2.isStreaming()) {
                    int cRotation = CameraHelper.getCameraOrientation(this);
                    if (rtmpCamera2.prepareVideo(720, 1280, 30, 1331200, cRotation, cRotation, 1, 1 )) {
                        recordButton.setText("NOW RECORDING");
                        rtmpCamera2.startStream(streamUrl);
                    } else {
                        Toast.makeText(this, "Error preparing stream, This device cant do it",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    recordButton.setText(R.string.record_button);
                    rtmpCamera2.stopStream();
                }
                break;

            case R.id.buttonSwitch:
                try {
                    rtmpCamera2.switchCamera();
                } catch (CameraOpenException e) {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

//
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





}
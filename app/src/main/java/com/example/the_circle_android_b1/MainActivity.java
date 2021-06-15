package com.example.the_circle_android_b1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {
    //NB: Give camera permissions manually for testing, feature will be added later

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO add main screen ui to go to camera, profile etc
        cameraActivity();

    }

    public void cameraActivity(){
        Intent cameraIntent = new Intent(this, CameraActivity.class);
        startActivity(cameraIntent);
    }

    public void chatActivity(View view){

    }

}
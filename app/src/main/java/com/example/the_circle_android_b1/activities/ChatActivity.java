package com.example.the_circle_android_b1.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.the_circle_android_b1.MainActivity;
import com.example.the_circle_android_b1.R;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
    }
    public void mainScreenActivity (View view){
        Intent intent = new Intent(ChatActivity.this, MainActivity.class);
        startActivity(intent);
    }
//    page for testing chat
}
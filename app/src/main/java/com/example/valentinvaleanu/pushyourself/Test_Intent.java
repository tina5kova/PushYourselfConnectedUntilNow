package com.example.valentinvaleanu.pushyourself;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Test_Intent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test__intent);

        Intent intent = getIntent();
        int info = intent.getIntExtra("info", 0);
    }
}

package com.example.valentinvaleanu.pushyourself;

/**
 * Created by Aleksandar on 13.11.2017 г..
 */



import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class ExerciseInfoLayout extends AppCompatActivity {

    private FloatingActionButton but;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_layout);

        Intent intent = getIntent();
        int picked = intent.getIntExtra("info", 0);

        TextView exName = (TextView) findViewById(R.id.ex_name);
        TextView p1 = (TextView) findViewById(R.id.desc_prg1);
        TextView p2 = (TextView) findViewById(R.id.desc_prg2);
        TextView p3 = (TextView) findViewById(R.id.desc_prg3);
        TextView p4 = (TextView) findViewById(R.id.desc_prg4);
        TextView p5 = (TextView) findViewById(R.id.desc_prg5);
        TextView c = (TextView) findViewById(R.id.caution);
        TextView t = (TextView) findViewById(R.id.tip);


        switch (picked){
            case 0:
                p1.setText(R.string.example_exercise);
                p2.setText(R.string.example_exercise);
                p3.setText(R.string.example_exercise);
                p4.setText(R.string.example_exercise);
                p5.setText(R.string.example_exercise);
                break;
            case 1:
                // needs further development to me implemented
        }


        but = (FloatingActionButton) findViewById(R.id.fab);
        but.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence text = "Exercise added to chedule! :)";

                Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    @Override
    protected void onStart() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        super.onStart();
    }
}

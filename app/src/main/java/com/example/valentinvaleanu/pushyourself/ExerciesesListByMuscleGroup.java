package com.example.valentinvaleanu.pushyourself;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Valentin Valeanu on 11/11/2017.
 */

public class ExerciesesListByMuscleGroup extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercises_list_layout);

        System.out.println("activity created");

        ListView listView = (ListView) findViewById(R.id.exercises_list);

        Bundle bundle = getIntent().getExtras();

        String muscleGroup = (String) bundle.get("muscle_group");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>());

        adapter.add(muscleGroup);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent newIntent;

                newIntent = new Intent(ExerciesesListByMuscleGroup.this, ExerciseInfoLayout.class);

                switch (i)
                {
                    case 0:
                        newIntent.putExtra("info", 0);
                        startActivity(newIntent);
                        break;
                    case 1:
                        // need further development to me implemented
                }
            }
        });
    }

    @Override
    protected void onStart() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        super.onStart();
    }

}


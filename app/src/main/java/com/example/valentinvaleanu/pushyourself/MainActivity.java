package com.example.valentinvaleanu.pushyourself;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity
{
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    private DrawerLayout mDrawerLayout;
    private ViewPager viewPager;

    private MenuItem prevMenuItem;
    private BottomNavigationView bottomNavigationView;
    private ExercisesFragment exercisesFragment;
    private ScheduleFragment scheduleFragment;
    private AchievementFragment achievementFragment;

    private ArrayList<Exercise> absExercises;
    private ArrayList<Exercise> legsExercises;
    private ArrayList<Exercise> armsExercises;
    private ArrayList<Exercise> backExercises;
    private ArrayList<Exercise> chestExercises;
    private ArrayList<Exercise> cardioExercises;

    private StorageReference mStorageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        loadFromFirebase();

        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_dehaze_white_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView n = (NavigationView) findViewById(R.id.navigation);

        n.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.drawer_menu_settings:
                        Toast.makeText(MainActivity.this, "Settings", Toast.LENGTH_SHORT).show();

                }
                mDrawerLayout.closeDrawers();  // CLOSE DRAWER
                return true;
            }
        });

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_exercises:

                                viewPager.setCurrentItem(0);

                                break;
                            case R.id.action_schedules:

                                viewPager.setCurrentItem(1);

                                break;
                            case R.id.action_achievements:

                                viewPager.setCurrentItem(2);

                                break;
                        }
                        return false;
                    }
                });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                }
                else
                {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setupViewPager(viewPager);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            // THIS IS YOUR DRAWER/HAMBURGER BUTTON
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);  // OPEN DRAWER
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public void openExercises(View view)
    {
        Intent newIntent;

        switch(view.getId()) {

            case R.id.arms_exercises:

                newIntent = new Intent(MainActivity.this, ExerciesesListByMuscleGroup.class);

                newIntent.putExtra("muscle_group", "arms");

                startActivity(newIntent);

                break;

            case R.id.chest_exercises:

                newIntent = new Intent(MainActivity.this, ExerciesesListByMuscleGroup.class);

                newIntent.putExtra("muscle_group", "chest");

                startActivity(newIntent);

                break;

            case R.id.abs_exercises:

                newIntent = new Intent(MainActivity.this, ExerciesesListByMuscleGroup.class);

                newIntent.putExtra("muscle_group", "abs");

                startActivity(newIntent);

                break;

            case R.id.back_exercises:

                newIntent = new Intent(MainActivity.this, ExerciesesListByMuscleGroup.class);

                newIntent.putExtra("muscle_group", "back");

                startActivity(newIntent);

                break;

            case R.id.legs_exercises:

                newIntent = new Intent(MainActivity.this, ExerciesesListByMuscleGroup.class);

                newIntent.putExtra("muscle_group", "legs");

                startActivity(newIntent);

                break;

            case R.id.cardio_exercises:

                newIntent = new Intent(MainActivity.this, ExerciesesListByMuscleGroup.class);

                newIntent.putExtra("muscle_group", "cardio");

                startActivity(newIntent);

                break;

        }
    }

    private void setupViewPager(ViewPager viewPager)
    {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        exercisesFragment = new ExercisesFragment();
        scheduleFragment = new ScheduleFragment();
        achievementFragment = new AchievementFragment();

        adapter.addFragment(exercisesFragment);
        adapter.addFragment(scheduleFragment);;
        adapter.addFragment(achievementFragment);

        viewPager.setAdapter(adapter);
    }

    public void loadFromFirebase()
    {
        absExercises = new ArrayList<Exercise>();
        armsExercises = new ArrayList<Exercise>();
        backExercises = new ArrayList<Exercise>();
        legsExercises = new ArrayList<Exercise>();
        chestExercises = new ArrayList<Exercise>();
        cardioExercises = new ArrayList<Exercise>();

        database = FirebaseDatabase.getInstance();

        readChildrenFromDataSnapshot(absExercises, "abs");
        readChildrenFromDataSnapshot(armsExercises, "arms");
        readChildrenFromDataSnapshot(backExercises, "back");
        readChildrenFromDataSnapshot(legsExercises, "legs");
        readChildrenFromDataSnapshot(chestExercises, "chest");
        readChildrenFromDataSnapshot(cardioExercises, "cardio");

        addSQLTable("abs_exercises", absExercises);
        addSQLTable("arms_exercises", armsExercises);
        addSQLTable("legs_exercises", armsExercises);
        addSQLTable("back_exercises", armsExercises);
        addSQLTable("chest_exercises", chestExercises);
        addSQLTable("cardio_exercises", cardioExercises);

    }

    public void addSQLTable(String tableName, ArrayList<Exercise> exercises)
    {
        // TO BE IMPLEMENTED
        ///
        ///
        ///
    }

    public void readChildrenFromDataSnapshot(final ArrayList<Exercise> exercises, String muscleGroup)
    {
        databaseReference = database.getReference("Exercises").child(muscleGroup);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {

                    exercises.add(childDataSnapshot.getValue(Exercise.class)); // cast to Exercise class
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}

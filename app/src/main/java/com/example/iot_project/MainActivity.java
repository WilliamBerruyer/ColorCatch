package com.example.iot_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);

    }
    Home homeFragment = new Home();
    Library libraryFragment = new Library();
    Profile profileFragment = new Profile();


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.home:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                return true;

            case R.id.library:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getSupportFragmentManager().beginTransaction().replace(R.id.container, libraryFragment).commit();
                return true;

            case R.id.profile:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getSupportFragmentManager().beginTransaction().replace(R.id.container, profileFragment).commit();
                return true;

        }
        return false;
    }

    @Override
    public void onBackPressed(){
        try {
            int backStackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
            Log.d("class", "items in backstack " + backStackEntryCount);
            if (backStackEntryCount > 0) {
                super.onBackPressed();
            } else if(bottomNavigationView.getSelectedItemId() == R.id.home && backStackEntryCount == 0){
                super.onBackPressed();
            }else {
                bottomNavigationView.setSelectedItemId(R.id.home);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}


/*
next_Activity_button = (Button) findViewById(R.id.first_activity_button);
        question1 = (TextView) findViewById(R.id.question1_id);

        question1.setText("Q1 - How to pass the data between activities in Android?\n" + "\n" + "Ans - Intent");

        // Add_button add clicklistener
        next_Activity_button.setOnClickListener(v -> {
        // Intents are objects of the android.content.Intent type. Your code can send them to the Android system defining
        // the components you are targeting. Intent to start an activity called SecondActivity with the following code
        Intent intent = new Intent(MainActivity.this, Library.class);
        // start the activity connect to the specified class
        startActivity(intent);
        });*/

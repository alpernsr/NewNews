package com.alperen.newnews;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new FragmentHome()).commit();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getTitle().equals("Profile")) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new FragmentPerson()).commit();
                } else {
                    getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new FragmentHome()).commit();
                }
                return true;
            }
        });
    }
}
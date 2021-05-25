package com.example.speedmath;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private ExtendedFloatingActionButton beginBTN;
    private ExtendedFloatingActionButton intermediateBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        beginBTN = (ExtendedFloatingActionButton) findViewById(R.id.basic_test);
        intermediateBTN = (ExtendedFloatingActionButton) findViewById(R.id.intermediate_test);

        beginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AdditionTest.class));
            }
        });

        intermediateBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SquaresActivity.class));
            }
        });
    }
}
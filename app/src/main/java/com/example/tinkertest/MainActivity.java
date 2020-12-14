package com.example.tinkertest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=findViewById(R.id.btn_update);
        Button iv=findViewById(R.id.btn_update);
        Button text=findViewById(R.id.btn_update);
        text.setText("132132131");
    }
}

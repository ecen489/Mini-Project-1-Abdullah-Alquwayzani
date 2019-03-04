package com.example.miniproject1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OnRev1(View view){
        Intent intent = new Intent(MainActivity.this,quiz.class);
        intent.putExtra("user","rev1");
        startActivity(intent);
    }
    public void OnRev2(View view){
        Intent intent = new Intent(MainActivity.this,quiz.class);
        intent.putExtra("user","rev2");
        startActivity(intent);
    }
    public void OnRev3(View view){
        Intent intent = new Intent(MainActivity.this,quiz.class);
        intent.putExtra("user","rev3");
        startActivity(intent);
    }
}

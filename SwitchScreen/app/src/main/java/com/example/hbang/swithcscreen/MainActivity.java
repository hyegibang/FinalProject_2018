package com.example.hbang.swithcscreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sketch = findViewById(R.id.sketch);
        Button evaluate = findViewById(R.id.evaluate);
        Button features = findViewById(R.id.features);
        Button something = findViewById(R.id.something);

        sketch.setOnClickListener(this);
        evaluate.setOnClickListener(this);
        features.setOnClickListener(this);
        something.setOnClickListener(this);

    }
  //      sketch.setOnClickListener(new View.OnClickListener() {
  @Override
  public void onClick(View V) {
        switch (V.getId()) {
            case R.id.sketch:
                opensketchin();
                break;
            case R.id.evaluate:
                openevaluate();
                break;
            case R.id.features:
                openfeature();
                break;
            case  R.id.something:
                opensomething();
                break;
                }
            };



    public void opensketchin() {
        Intent intent = new Intent(MainActivity.this, sketchin.class);
        startActivity(intent);
    }

    public void openevaluate() {
        Intent intent = new Intent(MainActivity.this, evaluatein.class);
        startActivity(intent);
    }

    public void openfeature() {
        Intent intent = new Intent(MainActivity.this, featurein.class);
        startActivity(intent);
    }

    public void opensomething() {
        Intent intent = new Intent(MainActivity.this, somethingin.class);
        startActivity(intent);
    }
}
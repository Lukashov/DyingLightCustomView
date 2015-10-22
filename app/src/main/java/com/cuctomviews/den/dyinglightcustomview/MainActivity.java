package com.cuctomviews.den.dyinglightcustomview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DyingLightProgressBar dyingLightProgressBar = (DyingLightProgressBar) findViewById(R.id.progress);

        dyingLightProgressBar.startAnim();
    }
}

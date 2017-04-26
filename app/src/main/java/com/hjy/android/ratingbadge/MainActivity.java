package com.hjy.android.ratingbadge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    RatingBadge ratingBadge;
    RatingBadge2 ratingBadge2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ratingBadge = (RatingBadge) findViewById(R.id.ratingBadge);
        ratingBadge2 = (RatingBadge2) findViewById(R.id.ratingBadge2);
    }

    int i = 1;

    public void onClick(View view) {
        ratingBadge.setRatingValue(i % 11);
        ratingBadge2.setRatingValue(i++ % 11);
    }
}

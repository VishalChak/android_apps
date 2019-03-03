package com.example.kovish;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

public class SecondActivity extends AppCompatActivity {

    private static final String TOTAL_COUNT = "total_count";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        showRandomNumber();
    }

    public void showRandomNumber(){
        TextView randomVIew = (TextView) findViewById(R.id.textview_random);
        TextView headingView = (TextView) findViewById(R.id.textview_label);

        int count = getIntent().getIntExtra(TOTAL_COUNT,0);
        int randomInt = 0;
        Random random = new Random();

        if (count > 0 ){
            randomInt = random.nextInt(count);
        }
        randomVIew.setText(Integer.toString(randomInt));
        headingView.setText(getString(R.string.random_heading, count));
    }


}

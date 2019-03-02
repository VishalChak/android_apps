package com.example.kovish;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toastMe(View view){
        Toast myToast = Toast.makeText(this, "Hello Toast", Toast.LENGTH_SHORT);
        myToast.show();
    }

    public void countMe(View view){

        TextView showCountTextView = (TextView) findViewById(R.id.textView);
        String countString = showCountTextView.getText().toString();

        Integer count = Integer.parseInt(countString);
        count++;

        //Toast myToast = Toast.makeText(this, String.valueOf(count), Toast.LENGTH_SHORT);
        //myToast.show();
        showCountTextView.setText(String.valueOf(count));
    }
}

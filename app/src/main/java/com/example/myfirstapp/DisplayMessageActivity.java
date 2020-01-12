package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.RequestQueue;

import org.w3c.dom.Text;

public class DisplayMessageActivity extends AppCompatActivity {
    private TextView internetTextView;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        // Get intent that start the activity
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // capture layout text view and set the string as text
        TextView textView = findViewById(R.id.textView);
        textView.setText(message);
    }
}

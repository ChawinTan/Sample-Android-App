package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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

        // view for strings from http
        internetTextView = findViewById((R.id.textView2));
        Button buttonParse = findViewById((R.id.button2));

        mQueue = Volley.newRequestQueue(this);

        buttonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("---------------------- button click ----------------------");
                jsonParse();
            }
        });
    }

    public void jsonParse() {
        System.out.println("---------------------- jsonparse called ----------------------");
        String url = "https://rackley-octopus-5384.twil.io/android-test";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
        new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response)  {
                try {
                    JSONArray jsonArray = response.getJSONArray("details");
                    System.out.println(jsonArray);
                    for (int i=0; i < jsonArray.length(); i++) {
                        JSONObject people = jsonArray.getJSONObject(i);

                        String name = people.getString("name");
                        String age = people.getString("age");
                        String salary = people.getString("salary");

                        internetTextView.append(name + " , " + age + ", " + salary + "\n\n");
                    }
                } catch(JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }
}

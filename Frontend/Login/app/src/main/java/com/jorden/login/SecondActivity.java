package com.jorden.login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.jorden.app.AppController;
import com.jorden.net_utils.Const;


public class SecondActivity extends AppCompatActivity {
    private TextView textShown;
    private Button showText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        showText = (Button) findViewById(R.id.show_text);
        textShown = (TextView) findViewById(R.id.textToShow);
        showText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SecondActivity.this,
                        StringRequestActivity.class));
            }
        });
    }
}

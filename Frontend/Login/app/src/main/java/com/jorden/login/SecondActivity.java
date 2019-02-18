package com.jorden.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class SecondActivity extends AppCompatActivity{
    private TextView textShown;
    private Button showText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        showText = (Button) findViewById(R.id.show_text);
        textShown = (TextView) findViewById(R.id.textToShow);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        showText.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, StringRequestActivity.class);
                startActivity(intent);
            }
        });
    }
}

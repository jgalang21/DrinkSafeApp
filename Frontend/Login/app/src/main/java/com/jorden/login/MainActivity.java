package com.jorden.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText Email;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Email = (EditText) findViewById(R.id.etemail);
        Password = (EditText) findViewById(R.id.etpass);
        Info = (TextView) findViewById(R.id.tvInfo);
        Login = (Button) findViewById(R.id.btnLogin);

        Login.setOnClickListener(new View.OnClickListener(){
           @Override
            public void onClick(View view) {
                validate(Email.getText().toString(), Password.getText().toString());
           }
        });
    }

    private void validate(String userEmail, String userPass){
        if((userEmail.equals("Admin")) && (userPass.equals("123Abc"))){
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);

        }else{
            counter--;
            Info.setText("Attempts left:" + String.valueOf(counter));
            if(counter == 5){
                Login.setEnabled(false);
            }
        }
    }
}

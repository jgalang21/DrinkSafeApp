package com.jorden.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.jorden.net_utils.Const;

import org.json.JSONArray;


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

        Info.setText("attempts left: 5");
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
            Info.setText("Attempts left: " + String.valueOf(counter));
            if(counter == 0){
                counter = 0;
                Login.setEnabled(false);
            }
        }
    }
    private void makeJsonArryReq() {
        showProgressDialog();
        JsonArrayRequest req = new JsonArrayRequest(Const.URL_USER_INFO/*URL_USER_INFO*/,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        msgResponse.setText(response.toString());
                        hideProgressDialog();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Log.d(TAG, error.getStackTrace() + "");
                Log.d(TAG, error.getLocalizedMessage() + "");
                hideProgressDialog();
            }
        });
}

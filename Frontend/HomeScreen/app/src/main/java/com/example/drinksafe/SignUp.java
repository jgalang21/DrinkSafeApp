package com.example.drinksafe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class SignUp extends AppCompatActivity {

    private EditText email, password, weight, heightIn, heightFt;
    private Switch maleSw, femSw;
    private Button signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        email = (EditText) findViewById(R.id.userEmail);
        password = (EditText) findViewById(R.id.UserPass);
        weight = (EditText) findViewById(R.id.userWeight);
        heightIn = (EditText) findViewById(R.id.height_in);
        heightFt = (EditText) findViewById(R.id.height_ft);
        maleSw = (Switch) findViewById(R.id.maleSex);
        femSw = (Switch) findViewById(R.id.femSex);
        signUp = (Button) findViewById(R.id.SignUpbtn);
    }
    //height conversion from ProfileScreen
    private void heightConv(int h, int[] h_arr, boolean convert) {
        if(convert) {
            h_arr[0] = h / 12;
            h_arr[1] = h % 12;
        } else {
            int temp = 0;
            temp += h_arr[0] * 12;
            temp += h_arr[1];
            h = temp;
        }
    }
}

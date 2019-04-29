package com.example.drinksafe;
/**
 * @author Jorden Lee
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.drinksafe.app.AppController;

import static com.example.drinksafe.net_utils.Const.URL_DRINK_INFO;
import static com.example.drinksafe.net_utils.Const.cur_user_name;

public class DrinkAdd extends AppCompatActivity {
    private RadioGroup radioGroup;
    private RadioGroup radioGroup32;
    private RadioButton radioButton16;
    private RadioButton radioButton32;
    private Button scSingle;
    private Button scDouble;
    private Button jdSingle;
    private Button jdDouble;
    private Button vSingle;
    private Button vDouble;
    private Button wSingle;
    private Button wDouble;
    private Button rSingle;
    private Button rDouble;
    private Button beer16;
    private Button beer32;
    private String tempURL = URL_DRINK_INFO;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_add);

        radioGroup = findViewById(R.id.radioGroup16oz);
        radioGroup32 = findViewById(R.id.radioGroup32oz);
        scSingle = findViewById(R.id.sgSingle);
        scSingle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    tempURL += "/new/Seagrams_Gin/40/2/"+cur_user_name;
                    JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, tempURL, null, null, null);
                    AppController.getInstance().addToRequestQueue(req);
                    Toast.makeText(getApplicationContext(), "Added 2oz of Seagram's Gin", Toast.LENGTH_LONG).show();

                }
                catch(Exception e){
                }
            }
        });
        scDouble = findViewById(R.id.sgDouble);
        scDouble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    tempURL += "/new/Seagrams_Gin/40/4/"+cur_user_name;
                    JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, tempURL, null, null, null);
                    AppController.getInstance().addToRequestQueue(req);
                    Toast.makeText(getApplicationContext(), "Added 4oz of Seagram's Gin", Toast.LENGTH_LONG).show();

                }
                catch(Exception e){
                }
            }
        });
        jdSingle = findViewById(R.id.jdSingle);
        jdSingle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    tempURL += "/new/Jack_Daniels/40/2/"+cur_user_name;
                    JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, tempURL, null, null, null);
                    AppController.getInstance().addToRequestQueue(req);
                    Toast.makeText(getApplicationContext(), "Added 2oz of Jack Daniels", Toast.LENGTH_LONG).show();

                }
                catch(Exception e){
                }
            }
        });
        jdDouble = findViewById(R.id.jdDouble);
        jdDouble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    tempURL += "/new/Jack_Daniels/40/4/"+cur_user_name;
                    JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, tempURL, null, null, null);
                    AppController.getInstance().addToRequestQueue(req);
                    Toast.makeText(getApplicationContext(), "Added 4oz of Jack Daniels", Toast.LENGTH_LONG).show();
                }
                catch(Exception e){
                }
            }
        });
        vSingle = findViewById(R.id.vodkaSingle);
        vSingle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    tempURL += "/new/vodka/35/2/"+cur_user_name;
                    JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, tempURL, null, null, null);
                    AppController.getInstance().addToRequestQueue(req);
                    Toast.makeText(getApplicationContext(), "Added 2oz of vodka", Toast.LENGTH_LONG).show();

                }
                catch(Exception e){
                }
            }
        });
        vDouble = findViewById(R.id.vodkaDouble);
        vDouble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    tempURL += "/new/vodka/35/4/"+cur_user_name;
                    JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, tempURL, null, null, null);
                    AppController.getInstance().addToRequestQueue(req);
                    Toast.makeText(getApplicationContext(), "Added 4oz of vodka", Toast.LENGTH_LONG).show();

                }
                catch(Exception e){
                }
            }
        });
        wSingle = findViewById(R.id.whiskeySingle);
        wSingle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    tempURL += "/new/Crown_Royal/35/2/"+cur_user_name;
                    JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, tempURL, null, null, null);
                    AppController.getInstance().addToRequestQueue(req);
                    Toast.makeText(getApplicationContext(), "Added 2oz of Crown Royal", Toast.LENGTH_LONG).show();

                }
                catch(Exception e){
                }
            }
        });
        wDouble = findViewById(R.id.whiskeyDouble);
        wDouble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    tempURL += "/new/Crown_Royal/35/4/"+cur_user_name;
                    JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, tempURL, null, null, null);
                    AppController.getInstance().addToRequestQueue(req);
                    Toast.makeText(getApplicationContext(), "Added 4oz of Crown Royal", Toast.LENGTH_LONG).show();

                }
                catch(Exception e){
                }
            }
        });
        rSingle = findViewById(R.id.rumSingle);
        rSingle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    tempURL += "/new/Captain_Morgan/35/2/"+cur_user_name;
                    JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, tempURL, null, null, null);
                    AppController.getInstance().addToRequestQueue(req);
                    Toast.makeText(getApplicationContext(), "Added 2oz of Captain Morgan", Toast.LENGTH_LONG).show();

                }
                catch(Exception e){
                }
            }
        });
        rDouble = findViewById(R.id.rumDouble);
        rDouble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    tempURL += "/new/Captain_Morgan/35/4/"+cur_user_name;
                    JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, tempURL, null, null, null);
                    AppController.getInstance().addToRequestQueue(req);
                    Toast.makeText(getApplicationContext(), "Added 4oz of Captain Morgan", Toast.LENGTH_LONG).show();

                }
                catch(Exception e){
                }
            }
        });
        beer16 = findViewById(R.id.button_add_beer16);
        beer16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton16 = findViewById(radioId);

            }
        });
        beer32 = findViewById(R.id.button_add_beer32);
        beer32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton32 = findViewById(radioId);

            }
        });
    }
    //check radiobuttons for 16oz
    public void checkButton(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();

        radioButton16 = findViewById(radioId);

        Toast.makeText(this, "Selected Radio Button: " + radioButton16.getText(),
                Toast.LENGTH_SHORT).show();
    }
    //check the radiobuttons for 32oz
    public void checkButton2(View v) {
        int radioId = radioGroup32.getCheckedRadioButtonId();

        radioButton32 = findViewById(radioId);

        Toast.makeText(this, "Selected Radio Button: " + radioButton32.getText(),
                Toast.LENGTH_SHORT).show();
    }
}

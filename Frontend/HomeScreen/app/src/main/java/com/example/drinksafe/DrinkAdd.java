package com.example.drinksafe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class DrinkAdd extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioGroup radioGroup32;
    RadioButton radioButton16;
    RadioButton radioButton32;
    Button vSingle;
    Button vDouble;
    Button wSingle;
    Button wDouble;
    Button rSingle;
    Button rDouble;
    Button beer16;
    Button beer32;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_add);

        radioGroup = findViewById(R.id.radioGroup16oz);
        radioGroup32 = findViewById(R.id.radioGroup32oz);
        vSingle = findViewById(R.id.vodkaSingle);
        vDouble = findViewById(R.id.vodkaDouble);
        wSingle = findViewById(R.id.whiskeySingle);
        wDouble = findViewById(R.id.whiskeyDouble);
        rSingle = findViewById(R.id.rumSingle);
        rDouble = findViewById(R.id.rumDouble);
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

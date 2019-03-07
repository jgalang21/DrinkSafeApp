package com.example.drinksafe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import com.example.drinksafe.net_utils.Const;
import com.example.drinksafe.app.AppController;

public class ProfileScreen extends AppCompatActivity {
    private EditText name_box, weight_box, email_box;
    private Spinner gender_s, feet_s, inches_s;
    private String name, email;
    private int weight, height, gender;
    private int[] height_arr = new int[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);

        name_box = findViewById(R.id.name_edit_box);
        weight_box = findViewById(R.id.weight_edit_box);
        email_box = findViewById(R.id.email_edit_box);

        name_box.setEnabled(false);
        weight_box.setEnabled(false);
        email_box.setEnabled(false);

        gender_s = (Spinner) findViewById(R.id.sex_spin);
        feet_s = (Spinner) findViewById(R.id.feet_spin);
        inches_s = (Spinner) findViewById(R.id.incehs_spin);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.sex_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        gender_s.setAdapter(adapter1);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.height_feet_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        feet_s.setAdapter(adapter2);

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.height_inches_array, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inches_s.setAdapter(adapter3);

        gender_s.setEnabled(false);
        feet_s.setEnabled(false);
        inches_s.setEnabled(false);

        ImageButton b_back = findViewById(R.id.back_button);
        b_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfileScreen.this, MainActivity.class);
                startActivity(i);
            }
        });

        final Button b = findViewById(R.id.save_edit_button);
        b.setTag(1);
        b.setText(R.string.edit_t);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int status =(Integer) v.getTag();
                if(status == 1) {
                    b.setText(R.string.save_t);
                    name_box.setEnabled(true);
                    weight_box.setEnabled(true);
                    email_box.setEnabled(true);
                    gender_s.setEnabled(true);
                    feet_s.setEnabled(true);
                    inches_s.setEnabled(true);
                    v.setTag(0);
                } else {
                    b.setText(R.string.edit_t);
                    name_box.setEnabled(false);
                    weight_box.setEnabled(false);
                    email_box.setEnabled(false);
                    gender_s.setEnabled(false);
                    feet_s.setEnabled(false);
                    inches_s.setEnabled(false);
                    v.setTag(1);
                }
            }
        });

        getInfo(this.name, this.email, this.weight, this.height, this.gender);

        heightConv(this.height, this.height_arr, true);
    }

    private void getInfo(String name, String email, int weight, int height, int gender) {

    }

    /**
     * Simple helper method to convert the height measurement from inches to feet and inches
     * @param h height in inches
     * @param h_arr array used to store height
     * @param convert boolean to indicate which way to convert
     */
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

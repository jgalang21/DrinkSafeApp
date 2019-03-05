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

public class ProfileScreen extends AppCompatActivity {
    EditText name_box, phone_box, email_box;
    Spinner sex_s, feet_s, inches_s, lbs_s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);

        name_box = findViewById(R.id.name_edit_box);
        phone_box = findViewById(R.id.phone_edit_box);
        email_box = findViewById(R.id.email_edit_box);

        name_box.setEnabled(false);
        phone_box.setEnabled(false);
        email_box.setEnabled(false);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.sex_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        sex_s.setAdapter(adapter1);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.height_feet_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        feet_s.setAdapter(adapter2);

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.height_inches_array, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inches_s.setAdapter(adapter3);

        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,
                R.array.height_feet_array, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lbs_s.setAdapter(adapter4);

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
        b.setText("@string/edit_t");
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int status =(Integer) v.getTag();
                if(status == 1) {
                    b.setText("Save");
                    name_box.setEnabled(true);
                    phone_box.setEnabled(true);
                    email_box.setEnabled(true);
                    v.setTag(0);
                } else {
                    b.setText("Edit");
                    name_box.setEnabled(false);
                    phone_box.setEnabled(false);
                    email_box.setEnabled(false);
                    v.setTag(1);
                }
            }
        });
    }
}

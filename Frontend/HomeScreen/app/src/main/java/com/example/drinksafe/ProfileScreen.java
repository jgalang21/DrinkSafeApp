package com.example.drinksafe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class ProfileScreen extends AppCompatActivity {
    EditText name_box, phone_box, email_box;
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

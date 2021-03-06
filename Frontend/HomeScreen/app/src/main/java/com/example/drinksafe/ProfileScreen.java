package com.example.drinksafe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.drinksafe.app.AppController;
import com.example.drinksafe.net_utils.Const;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * This class is for the Profile Screen activity
 * @author Philip Payne
 */
public class ProfileScreen extends AppCompatActivity {
    private EditText name_box, weight_box, email_box;
    private Spinner gender_s, feet_s, inches_s;
    private String name, email, weight;
    private int height, gender; //if gender is 1, user is male. Otherwise they are female
    private int[] height_arr = new int[2];

    private static String TAG = ProfileScreen.class.getSimpleName();

    private HashMap<String, Boolean> changes;


    /**
     * This is the main method of this class, used to display the various text views, buttons, and
     * spinners displayed on the screen.  It also calls the necessary methods to pull info from the server
     * and update info on the server.  In this method, user clicks are also monitored.
     * @param savedInstanceState contains info for this activity if the activity was previously started, may be <code>null</code>
     * @see #getInfo()
     * @see #updateServer(HashMap)
     */
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

        gender_s = findViewById(R.id.sex_spin);
        feet_s = findViewById(R.id.feet_spin);
        inches_s = findViewById(R.id.inches_spin);

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
                Intent i = new Intent(ProfileScreen.this, Home.class);
                //Log.d(TAG, i.getAction());
                //Log.d(TAG, i.getDataString());
                startActivity(i);
            }
        });
        changes = new HashMap<>();
        changes.put("name", false);
        changes.put("email", false);
        changes.put("gender", false);
        //changes.put("feet", false);
        //changes.put("inches", false);
        changes.put("weight", false);
        changes.put("height", false);
        Log.d(TAG, changes.toString());

        getInfo();

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
                    if(checkForChanges(changes)) {
                        updateServer(changes);
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
            }
        });



    }

    /**
     *  Pulls a <code>JSONArray</code> from the database to find the current user's info. Using the
     *  {@link #findUser(JSONArray) findUser} method, this method finds the current user and then updates
     *  the text views on the screen with the {@link #update_text() update_text} method.
     */
    private void getInfo() {
        String tmpURL = Const.URL_USER_INFO + "/find/id/" + Const.cur_user_name;

        JsonObjectRequest req = new JsonObjectRequest
                (Request.Method.GET, tmpURL, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());

                        try {
                            name = response.getString("name");
                            email = response.getString("username");
                            String h = response.getString("height");
                            height = Integer.parseInt(h);
                            weight = response.getString("weight");
                            String g = response.getString("gender");
                            gender = Integer.parseInt(g);
                            heightConv(height, height_arr, true);
                            update_text();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(TAG, "Error: " + error.getMessage());
                        Toast.makeText(getApplicationContext(),
                                error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req);


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
            height = temp;
        }
    }

    /**
     *  This method searches through the <code>JSONArray</code> received in the
     *   {@link #getInfo() getInfo} method to find a specific user. The method
     *   iterates through the <code>JSONArray</code> to find a user based on the user name
     *   stored upon login.
     * @param response A <code>JSONArray</code> containing the users listed in the database
     * @return returns a <code>JSONObject</code> of the current user
     * @throws JSONException this exception is thrown if the user is not found in the <code>JSONArray</code>
     */
    private JSONObject findUser(JSONArray response) throws JSONException {
        JSONObject person = null;
        for (int i = 0; i < response.length(); i++) {
            person = (JSONObject) response.get(i);
            String tmp = person.getString("username");
            if (tmp.equals(Const.cur_user_name)) {
                break;
            }
        }
        if(person == null) {
            throw new JSONException("Could not find user");
        } else {
            return person;
        }
    }

    /**
     * Updates the text views on the screen to show the current User's
     * info pulled from the server.
     */
    private void update_text(){
        name_box.setText(name);
        email_box.setText(email);
        gender_s.setSelection(gender);
        feet_s.setSelection(height_arr[0]);
        inches_s.setSelection(height_arr[1]);
        weight_box.setText(weight);
    }

    /**
     *  This method is used to scan for changes a user may have made on the profile screen.  It
     *  stores the values in a Hashtable which is used in the {@link #updateServer(HashMap) updateServer}
     *  method. This method also checks to make sure that all text fields contain some value.
     * @return returns true if any changes were found, false if otherwise or if any errors exist
     */
    private boolean checkForChanges(HashMap<String, Boolean> c) {
        if(name_box.getText().toString().matches("")) {
            Toast.makeText(getApplicationContext(), "Please enter a valid name", Toast.LENGTH_SHORT).show();
            return false;
        } else if(email_box.getText().toString().matches("")) {
            Toast.makeText(getApplicationContext(), "Please enter a valid email", Toast.LENGTH_SHORT).show();
        } else if(weight_box.getText().toString().matches("")) {
            Toast.makeText(getApplicationContext(), "Please enter a valid weight", Toast.LENGTH_SHORT).show();
        }

        if(!name_box.getText().toString().equals(name)) {
            c.put("name", true);
            name = name_box.getText().toString();
        } else {
            c.put("name", false);
        }
        if(!email_box.getText().toString().equals(email)) {
            c.put("email", true);
            email = email_box.getText().toString();
        } else {
            c.put("email", false);
        }
        if(!weight_box.getText().toString().equals(weight)) {
            //Log.d(TAG, "Weight has changed");
            c.put("weight", true);
            weight = weight_box.getText().toString();
        } else {
            c.put("weight", false);
        }
        /*if(Integer.parseInt((String) feet_s.getSelectedItem()) != height_arr[0]) {
            c.put("feet", true);
            height_arr[0] = Integer.parseInt((String) feet_s.getSelectedItem());
        } else {
            c.put("feet", false);
        }
        if(Integer.parseInt((String) inches_s.getSelectedItem()) != height_arr[1]) {
            c.put("inches", true);
            height_arr[1] = Integer.parseInt((String) inches_s.getSelectedItem());
        } else {
            c.put("inches", false);
        }*/
        if(gender_s.getSelectedItemPosition() != gender) {
            c.put("gender", true);
            gender = gender_s.getSelectedItemPosition();
        } else {
            c.put("gender", false);
        }
        if(feet_s.getSelectedItemPosition() != height_arr[0] || inches_s.getSelectedItemPosition() != height_arr[1]) {
            c.put("height", true);
            //Log.d(TAG, "f" + feet_s.getSelectedItemPosition());
            //Log.d(TAG, "i" + inches_s.getSelectedItemPosition());
            height_arr[0] = feet_s.getSelectedItemPosition();
            height_arr[1] = inches_s.getSelectedItemPosition();
            heightConv(height, height_arr, false);
            //Log.d(TAG, "h" + height);
        } else {
            c.put("height", false);
        }

        Log.d(TAG, changes.toString());
        return true;
    }

    /**
     * This method is used to change a user's info stored in the server database.  The method
     * only updates the fields that have been marked as changed in the
     * {@link #checkForChanges(HashMap<String, Boolean>) checkForChanges} method.
     * @param c a <code>Hashtable</code> used to store info on if the fields on the screen have been changed
     * @return returns true if updates were successfully made to the server
     */
    private boolean updateServer(HashMap<String, Boolean> c){
        String tmpURL = Const.URL_USER_INFO + "/edit";

        Log.d(TAG, changes.toString());
        if(c.get("height").equals(true)) {
            Log.d(TAG, "Sending Height" + height);
            tmpURL += "/height/" + Const.cur_user_name + "/" + height;
            JsonObjectRequest req_h = new JsonObjectRequest(Request.Method.GET, tmpURL, null, null, null);
            // Adding request to request queue
            AppController.getInstance().addToRequestQueue(req_h,TAG);
        }
        tmpURL = Const.URL_USER_INFO + "/edit";
        if(c.get("weight").equals(true)) {
            Log.d(TAG, "Sending Weight" + weight);
            tmpURL += "/weight/" + Const.cur_user_name + "/" + weight;
            JsonObjectRequest req_w = new JsonObjectRequest(Request.Method.GET, tmpURL, null, null, null);
            // Adding request to request queue
            AppController.getInstance().addToRequestQueue(req_w,TAG);
        }
        tmpURL = Const.URL_USER_INFO + "/edit";
        if(c.get("name").equals(true)) {
            Log.d(TAG, "Sending name:" + name);
            tmpURL += "/name/" + Const.cur_user_name + "/" + name;
            JsonObjectRequest req_n = new JsonObjectRequest(Request.Method.GET, tmpURL, null, null, null);
            // Adding request to request queue
            AppController.getInstance().addToRequestQueue(req_n,TAG);
        }
        tmpURL = Const.URL_USER_INFO + "/edit";
        /*if(c.get("email").equals(true)) {
            Log.d(TAG, "Sending email");
            tmpURL += "/email/" + Const.cur_user_name + "/" + email;
            JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, tmpURL, null, null, null);
            // Adding request to request queue
            AppController.getInstance().addToRequestQueue(req);
        }*/

        if(c.get("gender").equals(true)) {
            Log.d(TAG, "Sending gender");
            tmpURL += "/gender/" + Const.cur_user_name + "/" + gender;
            JsonObjectRequest req_g = new JsonObjectRequest(Request.Method.GET, tmpURL, null, null, null);
            // Adding request to request queue
            AppController.getInstance().addToRequestQueue(req_g,TAG);
        }
        //tmpURL = Const.URL_USER_INFO + "/edit";
        return true;
    }
}

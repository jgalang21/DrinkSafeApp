package com.example.drinksafe;

import android.os.CountDownTimer;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.example.drinksafe.app.MyAdapter;
import com.example.drinksafe.net_utils.Const;
import com.example.drinksafe.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class is for the Home Screen activity.  It is the main screen, used to access the other
 * activities in the app.
 * @author Philip Payne
 */
public class Home extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private TextView home_hours,home_mins,drink_name,drink_vol,drink_amount;
    private long hours,mins, ms;

    private String time_f,time_s;
    CountDownTimer timer;
    private static String TAG = Home.class.getSimpleName();
    /**
     *  Creates the Home Screen activity and displays relevant info for the User, such as the timer,
     *  last drink, and group member summary.  Also links the activity to the other activities in the app
     *  using the navigation bar.
     * @param savedInstanceState contains info for this activity if the activity was previously started, may be <code>null</code>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        mDrawerLayout = findViewById(R.id.drawer_layout);

        mDrawerLayout.addDrawerListener(
                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        // Respond when the drawer's position changes
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        // Respond when the drawer is opened
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        // Respond when the drawer is closed
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        // Respond when the drawer motion state changes
                    }
                }
        );

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here
                        Intent i;
                        switch(menuItem.getItemId())
                        {
                            case R.id.nav_profile:
                                i = new Intent(Home.this, ProfileScreen.class);
                                startActivity(i);
                                break;
                            case R.id.nav_drinks_summary:
                                i = new Intent(Home.this, DrinkSummary.class);
                                startActivity(i);
                                break;
                            /*case R.id.nav_party:
                                i = new Intent(Home.this, Party.class);
                                startActivity(i);
                                break;

                                 <item
            android:id="@+id/nav_party"
            android:icon="@drawable/ic_group"
            android:title="Party" />*/
                            case R.id.nav_chat:
                                i = new Intent(Home.this, Messaging.class);
                                startActivity(i);
                                break;
                            case R.id.nav_log_out:
                                i = new Intent(Home.this, SignIn.class);
                                startActivity(i);
                                break;
                            case R.id.nav_drink_add:
                                i = new Intent(Home.this, DrinkAdd.class);
                                startActivity(i);
                                break;
                            case R.id.nav_disclaimer:
                                i = new Intent(Home.this, Disclaimer.class);
                                startActivity(i);
                                break;
                            default:
                                return true;
                        }
                        return true;
                    }
                });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        NetworkImageView imgView = findViewById(R.id.img);

        ImageLoader imageLoader = AppController.getInstance().getImageLoader();
        // If you are using NetworkImageView
        imgView.setImageUrl(Const.URL_Image, imageLoader);

        home_hours = findViewById(R.id.timer_hrs_home);
        home_mins = findViewById(R.id.timer_mins_home);
        getDrinkInfo();
        //Log.d(TAG, "ms2:" + ms);
        drink_amount = findViewById(R.id.drink_amount);
        drink_name = findViewById(R.id.drink_name_home);
        drink_vol = findViewById(R.id.drink_size_home);
        try {
            getDrinks();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Button b_qa = findViewById(R.id.qa_button);
        b_qa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, DrinkAdd.class);
                //Log.d(TAG, i.getAction());
                //Log.d(TAG, i.getDataString());
                startActivity(i);
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private JSONObject findTime(JSONArray response) throws JSONException {
        JSONObject ti = null;
        for (int i = 0; i < response.length(); i++) {
            ti = (JSONObject) response.get(i);
            String tmp = ti.getString("user");
            if (tmp.equals(Const.cur_user_name)) {
                break;
            }
        }
        if(ti == null) {
            throw new JSONException("Could not find time");
        } else {
            return ti;
        }
    }

    private void getDrinkInfo() {
        String tmpURL = "http://cs309-bs-7.misc.iastate.edu:8080/time/" + Const.cur_user_name;

        JsonObjectRequest req = new JsonObjectRequest
                (Request.Method.GET, tmpURL, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());

                        try {
                            time_f = response.getString("time_finish");
                            time_s = response.getString("time_start");
                            Log.d(TAG, time_f);
                            Log.d(TAG, time_s);
                            if(!time_f.equals("") && !time_s.equals("")) {
                                long tmp_s = Long.parseLong(time_s);
                                long tmp_f = Long.parseLong(time_f);
                                ms = tmp_f - tmp_s;
                                timer  = new CountDownTimer(ms, 1000) {

                                    public void onTick(long millisUntilFinished) {
                                        convertTime(millisUntilFinished);
                                        String h = Long.toString(hours);
                                        String m = Long.toString(mins);
                                        //Log.d(TAG, "h:" + h + "m:"+ m);
                                        home_hours.setText(h);
                                        home_mins.setText(m);
                                    }

                                    public void onFinish() {

                                    }
                                }.start();
                                //Log.d(TAG, "ms:" + ms);
                            } else {
                                ms = 0;
                            }
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

        /*JsonArrayRequest req = new JsonArrayRequest
                (tmpURL, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        //Log.d(TAG, response.toString());

                        try {
                            JSONObject tmp_time = findTime(response);
                            time_f = tmp_time.getString("time_finish");
                            time_s = tmp_time.getString("time_start");
                            Log.d(TAG, time_f);
                            Log.d(TAG, time_s);
                            if(!time_f.equals("") && !time_s.equals("")) {
                                long tmp_s = Long.parseLong(time_s);
                                long tmp_f = Long.parseLong(time_f);
                                ms = tmp_f - tmp_s;
                                timer  = new CountDownTimer(ms, 1000) {

                                    public void onTick(long millisUntilFinished) {
                                        convertTime(millisUntilFinished);
                                        String h = Long.toString(hours);
                                        String m = Long.toString(mins);
                                        //Log.d(TAG, "h:" + h + "m:"+ m);
                                        home_hours.setText(h);
                                        home_mins.setText(m);
                                    }

                                    public void onFinish() {

                                    }
                                }.start();
                                //Log.d(TAG, "ms:" + ms);
                            } else {
                                ms = 0;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            VolleyLog.d(TAG, "Error: " + e.getMessage());
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
                                error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req);
        //Log.d(TAG, "ms3:" + ms);*/
    }

    private void getDrinks() throws JSONException {
        String tmpURL = Const.URL_USER_INFO + "/list/drink/" + Const.cur_user_name;

        JsonArrayRequest req = new JsonArrayRequest
                (tmpURL, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());

                        try {
                            JSONObject d;
                            d = response.getJSONObject(0);
                            if(d == null) {
                                throw new JSONException("Could not find drinks");
                            } else {
                                drink_amount.setText("" + response.length());
                                drink_name.setText(d.getString("drinkid"));
                                drink_vol.setText(d.getString("volume"));
                            }
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

    private void convertTime(long ms) {
        long tmp = ms;
        hours = tmp / 3600000;
        tmp -= hours * 3600000;
        mins = tmp / 60000;
    }
}

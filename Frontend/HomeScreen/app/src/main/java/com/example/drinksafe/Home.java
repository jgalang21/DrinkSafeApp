package com.example.drinksafe;

import android.os.CountDownTimer;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.example.drinksafe.net_utils.Const;
import com.example.drinksafe.app.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * This class is for the Home Screen activity.  It is the main screen, used to access the other
 * activities in the app.
 * @author Philip Payne
 */
public class Home extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private TextView home_hours,home_mins;
    private long hours,mins;

    private String time;

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
                            case R.id.nav_party:
                                i = new Intent(Home.this, Party.class);
                                startActivity(i);
                                break;
                            case R.id.nav_chat:
                                i = new Intent(Home.this, Messaging.class);
                                startActivity(i);
                                break;
                            case R.id.nav_log_out:
                                i = new Intent(Home.this, SignIn.class);
                                startActivity(i);
                                break;
                            case R.id.nav_drink_add:
                                i = new Intent(Home.this, AddingDrink.class);
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
        new CountDownTimer(4500000, 1000) {

            public void onTick(long millisUntilFinished) {

                String h = Long.toString(hours);
                String m = Long.toString(mins);
                home_hours.setText(h);
                home_mins.setText(m);
            }

            public void onFinish() {

            }
        }.start();

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

    private void getDrinkInfo() {
        String tmpURL = Const.URL_USER_INFO + "/find/id/" + Const.cur_user_name;

        JsonObjectRequest req = new JsonObjectRequest
                (Request.Method.GET, tmpURL, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());

                        try {
                            time = response.getString("user_time");
                            long tmp = Long.parseLong(time);
                            convertTime(tmp);
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

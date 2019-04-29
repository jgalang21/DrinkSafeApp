package com.example.drinksafe;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.drinksafe.app.AppController;
import com.example.drinksafe.app.MyAdapter;
import com.example.drinksafe.net_utils.Const;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Vector;

/**
 * @author Jorden Lee
 * @author Philip Payne
 */
public class DrinkSummary extends AppCompatActivity {

    private TextView ds_hours,ds_mins, ds_sec;
    private long hours,mins,sec, ms;
    private Vector<JSONObject> drinks;
    private String time;
    private RecyclerView drink_view;
    private RecyclerView.Adapter drink_adapter;
    private RecyclerView.LayoutManager layoutManager;
    CountDownTimer timer;
    private static String TAG = DrinkSummary.class.getSimpleName();

    private String time_f,time_s;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks_summary);

        ds_hours = findViewById(R.id.ds_hour);
        ds_mins = findViewById(R.id.ds_min);
        ds_sec = findViewById(R.id.ds_sec);
        getTime();

        ImageButton b_back = findViewById(R.id.ds_back);
        b_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DrinkSummary.this, Home.class);
                //Log.d(TAG, i.getAction());
                //Log.d(TAG, i.getDataString());
                startActivity(i);
            }
        });
        drinks = new Vector<>();
        try {
            getDrinks();
        } catch (JSONException e) {
            e.printStackTrace();
            VolleyLog.d(TAG, "Error: " + e.getMessage());
            Toast.makeText(getApplicationContext(),
                    e.getMessage(), Toast.LENGTH_SHORT).show();
        }


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

    private void getTime() {
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
                                        String s = Long.toString(sec);
                                        ds_hours.setText(h);
                                        ds_mins.setText(m);
                                        ds_sec.setText(s);
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
                            for (int i = 0; i < response.length(); i++) {
                                d = response.getJSONObject(i);
                                drinks.add(d);

                            }
                            if(drinks.isEmpty()) {
                                throw new JSONException("Could not find drinks");
                            } else {
                                drink_view = findViewById(R.id.drink_history);

                                drink_view.setHasFixedSize(true);

                                layoutManager = new LinearLayoutManager(DrinkSummary.this);
                                drink_view.setLayoutManager(layoutManager);

                                drink_adapter = new MyAdapter(drinks);
                                drink_view.setAdapter(drink_adapter);
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
        tmp -= mins * 60000;
        sec = tmp / 1000;
    }
}

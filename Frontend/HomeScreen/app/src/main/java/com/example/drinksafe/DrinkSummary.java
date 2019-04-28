package com.example.drinksafe;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    private long hours,mins,sec;
    private Vector<JSONObject> drinks;
    private String time;
    private RecyclerView drink_view;
    private RecyclerView.Adapter drink_adapter;
    private RecyclerView.LayoutManager layoutManager;

    private static String TAG = DrinkSummary.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks_summary);

        ds_hours = findViewById(R.id.ds_hour);
        ds_mins = findViewById(R.id.ds_min);
        ds_sec = findViewById(R.id.ds_sec);
        getTime();
        new CountDownTimer(4500000, 1000) {

            public void onTick(long millisUntilFinished) {
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

        try {
            drinks = new Vector<JSONObject>(getDrinks());
        } catch (JSONException e) {
            e.printStackTrace();
            VolleyLog.d(TAG, "Error: " + e.getMessage());
            Toast.makeText(getApplicationContext(),
                    e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        drink_view = findViewById(R.id.drink_history);

        drink_view.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        drink_view.setLayoutManager(layoutManager);

        drink_adapter = new MyAdapter(drinks);
        drink_view.setAdapter(drink_adapter);
    }

    private void getTime() {
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

    private Vector<JSONObject> getDrinks() throws JSONException {
        final Vector<JSONObject> drinkList = new Vector<JSONObject>() {
        };
        String tmpURL = Const.URL_USER_INFO + "/list/drink/" + Const.cur_user_name;

        JsonArrayRequest req = new JsonArrayRequest
                (tmpURL, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());

                        try {
                            for (int i = 0; i < response.length(); i++) {
                                drinkList.add((JSONObject) response.get(i));

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

        if(drinkList.isEmpty()) {
            throw new JSONException("Could not find drinks");
        } else {
            return drinkList;
        }
    }

    private void convertTime(long ms) {
        long tmp = ms;
        hours = tmp / 3600000;
        tmp -= hours * 3600000;
        mins = tmp / 60000;
        tmp -= mins * 60000;
        sec = tmp / 1000;
        if(tmp != 0) {

        }
    }
}

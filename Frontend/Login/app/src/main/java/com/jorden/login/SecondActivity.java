package com.jorden.login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
//import com.android.volley.toolbox.StringRequest;
import com.jorden.app.AppController;
import com.jorden.net_utils.Const;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

/*    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        showText = (Button) findViewById(R.id.show_text);
        textShown = (TextView) findViewById(R.id.textToShow);
        showText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.show_text:
                    startActivity(new Intent(SecondActivity.this,
                            StringRequestActivity.class));
                    break;
                    default:break;
                }
            }
        });*/
/*
        private String TAG = SecondActivity.class.getSimpleName();
        private Button btnStringReq;
        private TextView msgResponse;
        private ProgressDialog pDialog;

        // This tag will be used to cancel the request
        private String tag_string_req = "string_req";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_second);

            btnStringReq = (Button) findViewById(R.id.show_text);
            msgResponse = (TextView) findViewById(R.id.textToShow);

            pDialog = new ProgressDialog(this);
            pDialog.setMessage("Loading...");
            pDialog.setCancelable(false);

            btnStringReq.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    makeStringReq();
                }
            });
        }

        private void showProgressDialog() {
            if (!pDialog.isShowing())
                pDialog.show();
        }

        private void hideProgressDialog() {
            if (pDialog.isShowing())
                pDialog.hide();
        }

        *//**
         * Making json object request
         * *//*
        private void makeStringReq() {
            showProgressDialog();

            StringRequest strReq = new StringRequest(Method.GET, Const.URL_STRING_REQ, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d(TAG, response.toString());
                    msgResponse.setText(response.toString());
                    hideProgressDialog();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d(TAG, "Error: " + error.getMessage());
                    hideProgressDialog();
                }
            });

            // Adding request to request queue
            AppController.getInstance().addToRequestQueue(strReq, tag_string_req);

        }
    }*/

        private String TAG = SecondActivity.class.getSimpleName();
        private Button /*btnJsonObj,*/ btnJsonArray;
        private TextView msgResponse;
        private ProgressDialog pDialog;

        // These tags will be used to cancel the requests
        private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

        @Override
        protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //btnJsonObj = (Button) findViewById(R.id.show_text);
        btnJsonArray = (Button) findViewById(R.id.show_text);
        msgResponse = (TextView) findViewById(R.id.textToShow);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);

//        btnJsonObj.setOnClickListener(this);
        btnJsonArray.setOnClickListener(this);

        }

        private void showProgressDialog () {
        if (!pDialog.isShowing())
            pDialog.show();
    }

        private void hideProgressDialog () {
        if (pDialog.isShowing())
            pDialog.hide();
    }

        /**
         * Making json object request
         * */
        private void makeJsonObjReq () {
        showProgressDialog();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.GET,
                Const.URL_USER_INFO, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        msgResponse.setText(response.toString());
                        hideProgressDialog();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hideProgressDialog();
            }
        }) {

            /**
             * Passing some request headers
             */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", "Androidhive");
                params.put("email", "abc@androidhive.info");
                params.put("pass", "password123");

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq,
                tag_json_obj);

        // Cancelling request
        // ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_obj);
    }
    private void makeJsonArryReq() {
        showProgressDialog();
        JsonArrayRequest req = new JsonArrayRequest(Const.URL_USER_INFO,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        msgResponse.setText(response.toString());
                        hideProgressDialog();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hideProgressDialog();
            }
        });
    }

        @Override
        public void onClick (View v){
        switch (v.getId()) {
            case R.id.show_text:
                makeJsonArryReq();
                break;

        }

    }

    }
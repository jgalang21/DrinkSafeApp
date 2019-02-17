package com.jorden.login;
import android.app.ProgressDialog;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;

public class StringRequest{
String  tag_string_req = "string_req";

        String url = "https://api.androidhive.info/volley/string_response.html";

        ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        StringRequest strReq = new StringRequest(Request.Method.GET,
        url, new Response.Listener<String>() {

@Override
public void onResponse(String response) {
        Log.d(TAG, response.toString());
        pDialog.hide();

        }
        }, new Response.ErrorListener() {

@Override
public void onErrorResponse(VolleyError error) {
        VolleyLog.d(TAG, "Error: " + error.getMessage());
        pDialog.hide();
        }
        });

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
        }
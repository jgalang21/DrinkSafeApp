package com.jorden.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.jorden.app.AppController;
import com.jorden.net_utils.Const;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class SignIn extends AppCompatActivity {

    private JSONArray msgResponseArr;
    private JSONObject msgResponseObj;
    private EditText Email;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private Button SignUp;
    private int counter = 5;
    private ProgressDialog pDialog;
    private String TAG = SecondActivity.class.getSimpleName();
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    private String passWd;
    String in;
    JSONObject reader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Email = (EditText) findViewById(R.id.etemail);
        Password = (EditText) findViewById(R.id.etpass);
        Info = (TextView) findViewById(R.id.tvInfo);
        Login = (Button) findViewById(R.id.btnLogin);
        SignUp = (Button) findViewById(R.id.SignUpbtn);
        try {
            reader = new JSONObject(in);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Info.setText("attempts left: 5");
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    validate(Email.getText().toString(), Password.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignIn.this, SignUp.class);
                startActivity(intent);
            }
        });
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
    }

    private void validate(String userEmail, String userPass) throws JSONException {
        if(userPass == ""||userPass == null) {
            return;
        }
            JSONObject sys  = reader.getJSONObject(userEmail);
            passWd = sys.getString("password");

        if (((userEmail.equals("Admin")) && (userPass.equals("123Abc")))||passWd.equals(userPass)) {
            Intent intent = new Intent(SignIn.this,  SecondActivity.class);
            startActivity(intent);

        } else {
            counter--;
            Info.setText("Attempts left: " + String.valueOf(counter));
            if (counter == 0) {
                counter = 0;
                Login.setEnabled(false);
            }
        }
    }

    private void showProgressDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideProgressDialog() {
        if (pDialog.isShowing())
            pDialog.hide();
    }

    private void makeJsonObjReq() {
        showProgressDialog();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                Const.URL_USER_INFO, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        msgResponseObj = response;
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
                params.put("email", Email.getText().toString());
                params.put("pass", Password.getText().toString());

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq,
                tag_json_obj);

    }

    private JsonArrayRequest makeJsonArryReq() {
        showProgressDialog();
        JsonArrayRequest req = new JsonArrayRequest(Const.URL_USER_INFO/*URL_USER_INFO*/,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                            msgResponseArr = response;
                            in = response.toString();
                        hideProgressDialog();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Log.d(TAG, error.getStackTrace() + "");
                Log.d(TAG, error.getLocalizedMessage() + "");
                hideProgressDialog();
            }
        });
        return req;
    }
}

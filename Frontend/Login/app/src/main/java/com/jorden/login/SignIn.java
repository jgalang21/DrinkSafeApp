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
import android.widget.Toast;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class SignIn extends AppCompatActivity {

    private EditText Email;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private Button SignUp;
    private int counter = 5;
    private ProgressDialog pDialog;
    private String TAG = SignIn.class.getSimpleName();
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    private ArrayList<String> passWd = new ArrayList<String>();
    private ArrayList<String> UserEmail = new ArrayList<String>();
    String storedPass = "";
    private TextView msgResponse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Email = (EditText) findViewById(R.id.etemail);
        Password = (EditText) findViewById(R.id.etpass);
        Info = (TextView) findViewById(R.id.tvInfo);
        Login = (Button) findViewById(R.id.btnLogin);
        SignUp = (Button) findViewById(R.id.SignUpbtn);
        msgResponse = (TextView) findViewById(R.id.textToShow);


        Info.setText("attempts left: 5");
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    makeJsonArrayReq();
                    //reader = new JSONObject(in);
                    System.err.println("makeJsonArrayReq");
                    validate(Email.getText().toString(), Password.getText().toString());
                    System.err.println("Finish validate");
                } catch (JSONException e) {
                    System.out.print("on click catch");
                }
                catch (Exception e){
                    System.err.println("Hard Fail");
                    System.err.println("FAILED: " + e);
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

    private void validate(String uEmail, String userPass) throws JSONException {
        if(userPass == ""||userPass == null) {
            return;
        }
            //JSONObject sys  = reader.getJSONObject(userEmail);
            //passWd = sys.getString("password");
        System.err.println("username: ");
        for(int i = 0; i< UserEmail.size();i++){
            if(UserEmail.get(i).equals(uEmail)){
                storedPass = passWd.get(i);
                break;
            }
        }
        if (((uEmail.equals("Admin")) && (userPass.equals("123Abc")))||passWd.equals(userPass)) {
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
                        hideProgressDialog();
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

    private void makeJsonArrayReq() {
        showProgressDialog();
        System.err.println("TEEEESSSSSSSTTTTTTTT");
        JsonArrayRequest req = new JsonArrayRequest(Const.URL_USER_INFO/*URL_USER_INFO*/,
             new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        System.err.println("in onResponse");
                        try{
                        for (int i = 0; i < response.length(); i++) {

                            JSONObject person = (JSONObject) response
                                    .get(i);
                            //JSONObject userName = person.getJSONObject("UserName");
                            System.err.print("This: " + person);
                            passWd.add(person.getString("password"));
                            UserEmail.add(person.getString("username"));
                            //System.err.print("Username: " + userName.toString());
                        }

                        }
                        catch(JSONException e) {
                                System.err.print("in makejsonarrReq");
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(),
                                        "Error: " + e.getMessage(),
                                        Toast.LENGTH_LONG).show();

                            }
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
        System.err.println("before Request");
        AppController.getInstance().addToRequestQueue(req);
        System.err.println("Request to queue added");


    }
}

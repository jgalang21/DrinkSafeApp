package com.example.drinksafe;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.drinksafe.app.AppController;
import com.example.drinksafe.net_utils.Const;

import org.json.JSONArray;

//import static com.example.drinksafe.net_utils.Const.URL_ADDUSER_INFO;
import static com.example.drinksafe.net_utils.Const.URL_USER_INFO;
//GO TO PROFILESCREEN
public class SignUp extends AppCompatActivity {

    private EditText email, password, weight, heightIn, heightFt;
    private CheckBox sexF,sexM;
    private Button signUp;
    private ProgressDialog pDialog;
    private String TAG = SignIn.class.getSimpleName();
    private int height, sex;
    private String tempURL;
    private boolean switchStatus, mayProceed = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        email = (EditText) findViewById(R.id.userEmail);
        password = (EditText) findViewById(R.id.UserPass);
        weight = (EditText) findViewById(R.id.userWeight);
        heightIn = (EditText) findViewById(R.id.height_in);
        heightFt = (EditText) findViewById(R.id.height_ft);
        sexM = (CheckBox) findViewById(R.id.checkBoxM);
        sexF = (CheckBox) findViewById(R.id.checkBoxF);
        signUp = (Button) findViewById(R.id.SignUpbtn);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    height = (Integer.parseInt(heightFt.getText().toString()) * 12) + Integer.parseInt(heightIn.getText().toString());
                    Boolean maleStatus = sexM.isChecked();
                    Boolean femaleStatus = sexF.isChecked();
                    if (maleStatus == false && femaleStatus == true){
                        sex = 1;
                        mayProceed = true;
                    }
                    else if (maleStatus == true && femaleStatus == false){
                        sex = 0;
                        mayProceed = true;
                    }
                    else if (maleStatus == false&&femaleStatus == false) {
                        Toast.makeText(getApplicationContext(), "please identify a sex", Toast.LENGTH_LONG).show();
                        mayProceed = false;
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "please identify as only one sex", Toast.LENGTH_LONG).show();
                        mayProceed = false;
                    }

                    tempURL += "/height/" + Const.cur_user_name + "/" + height;
                    JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, tmpURL, null, null, null);
                    //makeJsonArrayReq();
                    System.err.println("makeJsonArrayReq");
                    System.err.println("Finish validate");
                } catch (Exception e) {
                    System.err.println("Hard Fail");
                    System.err.println("FAILED: " + e);
                }
            }
        });
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
    }
        private void showProgressDialog() {
            if (!pDialog.isShowing())
                pDialog.show();
        }

        private void hideProgressDialog() {
            if (pDialog.isShowing())
                pDialog.hide();
        }
        private void makeJsonArrayReq() {
            showProgressDialog();
            System.err.println("TEEEESSSSSSSTTTTTTTT");
            JsonArrayRequest req = new JsonArrayRequest(tempURL/*URL_USER_INFO*/,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {

                            hideProgressDialog();
                            if(mayProceed){
                                Intent intent = new Intent(SignUp.this, SignIn.class);
                                startActivity(intent);
                            }

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

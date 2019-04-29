package com.example.drinksafe;
/**
 * @author Jorden Lee
 *
 * this page creates a user when a new user wants to make an account
 */

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

    private EditText email, password, weight, heightIn, heightFt, name;
    private CheckBox sexF,sexM;
    private Button signUp;
    private ProgressDialog pDialog;
    private String TAG = SignIn.class.getSimpleName();
    private int height;
    private String heightString, sexString;
    private String tempURL;
    private boolean switchStatus, mayProceed = true;

    /**
     * Executes functionality of the sign up page when the page is created
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        email = (EditText) findViewById(R.id.userEmail);
        name = (EditText) findViewById(R.id.name);
        password = (EditText) findViewById(R.id.UserPass);
        weight = (EditText) findViewById(R.id.userWeight);
        heightIn = (EditText) findViewById(R.id.height_in);
        heightFt = (EditText) findViewById(R.id.height_ft);
        sexM = (CheckBox) findViewById(R.id.checkBoxM);
        sexF = (CheckBox) findViewById(R.id.checkBoxF);
        signUp = (Button) findViewById(R.id.SignUpbtn);
        /**
         * waits for a click confirmation to execute the code following
         */
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    height = ((Integer.parseInt(heightFt.getText().toString()) * 12) + Integer.parseInt(heightIn.getText().toString()));
                    heightString += height;
                    Boolean maleStatus = sexM.isChecked();
                    Boolean femaleStatus = sexF.isChecked();
                    if (maleStatus == false && femaleStatus == true){
                        sexString = "1";
                        mayProceed = true;
                    }
                    else if (maleStatus == true && femaleStatus == false){
                        sexString = "0";
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
                    if(mayProceed&&email.getText() != null&&name.getText()!=null&&password.getText()!=null&&weight!= null&&heightFt.getText()!=null&&heightIn.getText()!=null) {
                        System.err.println("got there");
                        System.err.println(height);
                        tempURL = URL_USER_INFO;
                        tempURL += "/new/" + email.getText() + "/" + name.getText()+"/"+password.getText()+"/"+heightString+"/"+weight.getText()+"/"+sexString+"/0";
                        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, tempURL, null, null, null);
                        AppController.getInstance().addToRequestQueue(req);
                        Intent intent = new Intent(SignUp.this, SignIn.class);
                        startActivity(intent);
                    }
                    else
                        Toast.makeText(getApplicationContext(), "please fill out all information", Toast.LENGTH_LONG).show();

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

    /**
     * shows a dialog box with a spinning circle
     */
        private void showProgressDialog() {
            if (!pDialog.isShowing())
                pDialog.show();
        }

    /**
     * hides the progress dialog box
     */
        private void hideProgressDialog() {
            if (pDialog.isShowing())
                pDialog.hide();
        }

}

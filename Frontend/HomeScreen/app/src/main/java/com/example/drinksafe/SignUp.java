package com.example.drinksafe;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.drinksafe.app.AppController;
import com.example.drinksafe.net_utils.Const;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.example.drinksafe.net_utils.Const.URL_ADDUSER_INFO;
import static com.example.drinksafe.net_utils.Const.URL_USER_INFO;

public class SignUp extends AppCompatActivity {

    private EditText email, password, weight, heightIn, heightFt;
    private Switch maleSw, femSw;
    private Button signUp;
    private ProgressDialog pDialog;
    private String TAG = SignIn.class.getSimpleName();
    private int height;
    private String tempURL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        email = (EditText) findViewById(R.id.userEmail);
        password = (EditText) findViewById(R.id.UserPass);
        weight = (EditText) findViewById(R.id.userWeight);
        heightIn = (EditText) findViewById(R.id.height_in);
        heightFt = (EditText) findViewById(R.id.height_ft);
        maleSw = (Switch) findViewById(R.id.maleSex);
        femSw = (Switch) findViewById(R.id.femSex);
        signUp = (Button) findViewById(R.id.SignUpbtn);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    height = (Integer.parseInt(heightFt.getText().toString())*12)+Integer.parseInt(heightIn.getText().toString());
                    tempURL = URL_USER_INFO;
                    tempURL += "/new/" + "/" + email +"/";
                    makeJsonArrayReq();
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
            //height conversion from ProfileScreen
 /*   private void heightConv(int h, int[] h_arr, boolean convert) {
        if(convert) {
            h_arr[0] = h / 12;
            h_arr[1] = h % 12;
        } else {
            int temp = 0;
            temp += h_arr[0] * 12;
            temp += h_arr[1];
            h = temp;
        }
    }*/
}

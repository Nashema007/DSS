package com.example.dell.dss.activities;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.dell.dss.R;
import com.example.dell.dss.utilities.AppConfig;
import com.example.dell.dss.utilities.SharedPref;

import org.json.JSONArray;
import org.json.JSONObject;

public class SplashScreen extends AppCompatActivity {

    private static final int TIME_OUT = 4000;
    private SharedPref sharedPref = new SharedPref(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        //if user once logged in login info
        //is queried from shared preferencess
        // and checked for validity

        final String sharedUsername = sharedPref.getString("username", "");
        final String sharedPassword = sharedPref.getString("password", "");

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                if (!(sharedPassword.equals("") && sharedUsername.equals(""))) {
                    login();
                }
                else {
                    Intent intent = new Intent(SplashScreen.this, ChoiceActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        },TIME_OUT);
    }


    private void login() {

        StringRequest myStringRequest = new StringRequest(Request.Method.POST, AppConfig.URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    // gets response from php file for success or failure
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    String code = jsonObject.getString("code");
                    String message = jsonObject.getString("message");
                    if (code.equals("Login success")) {

                        StatsActivity.startIntent(SplashScreen.this, Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);


                    } else if (code.equals("Login failed")) {
                        Toast.makeText(SplashScreen.this, "Your password has been recently changed please input the new one"
                                , Toast.LENGTH_LONG).show();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                if (error instanceof TimeoutError) {
                    Toast.makeText(SplashScreen.this, "Login attempt has timed out. Please try again.",
                            Toast.LENGTH_LONG).show();

                } else if (error instanceof NetworkError) {
                    Toast.makeText(SplashScreen.this, "Network Error", Toast.LENGTH_LONG).show();

                } else if (error instanceof ServerError) {
                    Toast.makeText(SplashScreen.this, "Server is down", Toast.LENGTH_LONG).show();

                }
                error.printStackTrace();


            }


        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                String sharedUsername = sharedPref.getString("username", "");
                String sharedPassword = sharedPref.getString("password", "");
                params.put("Regnum", sharedUsername);
                params.put("Password", sharedPassword);

                return params;

            }
        };

        AppSingleton.getInstance(SplashScreen.this).addToRequestQueue(myStringRequest);


    }
}

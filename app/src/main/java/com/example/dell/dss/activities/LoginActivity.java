package com.example.dell.dss.activities;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dell.dss.R;
import com.example.dell.dss.utilities.AppConfig;
import com.example.dell.dss.utilities.AppSingleton;
import com.example.dell.dss.Modal.LoginAuth;
import com.example.dell.dss.utilities.SharedPref;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private Button loginBtn;
    private EditText username;
    private EditText password;
    private SharedPref sharedPref = new SharedPref(this);

    public static void startIntent(Context context) {

        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);

    }

    public static void startIntent(Context context, int flags) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(flags);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtn = findViewById(R.id.loginBtn);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);





        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final LoginAuth myloginAuth = new LoginAuth();
                String enteredregnum = username.getText().toString().trim();
                String enteredpassword = password.getText().toString().trim();

                if (TextUtils.isEmpty(enteredregnum) & TextUtils.isEmpty(enteredpassword)) {

                    username.setError("Enter your Registration number");
                    password.setError("Enter your password");
                }

                if (!(TextUtils.isEmpty(enteredregnum) & TextUtils.isEmpty(enteredpassword))) {

                    //check details in DB
                    myloginAuth.setRegistrationNumbner(enteredregnum);
                    myloginAuth.setStudentpass(enteredpassword);

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

                                    // if login is successful
                                    // username and password are stored in shared preferences
                                    sharedPref.saveString("username", myloginAuth.getRegistrationNumbner());
                                    sharedPref.saveString("password", myloginAuth.getStudentpass());

                                    StatsActivity.startIntent(LoginActivity.this, Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();


                                } else if (code.equals("Login failed")) {

                                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();

                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            if (error instanceof TimeoutError) {
                                Toast.makeText(LoginActivity.this, "Login attempt has timed out. Please try again.",
                                        Toast.LENGTH_LONG).show();

                            } else if (error instanceof NetworkError) {
                                Toast.makeText(LoginActivity.this, "Network Error", Toast.LENGTH_LONG).show();

                            } else if (error instanceof ServerError) {
                                Toast.makeText(LoginActivity.this, "Server is down", Toast.LENGTH_LONG).show();

                            }
                            error.printStackTrace();

                        }


                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {

                            // stores the login details using key pair system
                            Map<String, String> params = new HashMap<>();
                            params.put("Regnum", myloginAuth.getRegistrationNumbner());
                            params.put("Password", myloginAuth.getStudentpass());

                            return params;

                        }
                    };

                    AppSingleton.getInstance(LoginActivity.this).addToRequestQueue(myStringRequest);

                }

            }
        });


    }




}

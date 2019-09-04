package com.example.dell.dss.activities;


import android.content.Intent;
import android.os.Bundle;
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
import com.example.dell.dss.Modal.Person;
import com.example.dell.dss.R;
import com.example.dell.dss.utilities.AppConfig;
import com.example.dell.dss.utilities.AppSingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SigninActivity extends AppCompatActivity {

    private EditText email;
    private EditText pass;
    private EditText lname;
    private EditText fname;
    private EditText nationalID;
    private EditText passCnfm;
    final private Person person = new Person();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);


        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        passCnfm = findViewById(R.id.passCnfm);
        nationalID = findViewById(R.id.national_id);
        fname = findViewById(R.id.name);
        lname = findViewById(R.id.surname);
        Button submit = findViewById(R.id.registerBtn);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();

                String strEmail = email.getText().toString().trim();
                String strFname=  fname.getText().toString().trim();
                String strLname = lname.getText().toString().trim();
                String strNationalID = nationalID.getText().toString().trim();
                String strPass = pass.getText().toString().trim();
                String strPassCnfm = passCnfm.getText().toString().trim();

                if(strPass.length() <=5){
                    if(strPass.equals(strPassCnfm)){


                        person.setEmail(strEmail);
                        person.setName(strFname);
                        person.setSurname(strLname);
                        person.setNationality(strNationalID);
                        person.setPassword(strPassCnfm);


                        details(AppConfig.URL_REGISTER);


                    }else { passCnfm.setError("Passwords do not match"); }
                }else { pass.setError("Password should have 6 or more characters "); }
            }
        });


    }


    public void details(String url) {



        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    String code = jsonObject.getString("code");
                    String message = jsonObject.getString("message");
                    if (code.equals("Success")) {

                        LoginActivity.startIntent(SigninActivity.this, Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        Toast.makeText(SigninActivity.this, message, Toast.LENGTH_SHORT).show();


                    } else if (code.equals("Fail")) {

                        Toast.makeText(SigninActivity.this, message, Toast.LENGTH_LONG).show();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError) {
                    Toast.makeText(SigninActivity.this, "Login attempt has timed out. Please try again.",
                            Toast.LENGTH_LONG).show();

                } else if (error instanceof NetworkError) {
                    Toast.makeText(SigninActivity.this, "Network Error", Toast.LENGTH_LONG).show();

                } else if (error instanceof ServerError) {
                    Toast.makeText(SigninActivity.this, "Server is down", Toast.LENGTH_LONG).show();

                }
                error.printStackTrace();
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                // stores the login details using key pair system
                Map<String, String> params = new HashMap<>();
                params.put("fname", person.getName());
                params.put("email", person.getEmail());
                params.put("nationalID", person.getNationality());
                params.put("lname", person.getSurname());
                params.put("pass", person.getPassword());


                return params;

            }

        };
        AppSingleton.getInstance(this).addToRequestQueue(stringRequest);


    }


    private boolean validate() {
        boolean alldone;

        String strLname = lname.getText().toString().trim();
        String strNationalID = nationalID.getText().toString().trim();
        String strPass = pass.getText().toString().trim();
        String strPassCnfm = passCnfm.getText().toString().trim();
        String strFname = fname.getText().toString().trim();
        String strEmail = email.getText().toString().trim();


        if (strFname.isEmpty()) {
            fname.setError("Enter you Name");
            return false;
        } else {
            alldone = true;
            fname.setError(null);
        }

        if (strLname.isEmpty()) {
            lname.setError("Enter your Surname");
            return false;
        } else {
            alldone = true;
            lname.setError(null);
        }


        if (strEmail.isEmpty()) {
            email.setError("Enter your Email Address");
            return false;
        } else {
            alldone = true;
            email.setError(null);
        }

        if (strNationalID.isEmpty()) {
            nationalID.setError("Enter your National ID");
            return false;
        } else {
            alldone = true;
            nationalID.setError(null);
        }

        if (strPass.isEmpty()) {
            pass.setError("Enter your Password");
            return false;
        } else {
            alldone = true;
            pass.setError(null);
        }


        if (strPassCnfm.isEmpty()) {
            passCnfm.setError("Enter your Password");
            return false;
        } else {
            alldone = true;
            passCnfm.setError(null);
        }
        return true;


    }

}

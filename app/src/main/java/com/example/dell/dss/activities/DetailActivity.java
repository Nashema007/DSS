package com.example.dell.dss.activities;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
import com.example.dell.dss.utilities.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetailActivity extends AppCompatActivity {

    List<String> degreeList = new ArrayList<>();
    private EditText regnum;
    private EditText email;
    private EditText age;
    private Spinner nationality;
    private Spinner level;
    private Spinner campus;
    private Spinner hostel;
    private Spinner prog;
    private Spinner moe;
    private Spinner disability;
    private Button submit;
    private Person person = new Person();

    public static void startIntent(Context context) {

        Intent intent = new Intent(context, DetailActivity.class);
        context.startActivity(intent);

    }

    public static void startIntent(Context context, int flags) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.setFlags(flags);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        regnum = findViewById(R.id.regnum);
        email = findViewById(R.id.email);
        age = findViewById(R.id.age);
        nationality = findViewById(R.id.countries);
        level = findViewById(R.id.level);
        campus = findViewById(R.id.campus);
        prog = findViewById(R.id.degree);
        moe = findViewById(R.id.moe);
        hostel = findViewById(R.id.hostels);
        submit = findViewById(R.id.submit);
        disability = findViewById(R.id.choice);


        String male = getIntent().getStringExtra(Constants.MALE);
        String female = getIntent().getStringExtra(Constants.FEMALE);
        if (male == null)
            male = "";
        else if (female == null)
            female = "";

        addSpinnerItems(AppConfig.URL_DEGREES);


        final String finalFemale = female;
        final String finalMale = male;
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validate();
                String strRegnum = regnum.getText().toString().trim();
                String strEmail = email.getText().toString().trim();
                String checkAge = age.getText().toString().trim();
                String strNationality = nationality.getSelectedItem().toString();
                String strLevel = level.getSelectedItem().toString();
                String strHostel = hostel.getSelectedItem().toString();
                String strCampus = campus.getSelectedItem().toString();
                String strProg = prog.getSelectedItem().toString();
                String strMOE = moe.getSelectedItem().toString();
                String strDisability = disability.getSelectedItem().toString();


                    if ((finalMale.equals(Constants.MALE))) {
                        person.setGender(Constants.MALE);
                    } else if ((finalFemale.equals(Constants.FEMALE))) {
                        person.setGender(Constants.FEMALE);
                    }
                    person.setRegnum(strRegnum);
                    person.setAge(checkAge);
                    person.setCampus(strCampus);
                    person.setEmail(strEmail);
                    person.setNationality(strNationality);
                    person.setDisability(strDisability);
                    person.setProg(strProg);
                    person.setModeOfEntry(strMOE);
                    person.setLevel(strLevel);
                    person.setHostel(strHostel);


                    setDetails(AppConfig.URL_SET_DETAILS);

            }

        });


    }

    public void setDetails(String url) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    String code = jsonObject.getString("code");
                    String message = jsonObject.getString("message");
                    if (code.equals("Success")) {

                        MenuActivity.startIntent(DetailActivity.this, Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        Toast.makeText(DetailActivity.this, message, Toast.LENGTH_SHORT).show();


                    } else if (code.equals("Fail")) {

                        Toast.makeText(DetailActivity.this, message, Toast.LENGTH_LONG).show();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError) {
                    Toast.makeText(DetailActivity.this, "Login attempt has timed out. Please try again.",
                            Toast.LENGTH_LONG).show();

                } else if (error instanceof NetworkError) {
                    Toast.makeText(DetailActivity.this, "Network Error", Toast.LENGTH_LONG).show();

                } else if (error instanceof ServerError) {
                    Toast.makeText(DetailActivity.this, "Server is down", Toast.LENGTH_LONG).show();

                }
                error.printStackTrace();
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                // stores the login details using key pair system
                Map<String, String> params = new HashMap<>();
                params.put("regnum", person.getRegnum());
                params.put("email", person.getEmail());
                params.put("nationality", person.getNationality());
                params.put("level", person.getLevel());
                params.put("MOE", person.getModeOfEntry());
                params.put("campus", person.getCampus());
                params.put("hostel", person.getHostel());
                params.put("degree", person.getProg());
                params.put("gender", person.getGender());
                params.put("disability", person.getDisability());
                params.put("age", String.valueOf(person.getAge()));


                return params;

            }

        };
        AppSingleton.getInstance(this).addToRequestQueue(stringRequest);


    }

    public void addSpinnerItems(String url) {


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObject = array.getJSONObject(i);
                        String degreeDetails = jsonObject.getString("progname");
                        degreeList.add(degreeDetails);

                    }
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(DetailActivity.this, android.R.layout.simple_spinner_item, degreeList);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    prog.setAdapter(dataAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError) {
                    Toast.makeText(DetailActivity.this, "Login attempt has timed out. Please try again.",
                            Toast.LENGTH_LONG).show();

                } else if (error instanceof NetworkError) {
                    Toast.makeText(DetailActivity.this, "Network Error", Toast.LENGTH_LONG).show();

                } else if (error instanceof ServerError) {
                    Toast.makeText(DetailActivity.this, "Server is down", Toast.LENGTH_LONG).show();

                }
                error.printStackTrace();


            }
        });

        AppSingleton.getInstance(this).addToRequestQueue(stringRequest);

    }


    private boolean validate() {
        boolean alldone;

        String strAge = age.getText().toString().trim();
        String strReg = regnum.getText().toString().trim();
        String strEmail = email.getText().toString().trim();


        if (strAge.isEmpty()) {
            age.setError("Please Enter you age");
            return false;
        } else {
            alldone = true;
            age.setError(null);
        }
        if (strReg.isEmpty()) {
            regnum.setError("Enter your Reg Number");
            return false;
        } else {
            alldone = true;
            regnum.setError(null);

        }
        if (strEmail.isEmpty()) {
            email.setError("Enter your Email Address");
            return false;
        } else {
            alldone = true;
            email.setError(null);


            return true;


        }
    }
}


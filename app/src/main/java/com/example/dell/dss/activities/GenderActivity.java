package com.example.dell.dss.activities;


import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dell.dss.Modal.Graphs;
import com.example.dell.dss.R;
import com.example.dell.dss.adapter.GraphsAdapter;
import com.example.dell.dss.utilities.AppConfig;
import com.example.dell.dss.utilities.AppSingleton;
import com.example.dell.dss.utilities.DrawGraph;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GenderActivity extends AppCompatActivity {

    private PieChart pieChart;
    private TextView title;
    DrawGraph drawGraph = new DrawGraph();
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private GraphsAdapter graphsAdapter;
    private List<Graphs> graphsList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_pie_chart);

        pieChart = findViewById(R.id.pieAges);
        title = findViewById(R.id.titleValues);


        recyclerView = findViewById(R.id.chartRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);



        draw(AppConfig.URL_FETCH_DETAILS, pieChart);


    }


    public void draw(String url, final PieChart pieChart) {


        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject genderArray = jsonArray.getJSONObject(0);
                    JSONObject genderDetails = genderArray.getJSONObject("gender");
                    int male = genderDetails.getInt("Male");
                    int female = genderDetails.getInt("Female");

                    final int[] genderValues = {male, female};
                    final String[] genderLabel = {"Male", "Female"};

                    int count = 0;

                    for (String title : genderLabel ){
                        graphsList.add(new Graphs(title, String.valueOf(genderValues[count])));
                        count ++;
                    }


                    drawGraph.drawPieChart(genderValues, genderLabel, pieChart, ColorTemplate.MATERIAL_COLORS, "  No. of Males and Females");
                    title.setText(R.string.malesVfemales);
                   graphsAdapter = new GraphsAdapter(graphsList, GenderActivity.this);
                   recyclerView.setAdapter(graphsAdapter);





                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError) {
                    Toast.makeText(GenderActivity.this, "Login attempt has timed out. Please try again.",
                            Toast.LENGTH_LONG).show();

                } else if (error instanceof NetworkError) {
                    Toast.makeText(GenderActivity.this, "Network Error", Toast.LENGTH_LONG).show();

                } else if (error instanceof ServerError) {
                    Toast.makeText(GenderActivity.this, "Server is down", Toast.LENGTH_LONG).show();

                }
                error.printStackTrace();


            }
        });

        AppSingleton.getInstance(this).addToRequestQueue(stringRequest);


    }


}

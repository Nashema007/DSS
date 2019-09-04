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
import com.github.mikephil.charting.charts.BarChart;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HostelActivity extends AppCompatActivity {

    private BarChart barChart;
    private TextView title;
    private DrawGraph drawGraph = new DrawGraph();
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private GraphsAdapter graphsAdapter;
    private List<Graphs> graphsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);

       barChart = findViewById(R.id.barAges);
        title = findViewById(R.id.barChartTitleValues);

        recyclerView = findViewById(R.id.BarChartRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);


        draw(AppConfig.URL_FETCH_DETAILS, barChart);

    }

    public void draw(String url, final BarChart barChart) {


        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject genderArray = jsonArray.getJSONObject(0);
                    JSONObject genderDetails = genderArray.getJSONObject("hostel");
                    int rufaro = genderDetails.getInt("rufaro");
                    int runyararo = genderDetails.getInt("runyararo");
                    int china = genderDetails.getInt("china");
                    int dzapasi = genderDetails.getInt("dzapasi");
                    int nyadzonya = genderDetails.getInt("nyadzonya");
                    int japan = genderDetails.getInt("japan");
                    int kaguvi = genderDetails.getInt("kaguvi");
                    int wadzanayi = genderDetails.getInt("wadzanayi");
                    int uhuru = genderDetails.getInt("uhuru");
                    int rusununguko = genderDetails.getInt("rusununguko");
                    int magamba = genderDetails.getInt("magamba");

                    int[] array = new int[11];

                    for (int i = 0; i < 11; i++) {
                        array[i] = i + 1;

                    }


                    final int[] hostelValues = {rufaro, runyararo, china, dzapasi, nyadzonya, japan, kaguvi, wadzanayi, uhuru, rusununguko, magamba};
                    final String[] hostelLabel = {"1.Rufaro", "2.Runyararo", "3.China", "4.Dzapasi", "5.Nyadzonya",
                            "6.Japan", "7.Kaguvi", "8.Wadzanayi", "9.Uhuru", "10.Rusununguko", "11.Magamba"};

                    int count = 0;

                    for (String title : hostelLabel ){
                        graphsList.add(new Graphs(title, String.valueOf(hostelValues[count])));
                        count ++;
                    }




                    drawGraph.drawBarChart(array, hostelValues,barChart,  "Hostels", HostelActivity.this);

                    title.setText(R.string.hostelNmbers);
                    graphsAdapter = new GraphsAdapter(graphsList, HostelActivity.this);
                    recyclerView.setAdapter(graphsAdapter);





                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError) {
                    Toast.makeText(HostelActivity.this, "Login attempt has timed out. Please try again.",
                            Toast.LENGTH_LONG).show();

                } else if (error instanceof NetworkError) {
                    Toast.makeText(HostelActivity.this, "Network Error", Toast.LENGTH_LONG).show();

                } else if (error instanceof ServerError) {
                    Toast.makeText(HostelActivity.this, "Server is down", Toast.LENGTH_LONG).show();

                }
                error.printStackTrace();


            }
        });

        AppSingleton.getInstance(this).addToRequestQueue(stringRequest);


    }

}

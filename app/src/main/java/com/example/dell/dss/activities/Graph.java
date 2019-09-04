package com.example.dell.dss.activities;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dell.dss.R;
import com.example.dell.dss.utilities.DrawGraph;
import com.github.mikephil.charting.charts.BarChart;

public class Graph extends AppCompatActivity {

    DrawGraph drawGraph = new DrawGraph();
    BarChart barChart;

     int xValues[] = new int[5];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);

        barChart = findViewById(R.id.barAges);

        for(int i = 0; i < 5; i++){

            xValues[i] = i;

        }
        final int yValues[] = { 20, 21, 24,27, 30};

        drawGraph.drawBarChart(xValues,yValues,barChart, "Ages", this);














    }
}

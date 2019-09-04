package com.example.dell.dss.utilities;


import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;

import androidx.core.content.ContextCompat;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.model.GradientColor;

import java.util.ArrayList;
import java.util.List;

public class DrawGraph {

    private Typeface tf;

    public DrawGraph() {

    }

    public void drawLineChart(String[] xValues, float[] yValues, LineChart lineChart, int color, String Label){



        ArrayList<Entry> line = new ArrayList<>();

        int count;

        for(count = 0; count < yValues.length; count++){

                line.add(new Entry(count,yValues[count])) ;
        }
        LineDataSet lineDataSet = new LineDataSet(line, Label);

        lineDataSet.setFillAlpha(110);
        lineDataSet.setColor(color);
        lineDataSet.setLineWidth(5f);
        lineDataSet.setFormSize(30f);
        Legend legend =lineChart.getLegend();
        legend.setTextSize(20f);
        legend.setFormSize(20f);

      XAxis xAxis = lineChart.getXAxis();
      xAxis.setTextSize(20f);
      xAxis.setValueFormatter(new FormatXAxis(xValues));
      xAxis.setGranularity(1);
      xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);









        ArrayList<ILineDataSet>  dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);
        LineData data = new LineData(dataSets);
        lineChart.setData(data);
        lineChart.notifyDataSetChanged();
        lineChart.invalidate();
    }



    public void drawBarChart(int[] xValues, int[] yValues, BarChart barChart, String Label, Context mContext){

        ArrayList<BarEntry> bar = new ArrayList<>();

        int count;
        for(count = 0; count < xValues.length; count++) {
            bar.add(new BarEntry(xValues[count], yValues[count]));
        }


        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);

        barChart.getDescription().setEnabled(false);


        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(20);
        xAxis.setTextSize(20f);






        Legend legend = barChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        legend.setForm(Legend.LegendForm.EMPTY);
        legend.setFormSize(9f);
        legend.setTextSize(16f);
        legend.setXEntrySpace(4f);






        BarDataSet barDataSet = new BarDataSet(bar, Label);
        barDataSet.setDrawIcons(false);



        int startColor1 = ContextCompat.getColor(mContext, android.R.color.holo_orange_light);
        int startColor2 = ContextCompat.getColor(mContext, android.R.color.holo_blue_light);
        int startColor3 = ContextCompat.getColor(mContext, android.R.color.holo_orange_light);
        int startColor4 = ContextCompat.getColor(mContext, android.R.color.holo_green_light);
        int startColor5 = ContextCompat.getColor(mContext, android.R.color.holo_red_light);
        int endColor1 = ContextCompat.getColor(mContext, android.R.color.holo_blue_dark);
        int endColor2 = ContextCompat.getColor(mContext, android.R.color.holo_purple);
        int endColor3 = ContextCompat.getColor(mContext, android.R.color.holo_green_dark);
        int endColor4 = ContextCompat.getColor(mContext, android.R.color.holo_red_dark);
        int endColor5 = ContextCompat.getColor(mContext, android.R.color.holo_orange_dark);

        List<GradientColor> gradientColors = new ArrayList<>();
        gradientColors.add(new GradientColor(startColor1, endColor1));
        gradientColors.add(new GradientColor(startColor2, endColor2));
        gradientColors.add(new GradientColor(startColor3, endColor3));
        gradientColors.add(new GradientColor(startColor4, endColor4));
        gradientColors.add(new GradientColor(startColor5, endColor5));

        barDataSet.setGradientColors(gradientColors);

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(barDataSet);

        BarData barData = new BarData(dataSets);
        barData.setBarWidth(0.9f);
        barData.setValueTextSize(10f);
        barChart.setData(barData);
        barChart.notifyDataSetChanged();
        barChart.invalidate();



    }


    public void drawPieChart(int[] xValues, String[] yValues, PieChart pieChart, int[] color, String Label){


        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5,5);
        pieChart.setDragDecelerationFrictionCoef(0.95f);

       // tf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");

        pieChart.setTransparentCircleColor(Color.WHITE);
        pieChart.setTransparentCircleAlpha(110);

         pieChart.setHoleRadius(58f);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);








        pieChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        pieChart.setRotationEnabled(true);
        pieChart.setHighlightPerTapEnabled(true);

        ArrayList<PieEntry> charts = new ArrayList<>();

        int count;
        for(count = 0; count < xValues.length; count++) {
            charts.add(new PieEntry(xValues[count], yValues[count]));
        }

       pieChart.animateY(1400, Easing.EaseInOutQuad);

        Legend legend = pieChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        legend.setEnabled(true);
        legend.setTextSize(15f);
        legend.setFormSize(15f);


        PieDataSet dataSet = new PieDataSet(charts, Label);
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(color);

        dataSet.setValueLinePart1OffsetPercentage(50f);
        dataSet.setValueLinePart1Length(0.2f);
        dataSet.setValueLinePart2Length(0.2f);

        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(23f);
        data.setValueTextColor(Color.BLACK);
        data.setValueTypeface(tf);

        pieChart.setData(data);

        pieChart.highlightValues(null);
        pieChart.notifyDataSetChanged();
        pieChart.invalidate();
    }


    public class FormatXAxis implements IAxisValueFormatter {

        private String[] values;

        public FormatXAxis(String[] values) {
            this.values = values;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return values[(int)value];
        }
    }


}
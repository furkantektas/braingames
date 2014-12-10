package com.furkantektas.braingames.ui.games;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.furkantektas.braingames.R;
import com.furkantektas.braingames.datatypes.GameStatInt;
import com.furkantektas.braingames.datatypes.GameType;
import com.furkantektas.braingames.datatypes.Stat;
import com.furkantektas.braingames.utils.GameStatManager;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.Legend;
import com.github.mikephil.charting.utils.LimitLine;
import com.github.mikephil.charting.utils.YLabels;

import java.util.ArrayList;

public class StatActivity extends Activity {

    private LineChart mChart;
    private int mColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);
        mColor = getResources().getColor(R.color.orange_dark);
        initStatChart();
    }

    private void initStatChart() {
        mChart = (LineChart) findViewById(R.id.chart1);
//        mChart.setOnChartGestureListener(this);
//        mChart.setOnChartValueSelectedListener(this);

        mChart.setStartAtZero(true);

        // disable the drawing of values into the chart
        mChart.setDrawYValues(false);
        mChart.setDrawXLabels(false);
        mChart.setDrawBorder(false);
        mChart.setBorderPositions(new BarLineChartBase.BorderPosition[] {
                BarLineChartBase.BorderPosition.BOTTOM
        });

        // no description text
        mChart.setDescription("");
        mChart.setNoDataTextDescription("You need to provide data for the chart.");

        // // enable / disable grid lines
        // mChart.setDrawVerticalGrid(false);
        // mChart.setDrawHorizontalGrid(false);
        //
        // // enable / disable grid background
        // mChart.setDrawGridBackground(false);
        //
        // mChart.setDrawXLegend(false);
        // mChart.setDrawYLegend(false);

        // enable value highlighting
        mChart.setHighlightEnabled(true);

        // enable touch gestures
        mChart.setTouchEnabled(true);

        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        mChart.setPinchZoom(true);

        // set an alternative background color
        // mChart.setBackgroundColor(Color.GRAY);

//        // create a custom MarkerView (extend MarkerView) and specify the layout
//        // to use for it
//        MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);
//
//        // define an offset to change the original position of the marker
//        // (optional)
//        mv.setOffsets(-mv.getMeasuredWidth() / 2, -mv.getMeasuredHeight());
//
//        // set the marker to the chart
//        mChart.setMarkerView(mv);

        // enable/disable highlight indicators (the lines that indicate the
        // highlighted Entry)
        mChart.setHighlightIndicatorEnabled(false);

        GameStatManager statManager = new GameStatManager(getBaseContext());
        // add data
        setData(statManager.readStats(GameType.COLOR_MATCH));

        mChart.animateX(500);
        mChart.setBackgroundColor(Color.WHITE);
        mChart.setValueTextColor(mColor);
        mChart.setDrawLegend(false);

        // enable / disable grid lines
        mChart.setDrawVerticalGrid(false);
        // mChart.setDrawHorizontalGrid(false);
        //
        // enable / disable grid background
        mChart.setDrawGridBackground(false);
        mChart.setGridColor(Color.BLACK & 0x2FFFFFFF);
        mChart.setGridWidth(5f);

        mChart.setBorderColor(mColor);

        YLabels y = mChart.getYLabels();
        y.setTextColor(mColor);
        y.setLabelCount(4);
//        // restrain the maximum scale-out factor
//        mChart.setScaleMinima(3f, 3f);
//
//        // center the view to a specific position inside the chart
//        mChart.centerViewPort(10, 50);

        // get the legend (only possible after setting data)
        Legend l = mChart.getLegend();
        l.setTextColor(mColor);

        // modify the legend ...
        // l.setPosition(LegendPosition.LEFT_OF_CHART);
        l.setForm(Legend.LegendForm.LINE);

        // // dont forget to refresh the drawing
        // mChart.invalidate();
    }

    private void setData(Stat t) {
        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i < t.getScores().size(); i++) {
            xVals.add((i) + "");
        }

        ArrayList<Entry> yVals = new ArrayList<Entry>();

        for (int i = 0; i < t.getScores().size();++i) {
            yVals.add(new Entry(t.getScores().get(i).getScore(), i));
        }

        // create a dataset and give it a type
        LineDataSet set1 = new LineDataSet(yVals, "");
        // set1.setFillAlpha(110);
        // set1.setFillColor(Color.RED);

        // set the line to be drawn like this "- - - - - -"
        set1.enableDashedLine(10f, 5f, 0f);
        set1.setColor(mColor);
        set1.setCircleColor(mColor);
        set1.setLineWidth(2f);
        set1.setCircleSize(5f);
        set1.setFillAlpha(100);
        set1.setFillColor(mColor);
//        set1.setShader(new LinearGradient(0, 0, 0, mChart.getHeight(),
        // Color.BLACK, mColor, Shader.TileMode.MIRROR));

        ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
        dataSets.add(set1); // add the datasets

        // create a data object with the datasets
        LineData data = new LineData(xVals, dataSets);

        // set data
        mChart.setData(data);
    }
}

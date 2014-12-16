package com.furkantektas.braingames.ui;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.furkantektas.braingames.R;
import com.furkantektas.braingames.datatypes.GameType;
import com.furkantektas.braingames.datatypes.Stat;
import com.furkantektas.braingames.utils.GameStatManager;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.Legend;
import com.github.mikephil.charting.utils.YLabels;

import java.util.ArrayList;

public class StatActivity extends ActionBarActivity {

    private LineChart mChart;
    private int mColor;
    private GameStatManager mStatManager;
    private ArrayList<String> gameList = new ArrayList<String>();
    private Dialog mGameListDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        setContentView(R.layout.activity_stat);
        mColor = getResources().getColor(R.color.orange_dark);
        updateChart(GameType.MEMORY_MATRIX);
    }

    private void initStatChart() {
        mChart = (LineChart) findViewById(R.id.chart1);

        mChart.setStartAtZero(true);

        // disable the drawing of values into the chart
        mChart.setDrawYValues(false);
        mChart.setDrawXLabels(false);
        mChart.setDrawBorder(false);
        mChart.setBorderPositions(new BarLineChartBase.BorderPosition[] {
                BarLineChartBase.BorderPosition.BOTTOM
        });

        // no description text
        mChart.setNoDataText(getResources().getString(R.string.no_game_data_title));
        mChart.setNoDataTextDescription(getResources().getString(R.string.no_game_data_title_desc));


        // enable value highlighting
        mChart.setHighlightEnabled(true);

        // enable touch gestures
        mChart.setTouchEnabled(true);

        mChart.setDescription("");


        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);

        mChart.setPinchZoom(true);

        mChart.setHighlightIndicatorEnabled(false);

        mStatManager = new GameStatManager(getBaseContext());

        mChart.animateX(500);
        mChart.setBackgroundColor(Color.WHITE);
        mChart.setValueTextColor(mColor);
        mChart.setDrawLegend(false);

        mChart.setDrawVerticalGrid(false);
        mChart.setDrawGridBackground(false);
        mChart.setGridColor(Color.BLACK & 0x2FFFFFFF);
        mChart.setGridWidth(5f);

        mChart.setBorderColor(mColor);

        YLabels y = mChart.getYLabels();
        y.setTextColor(mColor);
        y.setLabelCount(4);
    }

    private void updateChart(GameType type) {
        if(mChart == null)
            initStatChart();
        setData(mStatManager.readStats(type));

        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle(type.getStrResId());
        }
        mChart.notifyDataSetChanged();
        mChart.animateX(500);
    }

    private void setData(Stat t) {
        if(t == null) {
            mChart.clear();
            return;
        }

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
        Legend l = mChart.getLegend();
        l.setTextColor(mColor);

        // modify the legend ...
        // l.setPosition(LegendPosition.LEFT_OF_CHART);
        l.setForm(Legend.LegendForm.LINE);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_stat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case R.id.action_game_list:
                showGameListDialog();
                return true;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return true;
        }
    }

    private void showGameListDialog() {
        if(mGameListDialog == null) {
            if (gameList.size() < 1)
                for (GameType game : GameType.values())
                    gameList.add(getResources().getString(game.getStrResId()));

            ListView listView = new ListView(this);


            mGameListDialog = new Dialog(this);
//        dialog.setContentView(R.layout.list_layout);
            mGameListDialog.setTitle(R.string.pick_game_title);
//        ListView listView = (ListView) dialog.findViewById(R.id.list);
            ArrayAdapter<String> ad = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, gameList);
            listView.setAdapter(ad);
            mGameListDialog.setContentView(listView);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {
                    mGameListDialog.dismiss();
                    updateChart(GameType.values()[pos]);
                }
            });


        }
        mGameListDialog.show();
    }
}

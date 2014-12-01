package com.furkantektas.braingames.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.furkantektas.braingames.datatypes.Game;
import com.furkantektas.braingames.datatypes.GameStat;
import com.furkantektas.braingames.ui.games.GameCalculateFastActivity;
import com.furkantektas.braingames.utils.GameStatManager;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;
import com.furkantektas.braingames.R;

/**
 * Created by Sinan NAR on 28/11/14.
 */
public class GraphActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        // init example series data
        GraphViewSeries exampleSeries = new GraphViewSeries(new GraphViewData[] {
                new GraphViewData(1, 2.0d)
                , new GraphViewData(2, 1.5d)
                , new GraphViewData(3, 2.5d)
                , new GraphViewData(4, 1.0d)
        });

        GraphView graphView = new LineGraphView(
                this // context
                , "" // heading
        );


        LinearLayout layout = (LinearLayout) findViewById(R.id.graph_layout);
        graphView.addSeries(exampleSeries);
        layout.addView(graphView);
    }

}

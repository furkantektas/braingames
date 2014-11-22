package com.furkantektas.braingames.ui.games;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.furkantektas.braingames.R;
import com.furkantektas.braingames.data.MemorizeMatrixAdapter;

public class GameMemoryMatrix extends AbstractGameActivity {
    private GridView mGridView;
    private MemorizeMatrixAdapter mAdapter;
    private int mSize = MIN_LEVEL;
    private int mScore = 0;
    private static final int MIN_LEVEL = 4;
    private static final int MAX_LEVEL = 10;
    private static final int NUM_LEVEL = MAX_LEVEL - MIN_LEVEL + 1;
    private int mNumLevel = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_memory_matrix);
        mGridView = (GridView) findViewById(R.id.grid_view);
        initAdapter(mSize);
    }

    private void initAdapter(int size) {
        mAdapter = new MemorizeMatrixAdapter(getApplicationContext(),size, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                positiveSound();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                negativeSound();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextLevel();
            }
        });
        mGridView.setNumColumns(size);
        mGridView.setAdapter(mAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_memory_matrix, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void nextLevel() {
        ++mNumLevel;
        mScore += Math.pow(mAdapter.getTotalAnswers(),2) * mAdapter.getCorrectResultNum();

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mNumLevel < NUM_LEVEL && mSize < MAX_LEVEL) {
                    if(mAdapter.getCorrectResultNum() == mAdapter.getTotalAnswers())
                        ++mSize;
                    initAdapter(mSize);
                } else {
                    finishGame();
                }
            }
        },2000);
    }

    @Override
    public boolean hasReadyScreen() {
        return false;
    }

    @Override
    public void startGame() {

    }

    @Override
    public int getScore() {
        return mScore;
    }
}

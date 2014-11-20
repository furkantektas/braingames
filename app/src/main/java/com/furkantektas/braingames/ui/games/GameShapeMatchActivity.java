package com.furkantektas.braingames.ui.games;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterViewFlipper;
import android.widget.Toast;

import com.furkantektas.braingames.R;
import com.furkantektas.braingames.data.ShapeMatchAdapter;
import com.furkantektas.braingames.datatypes.GameType;

/**
 * Created by Furkan Tektas on 11/20/14.
 */
public class GameShapeMatchActivity extends AbstractCountDownTimerGameActivity {
    private AdapterViewFlipper mAdapterFlipper;
    private static final int QUESTION_NUM = 25;
    private ShapeMatchAdapter mAdapter;

    @Override
    public boolean hasReadyScreen() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setGameName(getApplicationContext().getResources().getString(R.string.game_shape_match));
        setGameType(GameType.SHAPE_MATCH);
        setContentView(R.layout.activity_game_shape_match);

        mAdapterFlipper = (AdapterViewFlipper)findViewById(R.id.flipper);

        mAdapter = new ShapeMatchAdapter(QUESTION_NUM, this, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAdapterFlipper.showNext();
                positiveSound();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAdapterFlipper.showNext();
                negativeSound();
            }
        });

        mAdapterFlipper.setAdapter(mAdapter);
        mAdapterFlipper.setVisibility(View.INVISIBLE);
    }

    @Override
    public void startGame() {
        startTimer();
        mAdapterFlipper.setVisibility(View.VISIBLE);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapterFlipper.showNext();
            }
        }, 1000);
    }

    @Override
    public void finishGame() {
        Toast.makeText(getApplicationContext(), "Correct Results: " + mAdapter.getCorrectResults(), Toast.LENGTH_LONG).show();
        super.finishGame();
    }

    @Override
    public int getScore() {
        return (int) Math.round((mAdapter.getCorrectResults()/(float)mAdapter.getAnsweredQuestionCount()) * 1000);
    }
}

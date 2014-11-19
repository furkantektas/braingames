package com.furkantektas.braingames.ui.games;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterViewFlipper;
import android.widget.Toast;

import com.furkantektas.braingames.R;
import com.furkantektas.braingames.data.ColorMatchAdapter;
import com.furkantektas.braingames.datatypes.GameType;

public class GameColorMatchActivity extends AbstractTimerGameActivity {
    private String mGameName;
    private GameType mGameType = GameType.COLOR_MATCH;

    private AdapterViewFlipper mAdapterFlipper;
    private ColorMatchAdapter mAdapter;

    private static final int QUESTION_NUM = 25;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // show tutorial if user plays this game for the first time
//        if(getShowTutorial()) {
//            Intent i = new Intent(getApplicationContext(),TutColorMatchActivity.class);
//            startActivity(i);
//        }
        setContentView(R.layout.activity_game_color_match);


        mAdapterFlipper = (AdapterViewFlipper)findViewById(R.id.flipper);

        mAdapter = new ColorMatchAdapter(QUESTION_NUM, this, new View.OnClickListener() {
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
    }

    @Override
    public void finishGame() {
        stopTimer();
        Toast.makeText(getApplicationContext(),"Correct Results: "+mAdapter.getCorrectResults()+ "Time:"+getElapsedTime()/1000+"sec",Toast.LENGTH_LONG).show();
        super.finishGame();
    }

    @Override
    public boolean hasReadyScreen() {
        return true;
    }

    @Override
    public String getGameName() {
        if(mGameName == null)
            mGameName = getApplicationContext().getResources().getString(R.string.game_color_match);
        return mGameName;
    }

    @Override
    public void setGameName(String name) {
        mGameName = name;
    }

    @Override
    public GameType getGameType() {
        return mGameType;
    }

    @Override
    public void setGameType(GameType type) {
        mGameType = type;
    }

    @Override
    public int getScore() {
        return mAdapter.getCorrectResults();
    }
}

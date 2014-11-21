package com.furkantektas.braingames.ui.games;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterViewFlipper;
import android.widget.Toast;

import com.furkantektas.braingames.R;
import com.furkantektas.braingames.data.FindOperationAdapter;
import com.furkantektas.braingames.datatypes.GameCategory;
import com.furkantektas.braingames.datatypes.GameType;

/**
 * Created by Sinan NAR on 21/11/14.
 */
public class GameFindOperationActivity extends AbstractCountDownTimerGameActivity{
    private AdapterViewFlipper mAdapterFlipper;
    private FindOperationAdapter mAdapter;

    private static final int QUESTION_NUM = 25;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setGameName(getApplicationContext().getResources().getString(R.string.game_find_operation));
        setGameCategory(GameCategory.PROBLEM_SOLVING);
        setGameType(GameType.FIND_OPERATION);

        setContentView(R.layout.activity_game_find_operation);

        mAdapterFlipper = (AdapterViewFlipper)findViewById(R.id.flipper);

        mAdapter = new FindOperationAdapter(QUESTION_NUM, this,
            new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAdapterFlipper.showNext();
                positiveSound();
            }
        },
            new View.OnClickListener() {
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
        Toast.makeText(getApplicationContext(),"Correct Results: "+mAdapter.getCorrectResults(),Toast.LENGTH_LONG).show();
        super.finishGame();
    }

    @Override
    public boolean hasReadyScreen() {
        return true;
    }

    /**
     * TODO: when user answers 1 question, gets a high score
     * @return
     */
    @Override
    public int getScore() {
        return (int) Math.round((mAdapter.getCorrectResults()/(float)mAdapter.getAnsweredQuestionCount()) * 1000);
    }

}

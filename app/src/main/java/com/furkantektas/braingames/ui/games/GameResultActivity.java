package com.furkantektas.braingames.ui.games;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.furkantektas.braingames.R;
import com.furkantektas.braingames.ui.MainActivity;

import org.w3c.dom.Text;

public class GameResultActivity extends Activity {

    public static final String ARG_SCORE = "gameScore";
    public static final String ARG_GAME_CLASS = "gameClass";
    public static final String ARG_HIGH_SCORE = "gameIsHighScore";

    private String mGameClass;
    private int mGameScore;
    private boolean mIsHighScore = false;

    private TextView mScoreView;
    private Button mReplayButton;
    private Button mMainMenuButton;
    private TextView mHighScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            Bundle bundle = getIntent().getExtras();
            if(bundle != null) {
                mGameClass = bundle.getString(ARG_GAME_CLASS);
                mGameScore = bundle.getInt(ARG_SCORE,0);
                mIsHighScore = bundle.getBoolean(ARG_HIGH_SCORE);
            }
        } else {
            mGameClass = savedInstanceState.getString(ARG_GAME_CLASS);
            mGameScore = savedInstanceState.getInt(ARG_SCORE,0);
            mIsHighScore = savedInstanceState.getBoolean(ARG_HIGH_SCORE);
        }

        setContentView(R.layout.activity_game_result);
        mScoreView = (TextView) findViewById(R.id.score_tv);
        mHighScore = (TextView) findViewById(R.id.high_score);
        mHighScore.setVisibility(mIsHighScore ? View.VISIBLE : View.GONE);
        mReplayButton = (Button) findViewById(R.id.replay_button);
        mMainMenuButton = (Button) findViewById(R.id.menu_button);
        mScoreView.setText(String.valueOf(mGameScore));
        mReplayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnWithResult(AbstractGameActivity.finalActions.RESTART);
            }
        });
        mMainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnWithResult(AbstractGameActivity.finalActions.RETURN_TO_MAIN_MENU);
            }
        });

    }

    public void returnWithResult(AbstractGameActivity.finalActions f) {
        Intent i = new Intent();
        i.putExtra(AbstractGameActivity.ARG_ACTION,f.ordinal());
        setResult(RESULT_OK,i);
        finish();
    }
}

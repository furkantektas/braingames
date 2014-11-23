package com.furkantektas.braingames.ui.games;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.furkantektas.braingames.R;
import com.furkantektas.braingames.data.SFX;
import com.furkantektas.braingames.datatypes.Game;
import com.furkantektas.braingames.datatypes.GameCategory;
import com.furkantektas.braingames.datatypes.GameStat;
import com.furkantektas.braingames.datatypes.GameType;
import com.furkantektas.braingames.ui.MainActivity;
import com.furkantektas.braingames.ui.ReadyScreenFragment;
import com.furkantektas.braingames.utils.GameStatManager;

import java.util.Date;

/**
 * AbstractGameActivity is the predecessor class of all game activity
 * classes. It handles the tutorial showing preferences by using getGameType
 * method of Game interface.
 * Created by Furkan Tektas on 11/14/14.
 */
public abstract class AbstractGameActivity extends ActionBarActivity implements Game {
    private boolean mShowTutorial;
    private static final String SHARED_PREF_NAME = "GAME_PREFERENCES";
    private GameStat mGameStat = new GameStat();
    private SharedPreferences mPreferences;
    private Fragment mReadyScreenFragment;
    private static SFX mSFX;
    private static GameStatManager mGameStatManager;

    public static final int GAME_RESULT_CODE = 0;
    public static final String ARG_ACTION = "FinalAction";

    public static enum finalActions {
        RETURN_TO_MAIN_MENU(0),
        RESTART(1);

        int ind;

        finalActions(int ind) {
            this.ind = ind;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSFX = MainActivity.getSFX();
        mGameStatManager = MainActivity.getGameStatManager();
        setPreferences(getApplicationContext().getSharedPreferences(SHARED_PREF_NAME, android.content.Context.MODE_PRIVATE));
    }

    @Override
    public void setShowTutorial(boolean showTutorial) {
        mShowTutorial = showTutorial;
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putBoolean(getGameType().toString(), showTutorial);
        editor.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(hasReadyScreen())
            showReadyScreen();
    }

    @Override
    public boolean getShowTutorial() {
        return getPreferences().getBoolean(getGameType().toString(), true);
    }

    public SharedPreferences getPreferences() {
        return mPreferences;
    }

    public void setPreferences(SharedPreferences mPreferences) {
        this.mPreferences = mPreferences;
    }

    public SFX getSFX() {
        return mSFX;
    }

    @Override
    public void positiveSound() {
        mSFX.pop();
    }

    @Override
    public void negativeSound() {
        mSFX.deny();
    }


    @Override
    public void showReadyScreen() {
        mReadyScreenFragment = ReadyScreenFragment.newInstance(this);
        getFragmentManager().beginTransaction()
                .add(R.id.overlay_fragment_container, mReadyScreenFragment)
                .commit();
    }

    @Override
    public void hideReadyScreen() {
        getFragmentManager().beginTransaction().remove(mReadyScreenFragment).commit();
        startGame();
    }

    @Override
    public void finishGame() {
        System.gc(); // clean up
        mGameStat.setScore(getScore());
        boolean isHighScore = mGameStatManager.saveGame(this);
        System.out.println("isHighScore:"+isHighScore);
        Intent i = new Intent(getApplicationContext(),GameResultActivity.class);
        i.putExtra(GameResultActivity.ARG_GAME_CLASS,this.getClass().toString());
        i.putExtra(GameResultActivity.ARG_SCORE,getScore());
        i.putExtra(GameResultActivity.ARG_HIGH_SCORE,isHighScore);
        startActivityForResult(i, GAME_RESULT_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GAME_RESULT_CODE) {
            if (resultCode == RESULT_OK) {
                Class cl = null;
                if(data != null) {
                    int action = (data.getExtras().getInt(ARG_ACTION));
                    if(action == finalActions.RETURN_TO_MAIN_MENU.ind)//it was .ordinal
                        cl = MainActivity.class;
                    else if(action == finalActions.RESTART.ind)
                        cl = this.getClass();
                    if(cl != null)
                        startActivity(new Intent(getApplicationContext(), cl));
                }
            }
        }
    }

    @Override
    public Date getTime() {
        return mGameStat.getTime();
    }

    @Override
    public void setTime(Date date) {
        mGameStat.setTime(date);
    }

    @Override
    public void setScore(int score) {
        mGameStat.setScore(score);
    }

    @Override
    public String getGameName() {
        return mGameStat.getGameName();
    }

    @Override
    public void setGameName(String name) {
        mGameStat.setGameName(name);
    }

    @Override
    public GameType getGameType() {
        return mGameStat.getGameType();
    }

    @Override
    public void setGameType(GameType type) {
        mGameStat.setGameType(type);
    }

    @Override
    public GameCategory getGameCategory() {
        return mGameStat.getGameCategory();
    }

    @Override
    public void setGameCategory(GameCategory category) {
        mGameStat.setGameCategory(category);
    }
}

package com.furkantektas.braingames.ui.games;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

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
import java.util.Random;

/**
 * AbstractGameActivity is the predecessor class of all game activity
 * classes. It handles the tutorial showing preferences by using getGameType
 * method of Game interface.
 * Created by Furkan Tektas on 11/14/14.
 */
public abstract class AbstractGameActivity extends Activity implements Game {
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
        init();
        setPreferences(getApplicationContext().getSharedPreferences(SHARED_PREF_NAME, android.content.Context.MODE_PRIVATE));
    }


    private void init() {
        /* Getting SFX from MainActivity */
        if(mSFX == null)
            mSFX = MainActivity.getSFX();

        /* If SFX is still null (sometimes it is) create a new SFX */
        if(mSFX == null)
            mSFX = new SFX(getApplicationContext());

        if(mGameStatManager == null)
            mGameStatManager = new GameStatManager(getApplicationContext());
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
        mGameStat.setScore(getScore());
        boolean isHighScore = mGameStatManager.saveGame(mGameStat);
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

    public static Class getRandomGameIntent() {
        Random random = new Random();
        GameType[] gameTypes = GameType.values();
        GameType type = gameTypes[random.nextInt(gameTypes.length)];
        switch (type) {
            case SHAPE_MATCH:
                return GameShapeMatchActivity.class;
            case COLOR_MATCH:
                return GameColorMatchActivity.class;
            case FIND_OPERATION:
                return GameFindOperationActivity.class;
            case MEMORY_MATRIX:
                return GameMemoryMatrix.class;
            case CALCULATE_FAST:
                return GameCalculateFastActivity.class;
            case COMPARE_FAST:
                return GameCompareFastActivity.class;
            default:
                return GameMemoryMatrix.class;
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

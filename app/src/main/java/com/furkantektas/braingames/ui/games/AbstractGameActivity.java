package com.furkantektas.braingames.ui.games;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

/**
 * AbstractGameActivity is the predecessor class of all game activity
 * classes. It handles the tutorial showing preferences by using getGameType
 * method of Game interface.
 * Created by Furkan Tektas on 11/14/14.
 */
public abstract class AbstractGameActivity extends ActionBarActivity implements Game {
    private boolean mShowTutorial;
    private static final String SHARED_PREF_NAME = "GAME_PREFERENCES";
    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPreferences(getApplicationContext().getSharedPreferences(SHARED_PREF_NAME, android.content.Context.MODE_PRIVATE));
        // setting showTutorial value
        mShowTutorial = getPreferences().getBoolean(getGameType().toString(), true);
    }

    @Override
    public void setShowTutorial(boolean showTutorial) {
        mShowTutorial = showTutorial;
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putBoolean(getGameType().toString(), showTutorial);
        editor.commit();
    }

    @Override
    public boolean getShowTutorial() {
        return mShowTutorial;
    }

    public SharedPreferences getPreferences() {
        return mPreferences;
    }

    public void setPreferences(SharedPreferences mPreferences) {
        this.mPreferences = mPreferences;
    }
}

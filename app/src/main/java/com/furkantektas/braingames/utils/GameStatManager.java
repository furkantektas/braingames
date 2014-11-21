package com.furkantektas.braingames.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.furkantektas.braingames.datatypes.Game;
import com.furkantektas.braingames.datatypes.GameCategory;
import com.furkantektas.braingames.datatypes.GameStatInt;
import com.furkantektas.braingames.datatypes.Stat;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Saves/loads game data to/from SharedPreferences. It should be saved to sqlite
 * database but this project is developed as a term project and has a constrained that
 * we cannot use databases. So. we hijacked constraint by using shared preferences :)
 *
 * Created by Furkan Tektas on 11/19/14.
 */
public class GameStatManager {
    private Context mContext;
    private Gson mGson;
    private static final String STAT_KEY = "stat";

    public GameStatManager(Context context) {
        mContext = context;
        mGson = new Gson();
    }

    /**
     * Writes game's stats and returns true if user's score is higher than
     * high score.
     * @param g
     * @return true if game score high score. Otherwise, returns false
     */
    public boolean saveGame(GameStatInt g) {
        SharedPreferences prefs = mContext.getSharedPreferences(g.getGameName(), Context.MODE_PRIVATE);
        if(prefs == null)
            return false;
        Stat stat = readStats(prefs);
        boolean isHighScore = false;

        if(stat == null) { // first game
            stat = new Stat();
            stat.setHighScore(g.getScore());
            isHighScore = true;
        }

        if(g.getScore() > stat.getHighScore()) {
            isHighScore = true;
            stat.setHighScore(g.getScore());
        }

        writeStats(stat, prefs);
        return isHighScore;
    }

    private Stat readStats(GameStatInt g) {
        SharedPreferences prefs = mContext.getSharedPreferences(g.getGameName(), Context.MODE_PRIVATE);
        return readStats(prefs);
    }

    private Stat readStats(SharedPreferences prefs) {
        String json = prefs.getString(STAT_KEY, null);
        if(json == null)
            return null;
        return mGson.fromJson(json,Stat.class);
    }

    private void writeStats(Stat stat, SharedPreferences prefs) {
        SharedPreferences.Editor prefEditor  = prefs.edit();
        String json = mGson.toJson(stat);
        prefEditor.putString(STAT_KEY,json);
        prefEditor.commit();
    }

}

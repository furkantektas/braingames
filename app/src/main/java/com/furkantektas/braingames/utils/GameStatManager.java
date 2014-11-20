package com.furkantektas.braingames.utils;

import android.content.Context;

import com.furkantektas.braingames.datatypes.Game;
import com.furkantektas.braingames.datatypes.GameStatInt;

/**
 * Created by Furkan Tektas on 11/19/14.
 */
public class GameStatManager {
    private Context mContext;
    private static final String STAT_MEMORY_KEY = "stat_mem";
    private static final String STAT_ATTENTION_KEY = "stat_attention";
    private static final String STAT_PROBLEM_SOLVING_KEY = "stat_problem_solving";

    public GameStatManager(Context context) {
        mContext = context;
    }

    public void saveGame(GameStatInt g) {

    }

}

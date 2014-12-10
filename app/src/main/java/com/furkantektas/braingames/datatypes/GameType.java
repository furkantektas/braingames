package com.furkantektas.braingames.datatypes;

import com.furkantektas.braingames.R;

/**
 * Created by Furkan Tektas on 11/14/14.
 */
public enum GameType {
    SHAPE_MATCH(R.string.game_shape_match),
    COLOR_MATCH(R.string.game_color_match),
    FIND_OPERATION(R.string.game_find_operation),
    MEMORY_MATRIX(R.string.game_memory_matrix),
    CALCULATE_FAST(R.string.game_calculate_fast),
    COMPARE_FAST(R.string.game_compare_fast);

    private int strResId;

    private GameType(int strResId) {
        this.strResId = strResId;
    }

    public int getStrResId() {
        return strResId;
    }
}

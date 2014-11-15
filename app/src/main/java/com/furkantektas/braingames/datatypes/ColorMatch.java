package com.furkantektas.braingames.datatypes;

import com.furkantektas.braingames.R;

/**
 * Created by Furkan Tektas on 11/15/14.
 */
public class ColorMatch {
    public ColorNames colorName;
    public Color color;

    public ColorMatch(ColorNames colorName, Color color) {
        this.colorName = colorName;
        this.color = color;
    }

    public boolean isTrue() {
        return colorName.toString().toLowerCase().equals(color.toString().toLowerCase());
    }

    public static enum Color {
        RED(R.color.red),
        GREEN(R.color.green),
        ORANGE(R.color.orange),
        BLUE(R.color.blue),
        BLACK(R.color.black);

        private int resId;

        private Color(int resId) {
            this.resId = resId;
        }

        public int getResId() {
            return resId;
        }
    }

    public static enum ColorNames {
        RED(R.string.red),
        GREEN(R.string.green),
        ORANGE(R.string.orange),
        BLUE(R.string.blue),
        BLACK(R.string.black);

        private int resId;

        private ColorNames(int resId) {
            this.resId = resId;
        }

        public int getResId() {
            return resId;
        }
    }
}

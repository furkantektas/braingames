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
        YELLOW(R.color.yellow),
        BROWN(R.color.brown),
        PINK(R.color.pink),
        PURPLE(R.color.purple),
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
        YELLOW(R.string.yellow),
        BROWN(R.string.brown),
        PINK(R.string.pink),
        PURPLE(R.string.purple),
        BLACK(R.string.black);

        private int resId;

        private ColorNames(int resId) {
            this.resId = resId;
        }

        public int getResId() {
            return resId;
        }
    }

    public static Color generateColor(int ind) {
        int i = 0;
        Color[] colors = Color.values();

        if(ind > colors.length)
            ind = (ind + colors.length) % colors.length;

        return colors[ind];
    }

    public static ColorNames generateColorName(int ind) {
        int i = 0;
        ColorNames[] colorNames = ColorNames.values();

        if(ind > colorNames.length)
            ind = (ind + colorNames.length) % colorNames.length;

        return colorNames[ind];
    }
}

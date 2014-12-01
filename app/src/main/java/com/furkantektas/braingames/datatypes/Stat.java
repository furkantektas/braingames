package com.furkantektas.braingames.datatypes;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Furkan Tektas on 11/21/14.
 */
public class Stat implements Parcelable {
    private int mHighScore = 0;
    private List<GameStat> mScores = new ArrayList<GameStat>();

    public int getHighScore() {
        return mHighScore;
    }

    public void setHighScore(int mHighScore) {
        this.mHighScore = mHighScore;
    }

    public List<GameStat> getScores() {
        return mScores;
    }

    public void setScores(List<GameStat> mScores) {
        this.mScores = mScores;
    }

    public void addScore(GameStat score) {
        mScores.add(score);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mHighScore);
        parcel.writeList(mScores);
    }
}

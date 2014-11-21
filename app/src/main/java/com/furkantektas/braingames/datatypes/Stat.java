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
    private List<GameStatInt> mScores = new ArrayList<GameStatInt>();

    public int getHighScore() {
        return mHighScore;
    }

    public void setHighScore(int mHighScore) {
        this.mHighScore = mHighScore;
    }

    public List<GameStatInt> getScores() {
        return mScores;
    }

    public void setScores(List<GameStatInt> mScores) {
        this.mScores = mScores;
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

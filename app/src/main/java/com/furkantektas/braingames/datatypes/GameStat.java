package com.furkantektas.braingames.datatypes;

import java.util.Date;

/**
 * Created by Furkan Tektas on 11/19/14.
 */
public class GameStat implements GameStatInt {
    private GameType mGameType;
    private String mGameName;
    private int mScore;
    private Date mTime;
    private GameCategory mGameCategory;

    public GameStat() {
        mTime = new Date();
    }

    public GameStat(GameType gameType, String gameName) {
        this();
        mGameType = gameType;
        mGameName = gameName;
    }

    @Override
    public String getGameName() {
        return mGameName;
    }

    @Override
    public void setGameName(String name) {
        mGameName = name;
    }

    @Override
    public GameType getGameType() {
        return mGameType;
    }

    @Override
    public void setGameType(GameType type) {
        mGameType = type;
    }

    @Override
    public int getScore() {
        return mScore;
    }

    @Override
    public void setScore(int score) {
        mScore = score;
    }

    @Override
    public Date getTime() {
        return mTime;
    }

    @Override
    public void setTime(Date date) {
        mTime = date;
    }

    @Override
    public void setGameCategory(GameCategory category) {
        mGameCategory = category;
    }

    @Override
    public GameCategory getGameCategory() {
        return mGameCategory;
    }
}

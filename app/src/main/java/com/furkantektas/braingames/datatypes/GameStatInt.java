package com.furkantektas.braingames.datatypes;

import java.util.Date;

/**
 * Created by Furkan Tektas on 11/19/14.
 */
public interface GameStatInt {

    /**
     * Returns game's name in localized language.
     * @return game name
     */
    public String getGameName();

    /**
     * Sets game's name (always set localized name)
     * @param name new game name
     */
    public void setGameName(String name);

    /**
     * Returns game's type.
     * @return GameType enum
     */
    public GameType getGameType();

    /**
     * Sets game's type.
     * @param type
     */
    public void setGameType(GameType type);

    /**
     * Returns game score
     * @return game score
     */
    public int getScore();

    /**
     * Sets score of the game
     */
    public void setScore(int score);

    /**
     * Returns the datetime of the game
     * @return
     */
    public Date getTime();

    /**
     * Sets the datetime of game.
     * @param date
     */
    public void setTime(Date date);
}

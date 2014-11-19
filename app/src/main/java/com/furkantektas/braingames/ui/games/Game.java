package com.furkantektas.braingames.ui.games;

import com.furkantektas.braingames.datatypes.GameType;

/**
 * Created by Furkan Tektas on 11/14/14.
 */
public interface Game {
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
     * Returns true if game has not been played and tutorial should be shown.
     * @return true for first game
     */
    public boolean getShowTutorial();

    /**
     * Sets new showTutorial value.
     * @param showTutorial new showTutorial value.
     */
    public void setShowTutorial(boolean showTutorial);

    /**
     * If game is not starts immediately, returns true to
     * show ready screen.
     * @return
     */
    public boolean hasReadyScreen();

    /**
     * Displays a ready screen, when user is ready,
     * calls hideReadyScreen().
     */
    public void showReadyScreen();

    /**
     * Hides ready screen and calls startGame()
     */
    public void hideReadyScreen();

    /**
     * Starts the game. hideReadyScreen() can
     * call this function.
     */
    public void startGame();

    /**
     * Plays positive sound.
     */
    public void positiveSound();

    /**
     * Plays negative sound.
     */
    public void negativeSound();

    /**
     * Finishes the current game.
     */
    public void finishGame();

    /**
     * Returns game score
     * @return game score
     */
    public int getScore();
}

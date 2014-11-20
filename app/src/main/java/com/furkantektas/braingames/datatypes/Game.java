package com.furkantektas.braingames.datatypes;

/**
 * Created by Furkan Tektas on 11/14/14.
 */
public interface Game extends GameStatInt {

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

}

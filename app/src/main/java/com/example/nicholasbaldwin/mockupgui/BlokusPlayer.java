package com.example.nicholasbaldwin.mockupgui;

import android.widget.TextView;

import java.util.ArrayList;

/**
 * <!-- class BlokusPlayer-->
 * <p>
 * This class initializes a user or computer (AI) player that
 * does actions and plays the game. It also initializes each
 * players inventory by setting the default 21 pieces in an
 * ArrayList.
 *
 * @author <Justin Cao, Dylan Pascua, Nicholas Baldwin>
 * @version <Spring 2019>
 */
public class BlokusPlayer {

    protected String playerName;
    protected int playerColor;
    protected int piecesRemaining;

    // As initialized in the BlokusGameState for testing purposes
    // (0)local human,(1)Dumb AI,(2)Smart AI,(3)Network player
    protected int playerType;
    protected int playerScore;
    protected int playerID;

    protected ArrayList<Piece> piecesInventory = new ArrayList<>();

    //Every player has 21 pieces to select from that have a total value of 89 points
    private static final int INITIAL_PIECES_REMAINING = 21;
    private static final int INITIAL_SCORE = 89;

    /**
     * Constructor for the BlokusPlayer class
     * <p>
     * Initializes a player with default values and passed in
     * parameters, those of which will be chosen by the player
     * in the Game config GUI.
     *
     * @param initName       Name of the player
     * @param initColor      Chosen color of a player
     * @param initPlayerType Type of player (Human, AI, or Network)
     * @param initID         ID used to determine the turn order
     */
    public BlokusPlayer(String initName, int initColor, int initPlayerType,
                        int initID) {
        //Store passed in parameters and current class constants into
        //instance variable to be modified throughout the game.
        playerName = initName;
        playerColor = initColor;
        piecesRemaining = INITIAL_PIECES_REMAINING;
        playerType = initPlayerType;
        playerScore = INITIAL_SCORE;
        playerID = initID;
    }

    //Setters and Getters
    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String name) {
        this.playerName = name;
    }

    public int getPiecesRemaining() {
        return piecesRemaining;
    }

    public void setPiecesRemaining(int piecesVal) {
        this.piecesRemaining = piecesVal;
    }

    public int getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(int colorVal) {
        this.playerColor = colorVal;
    }

    public int getPlayerType() {
        return playerType;
    }

    public void setPlayerType(int playerType) {
        this.playerType = playerType;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int scoreVal) {
        this.playerScore = scoreVal;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int id) {
        playerID = id;
    }

    public ArrayList<Piece> getPiecesInventory() {
        return piecesInventory;
    }

}
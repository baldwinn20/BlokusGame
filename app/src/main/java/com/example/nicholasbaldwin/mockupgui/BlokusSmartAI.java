package com.example.nicholasbaldwin.mockupgui;

import com.example.nicholasbaldwin.mockupgui.game.infoMsg.GameInfo;
import com.example.nicholasbaldwin.mockupgui.game.util.GameComputerPlayer;

import java.util.ArrayList;

/**
 * <!-- class BlokusSmartAI -->
 * <p>
 * This class implements the "smart" computer player.
 * It picks the largest piece remaining by value and
 * plays it in the first legal spot.
 *
 * @author <Nicholas Baldwin, Justin Cao, Dylan Pascua>
 * @version <Spring 2019>
 */

public class BlokusSmartAI extends GameComputerPlayer {
    //All instance variables
    public BlokusGameState localState;
    private int playerColor, piecesRemaining, PlayerScore, stage, playerID;
    private int playerType = 1; // Dumb AI players are all of type 1
    private int playerScore;
    private Piece currentPiece;
    private ArrayList<Piece> piecesInventory;
    public int INITIAL_PIECES_rEMAINING = 21;
    public int INITIAL_SCORE = 89;

    public BlokusSmartAI(String initName, int initColor, int initID) {
        super(initName);
        playerColor = initColor;
        playerID = initID;
        piecesRemaining = INITIAL_PIECES_rEMAINING;
        playerScore = INITIAL_SCORE;
    }

    public void smartMove(ArrayList<Piece> currentList) {

    }

    @Override
    protected void receiveInfo(GameInfo info) {

    }
}

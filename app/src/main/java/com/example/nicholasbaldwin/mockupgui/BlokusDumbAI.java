package com.example.nicholasbaldwin.mockupgui;

import com.example.nicholasbaldwin.mockupgui.game.infoMsg.GameInfo;
import com.example.nicholasbaldwin.mockupgui.game.util.GameComputerPlayer;

import java.util.ArrayList;

public class BlokusDumbAI extends GameComputerPlayer {
    //All instance variables
    public BlokusGameState localState;
    private int playerColor;
    private int piecesRemaining;
    private int playerType = 1; // Dumb AI players are all of type 1
    private int playerScore;
    private int stage;
    private int playerID;
    private Piece currentPiece;
    private ArrayList<Piece> piecesInventory;
    public int INITIAL_PIECES_rEMAINING = 21;
    public int INITIAL_SCORE = 89;

    public BlokusDumbAI(String initName, int initColor,int initID){
        super(initName);
        playerColor = initColor;
        playerID = initID;
        piecesRemaining = INITIAL_PIECES_rEMAINING;
        playerScore = INITIAL_SCORE;
    }
    public void dumbMove(ArrayList<Piece> currentList){

    }
    @Override
    protected void receiveInfo(GameInfo info) {

    }
}

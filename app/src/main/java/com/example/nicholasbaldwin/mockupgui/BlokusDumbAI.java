package com.example.nicholasbaldwin.mockupgui;

import com.example.nicholasbaldwin.mockupgui.game.infoMsg.GameInfo;
import com.example.nicholasbaldwin.mockupgui.game.infoMsg.NotYourTurnInfo;
import com.example.nicholasbaldwin.mockupgui.game.util.GameComputerPlayer;

import java.util.ArrayList;

public class BlokusDumbAI extends GameComputerPlayer {
    //All instance variables
    public BlokusGameState localState;
    private int playerColor;
    private int piecesRemaining;
    private int playerScore;
    private int playerID;
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

        // if it's not a TTTState message, ignore it; otherwise
        // cast it
        if (!(info instanceof BlokusGameState)) return;
        localState = (BlokusGameState)info;

        // pick x and y positions at random (0-2)
        int xVal = (int)(21*Math.random());
        int yVal = (int)(21*Math.random());

        // if it was a "not your turn" message, just ignore it
        if (info instanceof NotYourTurnInfo) return;
        game.sendAction(new PlacePiece(this, xVal, yVal, selectRandomPiece()));
    }

    public Piece selectRandomPiece(){
        return localState.getAllPieceInventory().get(this.playerID).get((int)(21*Math.random()));
    }

    public int getPlayerColor() {
        return playerColor;
    }
}

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

        // if it was a "not your turn" message, just ignore it
        if (info instanceof NotYourTurnInfo) return;
        // if it's not a BlokusGameState message, ignore it; otherwise
        // cast it
        if (!(info instanceof BlokusGameState)) return;
        localState = (BlokusGameState)info;
        if(localState.getPlayerTurn() != playerID) return;

        // pick x and y positions at random (0-2)
        int xVal = (int)(21*Math.random());
        int yVal = (int)(21*Math.random());
        PlacePiece unusedPieceChecker = null;
//        int rotationCount = 3;
//        for(Piece unusedPiece : localState.getAllPieceInventory().get(playerID)) {
//            for (int j = 0; j < BlokusGameState.BOARD_LENGTH; j++) {
//                for (int k = 0; k < BlokusGameState.BOARD_LENGTH; k++) {
//                    if (localState.getBoard()[j][k] == Piece.EMPTY &&
//                            !unusedPiece.isOnBoard) {
//                        for (int l = 0; l < rotationCount; l++) {
//                            unusedPiece.setPieceLayout(unusedPiece.rotate90());
//                            unusedPiece.setxPosition(j);
//                            unusedPiece.setyPosition(k);
//                            unusedPieceChecker = new PlacePiece(this,j , k, unusedPiece);
//                            if(unusedPieceChecker.checkForValidMove(playerID)){
//                                game.sendAction(unusedPieceChecker);
//                            }
//                        }
//
//                    }
//                }
//            }
//        }
        //fot testing purposes
        switch (playerID) {
            case 1:
                unusedPieceChecker = new PlacePiece(this, 19, 0, localState.getAllPieceInventory().get(playerID).get(0));
                break;
            case 2:
                unusedPieceChecker = new PlacePiece(this, 0, 19, localState.getAllPieceInventory().get(playerID).get(0));
                break;
            case 3:
                unusedPieceChecker = new PlacePiece(this, 19, 19, localState.getAllPieceInventory().get(playerID).get(0));
                break;
        }
        game.sendAction(unusedPieceChecker);
    }

    public Piece selectRandomPiece(){
        return localState.getAllPieceInventory().get(localState.getPlayerTurn()).get(0);
    }

    public int getPlayerColor() {
        return playerColor;
    }
}

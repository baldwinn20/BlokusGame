package com.example.nicholasbaldwin.mockupgui;

import android.util.Log;

import com.example.nicholasbaldwin.mockupgui.game.infoMsg.GameInfo;
import com.example.nicholasbaldwin.mockupgui.game.infoMsg.NotYourTurnInfo;
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
        int rotationCount = 3;
        for(int i = localState.getAllPieceInventory().get(playerID).size()-1; i >= 0; i--) {
            Piece unusedPiece = localState.getAllPieceInventory().get(playerID).get(i);
            for (int j = 0; j < BlokusGameState.BOARD_LENGTH; j++) {
                for (int k = 0; k < BlokusGameState.BOARD_LENGTH; k++) {
                    Log.i("is on Board: ", unusedPiece.getIsOnBoard() + "");
                    if (localState.getBoard()[k][j] == Piece.EMPTY &&
                            !unusedPiece.isOnBoard) {
                        for (int l = 0; l < rotationCount; l++) {
                            unusedPiece.setPieceLayout(unusedPiece.rotate90());
                            unusedPiece.setxPosition(k);
                            unusedPiece.setyPosition(j);
                            unusedPieceChecker = new PlacePiece(this,k , j, unusedPiece);
                            unusedPieceChecker.setBoard(localState.getBoard());
                            if(unusedPieceChecker.checkForValidMove(playerID)){
                                game.sendAction(unusedPieceChecker);
                                localState.getAllPieceInventory().get(playerID).remove(unusedPiece);
//                                Log.i("is on Board: ", unusedPiece.getIsOnBoard() + "");
                                return;
                            }
                        }

                    }
                }
            }
        }
    }
}

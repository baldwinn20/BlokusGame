package com.example.nicholasbaldwin.mockupgui;

import android.util.Log;

import com.example.nicholasbaldwin.mockupgui.game.GameOverCheck;
import com.example.nicholasbaldwin.mockupgui.game.infoMsg.GameInfo;
import com.example.nicholasbaldwin.mockupgui.game.infoMsg.NotYourTurnInfo;
import com.example.nicholasbaldwin.mockupgui.game.util.GameComputerPlayer;

import java.util.ArrayList;
import java.util.Random;

public class BlokusDumbAI extends GameComputerPlayer {
    //All instance variables
    public BlokusGameState localState;
    private int playerColor;
    private int piecesRemaining;
    private int playerScore;
    private int playerID;
    public int INITIAL_PIECES_rEMAINING = 21;
    public int INITIAL_SCORE = 89;

    //private boolean[] onBoard

    public BlokusDumbAI(String initName, int initColor, int initID) {
        super(initName);
        playerColor = initColor;
        playerID = initID;
        piecesRemaining = INITIAL_PIECES_rEMAINING;
        playerScore = INITIAL_SCORE;
    }

    public void dumbMove(ArrayList<Piece> currentList) {

    }

    @Override
    protected void receiveInfo(GameInfo info) {

        // if it was a "not your turn" message, just ignore it
        if (info instanceof NotYourTurnInfo) return;
        // if it's not a BlokusGameState message, ignore it; otherwise
        // cast it
        if (!(info instanceof BlokusGameState)) return;
        localState = (BlokusGameState) info;
        if (localState.getPlayerTurn() != playerID) return;

//        sleep(1);

        PlacePiece unusedPieceChecker = null;
        int rotationCount = 3;
        //TODO there is a bug where if the AI cant move, the other players cannot make a move.
        for (Piece unusedPiece : localState.getAllPieceInventory().get(playerID)) {
            for (int j = 0; j < BlokusGameState.BOARD_LENGTH; j++) {
                for (int k = 0; k < BlokusGameState.BOARD_LENGTH; k++) {
                    if (localState.getBoard()[k][j] == Piece.EMPTY &&
                            !unusedPiece.isOnBoard) {
                        for (int l = 0; l < rotationCount; l++) {
                            unusedPiece.setPieceLayout(unusedPiece.rotate90());
                            unusedPiece.setxPosition(k);
                            unusedPiece.setyPosition(j);
                            unusedPieceChecker = new PlacePiece(this, k, j, unusedPiece);
                            unusedPieceChecker.setBoard(localState.getBoard());
                            if (unusedPieceChecker.checkForValidMove(playerID)) {
                                game.sendAction(unusedPieceChecker);
                                localState.getAllPieceInventory().get(playerID).remove(unusedPiece);
                                return;
                            }
                        }
                    }
                }
            }
        }
        //if the AI cant make a move
        unusedPieceChecker.setCantMove(true);
        game.sendAction(unusedPieceChecker);
        return;
    }

    public Piece selectRandomPiece() {
        Random r = new Random();
        int min = 0;
        int max = 20;
        int rand = r.nextInt((max - min) + 1) + min;
        return localState.getAllPieceInventory().get(localState.getPlayerTurn()).get(rand);
    }

    public int getPlayerColor() {
        return playerColor;
    }
}

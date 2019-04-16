package com.example.nicholasbaldwin.mockupgui;

import android.util.Log;

import com.example.nicholasbaldwin.mockupgui.game.infoMsg.GameInfo;
import com.example.nicholasbaldwin.mockupgui.game.infoMsg.NotYourTurnInfo;
import com.example.nicholasbaldwin.mockupgui.game.util.GameComputerPlayer;

import java.util.ArrayList;
import java.util.Collections;

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
        localState = (BlokusGameState) info;
        if (localState.getPlayerTurn() != playerID) return;

        // pick x and y positions at random (0-2)
        PlacePiece unusedPieceChecker = null;
        Piece pieceToRemove = null;
        int rotationCount = 3;
        boolean letMeOut = false;

        //the first move will always be the one piece
        if(findOnePiece(localState.getAllPieceInventory().get(playerID))){
            Piece p = localState.getAllPieceInventory().get(playerID).get(0);
            for (int j = 0; j < BlokusGameState.BOARD_LENGTH; j++) {
                for (int k = 0; k < BlokusGameState.BOARD_LENGTH; k++) {
                    if (localState.getBoard()[k][j] == Piece.EMPTY){
                        unusedPieceChecker = new PlacePiece(this,k,j, p);
                        unusedPieceChecker.setBoard(localState.getBoard());
                        if(unusedPieceChecker.checkForValidMove(playerID)){
                            game.sendAction(unusedPieceChecker);
                            pieceToRemove = p;
                            letMeOut = true;
                            break;
                        }
                    }
                    if (letMeOut) {
                        break;
                    }
                }
                if (letMeOut) {
                    break;
                }
            }
            if (pieceToRemove != null) {
                localState.getAllPieceInventory().get(playerID).remove(pieceToRemove);
                return;
            }
        }
        //this reverses the  order of the piece inventory so the smart AI places the biggest piece first
        Collections.reverse(localState.getAllPieceInventory().get(playerID));

        //TODO there is a bug where if the AI cant move, the other players cannot make a move.
        for (Piece unusedPiece : localState.getAllPieceInventory().get(playerID)) {
            for (int j = 1; j < BlokusGameState.BOARD_LENGTH; j++) {
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
                                pieceToRemove = unusedPiece;
                                letMeOut = true;
                                break;
                            }
                        }
                    }
                    if (letMeOut) {
                        break;
                    }
                }
                if (letMeOut) {
                    break;
                }
            }//for
            if (letMeOut) {
                break;
            }
        }//for
        if (pieceToRemove != null) {
            localState.getAllPieceInventory().get(playerID).remove(pieceToRemove);
            return;
        }
        //if the AI cant make a move
        unusedPieceChecker.setCantMove(true);
        game.sendAction(unusedPieceChecker);
        return;
    }

    private boolean findOnePiece( ArrayList<Piece> pieceList){
        for( Piece p : pieceList){
            if(p.getName().equals("one")){
                return true;
            }
        }
        return false;
    }

}
/**
 * External Citation:
 * Date: 15 April 2019
 * Problem: I wanted to know of a way to reverse the order of an array list
 * Source:https: https://stackoverflow.com/questions/580269/reverse-iteration-through-arraylist-gives-indexoutofboundsexception
 */

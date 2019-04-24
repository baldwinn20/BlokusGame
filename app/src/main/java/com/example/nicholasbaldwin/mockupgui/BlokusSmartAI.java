package com.example.nicholasbaldwin.mockupgui;

import com.example.nicholasbaldwin.mockupgui.game.infoMsg.GameInfo;
import com.example.nicholasbaldwin.mockupgui.game.infoMsg.NotYourTurnInfo;
import com.example.nicholasbaldwin.mockupgui.game.util.GameComputerPlayer;

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
    /**
     * constructor for the SmartAI
     * @param initName - sets the name for this player
     */
    public BlokusSmartAI(String initName) {
        super(initName);
    }

    /**
     * the main method of the Smart AI that decides how to move
     * based on the state of the game. Unlike the dumb Ai, this
     * AI rearranges its list to start at the bigger pieces first.
     * It can also flip a piece horizontally, another functionality
     * that was omitted for the Dumb AI
     *
     * @param info -the info of the game sent by the BlokusLocalGame
     *
     */
    @Override
    protected void receiveInfo(GameInfo info) {
// if it was a "not your turn" message, just ignore it
        if (info instanceof NotYourTurnInfo) return;
        // if it's not a BlokusGameState message, ignore it; otherwise
        // cast it
        if (!(info instanceof BlokusGameState)) return;
        localState = (BlokusGameState) info;
        if (localState.getPlayerTurn() != playerNum) return;

        // pick x and y positions at random (0-2)
        PlacePiece unusedPieceChecker = null;
        Piece pieceToRemove = null;
        int rotationCount = 4;
        int flipCount = 2;
        boolean letMeOut = false;

        //this reverses the  order of the piece inventory so the smart AI places the biggest piece first
        Collections.reverse(localState.getAllPieceInventory().get(playerNum));
        /**
         * External Citation:
         * Date: 15 April 2019
         * Problem: I wanted to know of a way to reverse the order of an array list
         * Source:https: https://stackoverflow.com/questions/580269/reverse-iteration-through-arraylist-gives-indexoutofboundsexception
         */

        //goes through the entire list of pieces and checks every single coordinate on the board
        //to see if it can place a piece
        for (Piece unusedPiece : localState.getAllPieceInventory().get(playerNum)) {
            for (int j = 0; j < BlokusGameState.BOARD_LENGTH; j++) {
                for (int k = 0; k < BlokusGameState.BOARD_LENGTH; k++) {
                    if (localState.getBoard()[k][j] == Piece.EMPTY &&
                            !unusedPiece.isOnBoard) {
                        //checks every orientation by rotation
                        for (int i = 0; i < flipCount; i++) {
                            for (int l = 0; l < rotationCount; l++) {
                                unusedPiece.setPieceLayout(unusedPiece.rotate90());
                                unusedPiece.setxPosition(k);
                                unusedPiece.setyPosition(j);
                                unusedPieceChecker = new PlacePiece(this, k, j, unusedPiece);
                                unusedPieceChecker.setBoard(localState.getBoard());
                                if (unusedPieceChecker.checkForValidMove(playerNum)) {
                                    game.sendAction(unusedPieceChecker);
                                    pieceToRemove = unusedPiece;
                                    letMeOut = true;
                                    break;
                                }
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
            localState.getAllPieceInventory().get(playerNum).remove(pieceToRemove);
            return;
        }
        //if the AI cant make a move
        GiveUp surrenderMessenger = new GiveUp(this);
        game.sendAction(surrenderMessenger);
        return;
    }


}

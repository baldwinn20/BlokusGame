package com.example.nicholasbaldwin.mockupgui;

import com.example.nicholasbaldwin.mockupgui.game.GiveUp;
import com.example.nicholasbaldwin.mockupgui.game.infoMsg.GameInfo;
import com.example.nicholasbaldwin.mockupgui.game.infoMsg.NotYourTurnInfo;
import com.example.nicholasbaldwin.mockupgui.game.util.GameComputerPlayer;


public class BlokusDumbAI extends GameComputerPlayer {
    //All instance variables
    public BlokusGameState localState;
    /**
     * constructor for the DumbAI
     * @param initName - sets the name for this player
     */
    public BlokusDumbAI(String initName) {
        super(initName);
    }

    /**
     * the main method of the Dumb AI that decides how to move
     * based on the state of the game. Will always start with
     * the first pieces in the list.
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



        //goes through the entire list of pieces and checks every single coordinate on the board
        //to see if it can place a piece
        PlacePiece unusedPieceChecker = null;
        Piece pieceToRemove = null;
        int rotationCount = 5;
        boolean letMeOut = false;
        for (Piece unusedPiece : localState.getAllPieceInventory().get(playerNum)) {
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
                            if (unusedPieceChecker.checkForValidMove(playerNum)) {
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
            localState.getAllPieceInventory().get(playerNum).remove(pieceToRemove);
            return;
        }
        //if the AI cant make a move
        GiveUp surrenderMessenger = new GiveUp(this);
        game.sendAction(surrenderMessenger);
        return;
    }


}

package com.example.nicholasbaldwin.mockupgui;

import com.example.nicholasbaldwin.mockupgui.game.actionMsg.GameAction;
import com.example.nicholasbaldwin.mockupgui.game.util.GamePlayer;
import com.example.nicholasbaldwin.mockupgui.game.util.LocalGame;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

/**
 * <!-- class BlokusLocalGame-->
 * <p>
 * This class defines and enforces
 * the game rules; handles interactions with players.
 *
 * @author <Justin Cao, Dylan Pascua, Nicholas Baldwin>
 * @version <Spring 2019>
 */

public class BlokusLocalGame extends LocalGame {

    // the game's state
    private BlokusGameState mainState;

    /**
     * Constructor for the BlokusLocalGame.
     */
    public BlokusLocalGame() {
        // perform superclass initialization
        super();

        // create a new, unfilled-in BlokusGameState object
        mainState = new BlokusGameState();
    }

    /**
     * Notify the given player that its state has changed. This should involve sending
     * a GameInfo object to the player. If the game is not a perfect-information game
     * this method should remove any information from the game that the player is not
     * allowed to know.
     *
     * @param p the player to notify
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        // make a copy of the state, and send it to the player
        p.sendInfo(new BlokusGameState(mainState, mainState.getPlayerTurn()));
    }

    /**
     * Tell whether the given player is allowed to make a move at the
     * present point in the game.
     *
     * @param playerIdx the player's player-number (ID)
     * @return true iff the player is allowed to move
     */
    @Override
    protected boolean canMove(int playerIdx) {
        return playerIdx == mainState.getPlayerTurn();
    }

    /**
     * Check if the game is over. It is over, return a string that tells
     * who the winner(s), if any, are. If the game is not over, return null;
     *
     * @return a message that tells who has won the game, or null if the
     * game is not over
     */
    @Override
    protected String checkIfGameOver() {
        //TODO check who has pieceRemaining = 0 and win the game

        int winner;
        for (int i = 0; i < players.length; i++) {
            if (mainState.getAllPiecesRemaining()[i] == 0) {
                winner = i;
                return playerNames[winner] + " is the winner.";
            }
        }

//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        //TODO check who has the highest score if no one can move
        //Look for all the empty tiles on the board
        PlacePiece unusedPieceChecker;
        int rotationCount = 3;
            for (int i = 0; i < players.length; i++) {
                for (Piece unusedPiece : mainState.getAllPieceInventory().get(i)) {
                    for (int j = 0; j < BlokusGameState.BOARD_LENGTH; j++) {
                        for (int k = 0; k < BlokusGameState.BOARD_LENGTH; k++) {
                            if (mainState.getBoard()[j][k] == Piece.EMPTY &&
                                    !unusedPiece.isOnBoard) {
                                for (int l = 0; l < rotationCount; l++) {
                                    unusedPiece.setPieceLayout(unusedPiece.rotate90());
                                    unusedPieceChecker = new PlacePiece(players[i], j, k, unusedPiece);

                                    if (unusedPieceChecker.checkForValidMove(mainState.getPlayerTurn())) {
                                        return null;
                                    }
                                }

                            }
                        }
                    }
                }
            }

        return null;
    }

    /**
     * Makes a move on behalf of a player.
     *
     * @param action The move that the player has sent to the game
     * @return Tells whether the move was a legal one.
     */
    @Override
    protected boolean makeMove(GameAction action) {
        PlacePiece pp = (PlacePiece) action;
        //the AI cant make a move
        if(pp.getCantMove()){
            mainState.setPlayerTurn(mainState.getPlayerTurn());
            return true;
        }
        int y = pp.getY();
        int x = pp.getX();
        pp.setBoard(mainState.getBoard());


        if (!pp.checkForValidMove(mainState.getPlayerTurn())) {
            return false;
        }



        mainState.placePiece(x, y, pp.getCurrentPiece());
        mainState.updatePiecesRemaining();
        mainState.updatePlayerScores(pp.getCurrentPiece());
        mainState.removePiece(pp.getCurrentPiece(),mainState.getPlayerTurn());

        mainState.setPlayerTurn(mainState.getPlayerTurn());

        return true;
    }
}

package com.example.nicholasbaldwin.mockupgui;

import com.example.nicholasbaldwin.mockupgui.game.actionMsg.GameAction;
import com.example.nicholasbaldwin.mockupgui.game.util.GamePlayer;
import com.example.nicholasbaldwin.mockupgui.game.util.LocalGame;

/**
 * <!-- class BlokusLocalGame-->
 *
 * This class defines and enforces
 * the game rules; handles interactions with players.
 *
 * @author <Justin Cao>
 * @author <Dylan Pascua>
 * @author <Nicholas Baldwin>
 */

public class BlokusLocalGame extends LocalGame {

    // the game's state
    public BlokusGameState mainState;

    /**
     * Constructor for the BlokusLocalGame.
     */
    public BlokusLocalGame(){
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
     * @param p
     * 			the player to notify
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        // make a copy of the state, and send it to the player
        p.sendInfo(new BlokusGameState(mainState));
        //TODO omit 3 pieces inventory
    }

    /**
     * Tell whether the given player is allowed to make a move at the
     * present point in the game.
     *
     * @param playerIdx
     * 		the player's player-number (ID)
     * @return
     * 		true iff the player is allowed to move
     */
    @Override
    protected boolean canMove(int playerIdx) {
        return playerIdx == mainState.getPlayerTurn();
    }

    /**
     * Check if the game is over. It is over, return a string that tells
     * who the winner(s), if any, are. If the game is not over, return null;
     *
     * @return
     * 		a message that tells who has won the game, or null if the
     * 		game is not over
     */
    @Override
    protected String checkIfGameOver() {
        //TODO check who has the highest score if no one can move
        //TODO check who has pieceRemaining = 0 and win the game

        int winner = -1;
        for (int i = 0; i < players.length; i++) {
            if(mainState.getAllPiecesRemaining()[i] == 0){
                winner = i;
                break;
            }
        }
        if(winner == -1){
            return null;
        }
        return playerNames[winner]+" is the winner.";
    }

    /**
     * Makes a move on behalf of a player.
     *
     * @param action
     * 			The move that the player has sent to the game
     * @return
     * 			Tells whether the move was a legal one.
     */
    @Override
    protected boolean makeMove(GameAction action) {
        PlacePiece pp = (PlacePiece) action;
        int row = pp.getRow();
        int col = pp.getCol();

        if(!pp.checkForValidMove()){
            return false;
        }

        mainState.placePiece(row, col, pp.getCurrentPiece());
        mainState.updatePiecesRemaining();
        mainState.updatePlayerScores(pp.getCurrentPiece());
        mainState.setPlayerTurn(mainState.getPlayerTurn());

        return true;
    }
}

package com.example.nicholasbaldwin.mockupgui;


import com.example.nicholasbaldwin.mockupgui.game.GiveUp;
import com.example.nicholasbaldwin.mockupgui.game.actionMsg.GameAction;
import com.example.nicholasbaldwin.mockupgui.game.util.GamePlayer;
import com.example.nicholasbaldwin.mockupgui.game.util.LocalGame;


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
    private int skipTurnCount = 0;
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

        int winner = 0;
        for (int i = 0; i < players.length; i++) {
            if (mainState.getAllPiecesRemaining()[i] == 0) {
                winner = i;
                return playerNames[winner] + " is the winner.";
            }
        }

        //TODO possibly add skips for human players as well?
        //if there has been more than 3 skips by the AIs, end the game
        for(boolean playerGivenUp : mainState.getAllPlayersGivenUp()) {
            if(!playerGivenUp){
                return null;
            }
        }

        if (skipTurnCount == 4) {
            for (int i = 0; i < players.length; i++) {
                if (mainState.getAllPlayerScores()[i] > mainState.getAllPlayerScores()[winner]) {
                    winner = i;
                }
            }
            return playerNames[winner] + " is the winner.";
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
        if(action instanceof PlacePiece) {
            PlacePiece pp = (PlacePiece) action;

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
        }
        else if(action instanceof GiveUp) {
            //the player cant make a move and hasn't skipped a turn yet
            if (!mainState.getAllPlayersGivenUp()[mainState.getPlayerTurn()]) {
                mainState.setAllPlayersGivenUp(true, mainState.getPlayerTurn());
                mainState.setPlayerTurn(mainState.getPlayerTurn());
                skipTurnCount++;
                return true;
            }
            //the current player has already given up once before, so skip to the next player's turn
            else if(mainState.getAllPlayersGivenUp()[mainState.getPlayerTurn()]){
                mainState.setPlayerTurn(mainState.getPlayerTurn());
                return true;
            }
        }

        return true;
    }
}

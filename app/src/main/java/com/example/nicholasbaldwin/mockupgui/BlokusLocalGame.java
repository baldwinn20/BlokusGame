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
    protected BlokusGameState mainState;

    /**
     * Constructor for the BlokusLocalGame.
     */
    public BlokusLocalGame(){
        // perform superclass initialization
        super();

        // create a new, unfilled-in BlokusGameState object
        mainState = new BlokusGameState();
    }

    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {

    }

    @Override
    protected boolean canMove(int playerIdx) {
        return false;
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
        return null;
    }

    @Override
    protected boolean makeMove(GameAction action) {
        return false;
    }
}

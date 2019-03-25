package com.example.nicholasbaldwin.mockupgui;

import com.example.nicholasbaldwin.mockupgui.game.actionMsg.GameAction;
import com.example.nicholasbaldwin.mockupgui.game.util.GamePlayer;

public class PlacePiece extends GameAction {
    //instance variables
    private int[][] pieceLayout;
    private int[][] boardSection;

    public void checkForValidMove(){

    }

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public PlacePiece(GamePlayer player) {
        super(player);
    }

}

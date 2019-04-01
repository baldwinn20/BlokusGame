package com.example.nicholasbaldwin.mockupgui;

import com.example.nicholasbaldwin.mockupgui.game.actionMsg.GameAction;
import com.example.nicholasbaldwin.mockupgui.game.util.GamePlayer;

public class Rotate90 extends GameAction {
    //instance variables
    private int[][] pieceLayout;
    private int row;
    private int col;
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public Rotate90(GamePlayer player, Piece p) {
        super(player);

        pieceLayout = p.getPieceLayout();

        //I DONT KNOW IF THIS ACTUALLY WORKS
        for(int i= 0; i < pieceLayout.length; i++){
            for(int j = pieceLayout.length -1; j > 0; j--){
                pieceLayout[i][j] = pieceLayout[j][i];
            }
        }

        p.setPieceLayout(pieceLayout);
    }

    /**
     External Citation:
     Date: 30 March 2019
     Problem: I didn't know the algorythm for rotating things in
     a 2D array
     Source:https://stackoverflow.com/questions/2799755/rotate-array-clockwise
     */
}

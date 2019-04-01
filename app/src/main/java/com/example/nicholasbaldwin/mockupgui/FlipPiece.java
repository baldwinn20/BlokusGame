package com.example.nicholasbaldwin.mockupgui;

import com.example.nicholasbaldwin.mockupgui.game.actionMsg.GameAction;
import com.example.nicholasbaldwin.mockupgui.game.util.GamePlayer;

public class FlipPiece extends GameAction {
    //instance variables
    private int[][] pieceLayout;
    private int row;
    private int col;
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public FlipPiece(GamePlayer player, Piece p) {
        super(player);

        pieceLayout = p.getPieceLayout();

        for(int i= 0; i < pieceLayout.length; i++){
            for(int j = 0; j < pieceLayout.length / 2; j++){
                //swaps values across the center line
                int temp = pieceLayout[i][j];
                pieceLayout[i][j] = pieceLayout[i][pieceLayout.length - j - 1];
                pieceLayout[i][pieceLayout.length - j - 1] = temp;
            }
        }

        p.setPieceLayout(pieceLayout);
    }
}

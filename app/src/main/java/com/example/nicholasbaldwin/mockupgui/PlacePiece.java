package com.example.nicholasbaldwin.mockupgui;

import com.example.nicholasbaldwin.mockupgui.game.actionMsg.GameAction;
import com.example.nicholasbaldwin.mockupgui.game.util.GamePlayer;

public class PlacePiece extends GameAction {
    //instance variables
    private int[][] pieceLayout;
    private int[][] boardSection;
    private int row;
    private int col;
    private Piece currentPiece;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public PlacePiece(GamePlayer player, int row, int col, Piece curPiece) {
        super(player);
        this.row = row;
        this.col = col;
        this.currentPiece = curPiece;
    }


    public boolean checkForValidMove(){
        return true;
    }

    /**
     * get the object's row
     *
     * @return the row selected
     */
    public int getRow() { return row; }

    /**
     * get the object's column
     *
     * @return the column selected
     */
    public int getCol() { return col; }

    public Piece getCurrentPiece() {
        return currentPiece;
    }
}

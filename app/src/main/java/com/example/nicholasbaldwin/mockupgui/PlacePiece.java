package com.example.nicholasbaldwin.mockupgui;

import android.util.Log;

import com.example.nicholasbaldwin.mockupgui.game.actionMsg.GameAction;
import com.example.nicholasbaldwin.mockupgui.game.util.GamePlayer;

public class PlacePiece extends GameAction {
    //instance variables
    private int[][] boardCopy = new int[20][20];
    private int[][] pieceLayout = new int [5][5];
    private final int boardLength = 20;
    private int y;
    private int x;
    private Piece currentPiece;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public PlacePiece(GamePlayer player, int x, int y, Piece curPiece) {
        super(player);
        this.y = y;
        this.x = x;
        this.currentPiece = curPiece;
        pieceLayout = curPiece.getPieceLayout();
    }

    //TODO finish this algorythm and pass in the boardcopy
    public boolean checkForValidMove(int pID){

        boolean isCorner = false;
        boolean isAdjacent = false;
        isCorner |= isStart(x,y, pID);
        if(isCorner){
            return true;
        }
        for(int i = x; i <= x + currentPiece.getPieceWidth(); i++){
            for(int j = y; j <= y + currentPiece.getPieceLength(); j++){
                int xOffset = i - x;
                int yOffset = j - y;
                if(pieceLayout[xOffset][yOffset] != -1){
                    //Check for out of bound piece tiles
                    Log.i("x+1", boardCopy[x+1] + "");

                    isAdjacent = boardCopy[x + xOffset - 1][y + yOffset] == pID || boardCopy[x + xOffset+ 1][y + yOffset] == pID
                            || boardCopy[x+xOffset][y+yOffset - 1] == pID || boardCopy[x+xOffset][y+yOffset + 1] == pID;
                    if(isAdjacent){
                        return false;
                    }
                    isCorner |= boardCopy[x+xOffset+1][y+yOffset+1] == pID || boardCopy[x+xOffset-1][y+yOffset-1] == pID;
                    isCorner |= boardCopy[x+xOffset-1][y+yOffset+1] == pID || boardCopy[x+xOffset+1][y+yOffset-1] == pID;

                }
            }
        }

        return isCorner;
    }

    //TODO placing pieces from the starting corners
    private boolean isStart(int x, int y, int pID){
        if(x == y && x == 0){

            return true;
        }
        return false;
    }


    /**
     * get the object's y
     *
     * @return the y selected
     */
    public int getY() { return y; }

    /**
     * get the object's column
     *
     * @return the column selected
     */
    public int getX() { return x; }
    public void setX(int x){this.x = x;}
    public void setY(int y){this.y= y;}

    public Piece getCurrentPiece() {
        return currentPiece;
    }
    public void setBoard(int[][] orig){
        this.boardCopy = orig;
    }
}

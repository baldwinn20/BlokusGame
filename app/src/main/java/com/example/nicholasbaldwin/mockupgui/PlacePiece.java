package com.example.nicholasbaldwin.mockupgui;

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
        for(int i = x; i < x + currentPiece.getPieceWidth(); i++){
            for(int j = y; j < y + currentPiece.getPieceLength(); j++){
                int xOffset = i - x;
                int yOffset = j - y;
                if(pieceLayout[xOffset][yOffset] != -1){
                    boolean isAdjacent = boardCopy[x-1][y] != pID || boardCopy[x+1][y] != pID
                            || boardCopy[x][y-1] != pID || boardCopy[x][y+1] != pID;
                    if(isAdjacent){
                        return false;
                    }
                    isCorner |= boardCopy[x+1][y+1] == pID || boardCopy[x-1][y-1] == pID;
                    isCorner |= boardCopy[x-1][y+1] == pID || boardCopy[x+1][y-1] == pID;
                    isCorner |= isStart(x,y, pID);
                }
            }
        }

        return true;
    }

    //TODO placing pieces from the starting corners
    private boolean isStart(int x, int y, int pID){
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

    public Piece getCurrentPiece() {
        return currentPiece;
    }
}

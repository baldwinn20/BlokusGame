package com.example.nicholasbaldwin.mockupgui;

import android.util.Log;
import android.widget.TextView;

import com.example.nicholasbaldwin.mockupgui.game.actionMsg.GameAction;
import com.example.nicholasbaldwin.mockupgui.game.util.GamePlayer;


/**
 * <!-- class PlacePiece -->
 * <p>
 * This class implements functionality that only allows a
 * player to make 'legal' moves.
 *
 * @author <Nicholas Baldwin, Justin Cao, Dylan Pascua>
 * @version <Spring 2019>
 */
public class PlacePiece extends GameAction {
    //instance variables
    private int[][] boardCopy = new int[20][20];
    private int[][] pieceLayout;
    private int x, y;
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


    //TODO if a player tries to tap on a opponent's empty starting corner
    //TODO if player flips/rotate, needs to check if starting corner is valid
    public boolean checkForValidMove(int pID) {

        if (currentPiece.isOnBoard) {
            return false;
        }

        boolean isCorner = false;
        boolean isAdjacent = false;

        //Continues check only if there is no piece in the starting corner and
        //its not the first turn of the game
        if ((boardCopy[x][y] == -1)) {
            isCorner |= checkStartingCorner(pID);
            if (isCorner) {
                return true;
            }
        }

        for (int i = x; i < x + currentPiece.getPieceWidth(); i++) {
            for (int j = y; j < y + currentPiece.getPieceLength(); j++) {
                int xOffset = i - x;
                int yOffset = j - y;

                //Check inside a piece's array to see if its individual tiles can be placed on the board
                //Make sure there is no other tile already placed at this board position
                if (pieceLayout[xOffset][yOffset] != -1 && boardCopy[x][y] == -1) {

                    //if a tile in a pieces layout goes past the board
                    if(x + xOffset >= 20 || y + yOffset >= 20){
                        return false;
                    }
                    // special checks for the 4 corners of the board depending on the player
                    //top left corner
                    if(x + xOffset == 0 && y + yOffset == 0 && pID != 0){
                        return false;
                    }
                    //top right corner
                    else if( x + xOffset >= 19 && y + yOffset == 0 && pID != 1){
                        return false;
                    }
                    //bottom left
                    else if(x + xOffset == 0 && y + yOffset == 19  && pID != 2){
                        return false;
                    }
                    //bottom right corner
                    else if(x + xOffset == 19 && y + yOffset == 19 && pID !=3 ){
                       return false;
                    }

                    //Special check for top row of board:
                    if (y == 0 && x != 0 && x != 19 && y + yOffset == 0){

                        //checks adjacent tiles to the left, right, and bottom of a selected tile, respectively
                        isAdjacent = boardCopy[x + xOffset - 1][y + yOffset] == pID || boardCopy[x + xOffset + 1][y + yOffset] == pID
                                || boardCopy[x + xOffset][y + yOffset + 1] == pID;

                        //checks for the bottom right and bottom left corners of a tile, respectively
                        isCorner |= boardCopy[x + xOffset + 1][y + yOffset + 1] == pID || boardCopy[x + xOffset - 1][y + yOffset + 1] == pID;
                    }

                    //Special check for bottom row of board:
                    else if (y == 19 || y + yOffset == 19) {

                        //checks adjacent tiles to the left, right, and top of a selected tile, respectively
                        isAdjacent = boardCopy[x + xOffset - 1][y + yOffset] == pID || boardCopy[x + xOffset + 1][y + yOffset] == pID
                                || boardCopy[x + xOffset][y + yOffset - 1] == pID;

                        //checks for the top left and top right corners of a tile, respectively
                        isCorner |= boardCopy[x + xOffset - 1][y + yOffset - 1] == pID || boardCopy[x + xOffset + 1][y + yOffset - 1] == pID;
                    }

                    //Special check for first column of board:
                    else if (x == 0 && y != 0) {

                        //checks adjacent tiles to the right, top, and bottom of a selected tile, respectively
                        isAdjacent = boardCopy[x + xOffset + 1][y + yOffset] == pID
                                || boardCopy[x + xOffset][y + yOffset - 1] == pID || boardCopy[x + xOffset][y + yOffset + 1] == pID;

                        //checks for the bottom right and top right corners of a tile, respectively
                        isCorner |= boardCopy[x + xOffset + 1][y + yOffset + 1] == pID || boardCopy[x + xOffset + 1][y + yOffset - 1] == pID;

                    }

                    //Special check for last column of board:
                    else if (x == 19 || x + xOffset == 19) {

                        //checks adjacent tiles to the left, top, and bottom of a selected tile, respectively
                        isAdjacent = boardCopy[x + xOffset - 1][y + yOffset] == pID
                                || boardCopy[x + xOffset][y + yOffset - 1] == pID || boardCopy[x + xOffset][y + yOffset + 1] == pID;

                        //checks for the top left and bottom left corners of a tile, respectively
                        isCorner |= boardCopy[x + xOffset - 1][y + yOffset - 1] == pID || boardCopy[x + xOffset - 1][y + yOffset + 1] == pID;

                    }

                    //Check middle positions of the board
                    else if (x + xOffset > 0 && y + yOffset > 0 && x + xOffset < 19 && y + yOffset < 19) {
                        isAdjacent = boardCopy[x + xOffset - 1][y + yOffset] == pID || boardCopy[x + xOffset + 1][y + yOffset] == pID
                                || boardCopy[x + xOffset][y + yOffset - 1] == pID || boardCopy[x + xOffset][y + yOffset + 1] == pID;
                        isCorner |= boardCopy[x + xOffset + 1][y + yOffset + 1] == pID || boardCopy[x + xOffset - 1][y + yOffset - 1] == pID;
                        isCorner |= boardCopy[x + xOffset - 1][y + yOffset + 1] == pID || boardCopy[x + xOffset + 1][y + yOffset - 1] == pID;
                    }

                    if (isAdjacent) {
                        return false;
                    }


                }
            }
        }

        return isCorner;
    }

    //TODO placing pieces from the starting corners
    private boolean checkStartingCorner(int pID) {

        if (boardCopy[x][y] != -1) {
            return false;
        }

        boolean isStartCorner = false;
        switch (pID) {

            //Checks the top left board corner
            case 0:
                if (x == y && x == 0 && pieceLayout[x][y] == pID) {
                        isStartCorner = true;
                }
                break;
            //Checks the top right board corner
            case 1:
                if (x == 19 && y == 0 && pieceLayout[x][y] == pID) {
                    isStartCorner = true;
                }
                break;

            //Checks the bottom left board corner
            case 2:
                if (x == 0 && y == 19 && pieceLayout[x][y] == pID) {
                    isStartCorner = true;
                }
                break;

            //Checks the bottom right board corner
            case 3:
                if (x == 19 && x == 19 && pieceLayout[x][y] == pID) {
                    isStartCorner = true;
                }
                break;
            default:
                break;
        }

        return isStartCorner;
    }


    /**
     * get the object's y
     *
     * @return the y selected
     */
    public int getY() {
        return y;
    }

    /**
     * get the object's column
     *
     * @return the column selected
     */
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Piece getCurrentPiece() {
        return currentPiece;
    }

    public void setBoard(int[][] orig) {
        this.boardCopy = orig;
    }
    public void setPieceLayout(int[][] orig) {
        this.pieceLayout = orig;
    }
}

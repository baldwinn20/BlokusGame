package com.example.nicholasbaldwin.mockupgui;

import com.example.nicholasbaldwin.mockupgui.game.actionMsg.GameAction;
import com.example.nicholasbaldwin.mockupgui.game.util.GamePlayer;

import java.io.Serializable;


/**
 * <!-- class PlacePiece -->
 * <p>
 * This class implements functionality that only allows a
 * player to make 'legal' moves.
 *
 * @author <Nicholas Baldwin, Justin Cao, Dylan Pascua>
 * @version <Spring 2019>
 */

public class PlacePiece extends GameAction implements Serializable {
    //instance variables
    private int[][] boardCopy = new int[BlokusGameState.BOARD_LENGTH][BlokusGameState.BOARD_LENGTH];
    private int[][] pieceLayout;
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

    /**
     * The key method that makes sure a piece that is
     * being played does not touch any adjacent sides
     * of the same color piece. Only the corners should
     * be touching.
     *
     * @param pID - used to determine what color piece
     *            is being played or who is playing
     *
     * @return  false if the piece is no a valid move
     *          true otherwise.
     */
    public boolean checkForValidMove(int pID) {

        if (currentPiece.isOnBoard) {
            return false;
        }

        //if a tile in a pieces layout goes past the board
        if (x + currentPiece.getPieceWidth() - 1 >= BlokusGameState.BOARD_LENGTH
                || y + currentPiece.getPieceLength() -1 >= BlokusGameState.BOARD_LENGTH) {
            return false;
        }

        pieceLayout = currentPiece.getPieceLayout();
        boolean isCorner = false;
        boolean isAdjacent = false;

        //Continues check only if there is no piece in the starting corner and
        //its not the first turn of the game
        if ((boardCopy[x][y] == Piece.EMPTY)) {
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
                if (pieceLayout[xOffset][yOffset] != Piece.EMPTY ) {
                    // special checks for the 4 corners of the board depending on the player
                    //top left corner
                    if (x + xOffset == 0 && y + yOffset == 0 && pID != Piece.RED) {
                        return false;
                    }
                    //there is overlap with other already placed pieces
                    if (boardCopy[x + xOffset][y + yOffset] != Piece.EMPTY && pieceLayout[xOffset][yOffset] == pID) {
                        return false;
                    }
                    //top right corner
                    else if (x + xOffset >= 19 && y + yOffset == 0 && pID != Piece.BLUE) {
                        return false;
                    }
                    //bottom left
                    else if (x + xOffset == 0 && y + yOffset == 19 && pID != Piece.GREEN) {
                        return false;
                    }
                    //bottom right corner
                    else if (x + xOffset == 19 && y + yOffset == 19 && pID != Piece.YELLOW) {
                        return false;
                    }


                    //Special check for top row of board:
                    if (y == 0 && x != 0 && x != 19 && y + yOffset == 0 && x + xOffset != 19) {

                        //checks adjacent tiles to the left, right, and bottom of a selected tile, respectively
                        isAdjacent = boardCopy[x + xOffset - 1][y + yOffset] == pID || boardCopy[x + xOffset + 1][y + yOffset] == pID
                                || boardCopy[x + xOffset][y + yOffset + 1] == pID;

                        //checks for the bottom right and bottom left corners of a tile, respectively
                        isCorner |= boardCopy[x + xOffset + 1][y + yOffset + 1] == pID || boardCopy[x + xOffset - 1][y + yOffset + 1] == pID;
                    }

                    //Special check for bottom row of board:
                    else if ((y == 19 && x != 0) || y + yOffset == 19) {

                        //checks adjacent tiles to the left, right, and top of a selected tile, respectively
                        isAdjacent = boardCopy[x + xOffset - 1][y + yOffset] == pID || boardCopy[x + xOffset + 1][y + yOffset] == pID
                                || boardCopy[x + xOffset][y + yOffset - 1] == pID;

                        //checks for the top left and top right corners of a tile, respectively
                        isCorner |= boardCopy[x + xOffset - 1][y + yOffset - 1] == pID || boardCopy[x + xOffset + 1][y + yOffset - 1] == pID;
                    }

                    //Special check for first column of board:
                    else if (x == 0 && y != 0 && x + xOffset == 0) {

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

                    //there is an already placed piece that is on-top of the anchor but there is space to place a piece
                    if (pieceLayout[xOffset][yOffset] != Piece.EMPTY && boardCopy[x][y] != pID) {
                        if (x + xOffset > 0 && y + yOffset > 0 && x + xOffset < 19 && y + yOffset < 19) {
                            //the top and right are touching
                            isCorner |= boardCopy[x + xOffset][y + yOffset - 1] == pID && boardCopy[x + xOffset + 1][y + yOffset] == pID;
                            //the top and left are touching
                            isCorner |= boardCopy[x + xOffset][y + yOffset - 1] == pID && boardCopy[x + xOffset - 1][y + yOffset] == pID;
                            //the left and bottom are touching
                            isCorner |= boardCopy[x + xOffset][y + yOffset + 1] == pID && boardCopy[x + xOffset - 1][y + yOffset] == pID;
                            //the bottom and right are touching
                            isCorner |= boardCopy[x + xOffset][y + yOffset + 1] == pID && boardCopy[x + xOffset + 1][y + yOffset] == pID;
                        }
                    }
                    if (isAdjacent) {
                        return false;
                    }

                }
            }
        }

        return isCorner;
    }
    /**
     * A Special method that helps players place their
     * first piece on their first turn. Each player has
     * been already assigned one of the 4 corners of the
     * board to place their first piece.
     *
     * @param pID - used to determine what color piece
     *            is being played or who is playing
     *
     * @return  false if the piece is not on a valid
     *          corner, true otherwise.
     */
    private boolean checkStartingCorner(int pID) {
        boolean isStartCorner = false;
        switch (pID) {
            //checks to see if your anchor is on a corner or one of the other tiles is
            //Checks the top left board corner
            case Piece.RED:
                if (((x+currentPiece.getPieceWidth() - 1) == 0
                        && (y+currentPiece.getPieceLength() - 1) == 0
                        && boardCopy[0][0] == Piece.EMPTY)
                        || (x == y && x == 0 && pieceLayout[0][0] == pID )) {
                    isStartCorner = true;
                }
                break;
            //Checks the top right board corner
            case Piece.BLUE:
                if (((x+currentPiece.getPieceWidth() - 1) == 19
                        && pieceLayout[currentPiece.getPieceWidth()-1][0] == pID && y == 0
                        && boardCopy[19][0] == Piece.EMPTY)
                        || (x == 19 && y == 0 && pieceLayout[0][0] == pID)) {
                    isStartCorner = true;
                }
                break;

            //Checks the bottom left board corner
            case Piece.GREEN:
                if (((y+currentPiece.getPieceLength() - 1) == 19
                        && pieceLayout[0][currentPiece.getPieceLength()-1] == pID && x == 0
                        && boardCopy[0][19] == Piece.EMPTY)
                        || (x == 0 && y == 19 && pieceLayout[0][0] == pID )) {
                    isStartCorner = true;
                }
                break;

            //Checks the bottom right board corner
            case Piece.YELLOW:
                if ((x+currentPiece.getPieceWidth() - 1) == 19
                        && (y+currentPiece.getPieceLength() - 1) == 19
                        && pieceLayout[currentPiece.getPieceWidth()-1][currentPiece.getPieceLength()-1] == pID
                        && boardCopy[19][19] == Piece.EMPTY) {
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
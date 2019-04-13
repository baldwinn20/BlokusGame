package com.example.nicholasbaldwin.mockupgui;

import android.graphics.Color;
import android.util.Log;

/**
 * <!-- class Piece -->
 * <p>
 * This class controls the attributes of the pieces used in Blokus.
 * Each player is to have 21 pieces in their inventories each with
 * different shapes and point values.
 *
 * @author <Justin Cao, Dylan Pascua, Nicholas Baldwin>
 * @version <Spring 2019>
 */
public class Piece {
    public static final int PIECE_LAYOUT_SIZE = 5;
    public static final int EMPTY = -1;

    //This will be used to identify the type of piece created
    //for this instance
    private String pieceName;

    //How much the piece is worth in terms of points
    private int pieceValue, pieceColor, colorNum;
    protected int xPosition, yPosition = 9;

    //0,1,2,3 are different orientation displays
    protected int orientationVal = 0;

    //how the pieces are arranged in terms of ints
    protected int[][] pieceLayout = new int[PIECE_LAYOUT_SIZE][PIECE_LAYOUT_SIZE];

    //if true, remove from a player's inventory user interface
    protected boolean isOnBoard = false;
    //An array of this width will be used to contain each piece
    protected final int pieceWidth = 5;

    /**
     * Constructor for the Piece class
     * <p>
     * Initializes a piece with its type, score value, and color.
     * This instance will be stored in each player's inventory.
     *
     * @param initName  Name of the player
     * @param initScore Type of player (Human, AI, or Network)
     * @param initColor Chosen color of a player
     */
    public Piece(String initName, int initScore, int initColor) {
        pieceName = initName;
        pieceValue = initScore;
        pieceColor = initColor;
        orientationVal = 0;

        //setting up a blank piece layout
        for (int i = 0; i < pieceLayout.length; i++) {
            for (int j = 0; j < pieceLayout.length; j++) {
                pieceLayout[i][j] = -1;
            }
        }

        //todo make a switch for color to replace the zeros
        if (pieceColor == Color.RED) {
            colorNum = 0;
        } else if (pieceColor == Color.BLUE) {
            colorNum = 1;
        } else if (pieceColor == Color.GREEN) {
            colorNum = 2;
        } else if (pieceColor == Color.YELLOW) {
            colorNum = 3;
        }


        //the setups for each piece
        if (pieceName.equals("one")) {
            pieceLayout[0][0] = colorNum;
        } else if (pieceName.equals("two")) {
            pieceLayout[0][0] = colorNum;
            pieceLayout[1][0] = colorNum;
        } else if (pieceName.equals("S")) {
            pieceLayout[1][0] = colorNum;
            pieceLayout[2][0] = colorNum;
            pieceLayout[1][1] = colorNum;
            pieceLayout[0][1] = colorNum;
        } else if (pieceName.equals("three")) {
            pieceLayout[0][0] = colorNum;
            pieceLayout[1][0] = colorNum;
            pieceLayout[2][0] = colorNum;
        } else if (pieceName.equals("smallT")) {
            pieceLayout[1][0] = colorNum;
            pieceLayout[1][1] = colorNum;
            pieceLayout[0][1] = colorNum;
            pieceLayout[2][1] = colorNum;
        } else if (pieceName.equals("four")) {
            pieceLayout[0][0] = colorNum;
            pieceLayout[1][0] = colorNum;
            pieceLayout[2][0] = colorNum;
            pieceLayout[3][0] = colorNum;
        } else if (pieceName.equals("fourL")) {
            pieceLayout[2][0] = colorNum;
            pieceLayout[0][1] = colorNum;
            pieceLayout[1][1] = colorNum;
            pieceLayout[2][1] = colorNum;
        } else if (pieceName.equals("five")) {
            pieceLayout[0][0] = colorNum;
            pieceLayout[1][0] = colorNum;
            pieceLayout[2][0] = colorNum;
            pieceLayout[3][0] = colorNum;
            pieceLayout[4][0] = colorNum;
        } else if (pieceName.equals("fiveL")) {
            pieceLayout[0][0] = colorNum;
            pieceLayout[0][1] = colorNum;
            pieceLayout[1][1] = colorNum;
            pieceLayout[2][1] = colorNum;
            pieceLayout[3][1] = colorNum;
        } else if (pieceName.equals("N")) {
            pieceLayout[0][1] = colorNum;
            pieceLayout[1][0] = colorNum;
            pieceLayout[1][1] = colorNum;
            pieceLayout[2][0] = colorNum;
            pieceLayout[3][0] = colorNum;
        } else if (pieceName.equals("Y")) {
            pieceLayout[0][1] = colorNum;
            pieceLayout[1][0] = colorNum;
            pieceLayout[1][1] = colorNum;
            pieceLayout[2][1] = colorNum;
            pieceLayout[3][1] = colorNum;
        } else if (pieceName.equals("v3")) {
            pieceLayout[0][0] = colorNum;
            pieceLayout[1][0] = colorNum;
            pieceLayout[1][1] = colorNum;
        } else if (pieceName.equals("cube")) {
            pieceLayout[0][0] = colorNum;
            pieceLayout[1][0] = colorNum;
            pieceLayout[0][1] = colorNum;
            pieceLayout[1][1] = colorNum;
        } else if (pieceName.equals("C")) {
            pieceLayout[0][0] = colorNum;
            pieceLayout[0][1] = colorNum;
            pieceLayout[0][2] = colorNum;
            pieceLayout[1][0] = colorNum;
            pieceLayout[1][2] = colorNum;
        } else if (pieceName.equals("B")) {
            pieceLayout[0][0] = colorNum;
            pieceLayout[0][1] = colorNum;
            pieceLayout[0][2] = colorNum;
            pieceLayout[1][1] = colorNum;
            pieceLayout[1][2] = colorNum;
        } else if (pieceName.equals("Z")) {
            pieceLayout[2][0] = colorNum;
            pieceLayout[0][1] = colorNum;
            pieceLayout[1][1] = colorNum;
            pieceLayout[2][1] = colorNum;
            pieceLayout[0][2] = colorNum;
        } else if (pieceName.equals("M")) {
            pieceLayout[1][0] = colorNum;
            pieceLayout[2][0] = colorNum;
            pieceLayout[1][1] = colorNum;
            pieceLayout[0][1] = colorNum;
            pieceLayout[0][2] = colorNum;
        } else if (pieceName.equals("X")) {
            pieceLayout[1][0] = colorNum;
            pieceLayout[1][1] = colorNum;
            pieceLayout[0][1] = colorNum;
            pieceLayout[2][1] = colorNum;
            pieceLayout[1][2] = colorNum;
        } else if (pieceName.equals("F")) {
            pieceLayout[1][0] = colorNum;
            pieceLayout[1][1] = colorNum;
            pieceLayout[0][1] = colorNum;
            pieceLayout[1][2] = colorNum;
            pieceLayout[2][0] = colorNum;
        } else if (pieceName.equals("bigT")) {
            pieceLayout[1][0] = colorNum;
            pieceLayout[1][1] = colorNum;
            pieceLayout[1][2] = colorNum;
            pieceLayout[0][2] = colorNum;
            pieceLayout[2][2] = colorNum;
        } else if (pieceName.equals("corner")) {
            pieceLayout[0][0] = colorNum;
            pieceLayout[0][1] = colorNum;
            pieceLayout[0][2] = colorNum;
            pieceLayout[1][2] = colorNum;
            pieceLayout[2][2] = colorNum;
        }

    }

    //method that flips the piece's layout horizontally
    public int[][] flip() {
        //checks to see if this can be flipped. if not return original
        if (!this.canBeFlipped()) {
            return this.getPieceLayout();
        }
        int[][] currentLayout = this.getPieceLayout();
        int yOffset = 5;
        //flips the piece horizontally at the middle
        for (int i = 0; i < PIECE_LAYOUT_SIZE; i++) {
            for (int j = 0; j < PIECE_LAYOUT_SIZE / 2; j++) {
                //swaps values across the center line
                int temp = currentLayout[i][j];
                currentLayout[i][j] = currentLayout[i][PIECE_LAYOUT_SIZE - j - 1];
                currentLayout[i][PIECE_LAYOUT_SIZE - j - 1] = temp;
            }
        }

        //counts the y offset when rotating
        for (int i = 0; i < PIECE_LAYOUT_SIZE; i++) {
            for (int j = 0; j < PIECE_LAYOUT_SIZE; j++) {
                if (currentLayout[i][j] != Piece.EMPTY && j < yOffset) {
                    yOffset = j;
                    break;
                }
            }
        }
        // if there is no change in y
        if (yOffset == 5) {
            yOffset = 0;
        }
        //this moves the piece back up to the top
        for (int i = 0; i < PIECE_LAYOUT_SIZE; i++) {
            for (int j = 0; j < PIECE_LAYOUT_SIZE; j++) {
                if (currentLayout[i][j] != Piece.EMPTY) {
                    int temp = currentLayout[i][j - yOffset];
                    currentLayout[i][j - yOffset] = currentLayout[i][j];
                    currentLayout[i][j] = temp;
                }
            }
        }
        return currentLayout;
    }

    public int[][] rotate90() {
        int[][] currentLayout = this.getPieceLayout();
        int[][] newLayout = new int[PIECE_LAYOUT_SIZE][PIECE_LAYOUT_SIZE];
        int xOffset = 5;
        int yOffset = 5;
        //this rotates the entire layout clockwise 90
        for (int i = 0; i < PIECE_LAYOUT_SIZE; i++) {
            for (int j = 0; j < PIECE_LAYOUT_SIZE; j++) {
                newLayout[i][j] = currentLayout[j][PIECE_LAYOUT_SIZE - i - 1];
            }
        }
        //counts the y offset when rotating
        for (int i = 0; i < PIECE_LAYOUT_SIZE; i++) {
            for (int j = 0; j < PIECE_LAYOUT_SIZE; j++) {
                if (newLayout[i][j] != Piece.EMPTY && j < yOffset) {
                    yOffset = j;
                    break;
                }
            }
        }
        //counts the x offset when rotating
        for (int i = 0; i < PIECE_LAYOUT_SIZE; i++) {
            for (int j = 0; j < PIECE_LAYOUT_SIZE; j++) {
                if (newLayout[i][j] != Piece.EMPTY && i < xOffset) {
                    xOffset = i;
                    break;
                }
            }
        }
        //if there is no offset, which means the original values are the same
        if (xOffset == 5) {
            xOffset = 0;
        }
        if (yOffset == 5) {
            yOffset = 0;
        }
        //puts the value back into the left corner
        //counts the y offset when rotating
        for (int i = 0; i < PIECE_LAYOUT_SIZE; i++) {
            for (int j = 0; j < PIECE_LAYOUT_SIZE; j++) {
                if (newLayout[i][j] != Piece.EMPTY) {
                    int temp = newLayout[i - xOffset][j - yOffset];
                    newLayout[i - xOffset][j - yOffset] = newLayout[i][j];
                    newLayout[i][j] = temp;
                }
            }
        }
        return newLayout;
    }

    //helper method to see if this piece can be flipped
    public boolean canBeFlipped() {
        String pName = this.getName();
        if (pName.equals("one") || pName.equals("two") || pName.equals("three")
                || pName.equals("four") || pName.equals("five") || pName.equals("cube")
                || pName.equals("X")) {
            return false;
        }
        return true;
    }

    //getters for the length and widths or each piece
    public int getPieceLength() {
        int length = 1;
        for (int i = 0; i < pieceLayout.length; i++) {
            for (int j = 0; j < pieceLayout.length; j++) {
                if (pieceLayout[i][j] != -1 && j >= length) {
                    length++;
                }
            }
        }
        return length;
    }

    public int getPieceWidth() {
        int width = 1;
        for (int i = 0; i < pieceLayout.length; i++) {
            for (int j = 0; j < pieceLayout.length; j++) {
                if (pieceLayout[i][j] != -1 && i >= width) {
                    width++;
                }
            }
        }
        return width;
    }


    //Setters and Getters
    public void setName(String name) {
        pieceName = name;
    }

    public String getName() {
        return pieceName;
    }

    public int getPieceValue() {
        return pieceValue;
    }

    public int getPieceColor() {
        return pieceColor;
    }

    public int getColorNum() {
        return colorNum;
    }

    public int getXPosition() {
        return xPosition;
    }

    public int[][] getPieceLayout() {
        return pieceLayout;
    }

    public void setPieceLayout(int[][] layout) {
        this.pieceLayout = layout;
    }

    public boolean getIsOnBoard() {
        return isOnBoard;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public void setPieceColor(int initColor) {
        this.pieceColor = initColor;
    }

    public void setOnBoard(boolean init) {
        this.isOnBoard = init;
    }

    public int getYPosition() {
        return yPosition;
    }

    public int getOrientationVal() {
        return orientationVal;
    }

    public void setOrientationVal(int val) {
        this.orientationVal = val;
    }
}
/**
 * External Citation:
 * Date: 30 March 2019
 * Problem: I didn't know the algorythm for rotating things in
 * a 2D array
 * Source:https://stackoverflow.com/questions/53110374/how-to-rotate-2-d-array-in-java
 */

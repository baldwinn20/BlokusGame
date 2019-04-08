package com.example.nicholasbaldwin.mockupgui;

import android.graphics.Color;

/**
 * <!-- class Piece -->
 * <p>
 * This class controls the attributes of the pieces used in Blokus.
 * Each player is to have 21 pieces in their inventories each with
 * different shapes and point values.
 *
 * @author <Justin Cao>
 * @author <Dylan Pascua>
 * @author <Nicholas Baldwin>
 */
public class Piece {
    //This will be used to identify the type of piece created
    //for this instance
    private String pieceName;

    //How much the piece is worth in terms of points
    private int pieceValue;
    private int pieceColor;
    protected int xPosition = -1;
    protected int yPosition = -1;
    private int colorNum;

    //0,1,2,3 are different orientation displays
    protected int orientationVal = 0;

    //how the pieces are arranged in terms of ints
    protected int[][] pieceLayout = new int[5][5];

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
        } else {
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

    //getters for the length and widths or each piece
    public int getPieceLength() {
        int length = 0;
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
        int width = 0;
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

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
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

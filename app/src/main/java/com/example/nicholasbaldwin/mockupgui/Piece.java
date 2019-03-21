package com.example.nicholasbaldwin.mockupgui;
/**
 * <!-- class Piece -->
 *
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

    //0,1,2,3 are different orientation displays
    protected int orientationVal = 0;

    //if true, remove from a player's inventory user interface
    protected boolean isOnBoard = false;
    //An array of this width will be used to contain each piece
    protected final int pieceWidth = 5;

    /**
     * Constructor for the Piece class
     *
     * Initializes a piece with its type, score value, and color.
     * This instance will be stored in each player's inventory.
     *
     * @param  initName Name of the player
     * @param initScore Type of player (Human, AI, or Network)
     * @param  initColor Chosen color of a player
     */
    public Piece(String initName, int initScore, int initColor) {
        pieceName = initName;
        pieceValue = initScore;
        pieceColor = initColor;
        orientationVal = 0;
    }

    //Setters and Getters
    public String getName() {return pieceName;}
    public int getPieceValue() {return pieceValue;}
    public int getPieceColor() {return pieceColor;}
    public int getXPosition(){return xPosition;}
    public void setxPosition(int xPosition){this.xPosition = xPosition;}
    public void setyPosition(int yPosition) {this.yPosition = yPosition;}
    public int getYPosition(){return yPosition;}
    public int getOrientationVal(){return orientationVal;}
    public void setOrientationVal(int val){this.orientationVal = val;}
}

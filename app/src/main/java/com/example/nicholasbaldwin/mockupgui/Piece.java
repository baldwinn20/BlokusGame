package com.example.nicholasbaldwin.mockupgui;

import android.graphics.Color;

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
    private  int colorNum;

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

        //setting up a blank piece layout
        for(int i = 0; i < pieceLayout.length; i++){
            for(int j = 0; j < pieceLayout.length;j++){
                pieceLayout[i][j] = -1;
            }
        }

        //todo make a switch for color to replace the zeros
        if(pieceColor == Color.RED){
            colorNum = 0;
        }
        else if (pieceColor == Color.BLUE){
            colorNum = 1;
        }
        else if (pieceColor == Color.GREEN){
            colorNum = 2;
        }
        else{
            colorNum = 3;
        }

        //the setups for each piece
        if(pieceName.equals("one")){
          pieceLayout[0][0] = colorNum;
        }
        else if(pieceName.equals("two")){
            pieceLayout[0][0] = colorNum;
            pieceLayout[0][1] = colorNum;
        }
        else if(pieceName.equals("VThree")){
            pieceLayout[0][0] = colorNum;
            pieceLayout[1][0] = colorNum;
            pieceLayout[1][1] = colorNum;
        }
        else if(pieceName.equals("IThree")){
            pieceLayout[0][0] = colorNum;
            pieceLayout[1][0] = colorNum;
            pieceLayout[2][0] = colorNum;
        }
        else if(pieceName.equals("TFour")){
            pieceLayout[1][0] = colorNum;
            pieceLayout[1][1] = colorNum;
            pieceLayout[0][1] = colorNum;
            pieceLayout[2][1] = colorNum;
        }
        else if(pieceName.equals("O")){
            pieceLayout[0][0] = colorNum;
            pieceLayout[1][0] = colorNum;
            pieceLayout[0][1] = colorNum;
            pieceLayout[1][1] = colorNum;
        }
        else if(pieceName.equals("LFour")){
            pieceLayout[0][0] = colorNum;
            pieceLayout[0][1] = colorNum;
            pieceLayout[1][1] = colorNum;
            pieceLayout[2][1] = colorNum;
        }
        else if(pieceName.equals("IFour")){
            pieceLayout[0][0] = colorNum;
            pieceLayout[1][0] = colorNum;
            pieceLayout[2][0] = colorNum;
            pieceLayout[3][0] = colorNum;
        }
        else if(pieceName.equals("ZFour")){
            pieceLayout[0][0] = colorNum;
            pieceLayout[0][1] = colorNum;
            pieceLayout[1][1] = colorNum;
            pieceLayout[1][2] = colorNum;
        }
        else if(pieceName.equals("F")){
            pieceLayout[1][0] = colorNum;
            pieceLayout[1][1] = colorNum;
            pieceLayout[0][1] = colorNum;
            pieceLayout[0][2] = colorNum;
            pieceLayout[2][1] = colorNum;
        }
        else if(pieceName.equals("X")){
            pieceLayout[1][0] = colorNum;
            pieceLayout[1][1] = colorNum;
            pieceLayout[0][1] = colorNum;
            pieceLayout[2][1] = colorNum;
            pieceLayout[1][2] = colorNum;
        }
        else if(pieceName.equals("P")){
            pieceLayout[0][0] = colorNum;
            pieceLayout[1][0] = colorNum;
            pieceLayout[0][1] = colorNum;
            pieceLayout[1][1] = colorNum;
            pieceLayout[0][2] = colorNum;
        }
        else if(pieceName.equals("W")){
            pieceLayout[0][0] = colorNum;
            pieceLayout[0][1] = colorNum;
            pieceLayout[1][1] = colorNum;
            pieceLayout[1][2] = colorNum;
            pieceLayout[2][2] = colorNum;
        }
        else if(pieceName.equals("ZFive")){
            pieceLayout[0][0] = colorNum;
            pieceLayout[0][1] = colorNum;
            pieceLayout[1][1] = colorNum;
            pieceLayout[2][1] = colorNum;
            pieceLayout[2][2] = colorNum;
        }
        else if(pieceName.equals("Y")){
            pieceLayout[0][0] = colorNum;
            pieceLayout[1][0] = colorNum;
            pieceLayout[1][1] = colorNum;
            pieceLayout[2][0] = colorNum;
            pieceLayout[3][0] = colorNum;
        }
        else if(pieceName.equals("LFive")){
            pieceLayout[0][0] = colorNum;
            pieceLayout[0][1] = colorNum;
            pieceLayout[1][0] = colorNum;
            pieceLayout[2][0] = colorNum;
            pieceLayout[3][0] = colorNum;
        }
        else if(pieceName.equals("U")){
            pieceLayout[0][0] = colorNum;
            pieceLayout[0][1] = colorNum;
            pieceLayout[1][0] = colorNum;
            pieceLayout[2][0] = colorNum;
            pieceLayout[2][1] = colorNum;
        }
        else if(pieceName.equals("TFive")){
            pieceLayout[1][0] = colorNum;
            pieceLayout[1][1] = colorNum;
            pieceLayout[1][2] = colorNum;
            pieceLayout[0][2] = colorNum;
            pieceLayout[2][2] = colorNum;
        }
        else if(pieceName.equals("VFive")){
            pieceLayout[0][0] = colorNum;
            pieceLayout[0][1] = colorNum;
            pieceLayout[0][2] = colorNum;
            pieceLayout[1][2] = colorNum;
            pieceLayout[2][2] = colorNum;
        }
        else if(pieceName.equals("N")){
            pieceLayout[0][1] = colorNum;
            pieceLayout[1][0] = colorNum;
            pieceLayout[1][1] = colorNum;
            pieceLayout[2][0] = colorNum;
            pieceLayout[3][0] = colorNum;
        }
        else if(pieceName.equals("IFive")){
            pieceLayout[0][0] = colorNum;
            pieceLayout[1][0] = colorNum;
            pieceLayout[2][0] = colorNum;
            pieceLayout[3][0] = colorNum;
            pieceLayout[4][0] = colorNum;
        }

    }
    //getters for the length and widths or each piece
    public int getPieceLength(){
        int length = 0;
        for(int i = 0; i < pieceLayout.length; i++){
            for(int j = 0; j < pieceLayout.length;j++){
                if(pieceLayout[i][j] != -1 && j >= length){
                    length++;
                }
            }
        }
        return length;
    }

    public int getPieceWidth(){
        int width = 0;
        for(int i = 0; i < pieceLayout.length; i++){
            for(int j = 0; j < pieceLayout.length;j++){
                if(pieceLayout[i][j] != -1 && i >= width){
                    width++;
                }
            }
        }
        return width;
    }


    //Setters and Getters
    public String getName() {return pieceName;}
    public int getPieceValue() {return pieceValue;}
    public int getPieceColor() {return pieceColor;}

    public int getColorNum() {
        return colorNum;
    }

    public int getXPosition(){return xPosition;}
    public int[][] getPieceLayout(){return pieceLayout;}
    public void setPieceLayout(int[][] layout){this.pieceLayout = layout;}
    public void setxPosition(int xPosition){this.xPosition = xPosition;}
    public void setyPosition(int yPosition) {this.yPosition = yPosition;}
    public int getYPosition(){return yPosition;}
    public int getOrientationVal(){return orientationVal;}
    public void setOrientationVal(int val){this.orientationVal = val;}
}

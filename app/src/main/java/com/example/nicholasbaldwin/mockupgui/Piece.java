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

    //how the pieces are arranged in terms of ints
    protected int[][] pieceLayout = new int[7][7];

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

        //the setups for each piece
        if(pieceName.equals("one")){
          pieceLayout[3][3] = 0;
        }
        else if(pieceName.equals("two")){
            pieceLayout[3][2] = 0;
            pieceLayout[3][3] = 0;
        }
        else if(pieceName.equals("VThree")){
            pieceLayout[2][3] = 0;
            pieceLayout[3][2] = 0;
            pieceLayout[3][3] = 0;
        }
        else if(pieceName.equals("IThree")){
            pieceLayout[3][2] = 0;
            pieceLayout[3][3] = 0;
            pieceLayout[3][4] = 0;
        }
        else if(pieceName.equals("TFour")){
            pieceLayout[3][2] = 0;
            pieceLayout[3][3] = 0;
            pieceLayout[2][3] = 0;
            pieceLayout[4][3] = 0;
        }
        else if(pieceName.equals("O")){
            pieceLayout[2][3] = 0;
            pieceLayout[3][3] = 0;
            pieceLayout[2][2] = 0;
            pieceLayout[3][2] = 0;
        }
        else if(pieceName.equals("LFour")){
            pieceLayout[2][2] = 0;
            pieceLayout[2][3] = 0;
            pieceLayout[3][3] = 0;
            pieceLayout[4][3] = 0;
        }
        else if(pieceName.equals("IFour")){
            pieceLayout[3][2] = 0;
            pieceLayout[3][3] = 0;
            pieceLayout[3][4] = 0;
            pieceLayout[3][5] = 0;
        }
        else if(pieceName.equals("ZFour")){
            pieceLayout[2][2] = 0;
            pieceLayout[2][3] = 0;
            pieceLayout[3][3] = 0;
            pieceLayout[3][4] = 0;
        }
        else if(pieceName.equals("F")){
            pieceLayout[3][2] = 0;
            pieceLayout[3][3] = 0;
            pieceLayout[2][3] = 0;
            pieceLayout[2][4] = 0;
            pieceLayout[4][3] = 0;
        }
        else if(pieceName.equals("X")){
            pieceLayout[3][2] = 0;
            pieceLayout[3][3] = 0;
            pieceLayout[3][4] = 0;
            pieceLayout[2][3] = 0;
            pieceLayout[4][3] = 0;
        }
        else if(pieceName.equals("P")){
            pieceLayout[3][2] = 0;
            pieceLayout[3][3] = 0;
            pieceLayout[3][4] = 0;
            pieceLayout[4][2] = 0;
            pieceLayout[4][3] = 0;
        }
        else if(pieceName.equals("W")){
            pieceLayout[2][3] = 0;
            pieceLayout[3][3] = 0;
            pieceLayout[2][2] = 0;
            pieceLayout[3][4] = 0;
            pieceLayout[4][4] = 0;
        }
        else if(pieceName.equals("ZFive")){
            pieceLayout[2][2] = 0;
            pieceLayout[2][3] = 0;
            pieceLayout[3][3] = 0;
            pieceLayout[4][3] = 0;
            pieceLayout[4][4] = 0;
        }
        else if(pieceName.equals("Y")){
            pieceLayout[1][3] = 0;
            pieceLayout[2][3] = 0;
            pieceLayout[2][4] = 0;
            pieceLayout[3][3] = 0;
            pieceLayout[4][3] = 0;
        }
        else if(pieceName.equals("LFive")){
            pieceLayout[1][3] = 0;
            pieceLayout[1][4] = 0;
            pieceLayout[2][3] = 0;
            pieceLayout[3][3] = 0;
            pieceLayout[4][3] = 0;
        }
        else if(pieceName.equals("U")){
            pieceLayout[2][4] = 0;
            pieceLayout[2][3] = 0;
            pieceLayout[3][3] = 0;
            pieceLayout[4][3] = 0;
            pieceLayout[4][4] = 0;
        }
        else if(pieceName.equals("TFive")){
            pieceLayout[3][2] = 0;
            pieceLayout[3][3] = 0;
            pieceLayout[3][4] = 0;
            pieceLayout[2][4] = 0;
            pieceLayout[4][4] = 0;
        }
        else if(pieceName.equals("VFive")){
            pieceLayout[2][2] = 0;
            pieceLayout[2][3] = 0;
            pieceLayout[2][4] = 0;
            pieceLayout[3][4] = 0;
            pieceLayout[4][4] = 0;
        }
        else if(pieceName.equals("N")){
            pieceLayout[3][2] = 0;
            pieceLayout[3][3] = 0;
            pieceLayout[2][3] = 0;
            pieceLayout[4][2] = 0;
            pieceLayout[5][2] = 0;
        }
        else if(pieceName.equals("IFive")){
            pieceLayout[3][1] = 0;
            pieceLayout[3][2] = 0;
            pieceLayout[3][3] = 0;
            pieceLayout[3][4] = 0;
            pieceLayout[3][5] = 0;
        }

    }

    //Setters and Getters
    public String getName() {return pieceName;}
    public int getPieceValue() {return pieceValue;}
    public int getPieceColor() {return pieceColor;}
    public int getXPosition(){return xPosition;}
    public int[][] getPieceLayout(){return pieceLayout;}
    public void setPieceLayout(int[][] layout){this.pieceLayout = layout;}
    public void setxPosition(int xPosition){this.xPosition = xPosition;}
    public void setyPosition(int yPosition) {this.yPosition = yPosition;}
    public int getYPosition(){return yPosition;}
    public int getOrientationVal(){return orientationVal;}
    public void setOrientationVal(int val){this.orientationVal = val;}
}

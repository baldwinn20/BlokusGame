package com.example.nicholasbaldwin.mockupgui;
import android.graphics.Color;

import com.example.nicholasbaldwin.mockupgui.game.infoMsg.GameState;
import com.example.nicholasbaldwin.mockupgui.game.util.GamePlayer;

import java.util.ArrayList;

/**
 * <!-- class BlokusGameState-->
 *
 * This class contains all the information needed about the current
 * state of the Blokus game in order to display it properly for a human user or allow a computer
 * player to make decisions.
 * and the board.
 *
 * @author <Justin Cao>
 * @author <Dylan Pascua>
 * @author <Nicholas Baldwin>
 */

public class BlokusGameState extends GameState {

    //Every player's piece inventory will be stored in one encompassing inventory
    //for easier access to specific player pieces
    private ArrayList<ArrayList<Piece>> allPieceInventory = new ArrayList<>();

    //0 for placement stage, 1 for waiting stage (Will be used with Network/AI)
    private int stage;

    //An integer array will help differentiate whose pieces are on the board.
    private int[][] board = new int[20][20];
    private static final int BOARD_LENGTH = 20;
    private int playerToMove;

    /**
     * Constructor for the BlokusGameState class
     *
     * Sets up the game state to be in a condition as
     * if a new Blokus game has been started.
     *
     *
     */
    public BlokusGameState() {

        //Initializes the Board to simulate the start of a Blokus Game
        for (int i = 0; i < BOARD_LENGTH; i++) {
            for (int j = 0; j < BOARD_LENGTH; j++) {
                //-1 means there are no pieces on that place
                //and 0,1,2,3 correspond to a players ID
                board[i][j] = -1;
            }
        }


        for (int i = 0; i < 4; i++) {
            allPieceInventory.add(initializeInventories(i));
        }
        //When the game starts, the first player will be able to place a piece on the board
        stage = 0;
        playerToMove = 0;
    }

    /**
     * Deep Copy Constructor for the BlokusGameState class
     *
     * Copies over variables
     * from a previous state of the game
     *
     * @param bgs the game state object that contains information
     *            during a certain state of the game
     */
    public BlokusGameState(BlokusGameState bgs) { //Deep copy constructor
        /**
         External Citation
         Date: 26 February 2019
         Problem: Couldn't get the deep copy constructor to properly copy
         over immutable variables
         Resource:
         CS 301 Lecture Resource: Week 6: Deep Copy Example
         Solution: I used the example code from this post to improve my understanding
         */
        //Makes deep copies of the original game state's player immutable variables
        //This will allow players to see modified versions of the game state
        //and prevent cheating
        for (int i = 0; i < bgs.players.size(); i++) {
            this.players.add(new BlokusPlayer(bgs.players.get(i).playerName,
                    bgs.players.get(i).playerColor, bgs.players.get(i).playerType,
                    bgs.players.get(i).playerID));
            this.players.get(i).playerName = bgs.players.get(i).playerName;
            this.players.get(i).playerColor = bgs.players.get(i).playerColor;
            this.players.get(i).piecesRemaining = bgs.players.get(i).piecesRemaining;
            this.players.get(i).playerType = bgs.players.get(i).playerType;
            this.players.get(i).playerScore = bgs.players.get(i).playerScore;
            this.players.get(i).playerID = bgs.players.get(i).playerID;

            for (Piece p : this.players.get(i).piecesInventory) {
                for (Piece o : bgs.players.get(i).piecesInventory) {
                    p.xPosition = o.xPosition;
                    p.yPosition = o.yPosition;
                    p.isOnBoard = o.isOnBoard;
                    p.orientationVal = o.orientationVal;
                }
            }
            for (int k = 0; k < bgs.board.length; k++) {
                for (int j = 0; j < bgs.board.length; j++) {
                    this.board[k][j] = bgs.board[k][j];
                }
            }
            this.stage = 0;
            this.playerToMove = bgs.playerToMove;

        }

    }


    /**
     * rotate90
     *
     * Changes a player's orientation for a specific piece by 90 degrees
     *
     * @return true if it is that specific player's turn and false otherwise
     */
    public boolean rotate90(int pID ,int pieceLocation){
        BlokusPlayer p = this.players.get(pID);
        //For testing purposes,the LFive piece will be rotated in this method called
        //from the MainActivity class
        Piece pc = p.getPiecesInventory().get(pieceLocation);
        if (pc.getOrientationVal() == 0) {
            pc.setOrientationVal(1);
        } else if (pc.getOrientationVal() == 1) {
            pc.setOrientationVal(2);
        } else if (pc.getOrientationVal() == 2) {
            pc.setOrientationVal(3);
        } else {
            pc.setOrientationVal(0);
        }
        return true;
    }

    /**
     * flip
     *
     * reflects the piece over a horizontal line to change its orientation
     *
     * @return true if it is that specific player's turn and false otherwise
     */
    public boolean flip(int pID, int pieceLocation){
        BlokusPlayer p = this.players.get(pID);
        //For testing purposes, the player's X piece will be flipped;
        Piece pc = p.getPiecesInventory().get(pieceLocation);
        if (pc.getOrientationVal() == 0) {
            pc.setOrientationVal(2);
        } else if (pc.getOrientationVal() == 1) {
            pc.setOrientationVal(3);
        } else if (pc.getOrientationVal() == 2) {
            pc.setOrientationVal(0);
        } else {
            pc.setOrientationVal(1);
        }
        return true;
    }

    /**
     * placePiece
     *
     * CAVEAT:      Method not fully implemented for GameState assignment.
     *              Board will be set as one big array, and each piece
     *              will be a smaller array. Small arrays will be inserted
     *              into board array, provided there are no pieces of like
     *              color in the desired spot.
     *
     * Places a currently selected piece from a player's piece inventory onto
     * the board.
     *
     * @return true if it is that specific player's turn and false otherwise
     */
    public boolean placePiece(int pId, int row, int col, int pieceLocation) {
        //ret is needed because the method will not recognize boolean
        //return values inside the 'if' statements
        Piece pc = allPieceInventory.get(playerToMove).get();
        boolean ret = true;

        //will equal the # of columns and rows in
        //the piece's 2D array, respectively
        int x = pc.pieceWidth;
        int y = pc.pieceWidth;
        if (pc.isOnBoard) {
            ret = false;
        } else {
            for (int i = 0; i < x; i++) {
                for (int j = 0; j < y; j++) {
                    //if a square on the board is not empty,
                    //place the piece and hide it from the inventory.
                    if (this.board[row + i][col + j] != -1) {
                        ret = false;
                    } else {
                        pc.isOnBoard = true;
                        pc.setxPosition(row);
                        pc.setyPosition(col);
                        ret = true;
                    }
                }
            }
        }
        return ret;
    }


    public ArrayList<Piece> initializeInventories(int pIndex){
        //Add the 21 default pieces into a player's inventory

        ArrayList<Piece> inv = new ArrayList<>();

        switch(pIndex) {
            case 0:
                inv.add(new Piece("one", 1, Color.RED));
                inv.add(new Piece("two", 2, Color.RED));
                inv.add(new Piece("VThree", 3, Color.RED));
                inv.add(new Piece("LThree", 3, Color.RED));
                inv.add(new Piece("TFour", 4, Color.RED));
                inv.add(new Piece("O", 4, Color.RED));
                inv.add(new Piece("LFour", 4, Color.RED));
                inv.add(new Piece("lFour", 4, Color.RED));
                inv.add(new Piece("zFour", 4, Color.RED));
                inv.add(new Piece("F", 5, Color.RED));
                inv.add(new Piece("X", 5, Color.RED));
                inv.add(new Piece("P", 5, Color.RED));
                inv.add(new Piece("W", 5, Color.RED));
                inv.add(new Piece("ZFive", 5, Color.RED));
                inv.add(new Piece("Y", 5, Color.RED));
                inv.add(new Piece("LFive", 5, Color.RED));
                inv.add(new Piece("U", 5, Color.RED));
                inv.add(new Piece("TFive", 5, Color.RED));
                inv.add(new Piece("VFive", 5, Color.RED));
                inv.add(new Piece("N", 5, Color.RED));
                inv.add(new Piece("lFive", 5, Color.RED));
                break;
            case 1:
                inv.add(new Piece("one", 1, Color.BLUE));
                inv.add(new Piece("two", 2, Color.BLUE));
                inv.add(new Piece("VThree", 3, Color.BLUE));
                inv.add(new Piece("LThree", 3, Color.BLUE));
                inv.add(new Piece("TFour", 4, Color.BLUE));
                inv.add(new Piece("O", 4, Color.BLUE));
                inv.add(new Piece("LFour", 4, Color.BLUE));
                inv.add(new Piece("lFour", 4, Color.BLUE));
                inv.add(new Piece("zFour", 4, Color.BLUE));
                inv.add(new Piece("F", 5, Color.BLUE));
                inv.add(new Piece("X", 5, Color.BLUE));
                inv.add(new Piece("P", 5, Color.BLUE));
                inv.add(new Piece("W", 5, Color.BLUE));
                inv.add(new Piece("ZFive", 5, Color.BLUE));
                inv.add(new Piece("Y", 5, Color.BLUE));
                inv.add(new Piece("LFive", 5, Color.BLUE));
                inv.add(new Piece("U", 5, Color.BLUE));
                inv.add(new Piece("TFive", 5, Color.BLUE));
                inv.add(new Piece("VFive", 5, Color.BLUE));
                inv.add(new Piece("N", 5, Color.BLUE));
                inv.add(new Piece("lFive", 5, Color.BLUE));
                break;
            case 2:
                inv.add(new Piece("one", 1, Color.GREEN));
                inv.add(new Piece("two", 2, Color.GREEN));
                inv.add(new Piece("VThree", 3, Color.GREEN));
                inv.add(new Piece("LThree", 3, Color.GREEN));
                inv.add(new Piece("TFour", 4, Color.GREEN));
                inv.add(new Piece("O", 4, Color.GREEN));
                inv.add(new Piece("LFour", 4, Color.GREEN));
                inv.add(new Piece("lFour", 4, Color.GREEN));
                inv.add(new Piece("zFour", 4, Color.GREEN));
                inv.add(new Piece("F", 5, Color.GREEN));
                inv.add(new Piece("X", 5, Color.GREEN));
                inv.add(new Piece("P", 5, Color.GREEN));
                inv.add(new Piece("W", 5, Color.GREEN));
                inv.add(new Piece("ZFive", 5, Color.GREEN));
                inv.add(new Piece("Y", 5, Color.GREEN));
                inv.add(new Piece("LFive", 5, Color.GREEN));
                inv.add(new Piece("U", 5, Color.GREEN));
                inv.add(new Piece("TFive", 5, Color.GREEN));
                inv.add(new Piece("VFive", 5, Color.GREEN));
                inv.add(new Piece("N", 5, Color.GREEN));
                inv.add(new Piece("lFive", 5, Color.GREEN));
                break;
            case 3:
                inv.add(new Piece("one", 1, Color.YELLOW));
                inv.add(new Piece("two", 2, Color.YELLOW));
                inv.add(new Piece("VThree", 3, Color.YELLOW));
                inv.add(new Piece("LThree", 3, Color.YELLOW));
                inv.add(new Piece("TFour", 4, Color.YELLOW));
                inv.add(new Piece("O", 4, Color.YELLOW));
                inv.add(new Piece("LFour", 4, Color.YELLOW));
                inv.add(new Piece("lFour", 4, Color.YELLOW));
                inv.add(new Piece("zFour", 4, Color.YELLOW));
                inv.add(new Piece("F", 5, Color.YELLOW));
                inv.add(new Piece("X", 5, Color.YELLOW));
                inv.add(new Piece("P", 5, Color.YELLOW));
                inv.add(new Piece("W", 5, Color.YELLOW));
                inv.add(new Piece("ZFive", 5, Color.YELLOW));
                inv.add(new Piece("Y", 5, Color.YELLOW));
                inv.add(new Piece("LFive", 5, Color.YELLOW));
                inv.add(new Piece("U", 5, Color.YELLOW));
                inv.add(new Piece("TFive", 5, Color.YELLOW));
                inv.add(new Piece("VFive", 5, Color.YELLOW));
                inv.add(new Piece("N", 5, Color.YELLOW));
                inv.add(new Piece("lFive", 5, Color.YELLOW));
                break;
            default:
                break;
        }
        return inv;
    }

    public void setPlayerTurn(BlokusPlayer p){
        this.playerToMove = p.playerID;
    }
    public int getPlayerTurn(){return this.playerToMove;}
}
/**
 External Citation
 Date: 26 February 2019
 Problem: Needed to see an example of a game state
 Resource:
 https://github.com/srvegdahl/TttGame
 Solution: I used the example code from this post to improve my understanding
 */

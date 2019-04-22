package com.example.nicholasbaldwin.mockupgui;

import android.graphics.Color;
import android.util.Log;

import com.example.nicholasbaldwin.mockupgui.game.infoMsg.GameState;
import com.example.nicholasbaldwin.mockupgui.game.util.GamePlayer;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * <!-- class BlokusGameState-->
 * <p>
 * This class contains all the information needed about the current
 * state of the Blokus game in order to display it properly for a human
 * user or allow a computer player to make decisions.
 *
 * @author <Justin Cao, Dylan Pascua, Nicholas Baldwin>
 * @version <Spring 2019>
 */

public class BlokusGameState extends GameState implements Serializable {

    //Every player's piece inventory will be stored in one encompassing inventory
    //for easier access to specific player pieces
    private ArrayList<ArrayList<Piece>> allPieceInventory = new ArrayList<>();

    private int[] allPiecesRemaining = new int[4];
    private int[] allPlayerScores = new int[4];
    private int[] allPlayerTilesRemaining = new int[4];
    private boolean[] allPlayersGivenUp = new boolean[4];

    public static final int BOARD_LENGTH = 20;
    //An integer array will help differentiate whose pieces are on the board.
    private int[][] board = new int[BOARD_LENGTH][BOARD_LENGTH];
    public final static int MAXIMUM_PLAYER_SCORE = 89;
    private static final int INITIAL_PLAYER_PIECE_COUNT = 21;
    private int playerToMove;

    /**
     * Constructor for the BlokusGameState class
     * <p>
     * Sets up the game state to be in a condition as
     * if a new Blokus game has been started.
     */
    public BlokusGameState() {

        //Initializes the Board to simulate the start of a Blokus Game
        for (int i = 0; i < BOARD_LENGTH; i++) {
            for (int j = 0; j < BOARD_LENGTH; j++) {
                //-1 means there are no pieces on that place
                //and 0,1,2,3 correspond to a players ID
                board[i][j] = Piece.EMPTY;
            }
        }


        for (int i = 0; i < 4; i++) {
            allPieceInventory.add(initializeInventories(i));
            allPiecesRemaining[i] = INITIAL_PLAYER_PIECE_COUNT;
            allPlayerScores[i] = 0;
            allPlayerTilesRemaining[i] = MAXIMUM_PLAYER_SCORE;
            allPlayersGivenUp[i] = false;
        }
        //When the game starts, the first player will be able to place a piece on the board
        playerToMove = 0;
    }

    /**
     * Deep Copy Constructor for the BlokusGameState class
     * <p>
     * Copies over variables
     * from a previous state of the game
     *
     * @param bgs the game state object that contains information
     *            during a certain state of the game
     */
    public BlokusGameState(BlokusGameState bgs, int playerID) { //Deep copy constructor
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

        for (int i = 0; i < 4; i++) {
            this.allPieceInventory.add(new ArrayList<Piece>());
//            if (playerID >= 0 && playerID != i) {
//                continue;
//            }
            for (int j = 0; j < bgs.allPieceInventory.get(i).size(); j++) {
                Piece src = bgs.allPieceInventory.get(i).get(j);
                Piece newPiece = new Piece(src.getName(),
                        src.getPieceValue(), src.getPieceColor());
                newPiece.isOnBoard = src.isOnBoard;
                newPiece.orientationVal = src.orientationVal;
                newPiece.xPosition = src.xPosition;
                newPiece.yPosition = src.yPosition;
                this.allPieceInventory.get(i).add(newPiece);
            }
        }

        for (int i = 0; i < 4; i++) {
            this.allPiecesRemaining[i] = bgs.allPiecesRemaining[i];
            this.allPlayerScores[i] = bgs.allPlayerScores[i];
            this.allPlayersGivenUp[i] = bgs.allPlayersGivenUp[i];
        }
        for (int k = 0; k < bgs.board.length; k++) {
            for (int j = 0; j < bgs.board.length; j++) {
                this.board[k][j] = bgs.board[k][j];
            }
        }
        this.playerToMove = bgs.playerToMove;

    }


    /**
     * rotate90
     * <p>
     * Changes a player's orientation for a specific piece by 90 degrees
     *
     * @return true if it is that specific player's turn and false otherwise
     */
    public boolean rotate90(Piece pc) {
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
     * <p>
     * reflects the piece over a horizontal line to change its orientation
     *
     * @return true if it is that specific player's turn and false otherwise
     */
    public boolean flip(Piece pc) {
        //For testing purposes, the player's X piece will be flipped;
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
     * <p>
     * CAVEAT:      Method not fully implemented for GameState assignment.
     * Board will be set as one big array, and each piece
     * will be a smaller array. Small arrays will be inserted
     * into board array, provided there are no pieces of like
     * color in the desired spot.
     * <p>
     * Places a currently selected piece from a player's piece inventory onto
     * the board.
     *
     * @return true if it is that specific player's turn and false otherwise
     */
    public boolean placePiece(int x, int y, Piece pc) {
        int[][] pieceArray = pc.getPieceLayout();
        for (int i = x; i < x + pc.getPieceWidth(); i++) {
            for (int j = y; j < y + pc.getPieceLength(); j++) {
                int xOffset = i - x;
                int yOffset = j - y;
                if (pieceArray[xOffset][yOffset] != -1) {
                    if (xOffset + x <= 19 && yOffset + y <= 19) {
                        this.board[x + xOffset][y + yOffset] = pieceArray[xOffset][yOffset];
                    }
                }
            }
        }
        pc.setOnBoard(true);
        this.allPlayerTilesRemaining[playerToMove] -= pc.getPieceValue();
        return true;
    }


    public ArrayList<Piece> initializeInventories(int pIndex) {
        //Add the 21 default pieces into a player's inventory

        ArrayList<Piece> inv = new ArrayList<>();

        switch (pIndex) {
            case 0:
                inv.add(new Piece("one", 1, Color.RED));
                inv.add(new Piece("two", 2, Color.RED));
                inv.add(new Piece("S", 4, Color.RED));
                inv.add(new Piece("three", 3, Color.RED));
                inv.add(new Piece("smallT", 4, Color.RED));
                inv.add(new Piece("four", 4, Color.RED));
                inv.add(new Piece("fourL", 4, Color.RED));
                inv.add(new Piece("five", 5, Color.RED));
                inv.add(new Piece("fiveL", 5, Color.RED));
                inv.add(new Piece("N", 5, Color.RED));
                inv.add(new Piece("Y", 5, Color.RED));
                inv.add(new Piece("v3", 3, Color.RED));
                inv.add(new Piece("cube", 4, Color.RED));
                inv.add(new Piece("C", 5, Color.RED));
                inv.add(new Piece("B", 5, Color.RED));
                inv.add(new Piece("Z", 5, Color.RED));
                inv.add(new Piece("M", 5, Color.RED));
                inv.add(new Piece("X", 5, Color.RED));
                inv.add(new Piece("F", 5, Color.RED));
                inv.add(new Piece("bigT", 5, Color.RED));
                inv.add(new Piece("corner", 5, Color.RED));
                break;
            case 1:
                inv.add(new Piece("one", 1, Color.BLUE));
                inv.add(new Piece("two", 2, Color.BLUE));
                inv.add(new Piece("S", 4, Color.BLUE));
                inv.add(new Piece("three", 3, Color.BLUE));
                inv.add(new Piece("smallT", 4, Color.BLUE));
                inv.add(new Piece("four", 4, Color.BLUE));
                inv.add(new Piece("fourL", 4, Color.BLUE));
                inv.add(new Piece("five", 5, Color.BLUE));
                inv.add(new Piece("fiveL", 5, Color.BLUE));
                inv.add(new Piece("N", 5, Color.BLUE));
                inv.add(new Piece("Y", 5, Color.BLUE));
                inv.add(new Piece("v3", 3, Color.BLUE));
                inv.add(new Piece("cube", 4, Color.BLUE));
                inv.add(new Piece("C", 5, Color.BLUE));
                inv.add(new Piece("B", 5, Color.BLUE));
                inv.add(new Piece("Z", 5, Color.BLUE));
                inv.add(new Piece("M", 5, Color.BLUE));
                inv.add(new Piece("X", 5, Color.BLUE));
                inv.add(new Piece("F", 5, Color.BLUE));
                inv.add(new Piece("bigT", 5, Color.BLUE));
                inv.add(new Piece("corner", 5, Color.BLUE));
                break;
            case 2:
                inv.add(new Piece("one", 1, Color.GREEN));
                inv.add(new Piece("two", 2, Color.GREEN));
                inv.add(new Piece("S", 4, Color.GREEN));
                inv.add(new Piece("three", 3, Color.GREEN));
                inv.add(new Piece("smallT", 4, Color.GREEN));
                inv.add(new Piece("four", 4, Color.GREEN));
                inv.add(new Piece("fourL", 4, Color.GREEN));
                inv.add(new Piece("five", 5, Color.GREEN));
                inv.add(new Piece("fiveL", 5, Color.GREEN));
                inv.add(new Piece("N", 5, Color.GREEN));
                inv.add(new Piece("Y", 5, Color.GREEN));
                inv.add(new Piece("v3", 3, Color.GREEN));
                inv.add(new Piece("cube", 4, Color.GREEN));
                inv.add(new Piece("C", 5, Color.GREEN));
                inv.add(new Piece("B", 5, Color.GREEN));
                inv.add(new Piece("Z", 5, Color.GREEN));
                inv.add(new Piece("M", 5, Color.GREEN));
                inv.add(new Piece("X", 5, Color.GREEN));
                inv.add(new Piece("F", 5, Color.GREEN));
                inv.add(new Piece("bigT", 5, Color.GREEN));
                inv.add(new Piece("corner", 5, Color.GREEN));
                break;
            case 3:
                inv.add(new Piece("one", 1, Color.YELLOW));
                inv.add(new Piece("two", 2, Color.YELLOW));
                inv.add(new Piece("S", 4, Color.YELLOW));
                inv.add(new Piece("three", 3, Color.YELLOW));
                inv.add(new Piece("smallT", 4, Color.YELLOW));
                inv.add(new Piece("four", 4, Color.YELLOW));
                inv.add(new Piece("fourL", 4, Color.YELLOW));
                inv.add(new Piece("five", 5, Color.YELLOW));
                inv.add(new Piece("fiveL", 5, Color.YELLOW));
                inv.add(new Piece("N", 5, Color.YELLOW));
                inv.add(new Piece("Y", 5, Color.YELLOW));
                inv.add(new Piece("v3", 3, Color.YELLOW));
                inv.add(new Piece("cube", 4, Color.YELLOW));
                inv.add(new Piece("C", 5, Color.YELLOW));
                inv.add(new Piece("B", 5, Color.YELLOW));
                inv.add(new Piece("Z", 5, Color.YELLOW));
                inv.add(new Piece("M", 5, Color.YELLOW));
                inv.add(new Piece("X", 5, Color.YELLOW));
                inv.add(new Piece("F", 5, Color.YELLOW));
                inv.add(new Piece("bigT", 5, Color.YELLOW));
                inv.add(new Piece("corner", 5, Color.YELLOW));
                break;
            default:
                break;
        }
        return inv;
    }

    public ArrayList<ArrayList<Piece>> getAllPieceInventory() {
        return this.allPieceInventory;
    }


    public void updatePiecesRemaining() {
        if (allPiecesRemaining[playerToMove] > 0) { //not < 0 pieces
            --allPiecesRemaining[playerToMove];
        }

    } // What if AI player can't move

    //TODO check to see if deepy copy is needed
    public int[] getAllPiecesRemaining() {
        return this.allPiecesRemaining;
    }

    public void updatePlayerScores(Piece curPiece) {
        if (allPlayerScores[playerToMove] < MAXIMUM_PLAYER_SCORE) {
            allPlayerScores[playerToMove] += curPiece.getPieceValue();
        }

    }

    public int[] getAllPlayerTilesRemaining() {
        return this.allPlayerTilesRemaining;
    }
    public boolean[] getAllPlayersGivenUp() {
        return this.allPlayersGivenUp;
    }
    public void setAllPlayersGivenUp(boolean init, int pID){
        this.allPlayersGivenUp[pID] = init;
    }

    //TODO check to see if deepy copy is needed
    public int[] getAllPlayerScores() {
        return this.allPlayerScores;
    }

    public void setPlayerTurn(int curTurn) {
        switch (curTurn) {
            case 0:
                this.playerToMove = 1;
                break;
            case 1:
                this.playerToMove = 2;
                break;
            case 2:
                this.playerToMove = 3;
                break;
            case 3:
                this.playerToMove = 0;
                break;
            default:
                break;
        }
    }

    public int getPlayerTurn() {
        return this.playerToMove;
    }

    public int[][] getBoard() {
        return this.board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public void removePiece(Piece currentPiece, int playerTurn) {
        //this.allPieceInventory.get(playerTurn).remove(currentPiece);
        //for loop look for
        Piece save = null;
        for (Piece p : this.allPieceInventory.get(playerTurn)) {
            if (p.getName().equals(currentPiece.getName())) {
                save = p;

            }
        }
        this.allPieceInventory.get(playerTurn).remove(save);
    }
}
/**
 * External Citation
 * Date: 26 February 2019
 * Problem: Needed to see an example of a game state
 * Resource:
 * https://github.com/srvegdahl/TttGame
 * Solution: I used the example code from this post to improve my understanding
 */

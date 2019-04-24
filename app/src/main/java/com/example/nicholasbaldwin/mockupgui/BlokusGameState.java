package com.example.nicholasbaldwin.mockupgui;

import android.graphics.Color;
import com.example.nicholasbaldwin.mockupgui.game.infoMsg.GameState;

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

    private final static int MAXIMUM_PLAYER_SCORE = 89;
    private static final int INITIAL_PLAYER_PIECE_COUNT = 21;
    public static final int BOARD_LENGTH = 20;
    private static final int PLAYER_COUNT = 4;

    //Attributes that each player has
    private int[] allPiecesRemaining = new int[PLAYER_COUNT];
    private int[] allPlayerScores = new int[PLAYER_COUNT];
    private boolean[] allPlayersGivenUp = new boolean[PLAYER_COUNT];

    //An integer array will help differentiate whose pieces are on the board.
    private int[][] board = new int[BOARD_LENGTH][BOARD_LENGTH];
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


        for (int i = 0; i < PLAYER_COUNT; i++) {
            allPieceInventory.add(initializeInventories(i));
            allPiecesRemaining[i] = INITIAL_PLAYER_PIECE_COUNT;
            allPlayerScores[i] = 0;
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

        for (int i = 0; i < PLAYER_COUNT; i++) {
            this.allPieceInventory.add(new ArrayList<Piece>());
            for (int j = 0; j < bgs.allPieceInventory.get(i).size(); j++) {
                Piece src = bgs.allPieceInventory.get(i).get(j);
                Piece newPiece = new Piece(src.getName(),
                        src.getPieceValue(), src.getPieceColor());
                newPiece.isOnBoard = src.isOnBoard;
                newPiece.xPosition = src.xPosition;
                newPiece.yPosition = src.yPosition;
                this.allPieceInventory.get(i).add(newPiece);
            }
        }

        for (int i = 0; i < PLAYER_COUNT; i++) {
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
     * placePiece
     * <p>
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
                if (pieceArray[xOffset][yOffset] != Piece.EMPTY) {
                    if (xOffset + x <= BOARD_LENGTH-1 && yOffset + y <= BOARD_LENGTH-1) {
                        this.board[x + xOffset][y + yOffset] = pieceArray[xOffset][yOffset];
                    }
                }
            }
        }
        pc.setOnBoard(true);
        return true;
    }


    public ArrayList<Piece> initializeInventories(int pIndex) {
        //Add the 21 default pieces into a player's inventory

        ArrayList<Piece> inv = new ArrayList<>();

        switch (pIndex) {
            case Piece.RED:
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
            case Piece.BLUE:
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
            case Piece.GREEN:
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
            case Piece.YELLOW:
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

    public int[] getAllPiecesRemaining() {
        return this.allPiecesRemaining;
    }

    public void updatePlayerScores(Piece curPiece) {
        if (allPlayerScores[playerToMove] < MAXIMUM_PLAYER_SCORE) {
            allPlayerScores[playerToMove] += curPiece.getPieceValue();
        }

    }

    public boolean[] getAllPlayersGivenUp() {
        return this.allPlayersGivenUp;
    }
    public void setAllPlayersGivenUp(int pID){
        this.allPlayersGivenUp[pID] = true;
    }

    public int[] getAllPlayerScores() {
        return this.allPlayerScores;
    }

    public void setPlayerTurn(int curTurn) {
        switch (curTurn) {
            case Piece.RED:
                this.playerToMove = Piece.BLUE;
                break;
            case Piece.BLUE:
                this.playerToMove = Piece.GREEN;
                break;
            case Piece.GREEN:
                this.playerToMove = Piece.YELLOW;
                break;
            case Piece.YELLOW:
                this.playerToMove = Piece.RED;
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

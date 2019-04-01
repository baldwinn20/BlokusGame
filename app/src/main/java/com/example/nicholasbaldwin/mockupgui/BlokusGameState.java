package com.example.nicholasbaldwin.mockupgui;
import android.graphics.Color;
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

public class BlokusGameState {

    private ArrayList<BlokusPlayer> players = new ArrayList<>();

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

        //Initializes the players to keep track of their variables throughout the game
        BlokusPlayer player1 = new BlokusPlayer("Human Player", Color.RED, 0, 0);
        BlokusPlayer player2 = new BlokusPlayer("Dumb AI", Color.BLUE, 1, 1);
        BlokusPlayer player3 = new BlokusPlayer("Smart AI", Color.YELLOW, 2, 2);
        BlokusPlayer player4 = new BlokusPlayer("Network Player", Color.GREEN, 3, 3);

        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);

        for (BlokusPlayer p : players) {
            allPieceInventory.add(initializeInventories(p));
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
     * toString
     *
     * Converts the data of a BlokusGameState instance into strings to be displayed
     *
     * @return playerData + inventoryData to show a record of individual player properties
     *                                    and what pieces they have in their inventories.
     */
    @Override
    public String toString() {
        /**
         External Citation
         Date: 26 February 2019
         Problem: Needed to find a way to convert variables into strings
         Resource:
            https://stackoverflow.com/questions/4842532/how-to-convert-an-integer-value-to-string
         Solution: I used the example code from this post to improve my understanding
         */
        String playerData = "\tPlayer Data: \n";
        String inventoryData = "\tInventory: \n";
        for (int i = 0; i < this.players.size(); i++) {
            playerData += "\t\t"+this.players.get(i).getPlayerName() +
                    "\n" +
                    "\t\t\tColor:" +
                    String.valueOf(this.players.get(i).getPlayerColor()) +
                    "\n" +
                    "\t\t\tPieces Rem:" +
                    String.valueOf(this.players.get(i).getPiecesRemaining()) +
                    "\n" +
                    "\t\t\tPlayer Type:" +
                    String.valueOf(this.players.get(i).getPlayerType()) +
                    "\n " +
                    "\t\t\tPlayer Score:" +
                    String.valueOf(this.players.get(i).getPlayerScore()) +
                    "\n";

            inventoryData += "\t\t"+this.players.get(i).getPlayerName()
                    + "'s Inventory: \n";
            for(Piece p : this.players.get(i).piecesInventory){
                inventoryData += "\t\t\t" + "Piece Name: "+p.getName() + " " +
                        "Piece Color: " + p.getPieceColor() + " " +
                       "Is on Board: " + String.valueOf(p.isOnBoard) + " " +
                        "X Position: " + String.valueOf(p.xPosition) + " " +
                        "Y Position: " + String.valueOf(p.yPosition) + " " +
                        "Orientation Value: "+
                        String.valueOf(p.orientationVal) + "\n";
            }

        }
        return playerData + inventoryData;
    }

    /**
     * isTurn
     *
     * Makes sure that it is the correct player's turn
     *
     * @return true if it is that specific player's turn and false otherwise
     */
    public boolean isTurn(BlokusPlayer p) {
        if (this.playerToMove == p.playerID) {
            return true;
        } else {
            return false;
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
        if(isTurn(p)) {
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
        return false;

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
        if(isTurn(p)){
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
        return false;
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
        BlokusPlayer p = this.players.get(pId);
        Piece pc = p.getPiecesInventory().get(pieceLocation);
        boolean ret = true;

        //will equal the # of columns and rows in
        //the piece's 2D array, respectively
        int x = pc.pieceWidth;
        int y = pc.pieceWidth;
        if (!isTurn(p)) {
            ret = false;
        } else if (isTurn(p) && pc.isOnBoard) {
            ret = false;
        } else {
            { //if is
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
        }
        return ret;
    }

    /**
     * setName
     *
     *
     * Sets the name of a certain player
     *
     * @return true if it the passed in ID matches that of a specific player
     *              and false otherwise
     */
    public boolean setName(String name, int pID) {
        for (BlokusPlayer p : this.players) {
            if (p.getPlayerID() == pID) {
                p.setPlayerName(name);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Piece> initializeInventories(BlokusPlayer bp){

        //Add the 21 default pieces into a player's inventory
        Piece one = new Piece("one", 1 , bp.playerColor);
        bp.piecesInventory.add(one);

        //done
        Piece two = new Piece("two",2, bp.playerColor);
        bp.piecesInventory.add(two);

        //done
        Piece vThree = new Piece("VThree", 3, bp.playerColor);
        bp.piecesInventory.add(vThree);

        //done
        Piece IThree = new Piece("IThree",3,bp.playerColor);
        bp.piecesInventory.add(IThree);

        //done
        Piece tFour = new Piece("TFour",4,bp.playerColor);
        bp.piecesInventory.add(tFour);

        //done
        Piece o = new Piece("O",4,bp.playerColor);
        bp.piecesInventory.add(o);

        //done
        Piece LFour = new Piece("LFour",4,bp.playerColor);
        bp.piecesInventory.add(LFour);

        //done
        Piece zFour = new Piece("ZFour",4,bp.playerColor);
        bp.piecesInventory.add(zFour);

        //done
        Piece f = new Piece("F",5,bp.playerColor);
        bp.piecesInventory.add(f);


        Piece x = new Piece("X",5,bp.playerColor);
        bp.piecesInventory.add(x);

        //done
        Piece p = new Piece("P",5,bp.playerColor);
        bp.piecesInventory.add(p);

        //done
        Piece w = new Piece("W",5,bp.playerColor);
        bp.piecesInventory.add(w);

        //done
        Piece zFive = new Piece("ZFive",5,bp.playerColor);
        bp.piecesInventory.add(zFive);

        //done
        Piece y = new Piece("Y",5,bp.playerColor);
        bp.piecesInventory.add(y);

        //done
        Piece LFive = new Piece("LFive",5,bp.playerColor);
        bp.piecesInventory.add(LFive);

        //done
        Piece u = new Piece("U",5,bp.playerColor);
        bp.piecesInventory.add(u);

        //done
        Piece tFive = new Piece("TFive",5,bp.playerColor);
        bp.piecesInventory.add(tFive);

        //done
        Piece vFive = new Piece("VFive",5,bp.playerColor);
        bp.piecesInventory.add(vFive);

        //done
        Piece n = new Piece("N",5,bp.playerColor);
        bp.piecesInventory.add(n);

        Piece IFive = new Piece("IFive",5,bp.playerColor);
        bp.piecesInventory.add(IFive);
        return bp.piecesInventory;
    } //contains all the piece arrays

    public ArrayList<BlokusPlayer> getPlayers(){
        return players;
    }
    public void setPlayerTurn(BlokusPlayer p){
        this.playerToMove = p.playerID;
    }
    public int[][] getBoard(){return  board;}
}
/**
 External Citation
 Date: 26 February 2019
 Problem: Needed to see an example of a game state
 Resource:
 https://github.com/srvegdahl/TttGame
 Solution: I used the example code from this post to improve my understanding
 */

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
        int[][] onePieceArr = new int[1][1];
        onePieceArr[0][0] = 1;
        bp.piecesInventory.add(one);

        //done
        Piece two = new Piece("two",2, bp.playerColor);
        int[][] twoPieceArr = new int[1][2];
        for (int i = 0; i < twoPieceArr.length; i++) {
            twoPieceArr[0][i] = 1;
        }
        bp.piecesInventory.add(two);

        //done
        Piece vThree = new Piece("VThree", 3, bp.playerColor);
        int[][] vThreePieceArr = new int[1][2];
        for (int i = 0; i < vThreePieceArr.length; i++) {
            for(int j = 0; j < vThreePieceArr.length; j++) {
                vThreePieceArr[i][j] = 1;
            }
        }
        vThreePieceArr[0][1] = 0;
        bp.piecesInventory.add(vThree);

        //done
        Piece IThree = new Piece("IThree",3,bp.playerColor);
        int[][] IThreePieceArr = new int[1][3];
        for (int i = 0; i < IThreePieceArr.length; i++) {
            IThreePieceArr[0][i] = 1;
        }
        bp.piecesInventory.add(IThree);

        //done
        Piece tFour = new Piece("TFour",4,bp.playerColor);
        int[][] tFourPieceArr = new int[2][3];
        for (int i = 0; i < tFourPieceArr.length; i++) {
            for(int j = 0; j < tFourPieceArr.length; j++) {
                tFourPieceArr[i][j] = 1;
            }
        }
        vThreePieceArr[0][0] = 0;
        vThreePieceArr[0][2] = 0;
        bp.piecesInventory.add(tFour);

        //done
        Piece o = new Piece("O",4,bp.playerColor);
        int[][] oPieceArr = new int[2][2];
        for (int i = 0; i < oPieceArr.length; i++) {
            for(int j = 0; j < oPieceArr.length; j++) {
                oPieceArr[i][j] = 1;
            }
        }
        bp.piecesInventory.add(o);

        //done
        Piece LFour = new Piece("LFour",4,bp.playerColor);
        int[][] LFourPieceArr = new int[2][3];
        for (int i = 0; i < LFourPieceArr.length; i++) {
            for(int j = 0; j < LFourPieceArr.length; j++) {
                LFourPieceArr[i][j] = 1;
            }
        }
        LFourPieceArr[0][1] = 0;
        LFourPieceArr[0][2] = 0;
        bp.piecesInventory.add(LFour);

        //done
        Piece IFour = new Piece("IFour",4,bp.playerColor);
        int[][] IFourPieceArr = new int[1][4];
        for (int i = 0; i < IFourPieceArr.length; i++) {
            IFourPieceArr[0][i] = 1;
        }
        bp.piecesInventory.add(IFour);

        //done
        Piece zFour = new Piece("zFour",4,bp.playerColor);
        int[][] zFourPieceArr = new int[2][3];
        for (int i = 0; i < zFourPieceArr.length; i++) {
            for(int j = 0; j < zFourPieceArr.length; j++) {
                zFourPieceArr[i][j] = 1;
            }
        }
        zFourPieceArr[0][0] = 0;
        zFourPieceArr[1][2] = 0;
        bp.piecesInventory.add(zFour);

        //done
        Piece f = new Piece("F",5,bp.playerColor);
        int[][] fPieceArr = new int[3][3];
        for (int i = 0; i < fPieceArr.length; i++) {
            for(int j = 0; j < fPieceArr.length; j++) {
                fPieceArr[i][j] = 1;
            }
        }
        fPieceArr[0][0] = 0;
        fPieceArr[0][2] = 0;
        fPieceArr[2][1] = 0;
        fPieceArr[2][2] = 0;
        bp.piecesInventory.add(f);

        Piece x = new Piece("X",5,bp.playerColor);
        int[][] xPieceArr = new int[3][3];
        for (int i = 0; i < xPieceArr.length; i++) {
            for(int j = 0; j < xPieceArr.length; j++) {
                xPieceArr[i][j] = 1;
            }
        }
        xPieceArr[0][0] = 0;
        xPieceArr[0][2] = 0;
        xPieceArr[2][0] = 0;
        xPieceArr[2][2] = 0;
        bp.piecesInventory.add(x);

        //done
        Piece p = new Piece("P",5,bp.playerColor);
        int[][] pPieceArr = new int[2][3];
        for (int i = 0; i < pPieceArr.length; i++) {
            for(int j = 0; j < pPieceArr.length; j++) {
                pPieceArr[i][j] = 1;
            }
        }
        pPieceArr[1][0] = 0;
        bp.piecesInventory.add(p);

        //done
        Piece w = new Piece("W",5,bp.playerColor);
        int[][] wPieceArr = new int[3][3];
        for (int i = 0; i < wPieceArr.length; i++) {
            for(int j = 0; j < wPieceArr.length; j++) {
                wPieceArr[i][j] = 1;
            }
        }
        wPieceArr[0][1] = 0;
        wPieceArr[0][2] = 0;
        wPieceArr[1][2] = 0;
        wPieceArr[2][0] = 0;
        bp.piecesInventory.add(w);

        //done
        Piece zFive = new Piece("ZFive",5,bp.playerColor);
        int[][] zFivePieceArr = new int[3][3];
        for (int i = 0; i < zFivePieceArr.length; i++) {
            for(int j = 0; j < zFivePieceArr.length; j++) {
                zFivePieceArr[i][j] = 1;
            }
        }
        zFivePieceArr[0][1] = 0;
        zFivePieceArr[0][2] = 0;
        zFivePieceArr[2][0] = 0;
        zFivePieceArr[2][1] = 0;
        bp.piecesInventory.add(zFive);

        //done
        Piece y = new Piece("Y",5,bp.playerColor);
        int[][] yPieceArr = new int[2][4];
        for (int i = 0; i < yPieceArr.length; i++) {
            for(int j = 0; j < yPieceArr.length; j++) {
                yPieceArr[i][j] = 1;
            }
        }
        yPieceArr[1][0] = 0;
        yPieceArr[1][2] = 0;
        yPieceArr[1][3] = 0;
        bp.piecesInventory.add(y);

        //done
        Piece LFive = new Piece("LFive",5,bp.playerColor);
        int[][] LFivePieceArr = new int[2][4];
        for (int i = 0; i < LFivePieceArr.length; i++) {
            for(int j = 0; j < LFivePieceArr.length; j++) {
                LFivePieceArr[i][j] = 1;
            }
        }
        LFivePieceArr[0][1] = 0;
        LFivePieceArr[0][2] = 0;
        LFivePieceArr[0][3] = 0;
        bp.piecesInventory.add(LFive);

        //done
        Piece u = new Piece("U",5,bp.playerColor);
        int[][] uPieceArr = new int[2][3];
        for (int i = 0; i < uPieceArr.length; i++) {
            for(int j = 0; j < uPieceArr.length; j++) {
                uPieceArr[i][j] = 1;
            }
        }
        uPieceArr[1][1] = 0;
        bp.piecesInventory.add(u);

        //done
        Piece tFive = new Piece("TFive",5,bp.playerColor);
        int[][] tFivePieceArr = new int[3][3];
        for (int i = 0; i < tFivePieceArr.length; i++) {
            for(int j = 0; j < tFivePieceArr.length; j++) {
                tFivePieceArr[i][j] = 1;
            }
        }
        tFivePieceArr[0][0] = 0;
        tFivePieceArr[0][2] = 0;
        tFivePieceArr[1][0] = 0;
        tFivePieceArr[1][2] = 0;
        bp.piecesInventory.add(tFive);

        //done
        Piece vFive = new Piece("VFive",5,bp.playerColor);
        int[][] vFivePieceArr = new int[3][3];
        for (int i = 0; i < vFivePieceArr.length; i++) {
            for(int j = 0; j < vFivePieceArr.length; j++) {
                vFivePieceArr[i][j] = 1;
            }
        }
        vFivePieceArr[0][1] = 0;
        vFivePieceArr[0][2] = 0;
        vFivePieceArr[1][1] = 0;
        vFivePieceArr[1][2] = 0;
        bp.piecesInventory.add(vFive);

        //done
        Piece n = new Piece("N",5,bp.playerColor);
        int[][] nPieceArr = new int[2][4];
        for (int i = 0; i < nPieceArr.length; i++) {
            for(int j = 0; j < nPieceArr.length; j++) {
                nPieceArr[i][j] = 1;
            }
        }
        nPieceArr[0][0] = 0;
        nPieceArr[1][2] = 0;
        nPieceArr[1][3] = 0;
        bp.piecesInventory.add(n);

        Piece IFive = new Piece("IFive",5,bp.playerColor);
        int[][] IFivePieceArr = new int[1][5];
        for (int i = 0; i < IFivePieceArr.length; i++) {
            IFivePieceArr[0][i] = 1;
        }
        bp.piecesInventory.add(IFive);
        return bp.piecesInventory;
    } //contains all the piece arrays

    public ArrayList<BlokusPlayer> getPlayers(){
        return players;
    }
    public void setPlayerTurn(BlokusPlayer p){
        this.playerToMove = p.playerID;
    }
}
/**
 External Citation
 Date: 26 February 2019
 Problem: Needed to see an example of a game state
 Resource:
 https://github.com/srvegdahl/TttGame
 Solution: I used the example code from this post to improve my understanding
 */

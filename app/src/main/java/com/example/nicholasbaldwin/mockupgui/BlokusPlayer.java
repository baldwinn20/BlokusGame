package com.example.nicholasbaldwin.mockupgui;

import java.util.ArrayList;

/**
 * <!-- class BlokusPlayer-->
 *
 * This class initializes a user or computer (AI) player that
 * does actions and plays the game. It also initializes each
 * players inventory by setting the default 21 pieces in an
 * ArrayList.
 *
 * @author <Justin Cao>
 * @author <Dylan Pascua>
 * @author <Nicholas Baldwin>
 */
public class BlokusPlayer {

   protected String playerName;
   protected int playerColor;
   protected int piecesRemaining;

    // As initialized in the BlokusGameState for testing purposes
    // (0)local human,(1)Dumb AI,(2)Smart AI,(3)Network player
   protected int playerType;
   protected int playerScore;
   protected int playerID;

   protected ArrayList<Piece> piecesInventory = new ArrayList<>();

   //Every player has 21 pieces to select from that have a total value of 89 points
   private static final int INITIAL_PIECES_REMAINING = 21;
   private static final int INITIAL_SCORE = 89;

    /**
     * Constructor for the BlokusPlayer class
     *
     * Initializes a player with default values and passed in
     * parameters, those of which will be chosen by the player
     * in the Game config GUI.
     *
     * @param  initName Name of the player
     * @param  initColor Chosen color of a player
     * @param initPlayerType Type of player (Human, AI, or Network)
     * @param initID ID used to determine the turn order
     */
    public BlokusPlayer(String initName,int initColor, int initPlayerType,
                        int initID) {
        //Store passed in parameters and current class constants into
        //instance variable to be modified throughout the game.
        playerName = initName;
        playerColor = initColor;
        piecesRemaining = INITIAL_PIECES_REMAINING;
        playerType = initPlayerType;
        playerScore = INITIAL_SCORE;
        playerID = initID;

        //Add the 21 default pieces into a player's inventory
        Piece one = new Piece("one", 1 , playerColor);
        piecesInventory.add(one);
        Piece two = new Piece("two",2, playerColor);
        piecesInventory.add(two);
        Piece vThree = new Piece("VThree", 3, playerColor);
        piecesInventory.add(vThree);
        Piece lThree = new Piece("LThree",3,playerColor);
        piecesInventory.add(lThree);
        Piece tFour = new Piece("TFour",4,playerColor);
        piecesInventory.add(tFour);
        Piece o = new Piece("O",4,playerColor);
        piecesInventory.add(o);
        Piece LFour = new Piece("LFour",4,playerColor);
        piecesInventory.add(LFour);
        Piece lFour = new Piece("lFour",4,playerColor);
        piecesInventory.add(lFour);
        Piece zFour = new Piece("zFour",4,playerColor);
        piecesInventory.add(zFour);
        Piece f = new Piece("F",5,playerColor);
        piecesInventory.add(f);
        Piece x = new Piece("X",5,playerColor);
        piecesInventory.add(x);
        Piece p = new Piece("P",5,playerColor);
        piecesInventory.add(p);
        Piece w = new Piece("W",5,playerColor);
        piecesInventory.add(w);
        Piece zFive = new Piece("ZFive",5,playerColor);
        piecesInventory.add(zFive);
        Piece y = new Piece("Y",5,playerColor);
        piecesInventory.add(y);
        Piece LFive = new Piece("LFive",5,playerColor);
        piecesInventory.add(LFive);
        Piece u = new Piece("U",5,playerColor);
        piecesInventory.add(u);
        Piece tFive = new Piece("TFive",5,playerColor);
        piecesInventory.add(tFive);
        Piece vFive = new Piece("VFive",5,playerColor);
        piecesInventory.add(vFive);
        Piece n = new Piece("N",5,playerColor);
        piecesInventory.add(n);
        Piece lFive = new Piece("lFive",5,playerColor);
        piecesInventory.add(lFive);

    }

    //Setters and Getters
    public String getPlayerName(){ return playerName;}
    public void setPlayerName(String name){this.playerName = name;}
    public int getPiecesRemaining(){ return piecesRemaining;}
    public void setPiecesRemaining(int piecesVal){this.piecesRemaining = piecesVal;}
    public int getPlayerColor(){return playerColor;}
    public void setPlayerColor(int colorVal){this.playerColor = colorVal;}
    public int getPlayerType(){return playerType;}
    public void setPlayerType(int playerType) { this.playerType = playerType;}
    public int getPlayerScore(){return  playerScore;}
    public void setPlayerScore(int scoreVal){this.playerScore = scoreVal;}
    public int getPlayerID(){return playerID;}
    public void setPlayerID(int id){playerID = id;}
    public ArrayList<Piece> getPiecesInventory(){return piecesInventory;}

}

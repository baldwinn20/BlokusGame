package com.example.nicholasbaldwin.mockupgui;

import android.graphics.Color;

import com.example.nicholasbaldwin.mockupgui.game.util.GamePlayer;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class BlokusTests {

    @Test
    public void getPlayerTurn() {
        BlokusGameState bgs = new BlokusGameState();
        bgs.setPlayerTurn(2);
        assertEquals(bgs.getPlayerTurn(), 3);
    }

    @Test
    public void rotate90() {
        Piece test = new Piece("smallT", 4, Color.BLUE);
        test.setPieceLayout(test.rotate90());

        assertEquals(test.pieceLayout[0][0], 1 );
        assertEquals(test.pieceLayout[0][1], 1 );
        assertEquals(test.pieceLayout[0][2], 1 );
        assertEquals(test.pieceLayout[1][1], 1 );
    }

    @Test
    public void flip() {
        Piece test = new Piece("smallT", 4, Color.BLUE);
        test.setPieceLayout(test.flip());

        assertEquals(test.pieceLayout[0][0], 1 );
        assertEquals(test.pieceLayout[1][0], 1 );
        assertEquals(test.pieceLayout[2][0], 1 );
        assertEquals(test.pieceLayout[1][1], 1 );
    }

    @Test
    public void placePiece() {
        BlokusGameState bgs = new BlokusGameState();
        //this places the one piece
        bgs.placePiece(0,0, bgs.getAllPieceInventory().get(0).get(0));

        assertEquals(bgs.getBoard()[0][0], 0);

    }


    @Test
    public void initializeInventories() {
        BlokusGameState bgs = new BlokusGameState();

        //checks to see if the very last piece in the inventory is there
        assertEquals(bgs.getAllPieceInventory().get(0).get(20).getName(), "corner" );
    }
    @Test
    public void removeInventory(){
        BlokusGameState bgs = new BlokusGameState();

        bgs.removePiece(bgs.getAllPieceInventory().get(0).get(20), 0);

        assertEquals(bgs.getAllPieceInventory().get(0).size(), 20);
    }

    @Test
    public void checkForValidMove(){
        BlokusHumanPlayer bhp = new BlokusHumanPlayer("testboi");
        bhp.test();
    }
}
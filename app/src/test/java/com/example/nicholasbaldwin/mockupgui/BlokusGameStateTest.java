package com.example.nicholasbaldwin.mockupgui;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class BlokusGameStateTest {

    @Test
    public void isTurn() {
        BlokusGameState bgs = new BlokusGameState();
        ArrayList<BlokusPlayer> players = bgs.getPlayers();
        bgs.setPlayerTurn(players.get(2));
        assertTrue("Expected it to be player 2's turn", bgs.isTurn(players.get(2)));
    }

    @Test
    public void rotate90() {
        BlokusGameState bgs = new BlokusGameState();
        ArrayList<BlokusPlayer> players = bgs.getPlayers();
        bgs.rotate90(players.get(0).playerID, 0);
        int val = players.get(0).getPiecesInventory().get(0).getOrientationVal();
        assertEquals(val, 1);
    }

    @Test
    public void flip() {
        BlokusGameState bgs = new BlokusGameState();
        ArrayList<BlokusPlayer> players = bgs.getPlayers();
        bgs.flip(players.get(0).playerID, 0);
        int val = players.get(0).getPiecesInventory().get(0).getOrientationVal();
        assertEquals(val, 2);
    }

    @Test
    public void placePiece() {
        BlokusGameState bgs = new BlokusGameState();
        ArrayList<BlokusPlayer> players = bgs.getPlayers();
        bgs.placePiece(players.get(0).playerID, 0, 0, 0);
        assertTrue(players.get(0).getPiecesInventory().get(0).isOnBoard);
        assertEquals(players.get(0).getPiecesInventory().get(0).getXPosition(), 0);
        assertEquals(players.get(0).getPiecesInventory().get(0).getYPosition(), 0);
    }

    @Test
    public void setName() {
        BlokusGameState bgs = new BlokusGameState();
        ArrayList<BlokusPlayer> players = bgs.getPlayers();
        bgs.setName("Dylan", players.get(0).playerID);
        assertEquals("Expect to be Dylan ", players.get(0).playerName, "Dylan");
    }

    @Test
    public void initializeInventories() {
        BlokusGameState bgs = new BlokusGameState();
        ArrayList<BlokusPlayer> players = bgs.getPlayers();
        bgs.initializeInventories(players.get(0));
        Piece one = new Piece("one", 1 ,players.get(0).playerColor);
        assertEquals(players.get(0).getPiecesInventory().get(0).getName(), one.getName());
    }
}
package com.example.nicholasbaldwin.mockupgui;

import android.view.View;

import com.example.nicholasbaldwin.mockupgui.game.infoMsg.GameInfo;
import com.example.nicholasbaldwin.mockupgui.game.util.Game;
import com.example.nicholasbaldwin.mockupgui.game.util.GameHumanPlayer;
import com.example.nicholasbaldwin.mockupgui.game.util.GameMainActivity;

import java.util.ArrayList;

public class BlokusHumanPlayer extends GameHumanPlayer {
    //All the instance variables
    protected Game game;
    private int playerColor;
    private int piecesRemaining;
    private int playerType = 0; // human players are all of type 0
    private int playerScore;
    private int stage;
    private int playerID;
    private Piece currentPiece;
    //TODO Remove instnace varprivate ArrayList<Piece> piecesInventory;
    public int INITIAL_PIECES_rEMAINING = 21;
    public int INITIAL_SCORE = 89;

    @Override
    public View getTopView() {
        return null;
    }

    @Override
    public void receiveInfo(GameInfo info) {

    }

    @Override
    public void setAsGui(GameMainActivity activity) {
        activity.setContentView(R.layout.activity_main);
    }

    @Override
    public void sendInfo(GameInfo info) {

    }

    public BlokusHumanPlayer(String initName, int initColor, int initID){
        super(initName);
        playerColor = initColor;
        playerID = initID;
        piecesRemaining = INITIAL_PIECES_rEMAINING;
        playerScore = INITIAL_SCORE;
    }
}

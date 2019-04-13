package com.example.nicholasbaldwin.mockupgui.game;

import com.example.nicholasbaldwin.mockupgui.PlacePiece;
import com.example.nicholasbaldwin.mockupgui.game.util.Game;

public class GameOverCheck extends Thread {

    private Game game;
    private PlacePiece pp;

    public GameOverCheck(Game g, PlacePiece pp){
        this.game = g;
        this.pp = pp;
    }

    @Override
    public void run(){
        game.sendAction(pp);
    }
}

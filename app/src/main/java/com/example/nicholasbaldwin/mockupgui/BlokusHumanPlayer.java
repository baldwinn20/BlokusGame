package com.example.nicholasbaldwin.mockupgui;

import android.app.Activity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.nicholasbaldwin.mockupgui.game.infoMsg.GameInfo;
import com.example.nicholasbaldwin.mockupgui.game.infoMsg.IllegalMoveInfo;
import com.example.nicholasbaldwin.mockupgui.game.infoMsg.NotYourTurnInfo;
import com.example.nicholasbaldwin.mockupgui.game.util.Game;
import com.example.nicholasbaldwin.mockupgui.game.util.GameHumanPlayer;
import com.example.nicholasbaldwin.mockupgui.game.util.GameMainActivity;

import java.util.ArrayList;

public class BlokusHumanPlayer extends GameHumanPlayer implements View.OnTouchListener{
    //All the instance variables

    // the activity under which we're running
    private Activity myActivity = null;

    // the game's state
    BlokusGameState state = null;

    //TODO Insert Master GUI object here
    private BlokusBoard surfaceView = null;

    protected Game game;
    private int playerColor;
    private int piecesRemaining;
    private int playerType = 0; // human players are all of type 0
    private int playerScore;
    private int stage;
    private int playerID;
    private Piece currentPiece;
    public int INITIAL_PIECES_REMAINING = 21;
    public int INITIAL_SCORE = 89;


    /**
     * constructor
     *
     * @param initName
     * 		the player's name
     * @param initColor
     *      the player color
     * @param initID
     *      the index of the player to track turns
     */
    public BlokusHumanPlayer(String initName, int initColor, int initID){
        super(initName);
        playerColor = initColor;
        playerID = initID;
        piecesRemaining = INITIAL_PIECES_REMAINING;
        playerScore = INITIAL_SCORE;
    }

    @Override
    public View getTopView() {
        return null;
    }

    @Override
    public void setAsGui(GameMainActivity activity) {
        // remember the activity
        myActivity = activity;

        //TODO replace xml layout with layout id
        activity.setContentView(R.layout.activity_main);

        Log.i("set listener","OnTouch");
        surfaceView.setOnTouchListener(this);
    }

    @Override
    public void sendInfo(GameInfo info) {

    }

    @Override
    public void receiveInfo(GameInfo info) {
        if (surfaceView == null) return;

        if (info instanceof IllegalMoveInfo || info instanceof NotYourTurnInfo) {
            //TODO place holder for handling
            return;
        }
        else if (!(info instanceof BlokusGameState))
            // if we do not have a TTTState, ignore
            return;
        else {
            state = (BlokusGameState)info;
            //TODO make setState method in Master GUI class
            surfaceView.setState((BlokusGameState)info);
            surfaceView.invalidate();
            Log.i("human player", "receiving");
        }
    }

    public int getPlayerColor() {
        return playerColor;
    }

    public Piece getCurrentPiece() {
        return currentPiece;
    }

    public void setCurrentPiece(Piece currentPiece) {
        this.currentPiece = currentPiece;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        // get the x and y coordinates of the touch-location;
        // convert them to square coordinates (where both
        // values are in the range 0..2)
        int x = (int) event.getX();
        int y = (int) event.getY();

        //TODO need to map pixel touch to an actual tile on the board
        game.sendAction(new PlacePiece(this, x, y, currentPiece));
        surfaceView.invalidate();
        return false;
    }
}

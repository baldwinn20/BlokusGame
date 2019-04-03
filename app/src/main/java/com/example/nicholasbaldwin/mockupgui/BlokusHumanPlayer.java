package com.example.nicholasbaldwin.mockupgui;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

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
    private TextView messageBox = null;

    private int playerColor;
    private int piecesRemaining;
    private int playerType = 0; // human players are all of type 0
    private int playerScore;
    private int stage;
    private int playerID;
    private Piece currentPiece;
    //TODO Remove instnace varprivate ArrayList<Piece> piecesInventory;
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
        surfaceView = myActivity.findViewById(R.id.blokusBoard);
        messageBox = myActivity.findViewById(R.id.messageTV);
        Log.i("set listener","OnTouch");
        surfaceView.setOnTouchListener(this);
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
            surfaceView.setState(state);
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

        Point p = surfaceView.mapPixelToTile(x,y);
        if (p == null){
            //Makes a message to the widget stating that the player
            // is touching out of bounds
            messageBox.setText("Invalid Touch, out of bounds.\n");
            return false;
        }
        else {
            //game.sendAction(new PlacePiece(this, x, y, currentPiece));
            messageBox.setText("Placing Piece.\n");
//            BlokusGameState testState = new BlokusGameState();
//            surfaceView.setState(testState);
            Piece testP = new Piece("one", 1, Color.RED);
//            surfaceView.state.placePiece(p.x,p.y,testP);
            setCurrentPiece(testP);
            game.sendAction(new PlacePiece(this,p.x,p.y, currentPiece));
            surfaceView.invalidate();
            return true;
        }
    }
}

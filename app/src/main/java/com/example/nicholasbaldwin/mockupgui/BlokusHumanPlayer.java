package com.example.nicholasbaldwin.mockupgui;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.nicholasbaldwin.mockupgui.game.infoMsg.GameInfo;
import com.example.nicholasbaldwin.mockupgui.game.infoMsg.IllegalMoveInfo;
import com.example.nicholasbaldwin.mockupgui.game.infoMsg.NotYourTurnInfo;
import com.example.nicholasbaldwin.mockupgui.game.util.Game;
import com.example.nicholasbaldwin.mockupgui.game.util.GameHumanPlayer;
import com.example.nicholasbaldwin.mockupgui.game.util.GameMainActivity;

import java.util.ArrayList;

public class BlokusHumanPlayer extends GameHumanPlayer implements
        View.OnTouchListener, ScrollView.OnClickListener {
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
    private TextView redScore, blueScore, greenScore, yellowScore;
    private TextView redPR, bluePR, greenPR, yellowPR;
    private ScrollView scrollView;
    private ImageButton oneButton, twoButton, sButton, threeButton, smallTButton,
            fourButton, fourLButton, fiveButton, fiveLButton, nButton, yButton,
            v3Button, cubeButton, cButton, bButton, zButton, mButton, xButton,
            fButton, bigTButton, cornerButton, imageButton;

    //TODO Remove instance var private ArrayList<Piece> piecesInventory;
    public int INITIAL_PIECES_REMAINING = 21;
    public int INITIAL_SCORE = 89;


    /**
     * constructor
     *
     * @param initName  the player's name
     * @param initColor the player color
     * @param initID    the index of the player to track turns
     */
    public BlokusHumanPlayer(String initName, int initColor, int initID) {
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

        //all the scores and pieces remaining
        redScore = myActivity.findViewById(R.id.redScoreTV);
        redScore.setText("0");
        blueScore = myActivity.findViewById(R.id.blueScoreTV);
        blueScore.setText("0");
        greenScore = myActivity.findViewById(R.id.greenScoreTV);
        greenScore.setText("0");
        yellowScore = myActivity.findViewById(R.id.yellowScoreTV);
        yellowScore.setText("0");
        redPR = myActivity.findViewById(R.id.redPiecesCount);
        bluePR = myActivity.findViewById(R.id.bluePiecesCount);
        greenPR = myActivity.findViewById(R.id.greenPiecesCount);
        yellowPR = myActivity.findViewById(R.id.yellowPiecesCount);
        redPR.setText("21");
        bluePR.setText("21");
        greenPR.setText("21");
        yellowPR.setText("21");

        Log.i("set listener", "OnTouch");
        surfaceView.setOnTouchListener(this);

        scrollView = myActivity.findViewById(R.id.piecesScrollView);
        scrollView.setOnClickListener(this);
        //imageButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        oneButton = myActivity.findViewById(R.id.oneButton);
        oneButton.setOnClickListener(this);
        twoButton = myActivity.findViewById(R.id.twoButton);
        twoButton.setOnClickListener(this);
        sButton = myActivity.findViewById(R.id.sButton);
        sButton.setOnClickListener(this);
        threeButton = myActivity.findViewById(R.id.threeButton);
        threeButton.setOnClickListener(this);
        smallTButton = myActivity.findViewById(R.id.smallTButton);
        smallTButton.setOnClickListener(this);
        fourButton = myActivity.findViewById(R.id.fourButton);
        fourButton.setOnClickListener(this);
        fourLButton = myActivity.findViewById(R.id.fourLButton);
        fourLButton.setOnClickListener(this);
        fiveButton = myActivity.findViewById(R.id.fiveButton);
        fiveButton.setOnClickListener(this);
        fiveLButton = myActivity.findViewById(R.id.fiveLButton);
        fiveLButton.setOnClickListener(this);
        nButton = myActivity.findViewById(R.id.nButton);
        nButton.setOnClickListener(this);
        yButton = myActivity.findViewById(R.id.yButton);
        yButton.setOnClickListener(this);
        v3Button = myActivity.findViewById(R.id.v3Button);
        v3Button.setOnClickListener(this);
        cubeButton = myActivity.findViewById(R.id.cubeButton);
        cubeButton.setOnClickListener(this);
        cButton = myActivity.findViewById(R.id.cButton);
        cButton.setOnClickListener(this);
        bButton = myActivity.findViewById(R.id.bButton);
        bButton.setOnClickListener(this);
        zButton = myActivity.findViewById(R.id.zButton);
        zButton.setOnClickListener(this);
        mButton = myActivity.findViewById(R.id.mButton);
        mButton.setOnClickListener(this);
        xButton = myActivity.findViewById(R.id.xButton);
        xButton.setOnClickListener(this);
        fButton = myActivity.findViewById(R.id.fButton);
        fButton.setOnClickListener(this);
        bigTButton = myActivity.findViewById(R.id.bigTButton);
        bigTButton.setOnClickListener(this);
        cornerButton = myActivity.findViewById(R.id.cornerButton);
        cornerButton.setOnClickListener(this);


        if (imageButton == oneButton) {
            currentPiece.setName("one");
        } else if (imageButton == twoButton) {
            currentPiece.setName("two");
        } else if (imageButton == myActivity.findViewById(R.id.sButton)) {
            currentPiece.setName("s");
        } else if (imageButton == myActivity.findViewById(R.id.threeButton)) {
            currentPiece.setName("three");
        } else if (imageButton == myActivity.findViewById(R.id.smallTButton)) {
            currentPiece.setName("smallT");
        } else if (imageButton == myActivity.findViewById(R.id.fourButton)) {
            currentPiece.setName("four");
        } else if (imageButton == myActivity.findViewById(R.id.fourLButton)) {
            currentPiece.setName("fourL");
        } else if (imageButton == myActivity.findViewById(R.id.fiveButton)) {
            currentPiece.setName("five");
        } else if (imageButton == myActivity.findViewById(R.id.fiveLButton)) {
            currentPiece.setName("fiveL");
        } else if (imageButton == myActivity.findViewById(R.id.nButton)) {
            currentPiece.setName("N");
        } else if (imageButton == myActivity.findViewById(R.id.yButton)) {
            currentPiece.setName("Y");
        } else if (imageButton == myActivity.findViewById(R.id.v3Button)) {
            currentPiece.setName("v3");
        } else if (imageButton == myActivity.findViewById(R.id.cubeButton)) {
            currentPiece.setName("cube");
        } else if (imageButton == myActivity.findViewById(R.id.cButton)) {
            currentPiece.setName("C");
        } else if (imageButton == myActivity.findViewById(R.id.bButton)) {
            currentPiece.setName("B");
        } else if (imageButton == myActivity.findViewById(R.id.zButton)) {
            currentPiece.setName("Z");
        } else if (imageButton == myActivity.findViewById(R.id.mButton)) {
            currentPiece.setName("M");
        } else if (imageButton == myActivity.findViewById(R.id.xButton)) {
            currentPiece.setName("X");
        } else if (imageButton == myActivity.findViewById(R.id.fButton)) {
            currentPiece.setName("F");
        } else if (imageButton == myActivity.findViewById(R.id.bigTButton)) {
            currentPiece.setName("bigT");
        } else if (imageButton == myActivity.findViewById(R.id.cornerButton)) {
            currentPiece.setName("corner");
        }
    }

    @Override
    public void receiveInfo(GameInfo info) {
        if (surfaceView == null) return;

        if (info instanceof IllegalMoveInfo || info instanceof NotYourTurnInfo) {
            //TODO place holder for handling
            return;
        } else if (!(info instanceof BlokusGameState))
            // if we do not have a TTTState, ignore
            return;
        else {
            state = (BlokusGameState) info;
            //TODO make setState method in Master GUI class
            surfaceView.setState(state);
            updatePlayerScores();
            updatePlayerPiecesRemaining();
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

        Point p = surfaceView.mapPixelToTile(x, y);
        if (p == null) {
            //Makes a message to the widget stating that the player
            // is touching out of bounds
            messageBox.setText("Invalid Touch, out of bounds.\n");
            return false;
        } else {
            //game.sendAction(new PlacePiece(this, x, y, currentPiece));
            messageBox.setText("Placing Piece.\n");
//            BlokusGameState testState = new BlokusGameState();
//            surfaceView.setState(testState);
            Piece testP = new Piece("N", 1, Color.RED);
//            surfaceView.state.placePiece(p.x,p.y,testP);
            setCurrentPiece(testP);
            game.sendAction(new PlacePiece(this, p.x, p.y, currentPiece));
            surfaceView.invalidate();
            return true;
        }
    }


    private void updatePlayerScores() {
        redScore.setText(state.getAllPlayerScores()[0] + "");
        blueScore.setText(state.getAllPlayerScores()[1] + "");
        greenScore.setText(state.getAllPlayerScores()[2] + "");
        yellowScore.setText(state.getAllPlayerScores()[3] + "");
    }

    private void updatePlayerPiecesRemaining() {
        redPR.setText(state.getAllPiecesRemaining()[0] + "");
        bluePR.setText(state.getAllPiecesRemaining()[1] + "");
        greenPR.setText(state.getAllPiecesRemaining()[2] + "");
        yellowPR.setText(state.getAllPiecesRemaining()[3] + "");
    }


}

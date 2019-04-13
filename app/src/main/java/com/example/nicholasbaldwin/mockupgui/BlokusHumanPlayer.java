package com.example.nicholasbaldwin.mockupgui;

import android.app.Activity;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.nicholasbaldwin.mockupgui.game.GameOverCheck;
import com.example.nicholasbaldwin.mockupgui.game.infoMsg.GameInfo;
import com.example.nicholasbaldwin.mockupgui.game.infoMsg.IllegalMoveInfo;
import com.example.nicholasbaldwin.mockupgui.game.infoMsg.NotYourTurnInfo;
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
    //TODO remove this variable?
    private int playerID;
    //    private Piece currentPiece;
    private TextView redScore, blueScore, greenScore, yellowScore;
    private TextView redPR, bluePR, greenPR, yellowPR;
    private ScrollView scrollView;
    private ImageButton oneButton, twoButton, sButton, threeButton, smallTButton,
            fourButton, fourLButton, fiveButton, fiveLButton, nButton, yButton,
            v3Button, cubeButton, cButton, bButton, zButton, mButton, xButton,
            fButton, bigTButton, cornerButton, imageButton;
    private Button placePieceButton, rotateButton, flipButton, helpButton;
    //TODO Remove instance var private ArrayList<Piece> piecesInventory;
    private PlacePiece pp = null;
    private ArrayList<Piece> currentInventory = null;
    ImageButton currentPieceButton = null;


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
        activity.setContentView(R.layout.red_player_gui);
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

        surfaceView.setOnTouchListener(this);

        scrollView = myActivity.findViewById(R.id.piecesScrollView);
        scrollView.setOnClickListener(this);

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
        //imageButton.setOnClickListener(this);

        placePieceButton = myActivity.findViewById((R.id.placePieceButton));
        placePieceButton.setOnClickListener(this);
        rotateButton = myActivity.findViewById(R.id.rotateButton);
        rotateButton.setOnClickListener(this);
        flipButton = myActivity.findViewById(R.id.flipButton);
        flipButton.setOnClickListener(this);
        helpButton = myActivity.findViewById(R.id.helpButton);
        helpButton.setOnClickListener(this);

        this.state = new BlokusGameState();
        currentInventory = state.getAllPieceInventory().get(playerID);

        placePieceButton.setEnabled(false);
    }

    @Override
    public void onClick(View v) {
        if (v == oneButton) {
            surfaceView.setCurrentPiece(currentInventory.get(0));
            currentPieceButton = oneButton;
        } else if (v == twoButton) {
            surfaceView.setCurrentPiece(currentInventory.get(1));
            currentPieceButton = twoButton;
        } else if (v == sButton) {
            surfaceView.setCurrentPiece(currentInventory.get(2));
            currentPieceButton = sButton;
        } else if (v == threeButton) {
            surfaceView.setCurrentPiece(currentInventory.get(3));
            currentPieceButton = threeButton;
        } else if (v == smallTButton) {
            surfaceView.setCurrentPiece(currentInventory.get(4));
            currentPieceButton = smallTButton;
        } else if (v == fourButton) {
            surfaceView.setCurrentPiece(currentInventory.get(5));
            currentPieceButton = fourButton;
        } else if (v == fourLButton) {
            surfaceView.setCurrentPiece(currentInventory.get(6));
            currentPieceButton = fourLButton;
        } else if (v == fiveButton) {
            surfaceView.setCurrentPiece(currentInventory.get(7));
            currentPieceButton = fiveButton;
        } else if (v == fiveLButton) {
            surfaceView.setCurrentPiece(currentInventory.get(8));
            currentPieceButton = fiveLButton;
        } else if (v == nButton) {
            surfaceView.setCurrentPiece(currentInventory.get(9));
            currentPieceButton = nButton;
        } else if (v == yButton) {
            surfaceView.setCurrentPiece(currentInventory.get(10));
            currentPieceButton = yButton;
        } else if (v == v3Button) {
            surfaceView.setCurrentPiece(currentInventory.get(11));
            currentPieceButton = v3Button;
        } else if (v == cubeButton) {
            surfaceView.setCurrentPiece(currentInventory.get(12));
            currentPieceButton = cubeButton;
        } else if (v == cButton) {
            surfaceView.setCurrentPiece(currentInventory.get(13));
            currentPieceButton = cButton;
        } else if (v == bButton) {
            surfaceView.setCurrentPiece(currentInventory.get(14));
            currentPieceButton = bButton;
        } else if (v == zButton) {
            surfaceView.setCurrentPiece(currentInventory.get(15));
            currentPieceButton = zButton;
        } else if (v == mButton) {
            surfaceView.setCurrentPiece(currentInventory.get(16));
            currentPieceButton = mButton;
        } else if (v == xButton) {
            surfaceView.setCurrentPiece(currentInventory.get(17));
            currentPieceButton = xButton;
        } else if (v == fButton) {
            surfaceView.setCurrentPiece(currentInventory.get(18));
            currentPieceButton = fButton;
        } else if (v == bigTButton) {
            surfaceView.setCurrentPiece(currentInventory.get(19));
            currentPieceButton = bigTButton;
        } else if (v == cornerButton) {
            surfaceView.setCurrentPiece(currentInventory.get(20));
            currentPieceButton = cornerButton;
        }

        //TODO is this needed?
        if (surfaceView.getCurrentPiece() != null) {
            surfaceView.invalidate();
        }


        if (v == placePieceButton) {
            //This makes the button disappear when pressed
            //Starts a separate thread to determine whether or not the game can be ended after this turn
            GameOverCheck gOverChecker = new GameOverCheck(game, pp);
            gOverChecker.start();
            currentPieceButton.setVisibility(View.GONE);
            surfaceView.setCurrentPiece(null);
            placePieceButton.setEnabled(false);
        }
        //TODO there is still a minor bug where it doesn't check if it is a valid move when pressing rotate or flip
        else if (v == flipButton && surfaceView.getCurrentPiece() != null) {
            Piece p = surfaceView.getCurrentPiece();
            p.setPieceLayout(p.flip());
            //checks to see if you can place a piece after you flipped the piece
            if(pp != null) {
                if (pp.checkForValidMove(playerID)) {
                    placePieceButton.setEnabled(true);
                } else if (!pp.checkForValidMove(playerID)) {
                    placePieceButton.setEnabled(false);
                }
            }

        }
        else if (v == rotateButton && surfaceView.getCurrentPiece() != null) {
            Piece p = surfaceView.getCurrentPiece();
            p.setPieceLayout(p.rotate90());
            //checks to see if you can place a piece after you flipped the piece
            if(pp != null) {
                if (pp.checkForValidMove(playerID)) {
                    placePieceButton.setEnabled(true);
                } else if (!pp.checkForValidMove(playerID)) {
                    placePieceButton.setEnabled(false);
                }
            }
        } else if (v == helpButton) {
            //TODO someone needs to put the rules down here or something
        }

        //this draws a preview on the middle of the board
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
            currentInventory = state.getAllPieceInventory().get(playerID);
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


    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if (surfaceView.getCurrentPiece() == null) {
            messageBox.setText("Invalid Move: Select a Piece.");
            return false;
        }
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
            //if the player makes a dragging motion the board will draw the piece
            // were the persons finger is
            messageBox.setText("Moving Piece\n");
            surfaceView.getCurrentPiece().setxPosition(p.x);
            surfaceView.getCurrentPiece().setyPosition(p.y);
            pp = new PlacePiece(this, surfaceView.getCurrentPiece().getXPosition(),
                    surfaceView.getCurrentPiece().getYPosition(), surfaceView.getCurrentPiece());
            pp.setBoard(state.getBoard());
            //this checks to see of the current piece is a valid move
            if (pp.checkForValidMove(playerID)) {
                placePieceButton.setEnabled(true);
            } else if (!pp.checkForValidMove(playerID)) {
                placePieceButton.setEnabled(false);
            }
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
    /**
     External Citation:
     Date: 8 April 2019
     Problem: I didn't know how to make buttons disappear when used
     Source:https://stackoverflow.com/questions/14868349/how-to-disappear-button-in-android
     */

}

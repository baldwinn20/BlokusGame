package com.example.nicholasbaldwin.mockupgui;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.nicholasbaldwin.mockupgui.game.GiveUp;
import com.example.nicholasbaldwin.mockupgui.game.infoMsg.GameInfo;
import com.example.nicholasbaldwin.mockupgui.game.infoMsg.IllegalMoveInfo;
import com.example.nicholasbaldwin.mockupgui.game.infoMsg.NotYourTurnInfo;
import com.example.nicholasbaldwin.mockupgui.game.util.GameHumanPlayer;
import com.example.nicholasbaldwin.mockupgui.game.util.GameMainActivity;
import com.example.nicholasbaldwin.mockupgui.game.util.MessageBox;

import java.util.ArrayList;

/**
 * <!-- class BlokusHumanPlayer -->
 * <p>
 * This class allows the human player to select, place, and rotate
 * pieces on the board.
 *
 * @author <Nicholas Baldwin, Justin Cao, Dylan Pascua>
 * @version <Spring 2019>
 */

public class BlokusHumanPlayer extends GameHumanPlayer implements
        View.OnTouchListener, ScrollView.OnClickListener {
    //All the instance variables

    // the activity under which we're running
    private Activity myActivity = null;

    // the game's state
    BlokusGameState state = null;

    //Drawing surface for the board
    private BlokusBoard surfaceView = null;

    //Widgets that correspond to the message box, player scores,
    //player inventories & pieces, and action buttons
    private TextView messageBox = null;

    private TextView redScore, blueScore, greenScore, yellowScore;
    private TextView redPR, bluePR, greenPR, yellowPR;
    private ScrollView scrollView;
    private ImageButton oneButton, twoButton, sButton, threeButton, smallTButton,
            fourButton, fourLButton, fiveButton, fiveLButton, nButton, yButton,
            v3Button, cubeButton, cButton, bButton, zButton, mButton, xButton,
            fButton, bigTButton, cornerButton;
    private Button placePieceButton, rotateButton, flipButton, helpButton, quitButton;

    //Game action that will be sent when the player tries to flip, rotate or place a piece
    private PlacePiece pp = null;
    //The player's piece inventory after the game state has changed
    private ArrayList<Piece> currentInventory = null;
    //Represents the currently selected piece from the scroll view
    ImageButton currentPieceButton = null;

    //used for sound effects
    MediaPlayer buttonSound;


    /**
     * constructor
     *
     * @param initName the player's name
     */
    public BlokusHumanPlayer(String initName) {
        super(initName);
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


        placePieceButton = myActivity.findViewById((R.id.placePieceButton));
        placePieceButton.setOnClickListener(this);
        rotateButton = myActivity.findViewById(R.id.rotateButton);
        rotateButton.setOnClickListener(this);
        flipButton = myActivity.findViewById(R.id.flipButton);
        flipButton.setOnClickListener(this);
        helpButton = myActivity.findViewById(R.id.helpButton);
        helpButton.setOnClickListener(this);
        quitButton = myActivity.findViewById(R.id.quitButton);
        quitButton.setOnClickListener(this);

        this.state = new BlokusGameState();
        currentInventory = state.getAllPieceInventory().get(playerNum);

        placePieceButton.setEnabled(false);

        //sets up all the sounds
        buttonSound = MediaPlayer.create(myActivity.getApplicationContext(),R.raw.button_sound);
        buttonSound.setLooping(false);
        /**
         External Citation:
         Date: 22 April 2019
         Problem: I wanted to know how to play sounds
         Source: https://www.youtube.com/watch?v=9oj4f8721LM
         */
    }

    @Override
    public void onClick(View v) {
        if(state.getPlayerTurn() != playerNum){
            return;
        }
        /**
         External Citation:
         Date: 20 April 2019
         Problem: I didn't know how to pass in a player object into the enclosed class
         Solution: Used the example code from the post
         Source: https://stackoverflow.com/questions/5530256/java-class-this
         */
        if( v == quitButton){
            buttonSound.start();
            String quitQuestion =
                    "Do you really want to give up?";
            String posLabel =
                    "Yes";
            String negLabel =
                    "No";
            MessageBox.popUpChoice(quitQuestion, posLabel, negLabel,
                    new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface di, int val) {
                            GiveUp gu = new GiveUp(BlokusHumanPlayer.this);
                            game.sendAction(gu);
                        }},
                    null,
                    myActivity);

        }
        if (v == oneButton) {
            surfaceView.setCurrentPiece(this.findPiece("one"));
            currentPieceButton = oneButton;
        } else if (v == twoButton) {
            surfaceView.setCurrentPiece(this.findPiece("two"));
            currentPieceButton = twoButton;
        } else if (v == sButton) {
            surfaceView.setCurrentPiece(this.findPiece("S"));
            currentPieceButton = sButton;
        } else if (v == threeButton) {
            surfaceView.setCurrentPiece(this.findPiece("three"));
            currentPieceButton = threeButton;
        } else if (v == smallTButton) {
            surfaceView.setCurrentPiece(this.findPiece("smallT"));
            currentPieceButton = smallTButton;
        } else if (v == fourButton) {
            surfaceView.setCurrentPiece(this.findPiece("four"));
            currentPieceButton = fourButton;
        } else if (v == fourLButton) {
            surfaceView.setCurrentPiece(this.findPiece("fourL"));
            currentPieceButton = fourLButton;
        } else if (v == fiveButton) {
            surfaceView.setCurrentPiece(this.findPiece("five"));
            currentPieceButton = fiveButton;
        } else if (v == fiveLButton) {
            surfaceView.setCurrentPiece(this.findPiece("fiveL"));
            currentPieceButton = fiveLButton;
        } else if (v == nButton) {
            surfaceView.setCurrentPiece(this.findPiece("N"));
            currentPieceButton = nButton;
        } else if (v == yButton) {
            surfaceView.setCurrentPiece(this.findPiece("Y"));
            currentPieceButton = yButton;
        } else if (v == v3Button) {
            surfaceView.setCurrentPiece(this.findPiece("v3"));
            currentPieceButton = v3Button;
        } else if (v == cubeButton) {
            surfaceView.setCurrentPiece(this.findPiece("cube"));
            currentPieceButton = cubeButton;
        } else if (v == cButton) {
            surfaceView.setCurrentPiece(this.findPiece("C"));
            currentPieceButton = cButton;
        } else if (v == bButton) {
            surfaceView.setCurrentPiece(this.findPiece("B"));
            currentPieceButton = bButton;
        } else if (v == zButton) {
            surfaceView.setCurrentPiece(this.findPiece("Z"));
            currentPieceButton = zButton;
        } else if (v == mButton) {
            surfaceView.setCurrentPiece(this.findPiece("M"));
            currentPieceButton = mButton;
        } else if (v == xButton) {
            surfaceView.setCurrentPiece(this.findPiece("X"));
            currentPieceButton = xButton;
        } else if (v == fButton) {
            surfaceView.setCurrentPiece(this.findPiece("F"));
            currentPieceButton = fButton;
        } else if (v == bigTButton) {
            surfaceView.setCurrentPiece(this.findPiece("bigT"));
            currentPieceButton = bigTButton;
        } else if (v == cornerButton) {
            surfaceView.setCurrentPiece(this.findPiece("corner"));
            currentPieceButton = cornerButton;
        }


        //Updates GUI to reflect user choosing new piece or
        //user rotating or flipping a piece
        if (surfaceView.getCurrentPiece() != null) {
            surfaceView.invalidate();
        }

        if (v == placePieceButton) {
            buttonSound.start();
            //This makes the button disappear when pressed
            game.sendAction(pp);
            currentPieceButton.setVisibility(View.GONE);
            surfaceView.setCurrentPiece(null);
            placePieceButton.setEnabled(false);
        }
        //TODO there is still a minor bug where it doesn't check if it is a valid move when pressing rotate or flip
        else if (v == flipButton && surfaceView.getCurrentPiece() != null) {
            buttonSound.start();
            Piece p = surfaceView.getCurrentPiece();
            p.setPieceLayout(p.flip());
            //checks to see if you can place a piece after you flipped the piece
            if (pp != null) {
                pp.setPieceLayout(p.getPieceLayout());
                if (pp.checkForValidMove(playerNum)) {
                    placePieceButton.setEnabled(true);
                } else if (!pp.checkForValidMove(playerNum)) {
                    placePieceButton.setEnabled(false);
                }
            }

        } else if (v == rotateButton && surfaceView.getCurrentPiece() != null) {
            buttonSound.start();
            Piece p = surfaceView.getCurrentPiece();
            p.setPieceLayout(p.rotate90());
            //checks to see if you can place a piece after you flipped the piece
            if (pp != null) {
                pp.setPieceLayout(p.getPieceLayout());
                if (pp.checkForValidMove(playerNum)) {
                    placePieceButton.setEnabled(true);
                } else if (!pp.checkForValidMove(playerNum)) {
                    placePieceButton.setEnabled(false);
                }
            }

        } else if (v == helpButton) {
            buttonSound.start();
            //TODO someone needs to put the rules down here or something
            /**
             External Citation:
             Date: 19 April 2019
             Problem: Not sure how to make a new activity
             Resource: https://www.youtube.com/watch?v=n21mXO1ASJM&t=62s
             Solution: Used the code from this video.
             */
            myActivity.startActivity(new Intent(myActivity, HelpMenu.class));
        }
    }

    @Override
    public void receiveInfo(GameInfo info) {
        if (surfaceView == null) return;

        if (info instanceof IllegalMoveInfo || info instanceof NotYourTurnInfo) {
            //TODO place holder for handling
            return;
        } else if (!(info instanceof BlokusGameState))
            // if we do not have a BlokusGameState, ignore
            return;
        else {
            state = (BlokusGameState) info;
            currentInventory = state.getAllPieceInventory().get(playerNum);
            //TODO make setState method in Master GUI class
            surfaceView.setState(state);
            updatePlayerScores();
            updatePlayerPiecesRemaining();
            surfaceView.invalidate();
            //If the human player has given up in the past, skip their turn
            if(state.getAllPlayersGivenUp()[playerNum]){
                GiveUp gu = new GiveUp(this);
                game.sendAction(gu);
                return;
            }
            Log.i("human player", "receiving");
        }
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
            if (pp.checkForValidMove(playerNum)) {
                placePieceButton.setEnabled(true);
            } else if (!pp.checkForValidMove(playerNum)) {
                placePieceButton.setEnabled(false);
            }
            surfaceView.invalidate();
            return true;
        }
    }

    //finds the piece in the the inventory based on name
    public Piece findPiece(String pieceName) {
        if (currentInventory != null) {
            for (Piece p : currentInventory) {
                if (pieceName.equals(p.getName())) {
                    return p;
                }
            }
        }
        return null;
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

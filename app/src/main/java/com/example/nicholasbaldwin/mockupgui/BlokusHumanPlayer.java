package com.example.nicholasbaldwin.mockupgui;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

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
    private TextView redPR, bluePR, greenPR, yellowPR, redName, blueName, greenName,
            yellowName;
    private ScrollView scrollView;
    private ImageButton oneButton, twoButton, sButton, threeButton, smallTButton,
            fourButton, fourLButton, fiveButton, fiveLButton, nButton, yButton,
            v3Button, cubeButton, cButton, bButton, zButton, mButton, xButton,
            fButton, bigTButton, cornerButton;
    private Button placePieceButton, rotateButton, flipButton, helpButton, giveUpButton, quitButton;

    //Game action that will be sent when the player tries to flip, rotate or place a piece
    private PlacePiece pp = null;
    //The player's piece inventory after the game state has changed
    private ArrayList<Piece> currentInventory = null;
    //Represents the currently selected piece from the scroll view
    ImageButton currentPieceButton = null;

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
    /**
     * Sets up all the initial components for the
     * Human Player's GUI. things like text views,
     * buttons, and even the sound.
     * @param activity - gets the activity from the
     *                  BlokusLocalGame
     */
    @Override
    public void setAsGui(GameMainActivity activity) {
        // remember the activity
        myActivity = activity;

        activity.setContentView(R.layout.default_player_gui);
        surfaceView = myActivity.findViewById(R.id.blokusBoard);
        messageBox = myActivity.findViewById(R.id.messageTV);

        //all the scores, pieces remaining, and text views
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
        redName = myActivity.findViewById(R.id.redName);
        blueName = myActivity.findViewById(R.id.blueName);
        greenName = myActivity.findViewById(R.id.greenName);
        yellowName = myActivity.findViewById(R.id.yellowName);

        //the board
        surfaceView.setOnTouchListener(this);

        //the inventory list of pieces
        scrollView = myActivity.findViewById(R.id.piecesScrollView);
        scrollView.setOnClickListener(this);

        //each individual piece button in the scroll view
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

        //all the action buttons
        placePieceButton = myActivity.findViewById((R.id.placePieceButton));
        placePieceButton.setOnClickListener(this);
        rotateButton = myActivity.findViewById(R.id.rotateButton);
        rotateButton.setOnClickListener(this);
        flipButton = myActivity.findViewById(R.id.flipButton);
        flipButton.setOnClickListener(this);
        helpButton = myActivity.findViewById(R.id.helpButton);
        helpButton.setOnClickListener(this);
        giveUpButton = myActivity.findViewById(R.id.giveUpButton);
        giveUpButton.setOnClickListener(this);
        quitButton = myActivity.findViewById(R.id.quitButton);
        quitButton.setOnClickListener(this);

        //sets the current inventory based on the current state
        this.state = new BlokusGameState();
        currentInventory = state.getAllPieceInventory().get(playerNum);

        //makes sure no one can place a piece on start up
        placePieceButton.setEnabled(false);
    }
    /**
     * An onclick listener method that handles all the
     * actions that comes with each button on the GUI
     *
     * @param v - the view that was pressed by the user
     */
    @Override
    public void onClick(View v) {

        //Opens a separate screen to explain the rules
         if (v == helpButton) {
            /**
             External Citation:
             Date: 19 April 2019
             Problem: Not sure how to make a new activity
             Resource: https://www.youtube.com/watch?v=n21mXO1ASJM&t=62s
             Solution: Used the code from this video.
             */
            myActivity.startActivity(new Intent(myActivity, HelpMenu.class));
        }

        //Players can quit back to the config screen
        if (v == quitButton) {
            myActivity.startActivity(new Intent(myActivity, QuitMenu.class));
        }

        //Ensures it's the player's turn, otherwise disable the buttons
        if (state.getPlayerTurn() != playerNum) {
            return;
        }
        /**
         External Citation:
         Date: 20 April 2019
         Problem: I didn't know how to pass in a player object into the enclosed class
         Solution: Used the example code from the post
         Source: https://stackoverflow.com/questions/5530256/java-class-this
         */
        //If players decide to give up, they skip their turn for the rest of the game
        if (v == giveUpButton) {
            String quitQuestion =
                    "Do you really want to give up?";
            String posLabel =
                    "Yes";
            String negLabel =
                    "No";
            MessageBox.popUpChoice(quitQuestion, posLabel, negLabel,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface di, int val) {
                            GiveUp gu = new GiveUp(BlokusHumanPlayer.this);
                            game.sendAction(gu);
                        }
                    },
                    null,
                    myActivity);

        }

        //all the individual piece buttons
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
        /**
         External Citation:
         Date: 8 April 2019
         Problem: I didn't know how to make buttons disappear when used
         Source:https://stackoverflow.com/questions/14868349/how-to-disappear-button-in-android
         */

        //This makes the piece button disappear when pressed
        //so that the user can't use that piece again
        if (v == placePieceButton) {
            game.sendAction(pp);
            currentPieceButton.setVisibility(View.GONE);
            surfaceView.setCurrentPiece(null);
            placePieceButton.setEnabled(false);
        }

        //Flips currently selected piece's orientation
        else if (v == flipButton && surfaceView.getCurrentPiece() != null) {
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

            //Rotates currently selected piece's orientation
        } else if (v == rotateButton && surfaceView.getCurrentPiece() != null) {
            Piece p = surfaceView.getCurrentPiece();
            p.setPieceLayout(p.rotate90());
            //checks to see if you can place a piece after you rotated the piece
            if (pp != null) {
                pp.setPieceLayout(p.getPieceLayout());
                if (pp.checkForValidMove(playerNum)) {
                    placePieceButton.setEnabled(true);
                } else if (!pp.checkForValidMove(playerNum)) {
                    placePieceButton.setEnabled(false);
                }
            }

        }
    }
    /**
     * Receives the updated BlokusGameState and updates the
     * GUI accordingly.
     *
     * @param info - the new updated BlokusGameState
     */
    @Override
    public void receiveInfo(GameInfo info) {
        //If the board surface hasn't been initialized yet, don't do anything
        if (surfaceView == null) return;

        if (info instanceof IllegalMoveInfo || info instanceof NotYourTurnInfo) {
            return;
        } else if (!(info instanceof BlokusGameState))
            // if we do not have a BlokusGameState, ignore
            return;
        else {
            state = (BlokusGameState) info;

            //tells the player whose turn it in the message box
            messageBox.setText(allPlayerNames[state.getPlayerTurn()] + "'s turn");

            //sets the names based on hte info given
            redName.setText(allPlayerNames[Piece.RED]);
            blueName.setText(allPlayerNames[Piece.BLUE]);
            greenName.setText(allPlayerNames[Piece.GREEN]);
            yellowName.setText(allPlayerNames[Piece.YELLOW]);

            //set the new inventory and updates the text views for scores and pieces remaining
            currentInventory = state.getAllPieceInventory().get(playerNum);
            surfaceView.setState(state);
            updatePlayerScores();
            updatePlayerPiecesRemaining();
            //redraws the board based on what it looks like now
            surfaceView.invalidate();
            //If the human player has given up in the past, skip their turn
            if (state.getAllPlayersGivenUp()[playerNum]) {
                GiveUp gu = new GiveUp(this);
                game.sendAction(gu);
                return;
            }
            Log.i("human player", "receiving");
        }
    }

    /**
     * The on touch method for the BlokusBoard surface view
     * class.Determines if a move is valid or not based on
     * where the user is touching. Uses the drag motion to
     * move pieces on the board
     *
     * @param v - the surface view that is being touched
     * @param event - either a touch or a drag that d
     *              determines how a piece should be
     *              moving on the board
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        //Encourage players to select a piece from their inventory
        if (surfaceView.getCurrentPiece() == null) {
            messageBox.setText("Invalid Move: Select a Piece.");
            return false;
        }
        // get the x and y coordinates of the touch-location;
        // convert them to square coordinates (where both
        // values are in the range 0..19)
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
            // where the persons finger is
            messageBox.setText("Moving Piece\n");

            //also sets the pieces x & y position based on where the piece is on the
            //board
            surfaceView.getCurrentPiece().setxPosition(p.x);
            surfaceView.getCurrentPiece().setyPosition(p.y);

            //create a new place piece object in order to constantly check to see if a
            //piece can be placed
            pp = new PlacePiece(this, surfaceView.getCurrentPiece().getXPosition(),
                    surfaceView.getCurrentPiece().getYPosition(), surfaceView.getCurrentPiece());
            pp.setBoard(state.getBoard());

            //this checks to see of the current piece is a valid move and updates the board to show the piece
            // being dragged
            if (pp.checkForValidMove(playerNum)) {
                placePieceButton.setEnabled(true);
            } else if (!pp.checkForValidMove(playerNum)) {
                placePieceButton.setEnabled(false);
            }
            surfaceView.invalidate();
            return true;
        }
    }

    /**
     * A helper method that finds a piece based on its name
     * @param pieceName - the name of the piece being searched
     *                  for
     * @return returns the piece if it is found, null otherwise
     */
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

    /**
     * Methods that update the text view that display the scores
     * and piece remaining for each player
     */
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
     * A JUnit test that tests the functionality of the place piece
     * action.
     */
    public void test(){
        /**
         External Citation:
         Date: 23 April 2019
         Problem: We didn't know how to unit test our GUI players
         Source:https://stackoverflow.com/questions/14868349/how-to-disappear-button-in-android
         Solution: We looked at another team (Amazing Labyrinth) for how they unit tested their computer players. (Thank you!)
         */
        BlokusGameState bgs = new BlokusGameState();
        this.state = bgs;

        Piece testPiece = state.getAllPieceInventory().get(0).get(0);
        PlacePiece tpp = new PlacePiece(this, 0, 0, testPiece);
        tpp.setBoard(state.getBoard());
        assert tpp.checkForValidMove(0);
    }
}

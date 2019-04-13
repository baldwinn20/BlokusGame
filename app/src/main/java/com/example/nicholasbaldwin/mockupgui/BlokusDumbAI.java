package com.example.nicholasbaldwin.mockupgui;

import android.app.Activity;
import android.widget.TextView;

import com.example.nicholasbaldwin.mockupgui.game.infoMsg.GameInfo;
import com.example.nicholasbaldwin.mockupgui.game.infoMsg.IllegalMoveInfo;
import com.example.nicholasbaldwin.mockupgui.game.infoMsg.NotYourTurnInfo;
import com.example.nicholasbaldwin.mockupgui.game.util.GameComputerPlayer;

import java.util.ArrayList;
import java.util.Random;

/**
 * <!-- class BlokusDumbAI -->
 * <p>
 * This class implements the "dumb" computer player.
 * It simply picks a random piece and plays it in the
 * first legal spot it finds.
 *
 * @author <Nicholas Baldwin, Justin Cao, Dylan Pascua>
 * @version <Spring 2019>
 */
public class BlokusDumbAI extends GameComputerPlayer {
    //All instance variables
    BlokusGameState state = null;
    private Activity myActivity = null;
    private BlokusBoard surfaceView = null;
    private TextView messageBox = null;
    private TextView redScore, blueScore, greenScore, yellowScore,
            redPR, bluePR, greenPR, yellowPR;
    private int playerColor, piecesRemaining, playerScore, playerID, x, y;
    private final int INITIAL_PIECES_REMAINING = 21;
    private final int INITIAL_SCORE = 89;
    private ArrayList<Piece> currentInventory = null;
    private Random r;

    public BlokusDumbAI(String initName, int initColor, int initID) {
        super(initName);
        playerColor = initColor;
        playerID = initID;
        piecesRemaining = INITIAL_PIECES_REMAINING;
        playerScore = INITIAL_SCORE;
    }


    @Override
    protected void receiveInfo(GameInfo info) {

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
            surfaceView.setState(state);
            updatePlayerScores();
            updatePlayerPiecesRemaining();
            surfaceView.invalidate();
        }

        int min = 0;
        int max = 19;


        PlacePiece pp = new PlacePiece(this, x, y, selectRandomPiece());

        for (x = 0; x < max; x++) {
            for (int y = 0; y < max; y++) {
                // x = r.nextInt((max - min) + 1) + min;
                //y = r.nextInt((max - min) + 1) + min;

                //go thru board and place piece at first available spot
                if (pp.checkForValidMove(state.getPlayerTurn())) {
                    game.sendAction(pp);
                } else {
                    pp = new PlacePiece(this, x, y, selectRandomPiece());
                }
            }
        }

    }

    /**
     * External Citation
     * Date: 10 April 2019
     * Problem: How to pick a random number in a specific range.
     * Resource:https://stackoverflow.com/questions/363681/how-do-
     * i-generate-random-integers-within-a-specific-range-in-java
     * Solution: Used sample code from post.
     */

    //selectRandomPiece picks a random piece from the inventory
    public Piece selectRandomPiece() {

        int min = 0;
        int max = 20;
        r = new Random();
        int rand = r.nextInt((max - min) + 1) + min;
        return state.getAllPieceInventory().get(state.getPlayerTurn()).get(rand);
    }


    public int getPlayerColor() {
        return playerColor;
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

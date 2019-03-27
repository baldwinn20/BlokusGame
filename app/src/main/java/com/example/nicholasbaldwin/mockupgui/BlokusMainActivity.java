package com.example.nicholasbaldwin.mockupgui;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.nicholasbaldwin.mockupgui.game.config.GameConfig;
import com.example.nicholasbaldwin.mockupgui.game.config.GamePlayerType;
import com.example.nicholasbaldwin.mockupgui.game.util.GameMainActivity;
import com.example.nicholasbaldwin.mockupgui.game.util.GamePlayer;
import com.example.nicholasbaldwin.mockupgui.game.util.LocalGame;

import java.util.ArrayList;

/**
 * <!-- class BlokusMainActivity-->
 *
 * This class initializes a user interface that contains
 * a Button and EditText to test multiple instances of
 * the Blokus game state.
 * It also initializes the button
 * with an event listener
 *
 * @author <Justin Cao>
 * @author <Dylan Pascua>
 * @author <Nicholas Baldwin>
 */
public class BlokusMainActivity extends GameMainActivity {

    public static final int PORT_NUMBER = 5213;

    @Override
    public GameConfig createDefaultConfig() {
        // Define the allowed player types
        ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();

        // human GUI
        playerTypes.add(new GamePlayerType("Local Human Player") {
            public GamePlayer createPlayer(String name) {
                return new BlokusHumanPlayer(name, Color.BLUE, 0);
            }
        });

        // dumb computer player
        playerTypes.add(new GamePlayerType("Dumb AI Player") {
            public GamePlayer createPlayer(String name) {
                return new BlokusDumbAI(name, Color.GREEN, 1);
            }
        });

//        // smarter computer player
//        playerTypes.add(new GamePlayerType("Smart AI Player") {
//            public GamePlayer createPlayer(String name) {
//                return new TTTHumanPlayer2(name);
//            }
//        });
//
//        // network player
//        playerTypes.add(new GamePlayerType("Computer Player (dumb)") {
//            public GamePlayer createPlayer(String name) {
//                return new TTTComputerPlayer1(name);
//            }
//        });

        // Create a game configuration class for Tic-tac-toe
        GameConfig defaultConfig = new GameConfig(playerTypes, 4,4, "Blokus", PORT_NUMBER);

        // Add the default players
        defaultConfig.addPlayer("Human", 0); // yellow-on-blue GUI
        defaultConfig.addPlayer("Dumb 1", 1); // dumb computer player
        defaultConfig.addPlayer("Dumb 2", 1); // dumb computer player
        defaultConfig.addPlayer("Dumb 3", 1); // dumb computer player

        // Set the initial information for the remote player
        defaultConfig.setRemoteData("Remote Player", "", 1); // red-on-yellow GUI

        //done!
        return defaultConfig;
    }

    @Override
    public LocalGame createLocalGame() {
        return new BlokusLocalGame();
    }

    @Override
    public void onClick(View v) {

    }
}

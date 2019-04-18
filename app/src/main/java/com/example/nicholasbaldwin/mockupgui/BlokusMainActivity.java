/**
 * Blokus Beta Release Known Issues
 * <p>
 * - Game does not let human player choose a piece every time game is
 * launched. Not sure why this happens, but relaunching the app fixes it.
 * - Network play is not functional.
 * - Smart AI is not functional because implementing the Dumb AI took longer
 * than expected.
 * - We chose not to implement zoom functionality because the board is plenty visible
 * without a zoom feature
 */

package com.example.nicholasbaldwin.mockupgui;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.nicholasbaldwin.mockupgui.game.BlokusNetworkPlayer;
import com.example.nicholasbaldwin.mockupgui.game.config.GameConfig;
import com.example.nicholasbaldwin.mockupgui.game.config.GamePlayerType;
import com.example.nicholasbaldwin.mockupgui.game.infoMsg.BindGameInfo;
import com.example.nicholasbaldwin.mockupgui.game.util.GameMainActivity;
import com.example.nicholasbaldwin.mockupgui.game.util.GamePlayer;
import com.example.nicholasbaldwin.mockupgui.game.util.LocalGame;

import java.util.ArrayList;

/**
 * <!-- class BlokusMainActivity-->
 * <p>
 * This class initializes a user interface that contains
 * a Button and EditText to test multiple instances of
 * the Blokus game state.It also initializes the button
 * with an event listener
 *
 * @author <Justin Cao, Dylan Pascua, Nicholas Baldwin>
 * @version <Spring 2019>
 */
public class BlokusMainActivity extends GameMainActivity {

    public static final int PORT_NUMBER = 5613;

    @Override
    public GameConfig createDefaultConfig() {
        // Define the allowed player types
        ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();

        // human GUI
        playerTypes.add(new GamePlayerType("Local Human Player") {
            public GamePlayer createPlayer(String name) {
                return new BlokusHumanPlayer(name);
            }
        });

        // dumb computer player
        playerTypes.add(new GamePlayerType("Dumb AI Player") {
            public GamePlayer createPlayer(String name) {
                return new BlokusDumbAI(name);
            }
        });

        // smarter computer player
        playerTypes.add(new GamePlayerType("Smart AI Player") {
            public GamePlayer createPlayer(String name) {
                return new BlokusSmartAI(name);
            }
        });

        // network player
//        playerTypes.add(new GamePlayerType("Computer Player (dumb)") {
//            public GamePlayer createPlayer(String name) {
//                return new BlokusNetworkPlayer(name);
//            }
//        });

        // Create a game configuration class for Tic-tac-toe
        GameConfig defaultConfig = new GameConfig(playerTypes, 4, 4, "Blokus", PORT_NUMBER);

        //TODO for some reason the type index messes with the AI's turns
        // Add the default players
        defaultConfig.addPlayer("Human", 0); // yellow-on-blue GUI
        defaultConfig.addPlayer("Dumb 1", 1); // dumb computer player
        defaultConfig.addPlayer("Dumb 2", 1); // dumb computer player
        defaultConfig.addPlayer("Dumb 3", 1); // dumb computer player

        // Set the initial information for the remote player
        defaultConfig.setRemoteData("Remote Player", "", 0); // red-on-yellow GUI

        //done!
        return defaultConfig;
    }

    @Override
    public LocalGame createLocalGame() {
        return new BlokusLocalGame();
    }

}

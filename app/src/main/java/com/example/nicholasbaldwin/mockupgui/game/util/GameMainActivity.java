package com.example.nicholasbaldwin.mockupgui.game.util;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.nicholasbaldwin.mockupgui.R;
import com.example.nicholasbaldwin.mockupgui.game.config.GameConfig;
import com.example.nicholasbaldwin.mockupgui.game.config.GamePlayerType;

import java.util.ArrayList;



/**
 * class GameMainActivity
 * 
 * is the main activity for the game framework. To create a new game, create a
 * sub-class of this class that implements its abstract methods below.
 * 
 * @author Andrew M. Nuxoll
 * @author Steven R. Vegdahl
 * @date Version 2013
 */
public abstract class GameMainActivity extends Activity implements
View.OnClickListener {

	/*
	 * ====================================================================
	 * Instance Variables
	 * --------------------------------------------------------------------
	 */

	// A reference to the object representing the game itself. This is the
	// object that knows the rules of the game. This variable is initialized in
	// launchGame.
	private Game game = null;

	// an array containing references to all the players that are playing the game
	private GamePlayer[] players = null;

	// tells which player, if any, is running in the GUI
	private GamePlayer guiPlayer = null;

	// whether the game is over
	private boolean gameIsOver = false;

	// whether it is so early in the game that the configuration screen may
	// not have been fully linked to the GUI
	private boolean justStarted = true;

	// whether the game is in the "configuration" stage, before the actual game
	// has started
	private boolean doingConfiguration = true;

	/**
	 * contains the game configuration this activity will be used to initialize
	 */
	GameConfig config = null;

	// Each of these is initialized to point to various GUI controls
	TableLayout playerTable = null;
	ArrayList<TableRow> tableRows = new ArrayList<TableRow>();

	/*
	 * ====================================================================
	 * Abstract Methods
	 *
	 * To create a game using the game framework you must create a subclass of
	 * GameMainActivity that implements the following methods.
	 * --------------------------------------------------------------------
	 */

	/**
	 * Creates a default, game-specific configuration for the current game.
	 * <p>
	 * IMPORTANT: The default configuration must be a legal configuration!
	 *
	 * @return an instance of the GameConfig class that defines a default
	 * configuration for this game. (The default may be subsequently
	 * modified by the user if this is allowed.)
	 */
	public abstract GameConfig createDefaultConfig();

	/**
	 * createLocalGame
	 * <p>
	 * Creates a new game that runs on the server tablet. For example, if
	 * you were creating tic-tac-toe, you would implement this method to return
	 * an instance of your TTTLocalGame class which, in turn, would be a
	 * subclass of {@link LocalGame}.
	 *
	 * @return a new, game-specific instance of a sub-class of the LocalGame
	 * class.
	 */
	public abstract LocalGame createLocalGame();




}


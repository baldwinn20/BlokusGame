package com.example.nicholasbaldwin.mockupgui.game;

import android.util.Log;

import com.example.nicholasbaldwin.mockupgui.game.actionMsg.GameAction;
import com.example.nicholasbaldwin.mockupgui.game.infoMsg.GameInfo;
import com.example.nicholasbaldwin.mockupgui.game.util.Game;
import com.example.nicholasbaldwin.mockupgui.game.util.GamePlayer;
import com.example.nicholasbaldwin.mockupgui.game.util.IPCoder;
import com.example.nicholasbaldwin.mockupgui.game.util.NetworkObjectPasser;
import com.example.nicholasbaldwin.mockupgui.game.util.ProxyGame;

import java.util.LinkedList;
import java.util.Queue;

public class BlokusNetworkGame implements Game {
    //instance variables
    private GamePlayer player; // the player that is associated with this game

    // a queue of objects that are collected, which might have been sent over the
    // network before we are connected to a player
    private Queue<GameInfo> queuedObjectsForPlayer = new LinkedList<GameInfo>();

    //the network connection object
    private NetworkObjectPasser networkPasser;

    //a private constructor
    private BlokusNetworkGame(int portNum, String ipCode){
        // set instance variables to their initial values
        player = null;
        ipCode = IPCoder.decodeIp(ipCode); // convert to IP address

        // create the network-connector object
        networkPasser = new NetworkObjectPasser(ipCode, portNum) {

            // callback method, called whenever an object is sent to us from
            // across the network
            public void onReceiveObject(Object obj) {
                Log.i("ProxyGame", "received object ("+obj.getClass()+")");
                try {
                    boolean b = obj instanceof GameInfo;
                    if (b) {
                        // object is a GameState object
                        GameInfo gs = (GameInfo)obj;
                        gs.setGame(BlokusNetworkGame.this);
                        synchronized(this) {
                            if (player == null) {
                                // if the player has not been connected, save the
                                // object in a queue
                                Log.i("ProxyGame", "adding object to queue");
                                queuedObjectsForPlayer.add(gs);
                            }
                            else {
                                // if the player has been connected, send the object
                                // directly to the player
                                Log.i("ProxyGame", "about to send state to player");
                                player.sendInfo(gs);
                                Log.i("ProxyGame", "... done sending state");
                            }
                        }
                    }
                    else {
                        // ignore if the object is not a GameInfo object
                        Log.i("ProxyGame", "object NOT being sent to player");
                    }
                }
                catch (Exception x) {
                    // if any other exception occurs, log it
                    Log.i(x.getClass().toString(), x.getMessage());
                }
            }
        };
    }

    public static BlokusNetworkGame create(int portNum, String ipCode){
        // create the game object
        BlokusNetworkGame rtnVal = new BlokusNetworkGame(portNum, ipCode);

        // see if a connection becomes established; if so, return
        // the object, otherwise null
        boolean isReady = rtnVal.networkPasser.isReady();
        if (isReady) {
            return rtnVal;
        }
        else {
            return null;
        }
    }

    @Override
    public void start(GamePlayer[] players) {
        Log.i("ProxyGame", "start() called");

        // if player has already been bound, ignore
        if (player != null) return;

        // if the player array somehow something other than
        // a single element, ignore
        if (players.length != 1) return;

        // start the player
        if (players[0] != null) {
            players[0].start(); // start our player
        }

        // loop through and empty (and send) the objects that might have
        // accumulated in the queue before the player was bound
        for (;;) {
            GameInfo unqueuedObject;
            synchronized (this) {
                if (queuedObjectsForPlayer.isEmpty()) {
                    // queue is finally empty: bind player and return
                    player = players[0];
                    return;
                }
                else {
                    // queue not empty, so remove an object from the queue
                    unqueuedObject = queuedObjectsForPlayer.remove();
                }
            }

            // send the just=unqueued object to the player
            Log.i("ProxyGame", "sending queued object to player ("+unqueuedObject.getClass()+")");
            players[0].sendInfo(unqueuedObject);
        }
    }

    @Override
    public void sendAction(GameAction action) {
        // sends the action across the network
        if(action != null){
            //apparently this nulls out the player so that it wont be serialized
            action.setPlayer(null);
            networkPasser.sendObject(action);
        }
    }
}

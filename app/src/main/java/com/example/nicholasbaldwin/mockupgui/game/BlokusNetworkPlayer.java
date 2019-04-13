package com.example.nicholasbaldwin.mockupgui.game;

import android.drm.DrmStore;

import com.example.nicholasbaldwin.mockupgui.BlokusGameState;
import com.example.nicholasbaldwin.mockupgui.PlacePiece;
import com.example.nicholasbaldwin.mockupgui.game.actionMsg.GameAction;
import com.example.nicholasbaldwin.mockupgui.game.infoMsg.BindGameInfo;
import com.example.nicholasbaldwin.mockupgui.game.infoMsg.GameInfo;
import com.example.nicholasbaldwin.mockupgui.game.util.Game;
import com.example.nicholasbaldwin.mockupgui.game.util.NetworkObjectPasser;
import com.example.nicholasbaldwin.mockupgui.game.util.ProxyPlayer;

public class BlokusNetworkPlayer extends ProxyPlayer {
    //instance variables
     private Game game;
     private NetworkObjectPasser networkPasser;
     private boolean isReady = false;
    /**
     * ProxyPlayer constructor.
     *
     * @param portNum the port number through which we connect to our client
     */
    //TODO idk if we have special cases with this class
    public BlokusNetworkPlayer(int portNum) {
        super(portNum);
    }

    public boolean isReady(){return isReady;}

    public void sendInfo(GameInfo info){
    }

}

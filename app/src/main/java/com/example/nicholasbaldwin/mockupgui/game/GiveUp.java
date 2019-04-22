package com.example.nicholasbaldwin.mockupgui.game;

import com.example.nicholasbaldwin.mockupgui.game.actionMsg.GameAction;
import com.example.nicholasbaldwin.mockupgui.game.util.GamePlayer;

public class GiveUp extends GameAction {

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public GiveUp(GamePlayer player) {
        super(player);
    }

}

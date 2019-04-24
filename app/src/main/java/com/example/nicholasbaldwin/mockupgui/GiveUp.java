package com.example.nicholasbaldwin.mockupgui;

import com.example.nicholasbaldwin.mockupgui.game.actionMsg.GameAction;
import com.example.nicholasbaldwin.mockupgui.game.util.GamePlayer;
/**
 * <!-- class GiveUp -->
 * <p>
 * This class implements functionality that only allows a
 * player to give up when they cannot make a move
 *
 * @author <Nicholas Baldwin, Justin Cao, Dylan Pascua>
 * @version <Spring 2019>
 */

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

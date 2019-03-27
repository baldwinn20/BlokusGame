package com.example.nicholasbaldwin.mockupgui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceView;

import com.example.nicholasbaldwin.mockupgui.game.util.FlashSurfaceView;

public class BlokusBoard extends SurfaceView {

/*variables for creating the board
  Each tile on the board is worth 4% of the surface view
  if you include the dividing lines it is 5%. A standard
  board has 20x20 tiles so 5x22 = %100 of the view
 */
private final int BOARD_LENGTH  = 20; // how many tiles are in a board
private final float TILE_SIZE_PERCENT = 4;// size of each of the tiles
private final float DIVIDER_PRCENTAGE = 1;//thickness of the dividers
private final float TILE_TOTAL_PERCENTAGE = TILE_SIZE_PERCENT
        + DIVIDER_PRCENTAGE;

/*  instance variables that are used to create the board
 */

protected BlokusGameState state; // the current games state
    protected float hBase;
    protected float vBase;



    public BlokusBoard(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas){
        Paint bgColor = new Paint();
        bgColor.setColor(Color.GRAY);




    }






}

package com.example.nicholasbaldwin.mockupgui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
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
    //the offset from the left and top to the beginning of the board
    protected float hBase;
    protected float vBase;
protected float fullSquare; // the size of the surfaceView


    public BlokusBoard(Context context) {
        super(context);
        init();
    }
    public BlokusBoard(Context context, AttributeSet attrs){
        super(context,attrs);
        init();
    }

    public BlokusBoard(Context context, AttributeSet attrs, int defStyleAttr){
        super(context,attrs,defStyleAttr);
        init();
    }

    private void init(){setBackgroundColor(Color.DKGRAY);}

    private void updateDimensions(Canvas canvas){
        int width = canvas.getWidth();
        int height = canvas.getHeight();

        /*sets the full square to the smallest value between
        the height and the width of the surface view
         */

        if(width> height){
            fullSquare = height;
            vBase = 0;
            hBase = (width - height)/(float) 2.0;
        }
        else{
            fullSquare = width;
            hBase = 0;
            vBase = (height - width)/ (float) 2.0;
        }
    }

    @Override
    protected void onDraw(Canvas canvas){
        Paint bgColor = new Paint();
        bgColor.setColor(Color.GRAY);
        //paints the horizontal and vertical lines
        for(int i = 0; i < BOARD_LENGTH; i++){
            float left = TILE_SIZE_PERCENT + (i * TILE_TOTAL_PERCENTAGE);
            float right = left + DIVIDER_PRCENTAGE;
            float top = 0;
            float bottom = 100;
            canvas.drawRect(hLocation(left),vLocation(top), hLocation(right)
                    , vLocation(bottom), bgColor);
            //the inverse to draw the vertical lines
            canvas.drawRect(hLocation(top),vLocation(left), hLocation(bottom)
                    , vLocation(right), bgColor);
        }

    }
    /*
    Helper methods to convert the percentages to both horizontal
    pixel location (hLocation) and vertical pixel location
    (vLocation)
     */
    private float hLocation(float percent){
        return hBase + percent * fullSquare / 100;
    }
    private float vLocation(float percent){
        return vBase + percent * fullSquare / 100;
    }






}

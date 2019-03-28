package com.example.nicholasbaldwin.mockupgui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class BlokusBoard extends SurfaceView {

/*variables for creating the board
  Each tile on the board is worth 4% of the surface view
  if you include the dividing lines it is 5%. A standard
  board has 20x20 tiles so 5x22 = %100 of the view
 */
private final int BOARD_LENGTH  = 20; // how many tiles are in a board
private final float TILE_SIZE_PERCENT = 4.5f;// size of each of the tiles
private final float DIVIDER_PERCENTAGE = .5f;//thickness of the dividers
private final float TILE_TOTAL_PERCENTAGE = TILE_SIZE_PERCENT
        + DIVIDER_PERCENTAGE;
private final float LEFT_BOARDER_PERCENTAGE = 0.5f;

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
        //this allows the message widget to have more space
        this.getHolder().setFixedSize(canvas.getWidth(), 100);


        updateDimensions(canvas);
        Paint dividerColor = new Paint();
        dividerColor.setColor(Color.GRAY);

        //this is the very left most vertical boarder
        canvas.drawRect(hLocation(0),vLocation(0), hLocation(0.2f)
                , vLocation(100), dividerColor);

        //paints the horizontal and vertical lines
        for(int i = -1; i < BOARD_LENGTH; i++){
            float left = TILE_SIZE_PERCENT + (i * TILE_TOTAL_PERCENTAGE);
            float right = left + DIVIDER_PERCENTAGE;
            float top = 0;
            float bottom = 100;
            canvas.drawRect(hLocation(left),vLocation(top), hLocation(right)
                    , vLocation(bottom), dividerColor);
            canvas.drawRect(hLocation(top),vLocation(left), hLocation(bottom)
                    , vLocation(right), dividerColor);
        }


        //updates the board based on the pieces found in BlokusGameState
        int[][] boardCopy = state.getBoard();

        //this is used to test the update board algorythm
        for(int k = 0; k < BOARD_LENGTH; k++){
            for (int h = 0; h < BOARD_LENGTH; h++){
                if(k == 5 && h == 5){
                    boardCopy[k][h] = 0; // the tile that should be colored
                }
                boardCopy[k][h]= -1;// everything else should be the same 
            }
        }

        for(int i = 0; i < BOARD_LENGTH; i++){
            for(int j = 0; j < BOARD_LENGTH; j++){
                if(boardCopy[i][j] != 0){
                    drawTile(i,j,boardCopy[i][j], canvas);
                }
            }
        }

    }

    protected void drawTile(int xPosition, int yPosition, int playerID, Canvas canvas){
        Paint tilePaint = new Paint();
        //chooses the color based on the playersID
        if(playerID == 0){
            tilePaint.setColor(Color.RED);
        }
        else if(playerID == 1){
            tilePaint.setColor(Color.BLUE);
        }
        else if(playerID == 2){
            tilePaint.setColor(Color.YELLOW);
        }
        else if (playerID == 2){
            tilePaint.setColor(Color.GREEN);
        }

        //draw one tile based on the location
        float left = LEFT_BOARDER_PERCENTAGE + (xPosition * TILE_TOTAL_PERCENTAGE);
        float right = TILE_SIZE_PERCENT;
        float top = DIVIDER_PERCENTAGE + (yPosition * TILE_TOTAL_PERCENTAGE);
        float bottom = 100 - ((20-yPosition) * TILE_TOTAL_PERCENTAGE );
        canvas.drawRect(hLocation(left),vLocation(top), hLocation(right)
                , vLocation(bottom),  tilePaint);

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

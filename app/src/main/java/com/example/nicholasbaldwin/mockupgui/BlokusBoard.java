package com.example.nicholasbaldwin.mockupgui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.SurfaceView;

/**
 * <!-- class BlokusBoard -->
 * <p>
 * This class creates the Blokus board and sets piece color based on player.
 *
 * @author <Nicholas Baldwin, Justin Cao, Dylan Pascua>
 * @version <Spring 2019>
 */

public class BlokusBoard extends SurfaceView {

    /*variables for creating the board
      Each tile on the board is worth 4% of the surface view
      if you include the dividing lines it is 5%. A standard
      board has 20x20 tiles so 5x22 = %100 of the view
     */
    private final int BOARD_LENGTH = 20; // how many tiles are in a board
    private final float TILE_SIZE_PERCENT = 4.5f;// size of each of the tiles
    private final float DIVIDER_PERCENT = .5f;//thickness of the dividers
    private final float TILE_TOTAL_PERCENT = TILE_SIZE_PERCENT
            + DIVIDER_PERCENT;
    private final float LEFT_BORDER_PERCENT = 0.5f;
    private int boardWidth, boardHeight;
    private Piece currentPiece = null;

    //All pieces previewed on the board are positioned near the center
    private int xCurPiece = 9, yCurPiece = 9;
    /*  instance variables that are used to create the board
     */
    protected BlokusGameState state; // the current games state

    //the offset from the left and top to the beginning of the board
    protected float hBase, vBase;
    protected float fullSquare; // the size of the surfaceView

    /**
     * Constructor for the BlokusBoard class.
     *
     * @param context - a reference to the activity this animation is run under
     */

    public BlokusBoard(Context context) {
        super(context);
        init();
    }

    /**
     * An alternate constructor for use when a subclass is directly specified
     * in the layout.
     *
     * @param context - a reference to the activity this animation is run under
     * @param attrs   - set of attributes passed from system
     */
    public BlokusBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * Helper-method for the constructors
     * Sets the background color of the board to dark gray
     */
    private void init() {
        setBackgroundColor(Color.DKGRAY);
    }

    /**
     * update the instance variables that relate to the drawing surface
     *
     * @param canvas
     * 		an object that references the drawing surface
     */
    private void updateDimensions(Canvas canvas) {
        boardWidth = canvas.getWidth();
        boardHeight = canvas.getHeight();

        /*sets the full square to the smallest value between
        the height and the width of the surface view
         */
        if (boardWidth > boardHeight) {
            fullSquare = boardHeight;
            vBase = 0;
            hBase = (boardWidth - boardHeight) / (float) 2.0;
        } else {
            fullSquare = boardWidth;
            hBase = 0;
            vBase = (boardHeight - boardWidth) / (float) 2.0;
        }
    }

    /**
     * callback method, called whenever it's time to redraw
     * frame
     *
     * @param canvas
     * 		the canvas to draw on
     *
     */
    @Override
    protected void onDraw(Canvas canvas) {
        //this allows the message widget to have more space
        this.getHolder().setFixedSize(canvas.getWidth(), 100);

        // update the variables that relate
        // to the dimensions of the animation surface
        updateDimensions(canvas);

        //paint the Blokus board's horizontal and vertical lines
        Paint dividerColor = new Paint();
        dividerColor.setColor(Color.BLACK);

        //this draws the starting points for each respective color
        //the literal floats basically make sure the board is
        //aligned
        Paint startingColor = new Paint();
        float sLeft = LEFT_BORDER_PERCENT - 0.5f;
        float sRight = 99.5f;
        float sTop = DIVIDER_PERCENT - 0.5f;
        float sBottom = 99.5f;
        startingColor.setColor(Color.RED);
        canvas.drawRect(hLocation(sLeft + 1.5f), vLocation(sTop + 1.4f),
                hLocation(sRight - (19 * TILE_TOTAL_PERCENT) - 1.4f),
                vLocation(sBottom - (19 * TILE_TOTAL_PERCENT) - 1.4f), startingColor);
        startingColor.setColor(Color.BLUE);
        canvas.drawRect(hLocation(sLeft + 1.5f + (19 * TILE_TOTAL_PERCENT)), vLocation(sTop + 1.4f),
                hLocation(sRight - 1.4f),
                vLocation(sBottom - (19 * TILE_TOTAL_PERCENT) - 1.4f), startingColor);
        startingColor.setColor(Color.GREEN);
        canvas.drawRect(hLocation(sLeft + 1.5f), vLocation(sTop + 1.4f + (19 * TILE_TOTAL_PERCENT)),
                hLocation(sRight - (19 * TILE_TOTAL_PERCENT) - 1.4f),
                vLocation(sBottom - 1.4f), startingColor);
        startingColor.setColor(Color.YELLOW);
        canvas.drawRect(hLocation(sLeft + 1.5f + (19 * TILE_TOTAL_PERCENT)),
                vLocation(sTop + 1.4f + (19 * TILE_TOTAL_PERCENT)), hLocation(sRight - 1.4f),
                vLocation(sBottom - 1.4f), startingColor);


        //draws the very left most vertical boarder
        canvas.drawRect(hLocation(0), vLocation(0), hLocation(0.2f)
                , vLocation(100), dividerColor);

        //paints the horizontal and vertical lines
        for (int i = -1; i < BOARD_LENGTH; i++) {
            float left = TILE_SIZE_PERCENT + (i * TILE_TOTAL_PERCENT);
            float right = left + DIVIDER_PERCENT;
            float top = 0;
            float bottom = 100;
            canvas.drawRect(hLocation(left), vLocation(top), hLocation(right)
                    , vLocation(bottom), dividerColor);
            canvas.drawRect(hLocation(top), vLocation(left), hLocation(bottom)
                    , vLocation(right), dividerColor);

        }

        // if we don't have any state, there's nothing more to draw, so return
        if (state == null) {
            return;
        }

        //this draws the new board once a new piece has been placed
        for (int i = 0; i < BOARD_LENGTH; i++) {
            for (int j = 0; j < BOARD_LENGTH; j++) {
                if (state.getBoard()[i][j] != -1) {
                    drawTile(i, j, state.getBoard()[i][j], canvas);
                }
            }
        }

        //Draw the current piece on top of the board
        if (currentPiece != null) {
            xCurPiece = currentPiece.getXPosition();
            yCurPiece = currentPiece.getYPosition();
            //This ensures that the preview piece doesn't go out of the bounds
            //There is no need for a check on the left and top of the board because
            //the pieces cant be placed less than 0.
            if (xCurPiece > state.getBoard().length - 1 && yCurPiece > state.getBoard().length - 1) {
                return;
            }
            for (int i = 0; i < Piece.PIECE_LAYOUT_SIZE; i++) {
                for (int j = 0; j < Piece.PIECE_LAYOUT_SIZE; j++) {
                    if (currentPiece.getPieceLayout()[i][j] != Piece.EMPTY) {
                        drawTile(i + xCurPiece,
                                j + yCurPiece,
                                currentPiece.getPieceLayout()[i][j],  //player id
                                canvas);
                    }
                }
            }
        }


    }
    /**
     * method used to draw the tiles of a piece based on
     * the x & y coordinates on the board
     *
     * @param xPosition -x coordinate on the board
     * @param yPosition -y coordinate on the board
     * @param playerID -the id of the player that determines
     *                 the color of the tile
     * @param canvas - the canvas to draw on
     *
     */
    protected void drawTile(int xPosition, int yPosition, int playerID, Canvas canvas) {
        Paint tilePaint = new Paint();

        //chooses the color based on the playersID
        if (playerID == 0) {
            tilePaint.setColor(Color.RED);
        } else if (playerID == 1) {
            tilePaint.setColor(Color.BLUE);
        } else if (playerID == 2) {
            tilePaint.setColor(Color.GREEN);
        } else if (playerID == 3) {
            tilePaint.setColor(Color.YELLOW);
        }

        //draw one tile based on the location
        float left = LEFT_BORDER_PERCENT - 0.5f + (xPosition * TILE_TOTAL_PERCENT);
        float right = 99.5f - ((19 - xPosition) * TILE_TOTAL_PERCENT);
        float top = DIVIDER_PERCENT - 0.5f + (yPosition * TILE_TOTAL_PERCENT);
        float bottom = 99.5f - ((19 - yPosition) * TILE_TOTAL_PERCENT);

        canvas.drawRect(hLocation(left), vLocation(top), hLocation(right)
                , vLocation(bottom), tilePaint);
    }
    /**
     * method that take a pixel on a canvas and turns it into an
     * x,y coordinate point
     *
     * @param x -the x value of the pixel that was touched
     * @param y -the y value of the pixel that was touched
     *
     * @returns a point if the pixel is on the board, null
     * if otherwise
     */
    public Point mapPixelToTile(int x, int y) {
        for (int i = 0; i < BOARD_LENGTH; i++) {
            for (int j = 0; j < BOARD_LENGTH; j++) {
                //the dime of the tile based on its position
                float left = hLocation(LEFT_BORDER_PERCENT - 0.5f + (i * TILE_TOTAL_PERCENT));
                float right = hLocation(DIVIDER_PERCENT + TILE_SIZE_PERCENT + (i * TILE_TOTAL_PERCENT));
                float top = vLocation(DIVIDER_PERCENT - 0.5f + (j * TILE_TOTAL_PERCENT));
                float bottom = vLocation(DIVIDER_PERCENT + TILE_SIZE_PERCENT + (j * TILE_TOTAL_PERCENT));
                if ((x > left) != (x > right) && (y > top) != (y > bottom)) {
                    return new Point(i, j);
                }
            }
        }
        //if the person did not touch a tile
        return null;
    }

    /*
    Helper methods to convert the percentages to both horizontal
    pixel location (hLocation) and vertical pixel location
    (vLocation)
     */
    private float hLocation(float percent) {
        return hBase + percent * fullSquare / 100;
    }

    private float vLocation(float percent) {
        return vBase + percent * fullSquare / 100;
    }

    //setters & getters
    public void setState(BlokusGameState bgs) {
        state = bgs;
    }

    public void setCurrentPiece(Piece currentPiece) {
        this.currentPiece = currentPiece;
    }

    public Piece getCurrentPiece() {
        return this.currentPiece;
    }



}

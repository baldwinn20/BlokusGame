package com.example.nicholasbaldwin.mockupgui;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * <!-- class MainActivity-->
 *
 * This class initializes a user interface that contains
 * a Button and EditText to test multiple instances of
 * the Blokus game state.
 * It also initializes the button
 * with an event listener
 *
 * @author <Justin Cao>
 * @author <Dylan Pascua>
 * @author <Nicholas Baldwin>
 */
public class MainActivity extends AppCompatActivity {
    private ImageButton fPieceButton;
    /**
     * onCreate
     *Sets up graphical user interface to respond
     *to the user pressing on the test button
     * and displaying game state instance
     * variables in the EditText field.
     *
     * @param savedInstanceState contains the activity's previously
     *                           saved state
     *
     * @return null
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fPieceButton = findViewById(R.id.fPiece);
    }



}

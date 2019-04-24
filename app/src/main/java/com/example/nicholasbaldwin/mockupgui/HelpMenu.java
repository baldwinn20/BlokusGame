package com.example.nicholasbaldwin.mockupgui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;

/**
 * <!-- class HelpMenu-->
 * <p>
 * This class implements a popup menu when the help button
 * is pushed. It displays the game rules and has a button that
 * allows the user to return to their game.
 *
 * @author <Justin Cao, Dylan Pascua, Nicholas Baldwin>
 * @version <Spring 2019>
 */

public class HelpMenu extends AppCompatActivity implements Serializable {

    private static long serialVersionUID = 1293786127936L;
    private Button returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.help_menu);
        returnButton = findViewById(R.id.exitButton);

        //closes activity when "return" button is touched
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

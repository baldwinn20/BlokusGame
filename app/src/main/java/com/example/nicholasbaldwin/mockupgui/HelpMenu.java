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
 * is pushed
 *
 * @author <Justin Cao, Dylan Pascua, Nicholas Baldwin>
 * @version <Spring 2019>
 */

public class HelpMenu extends AppCompatActivity implements Serializable {

    private static long serialVersionUID = 1293786127936L;
    private Button helpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.help_menu);
        helpButton = findViewById(R.id.helpButton);

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

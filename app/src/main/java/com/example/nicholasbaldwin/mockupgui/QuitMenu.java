package com.example.nicholasbaldwin.mockupgui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;

/**
 * <!-- class HelpMenu-->
 * <p>
 * This class implements a popup menu when the quit button
 * is pushed
 *
 * @author <Justin Cao, Dylan Pascua, Nicholas Baldwin>
 * @version <Spring 2019>
 */

public class QuitMenu extends AppCompatActivity implements Serializable {

    private static long serialVersionUID = 1850386927582L;
    private Button yesButton;
    private Button noButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.quit_menu);

        yesButton = findViewById(R.id.yesButton);
        noButton = findViewById(R.id.noButton);

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
    }
}


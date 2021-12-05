package com.puzzle15;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class CustomGameActivity extends MainActivity {

    private Button btnStartCustomGame, btnStartAIPlay, btnToMenu;
    private EditText edtTxtStepsToFinish;
    private Spinner CardStyleSpinner;
    private Spinner imageSpinner;
    private TextView cardStyletxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_game);

        initViews();

        btnStartCustomGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                try {
                    GameParams.turnsToFinish = Integer.parseInt(edtTxtStepsToFinish.getText().toString());
                } catch(NumberFormatException nfe) {
                    GameParams.turnsToFinish = 20;
                }


                GameParams.pictureId = imageSpinner.getSelectedItemId();
                GameParams.cardStyle = CardStyleSpinner.getSelectedItemId();

                Intent intent = new Intent(CustomGameActivity.this, RandomGameActivity.class);
                startActivity(intent);
            }
        });
        btnStartAIPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomGameActivity.this, RandomGameActivity.class);
                startActivity(intent);
            }
        });

        CardStyleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(i != 2){
                    cardStyletxt.setVisibility(View.INVISIBLE);
                    imageSpinner.setVisibility(View.INVISIBLE);
                }
                else {
                    cardStyletxt.setVisibility(View.VISIBLE);
                    imageSpinner.setVisibility(View.VISIBLE);

                }
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

        btnToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomGameActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initViews() {
        btnStartCustomGame = findViewById(R.id.btnStartCustomGame);
        btnStartAIPlay = findViewById(R.id.btnStartAIPlay);
        edtTxtStepsToFinish = findViewById(R.id.edtTxtStepsToFinish);
        CardStyleSpinner = findViewById(R.id.CardStyleSpinner);
        cardStyletxt = findViewById(R.id.cardStyletxt);
        imageSpinner = findViewById(R.id.imageSpinner);
        btnToMenu = findViewById(R.id.btnCustomGameToMenu);



    }
}
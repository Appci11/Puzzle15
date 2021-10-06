package com.puzzle15;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WinScreen extends AppCompatActivity {

    TextView txtWinnScreen;

    double score;

    Button btnWinBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win_screen);



        score = SessionScore.score;

        txtWinnScreen = findViewById(R.id.txtWinnScreen);

        txtWinnScreen.setText("Game Won!!!\n" + "Score: " + String.valueOf(score) +
                "\nTurns taken: " + SessionScore.turns + "\nTime taken: " + SessionScore.time + " seconds");

        btnWinBack = findViewById(R.id.btnWinBack);

        btnWinBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(WinScreen.this, RandomGameActivity.class);
                Intent intent = new Intent(WinScreen.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
package com.puzzle15;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameGuideActivity extends MainActivity {

    Button btnToMenu;
    Button btnGameGuideToAboutPuzzle;
    Button btnGameGuideToRandomGame;
    Button btnGameGuideToCustomGame;
    Button btnGameGuideToAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_guide);

        btnToMenu = findViewById(R.id.btnGameGuideToMenu);
        btnGameGuideToAboutPuzzle = findViewById(R.id.btnGameGuideToAboutPuzzle);
        btnGameGuideToRandomGame = findViewById(R.id.btnGameGuideToRandomGame);
        btnGameGuideToCustomGame = findViewById(R.id.btnGameGuideToCustomGame);
        btnGameGuideToAccount = findViewById(R.id.btnGameGuideToAccount);
        btnGameGuideToAboutPuzzle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(GameGuideActivity.this, GameGuideAboutPuzzle.class);
                startActivity(intent);
            }
        });
        btnToMenu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(GameGuideActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnGameGuideToCustomGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(GameGuideActivity.this, GameGuideCustomGame.class);
                startActivity(intent);
            }
        });
        btnGameGuideToRandomGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(GameGuideActivity.this, GameGuideRandomGame.class);
                startActivity(intent);
            }
        });
        btnGameGuideToAccount.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(GameGuideActivity.this, GameGuideAccount.class);
                startActivity(intent);
            }
        });
    }
}
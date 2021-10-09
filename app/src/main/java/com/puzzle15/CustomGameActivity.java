package com.puzzle15;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CustomGameActivity extends AppCompatActivity {

    private Button btnStartCustomGame, btnStartAIPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_game);

        initViews();

        btnStartCustomGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
    }

    private void initViews() {
        btnStartCustomGame = findViewById(R.id.btnStartCustomGame);
        btnStartAIPlay = findViewById(R.id.btnStartAIPlay);
    }
}
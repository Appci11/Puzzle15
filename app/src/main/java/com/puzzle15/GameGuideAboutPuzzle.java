package com.puzzle15;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameGuideAboutPuzzle extends GameGuideActivity {
    Button btnToGameGuide1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_guide_about_puzzle);
        btnToGameGuide1 = findViewById(R.id.btnToGameGuide1);
        btnToGameGuide1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(GameGuideAboutPuzzle.this, GameGuideActivity.class);
                startActivity(intent);
            }
        });
    }
}
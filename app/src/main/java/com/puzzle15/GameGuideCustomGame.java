package com.puzzle15;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameGuideCustomGame extends GameGuideActivity {

    Button btnToGameGuide3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_guide_custom_game);
        btnToGameGuide3 = findViewById(R.id.btnToGameGuide3);
        btnToGameGuide3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(GameGuideCustomGame.this, GameGuideActivity.class);
                startActivity(intent);
            }
        });
    }
}
package com.puzzle15;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameGuideActivity extends MainActivity {

    Button btnToMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_guide);

        btnToMenu = findViewById(R.id.btnGameGuideToMenu);

        btnToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameGuideActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
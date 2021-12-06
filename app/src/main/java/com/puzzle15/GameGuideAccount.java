package com.puzzle15;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameGuideAccount extends GameGuideActivity {
    Button btnToGameGuide4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_guide_account);
        btnToGameGuide4 = findViewById(R.id.btnToGameGuide4);
        btnToGameGuide4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(GameGuideAccount.this, GameGuideActivity.class);
                startActivity(intent);
            }
        });
    }
}
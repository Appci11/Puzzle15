package com.puzzle15;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RandomGameActivityAI extends AppCompatActivity {

    Button btnToMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_game_ai);

        btnToMenu = findViewById(R.id.random_game_main_menu_buttonAI);

        btnToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RandomGameActivityAI.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
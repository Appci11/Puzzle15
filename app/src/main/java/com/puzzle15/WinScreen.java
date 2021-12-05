package com.puzzle15;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class WinScreen extends MainActivity {

    TextView txtWinScreen;

    double score;

    Button btnWinBack, btnReplay;

    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win_screen);


        play();

        score = SessionScore.score;

        txtWinScreen = findViewById(R.id.txtWinnScreen);
        txtWinScreen.setText("Game Won!!!\n" + "Score: " + String.valueOf(score) +
                "\nTurns taken: " + SessionScore.turns + "\nTime taken: " + SessionScore.time + " seconds");

        btnWinBack = findViewById(R.id.btnWinBack);
        btnReplay = findViewById(R.id.btnWinReplay);

        btnWinBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(WinScreen.this, RandomGameActivity.class);
                Intent intent = new Intent(WinScreen.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnReplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                if (GameParams.gameMode.compareTo("Random") == 0) {
                    intent = new Intent(WinScreen.this, RandomGameActivity.class);
                } else {
                    intent = new Intent(WinScreen.this, CustomGameActivity.class);
                }
                startActivity(intent);
            }

        });

        //Save HighScore;
        HighScoreController controller = new HighScoreController();
        controller.UpdateHighScoreList(new HighScoreData(score), getFilesDir().getAbsolutePath());

    }

    public void play() {
        player = MediaPlayer.create(this, R.raw.wineffect);
        player.start();
        player.setLooping(true);
    }

    public void onStop() {
        // Do your stuff here
        player.stop();
        super.onStop();
    }

}
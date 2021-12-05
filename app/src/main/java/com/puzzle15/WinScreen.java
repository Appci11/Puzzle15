package com.puzzle15;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Struct;
import java.util.ArrayList;


public class WinScreen extends MainActivity {

    TextView txtWinnScreen;

    double score;

    Button btnWinBack;
    MediaPlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win_screen);


        play();

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

        //Save HighScore;
        HighScoreController controller = new HighScoreController();
        controller.UpdateHighScoreList(new HighScoreData(score), getFilesDir().getAbsolutePath());

    }

    public void play()
    {
        player = MediaPlayer.create(this,R.raw.wineffect);
        player.start();
        player.setLooping(true);
    }

    public void onStop () {
        // Do your stuff here
        player.stop();
        super.onStop();
    }

}
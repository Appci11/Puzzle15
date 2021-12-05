package com.puzzle15;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.room.Room;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class WinScreen extends MainActivity {

    TextView txtWinScreen;

    int score;

    Button btnWinBack, btnReplay;

    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win_screen);

        play();

        score = (int)SessionScore.score;

        btnWinBack = findViewById(R.id.btnWinBack);
        btnReplay = findViewById(R.id.btnWinReplay);
        txtWinScreen = findViewById(R.id.txtWinnScreen);

        txtWinScreen.setText("Game Won!!!\n" + "Score: " + String.valueOf(score) +
                "\nTurns taken: " + SessionScore.turns + "\nTime taken: " + SessionScore.time + " seconds");

        if(LoginInfo.state == 1){

            AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,
                    "my_app_db").allowMainThreadQueries().build();
            Date c = Calendar.getInstance().getTime();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = formatter.format(c);

            Score taskai = new Score();
            taskai.setName(LoginInfo.name);
            taskai.setScore(score);
            taskai.setGameMode(GameParams.gameMode);
            taskai.setDate(formattedDate);
            db.scoresDAO().insert(taskai);

            Toast.makeText(WinScreen.this, "Score uploaded successfully", Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(WinScreen.this, "Login to upload score", Toast.LENGTH_SHORT).show();
        }

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
                if(GameParams.gameMode.compareTo("Random") == 0) {
                    Toast.makeText(WinScreen.this, "Replay Random Game", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(WinScreen.this, RandomGameActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(WinScreen.this, "Replay Custom Game", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(WinScreen.this, CustomGameActivity.class);
                    startActivity(intent);
                }
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
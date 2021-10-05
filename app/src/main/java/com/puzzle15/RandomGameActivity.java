package com.puzzle15;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class RandomGameActivity extends MainActivity {


    private TextView timerText;

    Timer timer;
    TimerTask timerTask;
    Double time = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_game);

        Button menu = findViewById(R.id.random_game_main_menu_button);
        Button resetGame = findViewById(R.id.ResetBtn);
        timerText = findViewById(R.id.textView_countdown);
        timer = new Timer();
        startTimer();

        menu.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent(RandomGameActivity.this, MainActivity.class);
                                        startActivity(intent);
                                    }
                                });
        resetGame.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                  Intent intent = new Intent(RandomGameActivity.this, RandomGameActivity.class);
                  startActivity(intent);
             }
        }
       );
    }
    private void startTimer() {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        time++;
                        int rounded = (int) Math.round(time);
                        int seconds = ((rounded % 86400) % 3600) % 60;
                        int minutes = ((rounded % 86400) % 3600) / 60;
                        String formated = String.format("%02d", minutes) + ":" + String.format("%02d", seconds);
                        timerText.setText(formated);
                    }
                });

            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);

    }
}
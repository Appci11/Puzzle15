package com.puzzle15;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Console;
import java.util.Timer;
import java.util.TimerTask;

public class RandomGameActivity extends MainActivity {


    private TextView timerText;

    Timer timer;
    TimerTask timerTask;
    Double time = 0.0;

    private ConstraintLayout gameTileHolder;
    private ImageView[][] gameTiles = new ImageView[4][4];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_game);

        Button menu = findViewById(R.id.random_game_main_menu_button);
        Button resetGame = findViewById(R.id.ResetBtn);

        gameTileHolder = findViewById(R.id.gameTilesHolder);

        timerText = findViewById(R.id.textView_countdown);
        timer = new Timer();
        startTimer();

        //Game board setup

        for (int row = 0; row < gameTiles.length; row++){
            for (int column = 0; column < gameTiles[0].length; column++){
                int gameTileIndex = row * (gameTiles.length)+ column;

                ImageView gameTile = (ImageView) gameTileHolder.getChildAt(gameTileIndex);

                if(gameTileIndex == 15) { //quick hack for last piece
                    int resourceIndex = getDrawableIdFromGameTileIndex(0);
                    gameTile.setImageResource(resourceIndex);
                    gameTile.setContentDescription(String.valueOf(0));

                    int finalRow = row;
                    int finalColumn = column;
                    gameTile.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View view){
                            checkBoardChanges(gameTile, finalRow, finalColumn);
                        }
                    });

                    gameTiles[row][column] = gameTile;

                    return;
                }

                int resourceIndex = getDrawableIdFromGameTileIndex(gameTileIndex + 1);



                gameTile.setImageResource(resourceIndex);
                gameTile.setContentDescription(String.valueOf(gameTileIndex + 1));

                int finalRow = row;
                int finalColumn = column;
                gameTile.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        checkBoardChanges(gameTile, finalRow, finalColumn);
                    }
                });

                gameTiles[row][column] = gameTile;
            }
        }
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
        });

    }

    private void checkBoardChanges(ImageView gameTile, int row, int column){

        Log.v("Image Clicked", String.valueOf(gameTile.getContentDescription()) + " " + row + " " + column);

        //check around the tile for empty spots

        //check left gameTiles[row][column-1].getContentDescription()=="0"

        //Log.v("Image Clicked", String.valueOf(String.valueOf(gameTiles[row][column-1].getContentDescription())));

        if(column != 0 && String.valueOf(gameTiles[row][column-1].getContentDescription()).equals("0")){
            //left is empty! Move to the left
            moveTile(gameTile, gameTiles[row][column-1]);
        } //check right
        else if (column != gameTiles[0].length - 1 && String.valueOf(gameTiles[row][column+1].getContentDescription()).equals("0")){
            moveTile(gameTile, gameTiles[row][column+1]);
        } //check up
        else if (row != 0 && String.valueOf(gameTiles[row-1][column].getContentDescription()).equals("0")){
            moveTile(gameTile, gameTiles[row-1][column]);
        } //check down
        else if (row != gameTiles.length - 1 && String.valueOf(gameTiles[row+1][column].getContentDescription()).equals("0")){
            moveTile(gameTile, gameTiles[row+1][column]);
        }

        checkWinConditions();
    }

    private void moveTile(ImageView movingTile, ImageView receivingTile){
        String description = String.valueOf(receivingTile.getContentDescription());

        receivingTile.setContentDescription(movingTile.getContentDescription());
        receivingTile.setImageResource(getDrawableIdFromGameTileIndex(Integer.parseInt(String.valueOf(movingTile.getContentDescription()))));

        movingTile.setContentDescription(description);
        movingTile.setImageResource(getDrawableIdFromGameTileIndex(Integer.parseInt(description)));

    }

    private void checkWinConditions(){
        for (int row = 0; row < gameTiles.length; row++) {
            for (int column = 0; column < gameTiles[0].length; column++) {
                int gameTileIndex = row * (gameTiles.length)+ column;
                if(gameTileIndex == 15) {
                    //win
                    Log.v("Win", "Win");
                }
                if(gameTiles[row][column].getContentDescription() != String.valueOf(gameTileIndex + 1)){
                    return;
                }
            }
        }
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


    private int getDrawableIdFromGameTileIndex(int index){
        switch (index){
            case(1): return R.drawable.nr1;
            case(2): return R.drawable.nr2;
            case(3): return R.drawable.nr3;
            case(4): return R.drawable.nr4;
            case(5): return R.drawable.nr5;
            case(6): return R.drawable.nr6;
            case(7): return R.drawable.nr7;
            case(8): return R.drawable.nr8;
            case(9): return R.drawable.nr9;
            case(10): return R.drawable.nr10;
            case(11): return R.drawable.nr11;
            case(12): return R.drawable.nr12;
            case(13): return R.drawable.nr13;
            case(14): return R.drawable.nr14;
            case(15): return R.drawable.nr15;
            case(0): return R.drawable.empty;
            default:
                throw new IllegalStateException("Unexpected value: " + index);
        }
    }
}
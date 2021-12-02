package com.puzzle15;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.io.File;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class RandomGameActivity extends MainActivity {
    private TextView timerText;
    MediaPlayer player;
    public static final String SHARED_PREFS = "musicSettings";
    private boolean effectswitchOnOff;
    Timer timer;
    TimerTask timerTask;
    Double time = 0.0;
    Double score;

    int turnCount;
    TextView txtTurnCount;
    TextView txtWinScreen;
    String message;

    int style = (int) CustomGameParams.cardStyle;
    int image = (int) CustomGameParams.pictureId;

    private ImageView progressBar; //test


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

        txtTurnCount = findViewById(R.id.txtTurnCount);
        txtWinScreen = findViewById(R.id.txtWinScreen);


        progressBar = findViewById(R.id.progressBar);//test
        player = MediaPlayer.create(this,R.raw.tile_sound);

        timer = new Timer();
        startTimer();

        turnCount = 0;
        message = "";

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RandomGameActivity.this, "Return to main menu pressed", Toast.LENGTH_SHORT).show();
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

        //Game board setup
        resetBoard();
        do {
            resetBoard();
            if (CustomGameParams.turnsToFinish == -1) {
                //randomizeBoard();
            }
        } while (!checkIfSolvable());

        if (CustomGameParams.turnsToFinish > 0) {
            Log.v("Unsolving board", "unsolve board " + CustomGameParams.turnsToFinish);
            unsolveBoard(CustomGameParams.turnsToFinish);
        }

    }

    private void unsolveBoard(int unsolveSteps) {
        try {
            for (int step = 0; step < unsolveSteps; step++) {
                boolean stepDone = false;
                Log.v("Unsolving board", "unsolve board " + step);
                for (int row = 0; row < gameTiles.length; row++) {
                    for (int column = 0; column < gameTiles[0].length; column++) {
                        int gameTileIndex = row * (gameTiles.length) + column;
                        ImageView gameTile = (ImageView) gameTileHolder.getChildAt(gameTileIndex);

                        if (gameTile.getContentDescription() == String.valueOf(0)) {
                            selectRandomTileAroundEmptyAndMove(gameTile, row, column);
                            stepDone = true;
                            break;
                        }
                    }
                    if (stepDone) {
                        break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void selectRandomTileAroundEmptyAndMove(ImageView gameTile, int row, int column) {
        Random random = new Random();
        boolean goodMoveFound = false;
        do {
            int direction = random.nextInt(4);
                switch (direction) {
                    case 0: //left
                        if (column != 0) {
                            //left has something! Move it to the empty spot
                            moveTile(gameTiles[row][column - 1], gameTile);
                            goodMoveFound = true;
                        }
                        break;
                    case 1: //right
                        if (column != gameTiles[0].length - 1) {
                            moveTile(gameTiles[row][column + 1], gameTile);
                            goodMoveFound = true;
                        }
                        break;
                    case 2: //up
                        if (row != 0) {
                            moveTile(gameTiles[row - 1][column], gameTile);
                            goodMoveFound = true;
                        }
                        break;
                    case 3: //down
                        if (row != gameTiles.length - 1) {
                            moveTile(gameTiles[row + 1][column], gameTile);
                            goodMoveFound = true;
                        }
                        break;

                    default: //wtf
                        break;
                }
        } while (!goodMoveFound);
    }

    private void randomizeBoard() {
        int i = (gameTiles.length * gameTiles[0].length) - 1;

        Random rand = new Random();

        while (i > 0) {
            int r = rand.nextInt(i--);
            moveTile((ImageView) gameTileHolder.getChildAt(i), (ImageView) gameTileHolder.getChildAt(r));
        }
    }


    private boolean checkIfSolvable() {
        int inversions = 0;

        for (int i = 0; i < (gameTiles.length * gameTiles[0].length) - 1; i++) {
            for (int j = 0; j < i; j++) {
                if (Integer.valueOf(String.valueOf(gameTileHolder.getChildAt(i).getContentDescription())) <
                        Integer.valueOf(String.valueOf(gameTileHolder.getChildAt(j).getContentDescription()))) {
                    inversions++;
                }
            }
        }

        return inversions % 2 == 0;
    }

    private void resetBoard() {
        for (int row = 0; row < gameTiles.length; row++) {
            for (int column = 0; column < gameTiles[0].length; column++) {
                int gameTileIndex = row * (gameTiles.length) + column;
                ImageView gameTile = (ImageView) gameTileHolder.getChildAt(gameTileIndex);

                if (gameTileIndex == 15) { //quick hack for last piece
                    Drawable resource = getDrawableFromGameTileIndex(0);
                    gameTile.setImageDrawable(resource);
                    gameTile.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    gameTile.setContentDescription(String.valueOf(0));

                    int finalRow = row;
                    int finalColumn = column;
                    gameTile.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            checkBoardChanges(gameTile, finalRow, finalColumn);
                        }
                    });

                    gameTiles[row][column] = gameTile;

                    return;
                }

                Drawable resource = getDrawableFromGameTileIndex(gameTileIndex + 1);
                gameTile.setImageDrawable(resource);
                gameTile.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                gameTile.setPadding(0, 0, 0, 0);
                gameTile.setContentDescription(String.valueOf(gameTileIndex + 1));

                int finalRow = row;
                int finalColumn = column;
                gameTile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        checkBoardChanges(gameTile, finalRow, finalColumn);
                    }
                });

                gameTiles[row][column] = gameTile;
            }
        }
    }

    private void checkBoardChanges(ImageView gameTile, int row, int column) {

        //Log.v("Image Clicked", String.valueOf(gameTile.getContentDescription()) + " " + row + " " + column);

        if (column != 0 && String.valueOf(gameTiles[row][column - 1].getContentDescription()).equals("0")) {
            //left is empty! Move to the left
            moveTile(gameTile, gameTiles[row][column - 1]);
        } //check right
        else if (column != gameTiles[0].length - 1 && String.valueOf(gameTiles[row][column + 1].getContentDescription()).equals("0")) {
            moveTile(gameTile, gameTiles[row][column + 1]);
        } //check up
        else if (row != 0 && String.valueOf(gameTiles[row - 1][column].getContentDescription()).equals("0")) {
            moveTile(gameTile, gameTiles[row - 1][column]);
        } //check down
        else if (row != gameTiles.length - 1 && String.valueOf(gameTiles[row + 1][column].getContentDescription()).equals("0")) {
            moveTile(gameTile, gameTiles[row + 1][column]);
        }

        checkWinConditions();

        int val = 0;

        ObjectAnimator a = ObjectAnimator.ofFloat(progressBar, "scaleX", turnCount);
        a.setDuration(1000);

        a.start();
    }

    private void moveTile(ImageView movingTile, ImageView receivingTile) {
        String description = String.valueOf(receivingTile.getContentDescription());

        receivingTile.setContentDescription(movingTile.getContentDescription());
        play();
        turnCount++;
        if (CustomGameParams.turnsToFinish != -1) {
            if (turnCount > CustomGameParams.turnsToFinish) {
                //FAIL GAME
            }
        }
        System.out.println(turnCount);
        txtTurnCount.setText(String.valueOf(turnCount));
        receivingTile.setImageDrawable(getDrawableFromGameTileIndex(Integer.parseInt(String.valueOf(movingTile.getContentDescription()))));
        receivingTile.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

        movingTile.setContentDescription(description);
        movingTile.setImageDrawable(getDrawableFromGameTileIndex(Integer.parseInt(description)));
        movingTile.setScaleType(ImageView.ScaleType.CENTER_INSIDE);


    }

    private void checkWinConditions() {
        for (int row = 0; row < gameTiles.length; row++) {
            for (int column = 0; column < gameTiles[0].length; column++) {
                int gameTileIndex = row * (gameTiles.length) + column;
                if (gameTileIndex == 15) {
                    //win
                    Log.v("Win", "Win");


                    score = (1000 - time) * (100 - turnCount);
                    message = message + "";
                    txtWinScreen.setText(String.valueOf(score));
                    txtWinScreen.setVisibility((View.VISIBLE));

                    SessionScore.score = score;
                    SessionScore.time = time;
                    SessionScore.turns = turnCount;


                    //button1.setVisibility(View.INVISIBLE);
                    Intent intent = new Intent(RandomGameActivity.this, WinScreen.class);
                    startActivity(intent);


                }
                if (gameTiles[row][column].getContentDescription() != String.valueOf(gameTileIndex + 1)) {
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


    private Drawable getDrawableFromGameTileIndex(int index) {

        int graphicType = 0;
        int imageId = 0;

        Context context = getApplicationContext();
        Resources resources = context.getResources();

/*
        String pathName = getFilesDir() + "/xxx.jpg";
        Drawable d = Drawable.createFromPath(pathName);
*/

        if (index == 0) return resources.getDrawable(R.drawable.empty, getTheme());

        String name;
        String path = getFilesDir().getAbsolutePath() + "/SavedImage/pic" + index + ".jpg";
        File file = new File(path);

        if(image != 2 ) {
            switch (style) {
                case (0):
                    name = "";
                    break;
                case (1):
                    name = "_4";
                    break;
                case (2):
                    name = "_" + (image + 1);
                    break;
                default:
                    name = "";

            }

            final int resourceId = resources.getIdentifier("nr" + index + name, "drawable",
                    context.getPackageName());
            return resources.getDrawable(resourceId, getTheme());
        }
        else {
            if(file.exists()) {
                System.out.println("Vieta: " + path);
                Drawable d = Drawable.createFromPath(path);

                return d;
            }
            else {
                final int resourceId = resources.getIdentifier("nr" + index, "drawable",
                        context.getPackageName());
                return resources.getDrawable(resourceId, getTheme());
            }
            //nu čia labai nesąmoningai padariau, reiktų kada sutvarkyt
            //debar jei neegzistuoja įkeltas custom paveikslėlis tiesiog returnina skaičius
        }

    }

    public void play()
    {
        SharedPreferences sharedPreferences = this.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        effectswitchOnOff = sharedPreferences.getBoolean("swchEffect", true);
        if (effectswitchOnOff) {
            player.start();
        }
    }
}

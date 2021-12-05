package com.puzzle15;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Highscore2Activity extends AppCompatActivity {

    RadioGroup rgScoreCount, rgGameMode;
    Button btnMyScores, btnAllScores;
    private TextView txtView;
    Button btnToMenu;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore2);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "my_app_db").allowMainThreadQueries().build();

        rgScoreCount = findViewById(R.id.rgTest2ScoresCount);
        rgGameMode = findViewById(R.id.rgTest2GameMode);

        btnMyScores = findViewById(R.id.btnTest2MyScores);
        btnAllScores = findViewById(R.id.btnTest2AllScores);
        btnToMenu = findViewById(R.id.btnTest2ToMenu);
        txtView = findViewById(R.id.txtTest2Scores);


        btnMyScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (LoginInfo.state != 1) {
                    Toast.makeText(Highscore2Activity.this, "Login to see your scores", Toast.LENGTH_SHORT).show();
                    return;
                }
                txtView.setText("");
                int scoreCount;
                switch (rgScoreCount.getCheckedRadioButtonId()) {
                    case R.id.rmTest2_5:
                        scoreCount = 5;
                        break;
                    case R.id.rmTest2_10:
                        scoreCount = 10;
                        break;
                    case R.id.rmTest2_20:
                        scoreCount = 20;
                        break;
                    default:
                        scoreCount = 3;
                }
                String gameMode = "";
                switch (rgGameMode.getCheckedRadioButtonId()) {
                    case R.id.rmTest2_Random:
                        gameMode = "Random";
                        break;
                    case R.id.rmTest2_Custom:
                        gameMode = "Custom";
                        break;
                    default:
                        System.out.println("Kazkaip sugebeta nepasirinkt game mode");
                }
                List<Score> scoresList = db.scoresDAO().getUsersNScoresMode(scoreCount, LoginInfo.name, gameMode);
                for (Score score : scoresList) {
                    String eil = String.format("%-20s %-20d %-19s\n", score.getName(), score.getScore(), score.getDate());
                    txtView.append(eil);
                }
            }
        });

        btnAllScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(Highscore2Activity.this, "Clicked All Scores", Toast.LENGTH_SHORT).show();
                txtView.setText("");
                int scoreCount;
                switch (rgScoreCount.getCheckedRadioButtonId()) {
                    case R.id.rmTest2_5:
                        scoreCount = 5;
                        break;
                    case R.id.rmTest2_10:
                        scoreCount = 10;
                        break;
                    case R.id.rmTest2_20:
                        scoreCount = 20;
                        break;
                    default:
                        scoreCount = 3;
                }
                String gameMode = "";
                switch (rgGameMode.getCheckedRadioButtonId()) {
                    case R.id.rmTest2_Random:
                        gameMode = "Random";
                        break;
                    case R.id.rmTest2_Custom:
                        gameMode = "Custom";
                        break;
                    default:
                        System.out.println("Kazkaip sugebeta nepasirinkt game mode");
                }
                List<Score> scoresList = db.scoresDAO().getTopNScoresMode(scoreCount, gameMode);
                for (Score score : scoresList) {
                    String eil = String.format("%-20s %-20d %-19s\n", score.getName(), score.getScore(), score.getDate());
                    txtView.append(eil);
                }
            }
        });

        btnToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Highscore2Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
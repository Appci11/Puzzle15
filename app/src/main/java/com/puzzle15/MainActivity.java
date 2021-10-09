package com.puzzle15;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnRandomGame, btnCustomGame, btnSettings, btnAbout;
    private ImageView imgLogo;  //Jei sumastytume pakeist keiciant "Theme"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //super.getTheme().applyStyle(R.style.Custom1, true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        btnRandomGame.setOnClickListener(this);
        btnCustomGame.setOnClickListener(this);
        btnSettings.setOnClickListener(this);
        btnAbout.setOnClickListener(this);

// pvz jei kitur reiketu
 /*       btnRandomGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RandomGameActivity.class);
                startActivity(intent);
            }
        });*/
    }

    private void initViews() {
        btnRandomGame = findViewById(R.id.btnRandomGame);
        btnCustomGame = findViewById(R.id.btnCustomGame);
        btnSettings = findViewById(R.id.btnSettings);
        btnAbout = findViewById(R.id.btnAbout);
        imgLogo = findViewById(R.id.imgLogo);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btnRandomGame:
                intent = new Intent(MainActivity.this, RandomGameActivity.class);
                break;
            case R.id.btnCustomGame:
                intent = new Intent(MainActivity.this, CustomGameActivity.class);
                break;
            case R.id.btnSettings:
                intent = new Intent(MainActivity.this, SettingsActivity.class);
                break;
            case R.id.btnAbout:
                intent = new Intent(MainActivity.this, AboutActivity.class);
                break;
            default:
                Toast.makeText(this, "Kriu", Toast.LENGTH_SHORT).show();
        }
        startActivity(intent);
    }
}
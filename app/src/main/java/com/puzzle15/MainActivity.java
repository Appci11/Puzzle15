package com.puzzle15;

import static com.puzzle15.SettingsTabSoundFragment.SHARED_PREFS;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnRandomGame, btnCustomGame, btnSettings, btnAbout, btnHighScoreView;
    private Button btnAccount, btnGameGuide;
    private ImageView imgLogo;  //Jei sumastytume pakeist keiciant "Theme"

    private Button btnTest1;

    public int language, theme;
    public static final String SHARED_PREFS = "gameSettings";
    public static final String SHARED_PREFS1 = "graphicsSettings";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //super.getTheme().applyStyle(R.style.Custom1, true);
        super.onCreate(savedInstanceState);
        loadData();
        initViews();

        btnRandomGame.setOnClickListener(this);
        btnCustomGame.setOnClickListener(this);
        btnSettings.setOnClickListener(this);
        btnAbout.setOnClickListener(this);
        btnHighScoreView.setOnClickListener(this);
        btnAccount.setOnClickListener(this);
        btnGameGuide.setOnClickListener(this);

        btnTest1.setOnClickListener(this);

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
        btnHighScoreView = findViewById(R.id.btnHighScoreView);
        btnAccount = findViewById(R.id.btnAccount);
        btnGameGuide = findViewById(R.id.btnGameGuide);

        btnTest1 = findViewById(R.id.btnToTest1);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btnRandomGame:
                CustomGameParams.turnsToFinish = -1;
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
            case R.id.btnHighScoreView:
                intent = new Intent(MainActivity.this, HighScoreViewActivity.class);
                break;
            case R.id.btnAccount:
                intent = new Intent(MainActivity.this, AccountActivity.class);
                break;
            case R.id.btnGameGuide:
                intent = new Intent(MainActivity.this, GameGuideActivity.class);
                break;
            case R.id.btnToTest1:
                intent = new Intent(MainActivity.this, Test1Activity.class);
                break;
            default:
                Toast.makeText(this, "Kriu", Toast.LENGTH_SHORT).show();
        }
        startActivity(intent);
    }

    private void setAppLocale(String localCode)
    {
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
        {
            conf.setLocale(new Locale(localCode.toLowerCase()));
        }
        else{
            conf.locale = new Locale ((localCode.toLowerCase()));
        }
        res.updateConfiguration(conf,dm);
    }

    public void loadData() {
        SharedPreferences sharedPreferences = this.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences sharedPreferences1 = this.getSharedPreferences(SHARED_PREFS1, MODE_PRIVATE);
        language = (int) sharedPreferences.getInt("language", 0);
        theme = (int) sharedPreferences1.getInt("theme", 0);
        setTheme();
        setLang();
    }
    public void setLang() {

        if (language == 0)
        {
            setAppLocale("en");

        }
        else if (language == 1)
        {
            setAppLocale("lt");
        }
        setContentView(R.layout.activity_main);
    }

    public void setTheme(){
        switch(theme){
            case 0:
                super.getTheme().applyStyle(R.style.Theme_Green, true);
                break;
            case 1:
                super.getTheme().applyStyle(R.style.Theme_Red, true);
                break;
            case 2:
                super.getTheme().applyStyle(R.style.Theme_Blue, true);
                break;
            default:
                Toast.makeText(this, "Theme not changed.", Toast.LENGTH_SHORT).show();
        }

    }
}
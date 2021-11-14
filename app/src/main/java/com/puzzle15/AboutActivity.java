package com.puzzle15;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AboutActivity extends MainActivity {

    Button btnToMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //super.getTheme().applyStyle(R.style.Custom1, true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        btnToMenu = findViewById(R.id.btnAboutToMenu);

        btnToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AboutActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
package com.puzzle15;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.room.Room;

import java.util.List;
import java.util.Observer;

public class Test1Activity extends MainActivity {

    Button btnToMenu;
    Button btnBtn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);

        btnToMenu = findViewById(R.id.btnTest1ToMenu);
        btnBtn1 = findViewById(R.id.btnTest1Btn1);




        btnToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Test1Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
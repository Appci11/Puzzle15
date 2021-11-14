package com.puzzle15;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class HighScoreViewActivity extends MainActivity {

    Button btnToMenu;

    private TableLayout tableHighScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score_view);

        initViews();

        HighScoreController controller = new HighScoreController();
        ArrayList<HighScoreData> highScoreData = controller.LoadHighScore(getFilesDir().getAbsolutePath());

        tableHighScore.setBackgroundColor(Color.rgb(240,240,240));
        tableHighScore.setStretchAllColumns(true);

        TableRow tr0 = new TableRow(this);
        tr0.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

        TextView columsView = new TextView(getApplicationContext());
        columsView.setGravity(Gravity.CENTER);
        columsView.setText(getResources().getString(R.string.date));
        columsView.setTextSize(18);
        columsView.setPadding(20,20,20,20);
        columsView.setTypeface(null, Typeface.BOLD);

        tr0.addView(columsView);

        TextView columsView2 = new TextView(getApplicationContext());
        columsView.setGravity(Gravity.CENTER);

        columsView2.setText(getResources().getString(R.string.result));
        columsView2.setTextSize(18);
        columsView2.setPadding(20,20,20,20);
        columsView2.setTypeface(null, Typeface.BOLD);


        tr0.addView(columsView2);


        tableHighScore.addView(tr0);


        for(HighScoreData data : highScoreData) {
            TableRow tr = new TableRow(this);
            tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

            TextView columsView3 = new TextView(getApplicationContext());
            columsView3.setGravity(Gravity.CENTER);
            columsView3.setText(data.dateTime);
            columsView3.setTextSize(16);
            tr.addView(columsView3);

            TextView columsView4 = new TextView(getApplicationContext());
            columsView4.setGravity(Gravity.CENTER);

            columsView4.setText(String.valueOf(data.score));
            columsView4.setTextSize(16);
            tr.addView(columsView4);


            tableHighScore.addView(tr);

            btnToMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(HighScoreViewActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });
        }

    }

    private void initViews() {
        tableHighScore = findViewById(R.id.tableHighScore);
        btnToMenu = findViewById(R.id.btnHighScoreToMenu);

    }
}
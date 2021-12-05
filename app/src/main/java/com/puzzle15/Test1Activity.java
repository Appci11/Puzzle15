package com.puzzle15;

import androidx.room.Room;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Test1Activity extends MainActivity {

    private AppDatabase db;
    private TextView personsListTextView;
    private Button button, button2;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText phoneNumberEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);

        //db = AppActivity.getDatabase();
        //db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "my_app_db").build();
        //nerekomenduojama(dirbant tik ant main thread, galima viska pakabint ilgam laikui), bet galima ir taip
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "my_app_db").allowMainThreadQueries().build();

        personsListTextView = (TextView) findViewById(R.id.txt_list);
        firstNameEditText = (EditText) findViewById(R.id.txtTest1Name);
        lastNameEditText = (EditText) findViewById(R.id.txtTest1Score);
        phoneNumberEditText = (EditText) findViewById(R.id.txtTest1GameMode);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //String name = "Vardas2";
                //int score = 11000;
                //String gameMode = "Random";

                String name = firstNameEditText.getText().toString().trim();
                int score = Integer.parseInt(lastNameEditText.getText().toString().trim());
                String gameMode = phoneNumberEditText.getText().toString().trim();

                Date c = Calendar.getInstance().getTime();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formattedDate = formatter.format(c);

                Score taskai = new Score();
                taskai.setName(name);
                taskai.setScore(score);
                taskai.setGameMode(gameMode);
                taskai.setDate(formattedDate);
                db.scoresDAO().insert(taskai);

//                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getScoresInList();
            }
        });
    }

    private void getScoresInList() {
        personsListTextView.setText("");
        List<Score> scoresList = db.scoresDAO().getTopNScores(20);
        //List<Score> scoresList = db.scoresDAO().getUsersNScores(20, "aaa");
        //List<Score> scoresList = db.scoresDAO().getUsersNScoresMode(20, "Vardas2", "Random");
        for (Score score : scoresList) {
            String eil = String.format("%-3d %-8s %-8d %-8s %-19s\n",score.getId(), score.getName(), score.getScore(), score.getGameMode(), score.getDate());
            personsListTextView.append(eil);
        }
    }
    //nebereikalinga
    private void getAccountsInList() {
        personsListTextView.setText("");
        List<Account> accountList = db.accountDAO().getAllPersons();
        for (Account account : accountList) {
            personsListTextView.append(account.getId() + " "
                    + account.getName() + " "
                    + account.getSurname() + " : "
                    + account.getPhoneNumber());
            personsListTextView.append("\n");
        }
    }
}
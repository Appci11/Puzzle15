package com.puzzle15;

import androidx.room.Room;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Test1Activity extends MainActivity {

    private AppDatabase db;
    private TextView personsListTextView;
    private Button button;
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
        lastNameEditText = (EditText) findViewById(R.id.txtTest1Email);
        phoneNumberEditText = (EditText) findViewById(R.id.txtTest1Password);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = firstNameEditText.getText().toString().trim();
                String surname = lastNameEditText.getText().toString().trim();
                String phoneNumber = phoneNumberEditText.getText().toString().trim();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(surname)
                        || TextUtils.isEmpty(phoneNumber)) {
                    Toast.makeText(getApplicationContext(),
                            "Name/Surname/Phone Number should not be empty",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Account account = new Account();
                    account.setName(name);
                    account.setSurname(surname);
                    account.setPhoneNumber(phoneNumber);
                    //reiketu sitaip
                    //AsyncTask.execute(() -> db.personDAO().insert(person));
                    db.accountDAO().insert(account);
                    Toast.makeText(
                            getApplicationContext(),
                            "Saved successfully",
                            Toast.LENGTH_SHORT
                    ).show();
                    firstNameEditText.setText("");
                    lastNameEditText.setText("");
                    phoneNumberEditText.setText("");
                    firstNameEditText.requestFocus();
                    getDatabaseInList();
                }
            }
        });
    }
    private void getDatabaseInList() {
        personsListTextView.setText("");
        List<Account> accountList = db.accountDAO().getAllPersons();
        for (Account account : accountList) {
            personsListTextView.append(account.getName() + " " +
                    account.getSurname() + " : " + account.getPhoneNumber());
            personsListTextView.append("\n");
        }
    }
}
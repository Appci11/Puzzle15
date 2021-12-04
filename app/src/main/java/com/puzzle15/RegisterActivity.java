package com.puzzle15;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.room.Room;

public class RegisterActivity extends MainActivity {

    AppDatabase db;

    Button btnToMenu, btnRegister, btnToLogin;
    EditText edtTxtName, edtTxtEmail, edtTxtPassword, edtTxtPassword2;
    CheckBox agreementCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //db = AppActivity.getDatabase();   //nenori veikt per static klase
        //db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "my_app_db").build();

        //nerekomenduojama(dirbant tik ant main thread, galima viska pakabint ilgam laikui), bet nesudetingam appsui sueis
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "my_app_db").allowMainThreadQueries().build();

        btnRegister = findViewById(R.id.btnRegisterRegister);
        btnToLogin = findViewById(R.id.btnRegisterToLogin);
        btnToMenu = findViewById(R.id.btnRegisterToMenu);

        edtTxtName = findViewById(R.id.txtRegisterName);
        edtTxtEmail = findViewById(R.id.txtRegisterEmail);
        edtTxtPassword = findViewById(R.id.txtRegisterPassword);
        edtTxtPassword2 = findViewById(R.id.txtRegisterRepeatPassword);

        agreementCheck = findViewById(R.id.chkBoxRegisterAgreement);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(RegisterActivity.this, "Clicked Register", Toast.LENGTH_SHORT).show();
                String name = edtTxtName.getText().toString().trim();
                String email = edtTxtEmail.getText().toString().trim();
                String pass = edtTxtPassword.getText().toString().trim();
                String pass2 = edtTxtPassword2.getText().toString().trim();

//                if(!agreementCheck.isChecked()){
//                    Toast.makeText(getApplicationContext(), "Please agree to EULA", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (TextUtils.isEmpty(name)
//                        || TextUtils.isEmpty(email)
//                        || TextUtils.isEmpty(pass)
//                        || TextUtils.isEmpty(pass2)) {
//                Toast.makeText(getApplicationContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if(pass.compareTo(pass2) != 0){
//                    Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if(db.accountDAO().exists(name) == 1){  //if already exists
//                    Toast.makeText(getApplicationContext(), "User already exists", Toast.LENGTH_SHORT).show();
//                }


                Account account = new Account();
                account.setName(name);
                account.setSurname(email);
                account.setPhoneNumber(pass);

                //reiketu sitaip
                //AsyncTask.execute(() -> db.personDAO().insert(person));
                db.accountDAO().insert(account);
                Toast.makeText(getApplicationContext(), "Successfully registered!", Toast.LENGTH_SHORT).show();
            }


        });

        btnToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, AccountActivity.class);
                startActivity(intent);
            }
        });

        btnToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
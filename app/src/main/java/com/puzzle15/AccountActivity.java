package com.puzzle15;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AccountActivity extends MainActivity {

    Button btnToMenu, btnLogin,btnLogout, btnRegister;
    EditText edtTxtName, edtTxtPassword ;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        btnLogin = findViewById(R.id.btnAccountLogin);
        btnLogout = findViewById(R.id.btnAccountLogout);
        btnToMenu = findViewById(R.id.btnAccountToMenu);
        btnRegister = findViewById(R.id.btnAccountRegister);
        edtTxtName = findViewById(R.id.txtAccountName);
        edtTxtPassword = findViewById(R.id.txtAccountPassword);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "my_app_db").allowMainThreadQueries().build();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(AccountActivity.this, "Clicked Login", Toast.LENGTH_SHORT).show();
                String name = edtTxtName.getText().toString().trim();
                String pass = edtTxtPassword.getText().toString().trim();
                if (TextUtils.isEmpty(name)
                        || TextUtils.isEmpty(pass)) {
                Toast.makeText(getApplicationContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                int check = db.accountDAO().tryLogin(name, pass);
                if(check != 1){
                    Toast.makeText(getApplicationContext(), "Wrong user name or password", Toast.LENGTH_SHORT).show();
                }
                else{
                    LoginInfo.state = 1;
                    LoginInfo.name = name;
                    Toast.makeText(getApplicationContext(), "Logged in as: " + name, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AccountActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginInfo.state = 0;
                LoginInfo.name = "";
                Toast.makeText(AccountActivity.this, "Logged out", Toast.LENGTH_SHORT).show();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btnToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
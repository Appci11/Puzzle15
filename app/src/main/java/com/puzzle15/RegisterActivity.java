package com.puzzle15;

import androidx.appcompat.app.AppCompatActivity;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends MainActivity {


    Button btnToMenu, btnRegister, btnToLogin;
    EditText edtTxtName, edtTxtEmail, edtTxtPassword, edtTxtPassRepeat;
    private CheckBox agreementCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnRegister = findViewById(R.id.btnRegisterRegister);
        btnToLogin = findViewById(R.id.btnRegisterToLogin);
        btnToMenu = findViewById(R.id.btnRegisterToMenu);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RegisterActivity.this, "Clicked Register", Toast.LENGTH_SHORT).show();
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
package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private EditText etEmail, etUsername, etPwd, etFullname;
    private Button btnRegister, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(back);
                finish();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String username = etUsername.getText().toString();
                String password = etPwd.getText().toString();
                String fullname = etFullname.getText().toString();
                if (email.equals("") || username.equals("") || password.equals("") || fullname.equals(""))
                    {
                    Toast.makeText(RegisterActivity.this, "Minden mezőt ki kell tölteni!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Boolean result = databaseHelper.Insert(email, username, password, fullname);
                    if (result)
                    {
                        Toast.makeText(RegisterActivity.this, "Regisztráció sikeres!", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(RegisterActivity.this, "Regisztráció sikertelen!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void init()
    {
        databaseHelper = new DatabaseHelper(this);
        etEmail = findViewById(R.id.etEmail2);
        etUsername = findViewById(R.id.etUsername2);
        etPwd = findViewById(R.id.etPwd2);
        etFullname = findViewById(R.id.etFullName2);
        btnBack = findViewById(R.id.btnBack2);
        btnRegister = findViewById(R.id.btnRegister2);
    }
}

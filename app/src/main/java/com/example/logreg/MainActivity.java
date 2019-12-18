package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private EditText etUsername, etPwd;
    private Button btnLogin, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(register);
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String password = etPwd.getText().toString();
                String fullname = null;
                if (username.equals("") || password.equals(""))
                {
                    Toast.makeText(MainActivity.this, "Minden mezőt ki kell tölteni!", Toast.LENGTH_SHORT).show();
                }
                else
                    {
                        Cursor eredmeny = databaseHelper.Select(username, password);
                        StringBuffer stringBuffer = new StringBuffer();
                        if (eredmeny!= null && eredmeny.getCount() >0){
                            while (eredmeny.moveToNext())
                            {
                                fullname = String.valueOf(stringBuffer.append(eredmeny.getString(4)));
                            }
                            Intent login = new Intent(MainActivity.this, LoggedInActivity.class);
                            SharedPreferences shareddata = getSharedPreferences("data", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editshareddata = shareddata.edit();
                            editshareddata.putString("name", fullname);
                            editshareddata.apply();
                            startActivity(login);
                            finish();
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "Hibás adatok!", Toast.LENGTH_SHORT).show();
                        }
                    }
            }
        });
    }

    public void init()
    {
        databaseHelper = new DatabaseHelper(this);
        etUsername = findViewById(R.id.etUsername);
        etPwd = findViewById(R.id.etPwd);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
    }
}

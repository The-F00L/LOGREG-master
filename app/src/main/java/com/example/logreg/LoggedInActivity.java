package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoggedInActivity extends AppCompatActivity {


    private TextView tvLoggedIn;
    private Button btnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);
        init();
        SharedPreferences shareddata = getSharedPreferences("data", Context.MODE_PRIVATE);
        String helper = "";
        helper = shareddata.getString("name", "");
        tvLoggedIn.setText(helper);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logout = new Intent(LoggedInActivity.this, MainActivity.class);
                startActivity(logout);
                finish();
            }
        });

    }

    public void init()
    {
        tvLoggedIn = findViewById(R.id.tvLoggedIn);
        btnLogout = findViewById(R.id.btnLogout);
    }
}

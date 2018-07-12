package com.example.rakshitayadav.database;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class User_Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_user);

        String id = getIntent().getStringExtra("ID");
        TextView Id = findViewById(R.id.id_show);
        Id.setText(id);

        String password = getIntent().getStringExtra("Password");
        TextView Pword = findViewById(R.id.password_show);
        Pword.setText(password);

    }





}

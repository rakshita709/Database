package com.example.rakshitayadav.database;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void UserAccountAction(View view)
    {
        if(view.getId() == R.id.Blogin)
        {
            String id = ((EditText)findViewById(R.id.TVid)).getText().toString();
            String pass = ((EditText)findViewById(R.id.TVpass)).getText().toString();

           String pass_check =  helper.searchPass(id);

           if(pass.equals(pass_check))
           {
               Intent intent = new Intent();
               intent.setClass(MainActivity.this,User_Login.class);
               intent.putExtra("ID",id);
               intent.putExtra("Password",pass);
               startActivity(intent);
           }
           else
           {
               Toast.makeText(MainActivity.this,"ID AND PASSWORDS DON'T MATCH!",Toast.LENGTH_LONG).show();
           }
        }

        else if(view.getId() == R.id.Bsignup)
        {

            Intent intent = new Intent();
            intent.setClass(MainActivity.this,User_Sign_Up.class);
            startActivity(intent);
        }

    }

}

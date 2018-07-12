package com.example.rakshitayadav.database;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class User_Sign_Up extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__sign__up);


    }

    public void CreateUser(View view)
    {
        if(view.getId() == R.id.Bcreateuser)
        {
            String username = ((EditText)findViewById(R.id.user_name)).getText().toString();
            String email = ((EditText)findViewById(R.id.user_email)).getText().toString();
/*
            String id = ((EditText)findViewById(R.id.user_id)).getText().toString();
*/
            String pass1 = ((EditText)findViewById(R.id.user_pass1)).getText().toString();
            String pass2 = ((EditText)findViewById(R.id.user_pass2)).getText().toString();

            if(!pass1.equals(pass2))
            {
                Toast.makeText(User_Sign_Up.this,"Passwords don't match!", Toast.LENGTH_LONG).show();
            }
            else
            {
                //insert details in database!
                Contacts c = new Contacts();
                c.setUsername(username);
                c.setEmail(email);
/*
                c.setId(id);
*/
                c.setPassword(pass1);

                helper.insertContact(c);

            }

        }
    }
}

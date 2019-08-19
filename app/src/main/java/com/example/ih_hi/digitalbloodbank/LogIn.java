package com.example.ih_hi.digitalbloodbank;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LogIn extends Activity {

    private Button login;
    private EditText user,password;
    private TextView signUp,aboutApp,privacy,help;
    private DatabaseHelper helper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        getActionBar().setTitle("Log In");

        login = (Button)findViewById(R.id.logInBTN);
        user = (EditText)findViewById(R.id.userIdET);
        password = (EditText)findViewById(R.id.passET);
        signUp = (TextView)findViewById(R.id.reqForRegTV);
        aboutApp =(TextView)findViewById(R.id.aboutAppTV);
        privacy =(TextView)findViewById(R.id.privacyTV);
        help =(TextView)findViewById(R.id.helpTV);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String givenEmail = user.getText().toString();
                String pass = password.getText().toString();
                String rpass = helper.searchPassword(givenEmail);

                if(pass.equals(rpass))
                {
                    int ID = helper.getID();
                    Intent i = new Intent(LogIn.this,Home.class);
                    Bundle data = new Bundle();
                    data.putInt("USER_ID",ID);
                    i.putExtra("USER_DATA",data);
                    finish();
                    startActivity(i);
                }
                else
                {
                    Toast msg = Toast.makeText(LogIn.this,"User Name and Password Don't Match !",Toast.LENGTH_SHORT);
                    msg.show();
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LogIn.this,Registration.class));
            }
        });

        aboutApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LogIn.this,AboutApp.class));
            }
        });
        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LogIn.this,Privacy.class));
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LogIn.this,Help.class));
            }
        });
    }
}

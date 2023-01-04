package com.example.loginregister1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private Button login, register;
    private EditText etNIM, etPass;
    private DbHelper db;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DbHelper(this);
        session = new Session(this);
        login = findViewById(R.id.btnLogin);
        register = findViewById(R.id.btnReg);
        etNIM = findViewById(R.id.etNIM);
        etPass = findViewById(R.id.etPass);
        login.setOnClickListener(this);
        register.setOnClickListener(this);

        if(session.loggedin()){
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnLogin:
                login();
                break;
            case R.id.btnReg:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;
            default:

        }
    }

    private void login(){
        String nim = etNIM.getText().toString();
        String pass = etPass.getText().toString();

        if(db.getUser(nim,pass)){
            session.setLoggedin(true);
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }else{
            Toast.makeText(getApplicationContext(), "Wrong NIM/Password!",Toast.LENGTH_SHORT).show();
        }
    }
}
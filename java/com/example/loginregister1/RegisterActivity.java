package com.example.loginregister1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private Button reg;
    private TextView tvLogin;
    private EditText etNIM, etPass;
    private DbHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DbHelper(this);
        reg = findViewById(R.id.btnReg);
        tvLogin = findViewById(R.id.tvLogin);
        etNIM = findViewById(R.id.etNIM);
        etPass = findViewById(R.id.etPass);
        reg.setOnClickListener(this);
        tvLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnReg:
                register();
                break;
            case R.id.tvLogin:
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                finish();
                break;
            default:

        }
    }

    private void register(){
        String nim = etNIM.getText().toString();
        String pass = etPass.getText().toString();
        if(nim.isEmpty() && pass.isEmpty()){
            displayToast("NIM/password field empty");
        }else{
            db.addUser(nim,pass);
            displayToast("User registered");
            finish();
        }
    }

    private void displayToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
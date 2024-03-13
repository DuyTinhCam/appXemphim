package com.example.app_rapchieuphim.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_rapchieuphim.R;

public class LoginActivity extends AppCompatActivity {
    private EditText UserName, PassWord;
    private Button Login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        UserName = findViewById(R.id.UserName);
        PassWord = findViewById(R.id.PassWord);
        Login = findViewById(R.id.Login);
        Login.setOnClickListener(v -> {
            if(UserName.getText().toString().isEmpty() || PassWord.getText().toString().isEmpty()){
                Toast.makeText(LoginActivity.this,"Vui lòng nhập Tài khoản và Mật khẩu",Toast.LENGTH_SHORT).show();
            }else if(UserName.getText().toString().equals("phuong duy") && PassWord.getText().toString().equals("1234")){
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
            }
        });
    }
}
package com.example.foodapp.view.start;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.foodapp.R;

import java.time.LocalDate;

public class Login extends AppCompatActivity {

    Button btnDangKy, btnQMK, btnDangNhap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnDangNhap = findViewById(R.id.btnDangNhapLog);
        btnQMK = findViewById(R.id.btnQMKLog);
        btnDangKy = findViewById(R.id.btnDangKyLog);

        //Nav qua view đăng ký
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dangKi = new Intent(Login.this,SingupActivity.class);
               startActivity(dangKi);
            }
        });

        //Nav qua view Quên Pass
        btnQMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent quenmk = new Intent(Login.this, ForgetPassword.class);
                startActivity(quenmk);
            }
        });


    }
}
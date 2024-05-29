package com.example.foodapp.view.start;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.foodapp.R;
public class SingupActivity extends AppCompatActivity {

    Button btnDangKy, btnDangNhap, btnQuenMK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);

        btnDangKy = findViewById(R.id.btnDangKyDki);
        btnDangNhap = findViewById(R.id.btnDangNhapDki);
        btnQuenMK = findViewById(R.id.btnQuenMKDki);

        //Nav qua view Đăng Nhập
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dangnhapview = new Intent(SingupActivity.this, Login.class);
                startActivity(dangnhapview);
            }
        });

        //Nav qua view QMK
        btnQuenMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent qmkview = new Intent(SingupActivity.this, ForgetPassword.class);
                startActivity(qmkview);
            }
        });
    }
}
package com.example.foodapp.view.start;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.foodapp.R;
public class ForgetPassword extends AppCompatActivity {

    Button btnXacNhan, btnDangKy, btnDangNhap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        btnDangKy = findViewById(R.id.btnDangKyQMK);
        btnDangNhap = findViewById(R.id.btnDangNhapQMK);
        btnXacNhan = findViewById(R.id.btnXacNhanQMK);

        //Nav qua view đăng kí
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dangkyview = new Intent(ForgetPassword.this,SingupActivity.class);
                startActivity(dangkyview);
            }
        });

        //Nav qua view đăng nhập
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dangnhapview = new Intent(ForgetPassword.this,Login.class);
                startActivity(dangnhapview);
            }
        });


    }
}
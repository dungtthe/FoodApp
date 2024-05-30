package com.example.foodapp.view.main_view.setting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodapp.R;
import com.example.foodapp.model.DA.KhachHangDA;
import com.example.foodapp.model.DTO.KhachHangDTO;
import com.example.foodapp.model.DTO.DataCurrent;

import java.util.List;


public class activity_changepassword extends AppCompatActivity {
    private Button btnHoanThanh;
    private EditText edtMkcu, edtMkmoi, edtXacnhanmk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);

        btnHoanThanh = findViewById(R.id.btn_XacNhanMK);
        edtMkcu = findViewById(R.id.et_matkhaucu);
        edtMkmoi = findViewById(R.id.et_matkhaumoi);
        edtXacnhanmk = findViewById(R.id.et_nhaplaimatkhaumoi);

        btnHoanThanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentPassword = edtMkcu.getText().toString().trim();
                String newPassword = edtMkmoi.getText().toString().trim();
                String confirmPassword = edtXacnhanmk.getText().toString().trim();

                if (validatePasswordInput(currentPassword, newPassword, confirmPassword)) {
                    changePassword(newPassword, currentPassword);
                }
            }
        });
    }

    private boolean validatePasswordInput(String currentPassword, String newPassword, String confirmPassword) {
        if (currentPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!newPassword.equals(confirmPassword)) {
            Toast.makeText(this, "Mật khẩu mới và mật khẩu xác nhận không khớp", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void changePassword(String currentPassword, String newPassword) {
        String tenTaiKhoan = DataCurrent.khachHangDTOCur.gettenTaiKhoan();

        new KhachHangDA(new KhachHangDA.DatabaseCallback() {
            @Override
            public void onQueryExecuted(String query, List<KhachHangDTO> result, boolean isSuccess) {
                if (isSuccess) {
                    Toast.makeText(activity_changepassword.this, "Đổi mật khẩu thành công!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(activity_changepassword.this, "Đổi mật khẩu thất bại!" +tenTaiKhoan, Toast.LENGTH_SHORT).show();
                }
            }
        }, this).changePassword(tenTaiKhoan, currentPassword, newPassword);
    }
}
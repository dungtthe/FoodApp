package com.example.foodapp.view.start;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.foodapp.R;
import com.example.foodapp.model.DA.KhachHangDA;
import com.example.foodapp.model.DA.QueryParameter;
import com.example.foodapp.model.DTO.DataCurrent;
import com.example.foodapp.model.DTO.KhachHangDTO;
import com.example.foodapp.view.main_view.MainViewActivity;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {

    Button btnDangKy, btnQMK, btnDangNhap;
    EditText edtTenDangNhap, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnDangNhap = findViewById(R.id.btnDangNhapLog);
        btnQMK = findViewById(R.id.btnQMKLog);
        btnDangKy = findViewById(R.id.btnDangKyLog);
        edtTenDangNhap = findViewById(R.id.edtTenDangNhapLog);
        edtPassword = findViewById(R.id.edtPasswordLog);

        // Nav qua view đăng ký
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dangKi = new Intent(Login.this, SingupActivity.class);
                startActivity(dangKi);
            }
        });

        // Nav qua view Quên Pass
        btnQMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent quenmk = new Intent(Login.this, ForgetPassword.class);
                startActivity(quenmk);
            }
        });

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenTaiKhoan = edtTenDangNhap.getText().toString().trim();
                String matKhau = edtPassword.getText().toString().trim();
                login(tenTaiKhoan, matKhau, Login.this);
            }
        });
    }

    public void login(String tenTaiKhoan, String matKhau, Context context) {
        String query = "SELECT * FROM KhachHang WHERE TenTaiKhoan = ? AND MatKhau = ?";
        List<QueryParameter> parameters = new ArrayList<>();
        parameters.add(new QueryParameter(1, tenTaiKhoan));
        parameters.add(new QueryParameter(2, matKhau));


        Object[] params = new Object[parameters.size() + 1];
        params[0] = query;
        for (int i = 0; i < parameters.size(); i++) {
            params[i + 1] = parameters.get(i);
        }



        KhachHangDA khachHangDA = new KhachHangDA(new KhachHangDA.DatabaseCallback() {
            @Override
            public void onQueryExecuted(String query, List<KhachHangDTO> result, boolean isSuccess) {

                if (isSuccess && !result.isEmpty()) {
                    KhachHangDTO khachHangDTO = result.get(0);
                    Toast.makeText(context, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();

                    DataCurrent.khachHangDTOCur=khachHangDTO;
                    DataCurrent.danhSachSanPhamCoTrongGioHang=new ArrayList<>();

                    Intent intent = new Intent(Login.this, MainViewActivity.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(context, "Thông tin tài khoản hoặc mật khẩu không chính xác!", Toast.LENGTH_SHORT).show();
                }
            }
        }, context);

        khachHangDA.execute(params);
    }

}

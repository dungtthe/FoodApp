package com.example.foodapp.view.start;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodapp.R;
import com.example.foodapp.model.DA.KhachHangDA;
import com.example.foodapp.model.DA.QueryParameter;
import com.example.foodapp.model.DTO.KhachHangDTO;
import com.example.foodapp.view.main_view.MainViewActivity;

import java.util.ArrayList;
import java.util.List;

public class SingupActivity extends AppCompatActivity {

    Button btnDangKy, btnDangNhap, btnQuenMK;
    EditText edtHoTen, edtSDT, edtEmail, edtTenDangNhap, edtMatKhau, edtMatKhau2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);

        btnDangKy = findViewById(R.id.btnDangKyDki);
        btnDangNhap = findViewById(R.id.btnDangNhapDki);
        btnQuenMK = findViewById(R.id.btnQuenMKDki);
        edtHoTen = findViewById(R.id.edtHoTenDki);
        edtSDT = findViewById(R.id.edtSDTDki);
        edtEmail = findViewById(R.id.edtEmailDki);
        edtTenDangNhap = findViewById(R.id.edtTenDangNhapDki);
        edtMatKhau = findViewById(R.id.edtMK1Dki);
        edtMatKhau2 = findViewById(R.id.edtMK2Dki);

        // Nav qua view Đăng Nhập
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dangnhapview = new Intent(SingupActivity.this, Login.class);
                startActivity(dangnhapview);
            }
        });

        // Nav qua view QMK
        btnQuenMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent qmkview = new Intent(SingupActivity.this, ForgetPassword.class);
                startActivity(qmkview);
            }
        });

        // Đăng kí tài khoản
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hoTen = edtHoTen.getText().toString().trim();
                String sDT = edtSDT.getText().toString().trim();
                String eMail = edtEmail.getText().toString().trim();
                String tenDangNhap = edtTenDangNhap.getText().toString().trim();
                String matKhau = edtMatKhau.getText().toString().trim();
                String matKhau2 = edtMatKhau2.getText().toString().trim();
                Boolean daxoa = false;

                // Kiểm tra xem các trường thông tin có trống không
                if (hoTen.isEmpty() || sDT.isEmpty() || eMail.isEmpty() || tenDangNhap.isEmpty() || matKhau.isEmpty()) {
                    Toast.makeText(SingupActivity.this, "Thông tin không đầy đủ", Toast.LENGTH_LONG).show();
                    return;
                }

                // Kiểm tra email có chứa ký tự '@'
                if (!eMail.contains("@")) {
                    Toast.makeText(SingupActivity.this, "Email không hợp lệ, vui lòng nhập lại", Toast.LENGTH_SHORT).show();
                    edtEmail.requestFocus();
                    edtEmail.selectAll();
                    return;
                }

                // Kiểm tra mật khẩu có khớp
                if (!matKhau.equals(matKhau2)) {
                    Toast.makeText(SingupActivity.this, "Mật khẩu không khớp!", Toast.LENGTH_SHORT).show();
                    edtMatKhau2.requestFocus();
                    edtMatKhau2.selectAll();
                    return;
                }

                // Kiểm tra xem tên đăng nhập đã tồn tại chưa
                checkUsernameExists(tenDangNhap, hoTen, sDT, eMail, matKhau, false, SingupActivity.this);
            }
        });
    }

    // Phương thức kiểm tra tên đăng nhập có tồn tại hay không
    private void checkUsernameExists(String username, String hoTen, String sDT, String eMail, String matKhau, Boolean daxoa, Context context) {
        String query = "SELECT COUNT(*) AS count FROM KhachHang WHERE TenTaiKhoan = ?";
        List<QueryParameter> parameters = new ArrayList<>();
        parameters.add(new QueryParameter(1, username));

        Object[] params = new Object[parameters.size() + 1];
        params[0] = query;
        for (int i = 0; i < parameters.size(); i++) {
            params[i + 1] = parameters.get(i);
        }

        KhachHangDA khachHangDA = new KhachHangDA(new KhachHangDA.DatabaseCallback() {
            @Override
            public void onQueryExecuted(String query, List<KhachHangDTO> result, boolean isSuccess) {
                if (isSuccess && !result.isEmpty()) {
                    int count = Integer.parseInt(result.get(0).getHoTen());
                    if (count > 0) {
                        Toast.makeText(context, "Tên đăng nhập đã tồn tại, vui lòng chọn tên khác", Toast.LENGTH_SHORT).show();
                        edtTenDangNhap.requestFocus();
                        edtTenDangNhap.selectAll();
                    } else {
                        registerCustomer(hoTen, sDT, eMail, username, matKhau,daxoa, context);
                    }
                } else {
                    Toast.makeText(context, "Có lỗi xảy ra, vui lòng thử lại", Toast.LENGTH_SHORT).show();
                }
            }
        }, context);

        khachHangDA.execute(params);
    }


    // Phương thức thực hiện đăng ký khách hàng
    private void registerCustomer(String hoTen, String sDT, String eMail, String tenDangNhap, String matKhau, Boolean daxoa, Context context) {
        String query = "INSERT INTO KhachHang (HoTen, SoDienThoai, EMail, DaXoa, TenTaiKhoan, MatKhau) VALUES (?, ?, ?, ?, ?, ?)";
        List<QueryParameter> parameters = new ArrayList<>();
        parameters.add(new QueryParameter(1, hoTen));
        parameters.add(new QueryParameter(2, sDT));
        parameters.add(new QueryParameter(3, eMail));
        parameters.add(new QueryParameter(4, daxoa));
        parameters.add(new QueryParameter(5, tenDangNhap));
        parameters.add(new QueryParameter(6, matKhau));

        Object[] params = new Object[parameters.size() + 1];
        params[0] = query;
        for (int i = 0; i < parameters.size(); i++) {
            params[i + 1] = parameters.get(i);
        }

        KhachHangDA khachHangDA = new KhachHangDA(new KhachHangDA.DatabaseCallback() {
            @Override
            public void onQueryExecuted(String query, List<KhachHangDTO> result, boolean isSuccess) {
                if (isSuccess) {
                    Toast.makeText(context, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SingupActivity.this, Login.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(context, "Có lỗi xảy ra, vui lòng thử lại", Toast.LENGTH_SHORT).show();
                }
            }
        }, context);
        khachHangDA.execute(params);
    }
}





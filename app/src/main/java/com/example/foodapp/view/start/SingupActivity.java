package com.example.foodapp.view.start;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodapp.R;
import com.example.foodapp.model.DA.KhachHangDA;
import com.example.foodapp.model.DTO.KhachHangDTO;

import java.util.List;

public class SingupActivity extends AppCompatActivity {

    Button btnDangKy, btnDangNhap, btnQuenMK;
    EditText edtHoTen, edtSDT, edtEmail, edtTenDangNhap, edtMatKhau, edtMatKhau2;

    // Biến thành viên để lưu trữ thông tin người dùng nhập vào
    private String hoTen, sDT, eMail, tenDangNhap, matKhau;

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
                hoTen = edtHoTen.getText().toString().trim();
                sDT = edtSDT.getText().toString().trim();
                eMail = edtEmail.getText().toString().trim();
                tenDangNhap = edtTenDangNhap.getText().toString().trim();
                matKhau = edtMatKhau.getText().toString().trim();
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
                checkUsernameExists();
            }
        });
    }

    // Phương thức kiểm tra tên đăng nhập có tồn tại hay không
    private void checkUsernameExists() {
        new KhachHangDA(new KhachHangDA.DatabaseCallback() {
            @Override
            public void onQueryExecuted(String query, List<KhachHangDTO> result, boolean isSuccess) {
                if (isSuccess) {
                    if (result != null && result.size() > 0) {
                        // Tên đăng nhập đã tồn tại, hiển thị thông báo cho người dùng
                        Toast.makeText(SingupActivity.this, "Tên đăng nhập đã tồn tại, vui lòng chọn tên khác", Toast.LENGTH_SHORT).show();
                    } else {
                        // Tên đăng nhập chưa tồn tại, tiến hành đăng ký
                        registerCustomer();
                    }
                } else {
                    // Xử lý lỗi khi thực hiện truy vấn
                    Toast.makeText(SingupActivity.this, "Đã xảy ra lỗi khi kiểm tra tên đăng nhập", Toast.LENGTH_SHORT).show();
                }
            }
        }, SingupActivity.this).execute(
                "SELECT * FROM KhachHang WHERE TenTaiKhoan = ?",
                tenDangNhap
        );
    }

    // Phương thức thực hiện đăng ký khách hàng
    private void registerCustomer() {
        // Tạo đối tượng KhachHangDTO từ các thông tin đã nhập
        final KhachHangDTO khachHang = new KhachHangDTO(hoTen, sDT, eMail, tenDangNhap, matKhau);

        // Thực hiện truy vấn SQL để thêm khách hàng mới vào cơ sở dữ liệu
        new KhachHangDA(new KhachHangDA.DatabaseCallback() {
            @Override
            public void onQueryExecuted(String query, List<KhachHangDTO> result, boolean isSuccess) {
                if (isSuccess) {
                    Toast.makeText(SingupActivity.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SingupActivity.this, "Đăng ký thất bại, vui lòng thử lại", Toast.LENGTH_SHORT).show();
                }
            }
        }, SingupActivity.this).execute(
                "INSERT INTO KhachHang (HoTen, SDT, EMail, TenTaiKhoan, MatKhau, DaXoa) VALUES (?, ?, ?, ?, ?, ?)",
                khachHang
        );
    }
}





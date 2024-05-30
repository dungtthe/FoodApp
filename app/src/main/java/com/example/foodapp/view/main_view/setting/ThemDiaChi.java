package com.example.foodapp.view.main_view.setting;

import static com.example.foodapp.model.DTO.DataCurrent.khachHangDTOCur;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodapp.R;
import com.example.foodapp.model.DA.DiaChiGiaoHangDA;
import com.example.foodapp.model.DA.KhachHangDA;
import com.example.foodapp.model.DA.QueryParameter;
import com.example.foodapp.model.DTO.DiaChiGiaoHangDTO;
import com.example.foodapp.model.DTO.KhachHangDTO;
import com.example.foodapp.view.start.ForgetPassword;
import com.example.foodapp.view.start.Login;
import com.example.foodapp.view.start.SingupActivity;

import java.util.ArrayList;
import java.util.List;

public class ThemDiaChi extends AppCompatActivity {

    EditText edtHoTen, edtSDT, edtTinh, edtSoNha;
    Button btnThemDiaChi;
    CheckBox chkMacDinh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_dia_chi);

        btnThemDiaChi = findViewById(R.id.btnThemDiaChi);
        edtHoTen = findViewById(R.id.edtHoTenThemDiaChi);
        edtSDT = findViewById(R.id.edtSDTThemDiaChi);
        edtTinh = findViewById(R.id.edtTinhThemDiaChi);
        edtSoNha = findViewById(R.id.edtSoNhaThemDiaChi);
        chkMacDinh = findViewById(R.id.chkDatDiaChiMacDinh);
        btnThemDiaChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hoTen = edtHoTen.getText().toString().trim();
                String sDT = edtSDT.getText().toString().trim();
                String tinh = edtTinh.getText().toString().trim();
                String soNha = edtSoNha.getText().toString().trim();
                String diaChi = soNha + tinh;
                Boolean LoaiDiachiHam = false;
                Boolean LoaiDiaChi;
                if(chkMacDinh.isChecked())
                {
                    LoaiDiaChi = true;
                    ChonDiaChiMacDinh(LoaiDiachiHam, ThemDiaChi.this);
                }
                else
                    LoaiDiaChi = false;
                // Kiểm tra xem các trường thông tin có trống không
                if (hoTen.isEmpty() || sDT.isEmpty() || tinh.isEmpty() || soNha.isEmpty()) {
                    Toast.makeText(ThemDiaChi.this, "Thông tin không đầy đủ", Toast.LENGTH_LONG).show();
                    return;
                }
                ThemDiaChi(hoTen,sDT,diaChi,LoaiDiaChi,ThemDiaChi.this);

            }
        });
    }
    @SuppressLint("NotConstructor")
    private void ThemDiaChi(String hoTen, String sDT, String diaChi , Boolean loaiDiacChi, Context context)
    {
        int id = khachHangDTOCur.getId();
        String query = "INSERT INTO DIACHIGIAOHANG (DiaChi, LoaiDiaChi, TenKhachHang, SoDienThoai, KhachHang_id) VALUES (?, ?, ?, ?, ?)";
        List<QueryParameter> parameters = new ArrayList<>();
        parameters.add(new QueryParameter(1, diaChi));
        parameters.add(new QueryParameter(2, loaiDiacChi));
        parameters.add(new QueryParameter(3, hoTen));
        parameters.add(new QueryParameter(4, sDT));
        parameters.add(new QueryParameter(5, id));

        Object[] params = new Object[parameters.size() + 1];
        params[0] = query;
        for (int i = 0; i < parameters.size(); i++) {
            params[i + 1] = parameters.get(i);
        }

       DiaChiGiaoHangDA diaChiGiaoHangDA = new DiaChiGiaoHangDA(new DiaChiGiaoHangDA.DatabaseCallback() {
           @Override
           public void onQueryExecuted(String query, List<DiaChiGiaoHangDTO> result, boolean isSuccess) {
               if (isSuccess) {
                   Toast.makeText(context, "Thêm mới địa chỉ thành công!", Toast.LENGTH_SHORT).show();
                   Intent intent = new Intent(ThemDiaChi.this, diachi.class);
                   startActivity(intent);
               } else {
                   Toast.makeText(context, "Có lỗi xảy ra, vui lòng thử lại", Toast.LENGTH_SHORT).show();
               }
           }
       },context);
        diaChiGiaoHangDA.execute(params);

    }

    private void ChonDiaChiMacDinh(Boolean loaiDiaChi, Context context)
    {
        int id = khachHangDTOCur.getId();
        String query = "UPDATE DIACHIGIAOHANG SET LoaiDiaChi = ? WHERE KhachHang_id = ? ";
        List<QueryParameter> parameters = new ArrayList<>();
        parameters.add(new QueryParameter(1, loaiDiaChi));
        parameters.add(new QueryParameter(2, id));

        Object[] params = new Object[parameters.size() + 1];
        params[0] = query;
        for (int i = 0; i < parameters.size(); i++) {
            params[i + 1] = parameters.get(i);
        }

        DiaChiGiaoHangDA diaChiGiaoHangDA = new DiaChiGiaoHangDA(new DiaChiGiaoHangDA.DatabaseCallback() {
            @Override
            public void onQueryExecuted(String query, List<DiaChiGiaoHangDTO> result, boolean isSuccess) {
                if (isSuccess)
                {

                }
                else
                {
                }
            }
        }, context);
        diaChiGiaoHangDA.execute(params);

    }
}
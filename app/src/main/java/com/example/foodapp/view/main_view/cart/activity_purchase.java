package com.example.foodapp.view.main_view.cart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.model.DTO.DataCurrent;
import com.example.foodapp.view.main_view.MotSoPhuongThucBoTro;
import com.example.foodapp.view.main_view.setting.diachi;

public class activity_purchase extends AppCompatActivity {

    private RecyclerView rcvListItem;
    TextView GiaTriHoaDonTruocVoucher,GiaTriHoaDonSauVoucher,ChiPhiGiamGia,DichVuVanChuyen;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        // Ánh xạ các View từ XML
        rcvListItem = findViewById(R.id.ListItemPay);
        GiaTriHoaDonTruocVoucher = findViewById(R.id.txtTongTienHoaDonTruocVoucher);
        GiaTriHoaDonSauVoucher = findViewById(R.id.txtTongTienHoaDonSauVoucher);
        ChiPhiGiamGia = findViewById(R.id.txtChiPhiGiamGiaa);
        DichVuVanChuyen = findViewById(R.id.txtGiaDichVuVanChuyen);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        rcvListItem.setLayoutManager(gridLayoutManager);
        SanPhamAdapter adapter = new SanPhamAdapter(DataCurrent.danhSachSanPhamCoTrongHoaDon);
        rcvListItem.setAdapter(adapter);


        androidx.constraintlayout.widget.ConstraintLayout constr_Address= findViewById(R.id.constr_Address);
        androidx.constraintlayout.widget.ConstraintLayout constr_Voucher= findViewById(R.id.constr_Voucher);
        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        constr_Address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myItent = new Intent(activity_purchase.this, diachi.class);
                startActivity(myItent);
            }
        });
        constr_Voucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myItent = new Intent(activity_purchase.this, activity_voucher.class);
                startActivityForResult(myItent,99);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==99 && resultCode==33){
            int kq=data.getIntExtra("TongGiaTriVoucher",0);
            GiaTriHoaDonSauVoucher.setText(MotSoPhuongThucBoTro.tinhGiaTriHoaDonSauVoucher()+" VND");
            ChiPhiGiamGia.setText("-"+kq+" VND");
        }
    }
}

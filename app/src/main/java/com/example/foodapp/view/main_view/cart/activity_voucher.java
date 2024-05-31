package com.example.foodapp.view.main_view.cart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.model.DA.QueryParameter;
import com.example.foodapp.model.DA.VoucherDA;
import com.example.foodapp.model.DTO.DataCurrent;
import com.example.foodapp.model.DTO.VoucherDTO;
import com.example.foodapp.view.main_view.MotSoPhuongThucBoTro;

import java.util.ArrayList;
import java.util.List;

public class activity_voucher extends AppCompatActivity {

    private RecyclerView rcvListItemVoucherGiamGia;
    private RecyclerView rcvListItemVoucherFreeShip;
    private ScrollView scvVoucher;
    private LinearLayout ln_toggle;
    private TextView txtHienThi;
    private TextView txtIcon;
    private LinearLayout ln_toggle2;
    private TextView txtHienThi2;
    private TextView txtIcon2;
    private List<VoucherDTO> listVoucherGiamGia;
    private List<VoucherDTO> listVoucherFreeShip;
    private VoucherAdapter adapterGiamGia;
    private VoucherAdapter adapterFreeShip;
    private Button btnBoChon, btnDongY;
    ImageButton btnBackPurchase;
    Intent myIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voucher);

        // Ánh xạ các View từ XML
        txtHienThi2 = findViewById(R.id.xemthem2);
        txtIcon2 = findViewById(R.id.icon_showItem2);
        ln_toggle2 = findViewById(R.id.groupChuyenDoi2);
        txtHienThi = findViewById(R.id.xemthem);
        txtIcon = findViewById(R.id.icon_showItem);
        ln_toggle = findViewById(R.id.groupChuyenDoi);
        scvVoucher = findViewById(R.id.scvVoucher);
        rcvListItemVoucherGiamGia = findViewById(R.id.rcvListVoucherGiamGia);
        rcvListItemVoucherFreeShip = findViewById(R.id.rcvListVoucherVanChuyen);
        btnBoChon = findViewById(R.id.btnBoChon);
        btnDongY = findViewById(R.id.btnDongY);
        btnBackPurchase = findViewById(R.id.btnBackPurchase);

        // Xử lý listItemVoucherGiamGia
        LoadDataVoucherOnsales(activity_voucher.this);
        // Xử lý listItemVoucherFreeShip
        LoadDataVoucherFreeShip(activity_voucher.this);

        btnBoChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adapterGiamGia != null) {
                    adapterGiamGia.clearSelection();
                }
                if (adapterFreeShip != null) {
                    adapterFreeShip.clearSelection();
                }
                DataCurrent.danhSachVoucherApDungChoHoaDon = new ArrayList<>();
            }
        });

        btnDongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity_voucher.this, "Vouchers selected successfully!", Toast.LENGTH_SHORT).show();
                myIntent = getIntent();
                myIntent.putExtra("TongGiaTriVoucher", MotSoPhuongThucBoTro.TongGiaTriVoucher());
                setResult(33,myIntent);
                finish();
            }
        });
        btnBackPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        // Sử dụng Handler để cuộn về đầu trang sau khi dữ liệu được tải xong
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                scvVoucher.scrollTo(0, 0);
            }
        }, 100);
    }

    private void LoadDataVoucherFreeShip(Context context) {
        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(this, 1);
        rcvListItemVoucherFreeShip.setLayoutManager(gridLayoutManager2);
        listVoucherFreeShip = new ArrayList<>();

        String query = "SELECT * FROM Voucher WHERE LoaiVoucher=1";
        List<QueryParameter> parameters = new ArrayList<>();
        Object[] params = new Object[parameters.size() + 1];
        params[0] = query;
        for (int i = 0; i < parameters.size(); i++) {
            params[i + 1] = parameters.get(i);
        }
        VoucherDA voucherDA = new VoucherDA(new VoucherDA.DatabaseCallback() {
            @Override
            public void onQueryExecuted(String query, List<VoucherDTO> result, boolean isSuccess) {
                if (isSuccess && !result.isEmpty()) {
                    for (int i = 0; i < result.size(); i++) {
                        result.get(i).setHinhAnh(R.drawable.voucher_freeship);
                    }
                    listVoucherFreeShip.clear();
                    listVoucherFreeShip.addAll(result);
                    adapterFreeShip = new VoucherAdapter(listVoucherFreeShip);
                    rcvListItemVoucherFreeShip.setAdapter(adapterFreeShip);
                } else {
                    Toast.makeText(context, "Load data thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        }, context);
        voucherDA.execute(params);

        ln_toggle2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterFreeShip.toggleShowItems();
                if (adapterFreeShip.isShowingAll()) {
                    txtHienThi2.setText("Thu gọn");
                    txtIcon2.setBackgroundResource(R.drawable.iconthugon);
                } else {
                    txtHienThi2.setText("Xem thêm");
                    txtIcon2.setBackgroundResource(R.drawable.iconshow);
                }
            }
        });
    }

    private void LoadDataVoucherOnsales(Context context) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        rcvListItemVoucherGiamGia.setLayoutManager(gridLayoutManager);
        listVoucherGiamGia = new ArrayList<>();

        String query = "SELECT * FROM Voucher WHERE LoaiVoucher=0";
        List<QueryParameter> parameters = new ArrayList<>();
        Object[] params = new Object[parameters.size() + 1];
        params[0] = query;
        for (int i = 0; i < parameters.size(); i++) {
            params[i + 1] = parameters.get(i);
        }
        VoucherDA voucherDA = new VoucherDA(new VoucherDA.DatabaseCallback() {
            @Override
            public void onQueryExecuted(String query, List<VoucherDTO> result, boolean isSuccess) {
                if (isSuccess && !result.isEmpty()) {
                    listVoucherGiamGia.clear();
                    for (int i = 0; i < result.size(); i++) {
                        result.get(i).setHinhAnh(R.drawable.voucher_onsales);
                        listVoucherGiamGia.add(result.get(i));
                    }

                    adapterGiamGia = new VoucherAdapter(listVoucherGiamGia);

                    rcvListItemVoucherGiamGia.setAdapter(adapterGiamGia);

                    // Notify the adapter about the data change
                    adapterGiamGia.notifyDataSetChanged();
                } else {
                    Toast.makeText(context, "Load data thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        }, context);
        voucherDA.execute(params);

        ln_toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterGiamGia.toggleShowItems();
                if (adapterGiamGia.isShowingAll()) {
                    txtHienThi.setText("Thu gọn");
                    txtIcon.setBackgroundResource(R.drawable.iconthugon);
                } else {
                    txtHienThi.setText("Xem thêm");
                    txtIcon.setBackgroundResource(R.drawable.iconshow);
                }
            }
        });
    }
}

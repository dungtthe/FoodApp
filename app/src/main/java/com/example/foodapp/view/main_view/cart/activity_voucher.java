package com.example.foodapp.view.main_view.cart;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;

import java.util.ArrayList;
import java.util.List;

public class activity_voucher extends AppCompatActivity {

    private RecyclerView rcvListItemVoucherGiamGia;
    private RecyclerView rcvListItemVoucherFreeShip;
    private ScrollView scvVoucher;
    LinearLayout ln_toggle;
    TextView txtHienThi;
    TextView txtIcon;
    LinearLayout ln_toggle2;
    TextView txtHienThi2;
    TextView txtIcon2;
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

        //xử lý listItemVoucherGiamGia
        //load dữ liệu từ database
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        rcvListItemVoucherGiamGia.setLayoutManager(gridLayoutManager);
        VoucherAdapter adapter = new VoucherAdapter(getListItemVoucherGiamGia());
        rcvListItemVoucherGiamGia.setAdapter(adapter);
        ln_toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.toggleShowItems();
                if (adapter.isShowingAll()) {
                    txtHienThi.setText("Thu gọn");
                    txtIcon.setBackgroundResource(R.drawable.iconthugon);
                } else {
                    txtHienThi.setText("Xem thêm");
                    txtIcon.setBackgroundResource(R.drawable.iconshow);
                }
            }
        });

        //Xử lý listItemVoucherFreeShip
        rcvListItemVoucherFreeShip = findViewById(R.id.rcvListVoucherVanChuyen);
        GridLayoutManager gridLayoutManagerr = new GridLayoutManager(this, 1);
        rcvListItemVoucherFreeShip.setLayoutManager(gridLayoutManagerr);
        VoucherAdapter adapterr = new VoucherAdapter(getListItemVoucherFreeShip());
        rcvListItemVoucherFreeShip.setAdapter(adapterr);
        ln_toggle2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterr.toggleShowItems();
                if (adapterr.isShowingAll()) {
                    txtHienThi2.setText("Thu gọn");
                    txtIcon2.setBackgroundResource(R.drawable.iconthugon);
                } else {
                    txtHienThi2.setText("Xem thêm");
                    txtIcon2.setBackgroundResource(R.drawable.iconshow);
                }
            }
        });
        // Sử dụng Handler để cuộn về đầu trang sau khi dữ liệu được tải xong
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                scvVoucher.scrollTo(0, 0); // Sử dụng scrollTo
            }
        }, 100); // Đặt độ trễ nhỏ (ví dụ: 100ms)
    }

    private List<Voucher> getListItemVoucherFreeShip() {
        List<Voucher> listVoucher = new ArrayList<>();
        listVoucher.add(new Voucher(R.drawable.voucher_freeship,"19/5/2024","100.000","40.000"));
        listVoucher.add(new Voucher(R.drawable.voucher_freeship,"19/5/2024","100.000","40.000"));
        listVoucher.add(new Voucher(R.drawable.voucher_freeship,"19/5/2024","100.000","40.000"));
        listVoucher.add(new Voucher(R.drawable.voucher_freeship,"19/5/2024","100.000","40.000"));
        return listVoucher;
    }

    private List<Voucher> getListItemVoucherGiamGia() {
        List<Voucher> listVoucher = new ArrayList<>();
        listVoucher.add(new Voucher(R.drawable.voucher_onsales,"19/5/2024","100.000","40.000"));
        listVoucher.add(new Voucher(R.drawable.voucher_onsales,"19/5/2024","100.000","40.000"));
        listVoucher.add(new Voucher(R.drawable.voucher_onsales,"19/5/2024","100.000","40.000"));
        listVoucher.add(new Voucher(R.drawable.voucher_onsales,"19/5/2024","100.000","40.000"));
        return listVoucher;
    }
}
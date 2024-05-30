package com.example.foodapp.view.main_view.cart;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.model.DTO.DataCurrent;

public class activity_purchase extends AppCompatActivity {

    private RecyclerView rcvListItem;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        // Ánh xạ các View từ XML
        rcvListItem = findViewById(R.id.ListItemPay);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        rcvListItem.setLayoutManager(gridLayoutManager);
        SanPhamAdapter adapter = new SanPhamAdapter(DataCurrent.danhSachSanPhamCoTrongHoaDon);
        rcvListItem.setAdapter(adapter);
    }

}

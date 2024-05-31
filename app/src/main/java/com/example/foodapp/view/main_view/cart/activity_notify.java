package com.example.foodapp.view.main_view.cart;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.view.main_view.MainViewActivity;
import com.example.foodapp.view.main_view.cart.purchase_order.activity_PurchaseOrder;

import java.util.ArrayList;
import java.util.List;

public class activity_notify extends AppCompatActivity {
    private RecyclerView rcvListItem;
    private ScrollView scvContentNotify;
    private Button btnHome,btnDonMua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);

        // Ánh xạ các View từ XML
        scvContentNotify = findViewById(R.id.scvContentNotify);
        rcvListItem = findViewById(R.id.rcvListItemNotify);
        btnHome = findViewById(R.id.btnHome);
        btnDonMua = findViewById(R.id.btnDonMua);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rcvListItem.setLayoutManager(gridLayoutManager);
        ProductAdapter adapter = new ProductAdapter(getListItem());
        rcvListItem.setAdapter(adapter);

        // Sử dụng Handler để cuộn về đầu trang sau khi dữ liệu được tải xong
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                scvContentNotify.scrollTo(0, 0); // Sử dụng scrollTo
            }
        }, 100); // Đặt độ trễ nhỏ (ví dụ: 100ms)
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myItent = new Intent(activity_notify.this, MainViewActivity.class);
                startActivity(myItent);
            }
        });
        btnDonMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myItent = new Intent(activity_notify.this, activity_PurchaseOrder.class);
                startActivity(myItent);
            }
        });
    }

    private List<Product> getListItem() {
        List<Product> listProduct = new ArrayList<>();
        listProduct.add(new Product("CoCa", 20000, R.drawable.coca, 2));
        listProduct.add(new Product("CoCa", 20000, R.drawable.coca, 2));
        listProduct.add(new Product("CoCa", 20000, R.drawable.coca, 2));
        listProduct.add(new Product("CoCa", 20000, R.drawable.coca, 2));
        listProduct.add(new Product("CoCa", 20000, R.drawable.coca, 2));
        listProduct.add(new Product("CoCa", 20000, R.drawable.coca, 2));

        return listProduct;
    }
}

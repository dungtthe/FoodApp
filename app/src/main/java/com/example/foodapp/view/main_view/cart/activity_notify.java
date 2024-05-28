package com.example.foodapp.view.main_view.cart;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.view.main_view.R;

import java.util.ArrayList;
import java.util.List;

public class activity_notify extends AppCompatActivity {
    private RecyclerView rcvListItem;
    private ScrollView scvContentNotify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);

        // Ánh xạ các View từ XML
        scvContentNotify = findViewById(R.id.scvContentNotify);
        rcvListItem = findViewById(R.id.rcvListItemNotify);
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

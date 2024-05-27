package com.example.foodapp.view.main_view.cart;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.view.main_view.R;

import java.util.ArrayList;
import java.util.List;

public class activity_purchase extends AppCompatActivity {

    private RecyclerView rcvListItem;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        // Ánh xạ các View từ XML
        rcvListItem = findViewById(R.id.ListItemPay);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        rcvListItem.setLayoutManager(gridLayoutManager);
        ProductAdapter adapter = new ProductAdapter(getListItem());
        rcvListItem.setAdapter(adapter);
    }



    private List<Product> getListItem() {
        List<Product> listProduct = new ArrayList<>();
        listProduct.add(new Product("CoCa", 20000, R.drawable.coca, 2));
        listProduct.add(new Product("CoCa", 20000, R.drawable.coca, 2));
        listProduct.add(new Product("CoCa", 20000, R.drawable.coca, 2));
        listProduct.add(new Product("CoCa", 20000, R.drawable.coca, 2));
        listProduct.add(new Product("CoCa", 20000, R.drawable.coca, 2));
        return listProduct;
    }
}

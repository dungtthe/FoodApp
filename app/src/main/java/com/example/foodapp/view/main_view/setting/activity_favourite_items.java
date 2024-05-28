package com.example.foodapp.view.main_view.setting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.foodapp.model.DTO.favourite_product;
import android.os.Bundle;

import com.example.foodapp.R;

import java.util.ArrayList;
import java.util.List;

public class activity_favourite_items extends AppCompatActivity {
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_items);
        List<favourite_product> favourite_itemList = new ArrayList<favourite_product>() ;
        favourite_itemList.add(new favourite_product(1,"Bánh mì",10000, R.drawable.banhmi));
        favourite_itemList.add(new favourite_product(1,"Bánh mì",10000, R.drawable.banhmi));
        favourite_itemList.add(new favourite_product(1,"Bánh mì",10000, R.drawable.banhmi));
        favourite_itemList.add(new favourite_product(1,"Bánh mì",10000, R.drawable.banhmi));
        favourite_itemList.add(new favourite_product(1,"Bánh mì",10000, R.drawable.banhmi));
        favourite_itemList.add(new favourite_product(1,"Bánh mì",10000, R.drawable.banhmi));
        favourite_itemList.add(new favourite_product(1,"Bánh mì",10000, R.drawable.banhmi));
        favourite_itemList.add(new favourite_product(1,"Bánh mì",10000, R.drawable.banhmi));
        favourite_itemList.add(new favourite_product(1,"Bánh mì",10000, R.drawable.banhmi));
        favourite_itemList.add(new favourite_product(1,"Bánh mì",10000, R.drawable.banhmi));
        favourite_itemList.add(new favourite_product(1,"Bánh mì",10000, R.drawable.banhmi));
        favourite_itemList.add(new favourite_product(1,"Bánh mì",10000, R.drawable.banhmi));
        favourite_itemList.add(new favourite_product(1,"Bánh mì",10000, R.drawable.banhmi));
        favourite_itemList.add(new favourite_product(1,"Bánh mì",10000, R.drawable.banhmi));
        favourite_itemList.add(new favourite_product(1,"Bánh mì",10000, R.drawable.banhmi));
        favourite_itemList.add(new favourite_product(1,"Bánh mì",10000, R.drawable.banhmi));


        recyclerView =findViewById(R.id.recycler_view);
        MyAdapter myAdapter = new MyAdapter(favourite_itemList);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
    }
}
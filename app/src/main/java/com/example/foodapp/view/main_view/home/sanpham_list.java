package com.example.foodapp.view.main_view.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.model.DA.SanPhamDA;
import com.example.foodapp.model.DTO.SanPhamDTO;

import android.os.Bundle;

import com.example.foodapp.R;

import java.util.ArrayList;
import java.util.List;

public class sanpham_list extends AppCompatActivity {



    public sanpham_list() {
        // Required empty public constructor
    }
    private RecyclerView recyclerView;
    private SanPham_Adapter sanPhamAdapter;
    private List<SanPhamDTO> sanPhamList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sanpham_list);

        recyclerView = findViewById(R.id.recycler_view_loaisp);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        sanPhamList = new ArrayList<>();

        // Load sản phẩm từ cơ sở dữ liệu
        SanPhamDA sanPhamDA = new SanPhamDA(new SanPhamDA.DatabaseCallback() {
            @Override
            public void onQueryExecuted(String query, List<SanPhamDTO> result, boolean isSuccess) {
                if (isSuccess && result != null) {
                    sanPhamList.clear();
                    sanPhamList.addAll(result);
                    sanPhamAdapter.notifyDataSetChanged();
                }
            }
        }, this);

        sanPhamDA.execute("SELECT * FROM SanPham WHERE DaXoa = FALSE");

        sanPhamAdapter = new SanPham_Adapter(sanPhamList);
        recyclerView.setAdapter(sanPhamAdapter);





//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_favourite_items);
//        List<favourite_product> favourite_itemList = new ArrayList<favourite_product>() ;
//        favourite_itemList.add(new favourite_product(1,"Bánh mì",10000, R.drawable.banhmi));
//        favourite_itemList.add(new favourite_product(1,"Bánh mì",10000, R.drawable.banhmi));
//
//
//        recyclerView =findViewById(R.id.recycler_view);
//        MyAdapter myAdapter = new MyAdapter(favourite_itemList);
//        recyclerView.setAdapter(myAdapter);
//        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
    }


}
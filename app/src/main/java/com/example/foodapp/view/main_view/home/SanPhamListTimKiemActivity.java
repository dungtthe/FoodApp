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

public class SanPhamListTimKiemActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SanPham_Adapter sanPhamAdapter;
    private List<SanPhamDTO> sanPhamList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sanpham_list_for_timkiem_home);

        recyclerView = findViewById(R.id.recycler_view_loaisp);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        sanPhamList = new ArrayList<>();

        // Nhận dữ liệu tìm kiếm từ Intent
        String searchQuery = getIntent().getStringExtra("search_query");

        // Load sản phẩm từ cơ sở dữ liệu với từ khóa tìm kiếm
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

        // Thực hiện truy vấn với từ khóa tìm kiếm
        String query = "SELECT * FROM SanPham WHERE DaXoa = FALSE";
        sanPhamDA.execute(query);

        sanPhamAdapter = new SanPham_Adapter(sanPhamList);
        recyclerView.setAdapter(sanPhamAdapter);
    }
}

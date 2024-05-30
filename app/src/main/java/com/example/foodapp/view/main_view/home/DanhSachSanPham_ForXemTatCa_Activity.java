package com.example.foodapp.view.main_view.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.foodapp.R;
import com.example.foodapp.model.DA.QueryParameter;
import com.example.foodapp.model.DA.SanPhamDA;
import com.example.foodapp.model.DTO.SanPhamDTO;
import com.example.foodapp.view.main_view.MainViewActivity;

import java.util.ArrayList;
import java.util.List;

public class DanhSachSanPham_ForXemTatCa_Activity extends AppCompatActivity {



    private RecyclerView recyclerView;
    private SanPhamAdapter_For_XemTatCa sanPhamAdapter;
    private List<SanPhamDTO> sanPhamList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_san_pham_for_xemtatca);




        //nút back
        ImageView imageView = findViewById(R.id.icon_back_button_for_xemtatca_danhsachsanpham);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });





        //hiển thị danh sách sản phẩm

        recyclerView = findViewById(R.id.recycler_view_danhSachSanPhamForXemTatCa);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        sanPhamList = new ArrayList<>();

        danhSachSanPham_ForXemTatCa(DanhSachSanPham_ForXemTatCa_Activity.this);



    }



    public void danhSachSanPham_ForXemTatCa(Context context) {
        String query = "SELECT sp.*, (CASE WHEN spt.ID IS NOT NULL THEN TRUE ELSE FALSE END) AS daThich " +
                "FROM SanPham sp LEFT JOIN SanPhamThich spt ON sp.ID = spt.SanPham_id AND spt.KhachHang_id = ? " +
                "WHERE sp.DaXoa = ?";
        List<QueryParameter> parameters = new ArrayList<>();
        parameters.add(new QueryParameter(1, MainViewActivity.userCur.getId()));
        parameters.add(new QueryParameter(2, false));

        Object[] params = new Object[parameters.size() + 1];
        params[0] = query;
        for (int i = 0; i < parameters.size(); i++) {
            params[i + 1] = parameters.get(i);
        }

        SanPhamDA sanPhamDA = new SanPhamDA(new SanPhamDA.DatabaseCallback() {
            @Override
            public void onQueryExecuted(String query, List<SanPhamDTO> result, boolean isSuccess) {
                if (isSuccess && !result.isEmpty()) {
                    sanPhamList.clear();
                    sanPhamList.addAll(result);
                    sanPhamAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(context, "Có lỗi xảy ra!", Toast.LENGTH_SHORT).show();
                }
            }
        }, context);

        sanPhamDA.execute(params);
        sanPhamAdapter = new SanPhamAdapter_For_XemTatCa(sanPhamList);
        recyclerView.setAdapter(sanPhamAdapter);
    }

}
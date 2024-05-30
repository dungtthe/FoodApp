package com.example.foodapp.view.main_view.home;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.foodapp.R;
import com.example.foodapp.model.DA.QueryParameter;
import com.example.foodapp.model.DA.SanPhamDA;
import com.example.foodapp.model.DA.SanPhamThichDA;
import com.example.foodapp.model.DTO.DataCurrent;
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


        //toàn màn hình
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);



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
        String query = "SELECT * FROM SanPham WHERE DaXoa = false";
        List<QueryParameter> parameters = new ArrayList<>();

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

                    // Gọi phương thức để thiết lập thuộc tính daThich cho các sản phẩm
                    getAllSanPhamThich(context, DataCurrent.khachHangDTOCur.getId(), sanPhamList);

                    sanPhamAdapter = new SanPhamAdapter_For_XemTatCa(sanPhamList);
                    recyclerView.setAdapter(sanPhamAdapter);

                    sanPhamAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(context, "Có lỗi xảy ra!", Toast.LENGTH_SHORT).show();
                }
            }
        }, context);

        sanPhamDA.execute(params);
//        sanPhamAdapter = new SanPhamAdapter_For_XemTatCa(sanPhamList);
//        recyclerView.setAdapter(sanPhamAdapter);
    }



    public void getAllSanPhamThich(Context context, int khachHangId, List<SanPhamDTO> allSanPhamList) {
        SanPhamThichDA sanPhamThichDA = new SanPhamThichDA(new SanPhamThichDA.DatabaseCallback() {
            @Override
            public void onQueryExecuted(String query, List<SanPhamDTO> result, boolean isSuccess) {
                if (isSuccess) {
                    // Thiết lập thuộc tính daThich cho các sản phẩm yêu thích
                    for (SanPhamDTO likedProduct : result) {
                        for (SanPhamDTO product : allSanPhamList) {
                            if (product.getId() == likedProduct.getId()) {
                                product.setDaThich(true);
                                break;
                            }
                        }
                    }
                    // Cập nhật lại adapter để hiển thị
                     sanPhamAdapter.notifyDataSetChanged();
                } else {
                }
            }

            @Override
            public void onModifyExecuted(String query, Boolean result, boolean isSuccess) {
            }
        }, context, SanPhamThichDA.ActionType.GET_LIKED_PRODUCTS);

        sanPhamThichDA.execute(khachHangId);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {

            try {
                int sanPhamId = data.getIntExtra("sanPhamId", -1);
                boolean daThich = data.getBooleanExtra("daThich", false);

                // Cập nhật sản phẩm bị thay đổi trong danh sách
                for (int i = 0; i < sanPhamList.size(); i++) {
                    SanPhamDTO sanPham = sanPhamList.get(i);
                    if (sanPham.getId() == sanPhamId) {
                        sanPham.setDaThich(daThich);
                        sanPhamAdapter.notifyItemChanged(i);
                        break;
                    }
                }
            }
            catch (Exception e){
            }
        }
    }
}
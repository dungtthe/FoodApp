package com.example.foodapp.view.main_view.setting;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.model.DA.QueryParameter;
import com.example.foodapp.model.DA.SanPhamDA;
import com.example.foodapp.model.DA.SanPhamThichDA;
import com.example.foodapp.model.DTO.DataCurrent;
import com.example.foodapp.model.DTO.SanPhamDTO;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodapp.R;
import com.example.foodapp.view.main_view.home.SanPhamAdapter_For_TimKiem;
import com.example.foodapp.view.main_view.home.SanPhamAdapter_For_XemTatCa;
import com.example.foodapp.view.start.Login;

import java.util.ArrayList;
import java.util.List;

public class activity_favourite_items extends AppCompatActivity {


    public activity_favourite_items() {
        // Required empty public constructor
    }

    private RecyclerView recyclerView;
    private SanPhamThich_Adapter sanPhamAdapter;
    private List<SanPhamDTO> sanPhamList;
    private ImageView btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_items);


        recyclerView = findViewById(R.id.recycler_view_sanphamthich_forsetting);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        sanPhamList = new ArrayList<>();


        getAllSanPhamThich(activity_favourite_items.this,DataCurrent.khachHangDTOCur.getId());

        btn_back = findViewById(R.id.icon_back_dathich);
        // Thiết lập sự kiện click cho LinearLayout "Đã thích"
        btn_back.setOnClickListener(v -> handleBackButtonClicked());
    }
    private void handleBackButtonClicked() {
        // Xử lý sự kiện click button "Đổi mk"
        finish();
    }

    private void getAllSanPhamThich(Context context,int khachHangId){

        SanPhamThichDA sanPhamThichDA = new SanPhamThichDA(new SanPhamThichDA.DatabaseCallback() {
            @Override
            public void onQueryExecuted(String query, List<SanPhamDTO> result, boolean isSuccess) {
                if (isSuccess) {

                    sanPhamList.clear();
                    sanPhamList.addAll(result);



                    sanPhamAdapter = new SanPhamThich_Adapter(sanPhamList);
                    recyclerView.setAdapter(sanPhamAdapter);

                    sanPhamAdapter.notifyDataSetChanged();


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

                        if(!daThich){
                            sanPhamList.remove(sanPham);
                            sanPhamAdapter.notifyDataSetChanged();

                        }

                        break;
                    }
                }
            }
            catch (Exception e){
            }
        }
    }


}



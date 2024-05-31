package com.example.foodapp.view.main_view.home;

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
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodapp.R;
import com.example.foodapp.view.main_view.MotSoPhuongThucBoTro;

import java.util.ArrayList;
import java.util.List;

public class SanPhamListTimKiemActivity extends AppCompatActivity {



    private RecyclerView recyclerView;
    private SanPhamAdapter_For_TimKiem sanPhamAdapter;
    private List<SanPhamDTO> sanPhamList;

    private List<SanPhamDTO> sanPhamListDaTimKiem;

    private TextView tv_LoaiSanPham_for_timkiemsanpham_home;//hiển thị nội dung chuỗi tìm kiếm
    private EditText search_edit_text_fortimkiem_home;//chuỗi để nhập phục vụ tìm kiếm
    private  ImageView icon_search_for_timkiemsanpham_home;//img nút tìm kiếm

    private String chuoiTimKiem="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sanpham_list_for_timkiem_home);
        //toàn màn hình
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);




        //nút back
        ImageView imageView = findViewById(R.id.icon_back_button_for_timkiemsanpham_home);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        //
        tv_LoaiSanPham_for_timkiemsanpham_home=findViewById(R.id.tv_LoaiSanPham_for_timkiemsanpham_home);
        search_edit_text_fortimkiem_home=findViewById(R.id.search_edit_text_fortimkiem_home);
        icon_search_for_timkiemsanpham_home=findViewById(R.id.icon_search_for_timkiemsanpham_home);


        icon_search_for_timkiemsanpham_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {
                    if (search_edit_text_fortimkiem_home.getText().toString() == null || search_edit_text_fortimkiem_home.getText().toString().equals("") || MotSoPhuongThucBoTro.isAllWhitespace(search_edit_text_fortimkiem_home.getText().toString())) {

                        Toast.makeText(v.getContext(), "Vui lòng nhập thông tin tìm kiếm!", Toast.LENGTH_SHORT).show();

                        return;
                    }

                    chuoiTimKiem = search_edit_text_fortimkiem_home.getText().toString();

                    sanPhamListDaTimKiem = timKiem(sanPhamList, chuoiTimKiem);

                    // Gọi phương thức để thiết lập thuộc tính daThich cho các sản phẩm
                    getAllSanPhamThich(SanPhamListTimKiemActivity.this, DataCurrent.khachHangDTOCur.getId(), sanPhamList);

                    sanPhamAdapter = new SanPhamAdapter_For_TimKiem(sanPhamListDaTimKiem);
                    recyclerView.setAdapter(sanPhamAdapter);

                    sanPhamAdapter.notifyDataSetChanged();

                }
                catch (Exception e){}

            }
        });



        chuoiTimKiem = getIntent().getStringExtra("search_query");


        //hiển thị danh sách sản phẩm tìm kiếm

        recyclerView = findViewById(R.id.recycler_view_loaisp_for_timkiemsanpham_home);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        sanPhamList = new ArrayList<>();

        danhSachSanPham_ForTimKiem(SanPhamListTimKiemActivity.this);



    }


    public void danhSachSanPham_ForTimKiem(Context context) {
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

                    sanPhamList=result;

                    sanPhamListDaTimKiem=timKiem(sanPhamList,chuoiTimKiem);

                    // Gọi phương thức để thiết lập thuộc tính daThich cho các sản phẩm
                    getAllSanPhamThich(context, DataCurrent.khachHangDTOCur.getId(), sanPhamList);

                    sanPhamAdapter = new SanPhamAdapter_For_TimKiem(sanPhamListDaTimKiem);
                    recyclerView.setAdapter(sanPhamAdapter);

                    sanPhamAdapter.notifyDataSetChanged();
                }
            }
        }, context);

        sanPhamDA.execute(params);

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
                for (int i = 0; i < sanPhamListDaTimKiem.size(); i++) {
                    SanPhamDTO sanPham = sanPhamListDaTimKiem.get(i);
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


    private List<SanPhamDTO> timKiem(List<SanPhamDTO>listSanPham,String noidung){
        tv_LoaiSanPham_for_timkiemsanpham_home.setText(noidung);
        if(noidung.equals("")||noidung.isEmpty()){
            return listSanPham;
        }

        List<SanPhamDTO> ketQua = new ArrayList<>();
        for (SanPhamDTO sanPham : listSanPham) {
            if (sanPham.getTenSP().toLowerCase().contains(noidung.toLowerCase())) {
                ketQua.add(sanPham);
            }
        }

        return ketQua;

    }

}

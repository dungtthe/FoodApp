package com.example.foodapp.view.main_view.home;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodapp.R;
import com.example.foodapp.model.DA.QueryParameter;
import com.example.foodapp.model.DTO.SanPhamDTO;
import com.example.foodapp.model.DA.SanPhamDA;
import com.example.foodapp.view.main_view.MotSoPhuongThucBoTro;

import java.util.ArrayList;
import java.util.List;

public class Detail_SanPham_For_Home_Activity extends AppCompatActivity {

    private ImageView productImageView;
    private TextView productNameTextView;
    private TextView productPriceTextView;
    private TextView productDescriptionTextView;
    private TextView productQuantityTextView;
    private TextView totalAmountTextView;
    private ImageView img_traitim_forDetailSanPham;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_san_pham_for_home);


        //nút back
        ImageView imgBack=findViewById(R.id.imageView7_backicon_for_detailsanpham);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        // Khai báo các view
        productImageView = findViewById(R.id.imageView_imgSanPham_for_detailsanpham);
        productNameTextView = findViewById(R.id.tvNameSp_forDetailSanPham);
        productPriceTextView = findViewById(R.id.tv_price_for_detailSanpham);//giá bán của sản phẩm
        productDescriptionTextView = findViewById(R.id.tvMotaSanPham_forDetailSanPham);
        productQuantityTextView = findViewById(R.id.txtQuantity_SoLuongMuonThem_forDetailSanPham);//số lượng sản phẩm muốn thêm vào giỏ
        totalAmountTextView = findViewById(R.id.tv_TongTien_forDetailSanPham);
        img_traitim_forDetailSanPham=findViewById(R.id.img_traitim_forDetailSanPham);


        int sanPhamId = getIntent().getIntExtra("sanPhamId", -1);

        if (sanPhamId != -1) {
            loadProductDetails(sanPhamId);
        }
    }

    private void loadProductDetails(int sanPhamId) {


        String query = "SELECT * FROM SanPham WHERE ID = ?";
        List<QueryParameter> parameters = new ArrayList<>();
        parameters.add(new QueryParameter(1, sanPhamId));


        Object[] params = new Object[parameters.size() + 1];
        params[0] = query;
        for (int i = 0; i < parameters.size(); i++) {
            params[i + 1] = parameters.get(i);
        }



        SanPhamDA sanPhamDA = new SanPhamDA(new SanPhamDA.DatabaseCallback() {
            @Override
            public void onQueryExecuted(String query, List<SanPhamDTO> result, boolean isSuccess) {

                if (isSuccess && !result.isEmpty()) {
                    SanPhamDTO sanPhamDTO = result.get(0);
                    productImageView.setImageBitmap(sanPhamDTO.getHinhAnh());
                    productNameTextView.setText(sanPhamDTO.getTenSP());
                    productPriceTextView.setText(MotSoPhuongThucBoTro.formatTienSangVND(sanPhamDTO.getGiaBan()));
                    productDescriptionTextView.setText(sanPhamDTO.getMoTa());
                    productQuantityTextView.setText(1+"");
                    totalAmountTextView.setText(MotSoPhuongThucBoTro.formatTienSangVND(sanPhamDTO.getGiaBan()));

                    if(sanPhamDTO.isDaThich()){
                        img_traitim_forDetailSanPham.setImageResource(R.drawable.ic_favorite_red);
                    }
                    else{
                        img_traitim_forDetailSanPham.setImageResource(R.drawable.icon_favourite);
                    }

                } else {
                    Toast.makeText(Detail_SanPham_For_Home_Activity.this, "Có lỗi xảy ra!", Toast.LENGTH_SHORT).show();
                }
            }
        }, Detail_SanPham_For_Home_Activity.this);

        sanPhamDA.execute(params);


    }
}

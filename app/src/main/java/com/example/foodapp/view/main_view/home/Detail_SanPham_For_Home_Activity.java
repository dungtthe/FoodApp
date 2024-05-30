package com.example.foodapp.view.main_view.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodapp.R;
import com.example.foodapp.model.DA.QueryParameter;
import com.example.foodapp.model.DA.SanPhamThichDA;
import com.example.foodapp.model.DTO.DataCurrent;
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
    private EditText productQuantityTextView;
    private TextView totalAmountTextView;
    private ImageView img_traitim_forDetailSanPham;
    private Button btnTangSanPham;
    private Button btnGiamSanPham;
    private TextView txtTonKhoSanPham_forDetailProduct;
    private SanPhamDTO sanPhamCur=null;
    private LinearLayout btn_ThemVaoGioHang_forDetailSanPham;
    private  LinearLayout btn_DanhGia_forDetailSanPham;

    int sanPhamId=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_san_pham_for_home);

        //toàn màn hình
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);


        //nút back
        ImageView imgBack=findViewById(R.id.imageView7_backicon_for_detailsanpham);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("sanPhamId", sanPhamId);
                resultIntent.putExtra("daThich",daThich);
                setResult(RESULT_OK,resultIntent);
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
        btnTangSanPham=findViewById(R.id.btn_Tang_forDetailSanPham);
        btnGiamSanPham=findViewById(R.id.button_giam_forDetailSanPham);
        txtTonKhoSanPham_forDetailProduct=findViewById(R.id.txtTonKhoSanPham_forDetailProduct);
        btn_ThemVaoGioHang_forDetailSanPham=findViewById(R.id.btn_ThemVaoGioHang_forDetailSanPham);
        btn_DanhGia_forDetailSanPham=findViewById(R.id.btn_DanhGia_forDetailSanPham);

        sanPhamId = getIntent().getIntExtra("sanPhamId", -1);
        daThich = getIntent().getBooleanExtra("daThich", false);
        if (sanPhamId != -1) {
            loadProductDetails(sanPhamId);
        }



        //bắt sự kiện nhấn vào trái tim
        img_traitim_forDetailSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (daThich) {
                    SanPhamThichDA.removeLikeSanpham(v.getContext(), DataCurrent.khachHangDTOCur.getId(), sanPhamId);
                    img_traitim_forDetailSanPham.setImageResource(R.drawable.icon_favourite);

                } else {
                    SanPhamThichDA.likeSanpham(v.getContext(), DataCurrent.khachHangDTOCur.getId(), sanPhamId);
                    img_traitim_forDetailSanPham.setImageResource(R.drawable.ic_favorite_red);

                }
                daThich=!daThich;
            }
        });


        //bắt sự kiện nhấn vào button tăng , giảm
        btnTangSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soLuongCur=1;
                try {
                    soLuongCur = Integer.parseInt(productQuantityTextView.getText().toString().trim());
                }
                catch (Exception e){return;}

               if(sanPhamCur==null){
                   Toast.makeText(Detail_SanPham_For_Home_Activity.this, "Có lỗi xảy ra!", Toast.LENGTH_SHORT).show();
                   return;
               }




                if(soLuongCur>=sanPhamCur.getSoLuongTon()){
                    Toast.makeText(Detail_SanPham_For_Home_Activity.this, "Không đủ số lượng!", Toast.LENGTH_SHORT).show();
                    return;
                }

                soLuongCur++;

                productQuantityTextView.setText(""+soLuongCur);


                totalAmountTextView.setText(MotSoPhuongThucBoTro.formatTienSangVND(soLuongCur*sanPhamCur.getGiaBan()));
            }
        });


        btnGiamSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soLuongCur=1;
                try {
                    soLuongCur = Integer.parseInt(productQuantityTextView.getText().toString().trim());
                }
                catch (Exception e){return;}

                if(sanPhamCur==null){
                    Toast.makeText(Detail_SanPham_For_Home_Activity.this, "Có lỗi xảy ra!", Toast.LENGTH_SHORT).show();
                    return;
                }




                if(soLuongCur<=1){
                    Toast.makeText(Detail_SanPham_For_Home_Activity.this, "Số lượng tối thiểu là 1!", Toast.LENGTH_SHORT).show();
                    return;
                }

                soLuongCur--;

                productQuantityTextView.setText(""+soLuongCur);


                totalAmountTextView.setText(MotSoPhuongThucBoTro.formatTienSangVND(soLuongCur*sanPhamCur.getGiaBan()));
            }
        });



        //sự kiện nhập số lượng

        productQuantityTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                try {
                    String input = s.toString();
                    if (input.startsWith("0") && input.length() > 1) {
                        productQuantityTextView.setText(input.replaceFirst("^0+(?!$)", ""));
                        productQuantityTextView.setSelection(productQuantityTextView.getText().length());
                    }

                    if(productQuantityTextView.getText().toString()==null||productQuantityTextView.getText().toString().trim().equals("")){
                        productQuantityTextView.setText("1");
                    }

                    int soLuongCur = 1;
                    try {
                        soLuongCur = Integer.parseInt(productQuantityTextView.getText().toString().trim());
                    } catch (Exception e) {
                        return;
                    }

                    if (soLuongCur < 1) {
                        productQuantityTextView.setText(1 + "");
                        totalAmountTextView.setText(MotSoPhuongThucBoTro.formatTienSangVND(1 * sanPhamCur.getGiaBan()));

                    } else if (soLuongCur > sanPhamCur.getSoLuongTon()) {
                        productQuantityTextView.setText(sanPhamCur.getSoLuongTon() + "");
                        totalAmountTextView.setText(MotSoPhuongThucBoTro.formatTienSangVND(sanPhamCur.getSoLuongTon() * sanPhamCur.getGiaBan()));
                    }
                    else{
                        totalAmountTextView.setText(MotSoPhuongThucBoTro.formatTienSangVND(soLuongCur * sanPhamCur.getGiaBan()));
                    }


                }
                catch (Exception e){}
            }
        });


        //thêm vào giỏ hàng
        btn_ThemVaoGioHang_forDetailSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sanPhamCur.getSoLuongTon()==0){
                    Toast.makeText(Detail_SanPham_For_Home_Activity.this, "Đã hết hàng!", Toast.LENGTH_SHORT).show();
                }
                else{
                    int soLuongCur=1;
                    try {
                        soLuongCur = Integer.parseInt(productQuantityTextView.getText().toString().trim());
                    } catch (Exception e) {
                    }

                    SanPhamDTO sanPhamDTO= new SanPhamDTO();
                    sanPhamDTO.setId(sanPhamCur.getId());
                    sanPhamDTO.setTenSP(sanPhamCur.getTenSP());
                    sanPhamDTO.setLoai(sanPhamCur.getLoai());
                    sanPhamDTO.setGiaBan(sanPhamCur.getGiaBan());
                    sanPhamDTO.setSoLuongTon(soLuongCur);//đây hiểu là số lượng mua nhé
                    sanPhamDTO.setHinhAnh(sanPhamCur.getHinhAnh());
                    sanPhamDTO.setDaXoa(sanPhamCur.isDaXoa());
                    sanPhamDTO.setMoTa(sanPhamCur.getMoTa());
                    sanPhamDTO.setDaThich(sanPhamCur.isDaThich());

                    DataCurrent.danhSachSanPhamCoTrongGioHang.add(sanPhamDTO);
                    Toast.makeText(Detail_SanPham_For_Home_Activity.this, "Thêm thành công!", Toast.LENGTH_SHORT).show();

                }
            }
        });


        //vào activity đánh giá sản phẩm
        btn_DanhGia_forDetailSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detail_SanPham_For_Home_Activity.this, CommentListForDetailSanPhamActivity.class);
                intent.putExtra("sanPhamId", sanPhamCur.getId());
                startActivity(intent);
            }
        });
    }

    boolean daThich=false;
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
                    txtTonKhoSanPham_forDetailProduct.setText(""+sanPhamDTO.getSoLuongTon());


                    sanPhamDTO.setDaThich(daThich);
                    if(sanPhamDTO.isDaThich()){
                        img_traitim_forDetailSanPham.setImageResource(R.drawable.ic_favorite_red);
                    }
                    else{
                        img_traitim_forDetailSanPham.setImageResource(R.drawable.icon_favourite);
                    }

                    sanPhamCur=sanPhamDTO;
                } else {
                    Toast.makeText(Detail_SanPham_For_Home_Activity.this, "Có lỗi xảy ra!", Toast.LENGTH_SHORT).show();
                }
            }
        }, Detail_SanPham_For_Home_Activity.this);

        sanPhamDA.execute(params);


    }
}

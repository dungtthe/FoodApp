package com.example.foodapp.view.main_view.home;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.example.foodapp.R;
import com.example.foodapp.model.DTO.DataCurrent;
import com.example.foodapp.model.DTO.SanPhamDTO;
import com.example.foodapp.view.main_view.MotSoPhuongThucBoTro;


import java.util.List;

public class monngonhomnay_Adapter extends RecyclerView.Adapter<monngonhomnay_Adapter.MyViewHolder> {
    private List<SanPhamDTO> listItem;


    public monngonhomnay_Adapter(List<SanPhamDTO> listItem){
        this.listItem = listItem;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_monngonhomnay_forhome,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }
    // onBindViewHolder là 1 vòng for for(int position = 0; position< listItem.Size(),position++)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SanPhamDTO sanpham=listItem.get(position);
        holder.product_image_for_monngonhomnay.setImageBitmap(sanpham.getHinhAnh());
        holder.product_name_for_monngonhomnay.setText(sanpham.getTenSP());
        holder.product_price_number_for_monngonhomnay.setText(MotSoPhuongThucBoTro.formatTienSangVND(sanpham.getGiaBan()));
        holder.product_type_for_monngonhomnay.setText(MotSoPhuongThucBoTro.getTenLoaiSanPham(sanpham.getLoai()));


//        holder.product_image_for_monngonhomnay.setOnClickListener(v -> {
//            Intent intent = new Intent(v.getContext(), Detail_SanPham_For_Home_Activity.class);
//            intent.putExtra("sanPhamId", sanpham.getId());
//            intent.putExtra("daThich", sanpham.isDaThich());
//            ((Activity) v.getContext()).startActivityForResult(intent, 1);
//
//        });


        holder.btn_add_for_monngonhomnay.setOnClickListener(v -> {
            if (sanpham.getSoLuongTon() == 0) {
                Toast.makeText(v.getContext(), "Sản phẩm đã hết", Toast.LENGTH_SHORT).show();
            } else {
                if (DataCurrent.isCoTrongGioHang(sanpham.getId())) {
                    Toast.makeText(v.getContext(), "Sản phẩm đã có trong giỏ hàng!", Toast.LENGTH_SHORT).show();
                    return;
                }

                SanPhamDTO sanPhamDTO = new SanPhamDTO();
                sanPhamDTO.setId(sanpham.getId());
                sanPhamDTO.setTenSP(sanpham.getTenSP());
                sanPhamDTO.setLoai(sanpham.getLoai());
                sanPhamDTO.setGiaBan(sanpham.getGiaBan());
                sanPhamDTO.setSoLuongTon(1); // đây hiểu là số lượng mua nhé
                sanPhamDTO.setHinhAnh(sanpham.getHinhAnh());
                sanPhamDTO.setDaXoa(sanpham.isDaXoa());
                sanPhamDTO.setMoTa(sanpham.getMoTa());
                sanPhamDTO.setDaThich(sanpham.isDaThich());

                DataCurrent.danhSachSanPhamCoTrongGioHang.add(sanPhamDTO);
                Toast.makeText(v.getContext(), "Thêm thành công!", Toast.LENGTH_SHORT).show();
            }
        });


    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView product_image_for_monngonhomnay;
        private TextView product_name_for_monngonhomnay;
        private TextView product_price_number_for_monngonhomnay;
        private TextView product_type_for_monngonhomnay;
        private ImageButton btn_add_for_monngonhomnay;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            product_name_for_monngonhomnay=itemView.findViewById(R.id.product_name_for_monngonhomnay);
            product_image_for_monngonhomnay=itemView.findViewById(R.id.product_image_for_monngonhomnay);
            product_price_number_for_monngonhomnay=itemView.findViewById(R.id.product_price_number_for_monngonhomnay_home);
            product_type_for_monngonhomnay=itemView.findViewById(R.id.product_type_for_monngonhomnay);
            btn_add_for_monngonhomnay=itemView.findViewById(R.id.btn_add_for_monngonhomnay);
        }
    }



}

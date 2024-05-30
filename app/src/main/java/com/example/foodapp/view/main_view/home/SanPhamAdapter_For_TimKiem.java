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
import androidx.recyclerview.widget.RecyclerView;
import com.example.foodapp.R;
import com.example.foodapp.model.DA.SanPhamThichDA;
import com.example.foodapp.model.DTO.DataCurrent;
import com.example.foodapp.model.DTO.SanPhamDTO;
import com.example.foodapp.view.main_view.home.Detail_SanPham_For_Home_Activity;
import com.example.foodapp.view.main_view.MotSoPhuongThucBoTro;

import java.util.List;

public class SanPhamAdapter_For_TimKiem extends RecyclerView.Adapter<SanPhamAdapter_For_TimKiem.MyViewHolder> {
    private List<SanPhamDTO> listItem;

    public SanPhamAdapter_For_TimKiem(List<SanPhamDTO> listItem){
        this.listItem = listItem;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_sanpham_for_xemtatca, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SanPhamDTO sanpham = listItem.get(position);
        holder.imageView.setImageBitmap(sanpham.getHinhAnh());
        holder.productName.setText(sanpham.getTenSP());
        holder.productQuantity.setText(String.valueOf(sanpham.getSoLuongTon()));
        holder.productPrice.setText(MotSoPhuongThucBoTro.formatTienSangVND(sanpham.getGiaBan()));

        holder.imageView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), Detail_SanPham_For_Home_Activity.class);
            intent.putExtra("sanPhamId", sanpham.getId());
            intent.putExtra("daThich", sanpham.isDaThich());
            ((Activity) v.getContext()).startActivityForResult(intent, 1);
        });

        if (sanpham.isDaThich()) {
            holder.favouriteButton.setImageResource(R.drawable.ic_favorite_red); // Icon trái tim màu đỏ
        } else {
            holder.favouriteButton.setImageResource(R.drawable.icon_favourite); // Icon trái tim bình thường
        }

        holder.favouriteButton.setOnClickListener(v -> {
            sanpham.setDaThich(!sanpham.isDaThich());
            if (sanpham.isDaThich()) {
                SanPhamThichDA.likeSanpham(v.getContext(), DataCurrent.khachHangDTOCur.getId(), sanpham.getId());
            } else {
                SanPhamThichDA.removeLikeSanpham(v.getContext(), DataCurrent.khachHangDTOCur.getId(), sanpham.getId());
            }
            notifyItemChanged(position);
        });

        holder.addToCartButton.setOnClickListener(v -> {
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

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private ImageView imageView;
        private TextView productName;
        private TextView productPrice;
        private TextView productQuantity;
        ImageButton favouriteButton;
        ImageButton addToCartButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardview_loaisp_for_xemtatca);
            imageView = itemView.findViewById(R.id.product_image_for_xemtatca);
            productName = itemView.findViewById(R.id.product_name_for_xemtatca);
            productQuantity = itemView.findViewById(R.id.product_quantity_for_xemtatca);
            productPrice = itemView.findViewById(R.id.price_for_xemtatca);
            favouriteButton = itemView.findViewById(R.id.favourite_button_for_xemtatca);
            addToCartButton = itemView.findViewById(R.id.add_to_cart_button_for_xemtatca);
        }
    }
}

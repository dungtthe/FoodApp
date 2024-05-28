package com.example.foodapp.view.main_view.home;

import com.example.foodapp.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.model.DTO.SanPhamDTO;
import com.example.foodapp.view.main_view.MotSoPhuongThucBoTro;

import java.util.List;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.SanPhamViewHolder> {

    private List<SanPhamDTO> sanphamList;

    public SanPhamAdapter(List<SanPhamDTO> sanphamList) {
        this.sanphamList = sanphamList;
    }

    @NonNull
    @Override
    public SanPhamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_product, parent, false);
        return new SanPhamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamViewHolder holder, int position) {
        SanPhamDTO sanpham = sanphamList.get(position);
        holder.sanphamName.setText(sanpham.getTenSP());
        holder.sanphamType.setText("Loại: " + sanpham.getLoai());
        holder.sanphamQuantity.setText("Số lượng: " + sanpham.getSoLuongTon());
        holder.sanphamPrice.setText( MotSoPhuongThucBoTro.formatTienSangVND(sanpham.getGiaBan()));
        if (sanpham.getHinhAnh() != null) {
            holder.sanphamImage.setImageBitmap(sanpham.getHinhAnh());
        } else {
            holder.sanphamImage.setImageResource(R.drawable.img_default_for_product);
        }
    }

    @Override
    public int getItemCount() {
        return sanphamList.size();
    }

    static class SanPhamViewHolder extends RecyclerView.ViewHolder {
        ImageView sanphamImage;
        TextView sanphamName, sanphamType, sanphamQuantity, sanphamPrice;
        ImageButton favoriteButton, addToCartButton;

        public SanPhamViewHolder(@NonNull View itemView) {
            super(itemView);
            sanphamImage = itemView.findViewById(R.id.product_image);
            sanphamName = itemView.findViewById(R.id.product_name);
            sanphamType = itemView.findViewById(R.id.product_type);
            sanphamQuantity = itemView.findViewById(R.id.product_quantity);
            sanphamPrice = itemView.findViewById(R.id.product_price);
            favoriteButton = itemView.findViewById(R.id.favorite_button);
            addToCartButton = itemView.findViewById(R.id.add_to_cart_button);
        }
    }
}

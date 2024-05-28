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

import java.util.List;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.SanPhamViewHolder> {

    private List<SanPhamDTO> productList;

    public SanPhamAdapter(List<SanPhamDTO> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public SanPhamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_product, parent, false);
        return new SanPhamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamViewHolder holder, int position) {
        SanPhamDTO product = productList.get(position);
        holder.productName.setText(product.getTenSP());
        holder.productType.setText("Loại: " + product.getLoai());
        holder.productQuantity.setText("Số lượng: " + product.getSoLuongTon());
        holder.productPrice.setText(product.getGiaNhap() + " VND");

        if (product.getHinhAnh() != null) {
            holder.productImage.setImageBitmap(product.getHinhAnh());
        } else {
            holder.productImage.setImageResource(R.drawable.img_default_for_product);
        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class SanPhamViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName, productType, productQuantity, productPrice;
        ImageButton favoriteButton, addToCartButton;

        public SanPhamViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image);
            productName = itemView.findViewById(R.id.product_name);
            productType = itemView.findViewById(R.id.product_type);
            productQuantity = itemView.findViewById(R.id.product_quantity);
            productPrice = itemView.findViewById(R.id.product_price);
            favoriteButton = itemView.findViewById(R.id.favorite_button);
            addToCartButton = itemView.findViewById(R.id.add_to_cart_button);
        }
    }
}

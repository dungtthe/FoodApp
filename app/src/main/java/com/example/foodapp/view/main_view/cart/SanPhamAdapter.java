package com.example.foodapp.view.main_view.cart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.model.DTO.SanPhamDTO;

import java.util.List;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.ProductViewHolder> {

    private List<SanPhamDTO> listProduct;

    public SanPhamAdapter(List<SanPhamDTO> listProduct) {
        this.listProduct = listProduct;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart,parent,false);
        return new SanPhamAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        SanPhamDTO product = listProduct.get(position);
        if(product==null){
            return;
        }
        holder.imgProduct.setImageBitmap(product.getHinhAnh());
        holder.txtTenProduct.setText(product.getTenSP());
        holder.txtGiaProduct.setText(product.getGiaBan()+" VND");
        holder.txtSoLuongProduct.setText(product.getSoLuongTon()+"");
    }

    @Override
    public int getItemCount() {
        if(listProduct!=null){
            return listProduct.size();
        }
        return 0;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{
        //khai bao nhung tp co trong layout item
        private ImageView imgProduct;
        private TextView txtTenProduct;
        private TextView txtGiaProduct;
        private TextView txtSoLuongProduct;
        private TextView txtTongGiaProduct;


        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            //anh xa view thong qua id
            imgProduct = itemView.findViewById(R.id.img_HinhAnh);
            txtTenProduct=itemView.findViewById(R.id.tv_TenHienThi);
            txtGiaProduct=itemView.findViewById(R.id.tv_Gia);
            txtSoLuongProduct=itemView.findViewById(R.id.tv_SoLuong);
            txtTongGiaProduct=itemView.findViewById(R.id.tv_GiaTien);

        }
    }
}

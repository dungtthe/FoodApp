package com.example.foodapp.view.main_view.home;

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
import com.example.foodapp.model.DTO.SanPhamDTO;


import java.util.List;

public class DanhMucSanPham_Adapter extends RecyclerView.Adapter<DanhMucSanPham_Adapter.MyViewHolder> {
    private List<SanPhamDTO> listItem;


    public DanhMucSanPham_Adapter(List<SanPhamDTO> listItem){
        this.listItem = listItem;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_danhmucsp,parent,false);
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
        holder.imageView.setImageBitmap(sanpham.getHinhAnh());
        holder.productname.setText(sanpham.getTenSP());
        //holder.producttype.setText(sanpham.getLoai());
        //holder.productquantity.setText(sanpham.getSoLuongTon());
        // holder.productprice.setText(sanpham.getGiaBan() + " VND");

    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
        private ImageView imageView;
        private TextView productname;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardview_danhmucsp);
            imageView= itemView.findViewById(R.id.product_image);
            productname = itemView.findViewById(R.id.product_name);

        }
    }



}
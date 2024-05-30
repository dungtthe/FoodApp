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

public class monngonhomnay_Adapter extends RecyclerView.Adapter<monngonhomnay_Adapter.MyViewHolder> {
    private List<SanPhamDTO> listItem;


    public monngonhomnay_Adapter(List<SanPhamDTO> listItem){
        this.listItem = listItem;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_monngonhomnay,parent,false);
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
        private TextView productprice,  productquantity;
        ImageButton addToCartButton;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardview_monngonhomnay);
            imageView= itemView.findViewById(R.id.product_image);
            productname = itemView.findViewById(R.id.product_name);
            productquantity = itemView.findViewById(R.id.product_quantity_number);
            productprice = itemView.findViewById(R.id.product_price_number);
            addToCartButton = itemView.findViewById(R.id.btn_add);

        }
    }



}

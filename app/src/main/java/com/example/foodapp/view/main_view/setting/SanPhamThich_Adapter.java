package com.example.foodapp.view.main_view.setting;

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

public class SanPhamThich_Adapter extends RecyclerView.Adapter<SanPhamThich_Adapter.MyViewHolder> {
    private List<SanPhamDTO> listItem;

    public SanPhamThich_Adapter(List<SanPhamDTO> listItem){
        this.listItem = listItem;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_favourite_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }
    // onBindViewHolder là 1 vòng for for(int position = 0; position< listItem.Size(),position++)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SanPhamDTO favourite_item=listItem.get(position);
//            holder.imageView.setImageResource(favourite_item.getImageId());
        holder.productname.setText(favourite_item.getTenSP());
        holder.productprice.setText(favourite_item.getGiaBan() + " VND");

    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
        private ImageView productimage;
        private TextView productname;
        private TextView productprice;
        ImageButton cancelButton, addToCartButton;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardview);
            productimage= itemView.findViewById(R.id.product_image);
            productname = itemView.findViewById(R.id.product_name);
            productprice = itemView.findViewById(R.id.product_price_number);
            cancelButton = itemView.findViewById(R.id.cancel_button);
            addToCartButton = itemView.findViewById(R.id.add_to_cart_button);
            // Xử lý sự kiện click của cancelButton
            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Xử lý sự kiện khi nhấn nút "cancel_button"
                    Toast.makeText(view.getContext(), "Nút 'cancel_button' đã được nhấn", Toast.LENGTH_SHORT).show();
                    // Thêm các xử lý khác tại đây
                }
            });
        }
    }



}

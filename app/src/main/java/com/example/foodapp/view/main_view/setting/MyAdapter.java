package com.example.foodapp.view.main_view.setting;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.foodapp.R;
import com.example.foodapp.model.DTO.favourite_product;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<favourite_product> listItem;

    public MyAdapter(List<favourite_product> listItem){
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
            favourite_product favourite_item=listItem.get(position);
//            holder.imageView.setImageResource(favourite_item.getImageId());
            holder.productname.setText(favourite_item.getName());
            holder.productprice.setText(favourite_item.getPrice() + " VND");

    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
        private ImageView productimage;
        private TextView productname;
        private TextView productprice;
        ImageButton favoriteButton, addToCartButton;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardview);
            productimage= itemView.findViewById(R.id.product_image);
            productname = itemView.findViewById(R.id.product_name);
            productprice = itemView.findViewById(R.id.product_price_number);
            favoriteButton = itemView.findViewById(R.id.favorite_button);
            addToCartButton = itemView.findViewById(R.id.add_to_cart_button);
        }
    }



}

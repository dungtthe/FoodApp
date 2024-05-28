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
import com.example.foodapp.model.DTO.payment_history_item;

import java.util.List;

public class payment_history_Adapter extends RecyclerView.Adapter<payment_history_Adapter.MyViewHolder> {
    private List<payment_history_item> listItem;

    public payment_history_Adapter(List<payment_history_item> listItem){
        this.listItem = listItem;
    }
    @NonNull
    @Override
    public payment_history_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_payment_history,parent,false);
        return new payment_history_Adapter.MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }
    // onBindViewHolder là 1 vòng for for(int position = 0; position< listItem.Size(),position++)
    @Override
    public void onBindViewHolder(@NonNull payment_history_Adapter.MyViewHolder holder, int position) {
        payment_history_item payment_item=listItem.get(position);
//            holder.imageView.setImageResource(favourite_item.getImageId());
        //holder.productname.setText(payment_item.getName());
        //holder.productprice.setText(payment_item.getPrice() + " VND");


    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
        private ImageView productimage;
        private TextView productname;
        private TextView productprice,time_text,delivery_time_text,payment_time_text;
        ImageButton buy_btn;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardview_payment_history);
            productimage= itemView.findViewById(R.id.product_image);
            productname = itemView.findViewById(R.id.product_name);
            productprice = itemView.findViewById(R.id.product_price_number);
            delivery_time_text = itemView.findViewById(R.id.delivery_time_text);
            payment_time_text = itemView.findViewById(R.id.payment_time_text);
            time_text = itemView.findViewById(R.id.time_text);
            buy_btn = itemView.findViewById(R.id.buy_btn);

        }
    }

}

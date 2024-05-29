package com.example.foodapp.view.main_view.cart.purchase_order;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.view.main_view.cart.Product;

import java.util.List;

public class Item_OfPurchaseOrderAdapter extends RecyclerView.Adapter<Item_OfPurchaseOrderAdapter.ProductViewHolder> {
    private List<Product> listProduct;
    public Item_OfPurchaseOrderAdapter(List<Product> listProduct) {
        this.listProduct = listProduct;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart_of_purchase_order,parent,false);
        return new Item_OfPurchaseOrderAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = listProduct.get(position);
        if(product==null){
            return;
        }
        holder.imgProduct.setImageResource(product.getIdImage());
        holder.txtTenProduct.setText(product.getTenSP()+"");
        holder.txtGiaProduct.setText(product.getGiaSP()+" VND");
        holder.txtSoLuongProduct.setText(product.getSoLuong()+"");
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



        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            //anh xa view thong qua id
            imgProduct = itemView.findViewById(R.id.product_image3);
            txtTenProduct=itemView.findViewById(R.id.product_name3);
            txtGiaProduct=itemView.findViewById(R.id.product_price3);
            txtSoLuongProduct=itemView.findViewById(R.id.number_text3);


        }
    }
}

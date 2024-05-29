package com.example.foodapp.view.main_view.cart.purchase_order;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;

import java.util.List;

public class Item_PurchaseOrderAdapter extends RecyclerView.Adapter<Item_PurchaseOrderAdapter.ProductViewHolder> {
    private List<OuterPurchaseOrder> listProduct;

    public Item_PurchaseOrderAdapter(List<OuterPurchaseOrder> listProduct) {
        this.listProduct = listProduct;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart_purchase_order, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        OuterPurchaseOrder product = listProduct.get(position);
        if (product == null) {
            return;
        }
        holder.txtThanhToan.setText("Tổng thanh toán: "+product.getTongGia());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(holder.rcvItems.getContext(), LinearLayoutManager.VERTICAL, false);
        holder.rcvItems.setLayoutManager(linearLayoutManager);

        Item_OfPurchaseOrderAdapter adapter = new Item_OfPurchaseOrderAdapter(product.getInnerItems());
        holder.rcvItems.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        if (listProduct != null) {
            return listProduct.size();
        }
        return 0;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        private TextView txtThanhToan;
        private RecyclerView rcvItems;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            txtThanhToan = itemView.findViewById(R.id.txtThanhToan);
            rcvItems = itemView.findViewById(R.id.rcvItemPurchaseOrder);
        }
    }
}

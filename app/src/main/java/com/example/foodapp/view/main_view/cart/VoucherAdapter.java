
package com.example.foodapp.view.main_view.cart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;

import java.util.ArrayList;
import java.util.List;

public class VoucherAdapter extends RecyclerView.Adapter<VoucherAdapter.VoucherViewHolder> {

    private List<Voucher> FulllistVoucher;
    private List<Voucher> partialList;
    private boolean showAll = false;

    public VoucherAdapter(List<Voucher> listVoucher) {

        this.FulllistVoucher =listVoucher ;
        this.partialList = new ArrayList<>();
        // Chỉ lấy 2 phần tử đầu tiên
        for (int i = 0; i < Math.min(2, listVoucher.size()); i++) {
            partialList.add(listVoucher.get(i));
        }
    }

    @NonNull
    @Override
    public VoucherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_voucher,parent,false);
        return new VoucherAdapter.VoucherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VoucherViewHolder holder, int position) {
        Voucher voucher = showAll ? FulllistVoucher.get(position) : partialList.get(position);
        if(voucher==null){
            return;
        }
        holder.imgVoucher.setImageResource(voucher.getImgHinhAnh());
        holder.txtHanSD.setText("Hạn sử dụng: "+voucher.getHanSD());
        holder.txtMoTa.setText("Đơn tối thiểu: "+voucher.getMoTa()+" VND");
        holder.txtMenhGia.setText("Giảm tối đa: "+voucher.getMenhGia()+" VND");

    }

    @Override
    public int getItemCount() {
        if(FulllistVoucher==null||partialList==null){
            return 0;
        }
        return showAll ? FulllistVoucher.size() : partialList.size();
    }
    public void toggleShowItems() {
        showAll = !showAll;
        notifyDataSetChanged();
    }

    public boolean isShowingAll() {
        return showAll;
    }
    public class VoucherViewHolder extends RecyclerView.ViewHolder{
        //khai bao nhung tp co trong layout item
        private ImageView imgVoucher;
        private TextView txtHanSD;
        private TextView txtMoTa;
        private TextView txtMenhGia;


        public VoucherViewHolder(@NonNull View itemView) {
            super(itemView);
            imgVoucher=itemView.findViewById(R.id.img_HinhAnh);
            txtMenhGia=itemView.findViewById(R.id.txtMenhGia);
            txtMoTa=itemView.findViewById(R.id.txtMoTa);
            txtHanSD=itemView.findViewById(R.id.txtHanSD);

        }
    }

}


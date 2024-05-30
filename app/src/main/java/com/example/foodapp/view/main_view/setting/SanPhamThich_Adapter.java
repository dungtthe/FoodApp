package com.example.foodapp.view.main_view.setting;

import android.app.Activity;
import android.content.Intent;
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
import com.example.foodapp.model.DA.SanPhamThichDA;
import com.example.foodapp.model.DTO.DataCurrent;
import com.example.foodapp.model.DTO.SanPhamDTO;
import com.example.foodapp.view.main_view.home.Detail_SanPham_For_Home_Activity;


import java.util.List;

public class SanPhamThich_Adapter extends RecyclerView.Adapter<SanPhamThich_Adapter.MyViewHolder> {
    private List<SanPhamDTO> listItem;

    public SanPhamThich_Adapter(List<SanPhamDTO> listItem) {
        this.listItem = listItem;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_favourite_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    // onBindViewHolder là 1 vòng for for(int position = 0; position< listItem.Size(),position++)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SanPhamDTO sanpham = listItem.get(position);
        holder.productimage.setImageBitmap(sanpham.getHinhAnh());
        holder.productname.setText(sanpham.getTenSP());
        holder.productprice.setText(sanpham.getGiaBan() + " VND");


        holder.cancelButton.setOnClickListener(v -> {
            int newPosition = holder.getAdapterPosition();
            if (newPosition != RecyclerView.NO_POSITION) {
                SanPhamThichDA.removeLikeSanpham(v.getContext(), DataCurrent.khachHangDTOCur.getId(), sanpham.getId());
                listItem.remove(newPosition);
                notifyItemRemoved(newPosition);
                notifyItemRangeChanged(newPosition, listItem.size());
            }
        });


        holder.addToCartButton.setOnClickListener(v -> {
            if (sanpham.getSoLuongTon() == 0) {
                Toast.makeText(v.getContext(), "Sản phẩm đã hết", Toast.LENGTH_SHORT).show();
            } else {

                if (DataCurrent.isCoTrongGioHang(sanpham.getId())) {
                    Toast.makeText(v.getContext(), "Sản phẩm đã có trong giỏ hàng!", Toast.LENGTH_SHORT).show();
                    return;
                }

                SanPhamDTO sanPhamDTO = new SanPhamDTO();
                sanPhamDTO.setId(sanpham.getId());
                sanPhamDTO.setTenSP(sanpham.getTenSP());
                sanPhamDTO.setLoai(sanpham.getLoai());
                sanPhamDTO.setGiaBan(sanpham.getGiaBan());
                sanPhamDTO.setSoLuongTon(1);//đây hiểu là số lượng mua nhé
                sanPhamDTO.setHinhAnh(sanpham.getHinhAnh());
                sanPhamDTO.setDaXoa(sanpham.isDaXoa());
                sanPhamDTO.setMoTa(sanpham.getMoTa());
                sanPhamDTO.setDaThich(sanpham.isDaThich());

                DataCurrent.danhSachSanPhamCoTrongGioHang.add(sanPhamDTO);
                Toast.makeText(v.getContext(), "Thêm thành công!", Toast.LENGTH_SHORT).show();
            }


        });


        holder.productimage.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), Detail_SanPham_For_Home_Activity.class);
            intent.putExtra("sanPhamId", sanpham.getId());
            intent.putExtra("daThich", true);
            ((Activity) v.getContext()).startActivityForResult(intent, 1);

        });


    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private ImageView productimage;
        private TextView productname;
        private TextView productprice;
        ImageButton cancelButton, addToCartButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardview_yeuthich_forsetting);
            productimage = itemView.findViewById(R.id.product_image_yeuthich_forsetting);
            productname = itemView.findViewById(R.id.product_name_yeuthich_forsetting);
            productprice = itemView.findViewById(R.id.product_price_number_yeuthich_forsetting);
            cancelButton = itemView.findViewById(R.id.cancel_button_yeuthich_forsetting);
            addToCartButton = itemView.findViewById(R.id.add_to_cart_button_yeuthich_forsetting);
        }
    }


}

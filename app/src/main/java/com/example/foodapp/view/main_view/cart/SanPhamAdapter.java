package com.example.foodapp.view.main_view.cart;

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
import com.example.foodapp.model.DTO.DataCurrent;
import com.example.foodapp.model.DTO.SanPhamDTO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.ProductViewHolder> {

    private List<SanPhamDTO> listProduct;
    private Set<Integer> selectedPositions = new HashSet<>();

    public SanPhamAdapter(List<SanPhamDTO> listProduct) {
        this.listProduct = listProduct;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new SanPhamAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        SanPhamDTO product = listProduct.get(position);
        if (product == null) {
            return;
        }
        holder.imgProduct.setImageBitmap(product.getHinhAnh());
        holder.txtTenProduct.setText(product.getTenSP());
        holder.txtGiaProduct.setText(product.getGiaBan() + " VND");
        holder.txtSoLuongProduct.setText(String.valueOf(product.getSoLuongTon()));
        holder.txtTongGiaProduct.setText(product.getSoLuongTon()*product.getGiaBan()+"");

        // Cập nhật trạng thái của item
        holder.imgNutTichItemCart.setImageResource(
                selectedPositions.contains(position) ? R.drawable.iconselect : R.drawable.icon_selectvoucher_false
        );

        holder.itemCartCur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cập nhật trạng thái chọn của item
                if (selectedPositions.contains(position)) {
                    selectedPositions.remove(position);
                    if(DataCurrent.danhSachSanPhamCoTrongHoaDon.contains(listProduct.get(position))){
                        DataCurrent.danhSachSanPhamCoTrongHoaDon.remove(listProduct.get(position));
                    }
                } else {
                    selectedPositions.add(position);
                    if(!DataCurrent.danhSachSanPhamCoTrongHoaDon.contains(listProduct.get(position))){
                        DataCurrent.danhSachSanPhamCoTrongHoaDon.add(listProduct.get(position));
                    }
                }
                notifyDataSetChanged(); // Thông báo thay đổi để cập nhật giao diện


            }
        });

        holder.btnGiamSL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soLuongCur = product.getSoLuongTon();
                if (soLuongCur > 1) {
                    soLuongCur--;
                    product.setSoLuongTon(soLuongCur);
                    holder.txtSoLuongProduct.setText(String.valueOf(soLuongCur));
                    notifyDataSetChanged();
                } else {
                    Toast.makeText(v.getContext(), "Số lượng tối thiểu là 1!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.btnTangSL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soLuongCur = product.getSoLuongTon();
                soLuongCur++;
                product.setSoLuongTon(soLuongCur);
                holder.txtSoLuongProduct.setText(String.valueOf(soLuongCur));
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (listProduct != null) {
            return listProduct.size();
        }
        return 0;
    }
    // Phương thức để chọn tất cả các mục
    public void selectAll() {
        for (int i = 0; i < listProduct.size(); i++) {
            selectedPositions.add(i);
        }
        notifyDataSetChanged();
    }


    // Phương thức để bỏ chọn tất cả các mục
    public void deselectAll() {
        selectedPositions.clear();
        notifyDataSetChanged();
    }
    public class ProductViewHolder extends RecyclerView.ViewHolder {
        //khai bao nhung tp co trong layout item
        private ImageView imgProduct;
        private TextView txtTenProduct;
        private TextView txtGiaProduct;
        private TextView txtSoLuongProduct;
        private TextView txtTongGiaProduct;
        private CardView itemCartCur;
        private ImageButton imgNutTichItemCart;
        private TextView btnTangSL, btnGiamSL;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            //anh xa view thong qua id
            imgProduct = itemView.findViewById(R.id.img_HinhAnh);
            txtTenProduct = itemView.findViewById(R.id.tv_TenHienThi);
            txtGiaProduct = itemView.findViewById(R.id.tv_Gia);
            txtSoLuongProduct = itemView.findViewById(R.id.tv_SoLuong);
            txtTongGiaProduct = itemView.findViewById(R.id.tv_GiaTien);
            itemCartCur = itemView.findViewById(R.id.ItemCartCurrent);
            imgNutTichItemCart = itemView.findViewById(R.id.imgNutTichItemCart);
            btnGiamSL = itemView.findViewById(R.id.btn_GiamSoLuong);
            btnTangSL = itemView.findViewById(R.id.btn_TangSoLuong);
        }
    }
}

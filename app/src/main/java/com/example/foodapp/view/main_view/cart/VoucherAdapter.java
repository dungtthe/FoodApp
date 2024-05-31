package com.example.foodapp.view.main_view.cart;

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
import com.example.foodapp.model.DTO.DataCurrent;
import com.example.foodapp.model.DTO.VoucherDTO;

import java.util.ArrayList;
import java.util.List;

public class VoucherAdapter extends RecyclerView.Adapter<VoucherAdapter.VoucherViewHolder> {

    private List<VoucherDTO> fullListVoucher;
    private List<VoucherDTO> partialList;
    private boolean showAll = false;
    private int selectedPosition = -1; // Biến để lưu vị trí của item được chọn

    public VoucherAdapter(List<VoucherDTO> listVoucher) {
        this.fullListVoucher = listVoucher;
        this.partialList = new ArrayList<>();
        for (int i = 0; i < Math.min(1, fullListVoucher.size()); i++) {
            partialList.add(fullListVoucher.get(i));
        }
    }

    @NonNull
    @Override
    public VoucherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_voucher, parent, false);
        return new VoucherAdapter.VoucherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VoucherViewHolder holder, int position) {
        VoucherDTO voucher = showAll ? fullListVoucher.get(position) : partialList.get(position);
        if (voucher == null) {
            return;
        }
        holder.imgVoucher.setImageResource(voucher.getHinhAnh());
        holder.txtHanSD.setText("Hạn sử dụng: " + voucher.getNgayKetThuc());
        holder.txtChiTiet.setText(voucher.getChiTiet());
        holder.txtTenVoucher.setText(voucher.getTenVoucher());

        // Cập nhật trạng thái của item
        holder.imgNutTichVoucher.setImageResource(
                holder.getAdapterPosition() == selectedPosition ? R.drawable.iconselect : R.drawable.icon_selectvoucher_false
        );

        holder.itemVoucherCur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cập nhật selectedPosition và thông báo cho adapter
                if (selectedPosition == holder.getAdapterPosition()) {
                    selectedPosition = -1; // Bỏ chọn nếu cùng vị trí được click lại
                    if (DataCurrent.danhSachVoucherApDungChoHoaDon.contains(voucher)) {
                        DataCurrent.danhSachVoucherApDungChoHoaDon.remove(voucher);

                    }
                } else {
                    selectedPosition = holder.getAdapterPosition();
                    if (!DataCurrent.danhSachVoucherApDungChoHoaDon.contains(voucher)) {
                        if (voucher.getLoaiVoucher() == 0 || voucher.getLoaiVoucher() == 1) {
                            for(int i=0;i<DataCurrent.danhSachVoucherApDungChoHoaDon.size();i++){
                                if(DataCurrent.danhSachVoucherApDungChoHoaDon.get(i).getLoaiVoucher()==voucher.getLoaiVoucher()){
                                    DataCurrent.danhSachVoucherApDungChoHoaDon.remove(DataCurrent.danhSachVoucherApDungChoHoaDon.get(i));
                                }
                            }
                            DataCurrent.danhSachVoucherApDungChoHoaDon.add(voucher);

                        }
                    }
                }
                notifyDataSetChanged(); // Thông báo thay đổi để cập nhật giao diện
            }
        });
    }

    @Override
    public int getItemCount() {
        return showAll ? fullListVoucher.size() : partialList.size();
    }

    public void toggleShowItems() {
        showAll = !showAll;
        notifyDataSetChanged();
    }

    public boolean isShowingAll() {
        return showAll;
    }

    public void clearSelection() {
        selectedPosition = -1;
        notifyDataSetChanged();
    }

    public class VoucherViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgVoucher;
        private TextView txtHanSD;
        private TextView txtChiTiet;
        private TextView txtTenVoucher;
        private CardView itemVoucherCur;
        private ImageButton imgNutTichVoucher;

        public VoucherViewHolder(@NonNull View itemView) {
            super(itemView);
            imgVoucher = itemView.findViewById(R.id.img_HinhAnh);
            txtTenVoucher = itemView.findViewById(R.id.txtTenVoucher);
            txtChiTiet = itemView.findViewById(R.id.txtChiTiet);
            txtHanSD = itemView.findViewById(R.id.txtHanSD);
            itemVoucherCur = itemView.findViewById(R.id.itemVoucherCurrent);
            imgNutTichVoucher = itemView.findViewById(R.id.imgNutTichVoucher);
        }
    }
}

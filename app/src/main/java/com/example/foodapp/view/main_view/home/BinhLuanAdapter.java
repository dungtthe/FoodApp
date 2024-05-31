package com.example.foodapp.view.main_view.home;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.model.DTO.BinhLuanDTO;
import com.example.foodapp.model.DTO.DataCurrent;

import java.util.List;

public class BinhLuanAdapter extends RecyclerView.Adapter<BinhLuanAdapter.MyViewHolder> {
    private List<BinhLuanDTO> listItem;

    public BinhLuanAdapter(List<BinhLuanDTO> listItem) {
        this.listItem = listItem;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_comment, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        BinhLuanDTO binhLuan = listItem.get(position);

        if(binhLuan.getKhachHangId()!= DataCurrent.khachHangDTOCur.getId()) {
            holder.userName.setText(binhLuan.getTenKhachHang());
        }
        else{
            holder.userName.setTextColor(Color.RED);
            holder.userName.setText("Bạn");
        }
        holder.commentContent.setText(binhLuan.getNoiDung());
        holder.commentTime.setText(binhLuan.getNgayBL().toString());
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private ImageView icon;
        private TextView userName;
        private TextView commentContent;
        private TextView commentTime;
        private ImageView iconSent;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardview_comment);
            icon = itemView.findViewById(R.id.icon_comment);
            userName = itemView.findViewById(R.id.user_name_comment);
            commentContent = itemView.findViewById(R.id.noidungcomment);
            commentTime = itemView.findViewById(R.id.time_text);
            iconSent = itemView.findViewById(R.id.icon_sent);
        }
    }
}

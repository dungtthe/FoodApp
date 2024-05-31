package com.example.foodapp.view.main_view.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.foodapp.R;
import com.example.foodapp.model.DA.BinhLuanDA;
import com.example.foodapp.model.DA.QueryParameter;
import com.example.foodapp.model.DTO.BinhLuanDTO;
import com.example.foodapp.model.DTO.DataCurrent;

import java.util.ArrayList;
import java.util.List;

public class CommentListForDetailSanPhamActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BinhLuanAdapter binhLuanAdapter;
    private List<BinhLuanDTO> binhLuanList;
    private  EditText editTextNoiDungSendComment;
    private int sanPhamId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_list);

        // Toàn màn hình
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        sanPhamId = getIntent().getIntExtra("sanPhamId", -1);

        // Nút back
        ImageView imageView = findViewById(R.id.image_header_back);
        imageView.setOnClickListener(v -> finish());

        recyclerView = findViewById(R.id.recycler_view_comment);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Sử dụng LinearLayoutManager để kiểm tra

        binhLuanList = new ArrayList<>();
        binhLuanAdapter = new BinhLuanAdapter(binhLuanList); // Khởi tạo adapter với danh sách ban đầu
        recyclerView.setAdapter(binhLuanAdapter);

        danhSachBinhLuan(this);

        ImageView buttonImageSendComment = findViewById(R.id.button_image_send_comment);
         editTextNoiDungSendComment = findViewById(R.id.edit_text_noidungsend_comment);

        buttonImageSendComment.setOnClickListener(v -> {
            String input = editTextNoiDungSendComment.getText().toString().trim();
            if (input.isEmpty()) {
                Toast.makeText(CommentListForDetailSanPhamActivity.this, "Vui lòng nhập nội dung hợp lệ", Toast.LENGTH_SHORT).show();
            } else {
                insertBinhLuan(DataCurrent.khachHangDTOCur.getId(), sanPhamId, input, this);
            }
        });
    }

    private void danhSachBinhLuan(Context context) {
        String query = "SELECT BinhLuan.*, KhachHang.HoTen AS TenKhachHang FROM BinhLuan " +
                "INNER JOIN KhachHang ON BinhLuan.KhachHang_id = KhachHang.ID " +
                "WHERE BinhLuan.SanPham_id = ? " +
                "ORDER BY BinhLuan.NgayBL ASC";
        List<QueryParameter> parameters = new ArrayList<>();
        parameters.add(new QueryParameter(1, sanPhamId));
        Object[] params = new Object[parameters.size() + 1];
        params[0] = query;
        for (int i = 0; i < parameters.size(); i++) {
            params[i + 1] = parameters.get(i);
        }

        BinhLuanDA binhLuanDA = new BinhLuanDA(new BinhLuanDA.DatabaseCallback() {
            @Override
            public void onQueryExecuted(String query, List<BinhLuanDTO> result, boolean isSuccess) {
                if (isSuccess && !result.isEmpty()) {
                    binhLuanList.clear();
                    binhLuanList.addAll(result);
                    binhLuanAdapter.notifyDataSetChanged();
                }
            }
        }, context);

        binhLuanDA.execute(params);
    }

    private void insertBinhLuan(int khachHangId, int sanPhamId, String noiDung, Context context) {
        String query = "INSERT INTO BinhLuan (NoiDung, KhachHang_id, SanPham_id) VALUES (?, ?, ?)";
        List<QueryParameter> queryParameters = new ArrayList<>();
        queryParameters.add(new QueryParameter(1, noiDung));
        queryParameters.add(new QueryParameter(2, khachHangId));
        queryParameters.add(new QueryParameter(3, sanPhamId));
        Object[] params = new Object[queryParameters.size() + 1];
        params[0] = query;
        for (int i = 0; i < queryParameters.size(); i++) {
            params[i + 1] = queryParameters.get(i);
        }

        BinhLuanDA binhLuanDA = new BinhLuanDA(new BinhLuanDA.DatabaseCallback() {
            @Override
            public void onQueryExecuted(String query, List<BinhLuanDTO> result, boolean isSuccess) {
                if (isSuccess) {
                    try {
                        // Tắt bàn phím
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(editTextNoiDungSendComment.getWindowToken(), 0);

                        // Làm rỗng EditText
                        editTextNoiDungSendComment.setText("");
                    }
                    catch (Exception e){}

                    danhSachBinhLuan(context);
                    Toast.makeText(CommentListForDetailSanPhamActivity.this, "Đã tải bình luận lên", Toast.LENGTH_SHORT).show();
                }
            }
        }, context);

        binhLuanDA.execute(params);
    }
}

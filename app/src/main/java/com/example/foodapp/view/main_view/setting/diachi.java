package com.example.foodapp.view.main_view.setting;

import static com.example.foodapp.model.DTO.DataCurrent.khachHangDTOCur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.foodapp.R;
import com.example.foodapp.model.DA.DiaChiGiaoHangDA;
import com.example.foodapp.model.DA.QueryParameter;
import com.example.foodapp.model.DTO.DiaChiGiaoHangDTO;

import java.util.ArrayList;
import java.util.List;

public class diachi extends AppCompatActivity {

    RadioGroup groupDiachi;
    private ImageView btn_back;
    List<DiaChiGiaoHangDTO> diaChiList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diachi);

        groupDiachi = findViewById(R.id.group_diachi);
        int id = khachHangDTOCur.getId();

        // Hiển thị địa chỉ
        hienThiDiaChi(id, groupDiachi, this);

        // Lấy tham chiếu đến LinearLayout
        LinearLayout linearLayout = findViewById(R.id.linearLayout_2);

        // Đặt một trình nghe sự kiện cho LinearLayout
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Điều hướng đến activity ThemDiaChi khi LinearLayout được nhấn
                Intent intent = new Intent(diachi.this, ThemDiaChi.class);
                startActivity(intent);
            }
        });

        // Đặt trình nghe sự kiện cho RadioGroup
        groupDiachi.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (DiaChiGiaoHangDTO diachi : diaChiList) {
                    if (group.findViewById(checkedId).getTag().equals(diachi.getId())) {
                        capNhatLuaChonDiaChi(diachi, true, diachi.this);
                    } else {
                        capNhatLuaChonDiaChi(diachi, false, diachi.this);
                    }
                }
            }
        });
        btn_back = findViewById(R.id.icon_back_diachi);
        // Thiết lập sự kiện click cho LinearLayout "Đã thích"
        btn_back.setOnClickListener(v -> handleBackButtonClicked());
    }
    private void handleBackButtonClicked() {
        // Xử lý sự kiện click button "Đổi mk"
        finish();
    }

    public void hienThiDiaChi(int id, RadioGroup group, Context context) {
        String query = "SELECT * FROM DiaChiGiaoHang WHERE KhachHang_id = ?";
        List<QueryParameter> parameters = new ArrayList<>();
        parameters.add(new QueryParameter(1, id));

        Object[] params = new Object[parameters.size() + 1];
        params[0] = query;
        for (int i = 0; i < parameters.size(); i++) {
            params[i + 1] = parameters.get(i);
        }

        DiaChiGiaoHangDA diaChiGiaoHangDA = new DiaChiGiaoHangDA(new DiaChiGiaoHangDA.DatabaseCallback() {
            @Override
            public void onQueryExecuted(String query, List<DiaChiGiaoHangDTO> result, boolean isSuccess) {
                if (isSuccess && !result.isEmpty()) {
                    diaChiList.clear();
                    diaChiList.addAll(result);
                    for (DiaChiGiaoHangDTO diachi : result) {
                        // Cập nhật giao diện người dùng trên luồng chính
                        runOnUiThread(() -> {
                            // Tạo một RadioButton cho mỗi địa chỉ và thêm vào RadioGroup
                            RadioButton radioButton = new RadioButton(context);
                            radioButton.setText(diachi.getDiaChi());
                            radioButton.setTag(diachi.getId());
                            group.addView(radioButton);

                            // Chọn RadioButton nếu loaiDiaChi là true
                            if (diachi.getLoaiDiaChi()) {
                                radioButton.setChecked(true);
                            }
                        });
                    }
                } else {
                    // Xử lý lỗi hoặc kết quả rỗng
                    runOnUiThread(() -> {
                        // Hiển thị một thông báo cho người dùng, ví dụ: Toast
                        Toast.makeText(context, "Không có địa chỉ giao hàng nào.", Toast.LENGTH_SHORT).show();
                    });
                }
            }
        }, context);
        diaChiGiaoHangDA.execute(params);
    }

    private void capNhatLuaChonDiaChi(DiaChiGiaoHangDTO diachi, boolean isSelected, Context context) {
        // Cập nhật thuộc tính loaiDiaChi
        diachi.setLoaiDiaChi(isSelected);

        // Cập nhật cơ sở dữ liệu
        String updateQuery = "UPDATE DiaChiGiaoHang SET loaiDiaChi = ? WHERE id = ?";
        List<QueryParameter> updateParameters = new ArrayList<>();
        updateParameters.add(new QueryParameter(1, isSelected));
        updateParameters.add(new QueryParameter(2, diachi.getId()));

        Object[] updateParams = new Object[updateParameters.size() + 1];
        updateParams[0] = updateQuery;
        for (int i = 0; i < updateParameters.size(); i++) {
            updateParams[i + 1] = updateParameters.get(i);
        }

        DiaChiGiaoHangDA diaChiGiaoHangDA = new DiaChiGiaoHangDA(new DiaChiGiaoHangDA.DatabaseCallback() {
            @Override
            public void onQueryExecuted(String query, List<DiaChiGiaoHangDTO> result, boolean isSuccess) {
                if (!isSuccess) {
                    runOnUiThread(() -> {
                        Toast.makeText(context, "Cập nhật địa chỉ thất bại.", Toast.LENGTH_SHORT).show();
                    });
                }
            }
        }, context);
        diaChiGiaoHangDA.execute(updateParams);
    }
}

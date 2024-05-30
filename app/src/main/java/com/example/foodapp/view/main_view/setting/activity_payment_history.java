package com.example.foodapp.view.main_view.setting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import com.example.foodapp.R;
import com.example.foodapp.model.DTO.payment_history_item;
import com.example.foodapp.view.main_view.setting.payment_history_Adapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class activity_payment_history extends AppCompatActivity {
    private RecyclerView recyclerView;
    private payment_history_Adapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_history);

        recyclerView = findViewById(R.id.payment_history_recycler_view);

        List<payment_history_item> payment_itemList = new ArrayList<>();

        // Định nghĩa các thời gian cụ thể
        // Tạo các đối tượng Date
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.MAY, 28, 10, 30, 0);
        Date tgiangiaohang = calendar.getTime();

        calendar.set(2023, Calendar.MAY, 29, 15, 0, 0);
        Date tgiannhanhang = calendar.getTime();

        calendar.set(2023, Calendar.MAY, 30, 9, 45, 0);
        Date tgianthanhtoan = calendar.getTime();

        // Thêm payment_history_item vào payment_itemList
        payment_itemList.add(new payment_history_item(1, "Bánh mì", tgiangiaohang, tgiannhanhang, tgianthanhtoan, 10000, R.drawable.banhmi, "Tiền mặt"));
        payment_itemList.add(new payment_history_item(1, "Bánh mì", tgiangiaohang, tgiannhanhang, tgianthanhtoan, 10000, R.drawable.banhmi, "Tiền mặt"));
        payment_itemList.add(new payment_history_item(1, "Bánh mì", tgiangiaohang, tgiannhanhang, tgianthanhtoan, 10000, R.drawable.banhmi, "Tiền mặt"));
        payment_itemList.add(new payment_history_item(1, "Bánh mì", tgiangiaohang, tgiannhanhang, tgianthanhtoan, 10000, R.drawable.banhmi, "Tiền mặt"));
        payment_itemList.add(new payment_history_item(1, "Bánh mì", tgiangiaohang, tgiannhanhang, tgianthanhtoan, 10000, R.drawable.banhmi, "Tiền mặt"));
        payment_itemList.add(new payment_history_item(1, "Bánh mì", tgiangiaohang, tgiannhanhang, tgianthanhtoan, 10000, R.drawable.banhmi, "Tiền mặt"));
        payment_itemList.add(new payment_history_item(1, "Bánh mì", tgiangiaohang, tgiannhanhang, tgianthanhtoan, 10000, R.drawable.banhmi, "Tiền mặt"));
        payment_itemList.add(new payment_history_item(1, "Bánh mì", tgiangiaohang, tgiannhanhang, tgianthanhtoan, 10000, R.drawable.banhmi, "Tiền mặt"));
        payment_itemList.add(new payment_history_item(1, "Bánh mì", tgiangiaohang, tgiannhanhang, tgianthanhtoan, 10000, R.drawable.banhmi, "Tiền mặt"));
        payment_itemList.add(new payment_history_item(1, "Bánh mì", tgiangiaohang, tgiannhanhang, tgianthanhtoan, 10000, R.drawable.banhmi, "Tiền mặt"));
        payment_itemList.add(new payment_history_item(1, "Bánh mì", tgiangiaohang, tgiannhanhang, tgianthanhtoan, 10000, R.drawable.banhmi, "Tiền mặt"));
        payment_itemList.add(new payment_history_item(1, "Bánh mì", tgiangiaohang, tgiannhanhang, tgianthanhtoan, 10000, R.drawable.banhmi, "Tiền mặt"));
        payment_itemList.add(new payment_history_item(1, "Bánh mì", tgiangiaohang, tgiannhanhang, tgianthanhtoan, 10000, R.drawable.banhmi, "Tiền mặt"));

        myAdapter = new payment_history_Adapter(payment_itemList);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
    }
}
package com.example.foodapp.view.main_view.cart.purchase_order;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.foodapp.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class activity_PurchaseOrder extends AppCompatActivity {
    private ViewPager2 viewpager;
    private TabLayout tablayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_order);
        //anh xa view
        tablayout = findViewById(R.id.tabLayout);
        viewpager = findViewById(R.id.ViewPager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewpager.setAdapter(adapter);

        new TabLayoutMediator(tablayout, viewpager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("Chờ xác nhận");
                        break;
                    case 1:
                        tab.setText("Đang giao");
                        break;
                    case 2:
                        tab.setText("Đã giao");
                        break;
                    case 3:
                        tab.setText("Đã hủy");
                        break;
                }
            }
        }).attach();

        // Custom tab selected and unselected text colors
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                TextView tabTextView = (TextView) tab.view.getChildAt(1);
                tabTextView.setTextColor(getResources().getColor(R.color.colorSelectedTabText));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                TextView tabTextView = (TextView) tab.view.getChildAt(1);
                tabTextView.setTextColor(getResources().getColor(R.color.colorUnselectedTabText));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Do nothing
            }
        });

    }
}

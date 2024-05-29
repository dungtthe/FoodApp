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

        // Ánh xạ view
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
                    default:
                        tab.setText("Chờ xác nhận");
                        break;
                }
            }
        }).attach();

        // Custom tab selected and unselected text colors
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                updateTabColors();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                updateTabColors();
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                updateTabColors();
            }
        });

        viewpager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                updateTabColors();
            }
        });

        // Đảm bảo cập nhật màu tab khi activity được load
        updateTabColors();
    }

    private void updateTabColors() {
        for (int i = 0; i < tablayout.getTabCount(); i++) {
            TabLayout.Tab tab = tablayout.getTabAt(i);
            if (tab != null) {
                TextView tabTextView = (TextView) tab.view.getChildAt(1);
                if (tab.isSelected()) {
                    tabTextView.setTextColor(getResources().getColor(R.color.colorSelectedTabText));
                } else {
                    tabTextView.setTextColor(getResources().getColor(R.color.colorUnselectedTabText));
                }
            }
        }
    }
}

package com.example.foodapp.view.main_view;
import com.example.foodapp.*;
import com.example.foodapp.view.main_view.cart.CartFragment;
import com.example.foodapp.view.main_view.home.HomeFragment;
import com.example.foodapp.view.main_view.home.fragment_sanpham_list;
import com.example.foodapp.view.main_view.information.InformationFragment;
import com.example.foodapp.view.main_view.setting.SettingFragment;
import com.example.foodapp.view.main_view.support.SupportFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

public class MainViewActivity extends AppCompatActivity {



    private FrameLayout frameLayoutFirst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);


        //chỉnh màu thanh trạng thái
        setUpMauChoThanhTrangThai();

        //home sẽ mở đầu tiên
        frameLayoutFirst=findViewById(R.id.frame_contain);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_contain, new HomeFragment()).commit();


        //sử lý sự kiện để nav
        BottomNavigationView bottomNavigationView= findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragmentDestination = null;
                switch (item.getItemId()){
                    case R.id.action_home:
                     //   fragmentDestination = new HomeFragment();

                        fragmentDestination = new fragment_sanpham_list();

                        break;
                    case R.id.action_info:
                        fragmentDestination = new InformationFragment();
                        break;
                    case R.id.action_cart:
                        fragmentDestination = new CartFragment();
                        break;
                    case R.id.action_chat:
                        fragmentDestination = new SupportFragment();
                        break;
                    case R.id.action_setting:
                        fragmentDestination= new SettingFragment();
                        break;
                }
                if (fragmentDestination == null) {
                    return false;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_contain, fragmentDestination).commit();
                return true;
            }
        });
    }



    private void setUpMauChoThanhTrangThai(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.black));
        }
    }

}
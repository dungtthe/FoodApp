package com.example.foodapp.view.main_view.cart.purchase_order;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;


public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position){
            case 0:
                return new Fragment_ChoXacNhan();
            case 1:
                return new Fragment_DaGiao();
            case 2:
                return new Fragment_DangGiao();
            case 3:
                return new Fragment_DaHuy();
            default:
                return new Fragment_ChoXacNhan();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}

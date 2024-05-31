package com.example.foodapp.view.main_view.setting;

import static com.example.foodapp.model.DTO.DataCurrent.khachHangDTOCur;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.foodapp.R;
import com.example.foodapp.view.main_view.setting.activity_favourite_items;
import com.example.foodapp.view.start.Login;

public class SettingFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private Handler mHandler;
    private LinearLayout linearLayout4, linearLayout5,linearLayout, btn_dangxuat;
    private TextView amstrong;

    public SettingFragment() {
        // Required empty public constructor
    }

    public static SettingFragment newInstance(String param1, String param2) {
        SettingFragment fragment = new SettingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        // Khởi tạo Handler
        mHandler = new Handler(Looper.getMainLooper());

        // Post Runnable để ẩn thanh navigation bar sau 3 giây
        mHandler.postDelayed(this::hideNavigationBar, 000);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);


        // Tìm và lưu reference đến LinearLayout "Đã thích"
        linearLayout = view.findViewById(R.id.linearLayout);
        // Thiết lập sự kiện click cho LinearLayout "Đã thích"
        linearLayout.setOnClickListener(v -> handleDiaChiButtonClicked());

        // Tìm và lưu reference đến LinearLayout "Đã thích"
        linearLayout4 = view.findViewById(R.id.linearLayout4);
        // Thiết lập sự kiện click cho LinearLayout "Đã thích"
        linearLayout4.setOnClickListener(v -> handleLikeButtonClicked());

        // Tìm và lưu reference đến LinearLayout "Đổi mật khẩu"
        linearLayout5 = view.findViewById(R.id.linearLayout5);
        // Thiết lập sự kiện click cho LinearLayout "Đổi mật khẩu"
        linearLayout5.setOnClickListener(v -> handleChangePassButtonClicked());

        btn_dangxuat = view.findViewById(R.id.btn_dangxuat);
        // Thiết lập sự kiện click cho LinearLayout "Đổi mật khẩu"
        btn_dangxuat.setOnClickListener(v -> handleDangXuatButtonClicked());


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Do something else with the view
        amstrong = view.findViewById(R.id.tv_name_caidat);
        amstrong.setText(khachHangDTOCur.getHoTen());
    }

    private void hideNavigationBar() {
        // Lấy activity hiện tại
        Activity activity = requireActivity();

        // Ẩn thanh navigation
        if (activity.getWindow() != null) {
            activity.getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    private void handleLikeButtonClicked() {
        // Xử lý sự kiện click button "Đã thích"
        Intent intent = new Intent(requireContext(), activity_favourite_items.class);
        startActivity(intent);
    }
    private void handleDiaChiButtonClicked() {
        // Xử lý sự kiện click button "Đổi mk"
        Intent intent = new Intent(requireContext(), diachi.class);
        startActivity(intent);
    }
    private void handleChangePassButtonClicked() {
        // Xử lý sự kiện click button "Đổi mk"
        Intent intent = new Intent(requireContext(), activity_changepassword.class);
        startActivity(intent);
    }

    private void handleDangXuatButtonClicked() {
        // Xử lý sự kiện click button "Đổi mk"
        Intent intent = new Intent(requireContext(), Login.class);
        startActivity(intent);
    }
}
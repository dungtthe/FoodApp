package com.example.foodapp.view.main_view.cart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.model.DTO.DataCurrent;
import com.example.foodapp.model.DTO.SanPhamDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CartFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
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

    }

    private RecyclerView rcvListItem;
    private Button btnSelectAll;
    private Button btnPurchase;
    private List<SanPhamDTO> sanPhamList;
    private Boolean DaXoa=false;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        // Ánh xạ các View từ XML
        rcvListItem = view.findViewById(R.id.rcvListItem);
        btnSelectAll = view.findViewById(R.id.btnSelectAll);
        btnPurchase = view.findViewById(R.id.btnPurchase);

        //load dữ liệu vào màn hình giỏ hàng
        loadData(getContext());
        // Thiết lập sự kiện OnClickListener cho các Button
        btnSelectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hành động khi btnSelectAll được nhấn
                // Code xử lý sự kiện khi btnSelectAll được nhấn
            }
        });

        btnPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(),activity_purchase.class);
                startActivity(myIntent);
            }
        });

        return view;
    }

    private void loadData(Context context) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        rcvListItem.setLayoutManager(gridLayoutManager);
        sanPhamList = new ArrayList<>();

        SanPhamAdapter adapter = new SanPhamAdapter(DataCurrent.danhSachSanPhamCoTrongGioHang);
        rcvListItem.setAdapter(adapter);
    }
}
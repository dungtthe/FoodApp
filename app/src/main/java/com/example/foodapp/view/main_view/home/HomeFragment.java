package com.example.foodapp.view.main_view.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodapp.R;
import com.example.foodapp.model.DA.SanPhamDA;
import com.example.foodapp.model.DTO.SanPhamDTO;
import com.example.foodapp.view.main_view.MotSoPhuongThucBoTro;
import com.example.foodapp.view.main_view.setting.activity_changepassword;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements SanPhamDA.DatabaseCallback {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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

    private RecyclerView recyclerViewmonngon;
    //private RecyclerView recyclerViewdanhmuc;
    private monngonhomnay_Adapter monngonhomnayAdapter;
    //private DanhMucSanPham_Adapter danhMucSanPhamAdapter;
    private List<SanPhamDTO> sanPhamList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerViewmonngon = view.findViewById(R.id.recycler_view_monngonhomnay);
        recyclerViewmonngon.setLayoutManager(new GridLayoutManager(getContext(), 2));

        //recyclerViewdanhmuc = view.findViewById(R.id.recycler_view_danhmucsp);
        //recyclerViewdanhmuc.setLayoutManager(new GridLayoutManager(getContext(), 2));

        sanPhamList = new ArrayList<>();

        // Load sản phẩm từ cơ sở dữ liệu
        SanPhamDA sanPhamDA = new SanPhamDA(this, getContext());
        sanPhamDA.execute("SELECT * FROM SanPham WHERE DaXoa = FALSE");

        monngonhomnayAdapter = new monngonhomnay_Adapter(sanPhamList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewmonngon.setLayoutManager(layoutManager);
        recyclerViewmonngon.setAdapter(monngonhomnayAdapter);

        //
//        danhMucSanPhamAdapter = new DanhMucSanPham_Adapter(sanPhamList);
//        recyclerViewdanhmuc.setAdapter(danhMucSanPhamAdapter);


        LinearLayout linearbanhmi = view.findViewById(R.id.linear_banhmi);
        LinearLayout linearkebab = view.findViewById(R.id.linear_kebab);
        LinearLayout linearpizza = view.findViewById(R.id.linear_pizza);
        LinearLayout linearhamburger = view.findViewById(R.id.linear_hamburger);
        LinearLayout linearnuocngot = view.findViewById(R.id.linear_nuocngot);
        LinearLayout linearbimbimlang = view.findViewById(R.id.linear_bimbimlang);

        linearbanhmi.setOnClickListener(v -> handledanhmucButtonClicked());
        linearkebab.setOnClickListener(v -> handledanhmucButtonClicked());
        linearpizza.setOnClickListener(v -> handledanhmucButtonClicked());
        linearhamburger.setOnClickListener(v -> handledanhmucButtonClicked());
        linearnuocngot.setOnClickListener(v -> handledanhmucButtonClicked());
        linearbimbimlang.setOnClickListener(v -> handledanhmucButtonClicked());

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);





        // Tìm kiếm sản phẩm
        EditText searchEditText = view.findViewById(R.id.search_edit_text);
        ImageView searchIcon = view.findViewById(R.id.icon_search);
        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if( searchEditText.getText().toString()==null || searchEditText.getText().toString().equals("")|| MotSoPhuongThucBoTro.isAllWhitespace(searchEditText.getText().toString())){

                    Toast.makeText(v.getContext(), "Vui lòng nhập thông tin tìm kiếm!", Toast.LENGTH_SHORT).show();

                    return;
                }

                String query = searchEditText.getText().toString();
                Intent intent = new Intent(getActivity(), SanPhamListTimKiemActivity.class);
                intent.putExtra("search_query", query);
                startActivity(intent);
            }
        });



        //xem tất cả món ngon hôm nay
        TextView tvXemTatCa=view.findViewById(R.id.tvXemTatCa);
        tvXemTatCa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DanhSachSanPham_ForXemTatCa_Activity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onQueryExecuted(String query, List<SanPhamDTO> result, boolean isSuccess) {
        if (isSuccess && result != null) {
            sanPhamList.clear();
            sanPhamList.addAll(result);
            monngonhomnayAdapter.notifyDataSetChanged();
            //danhMucSanPhamAdapter.notifyDataSetChanged();
        }
    }

    private void handledanhmucButtonClicked() {

        Intent intent = new Intent(requireContext(), danh_muc_san_pham.class);
        startActivity(intent);
    }
}

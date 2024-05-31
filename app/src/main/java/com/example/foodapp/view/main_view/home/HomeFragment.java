package com.example.foodapp.view.main_view.home;

import android.app.Activity;
import android.content.Context;
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
import com.example.foodapp.model.DA.QueryParameter;
import com.example.foodapp.model.DA.SanPhamDA;
import com.example.foodapp.model.DA.SanPhamThichDA;
import com.example.foodapp.model.DTO.DataCurrent;
import com.example.foodapp.model.DTO.SanPhamDTO;
import com.example.foodapp.view.main_view.MotSoPhuongThucBoTro;
import com.example.foodapp.view.main_view.setting.activity_changepassword;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeFragment extends Fragment {

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
    private monngonhomnay_Adapter monngonhomnayAdapter;
    private List<SanPhamDTO> sanPhamList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

//        recyclerViewmonngon = view.findViewById(R.id.recycler_view_monngonhomnay);
//        recyclerViewmonngon.setLayoutManager(new GridLayoutManager(getContext(), 2));
//
//        //recyclerViewdanhmuc = view.findViewById(R.id.recycler_view_danhmucsp);
//        //recyclerViewdanhmuc.setLayoutManager(new GridLayoutManager(getContext(), 2));
//
//        sanPhamList = new ArrayList<>();
//
//        // Load sản phẩm từ cơ sở dữ liệu





        LinearLayout linearbanhmi = view.findViewById(R.id.linear_banhmi);
        LinearLayout linearkebab = view.findViewById(R.id.linear_kebab);
        LinearLayout linearpizza = view.findViewById(R.id.linear_pizza);
        LinearLayout linearhamburger = view.findViewById(R.id.linear_hamburger);
        LinearLayout linearnuocngot = view.findViewById(R.id.linear_nuocngot);
        LinearLayout linearbimbimlang = view.findViewById(R.id.linear_bimbimlang);

        linearbanhmi.setOnClickListener(v -> handledanhmucButtonClicked(1));
        linearkebab.setOnClickListener(v -> handledanhmucButtonClicked(2));
        linearpizza.setOnClickListener(v -> handledanhmucButtonClicked(3));
        linearhamburger.setOnClickListener(v -> handledanhmucButtonClicked(4));
        linearnuocngot.setOnClickListener(v -> handledanhmucButtonClicked(5));
        linearbimbimlang.setOnClickListener(v -> handledanhmucButtonClicked(6));

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




        recyclerViewmonngon = view.findViewById(R.id.recycler_view_monngonhomnay);
        recyclerViewmonngon.setLayoutManager(new GridLayoutManager(getContext(), 2));
        sanPhamList = new ArrayList<>();

        danhSachSanPham_ForMonNgonHomNay(getContext());


    }

    private List<SanPhamDTO> sanPhamListRandom;

    public void danhSachSanPham_ForMonNgonHomNay(Context context) {
        String query = "SELECT * FROM SanPham WHERE DaXoa = false";
        List<QueryParameter> parameters = new ArrayList<>();

        Object[] params = new Object[parameters.size() + 1];
        params[0] = query;
        for (int i = 0; i < parameters.size(); i++) {
            params[i + 1] = parameters.get(i);
        }

        SanPhamDA sanPhamDA = new SanPhamDA(new SanPhamDA.DatabaseCallback() {
            @Override
            public void onQueryExecuted(String query, List<SanPhamDTO> result, boolean isSuccess) {
                if (isSuccess && !result.isEmpty()) {
                    sanPhamList.clear();

                    sanPhamList=result;

                    sanPhamListRandom=getListRanDom(sanPhamList);


                    monngonhomnayAdapter = new monngonhomnay_Adapter(sanPhamListRandom);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                    recyclerViewmonngon.setLayoutManager(layoutManager);
                    recyclerViewmonngon.setAdapter(monngonhomnayAdapter);

                    monngonhomnayAdapter.notifyDataSetChanged();

                    // Gọi phương thức để thiết lập thuộc tính daThich cho các sản phẩm
                    getAllSanPhamThich(context, DataCurrent.khachHangDTOCur.getId(), sanPhamList);

                }
            }
        }, context);

        sanPhamDA.execute(params);

    }

    public void getAllSanPhamThich(Context context, int khachHangId, List<SanPhamDTO> allSanPhamList) {
        SanPhamThichDA sanPhamThichDA = new SanPhamThichDA(new SanPhamThichDA.DatabaseCallback() {
            @Override
            public void onQueryExecuted(String query, List<SanPhamDTO> result, boolean isSuccess) {
                if (isSuccess) {
                    // Thiết lập thuộc tính daThich cho các sản phẩm yêu thích
                    for (SanPhamDTO likedProduct : result) {
                        for (SanPhamDTO product : allSanPhamList) {
                            if (product.getId() == likedProduct.getId()) {
                                product.setDaThich(true);
                                break;
                            }
                        }
                    }
                    // Cập nhật lại adapter để hiển thị
                    monngonhomnayAdapter.notifyDataSetChanged();
                } else {
                }
            }

            @Override
            public void onModifyExecuted(String query, Boolean result, boolean isSuccess) {
            }
        }, context, SanPhamThichDA.ActionType.GET_LIKED_PRODUCTS);

        sanPhamThichDA.execute(khachHangId);
    }

//    @Override
//    public void onQueryExecuted(String query, List<SanPhamDTO> result, boolean isSuccess) {
//        if (isSuccess && result != null) {
//            sanPhamList.clear();
//            sanPhamList.addAll(result);
//            monngonhomnayAdapter.notifyDataSetChanged();
//        }
//    }
//SanPhamDA sanPhamDA = new SanPhamDA(this, getContext());
//        sanPhamDA.execute("SELECT * FROM SanPham WHERE DaXoa = FALSE");
//
//    monngonhomnayAdapter = new monngonhomnay_Adapter(sanPhamList);
//    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
//        recyclerViewmonngon.setLayoutManager(layoutManager);
//        recyclerViewmonngon.setAdapter(monngonhomnayAdapter);
//





    private List<SanPhamDTO> getListRanDom(List<SanPhamDTO> listSanPham) {
        List<SanPhamDTO> ketQua = new ArrayList<>(listSanPham);

        // Xáo trộn danh sách
        Collections.shuffle(ketQua);

        return ketQua;
    }





    private void handledanhmucButtonClicked(int loaiSp) {
        Intent intent = new Intent(getActivity(), danh_muc_san_pham.class);
        intent.putExtra("loadSp", loaiSp);
        startActivity(intent);
    }
}

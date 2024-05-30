package com.example.foodapp.view.main_view.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodapp.R;
import com.example.foodapp.model.DA.SanPhamDA;
import com.example.foodapp.model.DTO.SanPhamDTO;

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
    private RecyclerView recyclerViewdanhmuc;
    private monngonhomnay_Adapter monngonhomnayAdapter;
    private DanhMucSanPham_Adapter danhMucSanPhamAdapter;
    private List<SanPhamDTO> sanPhamList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerViewmonngon = view.findViewById(R.id.recycler_view_monngonhomnay);
        recyclerViewmonngon.setLayoutManager(new GridLayoutManager(getContext(), 2));

        recyclerViewdanhmuc = view.findViewById(R.id.recycler_view_danhmucsp);
        recyclerViewdanhmuc.setLayoutManager(new GridLayoutManager(getContext(), 2));

        sanPhamList = new ArrayList<>();

        // Load sản phẩm từ cơ sở dữ liệu
        SanPhamDA sanPhamDA = new SanPhamDA(this, getContext());
        sanPhamDA.execute("SELECT * FROM SanPham WHERE DaXoa = FALSE");

        monngonhomnayAdapter = new monngonhomnay_Adapter(sanPhamList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewmonngon.setLayoutManager(layoutManager);
        recyclerViewmonngon.setAdapter(monngonhomnayAdapter);

        danhMucSanPhamAdapter = new DanhMucSanPham_Adapter(sanPhamList);
        recyclerViewdanhmuc.setAdapter(danhMucSanPhamAdapter);

        return view;
    }

    @Override
    public void onQueryExecuted(String query, List<SanPhamDTO> result, boolean isSuccess) {
        if (isSuccess && result != null) {
            sanPhamList.clear();
            sanPhamList.addAll(result);
            monngonhomnayAdapter.notifyDataSetChanged();
            danhMucSanPhamAdapter.notifyDataSetChanged();
        }
    }
}

package com.example.foodapp.view.main_view.cart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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
    private Boolean isSelectAll = false;
    private Boolean isMuaHang = false;
    private Boolean isSua = false;
    private TextView txtCart,EditCart;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        // Ánh xạ các View từ XML
        rcvListItem = view.findViewById(R.id.rcvListItem);
        btnSelectAll = view.findViewById(R.id.btnSelectAll);
        btnPurchase = view.findViewById(R.id.btnPurchase);
        txtCart = view.findViewById(R.id.txtCart);
        EditCart = view.findViewById(R.id.EditCart);

        //load dữ liệu vào màn hình giỏ hàng
        loadData(getContext());
        // Thiết lập sự kiện OnClickListener cho các Button
        btnSelectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SanPhamAdapter adapter = (SanPhamAdapter) rcvListItem.getAdapter();
                if (adapter != null) {
                    if (isSelectAll) {
                        adapter.deselectAll();
                        btnSelectAll.setText("Chọn tất cả");
                    } else {
                        adapter.selectAll();
                        btnSelectAll.setText("Bỏ chọn tất cả");
                    }
                    isSelectAll = !isSelectAll;
                }
            }
        });

        btnPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isMuaHang=!isMuaHang;
                if(!isMuaHang){
                    Intent myIntent = new Intent(getActivity(),activity_purchase.class);
                    startActivity(myIntent);
                }else{

                    Log.d("DTT","vao");
                    //nhấn nút xóa thì vào đây
                    for (SanPhamDTO item:DataCurrent.danhSachSanPhamCoTrongHoaDon) {
                        DataCurrent.getDanhSachSanPhamCoTrongGioHang().remove(item);
                    }

                    if(adapter!=null){
                        adapter.notifyDataSetChanged();
                        adapter.updateSelectedPositionsAfterDeletion();
                    }

                }

            }
        });
        EditCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSua=!isSua;
                if(isSua){
                    btnPurchase.setText("Xóa");


                    EditCart.setText("Xong");
                }else{
                    btnPurchase.setText("Mua hàng");
                    EditCart.setText("Sửa");
                }

            }
        });
        return view;
    }

    SanPhamAdapter adapter=null;
    private void loadData(Context context) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        rcvListItem.setLayoutManager(gridLayoutManager);
        sanPhamList = new ArrayList<>();
         adapter = new SanPhamAdapter(DataCurrent.danhSachSanPhamCoTrongGioHang);
        txtCart.setText("Giỏ hàng("+DataCurrent.danhSachSanPhamCoTrongGioHang.size()+")");
        rcvListItem.setAdapter(adapter);
    }
}
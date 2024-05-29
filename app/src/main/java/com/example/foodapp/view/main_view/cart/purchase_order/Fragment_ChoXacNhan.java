package com.example.foodapp.view.main_view.cart.purchase_order;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.view.main_view.cart.Product;
import com.example.foodapp.view.main_view.cart.ProductAdapter;

import java.util.ArrayList;
import java.util.List;

public class Fragment_ChoXacNhan extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public Fragment_ChoXacNhan() {
        // Required empty public constructor
    }

    public static Fragment_ChoXacNhan newInstance(String param1, String param2) {
        Fragment_ChoXacNhan fragment = new Fragment_ChoXacNhan();
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
    private RecyclerView rcvListItemFavorite;
    private ScrollView scv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__cho_xac_nhan, container, false);

        // Đơn hàng
        rcvListItem = view.findViewById(R.id.rcvItem);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvListItem.setLayoutManager(layoutManager);
        Item_PurchaseOrderAdapter adapter = new Item_PurchaseOrderAdapter(getListItemOuter());
        rcvListItem.setAdapter(adapter);

        // Có thể bạn cũng thích
        rcvListItemFavorite = view.findViewById(R.id.rcvItemFavorite);
        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(getContext(), 2);
        rcvListItemFavorite.setLayoutManager(gridLayoutManager2);
        ProductAdapter adapter2 = new ProductAdapter(getListItem2());
        rcvListItemFavorite.setAdapter(adapter2);

        // Sử dụng Handler để cuộn về đầu trang sau khi dữ liệu được tải xong
        scv = view.findViewById(R.id.scv);
        new Handler().postDelayed(() -> scv.scrollTo(0, 0), 100); // Đặt độ trễ nhỏ (ví dụ: 100ms)

        return view;
    }

    private List<OuterPurchaseOrder> getListItemOuter() {
        List<OuterPurchaseOrder> list = new ArrayList<>();
        list.add(new OuterPurchaseOrder("100.000 VND", getListItemInnerPurchaseOrder()));
        list.add(new OuterPurchaseOrder("100.000 VND", getListItemInnerPurchaseOrder()));
        return list;
    }

    private List<Product> getListItemInnerPurchaseOrder() {
        List<Product> listProduct = new ArrayList<>();
        listProduct.add(new Product("CoCa", 20000, R.drawable.coca, 2));
        listProduct.add(new Product("Pepsi", 20000, R.drawable.coca, 2));
        return listProduct;
    }

    private List<Product> getListItem2() {
        List<Product> listProduct = new ArrayList<>();
        listProduct.add(new Product("CoCa", 20000, R.drawable.coca, 2));
        listProduct.add(new Product("CoCa", 20000, R.drawable.coca, 2));
        listProduct.add(new Product("CoCa", 20000, R.drawable.coca, 2));
        listProduct.add(new Product("CoCa", 20000, R.drawable.coca, 2));
        listProduct.add(new Product("CoCa", 20000, R.drawable.coca, 2));
        listProduct.add(new Product("CoCa", 20000, R.drawable.coca, 2));

        return listProduct;
    }
}

package com.example.foodapp.view.main_view.cart.purchase_order;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.model.DA.HoaDonDA;
import com.example.foodapp.model.DA.QueryParameter;
import com.example.foodapp.model.DA.SanPhamDA;
import com.example.foodapp.model.DTO.ChiTietHoaDonDTO;
import com.example.foodapp.model.DTO.DataCurrent;
import com.example.foodapp.model.DTO.HoaDonChiTietDTO;
import com.example.foodapp.model.DTO.SanPhamDTO;
import com.example.foodapp.view.main_view.cart.Product;
import com.example.foodapp.view.main_view.cart.ProductAdapter;
import com.example.foodapp.view.main_view.home.SanPhamAdapter_For_DanhMucSP;

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



        // Sử dụng Handler để cuộn về đầu trang sau khi dữ liệu được tải xong
        scv = view.findViewById(R.id.scv);
        new Handler().postDelayed(() -> scv.scrollTo(0, 0), 100); // Đặt độ trễ nhỏ (ví dụ: 100ms)

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Đơn hàng
        rcvListItem = view.findViewById(R.id.rcvItem);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvListItem.setLayoutManager(layoutManager);

        // Lấy dữ liệu đơn hàng chưa thanh toán của khách hàng
        int khachHangId = DataCurrent.khachHangDTOCur.getId();
        setListItemOuter(khachHangId, getContext());

        // Có thể bạn cũng thích
        rcvListItemFavorite = view.findViewById(R.id.rcvItemFavorite);
        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(getContext(), 2);
        rcvListItemFavorite.setLayoutManager(gridLayoutManager2);
        ProductAdapter adapter2 = new ProductAdapter(getListItem2());
        rcvListItemFavorite.setAdapter(adapter2);
    }




    private void setListItemOuter(int khachHangId, Context context) {
        String query = "SELECT hd.*, cthd.*, sp.* FROM HoaDon hd " +
                "JOIN ChiTietHoaDon cthd ON hd.ID = cthd.HoaDon_id " +
                "JOIN SanPham sp ON cthd.SanPham_id = sp.ID " +
                "WHERE hd.KhachHang_id = ? AND hd.DaThanhToan = FALSE";
        List<QueryParameter> parameters = new ArrayList<>();
        parameters.add(new QueryParameter(1, khachHangId));

        Object[] params = new Object[parameters.size() + 1];
        params[0] = query;
        for (int i = 0; i < parameters.size(); i++) {
            params[i + 1] = parameters.get(i);
        }

        HoaDonDA hoaDonDA = new HoaDonDA(new HoaDonDA.DatabaseCallback() {
            @Override
            public void onQueryExecuted(String query, List<HoaDonChiTietDTO> result, boolean isSuccess) {
                if (isSuccess && !result.isEmpty()) {
                    List<OuterPurchaseOrder> outerPurchaseOrderList = new ArrayList<>();

                    for (HoaDonChiTietDTO hoaDonChiTietDTO : result) {
                        List<Product> productList = new ArrayList<>();
                        for (ChiTietHoaDonDTO chiTietHoaDon : hoaDonChiTietDTO.getChiTietHoaDonList()) {
                            SanPhamDTO sanPham = chiTietHoaDon.getSanPham();
                            Product product = new Product(sanPham.getTenSP(), chiTietHoaDon.getDonGiaBan(), R.drawable.img_default_for_product, chiTietHoaDon.getSoLuong());
                            productList.add(product);
                        }
                        OuterPurchaseOrder outerPurchaseOrder = new OuterPurchaseOrder(String.valueOf(hoaDonChiTietDTO.getTongTien()), productList);
                        outerPurchaseOrderList.add(outerPurchaseOrder);
                    }

                    // Cập nhật danh sách và adapter
                    rcvListItem.setAdapter(new Item_PurchaseOrderAdapter(outerPurchaseOrderList));
                }
            }
        }, context);

        hoaDonDA.execute(params);
    }



    //1 list chứa các đơn hàng chưa thanh toán
    private List<OuterPurchaseOrder> getListItemOuter() {
        List<OuterPurchaseOrder> list = new ArrayList<>();
//        list.add(new OuterPurchaseOrder("100.000 VND", getListItemInnerPurchaseOrder()));
//        list.add(new OuterPurchaseOrder("100.000 VND", getListItemInnerPurchaseOrder()));
        return list;
    }




    //danh sách các sản phẩm chứa đơn hàng chưa thanh toán
    private List<Product> getListItemInnerPurchaseOrder() {
        List<Product> listProduct = new ArrayList<>();
        listProduct.add(new Product("CoCa", 20000, R.drawable.coca, 2));
        listProduct.add(new Product("Pepsi", 20000, R.drawable.coca, 2));
        return listProduct;
    }











    //có thể bạn cũng thích
    private List<Product> getListItem2() {
        List<Product> listProduct = new ArrayList<>();
//        listProduct.add(new Product("CoCa", 20000, R.drawable.coca, 2));
//        listProduct.add(new Product("CoCa", 20000, R.drawable.coca, 2));
//        listProduct.add(new Product("CoCa", 20000, R.drawable.coca, 2));
//        listProduct.add(new Product("CoCa", 20000, R.drawable.coca, 2));
//        listProduct.add(new Product("CoCa", 20000, R.drawable.coca, 2));
//        listProduct.add(new Product("CoCa", 20000, R.drawable.coca, 2));

        return listProduct;
    }
}

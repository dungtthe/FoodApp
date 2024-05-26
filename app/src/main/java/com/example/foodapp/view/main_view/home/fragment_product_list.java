package com.example.foodapp.view.main_view.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.model.DTO.Product;
import com.example.foodapp.view.main_view.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_product_list#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_product_list extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragment_product_list() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_product_list.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_product_list newInstance(String param1, String param2) {
        fragment_product_list fragment = new fragment_product_list();
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



    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Product> productList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_product_list, container, false);

        View view = inflater.inflate(R.layout.fragment_product_list, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        productList = new ArrayList<>();


        productList.add(new Product(1,"Pepsi", 10,10000, R.drawable.pepsi,"Nước uống"));
        productList.add(new Product(1,"Pepsi", 10,10000, R.drawable.pepsi,"Nước uống"));
        productList.add(new Product(1,"Pepsi", 10,10000, R.drawable.pepsi,"Nước uống"));
        productList.add(new Product(1,"Pepsi", 10,10000, R.drawable.pepsi,"Nước uống"));
        productList.add(new Product(1,"Pepsi", 10,10000, R.drawable.pepsi,"Nước uống"));
        productList.add(new Product(1,"Pepsi", 10,10000, R.drawable.pepsi,"Nước uống"));
        productList.add(new Product(1,"Pepsi", 10,10000, R.drawable.pepsi,"Nước uống"));
        productList.add(new Product(1,"Pepsi", 10,10000, R.drawable.pepsi,"Nước uống"));
        productList.add(new Product(1,"Pepsi", 10,10000, R.drawable.pepsi,"Nước uống"));
        productList.add(new Product(1,"Pepsi", 10,10000, R.drawable.pepsi,"Nước uống"));
        productList.add(new Product(1,"Pepsi", 10,10000, R.drawable.pepsi,"Nước uống"));
        productList.add(new Product(1,"Pepsi", 10,10000, R.drawable.pepsi,"Nước uống"));
        productList.add(new Product(1,"Pepsi", 10,10000, R.drawable.pepsi,"Nước uống"));
        productList.add(new Product(1,"Pepsi", 10,10000, R.drawable.pepsi,"Nước uống"));
        productList.add(new Product(1,"Pepsi", 10,10000, R.drawable.pepsi,"Nước uống"));
        productList.add(new Product(1,"Pepsi", 10,10000, R.drawable.pepsi,"Nước uống"));
        productList.add(new Product(1,"Pepsi", 10,10000, R.drawable.pepsi,"Nước uống"));
        productList.add(new Product(1,"Pepsi", 10,10000, R.drawable.pepsi,"Nước uống"));
        productList.add(new Product(1,"Pepsi", 10,10000, R.drawable.pepsi,"Nước uống"));
        productList.add(new Product(1,"Pepsi", 10,10000, R.drawable.pepsi,"Nước uống"));
        productList.add(new Product(1,"Pepsi", 10,10000, R.drawable.pepsi,"Nước uống"));
        productList.add(new Product(1,"Pepsi", 10,10000, R.drawable.pepsi,"Nước uống"));
        productList.add(new Product(1,"Pepsi", 10,10000, R.drawable.pepsi,"Nước uống"));
        productList.add(new Product(1,"Pepsi", 10,10000, R.drawable.pepsi,"Nước uống"));
        productList.add(new Product(1,"Pepsi", 10,10000, R.drawable.pepsi,"Nước uống"));
        productList.add(new Product(1,"Pepsi", 10,10000, R.drawable.pepsi,"Nước uống"));
        productList.add(new Product(1,"Pepsi", 10,10000, R.drawable.pepsi,"Nước uống"));
        productList.add(new Product(1,"Pepsi", 10,10000, R.drawable.pepsi,"Nước uống"));
        productList.add(new Product(1,"Pepsi", 10,10000, R.drawable.pepsi,"Nước uống"));
        productList.add(new Product(1,"Pepsi", 10,10000, R.drawable.pepsi,"Nước uống"));
        productList.add(new Product(1,"Pepsi", 10,10000, R.drawable.pepsi,"Nước uống"));
        productList.add(new Product(1,"Pepsi", 10,10000, R.drawable.pepsi,"Nước uống"));
        productList.add(new Product(1,"Pepsi", 10,10000, R.drawable.pepsi,"Nước uống"));
        productList.add(new Product(1,"Pepsi", 10,10000, R.drawable.pepsi,"Nước uống"));
        productList.add(new Product(1,"Pepsi", 10,10000, R.drawable.pepsi,"Nước uống"));
        productList.add(new Product(1,"Pepsi", 10,10000, R.drawable.pepsi,"Nước uống"));

        productAdapter = new ProductAdapter(productList);
        recyclerView.setAdapter(productAdapter);

        return view;
    }
}
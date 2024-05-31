package com.example.foodapp.view.main_view.cart.purchase_order;

import com.example.foodapp.view.main_view.cart.Product;

import java.util.List;

public class OuterPurchaseOrder {
    private String TongGia;
    private List<Product> innerItems;

    public OuterPurchaseOrder(String title, List<Product> innerItems) {
        this.TongGia = title;
        this.innerItems = innerItems;
    }

    public String getTongGia() {
        return TongGia;
    }

    public List<Product> getInnerItems() {
        return innerItems;
    }

    public void setTitle(String title) {
        this.TongGia = title;
    }

    public void setInnerItems(List<Product> innerItems) {
        this.innerItems = innerItems;
    }
}

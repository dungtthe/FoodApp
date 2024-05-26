package com.example.foodapp.view.main_view.cart;

public class Product {
    private String tenSP;
    private int giaSP;
    private int idImage;
    private int soLuong;
    private int tongGiaMatHang;

    public Product(String tenSP, int giaSP, int idImage, int soLuong) {
        this.tenSP = tenSP;
        this.giaSP = giaSP;
        this.idImage = idImage;
        this.soLuong = soLuong;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getGiaSP() {
        return giaSP;
    }

    public void setGiaSP(int giaSP) {
        this.giaSP = giaSP;
    }

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getTongGiaMatHang() {
        return tongGiaMatHang*soLuong;
    }
}

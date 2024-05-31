package com.example.foodapp.view.main_view.cart;

public class Voucher {
    private int imgHinhAnh;
    private String hanSD;
    private String moTa;
    private String menhGia;

    public Voucher(int imgHinhAnh,String hanSD, String moTa, String menhGia) {
       this.hanSD = hanSD;
        this.moTa = moTa;
        this.menhGia = menhGia;
        this.imgHinhAnh=imgHinhAnh;
    }

    public void setImgHinhAnh(int imgHinhAnh) {
        this.imgHinhAnh = imgHinhAnh;
    }

    public void setHanSD(String hanSD) {
        this.hanSD = hanSD;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public void setMenhGia(String menhGia) {
        this.menhGia = menhGia;
    }

    public int getImgHinhAnh() {
        return imgHinhAnh;
    }

    public String getHanSD() {
        return hanSD;
    }

    public String getMoTa() {
        return moTa;
    }

    public String getMenhGia() {
        return menhGia;
    }
}

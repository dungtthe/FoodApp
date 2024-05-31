package com.example.foodapp.model.DTO;

import android.graphics.Bitmap;

public class KhachHangDTO {
    private int id;
    private String hoTen;
    private String sDT;
    private String eMail;
    private Boolean daXoa;
    private String tenTaiKhoan;
    private String matKhau;
    private Bitmap hinhAnh;

    //Getter và Setter cho các thuộc tính
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getHoTen() {
        return hoTen;
    }
    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getsDT() {
        return sDT;
    }
    public void setsDT(String sDT) {
        this.sDT = sDT;
    }

    public String getMail() {
        return eMail;
    }
    public void setMail(String eMail) {
        this.eMail = eMail;
    }

    public boolean isDaXoa() {
        return daXoa;
    }
    public void setDaXoa(Boolean daXoa) {
        this.daXoa = daXoa;
    }

    public String gettenTaiKhoan() {
        return tenTaiKhoan;
    }
    public void settenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }
    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public Bitmap getHinhAnh() {
        return hinhAnh;
    }
    public void setHinhAnh(Bitmap hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public KhachHangDTO()
    {

    }


    public KhachHangDTO(int id,String hoTen, String sDT, String eMail, String tenTk, String matKhau ) {
        this.id=id;
        this.hoTen = hoTen;
        this.sDT = sDT;
        this.eMail = eMail;
        this.tenTaiKhoan = tenTk;
        this.matKhau = matKhau;
        this.daXoa = false;
    }

    //Thêm 1 user
    public KhachHangDTO(String hoTen, String sDT, String eMail, String tenTk, String matKhau ) {
        this.hoTen = hoTen;
        this.sDT = sDT;
        this.eMail = eMail;
        this.tenTaiKhoan = tenTk;
        this.matKhau = matKhau;
        this.daXoa = false;
    }

}

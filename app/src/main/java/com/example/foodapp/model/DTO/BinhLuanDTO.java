package com.example.foodapp.model.DTO;

import java.sql.Timestamp;

public class BinhLuanDTO {
    private int id;
    private Timestamp ngayBL;
    private String noiDung;
    private int khachHangId;
    private int sanPhamId;
    private String tenKhachHang;

    public BinhLuanDTO() {}

    public BinhLuanDTO(int id, Timestamp ngayBL, String noiDung, int khachHangId, int sanPhamId) {
        this.id = id;
        this.ngayBL = ngayBL;
        this.noiDung = noiDung;
        this.khachHangId = khachHangId;
        this.sanPhamId = sanPhamId;
    }

    public BinhLuanDTO(int id, Timestamp ngayBL, String noiDung, int khachHangId, int sanPhamId, String tenKhachHang) {
        this.id = id;
        this.ngayBL = ngayBL;
        this.noiDung = noiDung;
        this.khachHangId = khachHangId;
        this.sanPhamId = sanPhamId;
        this.tenKhachHang = tenKhachHang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getNgayBL() {
        return ngayBL;
    }

    public void setNgayBL(Timestamp ngayBL) {
        this.ngayBL = ngayBL;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public int getKhachHangId() {
        return khachHangId;
    }

    public void setKhachHangId(int khachHangId) {
        this.khachHangId = khachHangId;
    }

    public int getSanPhamId() {
        return sanPhamId;
    }

    public void setSanPhamId(int sanPhamId) {
        this.sanPhamId = sanPhamId;
    }
}

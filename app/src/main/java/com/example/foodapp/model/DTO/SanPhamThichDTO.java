package com.example.foodapp.model.DTO;

public class SanPhamThichDTO {
    private int id;
    private int khachHangId;
    private int sanPhamId;

    public SanPhamThichDTO() {
    }

    public SanPhamThichDTO(int id,int khachHangId, int sanPhamId) {
        this.id=id;
        this.khachHangId = khachHangId;
        this.sanPhamId = sanPhamId;
    }

    public SanPhamThichDTO(int khachHangId, int sanPhamId) {
        this.khachHangId = khachHangId;
        this.sanPhamId = sanPhamId;
    }

    // Getter và Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

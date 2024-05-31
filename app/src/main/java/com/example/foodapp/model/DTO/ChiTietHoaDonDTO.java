package com.example.foodapp.model.DTO;

public class ChiTietHoaDonDTO {
    private int id;
    private int hoaDonId;
    private int sanPhamId;
    private int soLuong;
    private int donGiaBan;
    private SanPhamDTO sanPham;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHoaDonId() {
        return hoaDonId;
    }

    public void setHoaDonId(int hoaDonId) {
        this.hoaDonId = hoaDonId;
    }

    public int getSanPhamId() {
        return sanPhamId;
    }

    public void setSanPhamId(int sanPhamId) {
        this.sanPhamId = sanPhamId;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getDonGiaBan() {
        return donGiaBan;
    }

    public void setDonGiaBan(int donGiaBan) {
        this.donGiaBan = donGiaBan;
    }

    public SanPhamDTO getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPhamDTO sanPham) {
        this.sanPham = sanPham;
    }
}

package com.example.foodapp.model.DTO;

import android.graphics.Bitmap;

public class SanPhamDTO {
    private int id;
    private String tenSP;
    private int loai;
    private int giaNhap;
    private int giaBan;
    private int soLuongTon;
    private Bitmap hinhAnh;
    private int imageResourceId;
    private boolean daXoa;
    private String moTa;
    private boolean daThich;
    public SanPhamDTO() {
    }


    //không có mô tả
    public SanPhamDTO(int id, String tenSP, int loai, int giaNhap, int soLuongTon, Bitmap hinhAnh, boolean daXoa) {
        this.id = id;
        this.tenSP = tenSP;
        this.loai = loai;
        this.giaNhap = giaNhap;
        this.soLuongTon = soLuongTon;
        this.hinhAnh = hinhAnh;
        this.daXoa = daXoa;

        giaBan = (int) (ThamSoDTO.heSoBan * giaNhap);
    }


    //có mô tả
    public SanPhamDTO(int id, String tenSP, int loai, int giaNhap, int soLuongTon, Bitmap hinhAnh, boolean daXoa, String moTa) {
        this.id = id;
        this.tenSP = tenSP;
        this.loai = loai;
        this.giaNhap = giaNhap;
        this.soLuongTon = soLuongTon;
        this.hinhAnh = hinhAnh;
        this.daXoa = daXoa;
        this.moTa = moTa;

        giaBan = (int) (ThamSoDTO.heSoBan * giaNhap);
    }
    public SanPhamDTO(int loai, int imageResourceId) {

        this.loai = loai;
        this.imageResourceId = imageResourceId;
    }

    // Getter và Setter cho các thuộc tính

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getLoai() {
        return loai;
    }


    public void setLoai(int loai) {
        this.loai = loai;
    }

    public int getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(int giaNhap) {
        this.giaNhap = giaNhap;
        giaBan = (int) (ThamSoDTO.heSoBan * giaNhap);
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public Bitmap getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(Bitmap hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public boolean isDaXoa() {
        return daXoa;
    }

    public void setDaXoa(boolean daXoa) {
        this.daXoa = daXoa;
    }

    public int getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(int giaBan) {
        this.giaBan = giaBan;
    }

    public boolean isDaThich() {
        return daThich;
    }

    public void setDaThich(boolean daThich) {
        this.daThich = daThich;
    }
}
package com.example.foodapp.model.DTO;

public class DiaChiGiaoHangDTO {
    private int id;
    private String diaChi;
    private boolean loaiDiaChi;
    private String tenKhachHang;
    private String sDT;
    public int id_KhachHang;

    //Getter và Setter cho các thuộc tính
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getDiaChi() {
        return diaChi;
    }
    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public boolean getLoaiDiaChi() {
        return loaiDiaChi;
    }
    public void setLoaiDiaChi(boolean loaiDiaChi) {
        this.loaiDiaChi = loaiDiaChi;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }
    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getsDT() {
        return sDT;
    }
    public void setsDT(String sDT) {
        this.sDT = sDT;
    }

    public int getId_KhachHang() {
        return id_KhachHang;
    }
    public void setId_KhachHang(int id_KhachHang) {
        this.id_KhachHang = id_KhachHang;
    }

    public String getFullDiaChi()
    {
        return tenKhachHang + " | " + sDT +"\n" +diaChi;
    }
    public DiaChiGiaoHangDTO()
    {

    }

    //Thêm địa chỉ giao hàng
    public DiaChiGiaoHangDTO(String diaChi, String tenKhachHang, String sDT , int id_KhachHang, boolean loaiDiaChi)
    {
        this.diaChi = diaChi;
        this.tenKhachHang = tenKhachHang;
        this.sDT = sDT;
        this.id_KhachHang = id_KhachHang;
        this.loaiDiaChi = loaiDiaChi;
    }
}

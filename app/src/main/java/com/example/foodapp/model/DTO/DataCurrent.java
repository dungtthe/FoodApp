package com.example.foodapp.model.DTO;

import java.util.ArrayList;
import java.util.List;

public class DataCurrent {
    public static KhachHangDTO khachHangDTOCur;
    public static List<SanPhamDTO> danhSachSanPhamCoTrongGioHang;



    public static boolean isCoTrongGioHang(int sanPhamID){
        if(danhSachSanPhamCoTrongGioHang==null){
            danhSachSanPhamCoTrongGioHang=new ArrayList<>();
        }

        for (SanPhamDTO item:danhSachSanPhamCoTrongGioHang) {
            if(item.getId()==sanPhamID){
                return  true;
            }
        }
        return false;
    }
}

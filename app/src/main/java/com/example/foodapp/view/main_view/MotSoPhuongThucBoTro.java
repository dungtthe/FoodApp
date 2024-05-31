package com.example.foodapp.view.main_view;

import com.example.foodapp.model.DTO.DataCurrent;

public class MotSoPhuongThucBoTro {
    public static String formatTienSangVND(long value) {
        String numStr = Long.toString(value);
        StringBuilder formatted = new StringBuilder();
        int count = 0;
        for (int i = numStr.length() - 1; i >= 0; --i) {
            char ch = numStr.charAt(i);
            formatted.append(ch);
            if (++count != 3 || i == 0) continue;
            formatted.append(',');
            count = 0;
        }
        return formatted.reverse().toString()+" VND";
    }

    public static boolean isAllWhitespace(String str) {
        // Loại bỏ tất cả khoảng trắng ở đầu và cuối chuỗi và kiểm tra độ dài
        return str.trim().isEmpty();
    }
    public static int tinhTongTienHoaDonTruocVoucher(){
        int sum=0;
        for(int i=0;i< DataCurrent.danhSachSanPhamCoTrongHoaDon.size();i++){
            sum+=DataCurrent.danhSachSanPhamCoTrongHoaDon.get(i).getGiaBan()*DataCurrent.danhSachSanPhamCoTrongHoaDon.get(i).getSoLuongTon();
        }
        return sum;
    }
    public static int tinhGiaTriHoaDonSauVoucher(){
        int sum = tinhTongTienHoaDonTruocVoucher();
        int TongChiPhiGiamGia = 0;
        for(int i=0;i<DataCurrent.danhSachVoucherApDungChoHoaDon.size();i++){
            TongChiPhiGiamGia+=DataCurrent.danhSachVoucherApDungChoHoaDon.get(i).getGiaTri();
        }
        sum -= TongChiPhiGiamGia;
        return sum;
    }
    public static int TongGiaTriVoucher(){
        int TongChiPhiGiamGia = 0;
        for(int i=0;i<DataCurrent.danhSachVoucherApDungChoHoaDon.size();i++){
            TongChiPhiGiamGia+=DataCurrent.danhSachVoucherApDungChoHoaDon.get(i).getGiaTri();
        }
        return TongChiPhiGiamGia;
    }
    public static String getTenLoaiSanPham(int loaiSp) {
        switch (loaiSp) {
            case 1:
                return "Bánh mì";
            case 2:
                return "Kebab";
            case 3:
                return "Pizza";
            case 4:
                return "Hamburger";
            case 5:
                return "Nước ngọt";
            case 6:
                return "Bim bim lạng";
            default:
                return "Khác";
        }
    }
}

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
    public static int tinhTongTienHoaDon(){
        int sum=0;
        for(int i=0;i< DataCurrent.danhSachSanPhamCoTrongHoaDon.size();i++){
            sum+=DataCurrent.danhSachSanPhamCoTrongHoaDon.get(i).getGiaBan()*DataCurrent.danhSachSanPhamCoTrongHoaDon.get(i).getSoLuongTon();
        }
        return sum;
    }
    public static int tinhGiaTriHoaDon(){
        return 0;
    }
}

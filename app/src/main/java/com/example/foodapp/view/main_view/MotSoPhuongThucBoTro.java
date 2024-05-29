package com.example.foodapp.view.main_view;

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
}

package com.example.foodapp.model.DA;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.example.foodapp.model.DTO.ChiTietHoaDonDTO;
import com.example.foodapp.model.DTO.HoaDonChiTietDTO;
import com.example.foodapp.model.DTO.SanPhamDTO;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.example.foodapp.R;
import java.util.List;

public class HoaDonDA extends AsyncTask<Object, Void, List<HoaDonChiTietDTO>> {

    private String query;
    private DatabaseCallback callback;
    private boolean isSuccess = false;
    private Context context;

    public HoaDonDA(DatabaseCallback callback, Context context) {
        this.callback = callback;
        this.context = context;
    }

    public void getUnpaidOrdersWithDetailsByCustomerId(int khachHangId) {
        String query = "SELECT hd.*, cthd.*, sp.* FROM HoaDon hd " +
                "JOIN ChiTietHoaDon cthd ON hd.ID = cthd.HoaDon_id " +
                "JOIN SanPham sp ON cthd.SanPham_id = sp.ID " +
                "WHERE hd.KhachHang_id = ? AND hd.DaThanhToan = FALSE";
        List<QueryParameter> queryParameters = new ArrayList<>();
        queryParameters.add(new QueryParameter(1, khachHangId));

        execute(query, queryParameters.toArray());
    }

    @Override
    protected List<HoaDonChiTietDTO> doInBackground(Object... params) {
        query = (String) params[0];
        List<QueryParameter> queryParameters = new ArrayList<>();

        for (int i = 1; i < params.length; i++) {
            if (params[i] instanceof QueryParameter) {
                queryParameters.add((QueryParameter) params[i]);
            }
        }

        List<HoaDonChiTietDTO> hoaDonChiTietDTOList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseHelper.getConnection();
            statement = connection.prepareStatement(query);

            QueryParameter.setStatementParameters(statement, queryParameters);

            if (query.trim().toUpperCase().startsWith("SELECT")) {
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    HoaDonChiTietDTO hoaDonChiTietDTO = new HoaDonChiTietDTO();
                    hoaDonChiTietDTO.setHoaDonId(resultSet.getInt("hd.ID"));
                    hoaDonChiTietDTO.setNgayHD(resultSet.getTimestamp("hd.NgayHD"));
                    hoaDonChiTietDTO.setNhanVienId(resultSet.getInt("hd.NhanVien_id"));
                    hoaDonChiTietDTO.setKhachHangId(resultSet.getInt("hd.KhachHang_id"));
                    hoaDonChiTietDTO.setVoucherId((Integer) resultSet.getObject("hd.Voucher_id"));
                    hoaDonChiTietDTO.setTongTien(resultSet.getInt("hd.TongTien"));
                    hoaDonChiTietDTO.setTrangThai(resultSet.getInt("hd.TrangThai"));
                    hoaDonChiTietDTO.setDaThanhToan(resultSet.getBoolean("hd.DaThanhToan"));
                    hoaDonChiTietDTO.setGhiChu(resultSet.getString("hd.GhiChu"));

                    List<ChiTietHoaDonDTO> chiTietHoaDonList = new ArrayList<>();
                    do {
                        ChiTietHoaDonDTO chiTietHoaDonDTO = new ChiTietHoaDonDTO();
                        chiTietHoaDonDTO.setId(resultSet.getInt("cthd.ID"));
                        chiTietHoaDonDTO.setHoaDonId(resultSet.getInt("cthd.HoaDon_id"));
                        chiTietHoaDonDTO.setSanPhamId(resultSet.getInt("cthd.SanPham_id"));
                        chiTietHoaDonDTO.setSoLuong(resultSet.getInt("cthd.SoLuong"));
                        chiTietHoaDonDTO.setDonGiaBan(resultSet.getInt("cthd.DonGiaBan"));

                        SanPhamDTO sanPhamDTO = new SanPhamDTO();
                        sanPhamDTO.setId(resultSet.getInt("sp.ID"));
                        sanPhamDTO.setTenSP(resultSet.getString("sp.TenSP"));
                        sanPhamDTO.setLoai(resultSet.getInt("sp.Loai"));
                        sanPhamDTO.setGiaNhap(resultSet.getInt("sp.GiaNhap"));
                        sanPhamDTO.setSoLuongTon(resultSet.getInt("sp.SoLuongTon"));
                        sanPhamDTO.setDaXoa(resultSet.getBoolean("sp.DaXoa"));
                        sanPhamDTO.setMoTa(resultSet.getString("sp.MoTa"));
                        byte[] imgBytes = resultSet.getBytes("sp.HinhAnh");
                        if (imgBytes != null) {
                            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imgBytes);
                            Bitmap bitmap = BitmapFactory.decodeStream(byteArrayInputStream);
                            sanPhamDTO.setHinhAnh(bitmap);
                        } else {
                            Bitmap defaultBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.img_default_for_product);
                            sanPhamDTO.setHinhAnh(defaultBitmap);
                        }

                        chiTietHoaDonDTO.setSanPham(sanPhamDTO);
                        chiTietHoaDonList.add(chiTietHoaDonDTO);
                    } while (resultSet.next() && resultSet.getInt("hd.ID") == hoaDonChiTietDTO.getHoaDonId());

                    hoaDonChiTietDTO.setChiTietHoaDonList(chiTietHoaDonList);
                    hoaDonChiTietDTOList.add(hoaDonChiTietDTO);
                }

                isSuccess = !hoaDonChiTietDTOList.isEmpty();
            } else {
                isSuccess = statement.executeUpdate() > 0;
            }

        } catch (ClassNotFoundException | SQLException e) {
            Log.d("DTT", e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return hoaDonChiTietDTOList;
    }

    @Override
    protected void onPostExecute(List<HoaDonChiTietDTO> result) {
        if (callback != null) {
            callback.onQueryExecuted(query, result, isSuccess);
        }
    }

    public interface DatabaseCallback {
        void onQueryExecuted(String query, List<HoaDonChiTietDTO> result, boolean isSuccess);
    }
}

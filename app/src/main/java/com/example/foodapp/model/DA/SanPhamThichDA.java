package com.example.foodapp.model.DA;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.foodapp.model.DTO.SanPhamDTO;
import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import  com.example.foodapp.R;

public class SanPhamThichDA extends AsyncTask<Object, Void, Object> {
    private String query;
    private DatabaseCallback callback;
    private boolean isSuccess = false;
    private Context context;
    private ActionType actionType;

    public enum ActionType {
        GET_LIKED_PRODUCTS,
        REMOVE_LIKED_PRODUCT,
        LIKE_PRODUCT
    }

    public SanPhamThichDA(DatabaseCallback callback, Context context, ActionType actionType) {
        this.callback = callback;
        this.context = context;
        this.actionType = actionType;
    }

    @Override
    protected Object doInBackground(Object... params) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseHelper.getConnection();

            switch (actionType) {
                case GET_LIKED_PRODUCTS:
                    int khachHangId = (int) params[0];
                    query = "SELECT sp.ID, sp.TenSP, sp.Loai, sp.GiaNhap, sp.SoLuongTon, sp.HinhAnh, sp.MoTa, sp.DaXoa " +
                            "FROM SanPhamThich spt " +
                            "JOIN SanPham sp ON spt.SanPham_id = sp.ID " +
                            "WHERE spt.KhachHang_id = ? AND sp.DaXoa = FALSE";
                    statement = connection.prepareStatement(query);
                    statement.setInt(1, khachHangId);

                    resultSet = statement.executeQuery();
                    List<SanPhamDTO> sanPhamDTOList = new ArrayList<>();
                    while (resultSet.next()) {
                        SanPhamDTO sanPhamDTO = new SanPhamDTO();
                        sanPhamDTO.setId(resultSet.getInt("ID"));
                        sanPhamDTO.setTenSP(resultSet.getString("TenSP"));
                        sanPhamDTO.setLoai(resultSet.getInt("Loai"));
                        sanPhamDTO.setGiaNhap(resultSet.getInt("GiaNhap"));
                        sanPhamDTO.setSoLuongTon(resultSet.getInt("SoLuongTon"));
                        sanPhamDTO.setDaXoa(resultSet.getBoolean("DaXoa"));
                        sanPhamDTO.setMoTa(resultSet.getString("MoTa"));
                        byte[] imgBytes = resultSet.getBytes("HinhAnh");
                        if (imgBytes != null) {
                            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imgBytes);
                            Bitmap bitmap = BitmapFactory.decodeStream(byteArrayInputStream);
                            sanPhamDTO.setHinhAnh(bitmap);
                        } else {
                            Bitmap defaultBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.img_default_for_product);
                            sanPhamDTO.setHinhAnh(defaultBitmap);
                        }
                        sanPhamDTOList.add(sanPhamDTO);
                    }
                    isSuccess = !sanPhamDTOList.isEmpty();
                    return sanPhamDTOList;

                case REMOVE_LIKED_PRODUCT:
                    int khachHangIdToRemove = (int) params[0];
                    int sanPhamIdToRemove = (int) params[1];
                    query = "DELETE FROM SanPhamThich WHERE KhachHang_id = ? AND SanPham_id = ?";
                    statement = connection.prepareStatement(query);
                    statement.setInt(1, khachHangIdToRemove);
                    statement.setInt(2, sanPhamIdToRemove);

                    isSuccess = statement.executeUpdate() > 0;
                    return isSuccess;

                case LIKE_PRODUCT:
                    int khachHangIdToLike = (int) params[0];
                    int sanPhamIdToLike = (int) params[1];
                    query = "INSERT INTO SanPhamThich (KhachHang_id, SanPham_id) VALUES (?, ?)";
                    statement = connection.prepareStatement(query);
                    statement.setInt(1, khachHangIdToLike);
                    statement.setInt(2, sanPhamIdToLike);

                    isSuccess = statement.executeUpdate() > 0;
                    return isSuccess;

                default:
                    throw new IllegalArgumentException("Có lỗi xảy ra!");
            }
        } catch (ClassNotFoundException | SQLException e) {
            isSuccess = false;
            e.printStackTrace();
            return null;
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
    }

    @Override
    protected void onPostExecute(Object result) {
        if (callback != null) {
            if (actionType == ActionType.GET_LIKED_PRODUCTS) {
                callback.onQueryExecuted(query, (List<SanPhamDTO>) result, isSuccess);
            } else if (actionType == ActionType.REMOVE_LIKED_PRODUCT || actionType == ActionType.LIKE_PRODUCT) {
                callback.onModifyExecuted(query, (Boolean) result, isSuccess);
            }
        }
    }

    public interface DatabaseCallback {
        void onQueryExecuted(String query, List<SanPhamDTO> result, boolean isSuccess);
        void onModifyExecuted(String query, Boolean result, boolean isSuccess);
    }



    //phương thức lấy danh sách mà khách hàng có id là khachHang_Id thích
    public static void getAllSanPhamThich(Context context,int khachHangId){

        SanPhamThichDA sanPhamThichDA = new SanPhamThichDA(new SanPhamThichDA.DatabaseCallback() {
            @Override
            public void onQueryExecuted(String query, List<SanPhamDTO> result, boolean isSuccess) {
                if (isSuccess) {


                    //đây là danh sách sản phẩm thích nha
                    for (SanPhamDTO sp : result) {

                        Log.d("DTT",sp.getId()+"=="+sp.getTenSP());


                    }
                } else {
                    Toast.makeText(context, "Có lỗi xảy ram!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onModifyExecuted(String query, Boolean result, boolean isSuccess) {
            }
        }, context, SanPhamThichDA.ActionType.GET_LIKED_PRODUCTS);

        sanPhamThichDA.execute(khachHangId);

    }



    //bỏ thích một sản phẩm
    public static void removeLikeSanpham(Context context,int khachHangId,int sanPhamId){
        SanPhamThichDA sanPhamThichDA = new SanPhamThichDA(new SanPhamThichDA.DatabaseCallback() {
            @Override
            public void onQueryExecuted(String query, List<SanPhamDTO> result, boolean isSuccess) {
            }

            @Override
            public void onModifyExecuted(String query, Boolean result, boolean isSuccess) {
                if (isSuccess) {
                    Toast.makeText(context, "Đã bỏ thích sản phẩm!", Toast.LENGTH_SHORT).show();
                    // Cập nhật giao diện để phản ánh việc sản phẩm đã bị bỏ thích
                } else {
                    Toast.makeText(context, "Có lỗi xảy ra!", Toast.LENGTH_SHORT).show();
                }
            }
        }, context, SanPhamThichDA.ActionType.REMOVE_LIKED_PRODUCT);

        sanPhamThichDA.execute(khachHangId, sanPhamId);

    }


    // thích một sản phẩm
    public static void likeSanpham(Context context,int khachHangId,int sanPhamId){
        SanPhamThichDA sanPhamThichDA = new SanPhamThichDA(new SanPhamThichDA.DatabaseCallback() {
            @Override
            public void onQueryExecuted(String query, List<SanPhamDTO> result, boolean isSuccess) {
            }

            @Override
            public void onModifyExecuted(String query, Boolean result, boolean isSuccess) {
                if (isSuccess) {
                    Toast.makeText(context, "Đã thích sản phẩm!", Toast.LENGTH_SHORT).show();
                    // Cập nhật giao diện để phản ánh việc sản phẩm đã bị bỏ thích
                } else {
                    Toast.makeText(context, "Có lỗi xảy ra!", Toast.LENGTH_SHORT).show();
                }
            }
        }, context, ActionType.LIKE_PRODUCT);

        sanPhamThichDA.execute(khachHangId, sanPhamId);

    }

}

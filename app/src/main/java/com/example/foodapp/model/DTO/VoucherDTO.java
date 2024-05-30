package  com.example.foodapp.model.DTO;
import java.sql.Timestamp;

public class VoucherDTO {
    private int id;
    private String tenVoucher;
    private String chiTiet;
    private Timestamp ngayBatDau;
    private Timestamp ngayKetThuc;
    private int giaTri;
    private int soLuong;
    private boolean daXoa;
    private int loaiVoucher;

    // Constructors
    public VoucherDTO() {
    }


    public VoucherDTO(int id, String tenVoucher, String chiTiet, Timestamp ngayBatDau, Timestamp ngayKetThuc, int giaTri, int soLuong, boolean daXoa, int loaiVoucher) {
        this.id = id;
        this.tenVoucher = tenVoucher;
        this.chiTiet = chiTiet;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.giaTri = giaTri;
        this.soLuong = soLuong;
        this.daXoa = daXoa;
        this.loaiVoucher = loaiVoucher;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenVoucher() {
        return tenVoucher;
    }

    public void setTenVoucher(String tenVoucher) {
        this.tenVoucher = tenVoucher;
    }

    public String getChiTiet() {
        return chiTiet;
    }

    public void setChiTiet(String chiTiet) {
        this.chiTiet = chiTiet;
    }

    public Timestamp getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Timestamp ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Timestamp getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Timestamp ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public int getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(int giaTri) {
        this.giaTri = giaTri;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public boolean isDaXoa() {
        return daXoa;
    }

    public void setDaXoa(boolean daXoa) {
        this.daXoa = daXoa;
    }
    public int getLoaiVoucher() {
        return loaiVoucher;
    }

    public void setLoaiVoucher(int loaiVoucher) {
        this.loaiVoucher = loaiVoucher;
    }
}

DROP DATABASE IF EXISTS FoodApp;

CREATE DATABASE FoodApp;
USE FoodApp;

CREATE TABLE ThamSo (
    ThamSoID INT AUTO_INCREMENT PRIMARY KEY,
    ThoiGianVoucher INT DEFAULT 3,
    HeSoBan DOUBLE DEFAULT 1.2,
    PhiShip INT DEFAULT 20000
);

INSERT INTO ThamSo () VALUES ();


CREATE TABLE KhachHang (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    HoTen VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    SoDienThoai VARCHAR(20),
    Email VARCHAR(100),
    DaXoa BOOLEAN NOT NULL DEFAULT FALSE,
    TenTaiKhoan VARCHAR(100) NOT NULL UNIQUE ,
    MatKhau VARCHAR(100) NOT NULL,
    HinhAnh LONGBLOB
);


INSERT INTO KhachHang(HoTen,TenTaiKhoan,MatKhau) VALUES('Khách vãng lai','khachvanglai','123456');



CREATE TABLE SanPham (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    TenSP VARCHAR(255) NOT NULL,
    Loai INT NOT NULL,
    GiaNhap INT NOT NULL,
    SoLuongTon INT NOT NULL,
    HinhAnh LONGBLOB,
    DaXoa BOOLEAN NOT NULL DEFAULT FALSE
) CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE TABLE NhanVien (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    HoTen VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    SoDienThoai VARCHAR(20),
    VaiTro INT NOT NULL,
    Luong INT NOT NULL DEFAULT 5000000,
    DaXoa BOOLEAN NOT NULL DEFAULT FALSE,
    TenTaiKhoan VARCHAR(100) UNIQUE NOT NULL,
    MatKhau VARCHAR(100) NOT NULL,
    HinhAnh LONGBLOB
);


CREATE TABLE Voucher (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    TenVoucher VARCHAR(200) NOT NULL,
    ChiTiet VARCHAR(500) NOT NULL,
    NgayBatDau TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    NgayKetThuc TIMESTAMP NULL,
    GiaTri INT NOT NULL,
    SoLuong INT NOT NULL,
    DaXoa BOOLEAN NOT NULL DEFAULT FALSE
) CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE TABLE DiaChiGiaoHang (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    DiaChi VARCHAR(1000) NOT NULL,
    LoaiDiaChi INT NOT NULL,
    KhachHang_id INT NOT NULL,
    FOREIGN KEY (KhachHang_id) REFERENCES KhachHang(ID)
) CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE TABLE HoaDon (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    NgayHD TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    NhanVien_id INT NOT NULL,
    KhachHang_id INT NOT NULL,
    Voucher_id INT ,
    TongTien INT NOT NULL,
    TrangThai INT NOT NULL DEFAULT 0,
    DaThanhToan BOOLEAN NOT NULL DEFAULT FALSE,
    GhiChu VARCHAR(1000),
    FOREIGN KEY (NhanVien_id) REFERENCES NhanVien(ID),
    FOREIGN KEY (KhachHang_id) REFERENCES KhachHang(ID),
    FOREIGN KEY (Voucher_id) REFERENCES Voucher(ID)
) CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE TABLE ChiTietHoaDon (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    HoaDon_id INT NOT NULL,
    SanPham_id INT NOT NULL,
    SoLuong INT NOT NULL,
    DonGiaBan INT NOT NULL,
    FOREIGN KEY (HoaDon_id) REFERENCES HoaDon(ID),
    FOREIGN KEY (SanPham_id) REFERENCES SanPham(ID)
) CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE TABLE BinhLuan (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    NoiDung VARCHAR(500) NOT NULL,
    KhachHang_id INT NOT NULL,
    SanPham_id INT NOT NULL,
    FOREIGN KEY (KhachHang_id) REFERENCES KhachHang(ID),
    FOREIGN KEY (SanPham_id) REFERENCES SanPham(ID)
) CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE TABLE SanPhamThich (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    KhachHang_id INT NOT NULL,
    SanPham_id INT NOT NULL,
    FOREIGN KEY (KhachHang_id) REFERENCES KhachHang(ID),
    FOREIGN KEY (SanPham_id) REFERENCES SanPham(ID)
) CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Trigger chỉnh ngày kết thúc của voucher
DELIMITER //

CREATE TRIGGER before_insert_voucher
BEFORE INSERT ON Voucher
FOR EACH ROW
BEGIN
    DECLARE ThoiGian INT;
    SET ThoiGian = (SELECT ThoiGianVoucher FROM ThamSo LIMIT 1);
    SET NEW.NgayKetThuc = DATE_ADD(NEW.NgayBatDau, INTERVAL ThoiGian DAY);
END; //

DELIMITER ;


-- trigger cập nhật tồn kho
DELIMITER //

CREATE TRIGGER after_insert_chitiethoadon
AFTER INSERT ON ChiTietHoaDon
FOR EACH ROW
BEGIN
    UPDATE SanPham
    SET SoLuongTon = SoLuongTon - NEW.SoLuong
    WHERE ID = NEW.SanPham_id;
END; //

DELIMITER ;



-- trigger cập nhật số lượng voucher
DELIMITER //

CREATE TRIGGER after_insert_hoadon
AFTER INSERT ON HoaDon
FOR EACH ROW
BEGIN
    IF NEW.Voucher_id IS NOT NULL THEN
        UPDATE Voucher
        SET SoLuong = SoLuong - 1
        WHERE ID = NEW.Voucher_id;
    END IF;
END; //

DELIMITER ;



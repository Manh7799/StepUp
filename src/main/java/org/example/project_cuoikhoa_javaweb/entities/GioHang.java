package org.example.project_cuoikhoa_javaweb.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "GioHang")
public class GioHang {
    @Id
    @Column(name = "MaGioHang", nullable = false)
    @NotEmpty(message = "Bạn cần phải nhập mã sản phẩm")
    private String maGioHang;

    @Column(name = "TenSanPham", nullable = false, length = 100)
    @NotEmpty(message = "Bạn cần phải nhập tên sản phẩm")
    private String tenSanPham;

    @Column(name = "Anh", nullable = true, length = 255)
    private String anh;

    @Column(name = "DonGia", nullable = false)
    private int donGia;

    @Column(name = "SoLuong", nullable = false)
    private int soLuong;

    @Column(name = "ThanhTien", nullable = false)
    private int thanhTien;

    public String getMaGioHang() {
        return maGioHang;
    }

    public void setMaGioHang(String maGioHang) {
        this.maGioHang = maGioHang;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(int thanhTien) {
        this.thanhTien = thanhTien;
    }

}


package org.example.project_cuoikhoa_javaweb.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "HoaDonChiTiet")
public class HoaDonChiTiet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "IdNguoiDung", nullable = false, length = 15)
    private String idNguoiDung;

    @Column(name = "MaHoaDon", nullable = false, length = 25)
    private String maHoaDon;

    @Column(name = "MaSanPham", nullable = false, length = 25)
    private String maSanPham;

    @Column(name = "TenSanPham", nullable = true, length = 100)
    private String tenSanPham;

    @Column(name = "AnhSanPham", nullable = true, length = 255)
    private String anhSanPham;

    @Column(name = "SoLuong", nullable = true)
    private int soLuong;

    @Column(name = "GiaTien", nullable = true)
    private float giaTien;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdNguoiDung() {
        return idNguoiDung;
    }

    public void setIdNguoiDung(String idNguoiDung) {
        this.idNguoiDung = idNguoiDung;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getAnhSanPham() {
        return anhSanPham;
    }

    public void setAnhSanPham(String anhSanPham) {
        this.anhSanPham = anhSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(float giaTien) {
        this.giaTien = giaTien;
    }
}

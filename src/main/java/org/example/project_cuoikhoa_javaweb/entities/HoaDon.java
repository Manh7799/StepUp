package org.example.project_cuoikhoa_javaweb.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

import javax.xml.crypto.Data;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "HoaDon")
public class HoaDon {
    @Id
    @Column(name = "MaHoaDon", nullable = false, length = 25)
    private String maHoaDon;

    @Column(name = "IdNguoiDung", nullable = false, length = 15)
    private String idNguoiDung;

    @Column(name = "NguoiMua", nullable = false, length = 50)
    private String nguoiMua;

    @Column(name = "DiaChi", nullable = true, length = 255)
    private String diaChi;

    @Column(name = "Email", nullable = true, length = 255)
    private String email;

    @Column(name = "SoDienThoai", nullable = true, length = 10)
    private String soDienThoai;

    @Column(name = "GhiChu", nullable = true, length = 255)
    private String ghiChu;

    @Column(name = "NgayDatHang", nullable = true)
    private LocalDate ngayDatHang;

    @Column(name = "Size", nullable = true, length = 255)
    private String size;

    @Column(name = "TongTien", nullable = true)
    private float tongTien;

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getIdNguoiDung() {
        return idNguoiDung;
    }

    public void setIdNguoiDung(String idNguoiDung) {
        this.idNguoiDung = idNguoiDung;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getNguoiMua() {
        return nguoiMua;
    }

    public void setNguoiMua(String nguoiMua) {
        this.nguoiMua = nguoiMua;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public LocalDate getNgayDatHang() {
        return ngayDatHang;
    }

    public void setNgayDatHang(LocalDate ngayDatHang) {
        this.ngayDatHang = ngayDatHang;
    }
}

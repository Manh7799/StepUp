package org.example.project_cuoikhoa_javaweb.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table(name = "SanPham")
public class SanPham{

    @Id
    @Column(name = "MaSanPham", nullable = false, length = 25)
    @NotEmpty(message = "Bạn cần phải nhập mã sản phẩm")
    private String maSanPham;

    @Column(name = "TenSanPham", nullable = false, length = 100)
    @NotEmpty(message = "Bạn cần phải nhập tên sản phẩm")
    private String tenSanPham;

    @Column(name = "MoTa", nullable = true, length = 255)
    private String moTa;

    @Column(name = "Anh", nullable = true, length = 255)
    private String anh;

    @Column(name = "GiaTien", nullable = false)
    @NotNull(message = "Bạn cần phải nhập giá sản phẩm")
    @Min(value = 0, message = "Bạn phải nhập giá sản phẩm lớn hơn 100000")
    private int giaTien;

    @Column(name = "SoLuong", nullable = false)
    private int soLuong;

    @Column(name = "NoiDung", nullable = true, length = 1000)
    private String noiDung;

    @Column(name = "NgayTao", nullable = true)
    private Date ngayTao;

    @Column(name = "NgayDuyet", nullable = true)
    private Date ngayDuyet;

    @Column(name = "NgayCapNhat", nullable = true)
    private Date ngayCapNhat;

    @Column(name = "NguoiDuyet", nullable = true, length = 50)
    private String nguoiDuyet;

    @Column(name = "DaDuyet", nullable = true)
    private int daDuyet;

    @Column(name = "MaHang", nullable = true, length = 10)
    private String maHang;

    @Column(name = "MaNhaCungCap", nullable = true, length = 25)
    private String maNhaCungCap;

    @Column(name = "MaChuDe", nullable = true, length = 15)
    private String maChuDe;

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

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public int getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(int giaTien) {
        this.giaTien = giaTien;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgayDuyet() {
        return ngayDuyet;
    }

    public void setNgayDuyet(Date ngayDuyet) {
        this.ngayDuyet = ngayDuyet;
    }

    public Date getNgayCapNhat() {
        return ngayCapNhat;
    }

    public void setNgayCapNhat(Date ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }

    public String getNguoiDuyet() {
        return nguoiDuyet;
    }

    public void setNguoiDuyet(String nguoiDuyet) {
        this.nguoiDuyet = nguoiDuyet;
    }

    public int getDaDuyet() {
        return daDuyet;
    }

    public void setDaDuyet(int daDuyet) {
        this.daDuyet = daDuyet;
    }

    public String getMaHang() {
        return maHang;
    }

    public void setMaHang(String maHang) {
        this.maHang = maHang;
    }

    public String getMaNhaCungCap() {
        return maNhaCungCap;
    }

    public void setMaNhaCungCap(String maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
    }

    public String getMaChuDe() {
        return maChuDe;
    }

    public void setMaChuDe(String maChuDe) {
        this.maChuDe = maChuDe;
    }
}

package org.example.project_cuoikhoa_javaweb.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "NhaCungCap")
public class NhaCungCap {
    @Id
    @Column(name = "MaNhaCungCap", nullable = false, length = 25)
    @NotEmpty(message = "Bạn cần phải nhập mã nhà cung cấp")
    private String maNhaCungCap;

    @Column(name = "TenNhaCungCap", nullable = false, length = 255)
    private String tenNhaCungCap;

    @Column(name = "DienThoai", nullable = true, length = 10)
    private String dienThoai;

    @Column(name = "Email", nullable = true, length = 50)
    private String email;

    @Column(name = "DiaChi", nullable = true, length = 255)
    private String diaChi;

    public String getMaNhaCungCap() {
        return maNhaCungCap;
    }

    public void setMaNhaCungCap(String maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
    }

    public String getTenNhaCungCap() {
        return tenNhaCungCap;
    }

    public void setTenNhaCungCap(String tenNhaCungCap) {
        this.tenNhaCungCap = tenNhaCungCap;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
}

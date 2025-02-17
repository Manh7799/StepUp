package org.example.project_cuoikhoa_javaweb.entities;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "NguoiDung")
public class NguoiDung {
    @Id
    @Column(name = "IdNguoiDung", unique = true, nullable = false, length = 15)
    private String idNguoiDung;
    @Column(name = "TenDangNhap", nullable = true, length = 50)
    private String tenDangNhap;
    @Column(name = "MatKhau", nullable = true, length = 255)
    private String matKhau;
    @Column(name = "DienThoai", nullable = true, length = 15)
    private String dienThoai;
    @Column(name = "Email", nullable = true, length = 100)
    private String email;
    @Column(name = "VaiTro", nullable = true, length = 15)
    private String vaiTro;
    @Column(name = "NgayTao", nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayTao;

    public String getIdNguoiDung() {
        return idNguoiDung;
    }

    public void setIdNguoiDung(String idNguoiDung) {
        this.idNguoiDung = idNguoiDung;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
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

    public String getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }
}
